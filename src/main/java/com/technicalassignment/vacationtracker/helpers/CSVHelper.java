package com.technicalassignment.vacationtracker.helpers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.technicalassignment.vacationtracker.models.TotalVacationDays;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;
import com.technicalassignment.vacationtracker.models.Employee;
import com.technicalassignment.vacationtracker.models.UsedVacationDays;
import com.technicalassignment.vacationtracker.services.EmployeeService;
import java.text.SimpleDateFormat;
//In the CSVHelper class, methods were created for loading data from csv files into the appropriate data lists
public class CSVHelper {
    public static String TYPE = "text/csv";
    //hasCSVFormat is a method that checks if a file is in the correct format
    public boolean hasCSVFormat(MultipartFile file) {
        if(!TYPE.equals(file.getContentType())){
            return false;
        }
        return true;
    }
    //csvToEmployeeProfiles is a method that receives an input stream as a parameter and then extracts employee profile data from it and places it in a data list which it then returns
    public static List<Employee> csvToEmployeeProfiles(InputStream is) throws IOException {
        //create BufferedReader from InputStream
        BufferedReader fileReader = new BufferedReader(new InputStreamReader(is,"UTF-8"));
        //Load the first line of the file to create a format without it, since the first line of the document is not relevant
        System.out.println(fileReader.readLine());
        //Create the appropriate csv file format
        CSVFormat format = CSVFormat.Builder.create(CSVFormat.DEFAULT).setHeader().setSkipHeaderRecord(true).build();
        //create CSVParser from the BufferedReader and CSV format
        CSVParser csvParser = new CSVParser(fileReader,format);
        //Use try-with-resources statement to ensure that each resource will close after the program stops using it
        try(fileReader; csvParser;){
            List<Employee> employees = new ArrayList<Employee>();
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();
            //Creating instances of employees via a parameter constructor with data from the CSV file, adding them to the list
            for (CSVRecord csvRecord : csvRecords){
                Employee employee = new Employee(csvRecord.get("Employee Email"),csvRecord.get("Employee Password"));
                employees.add(employee);
            }
            return employees;
        }
        catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }

    }

    public static List<UsedVacationDays> csvToUsedVacationDates(InputStream is) throws IOException {
        EmployeeService employeeService = new EmployeeService();
        //Creating a date formatter
        SimpleDateFormat formatter = new SimpleDateFormat("EEEE, MMMM dd, yyyy");
        BufferedReader fileReader = new BufferedReader(new InputStreamReader(is,"UTF-8"));
        CSVFormat format = CSVFormat.Builder.create(CSVFormat.DEFAULT).setHeader().setSkipHeaderRecord(true).build();
        CSVParser csvParser = new CSVParser(fileReader,format);
        try(fileReader; csvParser;){
            List<UsedVacationDays> usedVacationDaysList = new ArrayList<UsedVacationDays>();
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();
            for (CSVRecord csvRecord : csvRecords){
                //Since we can only access the email address of employees via the CSV file and we need an id, we use a method that returns the id using the email address
                UsedVacationDays usedVacationDays = new UsedVacationDays(employeeService.getEmployeeIdByEmail(csvRecord.get("Employee")), (Date) formatter.parse(csvRecord.get("Vacation start date")),(Date) formatter.parse(csvRecord.get("Vacation start date")));
                usedVacationDaysList.add(usedVacationDays);
            }
            return usedVacationDaysList;
        }
        catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        } catch (ParseException e) {
            throw new RuntimeException("wrong date format: " + e.getMessage());
        }

    }

    public static List<TotalVacationDays> csvToTotalVacationDays(InputStream is) throws IOException {
        EmployeeService employeeService = new EmployeeService();
        BufferedReader fileReader = new BufferedReader(new InputStreamReader(is,"UTF-8"));
        //Since the first line of the file gives us the year we need, using the substring method we take only the year and ignore the rest
        String year = fileReader.readLine().substring(14,18);
        //After using the first line, we create a format without it
        CSVFormat format = CSVFormat.Builder.create(CSVFormat.DEFAULT).setHeader().setSkipHeaderRecord(true).build();
        CSVParser csvParser = new CSVParser(fileReader,format);
        try(fileReader; csvParser;){
            List<TotalVacationDays> totalVacationDaysList = new ArrayList<TotalVacationDays>();
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();
            for (CSVRecord csvRecord : csvRecords){
                TotalVacationDays totalVacationDays = new TotalVacationDays(employeeService.getEmployeeIdByEmail(csvRecord.get("Employee")),Integer.parseInt(year),Integer.parseInt(csvRecord.get("Total vacation days")));
                totalVacationDaysList.add(totalVacationDays);
            }
            return totalVacationDaysList;
        }
        catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }

    }
}
