package com.school.fa.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.school.fa.view.response.DashboardResult;

import javax.persistence.*;
import java.util.Date;

@SqlResultSetMapping(name = "DashboardResultMapping",
        classes = @ConstructorResult(
                targetClass = DashboardResult.class,
                columns = {@ColumnResult(name = "teacherNm"),
                        @ColumnResult(name = "teacherEmailId"),
                        @ColumnResult(name = "teacherProPic"),
                        @ColumnResult(name = "studentNm"),
                        @ColumnResult(name = "studentEmailId"),
                        @ColumnResult(name = "studentRollNo"),
                        @ColumnResult(name = "studentProPic")
                }
        )
)
@Entity
@Table(name = "student", uniqueConstraints={@UniqueConstraint(columnNames={"student_email"})})
public class StudentEntity {

    private int studentId;

    private String studentName;

    private String studentPP;

    private String studentEmail;

    private int studentRoll;

    private TeacherEntity teacher;

    @JsonIgnore
    private String createdBy;

    @JsonIgnore
    private Date createdOn;

    @Id @GeneratedValue
    @Column(name="student_id")
    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }
    @Column(name="student_name")
    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
    @Column(name="student_pro_pic")
    public String getStudentPP() {
        return studentPP;
    }

    public void setStudentPP(String studentPP) {
        this.studentPP = studentPP;
    }
    @Column(name="student_email")
    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    @Column(name="student_roll")
    public int getStudentRoll() {
        return studentRoll;
    }

    public void setStudentRoll(int studentRoll) {
        this.studentRoll = studentRoll;
    }

    @ManyToOne
    @JoinColumn(name="teacher_id")
    public TeacherEntity getTeacher() { return teacher; }

    public void setTeacher(TeacherEntity teacher) { this.teacher = teacher; }

    @Column(name="created_by")
    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    @Column(name="created_on")
    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", studentName='" + studentName + '\'' +
                ", studentPP='" + studentPP + '\'' +
                ", studentEmail='" + studentEmail + '\'' +
                ", studentRoll=" + studentRoll +
                ", teacherId='" + teacher + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", createdOn=" + createdOn +
                '}';
    }
}
