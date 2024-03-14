package com.giantLink.RH.services.impl;


import com.giantLink.RH.entities.Employee;
import com.giantLink.RH.entities.Payroll;
import com.giantLink.RH.entities.Warning;
import com.giantLink.RH.mappers.EmployeeMapper;
import com.giantLink.RH.repositories.EmployeeRepository;
import com.giantLink.RH.repositories.PayrollRepository;
import com.giantLink.RH.repositories.WarningRepository;
import com.giantLink.RH.services.EmployeeService;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@Transactional
public class PayrollProcessingService {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private PayrollRepository payrollRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private WarningRepository warningRepository;
    @Autowired
    private PayrollServiceImpl payrollService;

     //@Scheduled(cron = "*/20 * * * * *") //pour le tester
   @Scheduled(cron = "0 0 0 1 * *")
    public void processMonthlyPayroll() {
        List<Employee> employees = employeeRepository.findAll();
        for (Employee employee : employees) {
            Payroll payroll = new Payroll();
            if (!payrollRepository.existsByEmployeeAndPaymentDate(employee, payroll.getPaymentDate())) {
            Hibernate.initialize(employee.getWarnings());
            Hibernate.initialize(employee.getRequests());
            Hibernate.initialize(employee.getApprovedLeaves());
            Hibernate.initialize(employee.getUser());


            float salary = grantPromotionsSingleEmployee(employee);


            payroll.setEmployee(employee);
            payroll.setSalary(salary);
            payroll.setPaymentDate(new Date());

            payrollRepository.save(payroll);
            } else {
                // Un enregistrement existe déjà, ignorez l'insertion ou effectuez une autre action
            }
        }
    }
    private List<Employee> illegibleEmployees(List<Warning> warnings) {
        List<Employee> sanctionedEmployees=new ArrayList<>();
        warnings.forEach(warning -> {
            if(warning.getWarningType().getId().equals(5L)
                    ||warning.getWarningType().getId().equals(6L)
            )
                sanctionedEmployees.add(warning.getEmployee());
        });
        return sanctionedEmployees;
    }
    private List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }
    public float grantPromotionsSingleEmployee(Employee employee) {
        Date today = new Date();
        Date recrutementDate = employee.getRecrutementDate();

        long differenceMilliseconds = today.getTime() - recrutementDate.getTime();
        long workingPeriod = TimeUnit.MILLISECONDS.toDays(differenceMilliseconds);

        List<Payroll> employeePayrolls = employee.getPayrolls();

        if (!employeePayrolls.isEmpty() && !illegibleEmployees(warningRepository.findAll()).contains(employee) && workingPeriod > 180) {
            for (Payroll payroll : employeePayrolls) {
                float newSalary = (float) (payroll.getSalary() + (0.01 * payroll.getSalary()));
                payroll.setSalary(newSalary);
            }
        }

        // You might want to return something meaningful if no payrolls are available.
        // For example, you could return a special value to indicate no payrolls were found.
        return employeePayrolls.isEmpty() ? -1.0f : employeePayrolls.get(0).getSalary();
    }


    public void grantPromotionEmployees(List<Employee> employeeList) {
        for (Employee emp:employeeList){
            grantPromotionsSingleEmployee(emp);
        }
    }
   // @Scheduled(cron = "*/20 * * * * *")  // pour le test
    void testerPourEmployeId1 (){
        Long employeeId = 1L;
        float salary =1000;
        Employee employee = employeeRepository.findById(employeeId).orElse(null);
        if (employee == null) {
           System.out.println("not existe");
        } else {


            Payroll payroll = new Payroll();
            payroll.setEmployee(employee);
            payroll.setSalary(salary);
            payroll.setPaymentDate(new Date());
            payrollRepository.save(payroll);
        }

    }
}
