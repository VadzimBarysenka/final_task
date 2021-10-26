package com.automationpractice;

import com.automationpractice.Driver.WebDriverSingleton;
import com.automationpractice.Pages.CartPage;
import com.automationpractice.Pages.ProductsPage;
import com.automationpractice.Pages.SignInPage;
import com.automationpractice.TestListener.TestListener;
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import io.qameta.allure.TmsLink;
import org.apache.commons.math3.util.Precision;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

//@Execution(ExecutionMode.CONCURRENT)
@DisplayName("Cart functionality")
@ExtendWith(TestListener.class)
public class CartTest {
    private SignInPage signInPage;
    private CartPage cartPage;
    private final static String USER_EMAIL = "testo@mail.com";
    private final static String USER_PASSWORD = "!QAZxsw2";

    @BeforeEach
    public void setup() {
    }

    @AfterEach
    public void cleanAndLogout() {
        cartPage = new CartPage();
        cartPage.cleanCart();
        signInPage.logout();
    }

    @AfterAll
    public static void cleanup() {
        WebDriverSingleton.getInstance().closeDriver();
    }

    @Story("Add product to cart form quick view")
    @Description("Verify that user is able to add product to Cart from quick view")
    @DisplayName("Verify add to cart from quick view")
    @TmsLink("ID-103")
    @Test
    public void addToCartTest() {
        signInPage = new SignInPage();
        signInPage.openSignInPage()
                .fillEmailAndPassword(USER_EMAIL, USER_PASSWORD)
                .logInToApp()
                .openCategory("women")
                .addProductToCartAndContinue(3)
                .openCategory("dress")
                .addProductToCartAndContinue(2)
                .openCategory("t_shirts")
                .addProductToCartAndContinue(1);

        cartPage = new CartPage();
        cartPage.openCart();

        assertAll(
                () -> assertEquals(Precision.round(ProductsPage.getCartSum(), 2), cartPage.getTotalWithoutTax()),
                () -> assertEquals(3, cartPage.getSumProductQnt())
        );
    }
}
