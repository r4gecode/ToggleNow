package com.school.fa.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "teacher",uniqueConstraints={@UniqueConstraint(columnNames={"teacher_email"})})
public class TeacherEntity {

    @Id
    @GeneratedValue
    @Column(name="teacher_id")
    private int teacherId;
    @Column(name="teacher_name")
    private String teacherName;
    @Column(name="teacher_pro_pic")
    private String teacherPP;
    @Column(name="teacher_email")
    private String teacherEmail;

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

    @Override
    public String toString() {
        return "Teacher{" +
                "teacherId=" + teacherId +
                ", teacherName='" + teacherName + '\'' +
                ", teacherPP='" + teacherPP + '\'' +
                ", teacherEmail='" + teacherEmail + '\'' +
                '}';
    }
}
