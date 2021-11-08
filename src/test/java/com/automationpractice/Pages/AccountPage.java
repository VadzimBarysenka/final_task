package com.automationpractice.Pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountPage extends BasicPage {

  @FindBy(xpath = "//a[@title='My wishlists']")
  private WebElement wishList;

  @Step("Open [My wishlists] page")
  public WishListPage openWishLists() {
    wishList.click();
    return new WishListPage();
  }
}
