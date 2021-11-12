package com.hackathon;

import com.hackathon.driver.AppiumDriverManager;
import com.hackathon.driver.DriverAnnotateWrapper;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Slf4j
public abstract class SeleniumSession
{
    public final DriverAnnotateWrapper driverProperties;
    private DriverWrapper driver = null;

    protected SeleniumSession(DriverAnnotateWrapper driverProperties)
    {
        this.driverProperties = driverProperties;
    }

    public void initSession()
    {
        driver = initDriverProxy(driverProperties);
    }

    public void destroyMobileSession()
    {
        try
        {
            getDriverWrapper().getAppiumDriverManager().getDriver().quit();
            log.info("appium session is over!");
        }
        catch (Exception ignored)
        {
            log.info("couldn't quit driver..");
        }
    }

    protected abstract DriverWrapper initDriverProxy(DriverAnnotateWrapper driverAnnotateWrapper);

    public DriverWrapper getDriverWrapper()
    {
        return driver;
    }

    public static class DriverWrapper
    {
        private final DriverAnnotateWrapper driver;
        private final AppiumDriverManager appiumDriverManager;
        private final AppiumDriverLocalService appiumDriverLocalService;

        public DriverWrapper(DriverAnnotateWrapper driver, AppiumDriverManager appiumDriverManager, AppiumDriverLocalService appiumDriverLocalService)
        {
            this.driver = driver;
            this.appiumDriverManager = appiumDriverManager;
            this.appiumDriverLocalService = appiumDriverLocalService;
            log.info("DriverWrapper set up is ready..");
        }

        public DriverAnnotateWrapper getDriver()
        {
            return driver;
        }

        public AppiumDriverManager getAppiumDriverManager()
        {
            return appiumDriverManager;
        }

        public void stopAppiumSession()
        {
            appiumDriverLocalService.stop();
            log.info("Appium session stopped!");
        }

        @Override
        public boolean equals(Object o)
        {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            DriverWrapper that = (DriverWrapper) o;
            return Objects.equals(driver, that.driver);
        }

        @Override
        public int hashCode()
        {
            return Objects.hash(driver);
        }
    }
}