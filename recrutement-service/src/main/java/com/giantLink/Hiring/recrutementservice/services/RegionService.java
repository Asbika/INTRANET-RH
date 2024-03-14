package com.giantLink.Hiring.recrutementservice.services;

import com.giantLink.Hiring.recrutementservice.models.requests.RegionRequest;
import com.giantLink.Hiring.recrutementservice.models.requests.RegionUpdateRequest;
import com.giantLink.Hiring.recrutementservice.models.response.RegionResponse;

public interface RegionService extends CrudServices<RegionRequest, RegionResponse, RegionUpdateRequest, Long> {
}
