package com.nisum.test.service;

import com.nisum.test.dto.user.UpdateUserRequest;
import com.nisum.test.dto.user.UserDto;
import com.nisum.test.dto.user.UserRequest;

import java.util.List;
import java.util.UUID;


public interface UserService {
    UserDto create(UserRequest userRequest);

    List<UserDto> getAll();

    UserDto findByUuid(UUID uuid);

    UserDto update(UUID uuid, UpdateUserRequest updateRequest);

    void delete(UUID uuid);
}
