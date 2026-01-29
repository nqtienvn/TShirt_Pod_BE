package com.shirt.pod.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.shirt.pod.model.dto.response.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(AppException.class)
    public ResponseEntity<ApiResponse> generateAppException(AppException exception) {
        ErrorCode errorCode = exception.getErrorCode();
        ApiResponse apiResponse = ApiResponse.builder()
                .code(errorCode.getCode())
                .message(exception.getFormattedMessage())
                .build();
        return ResponseEntity.status(errorCode.getHttpStatusCode()).body(apiResponse);
    }
}
