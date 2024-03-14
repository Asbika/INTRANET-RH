package com.giantLink.Hiring.recrutementservice.services;

import com.giantLink.Hiring.recrutementservice.models.requests.InterviewAddRequest;
import com.giantLink.Hiring.recrutementservice.models.requests.InterviewUpdateRequest;
import com.giantLink.Hiring.recrutementservice.models.response.InterviewResponse;

public interface InterviewService extends CrudServices<InterviewAddRequest, InterviewResponse, InterviewUpdateRequest,Long>{

}
