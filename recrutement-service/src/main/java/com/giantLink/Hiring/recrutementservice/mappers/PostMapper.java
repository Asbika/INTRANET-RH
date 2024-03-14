package com.giantLink.Hiring.recrutementservice.mappers;

import com.giantLink.Hiring.recrutementservice.models.requests.PostRequest;
import com.giantLink.Hiring.recrutementservice.models.requests.PostUpdateRequest;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import com.giantLink.Hiring.recrutementservice.entities.Post;
import com.giantLink.Hiring.recrutementservice.models.response.PostResponse;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValueMapMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT,
        nullValueIterableMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface PostMapper extends ApplicationMapper<PostRequest, PostResponse, Post>{
    PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);
    void updateEntity(PostUpdateRequest request, @MappingTarget Post entity);
}
