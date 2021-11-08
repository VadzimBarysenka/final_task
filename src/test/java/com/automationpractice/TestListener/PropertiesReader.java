package com.automationpractice.TestListener;

import org.apache.http.protocol.ExecutionContext;

import java.io.IOException;
import java.util.Properties;

public class PropertiesReader {
  private static final Properties PROPS;

  static {
    PROPS = new Properties();
    try {
      PROPS.load(ExecutionContext.class.getResourceAsStream("/application.properties"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static String get(String key) {
    return PROPS.getProperty(key);
  }
}
