package com.giantLink.Hiring.recrutementservice.services;

import java.util.List;


import com.giantLink.Hiring.recrutementservice.models.requests.CvRequest;
import com.giantLink.Hiring.recrutementservice.models.requests.CvUpdateRequest;
import com.giantLink.Hiring.recrutementservice.models.response.CvResponse;
public interface CvService {
	
	public CvResponse add(CvRequest request);
    public List<CvResponse> get();
    public CvResponse get(Long id);
    public CvResponse update(CvUpdateRequest request, Long id);
    public void delete(Long id);

}
