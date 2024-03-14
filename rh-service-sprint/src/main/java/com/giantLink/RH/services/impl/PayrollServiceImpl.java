package com.giantLink.RH.services.impl;

import com.giantLink.RH.entities.Employee;
import com.giantLink.RH.entities.Payroll;
import com.giantLink.RH.exceptions.ResourceNotFoundException;
import com.giantLink.RH.exceptions.UnauthorizedAccessException;
import com.giantLink.RH.mappers.PayrollMapper;
import com.giantLink.RH.models.request.PayrollRequest;
import com.giantLink.RH.models.response.PayrollResponse;
import com.giantLink.RH.repositories.EmployeeRepository;
import com.giantLink.RH.repositories.PayrollRepository;
import com.giantLink.RH.services.PayrollService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PayrollServiceImpl implements PayrollService {

    @Autowired
    private PayrollRepository payrollRepository;

    @Autowired
    private EmployeeRepository employeeRepository;


    @Override
    public PayrollResponse createPayroll(PayrollRequest payrollRequest) {
        Employee employee = employeeRepository.findById(payrollRequest.getEmployeeId())
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        Payroll payroll = new Payroll();
        payroll.setSalary(payrollRequest.getSalary());
        payroll.setPaymentDate(payrollRequest.getPaymentDate());
        payroll.setEmployee(employee);

        Payroll savedPayroll = payrollRepository.save(payroll);

        return convertToPayrollResponse(savedPayroll);
    }

    @Override
    public PayrollResponse updatePayroll(Long payrollId, PayrollRequest payrollRequest) {
        Payroll payroll = payrollRepository.findById(payrollId)
                .orElseThrow(() -> new ResourceNotFoundException("Payroll not found"));

        Employee employee = employeeRepository.findById(payrollRequest.getEmployeeId())
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        payroll.setSalary(payrollRequest.getSalary());
        payroll.setPaymentDate(payrollRequest.getPaymentDate());
        payroll.setEmployee(employee);

        Payroll updatedPayroll = payrollRepository.save(payroll);

        return convertToPayrollResponse(updatedPayroll);
    }

    @Override
    public PayrollResponse getPayrollById(Long payrollId) {
        Payroll payroll = payrollRepository.findById(payrollId)
                .orElseThrow(() -> new ResourceNotFoundException("Payroll not found"));

        return convertToPayrollResponse(payroll);
    }

    @Override
    public List<PayrollResponse> getAllPayrolls() {
        List<Payroll> payrolls = payrollRepository.findAll();
        return payrolls.stream().map(this::convertToPayrollResponse).collect(Collectors.toList());
    }

    @Override
    public List<Employee> getEmployeesByPayroll() {
        List<Employee> employeesByPayyroll=new ArrayList<>();
        List<Payroll> payrolls=new ArrayList<>();
        payrolls.forEach(payroll ->employeesByPayyroll.add(payroll.getEmployee()));
        return employeesByPayyroll;
    }


    private PayrollResponse convertToPayrollResponse(Payroll payroll) {
        PayrollResponse response = new PayrollResponse();
        response.setId(payroll.getId());
        response.setSalary(payroll.getSalary());
        response.setPaymentDate(payroll.getPaymentDate());
        response.setEmployeeId(payroll.getEmployee().getId());
        return response;
    }

    public List<Payroll> getPayrollsForAuthenticatedEmployee() {
        // Récupérer l'employé authentifié depuis le contexte de sécurité
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new UnauthorizedAccessException("User is not authenticated.");
        }

        // Extraire les informations de l'utilisateur authentifié
        String username = authentication.getName();
        Optional<Employee> authenticatedEmployee = employeeRepository.findByCin(username);

        if (authenticatedEmployee == null) {
            throw new EntityNotFoundException("Authenticated employee not found.");
        }

        return authenticatedEmployee.get().getPayrolls();
    }



}
