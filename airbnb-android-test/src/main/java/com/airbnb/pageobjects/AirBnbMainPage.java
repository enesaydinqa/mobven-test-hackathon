package com.airbnb.pageobjects;

import com.hackathon.driver.AppiumDriverManager;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

import java.util.List;

public class AirBnbMainPage extends AirBnbPageObject
{
    public AirBnbMainPage(AppiumDriverManager appiumDriverManager)
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

    @AndroidFindBy(id = "com.airbnb.android:id/2131430787")
    private MobileElement profileIcon;

    @AndroidFindBy(id = "com.airbnb.android:id/2131432435")
    private MobileElement usernameText;



    ////////////////

    @AndroidFindBy(id = "com.airbnb.android:id/2131429940")
    private MobileElement discover;

    @AndroidFindBy(id = "com.airbnb.android:id/2131429019")
    private MobileElement search;

    @AndroidFindBy(id = "com.airbnb.android:id/2131430761")
    private MobileElement backButton;

    @AndroidFindBy(id = "com.airbnb.android:id/2131430752")
    private MobileElement whereToGo;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Antalya']")
    private MobileElement cityAntalya;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Kalacak yer bulun']")
    private MobileElement findPlaceToStay;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='24']")
    private MobileElement firstSelectedDate;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='26']")
    private MobileElement secondSelectedDate;

    @AndroidFindBy(xpath = "com.airbnb.android:id/2131430755")
    private MobileElement nextOne;

    @AndroidFindBy(id = "com.airbnb.android:id/2131431137")
    private MobileElement addPerson;

    @AndroidFindBy(id = "com.airbnb.android:id/n2_slim_little_search_end_button")
    private MobileElement searchEndBtn;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Wifi']")
    private MobileElement wifi;

    @AndroidFindBy(id = "com.airbnb.android:id/2131431290")
    private MobileElement showPlacesToStay;

    @AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc='Favorilere ekleyin'][0]")
    private MobileElement addFavorites;


    @AndroidFindBy(id = "com.airbnb.android:id/2131428776")
    private MobileElement favoriteTextField;

    @AndroidFindBy(id = "com.airbnb.android:id/2131431293")
    private MobileElement create;

    @AndroidFindBy(id = "com.airbnb.android:id/2131430805")
    private MobileElement favorites;

    @AndroidFindBy(id = "com.airbnb.android:id/wishlist_row_title")
    private MobileElement title;

    @AndroidFindBy(id = "com.airbnb.android:id/2131428310")
    private MobileElement closeClue;

    @AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc='Favorilere ekleyin']")
    private List<MobileElement> favoriteSize;


    public AirBnbMainPage clickContinueWithEmailBtn()
    {
        appiumDriverManager.waitAndClick(continueWithEmail);
        return this;
    }

    public AirBnbMainPage sendKeysEmailText(String email)
    {
        appiumDriverManager.waitAndSendKeys(emailTextField, email);
        return this;
    }

    public AirBnbMainPage clickContinueBtn()
    {
        appiumDriverManager.waitAndSendKeys(continueBtn);
        return this;
    }

    public AirBnbMainPage sendKeysPasswordText(String password)
    {
        appiumDriverManager.waitAndSendKeys(passwordField, password);
        return this;
    }

    public AirBnbMainPage clickProfileIcon()
    {
        appiumDriverManager.waitAndSendKeys(passwordField);
        return this;
    }

    public Boolean assertUsernameDisplayed(String username)
    {
        return appiumDriverManager.isTextDisplayedOnPage(username);
    }
}
