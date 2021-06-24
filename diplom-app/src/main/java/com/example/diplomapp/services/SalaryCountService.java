package com.example.diplomapp.services;

import com.example.diplomapp.entities.User;
import com.example.diplomapp.entities.WorkingDay;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SalaryCountService {

    private final Set<LocalDate> holidays = Set.of(
        LocalDate.of(2021, 1, 1),
        LocalDate.of(2021, 1, 2),
        LocalDate.of(2021, 1, 3),
        LocalDate.of(2021, 1, 4),
        LocalDate.of(2021, 1, 5),
        LocalDate.of(2021, 1, 6),
        LocalDate.of(2021, 1, 7),
        LocalDate.of(2021, 1, 8),
        LocalDate.of(2021, 1, 9),
        LocalDate.of(2021, 1, 10),
        LocalDate.of(2021, 2, 21),
        LocalDate.of(2021, 2, 22),
        LocalDate.of(2021, 2, 23),
        LocalDate.of(2021, 3, 6),
        LocalDate.of(2021, 3, 7),
        LocalDate.of(2021, 3, 8),
        LocalDate.of(2021, 5, 1),
        LocalDate.of(2021, 5, 2),
        LocalDate.of(2021, 5, 3),
        LocalDate.of(2021, 5, 8),
        LocalDate.of(2021, 5, 9),
        LocalDate.of(2021, 5, 10),
        LocalDate.of(2021, 6, 12),
        LocalDate.of(2021, 6, 13),
        LocalDate.of(2021, 6, 14),
        LocalDate.of(2021, 11, 4),
        LocalDate.of(2021, 11, 5),
        LocalDate.of(2021, 11, 6),
        LocalDate.of(2021, 11, 7),
        LocalDate.of(2021, 12, 31));

    public double countSalary(User user) {
        LocalDate startOfMonth = LocalDate.now().withDayOfMonth(1);
        LocalDate endOfMonth = LocalDate.now().withDayOfMonth(Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH));
        Set<WorkingDay> userWorkedDaysList = user.getUserDaysWorked();
        Set<WorkingDay> workedDaysThisMonth = userWorkedDaysList.stream().filter(workedDay -> workedDay.getWorkedDay().isBefore(endOfMonth)
            && workedDay.getWorkedDay().isAfter(startOfMonth)
            && !workedDay.getWorkedDay().getDayOfWeek().equals(DayOfWeek.SATURDAY)
            && !workedDay.getWorkedDay().getDayOfWeek().equals(DayOfWeek.SUNDAY)
            && !holidays.contains(workedDay.getWorkedDay())).collect(Collectors.toSet());
        Set<LocalDate> businessDays = new HashSet<>();
        LocalDate start = startOfMonth;
        while (start.isBefore(endOfMonth.plusDays(1))) {
            if (!(start.getDayOfWeek().equals(DayOfWeek.SATURDAY) || start.getDayOfWeek().equals(DayOfWeek.SUNDAY))) {
                businessDays.add(start);
            }
            start = start.plusDays(1);
        }
        double a = user.getSalary() / businessDays.size() * workedDaysThisMonth.size();
        return user.getSalary() / businessDays.size() * workedDaysThisMonth.size();
    }
}
