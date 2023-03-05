import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import by.a1qa.Utilities.ApiUtils;
import org.testng.annotations.AfterTest;


public class BaseTest {

    public Browser browser = AqualityServices.getBrowser();

    public void browserStart(){
        browser.maximize();
        browser.goTo(ApiUtils.authorisation());
        browser.waitForPageToLoad();
    }

    @AfterTest
    public void close(){
        browser.quit();
    }
}
