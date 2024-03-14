package com.giantLink.Hiring.recrutementservice.models.response;

import com.giantLink.Hiring.recrutementservice.entities.Role;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@Builder
public class UserResponse {
    private Long id;
    private String username;
    private Role role;
}
