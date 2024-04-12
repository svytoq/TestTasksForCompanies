package org.example.repositories;

import org.example.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface UserRepository extends JpaRepository<User,Integer> {
    @Query("SELECT q FROM User q WHERE q.name = :name")
    User findByName(String name);

    @Query("SELECT u FROM User u WHERE u.active = true")
    List<User> findAllActiveUsers();



    @Query("SELECT q FROM User q WHERE q.id = :id")
    User findUserById(int id);
}