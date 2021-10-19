package com.automationpractice;

import io.qameta.allure.Description;
import io.qameta.allure.Story;
import io.qameta.allure.TmsLink;
import org.apache.commons.math3.util.Precision;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

//@Execution(ExecutionMode.CONCURRENT)
@DisplayName("Cart functionality")
@ExtendWith(ScreenShot.class)
public class CartTest {
    private static SignInPage signInPage;
    private static CartPage cartPage;
    private final static String USER_EMAIL = "testo@mail.com";
    private final static String USER_PASSWORD = "!QAZxsw2";

    @BeforeAll
    public static void setup() {
        signInPage = new SignInPage();
        cartPage = new CartPage();
    }

    @AfterAll
    public static void cleanup() {
        cartPage.cleanCart();
        signInPage.logout();
        WebDriverSingleton.getInstance().closeDriver();
    }

    @Story("Add product to cart form quick view")
    @Description("Verify that user is able to add product to Cart from quick view")
    @DisplayName("Verify add to cart from quick view")
    @TmsLink("ID-103")
    @Test
    public void addToCartTest() {
        signInPage.openSignInPage()
                .fillEmailAndPassword(USER_EMAIL, USER_PASSWORD)
                .logInToApp()
                .openCategory("women")
                .addProductToCartAndContinue(1)
                .addProductToCartAndContinue(3)
                .openCategory("dress")
                .addProductToCartAndContinue(2)
                .openCart();

        assertAll(
                () -> assertEquals(Precision.round(ProductsPage.getCartSum(), 2), cartPage.getTotalWithoutTax()),
                () -> assertEquals(3, cartPage.getSumProductQnt())
        );
    }
}
