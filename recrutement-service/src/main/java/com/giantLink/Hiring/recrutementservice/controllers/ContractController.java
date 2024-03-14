package com.giantLink.Hiring.recrutementservice.controllers;

import jakarta.validation.Valid; 
import com.giantLink.Hiring.recrutementservice.models.response.ContractResponse;
import com.giantLink.Hiring.recrutementservice.models.response.SuccessResponse;
import com.giantLink.Hiring.recrutementservice.models.requests.ContractRequest;
import com.giantLink.Hiring.recrutementservice.models.requests.ContractUpdateRequest;
import com.giantLink.Hiring.recrutementservice.services.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contract")
public class ContractController {

    @Autowired
    private ContractService contractService;
    
    @Autowired
    private MessageSource messageSource;

    @PostMapping
    @PreAuthorize("hasAuthority('CONTRACT_CREATE')")
    public ResponseEntity<ContractResponse> add(@RequestBody @Valid ContractRequest contractRequest) {
        ContractResponse response = contractService.add(contractRequest);
        String successMessage = messageSource.getMessage("contract.controller.addSuccess", null, LocaleContextHolder.getLocale());
        response.setMessage(successMessage);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("all")
    @PreAuthorize("hasAuthority('CONTRACT_READ_ALL')")
    public ResponseEntity<List<ContractResponse>> get() {
        List<ContractResponse> contractResponses = contractService.getAll();
        return new ResponseEntity<>(contractResponses, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('CONTRACT_READ')")
    public ResponseEntity<ContractResponse> get(@PathVariable("id") Long id) {
        ContractResponse contractResponse = contractService.get(id);
        return new ResponseEntity<>(contractResponse, HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    @PreAuthorize("hasAuthority('CONTRACT_READ_BY_NAME')")
    public ResponseEntity<List<ContractResponse>> get(@PathVariable("name") String name) {
        List<ContractResponse> contractResponses = contractService.get(name);
        return new ResponseEntity<>(contractResponses, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('CONTRACT_UPDATE')")
    public ResponseEntity<ContractResponse> update(@PathVariable("id") Long id,
                                                   @RequestBody ContractUpdateRequest contractRequest) {
        ContractResponse contractResponse = contractService.update(contractRequest, id);
        String successMessage = messageSource.getMessage("contract.controller.updateSuccess", null, LocaleContextHolder.getLocale());
        contractResponse.setMessage(successMessage);
        return new ResponseEntity<>(contractResponse, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('CONTRACT_DELETE')")
    public ResponseEntity<SuccessResponse> delete(@PathVariable("id") Long id) {
        contractService.delete(id);
        String successMessage = messageSource.getMessage("contract.controller.deleteSuccess", null, LocaleContextHolder.getLocale());
        SuccessResponse successResponse = SuccessResponse.builder()
        		.message(successMessage).build();
        return new ResponseEntity<>(successResponse,HttpStatus.OK);
    }
}
