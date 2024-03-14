package com.giantLink.Hiring.recrutementservice.mappers;

import org.mapstruct.Mapper; 
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import com.giantLink.Hiring.recrutementservice.entities.Candidacy;
import com.giantLink.Hiring.recrutementservice.models.requests.CandidacyRequest;
import com.giantLink.Hiring.recrutementservice.models.response.CandidacyResponse;
@Mapper(componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValueMapMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT,
        nullValueIterableMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface CandidacyMapper extends ApplicationMapper<CandidacyRequest, CandidacyResponse, Candidacy> {

    CandidacyMapper INSTANCE = Mappers.getMapper(CandidacyMapper.class);

}
