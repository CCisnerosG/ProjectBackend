package com.example.ProjectBackend.controllers;


import com.example.ProjectBackend.dtos.LoginUserDto;
import com.example.ProjectBackend.dtos.RegisterUserDto;
import com.example.ProjectBackend.entities.User;
import com.example.ProjectBackend.responses.LoginResponse;
import com.example.ProjectBackend.services.AuthenticationService;
import com.example.ProjectBackend.services.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody RegisterUserDto registerUserDto) {
        User registeredUser = authenticationService.signup(registerUserDto);

        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
        User authenticatedUser = authenticationService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse().setToken(jwtToken).setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }
}