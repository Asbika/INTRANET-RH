package com.giantLink.Hiring.recrutementservice.models.response;

import com.giantLink.Hiring.recrutementservice.entities.Permission; 
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RoleResponse {

    private Long id;
    private String roleName;
    private List<Permission> permissions;

}
