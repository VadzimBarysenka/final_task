package com.automationpractice.Driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class LocalExecution implements DriverStrategy {
  private WebDriver driver;

  @Override
  public WebDriver startDriver() {
    switch (System.getProperty("browser")) {
      case "chrome":
        driver = new ChromeDriver();
        break;
      case "firefox":
        driver = new FirefoxDriver();
        break;
      default:
        throw new RuntimeException("We yet not support " + System.getProperty("browser"));
    }
    this.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    this.driver.manage().window().maximize();
    return driver;
  }
}
