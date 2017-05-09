package ru.stqa.pft.m2.appmanager;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class ILNHelper extends HelperBase {

    public ILNHelper(WebDriver wd) {
        super(wd);
    }

    public void gotoAttributeByName(String attributeName) {
        type(By.name("attribute_code"), attributeName);
        click(By.xpath("//div[2]/main/div[2]/div/div/div/div[1]/div[2]/div[1]/button[1]"));
        click(By.xpath("//table[@id='attributeGrid_table']/tbody/tr/td[2]"));
    }

    public void setAttributeAsMultiselect(String mode) {
        click(By.id("product_attribute_tabs_front"));
        selectDropdown(By.id("is_filterable"), "Filterable (with results)");

       click(By.xpath("//a[@id='product_attribute_tabs_amasty_shopby']//span[.='Improved Layered Navigation']"));
       selectDropdown(By.id("is_multiselect"), "Yes");
       selectDropdown(By.id("block_position"), "Sidebar");
       selectDropdown(By.id("display_mode"), mode);

       click(By.xpath("//button[@id='save']"));
    }

    public void gotoAllProductsPage() throws IOException {
        gotoPage("all-products");
    }
}
