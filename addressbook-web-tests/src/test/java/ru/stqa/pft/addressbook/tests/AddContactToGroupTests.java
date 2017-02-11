package ru.stqa.pft.addressbook.tests;


import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddContactToGroupTests extends TestBase {


    @BeforeMethod
    public void ensurePreconditions () {
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test1"));
        }
        if (app.db().contacts().size() == 0){
            app.goTo().gotoHomePage();
            app.contact().create(new ContactData()
                    .withFirstname("Ivan").withLastname("Ivanov").withNickname("ivan").withHomephone("+375172020327")
                    .withEmail("ivan@biosistema.com"), true);
        }
    }

    @Test
    public void testAddContactToGroup() {
        Groups groups = app.db().groups();
        GroupData group = groups.iterator().next();
        Contacts contacts = app.db().contacts();
        ContactData contact = contacts.iterator().next();
        app.goTo().gotoHomePage();
        app.contact().addContactToGroup(contact, group);
        Contacts afterContacts = app.db().contacts();
        //contact.inGroup(group);
        assertThat(contacts, equalTo(afterContacts));
        System.out.println("WWW");
    }
}
