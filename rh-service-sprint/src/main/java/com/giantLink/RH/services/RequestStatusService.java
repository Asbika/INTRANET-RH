package com.giantLink.RH.services;

import com.giantLink.RH.entities.RequestStatus;
import com.giantLink.RH.enums.State;
import com.giantLink.RH.models.request.RequestStatusRequest;
import com.giantLink.RH.models.request.RequestStatusUpdateRequest;
import com.giantLink.RH.models.response.RequestStatusResponse;
import java.util.List;

public interface RequestStatusService extends CrudService<RequestStatusRequest, RequestStatusResponse, RequestStatus, Long> {
    RequestStatusResponse processHolidayRequest(Long requestStatusId);

    List<RequestStatusResponse> getByStatusName(State state);

    RequestStatusResponse updateStatus(RequestStatusUpdateRequest RequestStatusUpdateRequest, Long requestHolidayId);
    
    RequestStatusResponse updateRequestStatus(Long requestId, RequestStatusRequest requestStatusRequest);

}
