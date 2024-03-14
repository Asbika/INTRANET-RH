package com.giantLink.Hiring.recrutementservice.mappers;

import com.giantLink.Hiring.recrutementservice.entities.Interview;
import com.giantLink.Hiring.recrutementservice.models.requests.InterviewAddRequest;
import com.giantLink.Hiring.recrutementservice.models.response.InterviewResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface InterviewMapper extends ApplicationMapper<InterviewAddRequest, InterviewResponse, Interview>{

    InterviewMapper INSTANCE= Mappers.getMapper(InterviewMapper.class);


}
