package com.giantLink.Hiring.recrutementservice.services;

import com.giantLink.Hiring.recrutementservice.enums.Telecommuting;
import com.giantLink.Hiring.recrutementservice.models.requests.PostRequest;
import com.giantLink.Hiring.recrutementservice.models.requests.PostUpdateRequest;
import com.giantLink.Hiring.recrutementservice.models.response.CandidacyResponse;
import com.giantLink.Hiring.recrutementservice.models.response.CandidateResponse;
import com.giantLink.Hiring.recrutementservice.models.response.PostResponse;

import java.util.List;

public interface PostService extends CrudServices<PostRequest, PostResponse, PostUpdateRequest, Long> {
     List<CandidateResponse> getPotentielCandidates(Long poste);
     List<CandidacyResponse> getPotentialCandidacies(Long poste);
     List<PostResponse> getByTelecommuting(Telecommuting telecommuting);
     PostResponse getByName(String name);

}
