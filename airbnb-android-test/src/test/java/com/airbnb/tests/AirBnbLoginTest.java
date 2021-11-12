package com.airbnb.tests;

import com.airbnb.client.AirBnbClient;
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
        loginAirBnb();
    }
}
