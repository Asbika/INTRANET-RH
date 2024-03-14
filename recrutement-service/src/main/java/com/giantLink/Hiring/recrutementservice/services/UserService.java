package com.giantLink.Hiring.recrutementservice.services;

import com.giantLink.Hiring.recrutementservice.models.requests.UserRequest; 
import com.giantLink.Hiring.recrutementservice.models.requests.UserUpdateRequest;
import com.giantLink.Hiring.recrutementservice.models.requests.LoginRequest;
import com.giantLink.Hiring.recrutementservice.models.response.LoginResponse;
import com.giantLink.Hiring.recrutementservice.models.response.UserResponse;

public interface UserService extends CrudServices<UserRequest, UserResponse, UserUpdateRequest, Long>{
    LoginResponse login(LoginRequest loginRequest);
}
