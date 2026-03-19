package com.mrlii.authwithpermissions.identity.controller;

import com.mrlii.authwithpermissions.commons.model.dto.response.ApiResponse;
import com.mrlii.authwithpermissions.identity.dto.request.UserRegistrationRequest;
import com.mrlii.authwithpermissions.identity.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<?>> registerUser(@Valid UserRegistrationRequest request) {
        return ResponseEntity.ok(ApiResponse.success(userService.createUser(request)));
    }


}
