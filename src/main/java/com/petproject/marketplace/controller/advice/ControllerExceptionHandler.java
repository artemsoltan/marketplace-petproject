package com.petproject.marketplace.controller.advice;

import com.petproject.marketplace.exception.NotAccessException;
import com.petproject.marketplace.exception.ProductDoesNotExistsException;
import com.petproject.marketplace.exception.UserDoesNotExistsException;
import com.petproject.marketplace.exception.UserExistsException;
import com.petproject.marketplace.util.ErrorModel;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler({UserExistsException.class})
    public ResponseEntity<?> handleUserExistsException(UserExistsException e) {
        ErrorModel errorModel = new ErrorModel("This user already exists.");
        return new ResponseEntity<>(errorModel, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({UserDoesNotExistsException.class})
    public ResponseEntity<?> handleUserDoesNotExistsException(UserDoesNotExistsException e) {
        ErrorModel errorModel = new ErrorModel("This user does not exists.");
        return new ResponseEntity<>(errorModel, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({ProductDoesNotExistsException.class})
    public ResponseEntity<?> handleProductDoesNotExistsException(ProductDoesNotExistsException e) {
        ErrorModel errorModel = new ErrorModel("This product does not exists.");
        return new ResponseEntity<>(errorModel, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({NotAccessException.class})
    public ResponseEntity<?> handleNotAccessException(NotAccessException e) {
        ErrorModel errorModel = new ErrorModel("You dont have success.");
        return new ResponseEntity<>(errorModel, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler({HttpMessageNotReadableException.class, ConstraintViolationException.class, MethodArgumentNotValidException.class})
    public ResponseEntity<?> handleValidationException(Exception e) {
        ErrorModel errorModel = new ErrorModel("Invalid input. Please check your data and try again.");
        return new ResponseEntity<>(errorModel, HttpStatus.BAD_REQUEST);
    }
}