package by.a1qa.Utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.IOException;

public class FileReader {

    private static final Logger log = LogManager.getLogger(FileReader.class);

    public static String getTestData(String key, String fileName) {
        String data = null;
        try {
            JSONParser parser = new JSONParser();
            JSONObject returnData = (JSONObject) parser.parse(new java.io.FileReader("src/main/resources/" + fileName));
            data = (String) returnData.get(key);
        } catch (IOException | ParseException e) {
            log.error(e.getMessage());
        }
        return data;
    }
}
