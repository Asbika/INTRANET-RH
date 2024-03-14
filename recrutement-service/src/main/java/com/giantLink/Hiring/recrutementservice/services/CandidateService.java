package com.giantLink.Hiring.recrutementservice.services;


import java.util.List;

import com.giantLink.Hiring.recrutementservice.models.requests.CandidateRequest;
import com.giantLink.Hiring.recrutementservice.models.response.CandidateResponse;

public interface CandidateService {
	public CandidateResponse add(CandidateRequest request);
    public List<CandidateResponse> get();
    public CandidateResponse get(Long id);
    public CandidateResponse update(CandidateRequest request, Long id);
    public void delete(Long id);

}
