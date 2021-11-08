package com.automationpractice.Driver;

import org.openqa.selenium.WebDriver;

import java.util.Objects;

public class WebDriverSingleton {
  private static final ThreadLocal<WebDriver> drivers = new ThreadLocal<>();
  private static WebDriverSingleton instance;
  private DriverStrategy driverStrategy;

  private WebDriverSingleton() {
  }

  public static WebDriverSingleton getInstance() {
    if (Objects.isNull(instance)) {
      instance = new WebDriverSingleton();
    }
    return instance;
  }

  public WebDriver getDriver() {
    if (Objects.isNull(drivers.get())) {
      driverStrategy = (Objects.isNull(System.getProperty("remote")))
          ? new LocalExecution()
          : new RemoteExecution();
      drivers.set(driverStrategy.startDriver());
    }
    return drivers.get();
  }

  public void closeDriver() {
    drivers.get().close();
    drivers.set(null);
  }
}