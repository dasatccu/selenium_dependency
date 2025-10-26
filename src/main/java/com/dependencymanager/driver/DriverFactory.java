package com.dependencymanager.driver;

public class DriverFactory {

  private DriverFactory() {
    // prevent instantiation
  }

  public static IDriverManager getManager() {
    return new DriverManagerImpl();
  }

}
