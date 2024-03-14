package com.giantLink.Hiring.recrutementservice.mappers;

import com.giantLink.Hiring.recrutementservice.entities.Candidate; 
import com.giantLink.Hiring.recrutementservice.models.requests.CandidateRequest;
import com.giantLink.Hiring.recrutementservice.models.response.CandidateResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CandidateMapper extends ApplicationMapper<CandidateRequest, CandidateResponse, Candidate>{
    CandidateMapper INSTANCE = Mappers.getMapper(CandidateMapper.class);
    Candidate responseToEntity(CandidateResponse candidateResponse);
	
}
