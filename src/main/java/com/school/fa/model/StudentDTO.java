package com.school.fa.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.school.fa.entity.TeacherEntity;

import java.util.Date;
import java.util.Objects;

public class StudentDTO {

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int studentId;

    private String studentName;

    private String studentPP;

    private String studentEmail;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String studentPassword;

    private int studentRoll;

    private TeacherDTO teacher;

    @JsonIgnore
    private String createdBy;

    @JsonIgnore
    private Date createdOn;


    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentPP() {
        return studentPP;
    }

    public void setStudentPP(String studentPP) {
        this.studentPP = studentPP;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public String getStudentPassword() { return studentPassword; }

    public void setStudentPassword(String studentPassword) { this.studentPassword = studentPassword; }

    public int getStudentRoll() {
        return studentRoll;
    }

    public void setStudentRoll(int studentRoll) {
        this.studentRoll = studentRoll;
    }

    public TeacherDTO getTeacher() {
        return teacher;
    }

    public void setTeacher(TeacherDTO teacher) {
        this.teacher = teacher;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentDTO that = (StudentDTO) o;
        return studentRoll == that.studentRoll &&
                studentName.equals(that.studentName) &&
                studentEmail.equals(that.studentEmail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentName, studentEmail, studentRoll);
    }
}
