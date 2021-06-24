package com.example.diplomapp.repos;

import com.example.diplomapp.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query("from Task t where concat(t.name) like concat('%', :name, '%')")
    List<Task> findByName(@Param("name") String name);
}
