package com.automationpractice;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class LocalExecution implements DriverStrategy {
    private WebDriver driver;

    @Override
    public WebDriver startDriver() {
            if (Objects.equals(System.getProperty("browser"), "chrome")) {
                driver = new ChromeDriver();
            } else if (Objects.equals(System.getProperty("browser"), "firefox")) {
                driver = new FirefoxDriver();
            }
            this.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            this.driver.manage().window().maximize();
        return driver;
    }
}
