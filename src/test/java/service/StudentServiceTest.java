package service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StudentServiceTest {
    StudentService studentService=new StudentService();

    @Test
    void TestUpdateEmail() throws Exception {
        //String email1="123@163.com";
        //studentService.updateEmail(email1);


        boolean thrown=false;
        String email2="!!!@163.com";
        try{
            studentService.updateEmail(email2);
        }catch (Exception e){
            thrown=true;
        }
        assertTrue(thrown);


    }
}
