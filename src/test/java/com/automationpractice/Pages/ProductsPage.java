package com.automationpractice.Pages;


import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductsPage extends BasicPage {
  private Actions action;
  private static final double SHIPPING_PRICE = 2.00;
  private double cartSum;
  private final static By productPrice = By.id("layer_cart_product_price");
  private final static String productToAdd = "//div[@id='center_column']/ul/li[%s]";
  private final static String addToCart = "//div[@id='center_column']/ul/li[%s]//a[@title='Add to cart']";
  private final static String addToWishList = "//div/ul/li[%s]//div[@class='wishlist']";
  private final static String openProductPage = "//div/ul/li[%s]//a[@title='View']";

  @FindBy(xpath = "//a[@title='View']")
  private WebElement viewProductDetail;

  @FindBy(xpath = "//a[@class='fancybox-item fancybox-close']")
  private WebElement closePopUpButton;

  @FindBy(xpath = "//span[@title='Continue shopping']")
  private WebElement continueShoppingButton;

  public ProductsPage() {
    PageFactory.initElements(driver, this);
  }

  public double getCartSum() {
    return cartSum + SHIPPING_PRICE;
  }

  @Step("Add product#{product} to wishlist")
  public ProductsPage addProductToWishList(int product) {
    WebElement item = driver.findElement(By.xpath(String.format(productToAdd, product)));
    action = new Actions(driver);
    scroll(driver, item);
    action.moveToElement(item).perform();
    WebElement addToWishListButton = driver.findElement(By.xpath(String.format(addToWishList, product)));
    action.moveToElement(addToWishListButton).perform();
    addToWishListButton.click();
    closePopUpButton.click();
    return this;
  }

  @Step("Add product#{product} to cart and continue")
  public ProductsPage addProductToCartAndContinue(int product) {
    WebElement element = driver.findElement(By.xpath(String.format(productToAdd, product)));
    action = new Actions(driver);
    scroll(driver, element); // add for FireFox
    action.moveToElement(element).perform();
    WebElement addToCardButton = driver.findElement(By.xpath(String.format(addToCart, product)));
    addToCardButton.click();
    WebDriverWait wait = new WebDriverWait(driver, 10);
    wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(productPrice));
    WebElement price = driver.findElement(productPrice);
    cartSum = cartSum + Double.parseDouble((price.getText().replace("$", "")));
    continueShoppingButton.click();
    return this;
  }

  @Step("Open product#{product} detail page")
  public ProductDetailPage openProductDetailPage(int product) {
    WebElement element = driver.findElement(By.xpath(String.format(productToAdd, product)));
    action = new Actions(driver);
    scroll(driver, element);
    action.moveToElement(element).perform();
    WebElement moreButton = driver.findElement(By.xpath(String.format(openProductPage, product)));
    moreButton.click();
    return new ProductDetailPage();
  }
}
