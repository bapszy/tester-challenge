package com.testerchallenge;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class TestPriceComponentsApp {

    private static WebDriver driver;

    @BeforeMethod
    public void setupTest() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://localhost:3000/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
    }

    @AfterMethod
    public void tearDownChromeBrowser() {
        driver.quit();
    }

    @Test
    public void testPriceComponents() throws InterruptedException {

        PriceComponentsPage mainPage = new PriceComponentsPage(driver);

        Assert.assertTrue(mainPage.isTitleVisible(), "Page not visible");

        mainPage.clickOnBasePencil();
        mainPage.addNewBaseValue("5");
        Assert.assertEquals(mainPage.getTotalValue(), "5.00", "Value doesn't match");

        mainPage.addNewComponentValue("Alloy surcharge", "2.15");
        String alloyPrice = mainPage.getPrice(2);
        int digitsAlloy = alloyPrice.split("\\.")[1].length();
        Assert.assertTrue((digitsAlloy == 1 || digitsAlloy == 2), "Value length not correct");

        mainPage.addNewComponentValue("Scrap surcharge", "3.14");
        String scrapPrice = mainPage.getPrice(3);
        int digitsScarp = scrapPrice.split("\\.")[1].length();
        Assert.assertTrue((digitsScarp == 1 || digitsScarp == 2), "Value length not correct");

        mainPage.addNewComponentValue("Internal surcharge", "0.7658");
        String internalPrice = mainPage.getPrice(4);
        int digitsInternal = internalPrice.split("\\.")[1].length();
        Assert.assertTrue((digitsInternal == 1 || digitsInternal == 2), "Value length not correct");
        Assert.assertTrue(Double.parseDouble(internalPrice) > 0.7658, "Value not rounded up");

        mainPage.addNewComponentValue("External surcharge", "1");
        String externalPrice = mainPage.getPrice(5);
        String digitValue = externalPrice.split("\\.")[1];
        int digitsExternal = digitValue.length();
        Assert.assertTrue((digitsExternal == 1 || digitsExternal == 2), "Value length not correct");
        Assert.assertEquals(digitValue, "0", "No zero digit if original value does not contain any");

        mainPage.addNewComponentValue("Storage surcharge", "0.3");
        String storagePrice = mainPage.getPrice(6);
        int digitsStorage = storagePrice.split("\\.")[1].length();
        Assert.assertTrue((digitsStorage == 1 || digitsStorage == 2), "Value length not correct");

        double totalValueBeforeDelete = Double.parseDouble(mainPage.getTotalValue());
        mainPage.removeComponent(4);

        Assert.assertEquals((totalValueBeforeDelete - Double.parseDouble(internalPrice)), 11.59,
                "Total price doesn't mach after delete");

        mainPage.editComponentName(5, "T");
        Assert.assertEquals(mainPage.getName(5), "Storage surcharge", "Name does not match");

        mainPage.editComponentPrice(3, "-2.15");
        Assert.assertEquals(mainPage.getPrice(3), "3.14", "Price does not match");

        mainPage.editComponentPrice(2, "1.79");
        Assert.assertEquals((Double.parseDouble(mainPage.getTotalValue())), 11.23,
                "Total price doesn't mach after modification");

    }
}
