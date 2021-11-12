package com.airbnb.pageobjects;

import com.hackathon.driver.AppiumDriverManager;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

import java.util.List;
import java.util.stream.Stream;

public class AirBnbSearchPage extends AirBnbPageObject
{
    public AirBnbSearchPage(AppiumDriverManager appiumDriverManager)
    {
        super(appiumDriverManager);
    }

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

    @AndroidFindBy(id = "com.airbnb.android:id/2131430755")
    private MobileElement nextOne;

    @AndroidFindBy(id = "com.airbnb.android:id/2131431137")
    private MobileElement addPerson;

    @AndroidFindBy(id = "com.airbnb.android:id/n2_slim_little_search_end_button")
    private MobileElement searchEndBtn;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Wifi']")
    private MobileElement wifi;

    @AndroidFindBy(id = "com.airbnb.android:id/2131431290")
    private MobileElement showPlacesToStay;

    @AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc='Favorilere ekleyin']")
    private List<MobileElement> addFavorites;


    public void sendKeysWhereToGo(String keys)
    {
        appiumDriverManager.waitAndSendKeys(whereToGo, keys);
    }

    public void clickCityAntalya()
    {
        appiumDriverManager.waitAndSendKeys(cityAntalya);
    }

    public void clickFindPlaceToStay()
    {
        appiumDriverManager.waitAndClick(findPlaceToStay);
    }

    public void enterDateData()
    {
        Stream.of(firstSelectedDate, secondSelectedDate)
                .forEach(appiumDriverManager::waitAndClick);
    }

    public void clickNextOne()
    {
        appiumDriverManager.waitAndClick(nextOne);
    }

    public void clickAddPerson()
    {
        appiumDriverManager.waitAndClick(addPerson);
    }

    public void clickEndSearch()
    {
        appiumDriverManager.waitAndClick(searchEndBtn);
    }

    public void clickWifi()
    {
        appiumDriverManager.waitAndClick(wifi);
    }

    public void clickShowPlacesToStay()
    {
        appiumDriverManager.waitAndClick(showPlacesToStay);
    }

    public void clickAddFavorite(int index)
    {
        appiumDriverManager.waitAndClick(addFavorites.get(index));
    }
}
