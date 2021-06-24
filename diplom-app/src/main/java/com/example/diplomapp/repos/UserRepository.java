package com.example.diplomapp.repos;

import com.example.diplomapp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    @Query("from User u where LOWER(concat(u.surname, ' ', u.name, ' ', u.patronymic)) like LOWER(concat('%', :name, '%'))")
    List<User> findByName(@Param("name") String name);
}
