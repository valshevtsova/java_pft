package ru.stqa.pft.m2.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


public class HelperBase  {
    private final Properties properties;
    protected WebDriver wd;

    public HelperBase(WebDriver wd) {
        this.wd = wd;
        properties = new Properties();
    }

    protected void click(By locator) {

        wd.findElement(locator).click();
    }

    public void gotoPage (String urlkey) throws IOException {
        properties.load(new FileReader(new File(String.format("src/test/resources/local.properties"))));
        wd.get(properties.getProperty("web.frontendUrl") + urlkey);
    }

    protected void type(By locator, String text)  {
        click(locator);
        if (text != null) {
            String existingText = wd.findElement(locator).getAttribute("value");
            if (!text.equals(existingText)){
                wd.findElement(locator).clear();
                wd.findElement(locator).sendKeys(text);
            }
        }
    }

    protected void waitForElement(By locator){
        WebDriverWait wait = new WebDriverWait(wd, 60);
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("admin__data-grid-loading-mask")));
    }

    protected void waitAndClick (By locator){
        waitForElement(locator);
        click(locator);
    }

    public boolean isAlertPresent() {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    protected boolean isElementPresent(By locator) {
        try {
            wd.findElement(locator);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    public void selectDropdown(By locator, String text) {
        new Select(wd.findElement(locator)).selectByVisibleText(text);
    }
}
