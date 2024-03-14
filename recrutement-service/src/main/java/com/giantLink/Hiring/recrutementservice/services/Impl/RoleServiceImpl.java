package com.giantLink.Hiring.recrutementservice.services.Impl;

import com.giantLink.Hiring.recrutementservice.entities.Permission;
import com.giantLink.Hiring.recrutementservice.entities.Role;
import com.giantLink.Hiring.recrutementservice.mappers.RoleMapper;
import com.giantLink.Hiring.recrutementservice.repositories.PermissionRepository;
import com.giantLink.Hiring.recrutementservice.repositories.RoleRepository;
import com.giantLink.Hiring.recrutementservice.exceptions.ResourceAlreadyExist;
import com.giantLink.Hiring.recrutementservice.exceptions.ResourceNotFound;
import com.giantLink.Hiring.recrutementservice.models.requests.RoleRequest;
import com.giantLink.Hiring.recrutementservice.models.requests.RoleUpdateRequest;
import com.giantLink.Hiring.recrutementservice.models.response.RoleResponse;
import com.giantLink.Hiring.recrutementservice.services.RoleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PermissionRepository permissionRepository;
    @Autowired
    MessageSource messageSource;


    @Override
    public RoleResponse add(@RequestBody @Valid RoleRequest request) {
        Optional<Role> roleName = roleRepository.findByRoleName(request.getRoleName());
        if (roleName.isPresent())
            throw new ResourceAlreadyExist("This Role","id",roleName.get().getId().toString());

        List<Permission> permissions = new ArrayList<>();
        request.getPermissionsId().forEach(permissionId -> {
            Permission permission = permissionRepository.findById(permissionId)
                    .orElseThrow(()->new ResourceNotFound("This Permission","id",permissionId.toString()));
            permissions.add(permission);
        });
        Role role = new Role();
        role.setRoleName(request.getRoleName());
        role.setPermissions(permissions);
        roleRepository.save(role);
        return RoleMapper.INSTANCE.entityToResponse(role);
    }

    @Override
    public List<RoleResponse> getAll() {
        List<Role> roles = roleRepository.findAll();
        return RoleMapper.INSTANCE.listToResponseList(roles);
    }

    @Override
    public RoleResponse get(Long id) {
        Optional<Role> optional = roleRepository.findById(id);
        if (optional.isPresent()) {
            return RoleMapper.INSTANCE.entityToResponse(optional.get());
        } else {
            String errorMessage = messageSource.getMessage("role.validation.notFound", new Object[] { id }, LocaleContextHolder.getLocale());
            throw new ResourceNotFound(errorMessage);
        }
    }

    @Override

    public RoleResponse update(RoleUpdateRequest request, Long aLong) {
        return null;
    }


    @Override
    public void delete(Long aLong) {

    }
    // Give permission to role
    @Override
    public RoleResponse addPermissionToRole(Long roleId, Long permissionId){
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
    public RoleResponse deletePermissionFromRole(Long roleId, Long permissionId){
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new ResourceNotFound("This role","id",roleId.toString()));
        Permission permission =permissionRepository.findById(permissionId)
                .orElseThrow(() -> new ResourceNotFound("This permission","id",roleId.toString()));
        role.getPermissions().remove(permission);
        roleRepository.save(role);
        return RoleMapper.INSTANCE.entityToResponse(role);
    }

    // Role Search
    @Override
    public List<RoleResponse> searchRolesByKeyword(String roleName) {
        List<Role> roles = roleRepository.findByRoleContaining(roleName);
        return RoleMapper.INSTANCE.listToResponseList(roles);
    }


}
