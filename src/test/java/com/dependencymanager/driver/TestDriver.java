package com.dependencymanager.driver;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestDriver {
    @Test
    public void testDriverForChrome(){
        IDriverManager manager = DriverFactory.getManager();
        WebDriver driver = manager.getDriver("windows","chrome");
        driver.get("https://www.google.com");
    }
}
