package com.automationpractice;

import com.automationpractice.Driver.WebDriverSingleton;
import com.automationpractice.Pages.*;
import com.automationpractice.TestListener.PropertiesReader;
import com.automationpractice.TestListener.TestListener;
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import io.qameta.allure.TmsLink;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import static org.junit.jupiter.api.Assertions.*;

@Execution(ExecutionMode.CONCURRENT)
@DisplayName("Wishlist functionality")
@ExtendWith(TestListener.class)
public class WishListTest {
  private final static String USER_EMAIL = PropertiesReader.get("user.mail");
  private final static String USER_PASSWORD = PropertiesReader.get("user.password");
  private final static String AUTO_CREATED_LIST_NAME = "My wishlist";
  private final static String CREATED_LIST_NAME = RandomStringUtils.randomAlphabetic(8);
  private final static int PRODUCT_1 = 3;
  private final static int PRODUCT_2 = 5;
  private final static int LIST_ITEMS_QNT = 1;

  @AfterAll
  public static void cleanup() {
    WebDriverSingleton.getInstance().closeDriver();
  }

  @AfterEach
  public void cleanListAndLogOut() {
    WishListPage wishListPage = new WishListPage();
    wishListPage.removeWishLists();
    wishListPage.logout();
  }

  @Execution(ExecutionMode.SAME_THREAD)
  @Story("Add product to wishlist")
  @Description("Verify that user is able to add product to autocreated wishlist")
  @DisplayName("Verify add to autocreated wishlist")
  @TmsLink("ID-104")
  @Test
  public void addToAutoCreatedWishList() {
    SignInPage signInPage = new SignInPage();
    AccountPage accountPage = signInPage.openSignInPage().fillEmailAndPassword(USER_EMAIL, USER_PASSWORD).logInToApp();
    WishListPage wishListPage = accountPage.openWishLists();
    ProductsPage productsPage = wishListPage.openCategory(BasicPage.Category.DRESS);
    ProductDetailPage productDetailPage = productsPage.openProductDetailPage(PRODUCT_1).addItemToWishList();
    productDetailPage.openAccountPage();
    accountPage.openWishLists();

    assertAll(
        () -> assertEquals(AUTO_CREATED_LIST_NAME, wishListPage.getWishListName()),
        () -> assertEquals(LIST_ITEMS_QNT, wishListPage.getWishListItemQnt())
    );
  }

  @Execution(ExecutionMode.SAME_THREAD)
  @Story("Add product to wishlist")
  @Description("Verify that that there is no wishlists")
  @DisplayName("Verify that that there is no wishlists")
  @TmsLink("ID-105")
  @Test
  public void noWishListsTest() {
    SignInPage signInPage = new SignInPage();
    AccountPage accountPage = signInPage.openSignInPage().fillEmailAndPassword(USER_EMAIL, USER_PASSWORD).logInToApp();
    WishListPage wishListPage = accountPage.openWishLists();

    assertTrue(wishListPage.verifyAbsentOfWishLists());
  }

  @Execution(ExecutionMode.SAME_THREAD)
  @Story("Add product to wishlist")
  @Description("Verify that user is able to add product to created wishlist")
  @DisplayName("Verify add to created wishlist")
  @TmsLink("ID-104")
  @Test
  public void addToCreatedWishListTest() {
    SignInPage signInPage = new SignInPage();
    AccountPage accountPage = signInPage.openSignInPage().fillEmailAndPassword(USER_EMAIL, USER_PASSWORD).logInToApp();
    WishListPage wishListPage = accountPage.openWishLists().createWishList(CREATED_LIST_NAME);
    ProductsPage productsPage = wishListPage.openCategory(BasicPage.Category.DRESS);
    ProductDetailPage productDetailPage = productsPage.openProductDetailPage(PRODUCT_2).addItemToWishList();
    productDetailPage.openAccountPage().openWishLists();

    assertAll(
        () -> assertEquals(CREATED_LIST_NAME, wishListPage.getWishListNames()),
        () -> assertEquals(1, wishListPage.getWishListItemQnt())
    );
  }
}
