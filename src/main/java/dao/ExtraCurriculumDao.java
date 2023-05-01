package dao;

import com.alibaba.fastjson2.JSONObject;
import model.ExtraCurriculum;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExtraCurriculumDao extends DAO {
    private final String storeDirectory = "src/main/resources/data/activity.txt";


    /**
     * Save the given ExtraCurriculum to the store.
     *
     * @param extraCurriculum The ExtraCurriculum to save
     */
    public void save(ExtraCurriculum extraCurriculum) {
        // Convert the activity to a JSON string
        String activityJSON = JSONObject.toJSONString(extraCurriculum);

        try (FileWriter fileWriter = new FileWriter(storeDirectory, true)) {
            // Write the JSON string to the file
            fileWriter.write(activityJSON + "\n");
            // Flush the buffer to force the data to be written
            fileWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Get an ExtraCurriculum by its ID.
     *
     * @param id The ID of the ExtraCurriculum
     * @return The ExtraCurriculum with the given ID
     */
    public ExtraCurriculum getExtraCurriculumById(String id) {
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try {
            fileReader = new FileReader(storeDirectory);
            bufferedReader = new BufferedReader(fileReader);
            return findExtraCurriculum(id, bufferedReader);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            closeFileReader(fileReader);
            closeBufferedReader(bufferedReader);
        }
    }

    /**
     * Find an ExtraCurriculum by its ID.
     *
     * @param id The ID of the ExtraCurriculum
     * @param bufferedReader The buffered reader to read the file
     * @return The ExtraCurriculum with the given ID
     */
    private ExtraCurriculum findExtraCurriculum(String id, BufferedReader bufferedReader) throws Exception {
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            ExtraCurriculum extraCurriculum = parseLine(line, ExtraCurriculum.class);
            if (isExtraCurriculum(extraCurriculum, id)) {
                return extraCurriculum;
            }
        }
        return null;
    }

    /**
     * Check if the given ExtraCurriculum has the given ID.
     *
     * @param extraCurriculum The activity to check
     * @param id The ID to check
     * @return True if the ExtraCurriculum has the given ID, false otherwise
     */
    private boolean isExtraCurriculum(ExtraCurriculum extraCurriculum, String id) {
        return extraCurriculum != null && extraCurriculum.getId() != null && extraCurriculum.getId().equals(id);
    }

    /**
     * Parse a line from the store into an Activity object and add it to the given list.
     *
     * @param list The list to add the Activity object to
     * @param line The line to parse
     */
    private void parseLineAndAddToList(List<ExtraCurriculum> list, String line) {
        ExtraCurriculum extraCurriculum = parseLine(line, ExtraCurriculum.class);
        list.add(extraCurriculum);
    }

    public List<ExtraCurriculum> getAllExtraCurriculums() {
        List<ExtraCurriculum> list = new ArrayList<>();
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try {
            fileReader = new FileReader(storeDirectory);
            bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                parseLineAndAddToList(list, line);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            closeFileReader(fileReader);
            closeBufferedReader(bufferedReader);
        }
        return list;
    }
}