package com.nisum.test.service;

import com.nisum.test.dto.phone.PhoneDto;
import com.nisum.test.dto.phone.PhoneRequest;
import com.nisum.test.model.User;

import java.util.List;


public interface PhoneService {
    List<PhoneDto> saveOrUpdateForUser(List<PhoneRequest> createOrUpdateRequest, User user);
}
