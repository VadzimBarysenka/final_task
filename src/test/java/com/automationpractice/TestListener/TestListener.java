package com.automationpractice.TestListener;

import com.automationpractice.Driver.WebDriverSingleton;
import io.qameta.allure.Allure;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;


public class TestListener implements TestWatcher {

  @Override
  public void testFailed(ExtensionContext context, Throwable throwable) {
    Allure.getLifecycle().addAttachment(
        "Screenshot for failure",
        "image/png", "png",
        ((TakesScreenshot) WebDriverSingleton.getInstance().getDriver()).getScreenshotAs(OutputType.BYTES)
    );
  }
}


