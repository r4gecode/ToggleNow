package com.school.fa.view.response;

import com.school.fa.entity.StudentEntity;
import com.school.fa.model.StudentDTO;

import java.util.List;

public class StudentCreateResponse {

    public List<StudentDTO> persistedList;
    public List<StudentDTO> nonPersistedList;
    public String status;
    public String message;

    public List<StudentDTO> getPersistedList() {
        return persistedList;
    }

    public void setPersistedList(List<StudentDTO> persistedList) {
        this.persistedList = persistedList;
    }

    public List<StudentDTO> getNonPersistedList() {
        return nonPersistedList;
    }

    public void setNonPersistedList(List<StudentDTO> nonPersistedList) {
        this.nonPersistedList = nonPersistedList;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
