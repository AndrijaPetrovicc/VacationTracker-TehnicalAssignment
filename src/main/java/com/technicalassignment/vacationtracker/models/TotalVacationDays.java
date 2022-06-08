package com.technicalassignment.vacationtracker.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "TotalVacationDays")
public class TotalVacationDays {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long employee_id;
    private int vacation_year;
    private int total_vacation_days;

    public TotalVacationDays(){
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(Long employee_id) {
        this.employee_id = employee_id;
    }

    public int getVacation_year() {
        return vacation_year;
    }

    public void setVacation_year(int vacation_year) {
        this.vacation_year = vacation_year;
    }

    public int getTotal_vacation_days() {
        return total_vacation_days;
    }

    public void setTotal_vacation_days(int total_vacation_days) {
        this.total_vacation_days = total_vacation_days;
    }
}
