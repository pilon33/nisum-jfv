package com.nisum.test.service.impl;


import com.nisum.test.dto.phone.PhoneDto;
import com.nisum.test.dto.phone.PhoneMapper;
import com.nisum.test.dto.phone.PhoneRequest;
import com.nisum.test.exception.ResourceNotFoundException;
import com.nisum.test.model.Phone;
import com.nisum.test.model.User;
import com.nisum.test.repository.PhoneRepository;
import com.nisum.test.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PhoneServiceImpl implements PhoneService {

    private PhoneRepository phoneRepository;

    private PhoneMapper phoneMapper;


    @Autowired
    public PhoneServiceImpl(PhoneRepository phoneRepository, PhoneMapper phoneMapper) {
        this.phoneRepository = phoneRepository;
        this.phoneMapper = phoneMapper;
    }

    /**
     * Create or update a list of phones for a given user
     *
     * @param createOrUpdateRequest - request representing a list of phones to save or update
     * @return void
     */
    @Override
    @Transactional
    public List<PhoneDto> saveOrUpdateForUser(List<PhoneRequest> createOrUpdateRequest, User user) {
        return createOrUpdateRequest.stream().map(request -> {
            Phone phone = getForSaveOrUpdate(request);
            phone.setUser(user);
            Phone savedPhone = phoneRepository.save(phone);
            return phoneMapper.toDto(savedPhone);
        }).collect(Collectors.toList());
    }

    private Phone getForSaveOrUpdate(PhoneRequest request) {
        if (request.getUuid() != null) {
            Phone foundPhone = phoneRepository.findById(request.getUuid()).orElseThrow(() -> new ResourceNotFoundException("Phone whit uuid" + request.getUuid().toString() + " not found"));
            phoneMapper.updateModel(request, foundPhone);
            return foundPhone;
        } else {
            return phoneMapper.createFromRequest(request);
        }
    }
}
