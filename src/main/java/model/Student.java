package model;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.util.ArrayList;
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

    public void setAwards(String time,String award){
        awards.put(time,award);
    }

    public HashMap<String, String> getAwards() {
        return awards;
    }

    @Override
    public String toString() {
        return super.toString() + ", Student ID: " + studentId + ", Degree: " + degree+",Awards: "+awards;
    }
}

