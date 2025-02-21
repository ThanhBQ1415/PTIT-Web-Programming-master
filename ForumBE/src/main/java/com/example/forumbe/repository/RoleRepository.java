package com.example.forumbe.repository;

import com.example.forumbe.common.ERole;
import com.example.forumbe.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(ERole name);
}
