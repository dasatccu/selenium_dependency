package com.dependencymanager.driver;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestDriver {
    @Test
    public void testDriverForChrome(){
       DriverManager manager = new DriverManager();
       WebDriver driver = manager.getDriver("windows","chrome");
       Assert.assertNotNull(driver);
    }
}
