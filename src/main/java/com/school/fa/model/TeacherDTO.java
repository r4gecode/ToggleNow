package com.school.fa.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import java.util.Objects;

public class TeacherDTO {

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int teacherId;

    private String teacherName;

    private String teacherPP;

    private String teacherEmail;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String teacherPassword;

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherPP() {
        return teacherPP;
    }

    public void setTeacherPP(String teacherPP) {
        this.teacherPP = teacherPP;
    }

    public String getTeacherEmail() {
        return teacherEmail;
    }

    public void setTeacherEmail(String teacherEmail) {
        this.teacherEmail = teacherEmail;
    }

    public String getTeacherPassword() { return teacherPassword; }

    public void setTeacherPassword(String teacherPassword) { this.teacherPassword = teacherPassword; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeacherDTO that = (TeacherDTO) o;
        return teacherName.equals(that.teacherName) &&
                teacherEmail.equals(that.teacherEmail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teacherName, teacherEmail);
    }
}
