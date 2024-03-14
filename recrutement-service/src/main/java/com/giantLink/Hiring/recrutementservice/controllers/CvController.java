package com.giantLink.Hiring.recrutementservice.controllers;

import com.giantLink.Hiring.recrutementservice.models.requests.CvRequest;
import com.giantLink.Hiring.recrutementservice.models.requests.CvUpdateRequest;
import com.giantLink.Hiring.recrutementservice.models.response.CvResponse;
import com.giantLink.Hiring.recrutementservice.models.response.SuccessResponse;
import com.giantLink.Hiring.recrutementservice.services.CvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value ="/api/cvs",produces = { "application/json" })
public class CvController {

    @Autowired
    private CvService cvService;

    @Autowired
    private MessageSource messageSource;

    @PostMapping
    @PreAuthorize("hasAuthority('CV_CREATE')")
    public ResponseEntity<CvResponse> addCv(@RequestBody CvRequest request) {
        CvResponse cvResponse = cvService.add(request);
        String successMessage = messageSource.getMessage("cv.controller.addSuccess", null, LocaleContextHolder.getLocale());
        cvResponse.setMessage(successMessage);
        return new ResponseEntity<>(cvResponse, HttpStatus.CREATED);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('CV_READ_ALL')")
    public ResponseEntity<List<CvResponse>> getAllCvs() {
        List<CvResponse> cvResponses = cvService.get();
        return new ResponseEntity<>(cvResponses, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('CV_READ')")
    public ResponseEntity<CvResponse> getCvById(@PathVariable Long id) {
        CvResponse cvResponse = cvService.get(id);
        return new ResponseEntity<>(cvResponse, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('CV_UPDATE')")
    public ResponseEntity<CvResponse> updateCv(@PathVariable Long id, @RequestBody CvUpdateRequest request) {
        CvResponse cvResponse = cvService.update(request, id);
        String successMessage = messageSource.getMessage("cv.controller.updateSuccess", null, LocaleContextHolder.getLocale());
        cvResponse.setMessage(successMessage);
        return new ResponseEntity<>(cvResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('CV_DELETE')")
    public ResponseEntity<SuccessResponse> deleteCv(@PathVariable Long id) {
        cvService.delete(id);
        String successMessage = messageSource.getMessage("cv.controller.deleteSuccess", null, LocaleContextHolder.getLocale());
        SuccessResponse successResponse = SuccessResponse.builder()
        		.message(successMessage).build();
        return new ResponseEntity<>(successResponse,HttpStatus.OK);
    }
}
