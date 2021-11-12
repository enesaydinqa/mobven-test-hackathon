package com.hackathon.driver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
public class AppiumDriverManager
{
    public final AppiumDriver driver;
    private final WebDriverWait webDriverWait;
    private static final int DEFAULT_TIMEOUT = 10;
    private int maxSwipeCount = 5;

    public AppiumDriverManager(AppiumDriver appiumDriver)
    {
        this.driver = appiumDriver;
        this.webDriverWait = new WebDriverWait(driver, DEFAULT_TIMEOUT);
    }

    public AppiumDriver getDriver()
    {
        return driver;
    }

    /**
     * Appium driver utils
     **/


    public void waitAndClick(MobileElement mobileElement)
    {
        try
        {
            mobileElement.click();
        }
        catch (Exception e)
        {
            log.info("Wait Until Element Clickable...");
            try
            {
                waitUntilElementToBeClickable(mobileElement);
                mobileElement.click();
            }
            catch (Exception e1)
            {
                log.info("Swipe to " + mobileElement);
                swipeToElement(mobileElement);
                mobileElement.click();
            }
        }
    }

    public void waitUntilElementToBeClickable(MobileElement mobileElement)
    {
        try
        {
            log.info("waiting for " + mobileElement);
            webDriverWait.until(ExpectedConditions.elementToBeClickable(mobileElement));
        }
        catch (Exception e)
        {
            log.info("coulnt't find  " + mobileElement);
            sleep(500);
        }
    }

    public void swipeToElement(MobileElement mobileElement)
    {
        if (maxSwipeCount == 0)
        {
            log.info("Element could not found!!!");
            return;
        }

        if (!isMobileElementDisplayedOnPage(mobileElement))
        {
            try
            {
                swipeScreen(AppiumDriverManager.SwipeDirection.DOWN, 1);
                assertTrue(mobileElement.isDisplayed());
            }
            catch (Exception e)
            {
                log.info(mobileElement + " could not found! Screen will be swiped one more time.");
                maxSwipeCount--;
                swipeToElement(mobileElement);
            }
        }
    }

    public void swipeScreen(AppiumDriverManager.SwipeDirection swipeDirection, int repeat)
    {
        JavascriptExecutor js = driver;
        HashMap<String, String> swipeObject = new HashMap<>();

        for (int i = 0; i < repeat; i++)
        {
            switch (swipeDirection)
            {
                case UP:
                    swipeObject.put("direction", "down");
                    js.executeScript("mobile: swipe", swipeObject);
                    break;
                case DOWN:
                    swipeObject.put("direction", "up");
                    js.executeScript("mobile: swipe", swipeObject);
                    break;
                case LEFT:
                    swipeObject.put("direction", "left");
                    js.executeScript("mobile: swipe", swipeObject);
                    break;
                case RIGHT:
                    swipeObject.put("direction", "right");
                    js.executeScript("mobile: swipe", swipeObject);
                    break;
            }
        }
        sleep(500);
    }

    public enum SwipeDirection
    {
        UP, DOWN, LEFT, RIGHT
    }

    public boolean isMobileElementDisplayedOnPage(MobileElement mobileElement)
    {
        boolean isElementDisplayed = false;
        try
        {
            webDriverWait.until(ExpectedConditions.elementToBeClickable(mobileElement));
            isElementDisplayed = mobileElement.isDisplayed();
        }
        catch (Exception e)
        {
            log.info(mobileElement + " is not appeared on the page!");
        }
        return isElementDisplayed;
    }

    public void waitAndSendKeys(MobileElement mobileElement, CharSequence... keys)
    {
        waitUntilVisibilityOf(mobileElement);
        mobileElement.sendKeys(keys);
    }

    public void waitAndSendKeys(MobileElement mobileElement, String keys)
    {
        try
        {
            mobileElement.clear();
            mobileElement.sendKeys(keys);
        }
        catch (Exception e)
        {
            log.info("Wait Until Element Visible...");
            try
            {
                waitUntilVisibilityOf(mobileElement);
                mobileElement.clear();
                mobileElement.sendKeys(keys);
            }
            catch (Exception e1)
            {
                log.info("Scroll to Element...");
                swipeToElement(mobileElement);
                mobileElement.clear();
                mobileElement.sendKeys(keys);
            }
        }
    }

    public void waitUntilVisibilityOf(MobileElement mobileElement)
    {
        try
        {
            webDriverWait.until(ExpectedConditions.visibilityOf(mobileElement));
        }
        catch (Exception e)
        {
            log.info("element is not appeared..");
        }
    }

    public static void sleep(int millisecond)
    {
        try
        {
            log.info("sleep : " + TimeUnit.MILLISECONDS.toSeconds(millisecond) + " seconds");
            Thread.sleep(millisecond, 500);
        }
        catch (InterruptedException e)
        {
            Thread.currentThread().interrupt();
        }
    }

    public boolean isTextDisplayedOnPage(String assertText)
    {
        return isTextDisplayedOnPage(assertText, 0);
    }

    public boolean isTextDisplayedOnPage(String assertText, int retryCount)
    {
        boolean isTextOnPage = false;
        try
        {
            isTextOnPage = getDriver().findElement(By.xpath("//*[@text='" + assertText + "']")).isDisplayed();
        }
        catch (Exception e)
        {
            log.info(assertText + " is not displayed on page");
            if (retryCount > 0)
            {
                log.info("Trying to find again..");
                retryCount--;
                isTextDisplayedOnPage(assertText, retryCount);
            }
        }

        return isTextOnPage;
    }

}
