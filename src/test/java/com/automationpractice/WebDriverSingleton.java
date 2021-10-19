package com.automationpractice;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class WebDriverSingleton {
    private WebDriver driver;
    private static WebDriverSingleton instance;

    enum Browsers {
        CHROME, FIREFOX
    }

    private WebDriverSingleton() {
    }

    public static WebDriverSingleton getInstance() {
        if (instance == null) {
            instance = new WebDriverSingleton();
        }
        return instance;
    }

    public WebDriver getDriver(Browsers getInBrowser) {
        if (driver == null) {
            if (getInBrowser == Browsers.CHROME) {
                driver = new ChromeDriver();
            } else if (getInBrowser == Browsers.FIREFOX) {
                driver = new FirefoxDriver();
            }
            this.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            this.driver.manage().window().maximize();
        }
        return driver;
    }

    public WebDriver getRemoteDriver(DesiredCapabilities capability) {
        if (driver == null) {
            try {
                //this.driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capability);
                this.driver = new RemoteWebDriver(new URL("https://oauth-vdmbrs90-1721b:44d764e1-e001-42c3-a0c0-098ec115a847@ondemand.eu-central-1.saucelabs.com:443/wd/hub"), capability);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        return driver;
    }

    public void closeDriver() {
        driver.close();
        driver = null;
    }
}