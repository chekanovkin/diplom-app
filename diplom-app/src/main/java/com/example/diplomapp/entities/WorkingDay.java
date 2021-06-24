package com.example.diplomapp.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "working_day")
public class WorkingDay {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    LocalDate workedDay;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User worker;

    public WorkingDay(LocalDate workedDay, User worker) {
        this.workedDay = workedDay;
        this.worker = worker;
    }

    public WorkingDay() {} // for JPA
}
