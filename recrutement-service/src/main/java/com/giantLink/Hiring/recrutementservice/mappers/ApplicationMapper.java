package com.giantLink.Hiring.recrutementservice.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

public interface ApplicationMapper<RQ,RS, ET> {
    ET requestToEntity(RQ request);
    RS entityToResponse(ET entity);
    List<RS> listToResponseList(List<ET> entities);
}
