package com.automationpractice;

import com.automationpractice.Driver.WebDriverSingleton;
import com.automationpractice.Pages.RegistrationPage;
import com.automationpractice.Pages.SignInPage;
import com.automationpractice.TestListener.TestListener;
import com.automationpractice.Users.RegistrationUser;
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
@ExtendWith(TestListener.class)
public class LoginTest {
    private SignInPage signInPage;
    private RegistrationUser registrationUser;
    private RegistrationPage registrationPage;
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
    private final static String CUSTOMER_STATE = "Florida";

    @BeforeEach
    public void setup() {
    }

    @AfterAll
    public static void cleanup() {
        WebDriverSingleton.getInstance().closeDriver();
    }

    @AfterEach
    public void logOut() {
        signInPage = new SignInPage();
        signInPage.logout();
    }

    @Story("Account creation and login")
    @Description("Verify that user is able to create an account with correct data")
    @DisplayName("Verify account creation")
    @TmsLink("ID-101")
    @Test
    @Order(1)
    public void createAccountTest() {
        signInPage = new SignInPage();
        registrationPage = new RegistrationPage();
        registrationUser = new RegistrationUser(CUSTOMER_FIRST_NAME,
                CUSTOMER_LAST_NAME,
                CUSTOMER_EMAIL,
                CUSTOMER_PASSWORD,
                CUSTOMER_ADDRESS,
                CUSTOMER_CITY,
                CUSTOMER_POST_CODE,
                CUSTOMER_PHONE_MOBILE,
                CUSTOMER_STATE);

        signInPage.openSignInPage()
                .fillEmailAddress(CUSTOMER_EMAIL)
                .openRegistrationPage();
        registrationPage.register(registrationUser);

        assertEquals(CUSTOMER_FIRST_NAME + " " + CUSTOMER_LAST_NAME, signInPage.getCustomerName());
    }

    @Story("Account creation and login")
    @Description("Verify that user is able to login to app with correct credentials")
    @DisplayName("Login to app with correct credentials")
    @TmsLink("ID-102")
    @Test
    @Order(2)
    public void loginToAppTest() {
        signInPage = new SignInPage();
        signInPage.openSignInPage()
                .fillEmailAndPassword(USER_EMAIL, USER_PASSWORD)
                .logInToApp();

        assertEquals(USER_NAME, signInPage.getCustomerName());
    }
}

