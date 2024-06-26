package com.example.ProjectBackend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class UserNameAlreadyExistsException extends RuntimeException{
    public UserNameAlreadyExistsException() {
        super("Username Already Exists");
    }
}
