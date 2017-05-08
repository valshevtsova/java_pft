package ru.stqa.pft.m2.tests;
import org.testng.annotations.Test;
import ru.stqa.pft.m2.model.CustomerData;

public class CustomerEditTests extends TestBase {

    @Test
    public void testCustomerEdit() {
        //app.getNav().closeNoisePopup();
        int a = 10000;
        int randomNumber = (int)(Math.random() * a);

        app.getNav().gotoCustomerGrid();
        if (!app.getCustomerHelper().isAnyCustomerPresent()){
            app.getCustomerHelper().createCustomer(new CustomerData()
                            .withFirstname("Test1")
                            .withLastname("Amasty1")
                            .withEmail("test"+randomNumber+"@7.com"),
                            //.withGender("Male"),
                    true);
        }
        app.getCustomerHelper().initCustomerEdit(2);
        app.getCustomerHelper().initCustomerAccountInfoEdit();
        app.getCustomerHelper().fillCustomerForm(new CustomerData()
                .withFirstname("TestEdit")
                .withLastname("AmastyEdit")
                .withEmail("help7M66e@amasty.com"),
                false);
        app.getCustomerHelper().submitCustomerCreation();
    }
}
