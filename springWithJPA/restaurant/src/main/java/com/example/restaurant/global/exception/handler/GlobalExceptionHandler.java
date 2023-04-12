package com.example.restaurant.global.exception.handler;

import com.example.restaurant.global.error.ErrorCode;
import com.example.restaurant.global.error.ErrorResponse;
import com.example.restaurant.global.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.example.restaurant.global.error.ErrorCode.INPUT_INVALID_VALUE;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    protected ResponseEntity<ErrorResponse> handleRuntimeException(BusinessException e) {
        final ErrorCode errorCode = e.getErrorCode();
        final ErrorResponse response =
                ErrorResponse.builder()
                        .errorMessage(errorCode.getMessage())
                        .businessCode(errorCode.getCode())
                        .build();
        log.warn(e.getMessage());
        return ResponseEntity.status(errorCode.getStatus()).body(response);
    }

    @ExceptionHandler
    protected ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException e) {
        final ErrorResponse response = ErrorResponse.of(INPUT_INVALID_VALUE, e.getBindingResult());
        log.warn(e.getMessage());
        return new ResponseEntity<>(response, BAD_REQUEST);
    }
}