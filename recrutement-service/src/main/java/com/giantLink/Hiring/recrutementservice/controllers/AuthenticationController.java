package com.giantLink.Hiring.recrutementservice.controllers;

import com.giantLink.Hiring.recrutementservice.services.UserService;
import jakarta.validation.Valid;
import com.giantLink.Hiring.recrutementservice.models.requests.LoginRequest;
import com.giantLink.Hiring.recrutementservice.models.response.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
    @Autowired
    UserService userService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest loginRequest){
        return new ResponseEntity<>(userService.login(loginRequest), HttpStatus.OK);
    }

}
