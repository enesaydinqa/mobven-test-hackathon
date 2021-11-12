package com.airbnb.tests;

import com.airbnb.client.AirBnbClient;
import com.airbnb.pageobjects.AirBnbFavoritePage;
import com.airbnb.pageobjects.AirBnbMainPage;
import com.airbnb.pageobjects.AirBnbSearchPage;
import com.hackathon.annotation.AirBnbTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class AirBnbAddFavoriteTest extends AirBnbClient
{
    @AirBnbTest
    public void search()
    {
        AirBnbMainPage airBnbMainPage = loginAirBnb();
        AirBnbSearchPage airBnbSearchPage = airBnbMainPage.clickDiscover();

        AirBnbFavoritePage airBnbFavoritePage =
                airBnbSearchPage.sendKeysWhereToGo("Antalya")
                        .clickCityAntalya()
                        .clickFindPlaceToStay()
                        .enterDateData()
                        .clickNextOne()
                        .clickAddPerson()
                        .clickEndSearch()
                        .clickWifi()
                        .clickShowPlacesToStay()
                        .clickAddFavorite(0);

        airBnbFavoritePage.sendKeysFavoriteTitle("Hackathon")
                .clickCreate()
                .clickFavorites()
                .clickWishListTitle()
                .clickCloseClue();

         Assertions.assertTrue(airBnbFavoritePage.isFavoriteDisplayed());
    }
}
