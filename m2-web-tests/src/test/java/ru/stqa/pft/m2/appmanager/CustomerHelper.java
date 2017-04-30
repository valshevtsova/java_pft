package ru.stqa.pft.m2.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.collections.Objects;
import ru.stqa.pft.m2.model.CustomerData;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

/**
 * Created by ruslan on 25.12.2016.
 */
public class CustomerHelper extends HelperBase  {

    public CustomerHelper(WebDriver wd) {
        super(wd);
    }

    public void submitCustomerCreation() {
        click(By.id("save"));
    }

    public void fillCustomerForm(CustomerData customerData, boolean creation) {
        waitForElement(By.name("customer[firstname]"));
        type(By.name("customer[firstname]"), customerData.getFirstname());
        waitForElement(By.name("customer[lastname]"));
        type(By.name("customer[lastname]"), customerData.getLastname());
        waitForElement(By.name("customer[email]"));
        type(By.name("customer[email]"), customerData.getEmail());
/*
        if (creation){
            new Select(wd.findElement(By.name("customer[gender]"))).selectByVisibleText(customerData.getGender());
        } else {
            Assert.assertFalse(isElementPresent(By.name("customer[gender]")));
        }
*/
    }

    public void initCustomerCreation() {
        click(By.id("add"));
        wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public void initDeleteAction() {
        click(By.cssSelector("button.action-primary.action-accept"));
    }

    public void selectDeleteAction() {
        click(By.xpath("//div[@class='col-xs-2']//span[.='Delete']"));
    }

    public void selectCustomerInGrid() {
        waitAndClick(By.xpath("//input[@id='idscheck1']"));
    }

    public void initCustomerDeletion(){
        click(By.xpath("//div[@class='col-xs-2']//button[normalize-space(.)='Actions']"));
    }

    public void initCustomerEdit(int order) {
        int i = order + 1;
        String path = "//div[2]/main/div[2]/div/div/div/div[5]/table/tbody/tr["+i+"]/td[17]/a";
        waitForElement(By.xpath(path));
        click(By.xpath(path));
    }

    public void initCustomerAccountInfoEdit() {
        waitAndClick(By.id("tab_customer"));
    }

    public boolean isAnyCustomerPresent() {
        return isElementPresent(By.xpath("//div[2]/main/div[2]/div/div/div/div[5]/table/tbody/tr[2]/td[17]/a"));
    }

    public void createCustomer(CustomerData customerData, boolean creation) {
        initCustomerCreation();
        fillCustomerForm(customerData, creation);
        submitCustomerCreation();
    }

    public int getCustomerCount() {
        int resultCount = 0;
        String locatorString = "div.admin__control-support-text";
        waitForElement(By.cssSelector(locatorString));

        while (resultCount == 0){
            List<WebElement> counterBlock =  wd.findElements(By.cssSelector(locatorString));
            String innText = counterBlock.get(0).getText();
            innText = innText.substring(0,innText.indexOf(" "));
            resultCount = Integer.parseInt(innText);
        }
        return resultCount;
    }

}
