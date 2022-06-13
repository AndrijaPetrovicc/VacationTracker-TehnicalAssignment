package com.technicalassignment.vacationtracker.services;

import com.technicalassignment.vacationtracker.helpers.CSVHelper;
import com.technicalassignment.vacationtracker.models.Employee;
import com.technicalassignment.vacationtracker.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
//The Service annotation tells Spring that this is a service class
@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;
    //The saveEmployeeProfiles method receives MultipartFile as a parameter and uses the csvToEmployeeProfiles method and the saveAll method to store the relevant data in the database
    public void saveEmployeeProfiles(MultipartFile file){
        try{
            List<Employee> employees = CSVHelper.csvToEmployeeProfiles(file.getInputStream());
            employeeRepository.saveAll(employees);
        } catch (IOException e){
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
    }

}
