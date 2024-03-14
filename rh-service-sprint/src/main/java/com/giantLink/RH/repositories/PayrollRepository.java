package com.giantLink.RH.repositories;

import com.giantLink.RH.entities.Employee;
import com.giantLink.RH.entities.Payroll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface PayrollRepository extends JpaRepository<Payroll, Long> {
    List<Payroll> findByUserUsername(String username);
    boolean existsByEmployeeAndPaymentDate(Employee employee, Date paymentDate);
    Optional<Payroll> findByEmployeeAndPaymentDate(Employee employee, Date paymentDate);


}
