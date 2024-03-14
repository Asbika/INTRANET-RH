package com.giantLink.Hiring.recrutementservice.controllers;

import com.giantLink.Hiring.recrutementservice.models.requests.PermissionRequest;
import com.giantLink.Hiring.recrutementservice.models.response.PermissionResponse;
import com.giantLink.Hiring.recrutementservice.models.response.RoleResponse;
import com.giantLink.Hiring.recrutementservice.models.response.SuccessResponse;
import com.giantLink.Hiring.recrutementservice.services.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/permission")
public class PermissionController {

    @Autowired
    PermissionService permissionService;

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('PERMISSION_READ_ALL')")
    public ResponseEntity<List<PermissionResponse>> get(){
        return new ResponseEntity<>(permissionService.getAll(),HttpStatus.OK);
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('PERMISSION_READ')")
    public ResponseEntity<PermissionResponse> get(@PathVariable("id")  Long id){
        return new ResponseEntity<>(permissionService.get(id),HttpStatus.OK);
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('PERMISSION_UPDATE')")
    public ResponseEntity<PermissionResponse> update(@RequestBody PermissionRequest permissionRequest,
                                                     @PathVariable("id") Long id){
        return new ResponseEntity<>(permissionService.update(permissionRequest,id),HttpStatus.CREATED);
    }
    @PostMapping("/Grant-permission-to-role/{roleId}/{permissionId}")
    @PreAuthorize("hasAuthority('PERMISSION_GRANT_TO_ROLE')")
    public ResponseEntity<SuccessResponse> grantPermissionToRole(@PathVariable Long roleId,
                                                                 @PathVariable Long permissionId){
        permissionService.grantPermissionToRole(roleId,permissionId);
        SuccessResponse successResponse = SuccessResponse.builder()
                .message("Permission Granted successfully")
                .build();
        return new ResponseEntity<>(successResponse,HttpStatus.CREATED);
    }
    @DeleteMapping("/Revoke-permission-from-role/{roleId}/{permissionId}")
    @PreAuthorize("hasAuthority('PERMISSION_REMOVE_FROM_ROLE')")
    public ResponseEntity<SuccessResponse> deletePermissionToRole(@PathVariable Long roleId,
                                                               @PathVariable Long permissionId){
        permissionService.revokePermissionFromRole(roleId,permissionId);
        SuccessResponse successResponse = SuccessResponse.builder()
                .message("Permission Revoked successfully")
                .build();
        return new ResponseEntity<>(successResponse,HttpStatus.OK);
    }
    // search
    @GetMapping("/search")
    public ResponseEntity<List<PermissionResponse>> searchPermissionsByKeyword(
            @RequestParam(defaultValue = "") String label) {
        List<PermissionResponse> permissionResponses = permissionService.searchPermissionsByKeyword(label);
        return ResponseEntity.ok(permissionResponses);
    }
}
