package com.school.fa.controller;

import com.school.fa.constant.ServiceConstants;
import com.school.fa.service.IDashboardService;
import com.school.fa.view.response.DashboardResponseWrapper;
import com.school.fa.view.response.DashboardResult;
import com.school.fa.view.response.DashboardResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    @Autowired
    IDashboardService dashboardService;

    @GetMapping("/fetchStudentTeacherRel")
    public ResponseEntity<List<DashboardResultResponse>> fetchStudentTeacherRel(){

        List<DashboardResultResponse> response = new ArrayList<>();
        try {
            response = dashboardService.fetchStudentTeacherRel();
            return ResponseEntity.ok(response);
        }catch(Exception e){
            System.out.println(ServiceConstants.ERROR_OCCURRED+e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/getStudentsByTeacher")
    public ResponseEntity<DashboardResponseWrapper> getStudentsByTeachers(){

        DashboardResponseWrapper response = new DashboardResponseWrapper();
        try {
            response = dashboardService.getStudentsByTeachers();
            return ResponseEntity.ok(response);
        }catch(Exception e){
            System.out.println(ServiceConstants.ERROR_OCCURRED+e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
