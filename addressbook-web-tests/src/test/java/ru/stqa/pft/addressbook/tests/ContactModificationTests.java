package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

    @Test
    public void TestContactModification() {

        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm(new ContactData("Ivan", "Ivanov", "ivan", "+375172020327", "1ivan@biosistema.com"));
        app.getContactHelper().submitContactModification();
    }

}
