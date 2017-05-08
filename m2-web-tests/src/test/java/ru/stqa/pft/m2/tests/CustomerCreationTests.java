package ru.stqa.pft.m2.tests;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.m2.model.CustomerData;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class CustomerCreationTests extends TestBase {

    @Test
    public void testCustomerCreation() {

        //app.getNav().closeNoisePopup();

        app.getNav().gotoCustomerGrid();
        int before = app.getCustomerHelper().getCustomerCount();
        int a = 10000;
        int randomNumber = (int)(Math.random() * a);
        app.getCustomerHelper().createCustomer(
                new CustomerData()
                        .withEmail("Test"+randomNumber+"@gmail.by")
                        .withFirstname("Tester")
                        .withLastname("Customer"),
                        //.withGender("Male"),
                true
        );
        int after = app.getCustomerHelper().getCustomerCount();
        assertThat(after, equalTo(before + 1));
    }

}
