package com.automationpractice;

import org.openqa.selenium.WebDriver;

public class WebDriverSingleton {
    private WebDriver driver;
    private static WebDriverSingleton instance;
    private DriverStrategy driverStrategy;

    private WebDriverSingleton() {
    }

    public static WebDriverSingleton getInstance() {
        if (instance == null) {
            instance = new WebDriverSingleton();
        }
        return instance;
    }

    public WebDriver getDriver() {
        if (driver == null) {
            if (System.getProperty("remote") == null) {
                driverStrategy = new LocalExecution();
            } else {
                driverStrategy = new RemoteExecution();
            }
            driver = driverStrategy.startDriver();
        }
        return driver;
    }

    public void closeDriver() {
        driver.close();
        driver = null;
    }
}