package com.example.news.repository;

import com.example.news.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByName(String user);

    boolean existsByName(String name);
}
