package com.automationpractice;

import com.automationpractice.Driver.WebDriverSingleton;
import com.automationpractice.Pages.AccountPage;
import com.automationpractice.Pages.SignInPage;
import com.automationpractice.Pages.WishListPage;
import com.automationpractice.TestListener.TestListener;
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import io.qameta.allure.TmsLink;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

//@Execution(ExecutionMode.CONCURRENT)
@DisplayName("Wishlist functionality")
@ExtendWith(TestListener.class)
public class WishListTest {
    private AccountPage accountPage;
    private SignInPage signInPage;
    private WishListPage wishListPage;
    private final static String USER_EMAIL = "testo@mail.com";
    private final static String USER_PASSWORD = "!QAZxsw2";
    private final static String AUTO_CREATED_LIST_NAME = "My wishlist";
    private final static String CREATED_LIST_NAME = RandomStringUtils.randomAlphabetic(8);

    @BeforeEach
    public void setup() {
    }

    @AfterAll
    public static void cleanup() {
        WebDriverSingleton.getInstance().closeDriver();
    }

    @AfterEach
    public void cleanListAndLogOut() {
        wishListPage = new WishListPage();
        wishListPage.removeWishLists();
        wishListPage.logout();
    }

    @Story("Add product to wishlist")
    @Description("Verify that user is able to add product to autocreated wishlist")
    @DisplayName("Verify add to autocreated wishlist")
    @TmsLink("ID-104")
    @Test
    public void addToAutoCreatedWishList() {
        signInPage = new SignInPage();
        wishListPage = new WishListPage();

        accountPage = signInPage.openSignInPage().fillEmailAndPassword(USER_EMAIL, USER_PASSWORD).logInToApp();
        wishListPage = accountPage.openWishLists();

        assertTrue(wishListPage.verifyAbsentOfWishLists());

        wishListPage.openCategory("DRESS")
                .openProductDetailPage(2)
                .addItemToWishList()
                .openAccountPage()
                .openWishLists();

        assertAll(
                () -> assertEquals(AUTO_CREATED_LIST_NAME, wishListPage.getWishListName()),
                () -> assertEquals(1, wishListPage.getWishListItemQnt())
        );
    }

    @Story("Add product to wishlist")
    @Description("Verify that user is able to add product to created wishlist")
    @DisplayName("Verify add to created wishlist")
    @TmsLink("ID-104")
    @Test
    public void addToCreatedWishListTest() {
        signInPage = new SignInPage();
        wishListPage = new WishListPage();

        signInPage.openSignInPage()
                .fillEmailAndPassword(USER_EMAIL, USER_PASSWORD)
                .logInToApp()
                .openWishLists()
                .createWishList(CREATED_LIST_NAME)
                .openCategory("DRESS")
                .openProductDetailPage(5)
                .addItemToWishList()
                .openAccountPage()
                .openWishLists();

        assertAll(
                () -> assertEquals(CREATED_LIST_NAME, wishListPage.getWishListNames()),
                () -> assertEquals(1, wishListPage.getWishListItemQnt())
        );
    }
}
