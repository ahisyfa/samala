package com.casa.samala.controller.advice;

import com.casa.samala.controller.response.ApiResponse;
import com.casa.samala.controller.response.ApiResponseStatusEnum;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

/**
 * GlobalExceptionHandlerController
 *
 * @author Ahmad Isyfalana Amin
 * @version $Id: GlobalExceptionHandlerController.java, v 0.1 2024-01-20  11.52 Ahmad Isyfalana Amin Exp $
 */
@RestControllerAdvice
public class GlobalExceptionHandlerController {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiResponse<String>> constraintViolationException(ConstraintViolationException exception) {
        ApiResponse<String> apiResponse = new ApiResponse<>();
        apiResponse.setResponseStatusInfo(ApiResponseStatusEnum.FAILED);
        apiResponse.setErrorMessage(exception.getMessage());

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(apiResponse);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ApiResponse<String>> responseStatusException(ResponseStatusException exception) {
        ApiResponse<String> apiResponse = new ApiResponse<>();
        apiResponse.setResponseStatusInfo(ApiResponseStatusEnum.FAILED);
        apiResponse.setErrorMessage(exception.getReason());

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(apiResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>> constraintViolationException(Exception exception) {
        ApiResponse<String> apiResponse = new ApiResponse<>();
        apiResponse.setResponseStatusInfo(ApiResponseStatusEnum.FAILED);
        apiResponse.setErrorMessage(exception.getMessage());

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(apiResponse);
    }
}