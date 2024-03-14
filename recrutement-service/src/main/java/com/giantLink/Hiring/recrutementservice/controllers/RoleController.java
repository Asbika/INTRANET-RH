package com.giantLink.Hiring.recrutementservice.controllers;

import jakarta.validation.Valid; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.giantLink.Hiring.recrutementservice.models.requests.RoleRequest;
import com.giantLink.Hiring.recrutementservice.models.requests.RoleUpdateRequest;
import com.giantLink.Hiring.recrutementservice.models.response.RoleResponse;
import com.giantLink.Hiring.recrutementservice.services.RoleService;
import org.springframework.security.access.prepost.PreAuthorize;
import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RoleController {
    @Autowired
    RoleService roleService;

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_CREATE')")
    public ResponseEntity<RoleResponse> add(@Valid @RequestBody RoleRequest roleAddRequest){
        return new ResponseEntity<>(roleService.add(roleAddRequest), HttpStatus.CREATED);

    }

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_READ')")
    public ResponseEntity<List<RoleResponse>> get(){
        return new ResponseEntity<>(roleService.getAll(),HttpStatus.OK);
    }


    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('ROLE_UPDATE')")
    public ResponseEntity<RoleResponse> update(@PathVariable("id") Long id, @RequestBody RoleUpdateRequest request){
        RoleResponse roleResponse = roleService.update(request, id);
        return new ResponseEntity<>(roleResponse,HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_READ')")
    public ResponseEntity<RoleResponse> get(@PathVariable("id") Long id){
        return new ResponseEntity<>(roleService.get(id),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_DELETE')")
    public ResponseEntity delete(@PathVariable Long id){
        roleService.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
    
    @PostMapping("/Add-permission-to-role/{roleId}/{permissionId}")
    @PreAuthorize("hasAuthority('ROLE_ADD_PERMISSION')")
    public ResponseEntity<RoleResponse> addPermissionToRole(@PathVariable Long roleId,
                                                              @PathVariable Long permissionId){
        return new ResponseEntity<>(roleService.addPermissionToRole(roleId,permissionId),HttpStatus.CREATED);
    }
    @DeleteMapping("/Delete-permission-from-role/{roleId}/{permissionId}")
    @PreAuthorize("hasAuthority('ROLE_DELETE_PERMISSION')")
    public ResponseEntity<RoleResponse> deletePermissionFromRole(@PathVariable Long roleId,
                                                            @PathVariable Long permissionId){
        return new ResponseEntity<>(roleService.deletePermissionFromRole(roleId,permissionId),HttpStatus.NO_CONTENT);
    }

    // Role Search
    @GetMapping("/search")
    public ResponseEntity<List<RoleResponse>> searchRolesByKeyword(@RequestParam(defaultValue = "")
                                                                       String roleName){
        List<RoleResponse> roles = roleService.searchRolesByKeyword(roleName);
        return new ResponseEntity<>(roles,HttpStatus.OK);
    }

}
