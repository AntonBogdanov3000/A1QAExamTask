package by.a1qa.PageObjects;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.IElement;
import aquality.selenium.elements.interfaces.IElementFactory;
import aquality.selenium.forms.Form;
import by.a1qa.Constants.TaskData;
import by.a1qa.Models.TestModel;
import org.openqa.selenium.By;
import java.util.LinkedList;
import java.util.List;


public class TestsPage extends Form {

    public TestsPage(String label) {
        super(By.xpath(String.format("//li[text()='%s']", label)), "Tests page");
    }

    private final static String TEST_NAME = "Test name";
    private final static String METHOD_NAME = "Test method";
    private final static String START_TIME = "Latest test start time";
    private final static String END_TIME = "Latest test end time";
    private final static String DURATION = "Latest test duration (H:m:s.S)";
    private final static IElementFactory elementFactory = AqualityServices.getElementFactory();
    private final String testOnPage = "//table[@id='allTests']/tbody/tr[%d]/td[%d]";
    private final String nameOfTheColumn = "//table[@id='allTests']/tbody/tr[1]/th[%d]";
    private IElement table;
    private IElement column;
    private static List<TestModel> modelsFromWeb;
    private IElement test = elementFactory.getLabel(By.xpath("//*[@id='allTests']/tbody/tr[2]/td[1]/a"),
             "Name of the created test");

    public static List<TestModel> getModelsFromWeb(){
        return modelsFromWeb;
    }

    public String getTestName(){
        test.state().waitForClickable();
        return test.getText();
    }

    public List<TestModel> getTestsFromWebPage(){
        modelsFromWeb = new LinkedList<>();
        TestModel testModel;

        for (int i = 2; i < TaskData.COUNT_TESTS_ON_PAGE; i++){
            testModel = new TestModel();
            for(int j = 1; j < TaskData.COUNT_ELEMENTS_IN_ROW; j++){
                column = elementFactory.getLabel(By.xpath(String.format(nameOfTheColumn,j)),"Column");
                table = elementFactory.getLabel(By.xpath(String.format(testOnPage,i,j)),
                        "Table of tests");

                switch (column.getText()){
                    case TEST_NAME : testModel.setName(table.getText());
                    case METHOD_NAME : testModel.setMethod(table.getText());
                    case START_TIME : testModel.setStartTime(table.getText());
                    case END_TIME : testModel.setEndTime(table.getText());
                    case DURATION : testModel.setDuration(table.getText());
                }
            }
            modelsFromWeb.add(testModel);
        }
        return modelsFromWeb;
        }
    }

