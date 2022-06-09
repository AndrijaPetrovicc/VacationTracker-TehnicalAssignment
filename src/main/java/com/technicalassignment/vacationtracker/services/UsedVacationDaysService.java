package com.technicalassignment.vacationtracker.services;

import com.technicalassignment.vacationtracker.helpers.CSVHelper;
import com.technicalassignment.vacationtracker.models.UsedVacationDays;
import com.technicalassignment.vacationtracker.repositories.UsedVacationDaysRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

//The Service annotation tells Spring that this is a service class
@Service
public class UsedVacationDaysService {
    @Autowired
    UsedVacationDaysRepository usedVacationDaysRepository;
    //The saveUsedVacationDays method receives MultipartFile as a parameter and uses the csvToUsedVacationDates method and the saveAll method to store the relevant data in the database
    public void saveUsedVacationDays(MultipartFile file){
        try{
            List<UsedVacationDays> usedVacationDaysList = CSVHelper.csvToUsedVacationDates(file.getInputStream());
            usedVacationDaysRepository.saveAll(usedVacationDaysList);
        } catch (IOException e){
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
    }
}
