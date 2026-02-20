package com.mrlii.springdatajpa_library_mgt_sys.dto.response;

import lombok.Builder;

import java.time.Instant;
import java.util.List;

@Builder
public record ApiResponse<T>(
        boolean success,
        String message,
        T data,
        ErrorDetails errorDetails,
        Instant timestamp
) {

    @Builder
    public record ErrorDetails(
            String code,
            String path,
            List<String> details
    ){}

    public static <T> ApiResponse<T> success(T data){
        return ApiResponse.<T>builder().message("Success").data(data).success(true).timestamp(Instant.now()).build();
    }

    public static <T> ApiResponse<T> success(String message){
        return ApiResponse.<T>builder().message(message).success(true).timestamp(Instant.now()).build();
}

    public static <T> ApiResponse<T> success(String message, T data){
        return ApiResponse.<T>builder().message(message).data(data).success(true).timestamp(Instant.now()).build();
    }

    public static <T> ApiResponse<T> created(String message, T data){
        return ApiResponse.<T>builder().message(message).data(data).success(true).timestamp(Instant.now()).build();
    }

    public static <T> ApiResponse<T> error(
            String message,
            String code,
            String path,
            List<String> details
    ){
        return ApiResponse.<T>builder()
                .message(message)
                .errorDetails(new ErrorDetails(code, path, details))
                .success(false)
                .timestamp(Instant.now())
                .build();
    }

    public static <T> ApiResponse<T> error(
            String message
    ){
        return ApiResponse.<T>builder()
                .message(message)
                .success(false)
                .timestamp(Instant.now())
                .build();
    }
}
