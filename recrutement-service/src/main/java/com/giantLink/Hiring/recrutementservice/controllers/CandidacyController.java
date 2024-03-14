package com.giantLink.Hiring.recrutementservice.controllers;

import com.giantLink.Hiring.recrutementservice.models.requests.CandidacyRequest; 
import com.giantLink.Hiring.recrutementservice.models.response.CandidacyResponse;
import com.giantLink.Hiring.recrutementservice.models.response.SuccessResponse;
import com.giantLink.Hiring.recrutementservice.services.CandidacyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/api/candidacies")
public class CandidacyController {

    @Autowired
    private CandidacyService candidacyService;

    @Autowired
    private MessageSource messageSource;

    @PostMapping("/cv/{id}")
    @PreAuthorize("hasAuthority('CANDIDACY_CREATE')")
    public ResponseEntity<CandidacyResponse> addCandidacy( @RequestBody CandidacyRequest request, @PathVariable Long id) {
    	request.setCvId(id);
        CandidacyResponse response = candidacyService.add(request);
        String successMessage = messageSource.getMessage("candidacy.controller.addSuccess", null, LocaleContextHolder.getLocale());
        response.setMessage(successMessage);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('CANDIDACY_READ_ALL')")
    public ResponseEntity<List<CandidacyResponse>> getAllCandidacies() {
        List<CandidacyResponse> responses = candidacyService.getAll();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('CANDIDACY_READ')")
    public ResponseEntity<CandidacyResponse> getCandidacy(@PathVariable Long id) {
        CandidacyResponse response = candidacyService.get(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/cv/{cvid}/candidacy/{id}")
    @PreAuthorize("hasAuthority('CANDIDACY_UPDATE')")
    public ResponseEntity<CandidacyResponse> updateCandidacy(
            @RequestBody CandidacyRequest request,
            @PathVariable Long id,
            @PathVariable Long cvId
    ) {
        request.setCvId(cvId);
        CandidacyResponse response = candidacyService.update(request, id);
        String successMessage = messageSource.getMessage("candidacy.controller.updateSuccess", null, LocaleContextHolder.getLocale());
        response.setMessage(successMessage);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('CANDIDACY_DELETE')")
    public ResponseEntity<SuccessResponse> deleteCandidacy(@PathVariable Long id) {
        candidacyService.delete(id);
        String successMessage = messageSource.getMessage("candidacy.controller.deleteSuccess", null, LocaleContextHolder.getLocale());
        SuccessResponse successResponse = SuccessResponse.builder()
        		.message(successMessage).build();
        return new ResponseEntity<>(successResponse, HttpStatus.OK);
    }
}
