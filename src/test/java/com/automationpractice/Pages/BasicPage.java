package com.automationpractice.Pages;

import com.automationpractice.Driver.WebDriverSingleton;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasicPage {
  protected final WebDriver driver;

  public enum Category {
    WOMEN,
    DRESS,
    T_SHIRTS
  }

  private final static By overlay = By.xpath("//div[contains(@class, 'fancybox-overlay')]");

  @FindBy(xpath = "//a[@class='account']")
  private WebElement openAccount;

  @FindBy(className = "logout")
  private WebElement logout;

  @FindBy(xpath = "//a[@title='View my shopping cart']")
  private WebElement shopCart;

  @FindBy(xpath = "//li/following-sibling::li/a[@title='T-shirts']")
  private WebElement shirtsBlock;

  @FindBy(xpath = "//div[@id='block_top_menu']/ul/li/a[@title='Dresses']")
  private WebElement dressBlock;

  @FindBy(xpath = "//a[@title='Women']")
  private WebElement womenBlock;

  public BasicPage() {
    this.driver = WebDriverSingleton.getInstance().getDriver();
    PageFactory.initElements(driver, this);
  }

  public String getCustomerName() {
    return openAccount.getText();
  }

  public int getNumbersOfItemsInCart() {
    return Integer.parseInt(shopCart.getText());
  }

  @Step("Open [Account] page")
  public AccountPage openAccountPage() {
    WebDriverWait wait = new WebDriverWait(driver, 10);
    wait.until(ExpectedConditions.invisibilityOfElementLocated(overlay));
    openAccount.click();
    return new AccountPage();
  }

  public SignInPage logout() {
    logout.click();
    return new SignInPage();
  }

  @Step("Open Cart")
  public CartPage openCart() {
    shopCart.click();
    return new CartPage();
  }

  @Step("Open category")
  public ProductsPage openCategory(Category getInCategory) {
    switch (getInCategory) {
      case WOMEN:
        womenBlock.click();
        break;
      case DRESS:
        dressBlock.click();
        break;
      case T_SHIRTS:
        shirtsBlock.click();
        break;
    }
    return new ProductsPage();
  }

  protected void scroll(WebDriver driver, WebElement element) {
    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
  }
}
