package by.a1qa.PageObjects;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.IElement;
import aquality.selenium.elements.interfaces.IElementFactory;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import by.a1qa.Utilities.FileReader;
import org.openqa.selenium.By;


public class AddProjectPage extends Form {

    public AddProjectPage() {
        super(By.xpath("//label[@for='projectName']"), "Add new project page");
    }

    private final IElementFactory elementFactory = AqualityServices.getElementFactory();
    private final ITextBox addProjectText = elementFactory.getTextBox(By.xpath("//input[@id='projectName']"),
            "Enter project name");
    private final IButton saveButton = elementFactory.getButton(By.xpath("//button[@type='submit']"),
            "Save project");
    private final IElement successAlert = elementFactory.getLabel(By.xpath("//div[contains(@class,'alert-success')]"),
            "Success message");

    public void enterProjectName(){
        addProjectText.sendKeys(FileReader.getTestData("project", "userData.json"));
    }

    public void clickSaveButton(){
        saveButton.click();
    }

    public boolean successMessageIsAppear(){
        successAlert.state().waitForDisplayed();
        return successAlert.state().isDisplayed();
    }
}
