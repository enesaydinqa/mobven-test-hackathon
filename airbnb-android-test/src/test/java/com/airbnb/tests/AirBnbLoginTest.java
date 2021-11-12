package com.airbnb.tests;

import com.airbnb.client.AirBnbClient;
import com.airbnb.pageobjects.AirBnbMainPage;
import com.hackathon.annotation.AirBnbTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class AirBnbLoginTest extends AirBnbClient
{
    private AirBnbMainPage airBnbMainPage;

    @BeforeEach
    public void before()
    {
        airBnbMainPage = new AirBnbMainPage(appiumDriverManager);
    }

    @AirBnbTest
    public void login()
    {
        airBnbMainPage
                .clickContinueWithEmailBtn()
                .sendKeysEmailText("email")
                .clickContinueBtn()
                .sendKeysPasswordText("password")
                .clickContinueBtn();
    }
}
