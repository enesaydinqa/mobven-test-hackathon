package com.hackathon.extensions;

import com.hackathon.annotation.Driver;
import com.hackathon.driver.AppiumDriverManager;
import org.junit.jupiter.api.extension.ExtensionContext;

public class AppiumExtension
{
    protected static final ExtensionContext.Namespace EXTENSION_NAMESPACE = ExtensionContext.Namespace.create(AppiumExtension.class);

    private static final Class<Driver> DRIVER_CLASS = Driver.class;
    protected static final String SESSION = "session";
    protected AppiumDriverManager appiumDriverManager;


}
