package com.hackathon.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Data
@Configuration
@PropertySource(ignoreResourceNotFound = true, value = "classpath:driver.properties")
public class DriverProp
{
    public static DriverProp driverProp;

    @Autowired
    public void DriverProp(DriverProp driverProp)
    {
        DriverProp.driverProp = driverProp;
    }

    @Value("#{systemProperties['android.home'] ?: systemProperties['user.home']+'/Library/Android/sdk'}")
    public String androidHomePath;

    @Value("#{systemProperties['node.path'] ?: '/usr/local/bin/node'}")
    public String nodePath;

    @Value("#{systemProperties['appium.path'] ?: '/usr/local/bin/appium'}")
    public String appiumPath;

    @Value("#{systemProperties['appium.driver.local.service'] ?: false}")
    public Boolean appiumDriverLocalService;

    @Value("${apk.file.path}")
    public String apkFilePath;

    @Value("${app.package}")
    public String appPackage;

    @Value("${app.activity}")
    public String appActivity;

    public static DriverProp getDriverProp()
    {
        return driverProp;
    }
}
