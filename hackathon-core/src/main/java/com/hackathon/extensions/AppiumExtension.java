package com.hackathon.extensions;

import com.hackathon.SeleniumSession;
import com.hackathon.annotation.Driver;
import com.hackathon.driver.AppiumDriverManager;
import com.hackathon.driver.DriverAnnotateWrapper;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

public class AppiumExtension
{
    protected static final ExtensionContext.Namespace EXTENSION_NAMESPACE = ExtensionContext.Namespace.create(AppiumExtension.class);

    private static final Class<Driver> DRIVER_CLASS = Driver.class;
    protected static final String SESSION = "session";
    protected AppiumDriverManager appiumDriverManager;

    protected SeleniumSession createSeleniumSession(DriverAnnotateWrapper driver)
    {
        return new DefaultSeleniumSession(driver);
    }

    protected void getInstanceForGlobalVariables(ExtensionContext extensionContext, Object targetObjet, Object sourceObject)
    {
        Class currentClass = null;
        try
        {
            currentClass = extensionContext.getTestClass().orElseThrow(() -> new Exception("Current class not found"));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        do
        {
            Field[] fields = Objects.requireNonNull(currentClass).getDeclaredFields();

            Arrays
                    .stream(fields)
                    .forEach(field ->
                            Optional.of(field.isAnnotationPresent(DRIVER_CLASS))
                                    .filter(Boolean::booleanValue)
                                    .ifPresent(d -> injectFieldValues(field, targetObjet, sourceObject)));

            currentClass = currentClass.getSuperclass();
        }
        while (!currentClass.equals(Object.class));
    }

    private void injectFieldValues(Field field, Object targetObject, Object sourceObject)
    {
        try
        {
            field.setAccessible(true);
            field.set(targetObject, sourceObject);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

    }
}
