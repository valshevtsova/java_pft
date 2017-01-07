package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {

        app.getContactHelper().createContact(new ContactData("Ivan", "Ivanov", "ivan", "+375172020327", "ivan@biosistema.com", "test1"), true);
    }


}
