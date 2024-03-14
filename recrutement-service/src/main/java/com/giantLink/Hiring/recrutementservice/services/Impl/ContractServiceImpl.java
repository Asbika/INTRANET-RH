package com.giantLink.Hiring.recrutementservice.services.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.giantLink.Hiring.recrutementservice.entities.Campaign;
import com.giantLink.Hiring.recrutementservice.entities.Contract;
import com.giantLink.Hiring.recrutementservice.exceptions.ResourceNotFound;
import com.giantLink.Hiring.recrutementservice.mappers.ContractMapper;
import com.giantLink.Hiring.recrutementservice.models.requests.ContractRequest;
import com.giantLink.Hiring.recrutementservice.models.requests.ContractUpdateRequest;
import com.giantLink.Hiring.recrutementservice.models.response.ContractResponse;
import com.giantLink.Hiring.recrutementservice.repositories.CampaignRepository;
import com.giantLink.Hiring.recrutementservice.repositories.ContractRepository;
import com.giantLink.Hiring.recrutementservice.services.ContractService;

import java.util.List;
@Service
public class ContractServiceImpl implements ContractService {
    @Autowired
    CampaignRepository campaignRepository;
    @Autowired
    ContractRepository contractRepository;
    @Override
    public ContractResponse add(ContractRequest request) {
        Contract contract = ContractMapper.INSTANCE.requestToEntity(request);
        Campaign campaign = campaignRepository.findById(request.getCampaign_id()).get();
        contract.setCampaign(campaign);
        contractRepository.save(contract);
        return ContractMapper.INSTANCE.entityToResponse(contract);
    }

    @Override

    public List<ContractResponse> getAll() {
        List<Contract> contract = contractRepository.findAll();
        return ContractMapper.INSTANCE.listToResponseList(contract);
    }

    @Override
    public ContractResponse get(Long id) {
        Contract contract = contractRepository.findById(id)
                .orElseThrow(()->new ResourceNotFound("This Contract","id",id.toString()));
        return ContractMapper.INSTANCE.entityToResponse(contract);
    }

    @Override
    public List<ContractResponse> get(String name) {
        List<Contract> contractResponses = contractRepository.findByName(name);
        if (contractResponses.isEmpty())
            throw new ResourceNotFound("This Contract","id",name);
        else
            return ContractMapper.INSTANCE.listToResponseList(contractResponses);
    }
    @Override
    public ContractResponse update(ContractUpdateRequest request, Long id) {
        Contract contract = contractRepository.findById(id)
                .orElseThrow(()->new ResourceNotFound("This Contract","id",id.toString()));
        ContractMapper.INSTANCE.updateEntity(request,contract);
        contractRepository.save(contract);
        return ContractMapper.INSTANCE.entityToResponse(contract);
    }

    @Override
    public void delete(Long id) {
        Contract contract = contractRepository.findById(id)
                .orElseThrow(()->new ResourceNotFound("This Contract","id",id.toString()));
        contractRepository.delete(contract);

    }


}
