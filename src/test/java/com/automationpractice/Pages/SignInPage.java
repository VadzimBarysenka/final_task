package com.automationpractice.Pages;

import com.automationpractice.TestListener.PropertiesReader;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignInPage extends BasicPage {
  private final static String URL = PropertiesReader.get("app.url");

  @FindBy(id = "email")
  private WebElement emailField;

  @FindBy(id = "passwd")
  private WebElement passwordField;

  @FindBy(id = "SubmitLogin")
  private WebElement submitLoginButton;

  @FindBy(id = "email_create")
  private WebElement createEmailField;

  @FindBy(id = "SubmitCreate")
  private WebElement submitCreateButton;

  @Step("Open sign in page")
  public SignInPage openSignInPage() {
    driver.get(URL);
    return this;
  }

  @Step("Fill in email with {mail} and password")
  public SignInPage fillEmailAndPassword(String mail, String pass) {
    emailField.sendKeys(mail);
    passwordField.sendKeys(pass);
    return this;
  }

  @Step("Login to the app")
  public AccountPage logInToApp() {
    submitLoginButton.click();
    return new AccountPage();
  }

  @Step("Fill registration email {email} field")
  public SignInPage fillEmailAddress(String email) {
    createEmailField.sendKeys(email);
    return this;
  }

  @Step("Open registration page")
  public RegistrationPage openRegistrationPage() {
    submitCreateButton.click();
    return new RegistrationPage();
  }
}
