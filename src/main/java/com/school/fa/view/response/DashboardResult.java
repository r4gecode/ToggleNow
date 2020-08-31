package com.school.fa.view.response;

public class DashboardResult {

    //teacher data
    public String teacherNm;
    public String teacherEmailId;
    public String teacherProPic;
    //student data
    public String studentNm;
    public String studentEmailId;
    public int studentRollNo;
    public String studentProPic;

    public DashboardResult() {
    }

    public DashboardResult(String teacherNm, String teacherEmailId, String teacherProPic, String studentNm, String studentEmailId, int studentRollNo, String studentProPic) {
        this.teacherNm = teacherNm;
        this.teacherEmailId = teacherEmailId;
        this.teacherProPic = teacherProPic;
        this.studentNm = studentNm;
        this.studentEmailId = studentEmailId;
        this.studentRollNo = studentRollNo;
        this.studentProPic = studentProPic;
    }

    public String getTeacherNm() {
        return teacherNm;
    }

    public void setTeacherNm(String teacherNm) {
        this.teacherNm = teacherNm;
    }

    public String getTeacherEmailId() {
        return teacherEmailId;
    }

    public void setTeacherEmailId(String teacherEmailId) {
        this.teacherEmailId = teacherEmailId;
    }

    public String getTeacherProPic() {
        return teacherProPic;
    }

    public void setTeacherProPic(String teacherProPic) {
        this.teacherProPic = teacherProPic;
    }

    public String getStudentNm() {
        return studentNm;
    }

    public void setStudentNm(String studentNm) {
        this.studentNm = studentNm;
    }

    public String getStudentEmailId() {
        return studentEmailId;
    }

    public void setStudentEmailId(String studentEmailId) {
        this.studentEmailId = studentEmailId;
    }

    public int getStudentRollNo() {
        return studentRollNo;
    }

    public void setStudentRollNo(int studentRollNo) {
        this.studentRollNo = studentRollNo;
    }

    public String getStudentProPic() {
        return studentProPic;
    }

    public void setStudentProPic(String studentProPic) {
        this.studentProPic = studentProPic;
    }

    @Override
    public String toString() {
        return "DashboardResults{" +
                "teacherNm='" + teacherNm + '\'' +
                ", teacherEmailId='" + teacherEmailId + '\'' +
                ", teacherProPic='" + teacherProPic + '\'' +
                ", studentNm='" + studentNm + '\'' +
                ", studentEmailId='" + studentEmailId + '\'' +
                ", studentRollNo='" + studentRollNo + '\'' +
                ", studentProPic='" + studentProPic + '\'' +
                '}';
    }
}
