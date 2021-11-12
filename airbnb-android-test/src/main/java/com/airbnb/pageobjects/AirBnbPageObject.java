package com.airbnb.pageobjects;

import com.hackathon.driver.AppiumDriverManager;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public abstract class AirBnbPageObject
{
    protected final AppiumDriverManager appiumDriverManager;

    public AirBnbPageObject(AppiumDriverManager appiumDriverManager)
    {
        this.appiumDriverManager = appiumDriverManager;
        PageFactory.initElements(new AppiumFieldDecorator(appiumDriverManager.getDriver()), this);
    }
}
