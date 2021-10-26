package com.automationpractice.Pages;

import io.qameta.allure.Step;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;


public class WishListPage extends BasicPage {
    private final List<String> names = new ArrayList<>();
    private final List<Integer> qntOfItems = new ArrayList<>();
    private final By tableRows = By.xpath("//tbody/tr");

    @FindBy(id = "name")
    private WebElement wishListName;

    @FindBy(id = "submitWishlist")
    private WebElement saveButton;

    @FindBy(className = "icon-remove")
    private WebElement removeWishListButton;

    @FindBy(xpath = "//table")
    private WebElement wishTable;

    @FindBy(xpath = "//td[1]")
    private WebElement createdWishListName;

    @FindBy(xpath = "//td[2]")
    private WebElement addedItemsQnt;

    @Step("Create wishlist")
    public WishListPage createWishList(String name) {
        wishListName.sendKeys(name);
        saveButton.click();
        return this;
    }

    public boolean verifyAbsentOfWishLists() {  // add adequate check
        try {
            wishTable.isDisplayed();
            return false;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return true;
        }
    }

    public WishListPage removeWishLists() {
        List<WebElement> allRows = driver.findElements(tableRows);
        for (WebElement element : allRows) {
            removeWishListButton.click();
            Alert alert = driver.switchTo().alert();
            alert.accept();
        }
        return this;
    }

    public List<String> getWishListNames() {
        List<WebElement> allRows = driver.findElements(tableRows);
        allRows.forEach(element -> names.add(createdWishListName.getText()));
        return names;
    }

    public String getWishListName() {
        return createdWishListName.getText();
    }

    public List<Integer> getWishListsItemsQnt() {
        List<WebElement> allRows = driver.findElements(tableRows);
        allRows.forEach(element -> names.add(addedItemsQnt.getText()));
        return qntOfItems;
    }

    public int getWishListItemQnt() {
        return Integer.parseInt(addedItemsQnt.getText());
    }
}
