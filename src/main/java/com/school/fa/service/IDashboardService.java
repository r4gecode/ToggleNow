package com.school.fa.service;

import com.school.fa.view.response.DashboardResponseWrapper;
import com.school.fa.view.response.DashboardResult;
import com.school.fa.view.response.DashboardResultResponse;

import java.util.List;

public interface IDashboardService {

    List<DashboardResultResponse> fetchStudentTeacherRel();

    public DashboardResponseWrapper getStudentsByTeachers();

    public List<DashboardResult> findAllStudentsByTeacher() throws Exception;
}
