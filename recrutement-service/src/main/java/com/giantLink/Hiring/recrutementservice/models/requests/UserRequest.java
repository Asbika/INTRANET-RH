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
public class UserRequest {

    @NotBlank
    @Length(min = 2, max = 20)
    private String username;
    @NotBlank
    private String password;
    @NotNull
    private Long roleId;
}
