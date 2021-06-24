package com.example.diplomapp.services;

import com.example.diplomapp.entities.Task;
import com.example.diplomapp.entities.User;
import com.example.diplomapp.enums.EmploymentCriteriaEnum;
import com.example.diplomapp.utils.TimeUtil;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class EmploymentAssessmentService {
    public double countAssessment(User user) {
        Set<Task> taskSet = user.getUserTasks();
        double count = 0;
        for (Task task : taskSet) {
            count += (task.getTaskType().getAdd_responsibility() * EmploymentCriteriaEnum.ADDITIONAL_RESPONSIBILITY.getValue() +
            task.getTaskType().getComplexity() * EmploymentCriteriaEnum.COMPLEXITY.getValue() +
            task.getTaskType().getFormat() * EmploymentCriteriaEnum.FORMAT.getValue() +
            task.getTaskType().getIndependence() * EmploymentCriteriaEnum.INDEPENDENCE.getValue() +
            task.getTaskType().getScale_and_complexity() * EmploymentCriteriaEnum.SCALE_AND_COMPLEXITY.getValue())
                * TimeUtil.getPercentOfWorkingDay(task.getElapsedTime());
        }
        return count;
    }
}
