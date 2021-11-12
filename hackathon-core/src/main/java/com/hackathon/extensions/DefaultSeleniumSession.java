package com.hackathon.extensions;

import com.hackathon.SeleniumSession;
import com.hackathon.driver.DriverAnnotateWrapper;
import com.hackathon.driver.DriverFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

public class DefaultSeleniumSession extends SeleniumSession
{
    protected DefaultSeleniumSession(DriverAnnotateWrapper driverProperties)
    {
        super(driverProperties);
    }

    @Override
    protected DriverWrapper initDriverProxy(DriverAnnotateWrapper driverAnnotateWrapper)
    {
        DriverFactory driverFactory = null;
        try
        {
            driverFactory = driverAnnotateWrapper.getDriverFactory().getConstructor(SeleniumSession.class).newInstance(this);
        }
        catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e)
        {
            e.printStackTrace();
        }
        return Objects.requireNonNull(driverFactory).initSessionProxy(driverAnnotateWrapper);
    }

}
