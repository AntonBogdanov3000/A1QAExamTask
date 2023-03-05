package by.a1qa.Utilities;

import by.a1qa.Constants.Parameters;
import by.a1qa.Constants.Requests;
import by.a1qa.Constants.TaskData;
import by.a1qa.Models.TestModel;
import by.a1qa.PageObjects.ProjectsPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;


public class ApiUtils {

    private static final Logger log = LogManager.getLogger(ApiUtils.class);

    private static Map<String,Object> parameters;


    public static String getTokenFromApi(){
        parameters = new LinkedHashMap<>();
        parameters.put(Parameters.VARIANT, TaskData.NUMBER);
        return RequestUtils.createRequest(parameters, Requests.TOKEN_GET);
    }

    public static String authorisation(){
        String login = FileReader.getTestData("login", "userData.json");
        String password = FileReader.getTestData("password", "userData.json");
        return String.format(Requests.AUTHORISATION, login, password);
    }

    public static List<TestModel> getModelsFromApi(){
        parameters = new LinkedHashMap<>();
        ArrayList<TestModel> modelsFromApi;
        parameters.put(Parameters.PROJECT_ID, ProjectsPage.getProjectId());
        modelsFromApi = RequestUtils.getModels(RequestUtils.createRequest(parameters, Requests.TEST_GET_JSON), TestModel[].class);
        log.info("Count of tests: " + modelsFromApi);
        return modelsFromApi;
    }

    public static void createTest(){
        try {
            parameters = new LinkedHashMap<>();
            parameters.put(Parameters.SID, StringUtils.getRandomText());
            parameters.put(Parameters.PROJECT_NAME, TaskData.CREATED);
            parameters.put(Parameters.TEST_NAME, TestModel.testName);
            parameters.put(Parameters.METHOD_NAME, TestModel.methodName);
            parameters.put(Parameters.ENV, InetAddress.getLocalHost().getHostName());
            TestModel.testId = RequestUtils.createRequest(parameters, Requests.TEST_PUT);
        }catch (UnknownHostException e){
            log.error(e.getMessage());
        }
    }

    public static void attachScreenshot(String testId, String img){
        parameters = new LinkedHashMap<>();
        parameters.put(Parameters.TEST_ID, testId);
        parameters.put(Parameters.CONTENT_TYPE, TaskData.IMAGE_PNG);
        RequestUtils.attachContent(parameters, Requests.TEST_PUT + Requests.ATTACHMENT, img);
    }

    public static void attachLogs(String testId, String logs){
        parameters = new LinkedHashMap<>();
        parameters.put(Parameters.TEST_ID, testId);
        parameters.put(Parameters.CONTENT, logs);
        RequestUtils.createRequest(parameters, Requests.TEST_PUT + Requests.LOG);
    }
}
