package com.example.ProjectBackend.controllers;


import com.example.ProjectBackend.dtos.LoginUserDto;
import com.example.ProjectBackend.dtos.RegisterUserDto;
import com.example.ProjectBackend.entities.Role;
import com.example.ProjectBackend.entities.User;
import com.example.ProjectBackend.exceptions.IncorrectPasswordException;
import com.example.ProjectBackend.exceptions.UserAlreadyExistsException;
import com.example.ProjectBackend.exceptions.UserAndPasswordEmptyException;
import com.example.ProjectBackend.exceptions.UserNotFoundException;
import com.example.ProjectBackend.repositories.UserRepository;
import com.example.ProjectBackend.responses.LoginResponse;
import com.example.ProjectBackend.services.AuthenticationService;
import com.example.ProjectBackend.services.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/auth")
@RestController
public class AuthenticationController {
    private final JwtService jwtService;
    private final AuthenticationService authenticationService;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @Autowired
    UserRepository userRepository;

    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody RegisterUserDto registerUserDto) {
        List<User> userList = userRepository.findAll();
        for (User user : userList) {
            if (user.getEmail().equals(registerUserDto.getEmail())) {
                throw new UserAlreadyExistsException();
            }
        }
        User registeredUser = authenticationService.signup(registerUserDto);

        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
        if (loginUserDto.getEmail().isEmpty()) {
            if (loginUserDto.getPassword().isEmpty()) {
                throw new UserAndPasswordEmptyException();
            } else {
                throw new UserNotFoundException();
            }
        }
        if (loginUserDto.getPassword().isEmpty()) {
            throw new IncorrectPasswordException();
        }
        User authenticatedUser = authenticationService.authenticate(loginUserDto);
        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse()
                .setToken(jwtToken)
                .setExpiresIn(jwtService.getExpirationTime())
                .setUserId(authenticatedUser.getId())
                .setRole(authenticatedUser.getRole().getName().toString());

        return ResponseEntity.ok(loginResponse);
    }
}