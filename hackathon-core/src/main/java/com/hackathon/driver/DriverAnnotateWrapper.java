package com.hackathon.driver;

public class DriverAnnotateWrapper
{
    private final Class<? extends DriverFactory> driverFactory;

    public DriverAnnotateWrapper(Class<? extends DriverFactory> driverFactory)
    {
        this.driverFactory = driverFactory;
    }

    public Class<? extends DriverFactory> getDriverFactory()
    {
        return driverFactory;
    }

    public static DriverAnnotateWrapper createFromDriver(Class<? extends DriverFactory> driverFactory)
    {
        return new DriverAnnotateWrapper(driverFactory);
    }
}
