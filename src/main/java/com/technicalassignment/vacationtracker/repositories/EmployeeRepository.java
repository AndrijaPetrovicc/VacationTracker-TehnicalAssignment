package com.technicalassignment.vacationtracker.repositories;

import com.technicalassignment.vacationtracker.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    public Employee findByEmail(String email_address);
}
