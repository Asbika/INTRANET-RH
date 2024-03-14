package com.giantLink.Hiring.recrutementservice.services.Impl;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service;
import com.giantLink.Hiring.recrutementservice.entities.Permission;
import com.giantLink.Hiring.recrutementservice.entities.Role;
import com.giantLink.Hiring.recrutementservice.exceptions.ResourceNotFound;
import com.giantLink.Hiring.recrutementservice.mappers.PermissionMapper;
import com.giantLink.Hiring.recrutementservice.mappers.RoleMapper;
import com.giantLink.Hiring.recrutementservice.models.requests.PermissionRequest;
import com.giantLink.Hiring.recrutementservice.models.response.PermissionResponse;
import com.giantLink.Hiring.recrutementservice.models.response.RoleResponse;
import com.giantLink.Hiring.recrutementservice.repositories.PermissionRepository;
import com.giantLink.Hiring.recrutementservice.repositories.RoleRepository;
import com.giantLink.Hiring.recrutementservice.services.PermissionService;
import java.util.List;
import java.util.Optional;

@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    PermissionRepository permissionRepository;
    @Autowired
    RoleRepository roleRepository;

    @Override
    public PermissionResponse add(PermissionRequest request) {
//        Optional<Permission> permission = permissionRepository.findByLabel(request.getLabel().toString());
//        if (permission.isPresent()){
//            throw new ResourceAlreadyExist( "This Permission","id",permission.get().getId().toString());
//        }
//        else {
//           Permission permission1 = PermissionMapper.INSTANCE.requestToEntity(request);
//           permissionRepository.save(permission1);
//           return PermissionMapper.INSTANCE.entityToResponse(permission1);
//        }
        return null;
    }

    @Override
    public List<PermissionResponse> getAll() {
        List<Permission> permissions = permissionRepository.findAll();
        return PermissionMapper.INSTANCE.listToResponseList(permissions);
    }

    @Override

    public PermissionResponse get(Long id) {
        Permission permission = permissionRepository.findById(id)
                .orElseThrow(()->new ResourceNotFound("This permission","id",id.toString()));
        return PermissionMapper.INSTANCE.entityToResponse(permission);
    }

    @Override
    public PermissionResponse update(PermissionRequest request, Long id) {
        Optional<Permission> permissionId = permissionRepository.findById(id);
        if (permissionId.isPresent()){
            Permission permission = permissionId.get();
            permission.setLabel(request.getLabel());
            permissionRepository.save(permission);
            return PermissionMapper.INSTANCE.entityToResponse(permission);
        }else {
            throw new ResourceNotFound("This permission","id",id.toString());
        }
    }

    @Override
    public void delete(Long id) {
        Permission permission = permissionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("This permission","id",id.toString()));
        permissionRepository.delete(permission);
    }
    // Give permission to role
    @Override
    public RoleResponse grantPermissionToRole(Long roleId, Long permissionId){
        Role role = roleRepository.findById(roleId)
                .orElseThrow(()->new ResourceNotFound("This role","id",roleId.toString()));
        Permission permission = permissionRepository.findById(permissionId)
                .orElseThrow(()-> new ResourceNotFound("This permission", "id", permissionId.toString()));
        role.getPermissions().add(permission);
        roleRepository.save(role);
        return RoleMapper.INSTANCE.entityToResponse(role);
    }
    // Delete permission from role
    @Override
    public RoleResponse revokePermissionFromRole(Long roleId, Long permissionId){
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new ResourceNotFound("This role","id",roleId.toString()));
        Permission permission =permissionRepository.findById(permissionId)
                .orElseThrow(() -> new ResourceNotFound("This permission","id",roleId.toString()));
        role.getPermissions().remove(permission);
        roleRepository.save(role);
        return RoleMapper.INSTANCE.entityToResponse(role);
    }
    // search
    @Override
    public List<PermissionResponse> searchPermissionsByKeyword(String label) {
        List<Permission> permissions = permissionRepository.findByPermissionContaining(label);
        return PermissionMapper.INSTANCE.listToResponseList(permissions);
    }
}
