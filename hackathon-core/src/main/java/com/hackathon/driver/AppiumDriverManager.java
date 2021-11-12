package com.hackathon.driver;

import io.appium.java_client.AppiumDriver;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.support.ui.WebDriverWait;

@Slf4j
public class AppiumDriverManager
{
    public final AppiumDriver driver;
    private final WebDriverWait webDriverWait;
    private static final int DEFAULT_TIMEOUT = 10;

    public AppiumDriverManager(AppiumDriver appiumDriver)
    {
        this.driver = appiumDriver;
        this.webDriverWait = new WebDriverWait(driver, DEFAULT_TIMEOUT);
    }

    public AppiumDriver getDriver()
    {
        return driver;
    }
}
