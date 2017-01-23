package ru.stqa.pft.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {

    @Test (enabled = false)
    public void testGroupDeletion() {

        app.goTo().gotoHomePage();
        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("Ivan", "Ivanov", "ivan", "+375172020327", "ivan@biosistema.com", "test1"), true);
        }
        List<ContactData> before = app.getContactHelper().getList();
        app.getContactHelper().selectContact(before.size() - 1);
        app.getContactHelper().deleteSelectedContacts();
        app.goTo().acceptPopup();
        app.goTo().gotoHomePage();
        List<ContactData> after = app.getContactHelper().getList();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(before.size() - 1);
        Assert.assertEquals(before, after);
    }
}
