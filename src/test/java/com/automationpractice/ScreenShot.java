package com.automationpractice;

import io.qameta.allure.Allure;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;


public class ScreenShot implements TestWatcher {

    @Override
    public void testFailed(ExtensionContext context, Throwable throwable) {

        Allure.getLifecycle().addAttachment(
                "Screenshot for failure",
                "image/png", "png",
                ((TakesScreenshot) WebDriverSingleton.getInstance().getDriver(WebDriverSingleton.Browsers.FIREFOX)).getScreenshotAs(OutputType.BYTES)
        );
    }
}
