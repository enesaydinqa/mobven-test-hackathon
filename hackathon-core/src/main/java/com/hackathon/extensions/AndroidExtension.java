package com.hackathon.extensions;

import com.hackathon.SeleniumSession;
import com.hackathon.device.DeviceManagement;
import com.hackathon.driver.AndroidDriverFactory;
import com.hackathon.driver.DriverAnnotateWrapper;
import com.hackathon.properties.DriverProp;
import io.appium.java_client.remote.MobileCapabilityType;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.util.Objects;

@Slf4j
public class AndroidExtension extends AppiumExtension implements BeforeTestExecutionCallback, AfterTestExecutionCallback
{
    @Override
    public void beforeTestExecution(ExtensionContext extensionContext)
    {
        ExtensionContext.Store store = extensionContext.getStore(EXTENSION_NAMESPACE);
        Object testInstance = extensionContext.getRequiredTestInstance();
        DriverAnnotateWrapper driver = DriverAnnotateWrapper.createFromDriver(AndroidDriverFactory.class);
        SeleniumSession seleniumSession = createSeleniumSession(driver);
        seleniumSession.initSession();
        appiumDriverManager = seleniumSession.getDriverWrapper().getAppiumDriverManager();
        getInstanceForGlobalVariables(extensionContext, testInstance, appiumDriverManager);
        store.put(SESSION, seleniumSession);

        String deviceUid = (String) seleniumSession.getDriverWrapper().getAppiumDriverManager().getDriver().getCapabilities().getCapability(MobileCapabilityType.UDID);
        extensionContext.getRoot().getStore(ExtensionContext.Namespace.create(AndroidExtension.class)).put(extensionContext.getUniqueId(), deviceUid);
        log.info("Test Started : {}#{}", extensionContext.getTestClass().get().getSimpleName(), extensionContext.getTestMethod().get().getName());
    }

    @Override
    public void afterTestExecution(ExtensionContext extensionContext)
    {
        ExtensionContext.Store store = extensionContext.getStore(EXTENSION_NAMESPACE);
        SeleniumSession seleniumSession = store.get(SESSION, SeleniumSession.class);

        if (extensionContext.getExecutionException().isPresent())
        {
            log.info("Test Failed : {}#{}", extensionContext.getTestClass().get().getSimpleName(), extensionContext.getTestMethod().get().getName());
            String base64ScreeShot = ((TakesScreenshot) Objects.requireNonNull(seleniumSession.getDriverWrapper().getAppiumDriverManager().getDriver())).getScreenshotAs(OutputType.BASE64);
            extensionContext.getRoot().getStore(ExtensionContext.Namespace.create(AndroidExtension.class)).put(extensionContext.getUniqueId(), base64ScreeShot);
        }

        String uid = (String) seleniumSession.getDriverWrapper().getAppiumDriverManager().getDriver().getCapabilities().getCapability(MobileCapabilityType.UDID);
        seleniumSession.destroyMobileSession();

        if (DriverProp.getDriverProp().getAppiumDriverLocalService())
        {
            seleniumSession.getDriverWrapper().stopAppiumSession();
        }

        DeviceManagement.releaseDevice(uid);
        log.info("Test Finished : {}#{}", extensionContext.getTestClass().get().getSimpleName(), extensionContext.getTestMethod().get().getName());
    }
}
