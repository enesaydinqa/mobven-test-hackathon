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
        log.info("Test Started : {}#{}", extensionContext.getTestClass().get().getSimpleName(), extensionContext.getTestMethod().get().getName());
    }
}
