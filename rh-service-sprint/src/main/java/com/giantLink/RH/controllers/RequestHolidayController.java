package com.giantLink.RH.controllers;

import com.giantLink.RH.entities.RequestHoliday;
import com.giantLink.RH.models.request.RequestHolidayRequest;
import com.giantLink.RH.models.response.RequestHolidayResponse;
import com.giantLink.RH.models.response.SuccessResponse;
import com.giantLink.RH.services.RequestHolidayService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@PreAuthorize("hasRole('EMPLOYEE')")
@RequestMapping("/api/request-holidays")
public class RequestHolidayController
{
    @Autowired
    RequestHolidayService requestHolidayService;
    @PostMapping
    @PreAuthorize("hasAnyAuthority('EMPLOYEE_CREATE')")
    public ResponseEntity<SuccessResponse> addRequestHoliday(@Valid @RequestBody RequestHolidayRequest requestHolidayRequest)
    {
        return new ResponseEntity<>(requestHolidayService.addRequestHoliday(requestHolidayRequest), HttpStatus.CREATED);
    }
    @GetMapping
    @PreAuthorize("hasAnyAuthority('EMPLOYEE_READ')")
    public ResponseEntity<List<RequestHolidayResponse>> get()
    {
        List<RequestHolidayResponse> holidayRequests = requestHolidayService.get();
        return new ResponseEntity<>(holidayRequests,HttpStatus.OK);
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('EMPLOYEE_READ')")
    public ResponseEntity<RequestHolidayResponse> get(@PathVariable("id") Long request_id)
    {
        RequestHolidayResponse holidayRequest = requestHolidayService.get(request_id);
        return new ResponseEntity<>(holidayRequest,HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('EMPLOYEE_DELETE')")
    public ResponseEntity<SuccessResponse> delete(@PathVariable("id") Long request_id)
    {
        return new ResponseEntity<>(requestHolidayService.drop(request_id),HttpStatus.OK);
    }
}
