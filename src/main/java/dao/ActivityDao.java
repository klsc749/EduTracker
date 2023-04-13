package dao;

import com.alibaba.fastjson2.JSONObject;
import model.Activity;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ActivityDao extends DAO {
    private final String storeDirectory = "src/main/resources/data/activity.txt";


    /**
     * Save the given activity to the store.
     *
     * @param activity The activity to save
     */
    public void save(Activity activity) {
        // Convert the activity to a JSON string
        String activityJSON = JSONObject.toJSONString(activity);

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
     * Get an activity by its ID.
     *
     * @param id The ID of the activity
     * @return The activity with the given ID
     */
    public Activity getActivityById(String id) {
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try {
            fileReader = new FileReader(storeDirectory);
            bufferedReader = new BufferedReader(fileReader);
            return findActivity(id, bufferedReader);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            closeFileReader(fileReader);
            closeBufferedReader(bufferedReader);
        }
    }

    /**
     * Get all activities from the store.
     *
     * @return A list of activities
     */
    public List<Activity> getAllActivities() {
        List<Activity> list = new ArrayList<>();
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

    /**
     * Find an activity by its ID.
     *
     * @param id The ID of the activity
     * @param bufferedReader The buffered reader to read the file
     * @return The activity with the given ID
     */
    private Activity findActivity(String id, BufferedReader bufferedReader) throws Exception {
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            Activity activity = parseLine(line, Activity.class);
            if (isActivity(activity, id)) {
                return activity;
            }
        }
        return null;
    }

    /**
     * Check if the given activity has the given ID.
     *
     * @param activity The activity to check
     * @param id The ID to check
     * @return True if the activity has the given ID, false otherwise
     */
    private boolean isActivity(Activity activity, String id) {
        return activity != null && activity.getId() != null && activity.getId().equals(id);
    }

    /**
     * Parse a line from the store into an Activity object and add it to the given list.
     *
     * @param list The list to add the Activity object to
     * @param line The line to parse
     */
    private void parseLineAndAddToList(List<Activity> list, String line) {
        Activity activity = parseLine(line, Activity.class);
        list.add(activity);        
    }

}
