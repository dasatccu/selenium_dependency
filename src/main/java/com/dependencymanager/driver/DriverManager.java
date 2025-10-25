package com.dependencymanager.driver;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverManager {

  private static final String DRIVER_CONFIG_LOCATION = "src/main/resources/config/driverpath.json";
  private static final List<String> BROWSER_LIST = List.of("chrome", "firefox", "edge");
  private static final List<String> OS_LIST = List.of("linux", "windows");
  private WebDriver driver;

  private static String getPath(String os, String browser) {
    ObjectMapper mapper = new ObjectMapper();
    try {
      JsonNode root = mapper.readTree(new File(DRIVER_CONFIG_LOCATION));
      JsonNode osNode = root.get(os);
      JsonNode browserNode = osNode.get(browser);
      return browserNode.asText();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private static void setSystemProperty(String os, String browser) {
    if (browser.equalsIgnoreCase(BROWSER_LIST.get(0))) {
      if (os.equalsIgnoreCase(OS_LIST.get(0))) {
        System.setProperty("webdriver.chrome.driver", getPath(OS_LIST.get(0), BROWSER_LIST.get(0)));
      } else {
        System.setProperty("webdriver.chrome.driver", getPath(OS_LIST.get(1), BROWSER_LIST.get(0)));
      }
    } else if (browser.equalsIgnoreCase(BROWSER_LIST.get(1))) {
      if (os.equalsIgnoreCase(OS_LIST.get(0))) {
        System.setProperty("webdriver.gecko.driver", getPath(OS_LIST.get(0), BROWSER_LIST.get(1)));
      } else {
        System.setProperty("webdriver.gecko.driver", getPath(OS_LIST.get(1), BROWSER_LIST.get(1)));
      }
    } else if (browser.equalsIgnoreCase(BROWSER_LIST.get(2))) {
      if (os.equalsIgnoreCase(OS_LIST.get(0))) {
        System.setProperty("webdriver.edge.driver", getPath(OS_LIST.get(0), BROWSER_LIST.get(2)));
      } else {
        System.setProperty("webdriver.edge.driver", getPath(OS_LIST.get(1), BROWSER_LIST.get(2)));
      }
    }
  }

  public WebDriver getDriver(String os, String browser) {
    setSystemProperty(os, browser);
    if (browser.equalsIgnoreCase(BROWSER_LIST.get(0))) {
      this.driver = new ChromeDriver();
      this.driver.manage().deleteAllCookies();
      this.driver.manage().window().maximize();
      return this.driver;
    }else if(browser.equalsIgnoreCase(BROWSER_LIST.get(1))){
      this.driver = new FirefoxDriver();
      this.driver.manage().deleteAllCookies();
      this.driver.manage().window().maximize();
      return this.driver;
    }else if(browser.equalsIgnoreCase(BROWSER_LIST.get(2))){
      this.driver = new EdgeDriver();
      this.driver.manage().deleteAllCookies();
      this.driver.manage().window().maximize();
      return this.driver;
    }
    return null;
  }
}
