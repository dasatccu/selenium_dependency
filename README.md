# Selenium Dependency

[![GitHub release](https://img.shields.io/github/v/release/dasatccu/selenium_dependency?style=flat-square)](https://github.com/dasatccu/selenium_dependency/releases)

**Selenium Dependency** is a reusable Maven POM that bundles commonly used dependencies for Selenium-based automation frameworks. It provides a single artifact for:

- Selenium WebDriver
- TestNG
- Log4j2
- Jackson Databind

This project helps teams avoid creating the same POM repeatedly for each Selenium automation project.

---

## Maven Coordinates

```xml
<dependency>
    <groupId>org.seleniumdump</groupId>
    <artifactId>selenium-dependency</artifactId>
    <version>1.0.0</version>
</dependency>


Features

Pre-configured Selenium Java dependency

TestNG integration for automated testing

Log4j2 logging framework included

Jackson for JSON parsing

Java 11+ compatible

Ready for GitHub Packages deployment


Usage

Add the repository in your project pom.xml:

<repositories>
    <repository>
        <id>github</id>
        <url>https://maven.pkg.github.com/dasatccu/selenium_dependency</url>
    </repository>
</repositories>


Add the dependency:

<dependency>
    <groupId>org.seleniumdump</groupId>
    <artifactId>selenium-dependency</artifactId>
    <version>1.0.0</version>
</dependency>


Start building your Selenium automation project without manually adding dependencies each time.

Example

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
