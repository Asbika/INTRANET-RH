package com.giantLink.Hiring.recrutementservice.services;

import com.giantLink.Hiring.recrutementservice.models.requests.CampaignRequest;
import com.giantLink.Hiring.recrutementservice.models.requests.CampaignUpdateRequest;
import com.giantLink.Hiring.recrutementservice.models.response.CampaignResponse;

import java.util.List;

public interface CampaignService extends CrudServices<CampaignRequest,CampaignResponse, CampaignUpdateRequest,Long>{
    List<CampaignResponse> get(String name);
}
