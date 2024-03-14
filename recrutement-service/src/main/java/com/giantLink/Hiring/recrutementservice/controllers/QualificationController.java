package com.giantLink.Hiring.recrutementservice.controllers;

import lombok.RequiredArgsConstructor; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.giantLink.Hiring.recrutementservice.models.requests.QualificationRequest;
import com.giantLink.Hiring.recrutementservice.models.response.QualificationResponse;
import com.giantLink.Hiring.recrutementservice.models.response.SuccessResponse;
import com.giantLink.Hiring.recrutementservice.services.QualificationService;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@RestController
@RequestMapping("api/qualification")
@RequiredArgsConstructor
public class QualificationController {

    @Autowired
    private final QualificationService qualificationService;

    @Autowired
    private MessageSource messageSource;

    @GetMapping
    @PreAuthorize("hasAuthority('QUALIFICATION_READ_ALL')")
    public ResponseEntity<List<QualificationResponse>> getAll() {
        List<QualificationResponse> qualificationResponses = qualificationService.getAll();
        return new ResponseEntity<>(qualificationResponses, HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('QUALIFICATION_CREATE')")
    public ResponseEntity<QualificationResponse> add(@RequestBody QualificationRequest request) {
        QualificationResponse response = qualificationService.add(request);
        String successMessage = messageSource.getMessage("qualification.controller.addSuccess", null, LocaleContextHolder.getLocale());
        response.setMessage(successMessage);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('QUALIFICATION_READ')")
    public ResponseEntity<QualificationResponse> get(@PathVariable Long id) {
        QualificationResponse qualificationResponse = qualificationService.get(id);
        return new ResponseEntity<>(qualificationResponse, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('QUALIFICATION_UPDATE')")
    public ResponseEntity<QualificationResponse> update(@RequestBody QualificationRequest request, @PathVariable Long id) {
        QualificationResponse response = qualificationService.update(request, id);
        String successMessage = messageSource.getMessage("qualification.controller.updateSuccess", null, LocaleContextHolder.getLocale());
        response.setMessage(successMessage);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('QUALIFICATION_DELETE')")
    public ResponseEntity<SuccessResponse> delete(@PathVariable Long id) {
        qualificationService.delete(id);
        String successMessage = messageSource.getMessage("qualification.controller.deleteSuccess", null, LocaleContextHolder.getLocale());
        SuccessResponse successResponse = SuccessResponse.builder()
        		.message(successMessage).build();
        return new ResponseEntity<>(successResponse,HttpStatus.NO_CONTENT);
    }
}
