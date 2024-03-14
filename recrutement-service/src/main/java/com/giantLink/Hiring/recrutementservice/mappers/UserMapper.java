package com.giantLink.Hiring.recrutementservice.mappers;

import com.giantLink.Hiring.recrutementservice.entities.User;
import com.giantLink.Hiring.recrutementservice.models.requests.UserRequest;
import com.giantLink.Hiring.recrutementservice.models.response.UserResponse;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import com.giantLink.Hiring.recrutementservice.models.requests.UserUpdateRequest;

@Mapper(componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValueMapMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT,
        nullValueIterableMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface UserMapper extends ApplicationMapper<UserRequest, UserResponse, User>{

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    void updateEntity(UserUpdateRequest request, @MappingTarget User entity);


}
