package com.school.fa.repository;

import com.school.fa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {
    User findByEmail(String email);
}
