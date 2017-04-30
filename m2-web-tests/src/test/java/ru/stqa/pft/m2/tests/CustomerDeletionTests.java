package ru.stqa.pft.m2.tests;
import org.testng.annotations.Test;
import ru.stqa.pft.m2.model.CustomerData;

public class CustomerDeletionTests extends TestBase {
    
    @Test
    public void testCustomerDeletion() {
       // app.getNav().closeNoisePopup();
        app.getNav().gotoCustomerGrid();
        if (!app.getCustomerHelper().isAnyCustomerPresent()){
            app.getCustomerHelper().createCustomer(new CustomerData()
                    .withFirstname("Test1")
                    .withLastname("Amasty1")
                    .withEmail("test4@amasty.com"),
                    //.withGender("Male"),
                    true);
        }
        app.getCustomerHelper().selectCustomerInGrid();
        app.getCustomerHelper().initCustomerDeletion();
        app.getCustomerHelper().selectDeleteAction();
        app.getCustomerHelper().initDeleteAction();
    }

}
