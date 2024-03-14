package com.giantLink.Hiring.recrutementservice.controllers;

import com.giantLink.Hiring.recrutementservice.models.requests.UserRequest; 
import com.giantLink.Hiring.recrutementservice.models.requests.UserUpdateRequest;
import com.giantLink.Hiring.recrutementservice.models.response.SuccessResponse;
import com.giantLink.Hiring.recrutementservice.models.response.UserResponse;
import com.giantLink.Hiring.recrutementservice.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;


    @PostMapping
    @PreAuthorize("hasAuthority('USER_CREATE')")
    public ResponseEntity<UserResponse> addUser(@RequestBody @Valid UserRequest request) {
        UserResponse userResponse = userService.add(request);
        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('USER_READ_ALL')")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<UserResponse> userResponses = userService.getAll();
        return new ResponseEntity<>(userResponses, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('USER_READ')")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
        UserResponse userResponse = userService.get(id);
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('USER_UPDATE')")
    public ResponseEntity<UserResponse> updateUser(@PathVariable Long id, @RequestBody UserUpdateRequest request) {
        UserResponse userResponse = userService.update(request, id);
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('USER_DELETE')")
    public ResponseEntity<SuccessResponse> deleteUser(@PathVariable Long id) {
        userService.delete(id);
        SuccessResponse successResponse = SuccessResponse.builder()
                .message("User deleted successfully")
                .build();
        return new ResponseEntity<>(successResponse, HttpStatus.OK);
    }
}


