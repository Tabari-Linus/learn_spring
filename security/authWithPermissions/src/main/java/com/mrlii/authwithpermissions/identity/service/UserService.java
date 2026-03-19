package com.mrlii.authwithpermissions.identity.service;

import com.mrlii.authwithpermissions.identity.dto.request.UserRegistrationRequest;
import com.mrlii.authwithpermissions.commons.model.dto.response.SuccessResponse;

public interface UserService {

    SuccessResponse createUser(UserRegistrationRequest request);
}
