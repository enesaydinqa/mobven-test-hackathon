package com.airbnb.pageobjects;

import com.hackathon.driver.AppiumDriverManager;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

import java.util.List;
import java.util.stream.Stream;

public class AirBnbLoginPage extends AirBnbPageObject
{
    public AirBnbLoginPage(AppiumDriverManager appiumDriverManager)
    {
        super(appiumDriverManager);
    }

    @AndroidFindBy(id = "com.airbnb.android:id/2131428790")
    private MobileElement continueWithEmail;

    @AndroidFindBy(id = "com.airbnb.android:id/2131428776")
    private MobileElement emailTextField;

    @AndroidFindBy(id = "com.airbnb.android:id/2131429273")
    private MobileElement continueBtn;

    @AndroidFindBy(id = "com.airbnb.android:id/2131428776")
    private MobileElement passwordField;

    @AndroidFindBy(id = "com.airbnb.android:id/2131432435")
    private MobileElement usernameText;

    ////////////////



    @AndroidFindBy(id = "com.airbnb.android:id/2131430761")
    private MobileElement backButton;



    //////////////////////////



    ////////////////////////////



    public AirBnbLoginPage clickContinueWithEmailBtn()
    {
        appiumDriverManager.waitAndClick(continueWithEmail);
        return this;
    }

    public AirBnbLoginPage sendKeysEmailText(String email)
    {
        appiumDriverManager.waitAndSendKeys(emailTextField, email);
        return this;
    }

    public AirBnbLoginPage clickContinueBtn()
    {
        appiumDriverManager.waitAndSendKeys(continueBtn);
        return this;
    }

    public AirBnbLoginPage sendKeysPasswordText(String password)
    {
        appiumDriverManager.waitAndSendKeys(passwordField, password);
        return this;
    }

    public AirBnbLoginPage clickProfileIcon()
    {
        appiumDriverManager.waitAndSendKeys(passwordField);
        return this;
    }

    public Boolean assertUsernameDisplayed(String username)
    {
        return appiumDriverManager.isTextDisplayedOnPage(username);
    }

    public void clickBackBtn()
    {
        appiumDriverManager.waitAndClick(backButton);
    }

}
