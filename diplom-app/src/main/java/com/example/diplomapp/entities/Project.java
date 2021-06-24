package com.example.diplomapp.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@EqualsAndHashCode(exclude="projectTasks")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String description;

    private int readinessDegree;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "users_projects",
        joinColumns = {@JoinColumn(name = "project_id")},
        inverseJoinColumns = {@JoinColumn(name = "user_id")}
    )
    private Set<User> workers = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "project")
    private Set<Task> projectTasks = new HashSet<>();

    public Project() {} //for JPA
}
