package com.airbnb.pageobjects;

import com.hackathon.driver.AppiumDriverManager;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class AirBnbMainPage extends AirBnbPageObject
{
    public AirBnbMainPage(AppiumDriverManager appiumDriverManager)
    {
        super(appiumDriverManager);
    }

    @AndroidFindBy(id = "com.airbnb.android:id/2131430787")
    private MobileElement profileIcon;

    @AndroidFindBy(id = "com.airbnb.android:id/2131429940")
    private MobileElement discover;

    @AndroidFindBy(id = "com.airbnb.android:id/2131429019")
    private MobileElement search;

    public AirBnbSearchPage clickDiscover()
    {
        appiumDriverManager.waitAndClick(discover);
        return new AirBnbSearchPage(appiumDriverManager);
    }

    public void clickSearch()
    {
        appiumDriverManager.waitAndClick(search);
    }
}
