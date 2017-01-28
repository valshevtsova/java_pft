package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.Comparator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {

        app.goTo().gotoHomePage();
        Contacts before = app.contact().all();
        ContactData contact = new ContactData().withFirstname("Ivan1").withLastname("Ivanov1").withNickname("ivan")
                .withHomephone("+375172020327").withEmail("1ivan@biosistema.com");
        app.contact().create(contact, true);
        app.goTo().gotoHomePage();
        Contacts after = app.contact().all();
        assertThat(after.size(), equalTo(before.size() + 1));
        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }


}
