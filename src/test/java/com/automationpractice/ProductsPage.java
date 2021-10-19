package com.automationpractice;


import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductsPage extends BasicPage {
    private final WebDriver driver;
    private Actions action;
    private static double CART_SUM;

    public ProductsPage() {
        PageFactory.initElements(DRIVER, this);
        this.driver = DRIVER;
    }

    @FindBy(xpath = "//a[@title=\"View\"]")
    private WebElement viewProductDetail;

    @FindBy(xpath = "//a[@class=\"fancybox-item fancybox-close\"]")
    private WebElement closePopUpButton;

    @FindBy(xpath = "//span[@title=\"Continue shopping\"]")
    private WebElement continueShoppingButton;

    public static double getCartSum() {
        return CART_SUM + 2.00;
    }

    @Step("Add product to wishlist")
    public ProductsPage addProductToWishList(int itemNumber) {
        WebElement element = driver.findElement(By.xpath("//div/ul/li[" + itemNumber + "]"));
        action = new Actions(driver);
        scroll(driver, element);
        action.moveToElement(element).perform();
        WebElement addToWishListButton = driver
                .findElement(By.xpath("//div/ul/li[" + itemNumber + "]//div[@class=\"wishlist\"]"));
        action.moveToElement(addToWishListButton).perform();
        addToWishListButton.click();
        closePopUpButton.click();
        return this;
    }

    @Step("Add product to cart and continue")
    public ProductsPage addProductToCartAndContinue(int itemNumber) {
        WebElement element = driver.findElement(By.xpath("//div[@id=\"center_column\"]/ul/li[" + itemNumber + "]"));
        action = new Actions(driver);
        scroll(driver, element); // add for FireFox
        action.moveToElement(element).perform();
        WebElement addToCardButton = driver
                .findElement(By.xpath("//div[@id=\"center_column\"]/ul/li[" + itemNumber + "]//a[@title=\"Add to cart\"]"));
        addToCardButton.click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("layer_cart_product_price")));
        WebElement price = driver.findElement(By.id("layer_cart_product_price"));
        CART_SUM = CART_SUM + Double.parseDouble((price.getText().replace("$", "")));
        continueShoppingButton.click();
        return this;
    }

    @Step("Open product detail page")
    public ProductDetailPage openProductDetailPage(int itemNumber) {
        WebElement element = driver.findElement(By.xpath("//div[@id=\"center_column\"]/ul/li[" + itemNumber + "]"));
        action = new Actions(driver);
        scroll(driver, element);
        action.moveToElement(element).perform();
        WebElement moreButton = driver.findElement(By.xpath("//div/ul/li[" + itemNumber + "]//a[@title=\"View\"]"));
        moreButton.click();
        return new ProductDetailPage();
    }
}
