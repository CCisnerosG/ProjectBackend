package com.example.ProjectBackend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class TokenExpiredException extends RuntimeException{
    public TokenExpiredException(){
        super("Token expired, create a new one");
    }
}
