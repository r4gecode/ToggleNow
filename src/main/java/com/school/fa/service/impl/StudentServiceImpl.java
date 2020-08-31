package com.school.fa.service.impl;

import com.school.fa.constant.ServiceConstants;
import com.school.fa.entity.StudentEntity;
import com.school.fa.entity.TeacherEntity;
import com.school.fa.model.StudentDTO;
import com.school.fa.model.TeacherDTO;
import com.school.fa.repository.StudentRepo;
import com.school.fa.repository.TeacherRepo;
import com.school.fa.service.IStudentService;
import com.school.fa.view.response.StudentCreateResponse;
import com.school.fa.view.response.StudentDataResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements IStudentService, ServiceConstants {

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private TeacherRepo teacherRepo;

    @Override
    public StudentDataResponse getStudentDetails() {
        List<StudentEntity> studentList = null;
        StudentDataResponse response = new StudentDataResponse();
        try{
            studentList = studentRepo.findAll();
            response.setStudentList(studentList);
            response.setStatus(SUCCESS);
            if(!CollectionUtils.isEmpty(studentList)) {
                response.setMessage(STUDENT_DETAILS_FETCHED_SUCCESSFULLY);
            }else{
                response.setMessage(NO_STUDENT_DETAILS_FOUND);
            }
        }catch(Exception e){

            System.out.println(ERROR_OCCURRED +e.getMessage());
            response.setStatus(FAIL);
            response.setMessage(REQUEST_PROCESSED_FAILED_DUE_TO +e.getMessage());
        }
        return response;
    }

    @Override
    public StudentCreateResponse createStudents(List<StudentDTO> studentsList) {

        StudentCreateResponse response = new StudentCreateResponse();
        List<StudentEntity> validStudentList = null;
        List<StudentDTO> inValidStudentList = null;
        try {
            if (!CollectionUtils.isEmpty(studentsList)) {
                inValidStudentList = studentsList;
                validStudentList = studentsList.parallelStream()
                        .map(studentDTO -> {
                            if(validateStudentData(studentDTO)) {
                                StudentEntity studentEntity = copyDTOToStudentEntity(studentDTO);
                                return studentEntity;
                            }else return null;
                        }).collect(Collectors.toList());
                if (!CollectionUtils.isEmpty(validStudentList)) {
                    inValidStudentList.removeAll(copyEntityPropertiesToDTO(validStudentList));
                    validStudentList.removeAll(Collections.singleton(null));
                    validStudentList.parallelStream().forEach((student) -> {
                        //Optional<TeacherEntity> teacher = teacherRepo.findById(Integer.valueOf(student.getTeacher().getTeacherId()));
                        student.setCreatedBy(student.getTeacher().getTeacherName());
                        student.setCreatedOn(new Date());
                    });
                }
            }

            final List<StudentEntity> persistedEntityList = studentRepo.saveAll(validStudentList);
            List<StudentDTO> persistedList = copyEntityPropertiesToDTO(persistedEntityList);
            response.setPersistedList(persistedList);
            response.setNonPersistedList(inValidStudentList);
            response.setStatus(SUCCESS);
            response.setMessage(REQUEST_PROCESSED_SUCCESSFULLY);
        }catch(Exception e){
            System.out.println(ERROR_OCCURRED +e.getMessage());
            response.setStatus(FAIL);
            response.setMessage(REQUEST_PROCESSED_FAILED_DUE_TO +e.getMessage());
        }

        return response;
    }

    private boolean validateStudentData(StudentDTO student){
        if(!StringUtils.isEmpty(student.getStudentName())
                && !StringUtils.isEmpty(student.getStudentEmail())
                && !StringUtils.isEmpty(student.getStudentPassword())
                && !StringUtils.isEmpty(student.getTeacher().getTeacherId()) ){
                return Boolean.TRUE;
        } else {
            System.out.println(INVALID_STUDENT_MANDATORY_FIELDS_MISSING);
            return Boolean.FALSE;
        }
    }

    private List<StudentEntity> copyDTOPropertiesToEntity(List<StudentDTO> studentDTOList){
        List<StudentEntity> studentEntityList = studentDTOList.parallelStream().map(studentDTO -> {
            StudentEntity studentEntity = copyDTOToStudentEntity(studentDTO);
            return studentEntity;
        }).collect(Collectors.toList());
        return studentEntityList;
    }

    private StudentEntity copyDTOToStudentEntity(StudentDTO studentDTO) {
        StudentEntity studentEntity = new StudentEntity();
        BeanUtils.copyProperties(studentDTO, studentEntity);
        if (null != studentDTO.getTeacher()) {
            TeacherEntity teacherEntity = new TeacherEntity();
            teacherEntity.setTeacherId(studentDTO.getTeacher().getTeacherId());
            teacherEntity.setTeacherName(studentDTO.getTeacher().getTeacherName());
            teacherEntity.setTeacherEmail(studentDTO.getTeacher().getTeacherEmail());
            teacherEntity.setTeacherPP(studentDTO.getTeacher().getTeacherPP());
            studentEntity.setTeacher(teacherEntity);
        }
        return studentEntity;
    }

    private List<StudentDTO> copyEntityPropertiesToDTO(List<StudentEntity> studentEntityList){
        List<StudentDTO> studentDTOList = studentEntityList.parallelStream().map(studentEntity -> {
            StudentDTO studentDTO = new StudentDTO();
            BeanUtils.copyProperties(studentEntity,studentDTO);
            if(null!=studentEntity.getTeacher()){
                TeacherDTO teacherDTO = new TeacherDTO();
                teacherDTO.setTeacherId(studentEntity.getTeacher().getTeacherId());
                teacherDTO.setTeacherName(studentEntity.getTeacher().getTeacherName());
                teacherDTO.setTeacherEmail(studentEntity.getTeacher().getTeacherEmail());
                teacherDTO.setTeacherPP(studentEntity.getTeacher().getTeacherPP());
                studentDTO.setTeacher(teacherDTO);
            }
            return studentDTO;
        }).collect(Collectors.toList());
        return studentDTOList;
    }

    private  List<StudentEntity>  stubStudentData(){
            List<StudentEntity> studentList = new ArrayList<StudentEntity>();
            StudentEntity student = new StudentEntity();
            student.setStudentId(1);
            student.setStudentName("Animesh");
            student.setStudentEmail("animesharma@deloitte.com");
            student.setStudentRoll(1);
            student.setStudentPP("C:/user/animesharma/documents/picture/animesh1.jpg");
            student.setTeacher(null);
            studentList.add(student);
            return studentList;

    }
}
