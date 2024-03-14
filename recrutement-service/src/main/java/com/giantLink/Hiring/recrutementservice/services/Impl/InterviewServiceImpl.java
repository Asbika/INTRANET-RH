package com.giantLink.Hiring.recrutementservice.services.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.giantLink.Hiring.recrutementservice.entities.Interview;
import com.giantLink.Hiring.recrutementservice.exceptions.ResourceNotFound;
import com.giantLink.Hiring.recrutementservice.mappers.CandidateMapper;
import com.giantLink.Hiring.recrutementservice.mappers.EvaluationMapper;
import com.giantLink.Hiring.recrutementservice.mappers.InterviewMapper;
import com.giantLink.Hiring.recrutementservice.models.requests.EvaluationAddRequest;
import com.giantLink.Hiring.recrutementservice.models.requests.InterviewAddRequest;
import com.giantLink.Hiring.recrutementservice.models.requests.InterviewUpdateRequest;
import com.giantLink.Hiring.recrutementservice.models.response.CandidateResponse;
import com.giantLink.Hiring.recrutementservice.models.response.InterviewResponse;
import com.giantLink.Hiring.recrutementservice.repositories.InterviewRepository;
import com.giantLink.Hiring.recrutementservice.services.CandidateService;
import com.giantLink.Hiring.recrutementservice.services.EvaluationService;
import com.giantLink.Hiring.recrutementservice.services.InterviewService;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class InterviewServiceImpl implements InterviewService {

    @Autowired
    InterviewRepository interviewRepository;
    @Autowired
    EvaluationService evaluationService;
    @Autowired
    CandidateService candidateService;

    @Override
    public InterviewResponse add(@Valid InterviewAddRequest request) {
        // Check if candidate exists
        CandidateResponse candidate = candidateService.get(request.getCandidateId());

        Interview interview = InterviewMapper.INSTANCE.requestToEntity(request);
        EvaluationAddRequest evaluation = new EvaluationAddRequest("no conclusion yet", "no comments yet");
        interview.setEvaluation(EvaluationMapper.INSTANCE.requestToEntity(evaluation));
        interview.setCandidate(CandidateMapper.INSTANCE.responseToEntity(candidate));
        interview.setStatus("SCHEDULED");
        interviewRepository.save(interview);

        return InterviewMapper.INSTANCE.entityToResponse(interview);
    }

    @Override
    public List<InterviewResponse> getAll() {
        List<Interview> list = interviewRepository.findAll();
        return InterviewMapper.INSTANCE.listToResponseList(list);
    }

    @Override
    public InterviewResponse get(Long id) {
        Optional<Interview> optional = interviewRepository.findById(id);
        if (optional.isPresent()) {
            return InterviewMapper.INSTANCE.entityToResponse(optional.get());
        } else {
            throw new ResourceNotFound("Interview", "Id", id.toString());
        }
    }

    @Override
    public InterviewResponse update(@Valid InterviewUpdateRequest request, Long id) {
    	Optional<Interview> optional=interviewRepository.findById(id);
        if(optional.isPresent()){
            optional.get().setTitle(request.getTitle());
            optional.get().setInterviewDate(request.getInterviewDate());
            optional.get().setLocation(request.getLocation());
            optional.get().setInterviewer(request.getInterviewer());
            optional.get().setStatus(request.getStatus());
            //Modify the Evaluation related the interview
            optional.get().getEvaluation().setComments(request.getComments());
            optional.get().getEvaluation().setConclusion(request.getConclusion());
            interviewRepository.save(optional.get());
            return InterviewMapper.INSTANCE.entityToResponse(optional.get());
        }else{
            throw new ResourceNotFound("Interview","Id",id.toString());
        }
    }

    @Override
    public void delete(Long id) {
        Optional<Interview> optional = interviewRepository.findById(id);
        if (optional.isPresent()) {
            interviewRepository.delete(optional.get());
            System.out.println("Deleted Successfully");
        } else {
            throw new ResourceNotFound("Interview", "Id", id.toString());
        }
    }
}
