package com.mrlii.authwithpermissions.commons.model.dto.response;

import com.mrlii.authwithpermissions.commons.model.enums.Status;

public record ApiResponse<T>(
        Status status,
        T data,
        String message,
        Integer code
) {

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(Status.SUCCESS, data, null, null);
    }

    public static <T> ApiResponse<T> fail(T data) {
        return new ApiResponse<>(Status.FAIL, data, null, null);
    }

    public static <T> ApiResponse<T> error(String message) {
        return new ApiResponse<>(Status.ERROR, null, message, null);
    }

    public static <T> ApiResponse<T> error(String message, int code) {
        return new ApiResponse<>(Status.ERROR, null, message, code);
    }

    public static <T> ApiResponse<T> error(String message, int code, T data) {
        return new ApiResponse<>(Status.ERROR, data, message, code);
    }
}