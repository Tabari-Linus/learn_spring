package com.mrlii.authwithpermissions.identity.service.impl;

import com.mrlii.authwithpermissions.commons.model.dto.response.SuccessResponse;
import com.mrlii.authwithpermissions.commons.model.exception.AlreadyExistException;
import com.mrlii.authwithpermissions.commons.model.exception.ResourceNotFoundException;
import com.mrlii.authwithpermissions.identity.entity.OrganizationOffice;
import com.mrlii.authwithpermissions.identity.repository.AccessLevelRespository;
import com.mrlii.authwithpermissions.identity.repository.OrganizationOfficeRespository;
import com.mrlii.authwithpermissions.identity.repository.UserRespository;
import com.mrlii.authwithpermissions.identity.dto.request.UserRegistrationRequest;
import com.mrlii.authwithpermissions.identity.entity.AccessLevel;
import com.mrlii.authwithpermissions.identity.entity.User;
import com.mrlii.authwithpermissions.identity.service.UserService;
import com.mrlii.authwithpermissions.util.Util;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRespository userRespository;
    private final AccessLevelRespository accessLevelRespository;
    private final OrganizationOfficeRespository organizationOfficeRespository;

    @Override
    public SuccessResponse createUser(UserRegistrationRequest request) {

        if(isEmailExist(request.email())){
            throw new AlreadyExistException("Email already exist");
        }

        User user = new User();
        user.setFirstName(request.firstName());
        user.setLastName(request.lastName());
        user.setEmail(request.email());
        user.setPassword(Util.byCryptPassWord(request.password()));
        user.setAccessLevel(getAccessLevelById(request.accessLevelId()));
        user.setOrganizationOffice(getOrganizationOfficeById(request.organizationOfficeId()));
        userRespository.save(user);
        return new SuccessResponse("User created successfully");
    }

    private boolean isEmailExist(String email) {
        return userRespository.existsByEmail(email);
    }

    private AccessLevel getAccessLevelById(Long id) {
        return accessLevelRespository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Access level not found"));
    }

    private OrganizationOffice getOrganizationOfficeById(Long id) {
        return organizationOfficeRespository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Organization office not found"));
    }

}
