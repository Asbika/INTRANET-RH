package com.giantLink.Hiring.recrutementservice.mappers;

import org.mapstruct.Mapper; 
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import com.giantLink.Hiring.recrutementservice.entities.Qualification;
import com.giantLink.Hiring.recrutementservice.models.requests.QualificationRequest;
import com.giantLink.Hiring.recrutementservice.models.response.QualificationResponse;

@Mapper(componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValueMapMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT,
        nullValueIterableMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface QualificationMapper extends  ApplicationMapper<QualificationRequest, QualificationResponse, Qualification> {
    QualificationMapper INSTANCE = Mappers.getMapper(QualificationMapper.class);

}
