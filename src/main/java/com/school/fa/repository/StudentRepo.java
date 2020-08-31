package com.school.fa.repository;

import com.school.fa.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepo extends JpaRepository<StudentEntity, Integer>{

    public StudentEntity findByStudentId(int studentId);

}
