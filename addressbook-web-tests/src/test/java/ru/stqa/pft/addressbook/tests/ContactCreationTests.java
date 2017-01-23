package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

    @Test (enabled = false)
    public void testContactCreation() {

        app.goTo().gotoHomePage();
        List<ContactData> before = app.getContactHelper().getList();
        ContactData contact = new ContactData("Ivan1", "Ivanov1", "ivan", "+375172020327", "1ivan@biosistema.com", null);
        app.getContactHelper().createContact(contact, true);
        app.goTo().gotoHomePage();
        List<ContactData> after = app.getContactHelper().getList();
        Assert.assertEquals(after.size(), before.size() + 1);

        before.add(contact);
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }


}
