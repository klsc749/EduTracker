package dao;


import com.alibaba.fastjson2.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public abstract class DAO {

    public <T> T parseLine(String line, Class<T> objectClass){
        try {
            return JSONObject.parseObject(line, objectClass);
        } catch (Exception e)
        {
            System.out.println("Error parsing line: " + line);
            return null;
        }
    }

    /**
     * Close the given file reader.
     *
     * @param fileReader The file reader to close
     */

    protected void closeFileReader(FileReader fileReader) {
        try {
            // If the file reader is not null
            if (fileReader != null) {
                // Close the file reader
                fileReader.close();
            }
        } catch (IOException e) {
            // If an exception occurs, throw a runtime exception
            throw new RuntimeException(e);
        }
    }

    /**
     * Close the given buffered reader.
     *
     * @param bufferedReader The buffered reader to close
     */
    protected void closeBufferedReader(BufferedReader bufferedReader) {
        // If the BufferedReader is not null, try to close it
        try {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
        } catch (IOException e) {
            // If there is an IOException, throw a RuntimeException
            throw new RuntimeException(e);
        }
    }
}
