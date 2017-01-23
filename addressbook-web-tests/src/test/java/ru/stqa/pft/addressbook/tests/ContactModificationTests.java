package ru.stqa.pft.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

    @Test (enabled = false)
    public void TestContactModification() {
        app.goTo().gotoHomePage();
        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("Ivan", "Ivanov", "ivan", "+375172020327", "ivan@biosistema.com", "test1"), true);
        }
        List<ContactData> before = app.getContactHelper().getList();
        app.getContactHelper().initContactModification(before.size() + 1);
        ContactData contact = new ContactData(before.get(before.size() - 1).getId(), "Ivan1", "Ivanov1", "ivan", "+375172020327", "1ivan@biosistema.com", null);
        app.getContactHelper().fillContactForm(contact, false);
        app.getContactHelper().submitContactModification();
        app.goTo().gotoHomePage();
        List<ContactData> after = app.getContactHelper().getList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(before.size() - 1);
        before.add(contact);
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }

}
