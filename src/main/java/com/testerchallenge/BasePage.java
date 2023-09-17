package com.testerchallenge;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    protected WebDriver webDriver;
    protected WebDriverWait wait;

    public BasePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        wait = new WebDriverWait(webDriver, Duration.ofSeconds(30));
        PageFactory.initElements(webDriver, this);
    }

    protected boolean waitForElement(WebElement webElement) {
        try {
            wait.until(ExpectedConditions.visibilityOf((webElement)));
            return true;
        } catch (TimeoutException e) {
            System.out.println("Something went wrong.");
        }
        return false;
    }
}
