package com.giantLink.Hiring.recrutementservice.services;

import com.giantLink.Hiring.recrutementservice.models.requests.PermissionRequest; 
import com.giantLink.Hiring.recrutementservice.models.response.PermissionResponse;
import com.giantLink.Hiring.recrutementservice.models.response.RoleResponse;

import java.util.List;

public interface PermissionService extends CrudServices<PermissionRequest,PermissionResponse, PermissionRequest,Long>{
    // Grant permission to role
    RoleResponse grantPermissionToRole(Long roleId, Long permissionId);
    // Revoke permission from role
    RoleResponse revokePermissionFromRole(Long roleId, Long permissionId);

    // search
    List<PermissionResponse> searchPermissionsByKeyword(String label);
}
