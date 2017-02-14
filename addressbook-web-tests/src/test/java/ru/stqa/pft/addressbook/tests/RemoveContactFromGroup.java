package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class RemoveContactFromGroup extends TestBase {

    @BeforeMethod
    public void ensurePreconditions () {
        long now = System.currentTimeMillis();
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName(String.format("test%s",now)));
        }
        if (app.db().contacts().size() == 0){
            app.goTo().gotoHomePage();
            app.contact().create(new ContactData()
                    .withFirstname("Ivan").withLastname("Ivanov").withNickname("ivan").withHomephone("+375172020327")
                    .withEmail("ivan@biosistema.com"), true);
        }
    }

    @Test
    public void testRemoveContactFromGroup() {
        Boolean isContactInGroup = false;
        GroupData currentGroup = null;
        ContactData currentContact = null;

        Groups groups = app.db().groups();
        Contacts contacts = app.db().contacts();

        for (ContactData contact : contacts) {
                Groups groupsForContact = contact.getGroups();
                if (groupsForContact.size() != 0) {
                    currentContact = contact;
                    currentGroup = groupsForContact.iterator().next();
                }
        }

        if (currentGroup == null){
            currentGroup = groups.iterator().next();
            currentContact = contacts.iterator().next();
            app.goTo().gotoHomePage();
            app.contact().addContactToGroup(currentContact, currentGroup);
        }

        app.goTo().groupPageById(currentGroup.getId());
        app.contact().removeContactFromGroup(currentContact);
        app.goTo().groupPageById(currentGroup.getId());

        Contacts afterContacts = app.db().contacts();

        contacts.remove(currentContact);
        currentContact.withoutGroup(currentGroup);
        contacts.add(currentContact);
        assertThat(contacts, equalTo(afterContacts));

    }

}