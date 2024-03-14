package com.giantLink.Hiring.recrutementservice.controllers;

import com.giantLink.Hiring.recrutementservice.models.requests.InterviewAddRequest;
import com.giantLink.Hiring.recrutementservice.models.requests.InterviewUpdateRequest;
import jakarta.validation.Valid;

import com.giantLink.Hiring.recrutementservice.models.response.InterviewResponse;
import com.giantLink.Hiring.recrutementservice.models.response.SuccessResponse;
import com.giantLink.Hiring.recrutementservice.services.InterviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/interviews")
public class InterviewController {

    @Autowired
    private InterviewService interviewService;

    @Autowired
    private MessageSource messageSource;

    @GetMapping()
    @PreAuthorize("hasAuthority('INTERVIEW_READ_ALL')")
    public ResponseEntity<List<InterviewResponse>> get() {
        List<InterviewResponse> interviewResponses = interviewService.getAll();
        return new ResponseEntity<>(interviewResponses, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('INTERVIEW_READ')")
    public ResponseEntity<InterviewResponse> get(@PathVariable Long id) {
        InterviewResponse interviewResponse = interviewService.get(id);
        return new ResponseEntity<>(interviewResponse, HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('INTERVIEW_UPDATE')")
    public ResponseEntity<InterviewResponse> add(@RequestBody @Valid InterviewAddRequest interview) {
        InterviewResponse response = interviewService.add(interview);
        String successMessage = messageSource.getMessage("interview.controller.addSuccess", null, LocaleContextHolder.getLocale());
        response.setMessage(successMessage);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('INTERVIEW_CREATE')")
    public ResponseEntity<InterviewResponse> update(@PathVariable Long id, @RequestBody @Valid InterviewUpdateRequest request) {
        InterviewResponse response = interviewService.update(request, id);
        String successMessage = messageSource.getMessage("interview.controller.updateSuccess", null, LocaleContextHolder.getLocale());
        response.setMessage(successMessage);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('INTERVIEW_DELETE')")
    public ResponseEntity<SuccessResponse> delete(@PathVariable Long id) {
        interviewService.delete(id);
        String successMessage = messageSource.getMessage("interview.controller.deleteSuccess", null, LocaleContextHolder.getLocale());
        SuccessResponse successResponse = SuccessResponse.builder()
        		.message(successMessage).build();
        return new ResponseEntity<>(successResponse,HttpStatus.OK);
    }
}
