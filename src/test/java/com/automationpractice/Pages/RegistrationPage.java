package com.automationpractice.Pages;

import com.automationpractice.Users.RegistrationUser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class RegistrationPage extends BasicPage {

    @FindBy(id = "customer_firstname")
    private WebElement customerFirstNameField;

    @FindBy(id = "customer_lastname")
    private WebElement customerLastNameField;

    @FindBy(id = "email")
    private WebElement emailField;

    @FindBy(id = "passwd")
    private WebElement passwdField;

    @FindBy(id = "address1")
    private WebElement addressField;

    @FindBy(id = "city")
    private WebElement cityField;

    @FindBy(id = "postcode")
    private WebElement postcodeField;

    @FindBy(id = "phone_mobile")
    private WebElement phoneMobileField;

    @FindBy(id = "submitAccount")
    private WebElement submitAccountButton;

    @FindBy(id = "uniform-id_state")
    private WebElement pickState;

    public RegistrationPage() {
        PageFactory.initElements(driver, this);
    }

    public SignInPage register(RegistrationUser registrationUser) {
        customerFirstNameField.sendKeys(registrationUser.getFirstName());
        customerLastNameField.sendKeys(registrationUser.getLastName());
        passwdField.sendKeys(registrationUser.getPassword());
        emailField.clear();
        emailField.sendKeys(registrationUser.getEmail());
        addressField.sendKeys(registrationUser.getAddress());
        cityField.sendKeys(registrationUser.getCity());
        postcodeField.sendKeys(registrationUser.getPostcode());
        phoneMobileField.sendKeys(registrationUser.getPhoneMobile());
        pickState.click();
        Select objSelect = new Select(driver.findElement(By.xpath("//select[@id='id_state']")));
        objSelect.selectByVisibleText(registrationUser.getState());
        submitAccountButton.click();
        return new SignInPage();
    }
}
