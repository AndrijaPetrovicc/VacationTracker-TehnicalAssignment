package com.technicalassignment.vacationtracker.services;

import com.technicalassignment.vacationtracker.models.Employee;
import com.technicalassignment.vacationtracker.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.technicalassignment.vacationtracker.helpers.MyUserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
        Optional<Employee> employee =  employeeRepository.findByEmail(email);

        employee.orElseThrow(() -> new UsernameNotFoundException("Not found:" + email));

        return employee.map(MyUserDetails::new).get();
    }

}
