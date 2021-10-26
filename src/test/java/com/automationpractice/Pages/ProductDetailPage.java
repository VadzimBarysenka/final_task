package com.automationpractice.Pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductDetailPage extends BasicPage {

    @FindBy(name = "Submit")
    private WebElement addToCart;

    @FindBy(id = "wishlist_button")
    private WebElement addToWishList;

    @FindBy(id = "our_price_display")
    private WebElement itemPrice;

    @FindBy(xpath = "//span[@title='Continue shopping']")
    private WebElement continueShoppingButton;

    @FindBy(xpath = "//a[@class='fancybox-item fancybox-close']")
    private WebElement closePopUpButton;

    @Step("Add product to cart and continue")
    public ProductDetailPage addItemToCartAndContinue() {
        addToCart.click();
        continueShoppingButton.click();
        return new ProductDetailPage();
    }

    @Step("Add product to wishlist")
    public ProductDetailPage addItemToWishList() {
        addToWishList.click();
        closePopUpButton.click();
        return this;
    }
}
