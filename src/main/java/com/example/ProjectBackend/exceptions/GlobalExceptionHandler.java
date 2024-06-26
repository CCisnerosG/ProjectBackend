package com.example.ProjectBackend.exceptions;

import com.example.ProjectBackend.responses.ResponseError;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ProblemDetail handleSecurityException(Exception exception) {
        ProblemDetail errorDetail = null;

        // TODO send this stack trace to an observability tool
        exception.printStackTrace();

//        if (exception instanceof BadCredentialsException) {
//            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(401), exception.getMessage());
//            errorDetail.setProperty("description", "The username or password is incorrect");
//            return errorDetail;
//        }
//
//        if (exception instanceof AccountStatusException) {
//            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403), exception.getMessage());
//            errorDetail.setProperty("description", "The account is locked");
//        }
//
//        if (exception instanceof AccessDeniedException) {
//            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403), exception.getMessage());
//            errorDetail.setProperty("description", "You are not authorized to access this resource");
//        }
//
//        if (exception instanceof SignatureException) {
//            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403), exception.getMessage());
//            errorDetail.setProperty("description", "The JWT signature is invalid");
//        }
//
//        if (exception instanceof ExpiredJwtException) {
//            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(440), exception.getMessage());
//            errorDetail.setProperty("description", "The JWT token has expired");
//        }

        if (errorDetail == null) {
            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(500), exception.getMessage());
            errorDetail.setProperty("description", "Unknown internal server error.");
        }

        return errorDetail;
    }

    @ExceptionHandler(value = {PokemonAlreadyExistsException.class})
    protected ResponseEntity<ResponseError> handleConflict(PokemonAlreadyExistsException ex, WebRequest request) {
        return new ResponseEntity<ResponseError>(new ResponseError(ex.getMessage(), 410), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {TokenExpiredException.class})
    protected ResponseEntity<ResponseError> handleConflict(TokenExpiredException ex, WebRequest request) {
        return new ResponseEntity<ResponseError>(new ResponseError(ex.getMessage(), 440), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {UserNameAlreadyExistsException.class})
    protected ResponseEntity<ResponseError> handleConflict(UserNameAlreadyExistsException ex, WebRequest request) {
        return new ResponseEntity<ResponseError>(new ResponseError(ex.getMessage(), 410), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {UserAlreadyExistsException.class})
    protected ResponseEntity<ResponseError> handleConflict(UserAlreadyExistsException ex, WebRequest request) {
        return new ResponseEntity<ResponseError>(new ResponseError(ex.getMessage(), 410), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {UserNotFoundException.class})
    protected ResponseEntity<ResponseError> handleConflict(UserNotFoundException ex, WebRequest request) {
        return new ResponseEntity<ResponseError>(new ResponseError(ex.getMessage(), 404), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {ShoppingCartNotFound.class})
    protected ResponseEntity<ResponseError> handleConflict(ShoppingCartNotFound ex, WebRequest request) {
        return new ResponseEntity<ResponseError>(new ResponseError(ex.getMessage(), 404), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {PokemonIdNullException.class})
    protected ResponseEntity<ResponseError> handleConflict(PokemonIdNullException ex, WebRequest request) {
        return new ResponseEntity<ResponseError>(new ResponseError(ex.getMessage(), 401), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {LoginUnsuccessfulException.class})
    protected ResponseEntity<ResponseError> handleConflict(LoginUnsuccessfulException ex, WebRequest request) {
        return new ResponseEntity<ResponseError>(new ResponseError(ex.getMessage(), 401), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {UserAndPasswordEmptyException.class})
    protected ResponseEntity<ResponseError> handleConflict(UserAndPasswordEmptyException ex, WebRequest request) {
        return new ResponseEntity<ResponseError>(new ResponseError(ex.getMessage(), 401), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {IncorrectPasswordException.class})
    protected ResponseEntity<ResponseError> handleConflict(IncorrectPasswordException ex, WebRequest request) {
        return new ResponseEntity<ResponseError>(new ResponseError(ex.getMessage(), 401), HttpStatus.BAD_REQUEST);
    }
}