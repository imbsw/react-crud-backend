package com.example.vms.repository;

import com.example.vms.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("from User u where u.username = :username and u.active = 'Y'")
    Optional<User> findByUsername(String username);
}