package com.giantLink.Hiring.recrutementservice.models.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateRequest {
    private String username;
    private String password;
    private Long roleId;
}
