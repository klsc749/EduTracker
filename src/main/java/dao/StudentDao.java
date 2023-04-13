package dao;

import com.alibaba.fastjson2.JSONObject;

import model.Student;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StudentDao extends DAO{
    private final String storeDirectory = "src/main/resources/data/student.txt";

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
        else if(!(email.charAt(email.length()-4)=='.' && email.charAt(email.length()-3)=='c'
                && email.charAt(email.length()-2)=='o' && email.charAt(email.length()-1)=='m'))
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
}
