package com.kooku.library.exception;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<AppError> catchResourceNotFoundException(ResourceNotFoundException e) {
        return new ResponseEntity<>(new AppError(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<AppError> catchDuplicateResourceException(DuplicateResourceException e) {
        return new ResponseEntity<>(new AppError(HttpStatus.CONFLICT.value(), e.getMessage()), HttpStatus.CONFLICT);
    }

    @ExceptionHandler
    public ResponseEntity<AppError> catchEmptyFieldException(EmptyFieldException e) {
        return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<AppError> catchBadCredentialsException(BadCredentialsException e) {
        return new ResponseEntity<>(new AppError(HttpStatus.UNAUTHORIZED.value(),
                "The username or password is incorrect"), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler
    public ResponseEntity<AppError> catchAccountStatusException(AccountStatusException e) {
        return new ResponseEntity<>(new AppError(HttpStatus.FORBIDDEN.value(),
                "The account is locked"), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler
    public ResponseEntity<AppError> catchAccessDeniedException(AccessDeniedException e) {
        return new ResponseEntity<>(new AppError(HttpStatus.FORBIDDEN.value(),
                "You are not authorized to access this resource"), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler
    public ResponseEntity<AppError> catchSignatureException(SignatureException e) {
        return new ResponseEntity<>(new AppError(HttpStatus.FORBIDDEN.value(),
                "The JWT signature is invalid"), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler
    public ResponseEntity<AppError> catchExpiredJwtException(ExpiredJwtException e) {
        return new ResponseEntity<>(new AppError(HttpStatus.FORBIDDEN.value(),
                "The JWT token has expired"), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler
    public ResponseEntity<AppError> catchInternalServerError(InternalError e) {
        return new ResponseEntity<>(new AppError(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Unknown internal server error"), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
