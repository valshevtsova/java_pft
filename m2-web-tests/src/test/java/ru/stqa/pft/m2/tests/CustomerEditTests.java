package ru.stqa.pft.m2.tests;
import org.testng.annotations.Test;
import ru.stqa.pft.m2.model.CustomerData;

public class CustomerEditTests extends TestBase {

    @Test
    public void testCustomerEdit() {
        //app.getNav().closeNoisePopup();
        app.getNav().gotoCustomerGrid();
        if (!app.getCustomerHelper().isAnyCustomerPresent()){
            app.getCustomerHelper().createCustomer(new CustomerData()
                            .withFirstname("Test1")
                            .withLastname("Amasty1")
                            .withEmail("test467@7.com"),
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
