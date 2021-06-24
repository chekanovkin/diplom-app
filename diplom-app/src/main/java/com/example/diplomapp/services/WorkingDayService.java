package com.example.diplomapp.services;

import com.example.diplomapp.entities.WorkingDay;
import com.example.diplomapp.repos.WorkingDayRepository;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkingDayService {

    private final WorkingDayRepository workingDayRepository;

    public WorkingDayService(WorkingDayRepository workingDayRepository) {
        this.workingDayRepository = workingDayRepository;
    }

    public boolean addDayWorked(WorkingDay day) {
        WorkingDay dayFromDB = workingDayRepository.findByWorkedDayAndWorker(day.getWorkedDay(), day.getWorker());
        if (ObjectUtils.isNotEmpty(dayFromDB)) {
            return false;
        }

        workingDayRepository.save(day);
        return true;
    }
}
