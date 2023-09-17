package com.testerchallenge;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class PriceComponentsPage extends BasePage{

    @FindBy(xpath = "//h1[@class='font-bold text-2xl my-3']")
    private WebElement titlePriceComponents;

    @FindBy(id = "base-value-input")
    private WebElement inputBaseValue;

    @FindBy(id = "base-check-icon")
    private WebElement iconCheckIcon;

    @FindBy(xpath = "//span[@class='font-bold']")
    private WebElement textTotalValue;

    @FindBy(id = "ghost-label-input")
    private WebElement inputLabel;

    @FindBy(id = "ghost-value-input")
    private WebElement inputComponentValue;

    @FindBy(id = "ghost-check-icon")
    private WebElement iconCheckIconComponent;

    public PriceComponentsPage(WebDriver webDriver) {
        super(webDriver);
    }

    public boolean isTitleVisible(){
        return waitForElement(titlePriceComponents);
    }

    public void clickOnBasePencil() {
        By textBasePrice = By.xpath("//div[@class='flex-grow flex flex-col']");
        By pencilIcon = By.id("base-edit-icon");

        hoverOverElement(textBasePrice);
        hoverOverElement(pencilIcon);
        webDriver.findElement(pencilIcon).click();
    }

    public void addNewBaseValue(String value) {
        inputBaseValue.clear();
        inputBaseValue.click();
        inputBaseValue.sendKeys(value);
        iconCheckIcon.click();
    }

    public String getTotalValue() {
        return textTotalValue.getText();
    }

    public void addNewComponentValue(String label, String value) {
        inputLabel.click();
        inputLabel.clear();
        inputLabel.sendKeys(label);
        inputComponentValue.click();
        inputComponentValue.clear();
        inputComponentValue.sendKeys(value);
        iconCheckIconComponent.click();
    }

    public String getPrice(int index) {
        String elementPriceSelector = "//*[@id='app']/div/div/ul/div[" + index + "]/div[3]/div";
        WebElement elementPrice = webDriver.findElement(By.xpath(elementPriceSelector));
        return elementPrice.getText();
    }

    public String getName(int index) {
        String elementNameSelector = "//*[@id='app']/div/div/ul/div[" + index + "]/div[2]/span";
        WebElement elementName = webDriver.findElement(By.xpath(elementNameSelector));
        return elementName.getText();
    }

    public void removeComponent(int index) {
        By textComponentName = By.xpath("//*[@id='app']/div/div/ul/div[" + index + "]/div[2]/span");
        By iconTrash = By.xpath("//*[@id='app']/div/div/ul/div[" + index + "]/div[4]/span");

        hoverOverElement(textComponentName);
        hoverOverElement(iconTrash);
        webDriver.findElement(iconTrash).click();
    }

    public void editComponentName(int index, String name) {
        By textComponentName = By.xpath("//*[@id='app']/div/div/ul/div[" + index + "]/div[2]/span");
        By iconPencil = By.xpath("//*[@id='app']/div/div/ul/div[" + index + "]/div[1]/span");
        By inputEditName = By.xpath("//*[@id='app']/div/div/ul/div[" + index + "]/div[2]/input");
        By iconCheckMark = By.xpath("//*[@id='app']/div/div/ul/div[" + index + "]/div[4]/span[2]");

        hoverOverElement(textComponentName);
        hoverOverElement(iconPencil);
        webDriver.findElement(iconPencil).click();
        WebElement editedName = webDriver.findElement(inputEditName);
        editedName.click();
        editedName.clear();
        editedName.sendKeys(name);
        webDriver.findElement(iconCheckMark).click();
    }

    public void editComponentPrice(int index, String price) {
        By textComponentName = By.xpath("//*[@id='app']/div/div/ul/div[" + index + "]/div[2]/span");
        By iconPencil = By.xpath("//*[@id='app']/div/div/ul/div[" + index + "]/div[1]/span");
        By inputEditPrice = By.xpath("//*[@id='app']/div/div/ul/div[" + index + "]/div[3]/input");
        By iconCheckMark = By.xpath("//*[@id='app']/div/div/ul/div[" + index + "]/div[4]/span[2]");

        hoverOverElement(textComponentName);
        hoverOverElement(iconPencil);
        webDriver.findElement(iconPencil).click();
        WebElement editedName = webDriver.findElement(inputEditPrice);
        editedName.click();
        editedName.clear();
        editedName.sendKeys(price);
        webDriver.findElement(iconCheckMark).click();
    }


    public void hoverOverElement(By by) {
        new Actions(webDriver)
                .moveToElement(webDriver.findElement(by))
                .build()
                .perform();
    }

}
