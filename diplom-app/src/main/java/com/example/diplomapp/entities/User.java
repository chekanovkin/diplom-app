package com.example.diplomapp.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@EqualsAndHashCode(exclude = {"projects", "userTasks", "userDaysWorked"})
@ToString(exclude = {"userTasks", "projects", "userDaysWorked"})
@Table(name = "usr")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    private String password;

    private String name;

    private String surname;

    private String patronymic;

    private double salary;

    private String department;

    private String qualification;

    private String position;

    private String email;

    private String filename;

    private String phone;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "assignee", cascade = CascadeType.ALL)
    private Set<Task> userTasks = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "worker")
    private Set<WorkingDay> userDaysWorked = new HashSet<>();

    @ManyToMany()
    @JoinTable(
        name = "users_projects",
        joinColumns = {@JoinColumn(name = "user_id")},
        inverseJoinColumns = {@JoinColumn(name = "project_id")}
    )
    private Set<Project> projects = new HashSet<>();

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    public User() {} //for JPA

    public User(String username, String password, String name, String surname) { //for EmploymentAssessment test
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() { return true; }
}
