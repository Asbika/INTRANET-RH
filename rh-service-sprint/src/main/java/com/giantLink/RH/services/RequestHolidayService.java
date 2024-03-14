package com.giantLink.RH.services;

import com.giantLink.RH.entities.RequestHoliday;
import com.giantLink.RH.models.request.RequestHolidayRequest;
import com.giantLink.RH.models.response.RequestHolidayResponse;
import com.giantLink.RH.models.response.SuccessResponse;

import java.util.List;

public interface RequestHolidayService extends CrudService<RequestHolidayRequest, RequestHolidayResponse, RequestHoliday,Long>
{
    SuccessResponse addRequestHoliday(RequestHolidayRequest request);
    SuccessResponse drop(Long id);
    List<RequestHolidayResponse> getAllRequestHolidaysByIdEmployee(Long employee_id);

}
