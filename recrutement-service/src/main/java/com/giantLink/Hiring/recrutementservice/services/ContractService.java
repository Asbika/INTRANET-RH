package com.giantLink.Hiring.recrutementservice.services;

import com.giantLink.Hiring.recrutementservice.models.requests.ContractRequest;
import com.giantLink.Hiring.recrutementservice.models.requests.ContractUpdateRequest;
import com.giantLink.Hiring.recrutementservice.models.response.ContractResponse;

import java.util.List;

public interface ContractService extends CrudServices<ContractRequest, ContractResponse, ContractUpdateRequest,Long>{
    List<ContractResponse> get(String name);
}
