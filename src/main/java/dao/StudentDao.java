package dao;

import com.alibaba.fastjson2.JSONObject;

import model.Student;
import javafx.scene.image.Image;

import java.awt.*;
import java.io.File;
import java.io.*;
import java.util.HashMap;

public class StudentDao extends DAO{
    private final String storeDirectory = "src/main/resources/data/student.txt";
    private final String photoDirectory = "src/main/resources/image/student/photo.jpg";
    private final String PSDirectory="src/main/resources/data/PS.txt";
    /**
     * Save the given student_information to the store.
     *
     * @param student The activity to save
     */
    public void save(Student student) {
        // Convert the activity to a JSON string
        String studentJSON = JSONObject.toJSONString(student);

        //overwrite student.txt, param append=false
        try (FileWriter fileWriter = new FileWriter(storeDirectory, false)) {
            // Write the JSON string to the file
            fileWriter.write(studentJSON + "\n");
            // Flush the buffer to force the data to be written
            fileWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Get Student information.
     *
     * @return The activity with the given ID
     */
    public Student getStudentInfo() {
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try {
            fileReader = new FileReader(storeDirectory);
            bufferedReader = new BufferedReader(fileReader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                Student student = parseLine(line, Student.class);
                return student;
            }
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
                closeFileReader(fileReader);
                closeBufferedReader(bufferedReader);
        }
    }

    /**
     * check whether the input email is legal
     * @param email input email
     * @return true, legal; false illegal
     */

    public boolean CheckEmailLegal(String email) throws Exception{
        boolean flag=true;

        if(email.indexOf("@")==-1 || email.indexOf(".")==-1)
            flag=false;
        else if(email.indexOf("@")>email.indexOf("."))
            flag=false;
        else{
            for(int i=0;i<email.length();i++){
                if(email.charAt(i)>='a'&&email.charAt(i)<='z'
                        ||email.charAt(i)>='A'&&email.charAt(i)<='Z'
                        ||email.charAt(i)>='0'&&email.charAt(i)<='9'
                        ||email.charAt(i)=='_'
                        ||email.charAt(i)=='@'
                        ||email.charAt(i)=='.'){
                    flag=true;
                }
                else{
                    flag=false;
                    break;
                }
            }
        }
        if(flag==false){
            throw new Exception("illegal email!");
        }
        return flag;
    }

    public Image loadImage() {
        Image image;
        File imageFile = new File(photoDirectory);
        if (imageFile.exists()) {
            image = new Image(imageFile.toURI().toString());
        } else {
            image = new Image("default_photo.jpg"); // 加载默认图像
        }
        return image;
    }

    /**
     * Modify content to the PS with the input in TextArea from GUI
     * @param newContent
     * @return
     */
    public String ModifyContentToPS(String newContent){
        FileWriter fileWriter=null;
        BufferedWriter bufferedWriter=null;
        try{
            fileWriter=new FileWriter(PSDirectory);
            bufferedWriter=new BufferedWriter(fileWriter);
            bufferedWriter.write(newContent);
            bufferedWriter.close();
            fileWriter.close();
            return newContent;
        }catch(IOException e){
            e.printStackTrace();
            return null;
        }
    }


    /**
     * Read from PS.txt
     * @return String content
     */

    public String loadPS(){
        String content="";
        FileReader fileReader=null;
        BufferedReader bufferedReader=null;

        try{
            fileReader=new FileReader(PSDirectory);
            bufferedReader=new BufferedReader(fileReader);
            String line="";
            while((line=bufferedReader.readLine())!=null){
                content+=line+"\n";
            }
            bufferedReader.close();
            fileReader.close();
        }catch(IOException e){
            e.printStackTrace();
        }

        return content;
    }



}
