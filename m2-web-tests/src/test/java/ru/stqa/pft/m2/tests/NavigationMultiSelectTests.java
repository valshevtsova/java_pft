package ru.stqa.pft.m2.tests;


import org.testng.annotations.Test;

import java.io.IOException;

public class NavigationMultiSelectTests extends TestBase {

    @Test
    public void testNavigationMultiSelectLabels() throws IOException {

        app.getNav().gotoProductAttributesGrid();
        app.getIlnHelper().gotoAttributeByName("activity");
        app.getIlnHelper().setAttributeAsMultiselect("Labels");
        app.getIlnHelper().gotoAllProductsPage();

        System.out.println("wuuw");


    }
}
