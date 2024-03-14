package com.giantLink.Hiring.recrutementservice.mappers;

import com.giantLink.Hiring.recrutementservice.entities.Region; 
import com.giantLink.Hiring.recrutementservice.models.requests.RegionRequest;
import com.giantLink.Hiring.recrutementservice.models.requests.RegionUpdateRequest;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import com.giantLink.Hiring.recrutementservice.models.response.RegionResponse;
 
@Mapper(componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValueMapMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT,
        nullValueIterableMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface RegionMapper extends ApplicationMapper<RegionRequest, RegionResponse, Region>{
    RegionMapper INSTANCE = Mappers.getMapper(RegionMapper.class);
    void updateEntity(RegionUpdateRequest request, @MappingTarget Region entity);
}
