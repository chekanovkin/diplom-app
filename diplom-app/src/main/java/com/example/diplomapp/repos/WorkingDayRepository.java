package com.example.diplomapp.repos;

import com.example.diplomapp.entities.User;
import com.example.diplomapp.entities.WorkingDay;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface WorkingDayRepository extends JpaRepository<WorkingDay, Long> {

    WorkingDay findByWorkedDayAndWorker(LocalDate workedDay, User user);
}
