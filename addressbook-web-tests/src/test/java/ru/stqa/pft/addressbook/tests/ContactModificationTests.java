package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTests extends TestBase {

    @BeforeMethod

    public void ensurePreconditions () {
        app.goTo().gotoHomePage();
        if (! app.contact().isThereAContact()) {
            app.contact().create(new ContactData()
                    .withFirstname("Ivan").withLastname("Ivanov").withNickname("ivan").withHomephone("+375172020327")
                    .withEmail("ivan@biosistema.com").withGroup("test1"), true);
        }
    }

    @Test
    public void TestContactModification() {

       /* List<ContactData> before = app.getContactHelper().getList();
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
        Assert.assertEquals(before, after); */


        Contacts before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData()
                .withFirstname("Ivan").withLastname("Ivanov").withNickname("ivan").withHomephone("+375172020327")
                .withEmail("ivan@biosistema.com").withId(modifiedContact.getId()).withIndex(modifiedContact.getIndex());

        app.contact().modify(contact, false);
        app.goTo().gotoHomePage();
        Contacts after = app.contact().all();
        assertEquals(after.size(), before.size());

        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    }

}
