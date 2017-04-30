package ru.stqa.pft.m2.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class NavigationHelper extends HelperBase{

    public NavigationHelper(WebDriver wd) {
        super(wd);
    }


    public void gotoCustomerGrid() {
        click(By.linkText("CUSTOMERS"));
        waitAndClick(By.linkText("All Customers"));
    }

    public void closeNoisePopup() {
        By locator = By.cssSelector("button.action-close");
        if (isElementPresent(locator))
            click(locator);
    }

}
