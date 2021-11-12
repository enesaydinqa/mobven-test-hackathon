package com.airbnb.tests;

import com.airbnb.client.AirBnbClient;
import com.airbnb.pageobjects.AirBnbFavoritePage;
import com.hackathon.annotation.AirBnbTest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class AirBnbRemoveFavoriteTest extends AirBnbClient
{
    @AirBnbTest
    public void login()
    {
        loginAirBnb();

        AirBnbFavoritePage airBnbFavoritePage = new AirBnbFavoritePage(appiumDriverManager);

        airBnbFavoritePage
                .clickWhishListSettings()
                .clickWishListTitle()
                .clickDeleteFavoriteList()
                .clickDelete();
    }
}
