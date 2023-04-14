package model;


public class Student extends People {
    private String studentId;
    private String degree;

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

    @Override
    public String toString() {
        return super.toString() + ", Student ID: " + studentId + ", Degree: " + degree;
    }
}

