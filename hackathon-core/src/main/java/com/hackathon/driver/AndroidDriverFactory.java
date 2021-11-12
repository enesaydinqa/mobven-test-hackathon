package com.hackathon.driver;

import com.hackathon.SeleniumSession;
import com.hackathon.device.Device;
import com.hackathon.device.DeviceManagement;
import com.hackathon.properties.DriverProp;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServerHasNotBeenStartedLocallyException;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

@Slf4j
public class AndroidDriverFactory extends DriverFactory
{
    private Device device;

    protected AppiumDriverLocalService service;

    public AndroidDriverFactory(SeleniumSession seleniumSession)
    {
        super(seleniumSession);
    }

    @Override
    public DesiredCapabilities prepareDriver()
    {
        return createDesiredCapabilities();
    }

    @Override
    public DesiredCapabilities createDesiredCapabilities()
    {
        device = DeviceManagement.getDevice();

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, device.getVersion());
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, device.getDeviceName());
        capabilities.setCapability(MobileCapabilityType.APP, DriverProp.getDriverProp().getApkFilePath());
        capabilities.setCapability(MobileCapabilityType.UDID, device.getUid());
        capabilities.setCapability("unicodeKeyboard", true);
        capabilities.setCapability("appPackage", DriverProp.getDriverProp().getAppPackage());
        capabilities.setCapability("appActivity", DriverProp.getDriverProp().getAppActivity());
        capabilities.setCapability("autoGrantPermissions", true);
        capabilities.setCapability("fastReset", true);
        capabilities.setCapability("clearSystemFiles", true);
        capabilities.setCapability("disableAndroidWatchers", true);
        capabilities.setCapability("noSign", true);
        capabilities.setCapability("newCommandTimeout", "600");
        capabilities.setCapability("automationName", "UiAutomator2");
        return capabilities;
    }

    @Override
    public RemoteWebDriver createDriver(DriverAnnotateWrapper driverAnnotateWrapper) throws MalformedURLException
    {
        DesiredCapabilities capabilities = prepareDriver();

        AppiumDriver appiumDriver;
        if (DriverProp.getDriverProp().getAppiumDriverLocalService())
        {
            service = startAppiumServer();
            appiumDriver = new AndroidDriver<>(service.getUrl(), capabilities);
            log.info("local appium session is created!");
        }
        else
        {
            URL url = new URL("http://" + device.getAppiumUrl() + ":" + device.getPort() + "/wd/hub");
            appiumDriver = new AndroidDriver<>(url, capabilities);
            log.info("appium session is created on url :" + url);
        }

        return appiumDriver;
    }

    @Override
    public SeleniumSession.DriverWrapper initSessionProxy(DriverAnnotateWrapper driverAnnotateWrapper)
    {
        try
        {
            AppiumDriver appiumDriver = (AppiumDriver) createDriver(driverAnnotateWrapper);

            driver = new SeleniumSession.DriverWrapper(driverAnnotateWrapper, new AppiumDriverManager(appiumDriver), service);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return driver;
    }

    @SuppressWarnings("unchecked")
    @Override
    public AppiumDriverLocalService startAppiumServer()
    {
        AppiumServiceBuilder serviceBuilder = new AppiumServiceBuilder();
        serviceBuilder
                .usingDriverExecutable(new File(DriverProp.getDriverProp().getNodePath()))
                .withAppiumJS(new File(DriverProp.getDriverProp().getAppiumPath()))
                .usingAnyFreePort()
                .withArgument(GeneralServerFlag.LOG_LEVEL, "error");

        HashMap<String, String> environment = new HashMap();
        environment.put("PATH", "/usr/local/bin:" + System.getenv("PATH"));
        environment.put("ANDROID_HOME", DriverProp.getDriverProp().getAndroidHomePath());
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
