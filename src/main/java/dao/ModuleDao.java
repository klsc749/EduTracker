package dao;

import com.alibaba.fastjson2.JSONObject;
import model.Activity;
import model.Mark;
import model.MarkItem;
import model.Module;

import java.io.*;
import java.util.List;

public class ModuleDao extends DAO {
    private final String storeDirectory = "src/main/resources/data/module.txt";

    /**
     * Save the given module to the store.
     *
     * @param module  The module to save
     */
    public void save(Module module) {
        // Convert the module to a JSON string
        String moduleJSON = JSONObject.toJSONString(module);

        try (FileWriter fileWriter = new FileWriter(storeDirectory, true)) {
            // Write the JSON string to the file
            fileWriter.write(moduleJSON + "\n");
            // Flush the buffer to force the data to be written
            fileWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Get a module by its ID.
     *
     * @param id The ID of the module
     * @return The Module with the given ID
     */
    public Module getModuleById(String id) {
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try {
            fileReader = new FileReader(storeDirectory);
            bufferedReader = new BufferedReader(fileReader);
            return findModule(id, bufferedReader);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            closeFileReader(fileReader);
            closeBufferedReader(bufferedReader);
        }
    }

    /**
     * Find an module by its ID.
     *
     * @param id The ID of the module
     * @param bufferedReader The buffered reader to read the file
     * @return The module with the given ID
     */
    private Module findModule(String id, BufferedReader bufferedReader) throws Exception {
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            Module module = parseLine(line, Module.class);
            if (isModule(module, id)) {
                return module;
            }
        }
        return null;
    }

    /**
     * Check if the given module has the given ID.
     *
     * @param module The module to check
     * @param id The ID to check
     * @return True if the module has the given ID, false otherwise
     */
    private boolean isModule(Module module, String id) {
        return module != null && module.getId() != null && module.getId().equals(id);
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

    /**
     * delete a module by giving its id
     *
     * @param id The module to delete
     */
    public void deleteModuleById(String id){
        File file = new File(storeDirectory);

        FileReader fileReader = null;
        BufferedReader bufferedReader = null;

        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;

        try {
            fileReader = new FileReader(storeDirectory);
            bufferedReader = new BufferedReader(fileReader);

            // Create a temporary file for writing
            File tempFile = new File(file.getAbsolutePath() + ".tmp");

            fileWriter = new FileWriter(tempFile);
            bufferedWriter = new BufferedWriter(fileWriter);

            String currentLine;
            while ((currentLine = bufferedReader.readLine()) != null) {
                Module module = parseLine(currentLine, Module.class);
                if (!isModule(module, id)) {
                    bufferedWriter.write(currentLine + System.getProperty("line.separator"));
                }
            }

            // Close both files
            bufferedReader.close();
            bufferedWriter.close();

            // Delete the original file
            if (!file.delete()) {
                System.out.println("Failed to delete the original file.");
                return;
            }

            // Rename the temporary file to the original file name
            if (!tempFile.renameTo(file)) {
                System.out.println("Failed to rename the temporary file.");
            }

            System.out.println("The line has been deleted from the file.");

        } catch (IOException e) {
            System.out.println("An error occurred while deleting the line from the file.");
            e.printStackTrace();
        }
    }

    public void updateModuleById(Module moduleUpdated){
        // The file to modify
        File file = new File(storeDirectory);

        // The line to delete
        String lineToDelete = null;

        FileReader fileReader = null;
        BufferedReader bufferedReader = null;

        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;

        try {
            // Open the original file for reading
            fileReader = new FileReader(storeDirectory);
            bufferedReader = new BufferedReader(fileReader);

            // Create a temporary file for writing
            File tempFile = new File(file.getAbsolutePath() + ".tmp");

            fileWriter = new FileWriter(tempFile);
            bufferedWriter = new BufferedWriter(fileWriter);

            // Copy the lines from the original file to the temporary file
            String currentLine;
            while ((currentLine = bufferedReader.readLine()) != null) {
                Module module = parseLine(currentLine, Module.class);
                if (!isModule(module, moduleUpdated.getId())) {
                    bufferedWriter.write(currentLine + System.getProperty("line.separator"));
                }else{
                    bufferedWriter.write(JSONObject.toJSONString(moduleUpdated) + System.getProperty("line.separator"));
                }
            }

            // Close both files
            bufferedReader.close();
            bufferedWriter.close();

            // Delete the original file
            if (!file.delete()) {
                System.out.println("Failed to delete the original file.");
                return;
            }

            // Rename the temporary file to the original file name
            if (!tempFile.renameTo(file)) {
                System.out.println("Failed to rename the temporary file.");
            }

            System.out.println("The line has been deleted from the file.");

        } catch (IOException e) {
            System.out.println("An error occurred while deleting the line from the file.");
            e.printStackTrace();
        }
    }

    public void addMarkItem(String id, MarkItem markItem){
        Module module = getModuleById(id);
        Mark mark = module.getMark();
        mark.getMarkItems().add(markItem);

        updateModuleById(module);
    }

    public void updateMarkItem(String module_id,String markItem_name, MarkItem markItemUpdated){
        Module module = getModuleById(module_id);
        List<MarkItem> markItems = module.getMark().getMarkItems();

        for (MarkItem markItem : markItems) {
            if(markItem.getName() == markItem_name){
                int index = markItems.indexOf(markItem);
                markItems.set(index, markItemUpdated);
            }
        }
    }
}
