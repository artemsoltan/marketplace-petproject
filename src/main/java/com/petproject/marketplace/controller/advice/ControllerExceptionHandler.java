package com.petproject.marketplace.controller.advice;

import com.petproject.marketplace.exception.NotAccessException;
import com.petproject.marketplace.exception.ProductDoesNotExistsException;
import com.petproject.marketplace.exception.UserDoesNotExistsException;
import com.petproject.marketplace.exception.UserExistsException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler({UserExistsException.class})
    public ProblemDetail handleUserExistsException(UserExistsException e) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "This user already exists.");
    }

    @ExceptionHandler({UserDoesNotExistsException.class})
    public ProblemDetail handleUserDoesNotExistsException(UserDoesNotExistsException e) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, "This user does not exists.");
    }

    @ExceptionHandler({ProductDoesNotExistsException.class})
    public ProblemDetail handleProductDoesNotExistsException(ProductDoesNotExistsException e) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, "This product does not exists.");
    }

    @ExceptionHandler({NotAccessException.class})
    public ProblemDetail handleNotAccessException(NotAccessException e) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.FORBIDDEN, "You dont have success!");
    }

    @ExceptionHandler({HttpMessageNotReadableException.class, ConstraintViolationException.class, MethodArgumentNotValidException.class})
    public ProblemDetail handleValidationException(Exception e) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Invalid input. Please check your data and try again.");
    }
}