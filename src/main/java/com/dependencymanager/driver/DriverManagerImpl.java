package com.dependencymanager.driver;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverManagerImpl implements IDriverManager{

  private static String getPath(String os, String browser) {
    // Load resource from inside the JAR
    try (InputStream inputStream = DriverManagerImpl.class
        .getClassLoader()
        .getResourceAsStream("config/driverpath.json")) {

      if (inputStream == null) {
        throw new RuntimeException("Resource not found: config/driverpath.json");
      }

      ObjectMapper mapper = new ObjectMapper();
      JsonNode root = mapper.readTree(inputStream);
      JsonNode osNode = root.get(os);
      if (osNode == null) throw new RuntimeException("OS not found in config: " + os);

      JsonNode browserNode = osNode.get(browser);
      if (browserNode == null) throw new RuntimeException("Browser not found in config: " + browser);

      return browserNode.asText();

    } catch (IOException e) {
      throw new RuntimeException("Error reading driver config file", e);
    }
  }

  private static void setSystemProperty(String os, String browser) {
    String path = getPath(os.toLowerCase(), browser.toLowerCase());

    switch (browser.toLowerCase()) {
      case "chrome" -> System.setProperty("webdriver.chrome.driver", path);
      case "firefox" -> System.setProperty("webdriver.gecko.driver", path);
      case "edge" -> System.setProperty("webdriver.edge.driver", path);
      default -> throw new IllegalArgumentException("Unsupported browser: " + browser);
    }
  }

  @Override
  public WebDriver getDriver(String os, String browser) {
    setSystemProperty(os, browser);
    WebDriver driver;

    switch (browser.toLowerCase()) {
      case "chrome" -> driver = new ChromeDriver();
      case "firefox" -> driver = new FirefoxDriver();
      case "edge" -> driver = new EdgeDriver();
      default -> throw new IllegalArgumentException("Unsupported browser: " + browser);
    }

    driver.manage().deleteAllCookies();
    driver.manage().window().maximize();
    return driver;
  }
}
