package com.example.ProjectBackend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class UserAndPasswordEmptyException extends RuntimeException {
    public UserAndPasswordEmptyException(){
        super("Empty email and/or password");
    }
}
