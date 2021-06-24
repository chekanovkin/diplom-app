package com.example.diplomapp.services;

import com.example.diplomapp.entities.User;
import com.example.diplomapp.entities.WorkingDay;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class SalaryCountServiceTest {

    @Autowired
    SalaryCountService salaryCountService;

    @Test
    void countSalary() {
        User user = new User("user", "password", "Андрей", "Андреев");
        Set<WorkingDay> workedDays = Set.of(new WorkingDay(LocalDate.now(), user),
                                            new WorkingDay(LocalDate.now().plusDays(1), user),
                                            new WorkingDay(LocalDate.now().plusDays(2), user),
                                            new WorkingDay(LocalDate.now().plusDays(3), user),
                                            new WorkingDay(LocalDate.now().plusMonths(1), user));
        user.setUserDaysWorked(workedDays);
        user.setSalary(60000.0);
        salaryCountService.countSalary(user);
        String salary = String.format("%.2f", salaryCountService.countSalary(user));
        assertEquals("8181,82", salary);
    }
}