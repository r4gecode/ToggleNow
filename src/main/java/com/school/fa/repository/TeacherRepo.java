package com.school.fa.repository;

import com.school.fa.entity.TeacherEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepo extends JpaRepository<TeacherEntity, Integer> {

}
