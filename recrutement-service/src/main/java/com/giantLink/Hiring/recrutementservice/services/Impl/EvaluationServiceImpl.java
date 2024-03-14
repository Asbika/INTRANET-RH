package com.giantLink.Hiring.recrutementservice.services.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.giantLink.Hiring.recrutementservice.entities.Evaluation;
import com.giantLink.Hiring.recrutementservice.mappers.EvaluationMapper;
import com.giantLink.Hiring.recrutementservice.models.requests.EvaluationAddRequest;
import com.giantLink.Hiring.recrutementservice.repositories.EvaluationRepository;
import com.giantLink.Hiring.recrutementservice.services.EvaluationService;

@Service
public class EvaluationServiceImpl implements EvaluationService {

    @Autowired
    EvaluationRepository evaluationRepository;
    @Override
    public void add(EvaluationAddRequest evaluationAddRequest) {
        Evaluation evaluation= EvaluationMapper.INSTANCE.requestToEntity(evaluationAddRequest);
        evaluationRepository.save(evaluation);
    }
}
