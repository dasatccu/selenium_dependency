package com.dependencymanager.driver;

import org.openqa.selenium.WebDriver;

public interface IDriverManager {
   WebDriver getDriver(String os, String browser);
}
