package com.airbnb.client;

import com.hackathon.annotation.Driver;
import com.hackathon.driver.AppiumDriverManager;
import com.hackathon.report.ExtentReportManager;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;

@Slf4j
public class AirBnbClient
{
    @Driver
    public AppiumDriverManager appiumDriverManager;

    @AfterAll
    public static void afterAll()
    {
        ExtentReportManager.extentReports.flush();
    }
}
