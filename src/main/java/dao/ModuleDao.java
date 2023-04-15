package dao;

import com.alibaba.fastjson2.JSONObject;

import model.Module;

import java.io.FileWriter;
import java.io.IOException;

public class ModuleDao extends DAO {
    private final String storeDirectory = "src/main/resources/data/module.txt";

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




}
