package com.example.diplomapp.services;

import com.example.diplomapp.entities.Task;
import com.example.diplomapp.entities.User;
import com.example.diplomapp.enums.TaskType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
class EmploymentAssessmentServiceTest {

    @Autowired
    EmploymentAssessmentService assessmentService;

    @Test
    void countAssessment() {
        User user = new User("user", "password", "Андрей", "Андреев");
        Set<Task> taskSet = Set.of(new Task("task1", "5h 45m", TaskType.DEVELOPEMENT, user),
                                   new Task("task2", "2h 15m", TaskType.ANALYSIS, user),
                                   new Task("task3", "8h 0m", TaskType.DOCUMENTATION, user));
        user.setUserTasks(taskSet);
        double assess = assessmentService.countAssessment(user);
        assertEquals(assess, 9.3046875);
    }
}