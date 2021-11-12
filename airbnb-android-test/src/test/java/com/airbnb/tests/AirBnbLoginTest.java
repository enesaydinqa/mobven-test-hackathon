package com.airbnb.tests;

import com.airbnb.client.AirBnbClient;
import com.airbnb.pageobjects.AirBnbLoginPage;
import com.airbnb.utils.AirBnbUtils;
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
        AirBnbLoginPage airBnbLoginPage = new AirBnbLoginPage(appiumDriverManager);

        airBnbLoginPage
                .clickContinueWithEmailBtn()
                .sendKeysEmailText(AirBnbUtils.email)
                .clickContinueBtn()
                .sendKeysPasswordText(AirBnbUtils.password)
                .clickContinueBtn();
    }
}
