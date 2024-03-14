package com.giantLink.RH.controllers;

import com.giantLink.RH.entities.ApprovedLeave;
import com.giantLink.RH.exceptions.UnauthorizedAccessException;
import com.giantLink.RH.models.request.PredefinedHolidayRequest;
import com.giantLink.RH.models.response.PredefinedHolidayResponse;
import com.giantLink.RH.services.ApprovedLeaveService;
import com.giantLink.RH.services.EmployeeService;
import com.giantLink.RH.services.PredefinedHolidayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@PreAuthorize("hasRole('ADMIN_RH')")
@RequestMapping("api/holiday")
public class HolidayController {

    @Autowired
    PredefinedHolidayService predefinedHolidayService;
    @Autowired
    ApprovedLeaveService approvedLeaveService;
    @Autowired
    MessageSource messageSource;

    @Autowired
    EmployeeService employeeService;

    @PreAuthorize("hasAuthority('ADMIN_READ')")
    @GetMapping("/predefined-holidays")
    public ResponseEntity<List<PredefinedHolidayResponse>> getPredefined(){
        return new ResponseEntity<>(predefinedHolidayService.get(), HttpStatus.OK);
    }
    @PreAuthorize("hasAuthority('EMPLOYEE_READ')")
    @GetMapping("/predefined-holidays/{id}")
    public ResponseEntity<PredefinedHolidayResponse> getPredefinedById(@PathVariable Long id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!authentication.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN_RH"))) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            if (!employeeService.doesEmployeeIdBelongToUser(id, userDetails.getUsername())) {
                String message = messageSource.getMessage("permission.denied", null, "No MESSAGE", LocaleContextHolder.getLocale());
                throw new UnauthorizedAccessException(message);
            }
        }
        return new ResponseEntity<>(predefinedHolidayService.get(id), HttpStatus.OK);
    }
    @PostMapping("/predefined-holidays")
    @PreAuthorize("hasAuthority('ADMIN_CREATE')")
    public ResponseEntity<PredefinedHolidayResponse> addPredefined(@RequestBody PredefinedHolidayRequest request){
        return new ResponseEntity<>(predefinedHolidayService.add(request), HttpStatus.CREATED);
    }
    @PutMapping("/predefined-holidays/{id}")
    @PreAuthorize("hasAuthority('ADMIN_UPDATE')")
    public ResponseEntity<PredefinedHolidayResponse> updatePredefined(@RequestBody PredefinedHolidayRequest request, @PathVariable Long id){
        return new ResponseEntity<>(predefinedHolidayService.update(request,id), HttpStatus.OK);
    }
    @DeleteMapping("/predefined-holidays/{id}")
    @PreAuthorize("hasAuthority('ADMIN_DELETE')")
    public ResponseEntity<String> deletePredefined(@PathVariable Long id){
        predefinedHolidayService.delete(id);
        return new ResponseEntity<>("Done",HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN_READ')")
    @GetMapping("/approved-leaves")
    public ResponseEntity<List<ApprovedLeave>> getLeaves(){
        return new ResponseEntity<>(approvedLeaveService.getAll(),HttpStatus.OK);
    }
}
