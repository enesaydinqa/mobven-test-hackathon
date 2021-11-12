package com.hackathon.driver;

import com.hackathon.SeleniumSession;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;

public class DriverFactory
{
    public abstract AppiumDriverLocalService startAppiumServer();

    public abstract Capabilities prepareDriver();

    public abstract Capabilities createDesiredCapabilities();

    public abstract RemoteWebDriver createDriver(DriverAnnotateWrapper driverAnnotateWrapper) throws MalformedURLException;

    public abstract SeleniumSession.DriverWrapper initSessionProxy(DriverAnnotateWrapper driverAnnotateWrapper);

    protected final SeleniumSession seleniumSession;
    public SeleniumSession.DriverWrapper driver = null;

    public DriverFactory(SeleniumSession seleniumSession)
    {
        this.seleniumSession = seleniumSession;
    }
}
