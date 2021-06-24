package com.example.diplomapp.repos;

import com.example.diplomapp.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    @Query("from Project p where LOWER(concat(p.name)) like LOWER(concat('%', :name, '%'))")
    List<Project> findByName(@Param("name") String name);
}
