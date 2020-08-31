package com.school.fa.controller;

import com.school.fa.constant.ServiceConstants;
import com.school.fa.entity.StudentEntity;
import com.school.fa.model.StudentDTO;
import com.school.fa.service.IStudentService;
import com.school.fa.view.response.StudentCreateResponse;
import com.school.fa.view.response.StudentDataResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/student")
public class StudentController {

    @Autowired
    private IStudentService studentService;

    @GetMapping("/getStudentDetails")
    public ResponseEntity<StudentDataResponse> getStudentDetails(){
        StudentDataResponse response = new StudentDataResponse();
        try {
            response = studentService.getStudentDetails();

        return ResponseEntity.ok(response);
        }catch(Exception e){
            System.out.println(ServiceConstants.ERROR_OCCURRED +e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/createStudent")
    public ResponseEntity<StudentCreateResponse> createStudent(RequestEntity<List<StudentDTO>> request){
        try {
            if (null != request && null != request.getBody() && request.getBody() instanceof List) {
                StudentCreateResponse response = studentService.createStudents(request.getBody());
                if(null!=response && ServiceConstants.SUCCESS.equalsIgnoreCase(response.getStatus()))
                    return ResponseEntity.status(HttpStatus.CREATED).body(response);
                else
                    return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(response);
            }else
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }catch(Exception e){
            System.out.println(ServiceConstants.ERROR_OCCURRED+e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
