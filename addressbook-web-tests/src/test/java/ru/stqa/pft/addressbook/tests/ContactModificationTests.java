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
        if (app.db().contacts().size() == 0){
            app.goTo().gotoHomePage();
            app.contact().create(new ContactData()
                    .withFirstname("Ivan").withLastname("Ivanov").withNickname("ivan").withHomephone("+375172020327")
                    .withEmail("ivan@biosistema.com").withGroup("test1"), true);
        }
    }

    @Test
    public void TestContactModification() {
        Contacts before = app.db().contacts();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData()
                .withFirstname("Ivan").withLastname("Ivanov").withNickname("ivan").withHomephone("+375172020327")
                .withEmail("ivan@biosistema.com").withId(modifiedContact.getId());
        app.goTo().gotoHomePage();
        app.contact().modify(contact, false);
        app.goTo().gotoHomePage();
        assertThat(app.contact().count(), equalTo(before.size()));
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    }

}
