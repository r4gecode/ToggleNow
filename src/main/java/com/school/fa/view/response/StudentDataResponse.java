package com.school.fa.view.response;

import com.school.fa.entity.StudentEntity;

import java.util.List;

public class StudentDataResponse {

    public List<StudentEntity> studentList;
    public String status;
    public String message;

    public List<StudentEntity> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<StudentEntity> studentList) {
        this.studentList = studentList;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
