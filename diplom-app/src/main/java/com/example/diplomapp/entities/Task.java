package com.example.diplomapp.entities;

import com.example.diplomapp.enums.TaskType;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    String name;

    String status;

    @Column(name = "totaltime")
    String totalTime;

    @Column(name = "elapsedtime")
    String elapsedTime;

    @Column(name = "tasktype")
    @Enumerated(EnumType.STRING)
    TaskType taskType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User assignee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;

    public Task(String name, String elapsedTime, TaskType type, User assignee, Project project) {
        this.name = name;
        this.elapsedTime = elapsedTime;
        this.taskType = type;
        this.assignee = assignee;
        this.project = project;
    }

    public Task(String name, String elapsedTime, TaskType taskType, User assignee) { //for testing
        this.name = name;
        this.elapsedTime = elapsedTime;
        this.taskType = taskType;
        this.assignee = assignee;
    }

    public Task() {} //for JPA
}
