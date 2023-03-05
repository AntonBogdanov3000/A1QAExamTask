package by.a1qa.PageObjects;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.IElement;
import aquality.selenium.elements.interfaces.IElementFactory;
import aquality.selenium.forms.Form;
import by.a1qa.Utilities.StringUtils;
import org.openqa.selenium.By;


public class ProjectsPage extends Form {

    public ProjectsPage() {
        super(By.xpath("//a[contains(@href,'projects')]"), "Projects page");
    }


    private final IElementFactory elementFactory = AqualityServices.getElementFactory();
    private final IElement footerText = elementFactory.getLabel(By.xpath("//p[contains(@class,'footer-text')]//span"),
            "Footer text");
    private String projectButton = "//a[text()='%s']";
    private static String projectId;
    private final IButton addButton = elementFactory.getButton(By.xpath("//a[contains(@class,'btn-primary')]"),
            "Add button");


    public void clickProjectButton(String button){
        IButton buttonToClick = elementFactory.getButton(By.xpath(String.format(projectButton,button)), String.format("Button %s",button));
        String textFromAttribute = buttonToClick.getAttribute("href");
        projectId = textFromAttribute.substring(StringUtils.getProjectId(textFromAttribute));
        buttonToClick.click();
    }

    public void clickAddButton(){
        addButton.click();
    }

    public String getFooterText(){
        return footerText.getText();
    }
    public static String getProjectId(){
        return projectId;
    }

}
