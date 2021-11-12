package com.airbnb.pageobjects;

import com.hackathon.driver.AppiumDriverManager;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

import java.util.List;

public class AirBnbFavoritePage extends AirBnbPageObject
{
    public AirBnbFavoritePage(AppiumDriverManager appiumDriverManager)
    {
        super(appiumDriverManager);
    }

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
    private List<MobileElement> favoriteListSize;

    @AndroidFindBy(id = "com.airbnb.android:id/2131431854")
    private MobileElement whishListSettings;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Bu favori listesini silin']")
    private MobileElement deleteFavoriteList;

    @AndroidFindBy(id = "android:id/button1")
    private MobileElement deleteBtn;

    @AndroidFindBy(xpath = "//*[@text='Hackathon']")
    private MobileElement favoriteTextName;


    //****************

    public AirBnbFavoritePage sendKeysFavoriteTitle(String keys)
    {
        appiumDriverManager.waitAndSendKeys(favoriteTextField, keys);
        return this;
    }

    public AirBnbFavoritePage clickCreate()
    {
        appiumDriverManager.waitAndClick(create);
        return this;
    }

    public AirBnbFavoritePage clickFavorites()
    {
        appiumDriverManager.waitAndClick(favorites);
        return this;
    }

    public AirBnbFavoritePage clickWishListTitle()
    {
        appiumDriverManager.waitAndClick(title);
        return this;
    }

    public AirBnbFavoritePage clickCloseClue()
    {
        appiumDriverManager.waitAndClick(closeClue);
        return this;
    }

    public int getSize()
    {
        return favoriteListSize.size();
    }

    public AirBnbFavoritePage clickWhishListSettings()
    {
        appiumDriverManager.waitAndClick(whishListSettings);
        return this;
    }

    public AirBnbFavoritePage clickDeleteFavoriteList()
    {
        appiumDriverManager.waitAndClick(deleteFavoriteList);
        return this;
    }

    public AirBnbFavoritePage clickDelete()
    {
        appiumDriverManager.waitAndClick(deleteBtn);
        return this;
    }

    public Boolean isFavoriteDisplayed()
    {
        return favoriteTextName.isDisplayed();
    }
}
