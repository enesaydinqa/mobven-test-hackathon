package com.hackathon.driver;

import com.hackathon.SeleniumSession;

public class DriverFactory
{
    protected final SeleniumSession seleniumSession;
    public SeleniumSession.DriverWrapper driver = null;

    public DriverFactory(SeleniumSession seleniumSession)
    {
        this.seleniumSession = seleniumSession;
    }
}
