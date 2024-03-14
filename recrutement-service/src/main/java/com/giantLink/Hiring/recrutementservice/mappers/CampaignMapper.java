package com.giantLink.Hiring.recrutementservice.mappers;

import com.giantLink.Hiring.recrutementservice.entities.Campaign; 
import com.giantLink.Hiring.recrutementservice.models.requests.CampaignRequest;
import com.giantLink.Hiring.recrutementservice.models.requests.CampaignUpdateRequest;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import com.giantLink.Hiring.recrutementservice.models.response.CampaignResponse;

@Mapper(componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValueMapMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT,
        nullValueIterableMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)

public interface CampaignMapper extends ApplicationMapper<CampaignRequest, CampaignResponse, Campaign>{
    CampaignMapper INSTANCE = Mappers.getMapper(CampaignMapper.class);
    void updateEntity(CampaignUpdateRequest request, @MappingTarget Campaign entity);

}
