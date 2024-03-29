package model;

import java.util.HashMap;

public class Student extends People {
    private String studentId;
    private String degree;
    private HashMap<String, String> awards;

    public Student(){

    }

    public Student(String name, String studentId,String email,  String degree) {
        super(name, email);
        this.studentId = studentId;
        this.degree = degree;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public HashMap<String, String> getAwards() {
        return awards;
    }

    public void setAwards(HashMap<String, String> awards) {
        this.awards = awards;
    }

    @Override
    public String toString() {
        return super.toString() + ", Student ID: " + studentId + ", Degree: " + degree+",Awards: "+awards;
    }
}

