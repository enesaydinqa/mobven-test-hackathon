package com.hackathon.driver;

import com.hackathon.SeleniumSession;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServerHasNotBeenStartedLocallyException;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.HashMap;

@Slf4j
public class AndroidDriverFactory extends DriverFactory
{
    protected AppiumDriverLocalService service;

    public AndroidDriverFactory(SeleniumSession seleniumSession)
    {
        super(seleniumSession);
    }

    @SuppressWarnings("unchecked")
    public AppiumDriverLocalService startAppiumServer()
    {
        AppiumServiceBuilder serviceBuilder = new AppiumServiceBuilder();
        serviceBuilder
                .usingDriverExecutable(new File(""))
                .withAppiumJS(new File(""))
                .usingAnyFreePort()
                .withArgument(GeneralServerFlag.LOG_LEVEL, "error");

        HashMap<String, String> environment = new HashMap();
        environment.put("PATH", "/usr/local/bin:" + System.getenv("PATH"));
        environment.put("ANDROID_HOME", "");
        serviceBuilder.withEnvironment(environment);

        AppiumDriverLocalService service = AppiumDriverLocalService.buildService(serviceBuilder);
        service.start();
        log.info(service.getUrl().toString());

        if (!service.isRunning())
        {
            throw new AppiumServerHasNotBeenStartedLocallyException("Android Local server not running");
        }
        return service;
    }
}
