package com.automationpractice.Driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

public class RemoteExecution implements DriverStrategy {
    private WebDriver driver;

    @Override
    public WebDriver startDriver() {
        if (Objects.equals(System.getProperty("remote"), "cloud")) {
            try {
                DesiredCapabilities capability = DesiredCapabilities.chrome();
                capability.setCapability("platform", "Windows 10");
                capability.setCapability("version", "latest");
                capability.setCapability("screenResolution", "1280x1024");
                this.driver = new RemoteWebDriver(new URL("https://oauth-vdmbrs90-1721b:44d764e1-e001-42c3-a0c0-098ec115a847@ondemand.eu-central-1.saucelabs.com:443/wd/hub"), capability);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        return driver;
    }
}
