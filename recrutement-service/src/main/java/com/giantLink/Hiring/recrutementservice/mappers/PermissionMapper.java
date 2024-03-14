package com.giantLink.Hiring.recrutementservice.mappers;

import com.giantLink.Hiring.recrutementservice.entities.Permission; 
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import com.giantLink.Hiring.recrutementservice.models.requests.PermissionRequest;
import com.giantLink.Hiring.recrutementservice.models.response.PermissionResponse;

@Mapper(componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValueMapMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT,
        nullValueIterableMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface PermissionMapper extends ApplicationMapper<PermissionRequest, PermissionResponse, Permission>{
    PermissionMapper INSTANCE  = Mappers.getMapper(PermissionMapper.class);
}
