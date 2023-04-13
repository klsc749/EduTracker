package service;

import dao.StudentDao;
import model.Student;

public class StudentService {
    private StudentDao studentDao = new StudentDao();

    // public void saveStudent(Student student){
    //    studentDao.save(student);
    //}

    //修改email 通过dao存到student.txt
    /**
        update student email and save the information in student.txt

        @param email user's input of new email address

     */
    public void updateEmail(String email) throws Exception{
        Student stu=studentDao.getStudentInfo();
            if(studentDao.CheckEmailLegal(email)){
                stu.setEmail(email);
                studentDao.save(stu);
            }
            else
                throw new Exception();
    }
}
