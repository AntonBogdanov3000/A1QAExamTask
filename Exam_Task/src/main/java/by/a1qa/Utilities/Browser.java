package by.a1qa.Utilities;

import org.openqa.selenium.WebDriver;
import java.util.Set;


public class Browser {

    public static void switchDriverToTab(WebDriver webDriver){
        String currentWindow = webDriver.getWindowHandle();
        String currentWindow2 = null;
        Set<String> currentWindows = webDriver.getWindowHandles();

        for(String window : currentWindows){
            if(!window.equals(currentWindow)){
                currentWindow2 = window;
                break;
            }
        }
        webDriver.switchTo().window(currentWindow2);
    }
}
