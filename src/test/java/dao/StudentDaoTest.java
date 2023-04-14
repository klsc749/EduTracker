package dao;

import model.Student;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StudentDaoTest {
    StudentDao studentDao=new StudentDao();

    @Test
    void testSave(){
        Student student=new Student();
        student.setStudentId("A002");
        student.setEmail("A002@163.com");
        student.setDegree("2");
        student.setName("WangWang");

        studentDao.save(student);
    }


    @Test
    void TestCheckEmail() throws Exception {
        boolean thrown = false;
        String email1="123Aa@163.com";
        assertEquals(true,studentDao.CheckEmailLegal(email1));


        try{
            String email2="!!!@163.com";
            studentDao.CheckEmailLegal(email2);
        }catch(Exception e){
            thrown=true;
        }
        assertTrue(thrown);

        thrown=false;
        try{
            String email3="abc.ac@142.com";
            studentDao.CheckEmailLegal(email3);
        }catch(Exception e){
            thrown=true;
        }
        assertTrue(thrown);

        thrown=false;
        try{
            String email4="abc";
            studentDao.CheckEmailLegal(email4);
        }catch(Exception e){
            thrown=true;
        }
        assertTrue(thrown);



    }

}
