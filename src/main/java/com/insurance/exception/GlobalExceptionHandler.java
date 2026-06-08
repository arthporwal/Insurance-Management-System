package com.insurance.exception;

import com.insurance.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(
            ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse>
    handleResourceNotFound(
            ResourceNotFoundException ex) {

        ErrorResponse error =
                new ErrorResponse(
                        ex.getMessage(),
                        404);

        return new ResponseEntity<>(
                error,
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidClaimStateException.class)
    public ResponseEntity<ErrorResponse>
    handleInvalidClaimStateException(
            InvalidClaimStateException ex) {

        ErrorResponse error =
                new ErrorResponse(
                        ex.getMessage(),
                        400);

        return new ResponseEntity<>(
                error,
                HttpStatus.BAD_REQUEST);
    }
}