package com.school.fa.view.response;

import com.school.fa.model.StudentDTO;
import com.school.fa.model.TeacherDTO;

import java.util.List;

public class DashboardResultResponse {

        public TeacherDTO teacher;
        public List<StudentDTO> students;

    public TeacherDTO getTeacher() {
        return teacher;
    }

    public void setTeacher(TeacherDTO teacher) {
        this.teacher = teacher;
    }

    public List<StudentDTO> getStudents() {
        return students;
    }

    public void setStudents(List<StudentDTO> students) {
        this.students = students;
    }
}
