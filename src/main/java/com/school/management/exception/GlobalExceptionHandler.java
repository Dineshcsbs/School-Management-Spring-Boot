package com.school.management.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.school.management.dto.ErrorDetailDto;



@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BadRequestServiceAlertException.class)
    public ResponseEntity<ErrorDetailDto> handleSecurityException(BadRequestServiceAlertException exception) {
        exception.printStackTrace();
        new ErrorDetailDto();
		ErrorDetailDto errorDetail=ErrorDetailDto.builder().status(HttpStatus.BAD_REQUEST.value()).detail(exception.getMessage()).build();
        return ResponseEntity.badRequest().body(errorDetail);
    }
}