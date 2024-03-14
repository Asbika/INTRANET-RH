package com.giantLink.Hiring.recrutementservice.controllers;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.giantLink.Hiring.recrutementservice.models.requests.RegionRequest;
import com.giantLink.Hiring.recrutementservice.models.requests.RegionUpdateRequest;
import com.giantLink.Hiring.recrutementservice.models.response.RegionResponse;
import com.giantLink.Hiring.recrutementservice.models.response.SuccessResponse;
import com.giantLink.Hiring.recrutementservice.services.RegionService;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/api/regions")
public class RegionController  {

    @Autowired
    private RegionService regionService;

    @Autowired
    private MessageSource messageSource;

    @PostMapping
    @PreAuthorize("hasAuthority('REGION_ADD')")
    public ResponseEntity<RegionResponse> add(@RequestBody @Valid RegionRequest request){
        RegionResponse response = regionService.add(request);
        String successMessage = messageSource.getMessage("region.controller.addSuccess", null, LocaleContextHolder.getLocale());
        response.setMessage(successMessage);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('REGION_READ_ALL')")
    public ResponseEntity<List<RegionResponse>> get(){
        List<RegionResponse> regionResponses = regionService.getAll();
        return new ResponseEntity<>(regionResponses, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('REGION_READ')")
    public ResponseEntity<RegionResponse> getById(@PathVariable Long id){
        RegionResponse regionResponse = regionService.get(id);
        return new ResponseEntity<>(regionResponse, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('REGION_UPDATE')")
    public ResponseEntity<RegionResponse> edit(@PathVariable Long id, @RequestBody RegionUpdateRequest regionUpdateRequest){
        RegionResponse response = regionService.update(regionUpdateRequest, id);
        String successMessage = messageSource.getMessage("region.controller.updateSuccess", null, LocaleContextHolder.getLocale());
        response.setMessage(successMessage);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('REGION_DELETE')")
    public ResponseEntity<SuccessResponse> delete(@PathVariable Long id){
        regionService.delete(id);
        String successMessage = messageSource.getMessage("region.controller.deleteSuccess", null, LocaleContextHolder.getLocale());
        SuccessResponse successResponse = SuccessResponse.builder()
                .message(successMessage)
                .build();

        return new ResponseEntity<>(successResponse, HttpStatus.OK);
    }
}
