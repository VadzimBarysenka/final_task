package com.automationpractice;

import com.automationpractice.Driver.WebDriverSingleton;
import com.automationpractice.Pages.BasicPage;
import com.automationpractice.Pages.CartPage;
import com.automationpractice.Pages.ProductsPage;
import com.automationpractice.Pages.SignInPage;
import com.automationpractice.TestListener.PropertiesReader;
import com.automationpractice.TestListener.TestListener;
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import io.qameta.allure.TmsLink;
import org.apache.commons.math3.util.Precision;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Execution(ExecutionMode.CONCURRENT)
@DisplayName("Cart functionality")
@ExtendWith(TestListener.class)
public class CartTest {
  private final static String USER_EMAIL = PropertiesReader.get("user.mail");
  private final static String USER_PASSWORD = PropertiesReader.get("user.password");
  private final static int EXP_QNT_PRODUCTS = 3;
  private final static int PRODUCT_1 = 3;
  private final static int PRODUCT_2 = 4;
  private final static int PRODUCT_3 = 2;

  @AfterEach
  public void cleanAndLogout() {
    CartPage cartPage = new CartPage();
    cartPage.cleanCart();
    cartPage.logout();
  }

  @AfterAll
  public static void cleanup() {
    WebDriverSingleton.getInstance().closeDriver();
  }

  @Execution(ExecutionMode.SAME_THREAD)
  @Story("Add product to cart form quick view")
  @Description("Verify that user is able to add product to Cart from quick view")
  @DisplayName("Verify add to cart from quick view")
  @TmsLink("ID-103")
  @Test
  public void addToCartTest() {
    SignInPage signInPage = new SignInPage();
    signInPage.openSignInPage().fillEmailAndPassword(USER_EMAIL, USER_PASSWORD).logInToApp();
    ProductsPage productsPage = signInPage.openCategory(BasicPage.Category.WOMEN)
        .addProductToCartAndContinue(PRODUCT_1)
        .openCategory(BasicPage.Category.DRESS)
        .addProductToCartAndContinue(PRODUCT_2)
        .addProductToCartAndContinue(PRODUCT_3);
    CartPage cartPage = productsPage.openCart();

    double expectedTotal = Precision.round(productsPage.getCartSum(), 2);
    assertAll(
        () -> assertEquals(expectedTotal, cartPage.getTotalWithoutTax()),
        () -> assertEquals(EXP_QNT_PRODUCTS, cartPage.getSumProductQnt())
    );
  }

  @Test
  public void addToCartTestTwo() {
    SignInPage signInPage = new SignInPage();
    signInPage.openSignInPage().fillEmailAndPassword(USER_EMAIL, USER_PASSWORD).logInToApp();
    ProductsPage productsPage = signInPage.openCategory(BasicPage.Category.WOMEN)
        .addProductToCartAndContinue(PRODUCT_1)
        .addProductToCartAndContinue(PRODUCT_2);
    CartPage cartPage = productsPage.openCart();
    double expectedTotal = Precision.round(productsPage.getCartSum(), 2);

    Assertions.assertEquals(expectedTotal, cartPage.getTotalWithoutTax());

  }
}
