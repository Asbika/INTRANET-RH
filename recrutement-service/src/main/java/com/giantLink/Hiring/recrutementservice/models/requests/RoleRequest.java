package com.giantLink.Hiring.recrutementservice.models.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.security.Permission;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RoleRequest {
    @NotBlank
    private String roleName;
    @NotNull
    private List<Long> permissionsId;

}
