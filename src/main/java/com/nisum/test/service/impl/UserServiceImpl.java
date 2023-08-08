package com.nisum.test.service.impl;


import com.nisum.test.security.AuthTokenData;
import com.nisum.test.dto.user.UpdateUserRequest;
import com.nisum.test.dto.user.UserDto;
import com.nisum.test.dto.user.UserMapper;
import com.nisum.test.dto.user.UserRequest;
import com.nisum.test.exception.ResourceNotFoundException;
import com.nisum.test.exception.UnprocessableEntityException;
import com.nisum.test.model.User;
import com.nisum.test.repository.UserRepository;
import com.nisum.test.service.PhoneService;
import com.nisum.test.service.UserService;
import com.nisum.test.security.JwtUtils;
import com.nisum.test.security.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PhoneService phoneService;

    private final UserMapper userMapper;

    private String emailRegex;

    private String passRegex;


    @Autowired
    public UserServiceImpl(UserRepository repository, UserMapper userMapper, PhoneService phoneService) {
        this.userRepository = repository;
        this.userMapper = userMapper;
        this.phoneService = phoneService;
    }

    /**
     * Create and persist a new user
     *
     * @param userRequest - request representing a new User
     * @return UserDto
     */
    @Override
    @Transactional
    public UserDto create(UserRequest userRequest) {
        validateEmail((userRequest.getEmail()));
        validatePassword((userRequest.getPassword()));
        var user = userMapper.toModel(userRequest);
        String jwtToken = JwtUtils.generateTokenAuth(AuthTokenData.builder().name(user.getName()).email(user.getEmail()).build());
        user.setToken(jwtToken);
        user.setPassword(SecurityUtils.encryptPassword(userRequest.getPassword()));
        user.setLastLogin(LocalDateTime.now());
        User savedUser = userRepository.save(user);
        phoneService.saveOrUpdateForUser(userRequest.getPhones(), savedUser);
        return userMapper.toDto(savedUser);
    }

    @Override
    public UserDto update(UUID uuid, UpdateUserRequest updateRequest) {
        User theUser = userRepository.findById(uuid).orElseThrow(() -> new ResourceNotFoundException("User whit uuid" + uuid + " not found"));

        if (updateRequest.getEmail() != null) {
            validateEmail((updateRequest.getEmail()));
        }

        if (updateRequest.getPassword() != null) {
            validatePassword((updateRequest.getPassword()));
            theUser.setPassword(SecurityUtils.encryptPassword(updateRequest.getPassword()));
        }
        User theUswer = userMapper.updateModelFromUpdateRequest(updateRequest, theUser);
        String jwtToken = JwtUtils.generateTokenAuth(AuthTokenData.builder().name(theUser.getName()).email(theUser.getEmail()).build());
        theUser.setToken(jwtToken);
        theUser.setLastLogin(LocalDateTime.now());
        User savedUser = userRepository.save(theUser);
        if (updateRequest.getPhones() != null) {
            phoneService.saveOrUpdateForUser(updateRequest.getPhones(), savedUser);
        }
        return userMapper.toDto(savedUser);
    }

    private void validateEmail(String email) {
        if (userRepository.existsByEmail(email))
            throw new UnprocessableEntityException("User with email" + email + " already exists");
        Pattern emailPattern = Pattern.compile(emailRegex);
        Matcher matcher = emailPattern.matcher(email);
        if (!matcher.matches()) {
            throw new UnprocessableEntityException("Wrong email address format");
        }
    }

    private void validatePassword(String password) {
        Pattern passwordPattern = Pattern.compile(passRegex);
        Matcher matcher = passwordPattern.matcher(password);
        if (!matcher.matches()) {
            throw new UnprocessableEntityException("Wrong password format. Min length 8, must contain at least 1 number and 1 letter and 1 capital letter");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserDto> getAll() {
        List<User> users = userRepository.findAll();
        return userMapper.toDto(users);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDto findByUuid(UUID uuid) {
        User user = userRepository.findById(uuid).orElseThrow(
                () -> new ResourceNotFoundException("User not found"));
        return userMapper.toDto(user);
    }

    @Override
    public void delete(UUID uuid) {
        if (!userRepository.existsById(uuid)) {
            throw new ResourceNotFoundException("The user does not exists");
        }
        userRepository.deleteById(uuid);
    }

    @Value("${app.regex.email}")
    public void setEmailRegex(String emailRegex) {
        this.emailRegex = emailRegex;
    }

    @Value("${app.regex.password}")
    public void setPasswordRegex(String passwordRegex) {
        this.passRegex = passwordRegex;
    }
}
