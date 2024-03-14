package com.giantLink.RH.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.giantLink.RH.models.request.WarningTypeRequest;
import com.giantLink.RH.models.response.WarningTypeResponse;
import com.giantLink.RH.services.WarningTypeService;

@RestController
@PreAuthorize("hasRole('ADMIN_RH')")
@RequestMapping("/warningtypes")
public class WarningTypeController {

    @Autowired
    private WarningTypeService warningTypeService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN_READ')")
    public ResponseEntity<List<WarningTypeResponse>> getAllWarningTypes() {
        List<WarningTypeResponse> warningTypes = warningTypeService.get();
        return new ResponseEntity<>(warningTypes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN_READ')")
    public ResponseEntity<WarningTypeResponse> getWarningTypeById(@PathVariable Long id) {
        WarningTypeResponse warningType = warningTypeService.get(id);
        return new ResponseEntity<>(warningType, HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN_CREATE')")
    public ResponseEntity<WarningTypeResponse> addWarningType(@RequestBody WarningTypeRequest request) {
        WarningTypeResponse warningType = warningTypeService.add(request);
        return new ResponseEntity<>(warningType, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN_UPDATE')")
    public ResponseEntity<WarningTypeResponse> updateWarningType(@RequestBody WarningTypeRequest request, @PathVariable Long id) {
        WarningTypeResponse warningType = warningTypeService.update(request, id);
        return new ResponseEntity<>(warningType, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN_DELETE')")
    public ResponseEntity<Void> deleteWarningType(@PathVariable Long id) {
        warningTypeService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
