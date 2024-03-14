package com.giantLink.RH.controllers;

import com.giantLink.RH.models.request.RequestAbsenceUpdateRequest;
import com.giantLink.RH.models.request.RequestAbsenceRequest;
import com.giantLink.RH.models.response.RequestAbsenceResponse;
import com.giantLink.RH.services.RequestAbsenceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/absences")
public class RequestAbsenceController {

	@Autowired
    private RequestAbsenceService requestAbsenceService;

    @PostMapping
    @PreAuthorize("hasAnyAuthority('EMPLOYEE_CREATE')")
   public ResponseEntity<RequestAbsenceResponse> addRequestAbsence(@RequestBody RequestAbsenceRequest request) {
        RequestAbsenceResponse response = requestAbsenceService.add(request);
        if (response != null) {
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN_RH') and hasAnyAuthority('ADMIN_READ')")
    public ResponseEntity<List<RequestAbsenceResponse>> getAllRequestAbsences() {
        List<RequestAbsenceResponse> responses = requestAbsenceService.get();
        if (!responses.isEmpty()) {
            return new ResponseEntity<>(responses, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN_RH')")
    public ResponseEntity<RequestAbsenceResponse> getRequestAbsenceById(@PathVariable Long id) {
        RequestAbsenceResponse response = requestAbsenceService.get(id);
        if (response != null) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('EMPLOYEE_UPDATE')")
    public ResponseEntity<RequestAbsenceResponse> updateRequestAbsence(@PathVariable Long id, @RequestBody RequestAbsenceRequest request) {
        RequestAbsenceResponse response = requestAbsenceService.update(request, id);
        if (response != null) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize(" hasAnyAuthority('ADMIN_DELETE')")
    public ResponseEntity<Void> deleteRequestAbsence(@PathVariable Long id) {
        requestAbsenceService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/justification/{id}")
    @PreAuthorize("hasAnyRole('ADMIN_RH')")
    public ResponseEntity<RequestAbsenceResponse> updateJustification(@PathVariable Long id, @RequestBody RequestAbsenceUpdateRequest request) {
        RequestAbsenceResponse response = requestAbsenceService.updateJustification(request, id);
        if (response != null) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/by_absence")
    @PreAuthorize("hasAnyRole('ADMIN_RH')")
    public ResponseEntity<List<RequestAbsenceResponse>> getAbsence(){
    	List<RequestAbsenceResponse> responses = requestAbsenceService.getIsSickness(false);
        if (!responses.isEmpty()) {
            return new ResponseEntity<>(responses, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
    @GetMapping("/by_sickness")
    @PreAuthorize("hasAnyRole('ADMIN_RH')")
    public ResponseEntity<List<RequestAbsenceResponse>> getSickness(){
    	List<RequestAbsenceResponse> responses = requestAbsenceService.getIsSickness(true);
        if (!responses.isEmpty()) {
            return new ResponseEntity<>(responses, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
    @GetMapping("/employee/{id}")
    @PreAuthorize("hasAnyRole('ADMIN_RH','MANAGER_RH','DIRECTOR','SUPER_ADMIN','EMPLOYEE')")
    public ResponseEntity<List<RequestAbsenceResponse>>  getRequestAbsenceByEmployeeId(@PathVariable Long id) {
        return new ResponseEntity<>(requestAbsenceService.getRequestAbsenceByEmployeeId(id), HttpStatus.OK);
    }
    
    @GetMapping("/employee/sickness/{id}")
    @PreAuthorize("hasAnyRole('ADMIN_RH','MANAGER_RH','DIRECTOR','SUPER_ADMIN','EMPLOYEE')")
    public ResponseEntity<List<RequestAbsenceResponse>>  getSicknessByEmployee(@PathVariable Long id ) {
        return new ResponseEntity<>(requestAbsenceService.getByEmployeeIsSickness(true, id), HttpStatus.OK);
    }
    
    @GetMapping("/employee/absence/{id}")
    @PreAuthorize("hasAnyRole('ADMIN_RH','MANAGER_RH','DIRECTOR','SUPER_ADMIN','EMPLOYEE')")
    public ResponseEntity<List<RequestAbsenceResponse>>  getAbsenceByEmployee(@PathVariable Long id ) {
        return new ResponseEntity<>(requestAbsenceService.getByEmployeeIsSickness(false, id), HttpStatus.OK);
    }
}
