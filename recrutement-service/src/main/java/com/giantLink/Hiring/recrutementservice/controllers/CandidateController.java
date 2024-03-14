package com.giantLink.Hiring.recrutementservice.controllers;

import jakarta.validation.Valid; 
import com.giantLink.Hiring.recrutementservice.models.requests.CandidateRequest;
import com.giantLink.Hiring.recrutementservice.models.response.CandidateResponse;
import com.giantLink.Hiring.recrutementservice.models.response.SuccessResponse;
import com.giantLink.Hiring.recrutementservice.services.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/candidates")
public class CandidateController {

    @Autowired
    private CandidateService candidateService;

    @Autowired
    private MessageSource messageSource;

    @PostMapping
    @PreAuthorize("hasAuthority('CANDIDATE_CREATE')")
    public ResponseEntity<CandidateResponse> add(@Valid @RequestBody CandidateRequest candidateAddRequest) {
        CandidateResponse response = candidateService.add(candidateAddRequest);
        String successMessage = messageSource.getMessage("candidate.controller.addSuccess", null, LocaleContextHolder.getLocale());
        response.setMessage(successMessage);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('CANDIDATE_READ_ALL')")
    public ResponseEntity<List<CandidateResponse>> get() {
        List<CandidateResponse> candidates = candidateService.get();
        return new ResponseEntity<>(candidates, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('CANDIDATE_UPDATE')")
    public ResponseEntity<CandidateResponse> update(@RequestBody CandidateRequest candidateUpdateRequest, @PathVariable Long id) {
        CandidateResponse response = candidateService.update(candidateUpdateRequest, id);
        String successMessage = messageSource.getMessage("candidate.controller.updateSuccess", null, LocaleContextHolder.getLocale());
        response.setMessage(successMessage);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('CANDIDATE_READ')")
    public ResponseEntity<CandidateResponse> get(@PathVariable("id") Long id) {
        CandidateResponse response = candidateService.get(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('CANDIDATE_DELETE')")
    public ResponseEntity<SuccessResponse> delete(@PathVariable Long id) {
        candidateService.delete(id);
        String successMessage = messageSource.getMessage("candidate.controller.deleteSuccess", null, LocaleContextHolder.getLocale());
        SuccessResponse successResponse = SuccessResponse.builder()
        		.message(successMessage).build();
        return new ResponseEntity<>(successResponse,HttpStatus.OK);

    }
}
