package com.technicalassignment.vacationtracker.repositories;

import  com.technicalassignment.vacationtracker.models.UsedVacationDays;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsedVacationDaysRepository extends JpaRepository<UsedVacationDays,Long> {
}
