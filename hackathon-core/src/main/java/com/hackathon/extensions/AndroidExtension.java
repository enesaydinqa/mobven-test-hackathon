package com.hackathon.extensions;

import com.hackathon.SeleniumSession;
import com.hackathon.driver.AndroidDriverFactory;
import com.hackathon.driver.DriverAnnotateWrapper;
import io.appium.java_client.remote.MobileCapabilityType;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

@Slf4j
public class AndroidExtension extends AppiumExtension implements BeforeTestExecutionCallback, AfterTestExecutionCallback
{

    @Override
    public void afterTestExecution(ExtensionContext extensionContext)
    {
        log.info("Test Finished : {}#{}", extensionContext.getTestClass().get().getSimpleName(), extensionContext.getTestMethod().get().getName());
    }

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
}
