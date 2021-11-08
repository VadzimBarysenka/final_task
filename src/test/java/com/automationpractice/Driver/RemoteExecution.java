package com.automationpractice.Driver;

import com.automationpractice.TestListener.PropertiesReader;
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
        this.driver = new RemoteWebDriver(new URL(PropertiesReader.get("remote.url")), capability);
      } catch (MalformedURLException e) {
        throw new RuntimeException("some message for remote driver url");
      }
    }
    return driver;
  }
}
