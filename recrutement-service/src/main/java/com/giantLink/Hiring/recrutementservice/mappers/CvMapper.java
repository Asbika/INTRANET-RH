package com.giantLink.Hiring.recrutementservice.mappers;

import org.mapstruct.Mapper; 
import org.mapstruct.factory.Mappers;
import com.giantLink.Hiring.recrutementservice.entities.Cv;
import com.giantLink.Hiring.recrutementservice.models.requests.CvRequest;
import com.giantLink.Hiring.recrutementservice.models.response.CvResponse;

@Mapper(componentModel = "spring")
public interface CvMapper extends ApplicationMapper<CvRequest, CvResponse, Cv> {
	
	CvMapper INSTANCE= Mappers.getMapper(CvMapper.class);

}
