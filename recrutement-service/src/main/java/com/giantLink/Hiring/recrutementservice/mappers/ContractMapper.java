package com.giantLink.Hiring.recrutementservice.mappers;

import org.mapstruct.*; 
import org.mapstruct.factory.Mappers;
import com.giantLink.Hiring.recrutementservice.entities.Contract;
import com.giantLink.Hiring.recrutementservice.models.response.ContractResponse;
import com.giantLink.Hiring.recrutementservice.models.requests.ContractRequest;
import com.giantLink.Hiring.recrutementservice.models.requests.ContractUpdateRequest;

@Mapper(componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValueMapMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT,
        nullValueIterableMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface ContractMapper extends ApplicationMapper<ContractRequest, ContractResponse, Contract>{
    ContractMapper INSTANCE = Mappers.getMapper(ContractMapper.class);
    void updateEntity(ContractUpdateRequest request, @MappingTarget Contract entity);

}
