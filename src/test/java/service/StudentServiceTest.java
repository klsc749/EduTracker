package service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNull;

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

    @Test
    void TestGetAllAwards(){
        HashMap<String,String> AllAwards=studentService.GetAllAwards();
        assertEquals(1,AllAwards.size());
        System.out.println(AllAwards);
    }

    @Test
    void TestDateValidCheck(){
        String time="2021-02-02";
        assertTrue(studentService.isValidDate(time));

        String time1="2021-02-32";
        Assertions.assertFalse(studentService.isValidDate(time1));

        String time2="2021/02/02";
        Assertions.assertFalse(studentService.isValidDate(time2));

    }

    @Test
    void TestAddAwards() throws Exception {
        String time="2021-02-02";
        String content="First Class Scholarship";
        HashMap<String,String> allawards=studentService.AddAwards(time,content);
        assertEquals(2,allawards.size());
        System.out.println(allawards);


        time="2021-02-02";
        content="First Class Scholarship";
        try{
            allawards=studentService.AddAwards(time,content);
            //fail("An Exception Thrown");
        }catch(Exception e){
            assertEquals("Date Key already exists",e.getMessage());
        }finally{
            System.out.println(allawards);
        }

        time="2021/02/02";
        content="First Class Scholarship";
        try{
            allawards=studentService.AddAwards(time,content);
            //fail("An Exception Thrown");
        }catch(Exception e){
            assertEquals("Invalid time",e.getMessage());
        }finally{
            System.out.println(allawards);
        }
    }

    @Test
    void TestGetContentByDate() throws Exception{
        String time="2021-02-02";
        assertEquals("First Class Scholarship",studentService.GetContentByDate(time));

        time="2021/02/02";
        String content=null;
        try{
            content=studentService.GetContentByDate(time);
        }catch(Exception e){
            assertEquals("Invalid time",e.getMessage());
            assertNull(content);
        }

        time="2021-02-11";
        try{
            content=studentService.GetContentByDate(time);
        }catch(Exception e){
            assertEquals("Time Not Exist",e.getMessage());
            assertNull(content);
        }
    }

    @Test
    void TestGetAllAwardsContent(){
        ArrayList<String> contents=studentService.GetAllAwardContents();
        assertEquals(2,contents.size());
        System.out.println(contents);
    }


    @Test
    public void testSaveNewImage() throws Exception {
        //File newfile=new File("/Users/coisini/Desktop/test.jpg");
        //File newfile=new File("/Users/coisini/Desktop/test2.png");
        File newfile=new File("/Users/coisini/Desktop/Y3-introduction-2023.pdf");
        assertFalse(studentService.SaveNewImage(newfile));
    }

    @Test
    public void testUpdateStudentImage(){
        // File newfile=new File("/Users/coisini/Desktop/test.jpg");
        // //File newfile=new File("/Users/coisini/Desktop/test2.png");
        // //File newfile=new File("/Users/coisini/Desktop/Y3-introduction-2023.pdf");
        // try{
        //     Image newimage=studentService.updateStudentImage(newfile);
        //     assertNotNull(newimage);
        //     //assertNull(newimage);
        // }catch(Exception e){
        //     e.printStackTrace();
        // }
    }

    @Test
    public void testExportCV() throws Exception{
        String directory="/Users/coisini/Desktop";
        File CV=studentService.ExportCV(directory);
        assertNotNull(CV);
    }
}
