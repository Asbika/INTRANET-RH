package com.giantLink.Hiring.recrutementservice.services;

import com.giantLink.Hiring.recrutementservice.models.requests.RoleRequest; 
import com.giantLink.Hiring.recrutementservice.models.requests.RoleUpdateRequest;
import com.giantLink.Hiring.recrutementservice.models.response.RoleResponse;

import java.util.List;

public interface RoleService extends  CrudServices<RoleRequest, RoleResponse, RoleUpdateRequest, Long>{

    // Give permission to role
    RoleResponse addPermissionToRole(Long roleId, Long permissionId);

    // Delete permission from role
    RoleResponse deletePermissionFromRole(Long roleId, Long permissionId);

    // Role Search
    List<RoleResponse> searchRolesByKeyword(String roleName);

}
