package dao;

import com.alibaba.fastjson2.JSONObject;

import model.Student;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

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

        try (FileWriter fileWriter = new FileWriter(storeDirectory, true)) {
            // Write the JSON string to the file
            fileWriter.write(student + "\n");
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
}
