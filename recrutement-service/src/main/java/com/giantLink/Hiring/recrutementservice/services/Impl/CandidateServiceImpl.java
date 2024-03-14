package com.giantLink.Hiring.recrutementservice.services.Impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import com.giantLink.Hiring.recrutementservice.entities.Candidate;
import com.giantLink.Hiring.recrutementservice.mappers.CandidateMapper;
import com.giantLink.Hiring.recrutementservice.models.requests.CandidateRequest;
import com.giantLink.Hiring.recrutementservice.models.response.CandidateResponse;
import com.giantLink.Hiring.recrutementservice.repositories.CandidateRepository;
import com.giantLink.Hiring.recrutementservice.services.CandidateService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CandidateServiceImpl implements CandidateService {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private MessageSource messageSource;

    @Override
    public CandidateResponse add(CandidateRequest request) {
        Candidate candidate = CandidateMapper.INSTANCE.requestToEntity(request);
        candidate = candidateRepository.save(candidate);
        return CandidateMapper.INSTANCE.entityToResponse(candidate);
    }

    @Override
    public List<CandidateResponse> get() {
        List<Candidate> candidates = candidateRepository.findAll();
        return candidates.stream()
                .map(CandidateMapper.INSTANCE::entityToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public CandidateResponse get(Long id) {
        Candidate candidate = candidateRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        messageSource.getMessage("error.candidate.notFound", null, LocaleContextHolder.getLocale())));
        return CandidateMapper.INSTANCE.entityToResponse(candidate);
    }

    @Override
    public CandidateResponse update(CandidateRequest request, Long id) {
        Candidate candidate = candidateRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        messageSource.getMessage("error.candidate.notFound", null, LocaleContextHolder.getLocale())));

        // Update the candidate properties using values from the request
        candidate.setFirstName(request.getFirstName());
        candidate.setLastName(request.getLastName());
        candidate.setEmail(request.getEmail());
        candidate.setPhone(request.getPhone());
        candidate.setAddress(request.getAddress());
        candidate.setCity(request.getCity());
        candidate.setCountry(request.getCountry());
        candidate.setBirthdayDate(request.getBirthdayDate());
        candidate.setMessage(request.getMessage());
        candidate.setAvailability(request.getAvailability());

        candidate = candidateRepository.save(candidate);

        return CandidateMapper.INSTANCE.entityToResponse(candidate);
    }

    @Override
    public void delete(Long id) {
        Candidate candidate = candidateRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        messageSource.getMessage("error.candidate.notFound", null, LocaleContextHolder.getLocale())));
        candidateRepository.delete(candidate);
    }
}
