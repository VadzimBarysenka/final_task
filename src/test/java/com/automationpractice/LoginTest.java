package com.automationpractice;

import io.qameta.allure.Description;
import io.qameta.allure.Story;
import io.qameta.allure.TmsLink;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@Execution(ExecutionMode.CONCURRENT)
@DisplayName("Account creation and and login")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(ScreenShot.class)
public class LoginTest {
    private static SignInPage signInPage;
    private static AccountPage accountPage;
    private final static String USER_EMAIL = "testo@mail.com";
    private final static String USER_PASSWORD = "!QAZxsw2";
    private final static String USER_NAME = "test test";
    private final static String CUSTOMER_EMAIL = RandomStringUtils.randomAlphabetic(8) + "@mail.com";
    private final static String CUSTOMER_PASSWORD = RandomStringUtils.randomAlphanumeric(8);
    private final static String CUSTOMER_FIRST_NAME = RandomStringUtils.randomAlphabetic(5);
    private final static String CUSTOMER_LAST_NAME = RandomStringUtils.randomAlphabetic(5);
    private final static String CUSTOMER_ADDRESS = RandomStringUtils.randomAlphanumeric(9);
    private final static String CUSTOMER_CITY = RandomStringUtils.randomAlphanumeric(5);
    private final static String CUSTOMER_POST_CODE = RandomStringUtils.randomNumeric(5);
    private final static String CUSTOMER_PHONE_MOBILE = RandomStringUtils.randomNumeric(7);

    @BeforeAll
    public static void setup() {
        signInPage = new SignInPage();
        accountPage = new AccountPage();
    }

    @AfterAll
    public static void cleanup() {
        // WebDriverSingleton.getInstance().closeDriver();
    }

    @AfterEach
    public void logOut() {
        signInPage.logout();
    }

    @Story("Account creation and login")
    @Description("Verify that user is able to create an account with correct data")
    @DisplayName("Verify account creation")
    @TmsLink("ID-101")
    @Test
    @Order(1)
    public void createAccountTest() {
        signInPage.openSignInPage()
                .fillEmailAddress(CUSTOMER_EMAIL)
                .openRegistrationPage()
                .fillCustomerFirstName(CUSTOMER_FIRST_NAME)
                .fillCustomerLastName(CUSTOMER_LAST_NAME)
                .fillCustomerEmail(CUSTOMER_EMAIL)
                .fillCustomerPassField(CUSTOMER_PASSWORD)
                .fillCustomerAddress(CUSTOMER_ADDRESS)
                .fillCustomerCity(CUSTOMER_CITY)
                .choseState(3)
                .fillCustomerPostCode(CUSTOMER_POST_CODE)
                .fillCustomerMobilePhone(CUSTOMER_PHONE_MOBILE)
                .submitAccount();

        assertEquals(CUSTOMER_FIRST_NAME + " " + CUSTOMER_LAST_NAME, signInPage.getCustomerName());
    }

    @Story("Account creation and login")
    @Description("Verify that user is able to login to app with correct credentials")
    @DisplayName("Login to app with correct credentials")
    @TmsLink("ID-102")
    @Test
    @Order(2)
    public void loginToAppTest() {
        signInPage.openSignInPage()
                .fillEmailAndPassword(USER_EMAIL, USER_PASSWORD)
                .logInToApp();

        assertEquals(USER_NAME, accountPage.getCustomerName());
    }
}

