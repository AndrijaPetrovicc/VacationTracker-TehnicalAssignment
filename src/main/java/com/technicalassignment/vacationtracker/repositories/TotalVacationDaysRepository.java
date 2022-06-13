package com.technicalassignment.vacationtracker.repositories;

import com.technicalassignment.vacationtracker.models.TotalVacationDays;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TotalVacationDaysRepository extends JpaRepository<TotalVacationDays, Long> {
}
