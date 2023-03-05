import by.a1qa.Constants.TaskData;
import by.a1qa.Models.TestModel;
import by.a1qa.PageObjects.AddProjectPage;
import by.a1qa.PageObjects.ProjectsPage;
import by.a1qa.PageObjects.TestsPage;
import by.a1qa.Utilities.ApiUtils;
import by.a1qa.Utilities.Browser;
import by.a1qa.Utilities.SortUtils;
import by.a1qa.Utilities.RequestUtils;
import org.openqa.selenium.OutputType;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import java.time.Duration;


@Listeners(Listener.class)

public class UiApiTest extends BaseTest{

    @Test
    public void test() {
        ProjectsPage projectsPage = new ProjectsPage();
        TestsPage testsPage;
        AddProjectPage addProjectPage = new AddProjectPage();
        String token = ApiUtils.getTokenFromApi();

        Assert.assertFalse(token.isEmpty(),"Token is not generate");

        browserStart();
        browser.getDriver().manage().addCookie(RequestUtils.getCookies("token", token));
        browser.getDriver().navigate().refresh();

        Assert.assertTrue(projectsPage.getFooterText().contains(TaskData.NUMBER), "Not true variant");
        projectsPage.clickProjectButton(TaskData.NEXAGE);

        testsPage = new TestsPage(TaskData.NEXAGE);
        testsPage.state().waitForDisplayed(Duration.ofSeconds(TaskData.WAIT));

        Assert.assertTrue(testsPage.state().isDisplayed(), "Nexage tests page is not open");

        Assert.assertTrue(SortUtils.isSortedByDate(testsPage.getTestsFromWebPage()),
                "Tests are not sorted by date");

        Assert.assertFalse(ApiUtils.getModelsFromApi().contains(TestsPage.getModelsFromWeb()),
                "Not equals");

        browser.getDriver().navigate().back();
        projectsPage.clickAddButton();

        String currentWindow = browser.getDriver().getWindowHandle();
        Browser.switchDriverToTab(browser.getDriver());

        addProjectPage.enterProjectName();
        addProjectPage.clickSaveButton();
        Assert.assertTrue(addProjectPage.successMessageIsAppear(), "Success message is not appear");

        browser.getDriver().close();
        browser.getDriver().switchTo().window(currentWindow);
        browser.getDriver().navigate().refresh();

        projectsPage.clickProjectButton(TaskData.CREATED);

        ApiUtils.createTest();
        ApiUtils.attachScreenshot(TestModel.testId, browser.getDriver().getScreenshotAs(OutputType.BASE64));

        Assert.assertEquals(testsPage.getTestName(), TestModel.testName,
                "Test is not appear");
    }
}
