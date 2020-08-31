package com.school.fa.service.impl;

import com.school.fa.constant.ServiceConstants;
import com.school.fa.entity.StudentEntity;
import com.school.fa.model.StudentDTO;
import com.school.fa.model.TeacherDTO;
import com.school.fa.repository.StudentRepo;
import com.school.fa.service.IDashboardService;
import com.school.fa.view.response.DashboardResponseWrapper;
import com.school.fa.view.response.DashboardResult;
import com.school.fa.view.response.DashboardResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class DashboardServiceImpl implements IDashboardService, ServiceConstants {

    @Autowired
    EntityManagerFactory emf;

    @Autowired
    StudentRepo studentRepo;

    @Autowired
    private IDashboardService IDashboardRepo;

    @Override
    public List<DashboardResultResponse> fetchStudentTeacherRel() {
        List<DashboardResultResponse> resultResponses = null;
        Map<TeacherDTO,List<StudentDTO>> studentTeacherMap =  new LinkedHashMap<>();
        try{
            List<StudentEntity> studentEList = studentRepo.findAll();
            //populate Teacher-Student Relationship Map
            studentTeacherMap = populateTeacherStudentRelMap(studentEList,studentTeacherMap);

            //Transform Teacher Student Relationship Map to List
            resultResponses = populateTeacherStudentList(studentTeacherMap);

            if(!CollectionUtils.isEmpty(resultResponses)) {
                System.out.println(DASHBOARD_DATA_FETCHED_SUCCESSFULLY);
            }else{
                System.out.println(NO_DETAILS_FOUND);
            }
        }catch(Exception e){
            System.out.println(ERROR_OCCURRED+e.getMessage());
        }
        return resultResponses;
    }

    @Override
    public DashboardResponseWrapper getStudentsByTeachers() {
        List<DashboardResult> dashboardResultsList = null;
        List<DashboardResultResponse> dashboardResponseList = new ArrayList<>();
        DashboardResponseWrapper dashboardResponseWrapper = new DashboardResponseWrapper();
        Map<TeacherDTO,List<StudentDTO>> studentTeacherMap =  new LinkedHashMap<>();
        try{
            List<StudentEntity> studentEList = studentRepo.findAll();
            //populate Teacher-Student Relationship Map
            studentTeacherMap = populateTeacherStudentRelMap(studentEList,studentTeacherMap);

            //Transform Teacher Student Relationship Map to List
            List<DashboardResultResponse> resultResponses = populateTeacherStudentList(studentTeacherMap);

            dashboardResponseWrapper.setDashboardResults(resultResponses);
            dashboardResponseWrapper.setStatus(SUCCESS);
            if(!CollectionUtils.isEmpty(resultResponses)) {
                dashboardResponseWrapper.setMessage(DASHBOARD_DATA_FETCHED_SUCCESSFULLY);
            }else{
                dashboardResponseWrapper.setMessage(NO_DETAILS_FOUND);
            }
        }catch(Exception e){
            System.out.println(ERROR_OCCURRED+e.getMessage());
            dashboardResponseWrapper.setStatus(FAIL);
            dashboardResponseWrapper.setMessage(REQUEST_PROCESSED_FAILED_DUE_TO+e.getMessage());
        }
        return dashboardResponseWrapper;
    }

    private Map<TeacherDTO, List<StudentDTO>> populateTeacherStudentRelMap(List<StudentEntity> studentEList, Map<TeacherDTO, List<StudentDTO>> studentTeacherMap){

        if(null!=studentTeacherMap) {
            for (StudentEntity element : studentEList) {

                StudentDTO student = new StudentDTO();
                student.setStudentName(element.getStudentName());
                student.setStudentEmail(element.getStudentEmail());
                student.setStudentPP(element.getStudentPP());
                student.setStudentRoll(element.getStudentRoll());

                TeacherDTO teacher = new TeacherDTO();
                teacher.setTeacherName(element.getTeacher().getTeacherName());
                teacher.setTeacherEmail(element.getTeacher().getTeacherEmail());
                teacher.setTeacherPP(element.getTeacher().getTeacherPP());

                if (studentTeacherMap.containsKey(teacher)) {
                    List<StudentDTO> studentDTOList = studentTeacherMap.get(teacher);
                    studentDTOList.add(student);
                } else {
                    List<StudentDTO> studentDTOList = new ArrayList<StudentDTO>();
                    studentDTOList.add(student);
                    studentTeacherMap.put(teacher, studentDTOList);
                }
            }
        }
        return studentTeacherMap;
    }

    private List<DashboardResultResponse> populateTeacherStudentList(Map<TeacherDTO,List<StudentDTO>> studentTeacherMap){
        List<DashboardResultResponse> resultResponses = new ArrayList<>();
        if(!CollectionUtils.isEmpty(studentTeacherMap)) {
            studentTeacherMap.entrySet().forEach(entry -> {
                DashboardResultResponse resultResponse = new DashboardResultResponse();
                resultResponse.setTeacher(entry.getKey());
                resultResponse.setStudents(entry.getValue());
                resultResponses.add(resultResponse);
            });
        }
        return resultResponses;
    }

    @Override
    public List<DashboardResult> findAllStudentsByTeacher() throws Exception {

        final String GET_ALL_STUDENTS_BY_TEACHER = "select t.teacher_name as teacherNm, t.teacher_email as teacherEmailId, t.teacher_pro_pic as teacherProPic, s.student_name as studentNm, s.student_roll as studentRollNo, s.student_pro_pic as studentProPic, s.student_email as studentEmailId from student s inner join teacher t on s.teacher_id = t.teacher_id";
        EntityManager em = null;
        @SuppressWarnings("unchecked")
        List<DashboardResult> resultsList = null;
        try {
            //em.getTransaction().begin( );
            em = emf.createEntityManager();

            Query query = em.createNativeQuery(GET_ALL_STUDENTS_BY_TEACHER, DASHBOARD_RESULT_MAPPING);
            resultsList = (List<DashboardResult>) query.getResultList();
        }catch(Exception e){
            System.out.println(ERROR_OCCURRED+e.getMessage());
            throw new Exception(e);
        }finally {
            em.close();
        }
        return resultsList;
    }
/*    public DashboardResponseWrapper getStudentsByTeachers() {
        List<DashboardResult> dashboardResultsList = null;
        List<DashboardResultResponse> dashboardResponseList = new ArrayList<>();
        DashboardResponseWrapper dashboardResponseWrapper = new DashboardResponseWrapper();
        try{
            dashboardResultsList = findAllStudentsByTeacher();
//            Map<TeacherEntity,>
            dashboardResponseList  = dashboardResultsList.parallelStream().map(result-> {

                TeacherEntity teacher = new TeacherEntity();
                teacher.setTeacherName(result.getTeacherNm());
                teacher.setTeacherEmail(result.getTeacherEmailId());
                teacher.setTeacherPP(result.getTeacherProPic());

                DashboardResultResponse dashboardResultResponse = new DashboardResultResponse();
                dashboardResultResponse.setTeacher(teacher);
                return dashboardResultResponse;
            }).collect(Collectors.toList());


            dashboardResponseWrapper.setDashboardResultsList(dashboardResultsList);
            dashboardResponseWrapper.setStatus("Success");
            if(!CollectionUtils.isEmpty(dashboardResultsList)) {
                dashboardResponseWrapper.setMessage("Dashboard Data Fetched Successfully");
            }else{
                dashboardResponseWrapper.setMessage("No Details Found");
            }
        }catch(Exception e){
            System.out.println("Error Occurred - "+e.getMessage());
            dashboardResponseWrapper.setStatus("Fail");
            dashboardResponseWrapper.setMessage("Request processed Failed due to "+e.getMessage());
        }
        return dashboardResponseWrapper;
    }*/
}
