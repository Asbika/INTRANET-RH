package com.giantLink.Hiring.recrutementservice.services.Impl;

import com.giantLink.Hiring.recrutementservice.entities.Role;
import com.giantLink.Hiring.recrutementservice.entities.User;
import com.giantLink.Hiring.recrutementservice.exceptions.ResourceAlreadyExist;
import com.giantLink.Hiring.recrutementservice.mappers.UserMapper;
import com.giantLink.Hiring.recrutementservice.repositories.RoleRepository;
import com.giantLink.Hiring.recrutementservice.repositories.UserRepository;
import com.giantLink.Hiring.recrutementservice.exceptions.ResourceNotFound;
import com.giantLink.Hiring.recrutementservice.models.requests.LoginRequest;
import com.giantLink.Hiring.recrutementservice.models.requests.UserRequest;
import com.giantLink.Hiring.recrutementservice.models.requests.UserUpdateRequest;
import com.giantLink.Hiring.recrutementservice.models.response.LoginResponse;
import com.giantLink.Hiring.recrutementservice.models.response.UserResponse;
import com.giantLink.Hiring.recrutementservice.services.UserService;
import com.giantLink.Hiring.recrutementservice.utilities.JwtUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtUtility jwtUtility;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    MessageSource messageSource;

    @Override
    public UserResponse add(UserRequest request) {
        // Check username is not already used
        if (userRepository.findByUsername(request.getUsername()).isPresent())
            throw new ResourceAlreadyExist("user", "username", request.getUsername());
        // Check if role exists
        Role role = roleRepository.findById(request.getRoleId())
                .orElseThrow(() -> new ResourceNotFound("role", "id", request.getRoleId().toString()));

        User user = UserMapper.INSTANCE.requestToEntity(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(role);
        return UserMapper.INSTANCE.entityToResponse(userRepository.save(user));
    }

    @Override
    public List<UserResponse> getAll() {
        List<User> users = userRepository.findAll();
        return UserMapper.INSTANCE.listToResponseList(users);
    }

    @Override
    public UserResponse get(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound(messageSource.getMessage("user.validation.notFound", new Object[] { id }, Locale.getDefault())));

        return UserMapper.INSTANCE.entityToResponse(user);
    }

    @Override
    public UserResponse update(UserUpdateRequest request, Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound(messageSource.getMessage("user.validation.notFound", new Object[] { id }, Locale.getDefault())));

        // Update user entity with new data
        UserMapper.INSTANCE.updateEntity(request, user);
        userRepository.save(user);

        if (request.getUsername() != null) {
            if (userRepository.findByUsername(request.getUsername()).isPresent())
                throw new ResourceAlreadyExist("user", "username", request.getUsername());

        }

        UserMapper.INSTANCE.updateEntity(request, user);

        if (request.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(request.getPassword()));
        }
        if (request.getRoleId() != null) {
            Role role = roleRepository.findById(request.getRoleId())
                    .orElseThrow(() -> new ResourceNotFound("role", "id", request.getRoleId().toString()));
            user.setRole(role);
        }

        return UserMapper.INSTANCE.entityToResponse(userRepository.save(user));
    }

    @Override
    public void delete(Long id) {
        userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound(messageSource.getMessage("user.validation.notFound", new Object[] { id }, Locale.getDefault())));

        userRepository.deleteById(id);
    }




    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getCin(), loginRequest.getPassword())
        );
        User user = userRepository.findByUsername(loginRequest.getCin()).get();
        Role role = roleRepository.findById(user.getRole().getId())
                .orElseThrow(() -> new ResourceNotFound("role", "id", user.getRole().getId().toString()));
        String token = jwtUtility.generateTokenWithRole(role, user);

        return LoginResponse.builder()
                .token(token)
                .build();
    }
}
