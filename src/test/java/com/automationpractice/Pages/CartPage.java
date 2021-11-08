package com.automationpractice.Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartPage extends BasicPage {

  @FindBy(id = "summary_products_quantity")
  private WebElement sumProductQnt;

  @FindBy(xpath = "//i[@class='icon-trash']")
  private WebElement deleteItemFromCart;

  @FindBy(id = "total_price_without_tax")
  private WebElement totalPriceWithoutTax;

  @FindBy(xpath = "//p[@class='alert alert-warning']")
  private WebElement alert;

  public int getSumProductQnt() {
    return Integer.parseInt(String.valueOf(sumProductQnt.getText().charAt(0)));
  }

  public double getTotalWithoutTax() {
    return Double.parseDouble(totalPriceWithoutTax.getText().replace("$", ""));
  }

  public BasicPage cleanCart() {
    while (!alert.isDisplayed()) {
      deleteItemFromCart.click();
    }
    return new BasicPage();
  }
}
