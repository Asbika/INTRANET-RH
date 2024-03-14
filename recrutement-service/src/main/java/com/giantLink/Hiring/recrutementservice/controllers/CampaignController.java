package com.giantLink.Hiring.recrutementservice.controllers;

import com.giantLink.Hiring.recrutementservice.models.requests.CampaignRequest;
import com.giantLink.Hiring.recrutementservice.models.requests.CampaignUpdateRequest;
import com.giantLink.Hiring.recrutementservice.services.CampaignService;
import jakarta.validation.Valid;
import com.giantLink.Hiring.recrutementservice.models.response.CampaignResponse;
import com.giantLink.Hiring.recrutementservice.models.response.SuccessResponse;

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
@RequestMapping("/api/campaign")
public class CampaignController {

    @Autowired
    CampaignService campaignService;
    @Autowired
    MessageSource messageSource;

    @PostMapping
    @PreAuthorize("hasAuthority('CAMPAIGN_CREATE')")
    public ResponseEntity<CampaignResponse> add(@RequestBody @Valid CampaignRequest request){
        CampaignResponse response = campaignService.add(request);
        String successMessage = messageSource.getMessage("campaign.controller.addSuccess", null, LocaleContextHolder.getLocale());
        response.setMessage(successMessage);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('CAMPAIGN_READ_ALL')")
    public ResponseEntity<List<CampaignResponse>> get(){
        List<CampaignResponse> campaign = campaignService.getAll();
        return new ResponseEntity<>(campaign, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('CAMPAIGN_READ')")
    public ResponseEntity<CampaignResponse> get(@PathVariable("id") Long id){
        CampaignResponse campaignResponses = campaignService.get(id);
        return new ResponseEntity<>(campaignResponses, HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    @PreAuthorize("hasAuthority('CAMPAIGN_READ_BY_NAME')")
    public ResponseEntity<List<CampaignResponse>> get(@PathVariable("name") String name){
        List<CampaignResponse> campaignResponses = campaignService.get(name);
        return new ResponseEntity<>(campaignResponses, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('CAMPAIGN_UPDATE')")
    public ResponseEntity<CampaignResponse> update(@PathVariable("id") Long id,
                                                    @RequestBody CampaignUpdateRequest campaignRequest){
        CampaignResponse campaignResponse = campaignService.update(campaignRequest, id);
        String successMessage = messageSource.getMessage("campaign.controller.updateSuccess", null, LocaleContextHolder.getLocale());
        campaignResponse.setMessage(successMessage);
        return new ResponseEntity<>(campaignResponse, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('CAMPAIGN_DELETE')")
    public ResponseEntity<SuccessResponse> delete(@PathVariable("id") Long id){
        campaignService.delete(id);
        String successMessage = messageSource.getMessage("campaign.controller.deleteSuccess", null, LocaleContextHolder.getLocale());
        SuccessResponse successresponse = SuccessResponse.builder()
        		.message(successMessage).build();
        return new ResponseEntity<>(successresponse, HttpStatus.OK);
    }
}


