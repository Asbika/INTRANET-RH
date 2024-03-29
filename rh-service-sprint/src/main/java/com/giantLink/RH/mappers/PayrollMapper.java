package com.giantLink.RH.mappers;

import com.giantLink.RH.entities.Employee;
import com.giantLink.RH.entities.Payroll;
import com.giantLink.RH.models.request.EmployeeRequest;
import com.giantLink.RH.models.request.PayrollRequest;
import com.giantLink.RH.models.response.EmployeeResponse;
import com.giantLink.RH.models.response.PayrollResponse;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValueMapMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT,
        nullValueIterableMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface PayrollMapper extends ApplicationMapper<PayrollRequest, PayrollResponse, Payroll>
{
    PayrollMapper INSTANCE = Mappers.getMapper(PayrollMapper.class);
}
