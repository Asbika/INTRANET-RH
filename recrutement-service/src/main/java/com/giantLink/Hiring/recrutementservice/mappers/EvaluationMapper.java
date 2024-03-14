package com.giantLink.Hiring.recrutementservice.mappers;

import com.giantLink.Hiring.recrutementservice.entities.Evaluation;
import com.giantLink.Hiring.recrutementservice.models.requests.EvaluationAddRequest;
import com.giantLink.Hiring.recrutementservice.models.response.EvaluationResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EvaluationMapper extends ApplicationMapper<EvaluationAddRequest, EvaluationResponse, Evaluation>{
    EvaluationMapper INSTANCE= Mappers.getMapper(EvaluationMapper.class);


}
