package com.technicalassignment.vacationtracker.controllers;
import com.technicalassignment.vacationtracker.helpers.CSVHelper;
import com.technicalassignment.vacationtracker.models.Employee;
import com.technicalassignment.vacationtracker.models.TotalVacationDays;
import com.technicalassignment.vacationtracker.repositories.EmployeeRepository;
import com.technicalassignment.vacationtracker.services.EmployeeService;
import com.technicalassignment.vacationtracker.services.TotalVacationDaysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.technicalassignment.vacationtracker.messages.ResponseMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.HttpStatus;
import com.technicalassignment.vacationtracker.services.UsedVacationDaysService;

import java.util.ArrayList;
import java.util.List;

//@CrossOrigin("http://localhost:5432")
@RestController
//@RequestMapping("/api/user")
public class AdminController {
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private TotalVacationDaysService totalVacationDaysService;
    @Autowired
    private UsedVacationDaysService usedVacationDaysService;

    @PostMapping("/uploadEmployeeProfiles")
    public ResponseEntity<ResponseMessage> uploadEmployeeProfilesFile(@RequestParam("file")MultipartFile file){
        String message = "";
        if(CSVHelper.hasCSVFormat(file)){
            try{
                employeeService.saveEmployeeProfiles(file);
                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
            } catch (Exception e){
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
            }
        }
        message = "Please upload a csv file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
    }
    @PostMapping("/uploadTotalVacationDays")
    public ResponseEntity<ResponseMessage> uploadTotalVacationDaysFile(@RequestParam("file")MultipartFile file){
        String message = "";
        if(CSVHelper.hasCSVFormat(file)){
            try{
                totalVacationDaysService.saveTotalVacationDays(file);
                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
            } catch (Exception e){
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
            }
        }
        message = "Please upload a csv file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
    }
    @PostMapping("/uploadUsedVacationDays")
    public ResponseEntity<ResponseMessage> uploadUsedVacationDaysFile(@RequestParam("file")MultipartFile file){
        String message = "";
        if(CSVHelper.hasCSVFormat(file)){
            try{
                usedVacationDaysService.saveUsedVacationDays(file);
                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
            } catch (Exception e){
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
            }
        }
        message = "Please upload a csv file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
    }

}
