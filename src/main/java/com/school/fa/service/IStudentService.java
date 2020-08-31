package com.school.fa.service;

import com.school.fa.model.StudentDTO;
import com.school.fa.view.response.StudentCreateResponse;
import com.school.fa.view.response.StudentDataResponse;

import java.util.List;

public interface IStudentService {

    public StudentDataResponse getStudentDetails();
    public StudentCreateResponse createStudents(List<StudentDTO> studentsList);

}
