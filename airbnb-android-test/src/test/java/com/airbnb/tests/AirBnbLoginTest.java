package com.airbnb.tests;

import com.airbnb.client.AirBnbClient;
import com.airbnb.pageobjects.AirBnbMainPage;
import com.hackathon.annotation.AirBnbTest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class AirBnbLoginTest extends AirBnbClient
{
    @AirBnbTest
    public void login()
    {
        AirBnbMainPage airBnbMainPage = new AirBnbMainPage(appiumDriverManager);

        airBnbMainPage
                .clickContinueWithEmailBtn()
                .sendKeysEmailText("email")
                .clickContinueBtn()
                .sendKeysPasswordText("password")
                .clickContinueBtn();
    }
}
