package com.school.fa.repository;


import com.school.fa.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role, Integer> {
    Role findByRole(String role);
}
