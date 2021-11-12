package com.hackathon.extensions;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.hackathon.SeleniumSession;
import com.hackathon.properties.ConfigurationProp;
import com.hackathon.report.ExtentTestManager;
import com.hackathon.report.JiraServiceProvider;
import io.appium.java_client.remote.MobileCapabilityType;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

import java.util.Optional;

import static com.hackathon.report.ExtentTestManager.getTest;

@Slf4j
public class ExtentReportExtension implements TestWatcher, BeforeTestExecutionCallback
{
    @Override
    public void beforeTestExecution(ExtensionContext extensionContext)
    {
        String deviceUid = extensionContext.getRoot().getStore(ExtensionContext.Namespace.create(AndroidExtension.class)).get(extensionContext.getUniqueId(), String.class);
        String testName = extensionContext.getTestClass().get().getSimpleName();
        String testDescription = "Class: " + extensionContext.getTestClass().get().getSimpleName() + " Method: " + extensionContext.getTestMethod().get().getName() +
                " runs in Device: " + deviceUid;
        ExtentTestManager
                .startTest(testName, testDescription)
                .assignDevice(deviceUid);
    }

    @Override
    public void testDisabled(ExtensionContext context, Optional<String> reason)
    {
        log.info("Test Disabled {}", context.getTestClass().get().getSimpleName());
    }

    @Override
    public void testSuccessful(ExtensionContext context)
    {
        log.info("Test Successful {}", context.getTestClass().get().getSimpleName());
    }

    @Override
    public void testAborted(ExtensionContext context, Throwable cause)
    {
        log.info("Test Aborted {}", context.getTestClass().get().getSimpleName());
    }

    @Override
    public void testFailed(ExtensionContext extensionContext, Throwable cause)
    {
        log.info("Test Failed {}", extensionContext.getTestClass().get().getSimpleName());
        String base64 = extensionContext.getRoot().getStore(ExtensionContext.Namespace.create(AndroidExtension.class)).get(extensionContext.getUniqueId(), String.class);
        getTest().fail("Test Failed", MediaEntityBuilder.createScreenCaptureFromBase64String(base64).build());

        if (ConfigurationProp.configurationProp.isJiraOpenIssue()){
            //use the project key of jira
            ExtensionContext.Store store = extensionContext.getStore(ExtensionContext.Namespace.create(AppiumExtension.class));
            SeleniumSession seleniumSession = store.get("session", SeleniumSession.class);
            String deviceUid = seleniumSession.getDriverWrapper().getAppiumDriverManager().getDriver().getCapabilities().getCapability(MobileCapabilityType.UDID).toString();

            JiraServiceProvider jiraServiceProvider = new JiraServiceProvider();
            String issueSummary = extensionContext.getTestClass().get().getSimpleName();

            String issueDescription = "Test run in device: " + deviceUid + "\n Exception Details: " + cause.getLocalizedMessage();

            //have to use username of user for reporter
            jiraServiceProvider.createJiraIssue("Bug", issueSummary, issueDescription, "testhackathon", base64);
        }
    }

}
