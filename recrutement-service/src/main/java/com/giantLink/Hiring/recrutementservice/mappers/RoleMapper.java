package com.giantLink.Hiring.recrutementservice.mappers;

import com.giantLink.Hiring.recrutementservice.entities.Role; 
import org.mapstruct.*;
import com.giantLink.Hiring.recrutementservice.models.requests.RoleRequest;
import com.giantLink.Hiring.recrutementservice.models.requests.RoleUpdateRequest;
import com.giantLink.Hiring.recrutementservice.models.response.RoleResponse;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValueMapMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT,
        nullValueIterableMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface RoleMapper extends ApplicationMapper<RoleRequest, RoleResponse, Role> {

    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

    //void updateEntity(RoleUpdateRequest request, @MappingTarget Role entity);
}

