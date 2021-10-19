package com.automationpractice;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegistrationPage extends BasicPage {
    private final WebDriver driver;

    public RegistrationPage() {
        PageFactory.initElements(DRIVER, this);
        this.driver = DRIVER;
    }

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

    @Step("Fill firstname field")
    public RegistrationPage fillCustomerFirstName(String firstName) {
        customerFirstNameField.sendKeys(firstName);
        return this;
    }

    @Step("Fill lastname field")
    public RegistrationPage fillCustomerLastName(String lastName) {
        customerLastNameField.sendKeys(lastName);
        return this;
    }

    @Step("Fill password field")
    public RegistrationPage fillCustomerPassField(String password) {
        passwdField.sendKeys(password);
        return this;
    }

    @Step("Fill email field")
    public RegistrationPage fillCustomerEmail(String email) {
        emailField.clear();
        emailField.sendKeys(email);
        return this;
    }

    @Step("Fill address field")
    public RegistrationPage fillCustomerAddress(String address) {
        addressField.sendKeys(address);
        return this;
    }

    @Step("Fill city field")
    public RegistrationPage fillCustomerCity(String city) {
        cityField.sendKeys(city);
        return this;
    }

    @Step("Fill postcode field")
    public RegistrationPage fillCustomerPostCode(String postCode) {
        postcodeField.sendKeys(postCode);
        return this;
    }

    @Step("Fill mobile phone field")
    public RegistrationPage fillCustomerMobilePhone(String phone) {
        phoneMobileField.sendKeys(phone);
        return this;
    }

    @Step("Choose state")
    public RegistrationPage choseState(int i) {
        pickState.click();
        WebElement state = driver.findElement(By.xpath("//select[@id=\"id_state\"]//option[" + i + "]"));
        state.click();
        return this;
    }

    @Step("Register account")
    public SignInPage submitAccount() {
        submitAccountButton.click();
        return new SignInPage();
    }
}
