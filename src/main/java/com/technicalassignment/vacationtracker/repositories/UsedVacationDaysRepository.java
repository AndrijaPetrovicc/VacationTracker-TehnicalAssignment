package com.technicalassignment.vacationtracker.repositories;

import  com.technicalassignment.vacationtracker.models.UsedVacationDays;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsedVacationDaysRepository extends JpaRepository<UsedVacationDays,Long> {
}
