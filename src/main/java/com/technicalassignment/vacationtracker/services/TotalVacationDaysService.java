package com.technicalassignment.vacationtracker.services;

import com.technicalassignment.vacationtracker.helpers.CSVHelper;
import com.technicalassignment.vacationtracker.models.Employee;
import com.technicalassignment.vacationtracker.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.technicalassignment.vacationtracker.repositories.TotalVacationDaysRepository;
import com.technicalassignment.vacationtracker.models.TotalVacationDays;

import java.io.IOException;
import java.util.List;

//The Service annotation tells Spring that this is a service class
@Service
public class TotalVacationDaysService {
    @Autowired
    TotalVacationDaysRepository totalVacationDaysRepository;
    //The saveTotalVacationDays method receives MultipartFile as a parameter and uses the csvToTotalVacationDays method and the saveAll method to store the relevant data in the database
    public void saveTotalVacationDays(MultipartFile file){
        try{
            List<TotalVacationDays> totalVacationDays = CSVHelper.csvToTotalVacationDays(file.getInputStream());
            totalVacationDaysRepository.saveAll(totalVacationDays);
        } catch (IOException e){
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
    }
}
