package by.a1qa.Utilities;

import by.a1qa.Constants.Parameters;
import by.a1qa.Constants.Requests;
import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Cookie;
import java.util.*;


public class RequestUtils {

    private static final Logger log = LogManager.getLogger(RequestUtils.class);


    public static <T> ArrayList<T> getModels(String json, Class<T[]> tClass) {
        return new ArrayList<>(Arrays.asList(new Gson().fromJson(json,tClass)));
    }

    public static String createRequest(Map<String,Object> parameters, String method) {
        String request = Requests.API_URL.concat(method);
        try {
            HttpResponse<String> response = Unirest.post(request).queryString(parameters).
            header("accept", "json/xml").asString();
            log.info("Response - " + response.getStatusText());
            log.info("Header: " + response.getHeaders());
            return response.getBody();
        } catch (UnirestException e) {
            log.error(e.getMessage());
            return null;
        }
    }

    public static void attachContent(Map<String,Object> parameters, String method, String img) {
        String request = Requests.API_URL.concat(method);
        try {
            HttpResponse<String> response = Unirest.post(request).queryString(parameters).field(Parameters.CONTENT,
                    img).asString();
            log.info("Response - " + response.getStatusText());
        } catch (UnirestException e) {
            log.error(e.getMessage());
        }
    }

    public static Cookie getCookies(String name, String value) {
        return new Cookie(name, value);
    }
}


