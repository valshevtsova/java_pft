package ru.stqa.pft.addressbook.tests;


import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.By;
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
    public void testAddContactToGroup() {
        Boolean isContactInGroup = false;
        GroupData currentGroup = null;
        ContactData currentContact = null;

        Groups groups = app.db().groups();
        Contacts contacts = app.db().contacts();

        for (GroupData group : groups) {
            currentGroup = group;
            for (ContactData contact : contacts) {
                currentContact = contact;
                Groups groupsForContact = contact.getGroups();
                if (groupsForContact.size() != 0) {
                    for (GroupData groupForContact : groupsForContact) {
                        if (groupForContact.getId() == group.getId()) {
                            isContactInGroup = true;
                            break;
                        } else {
                            isContactInGroup = false;
                        }
                    }
                } else {
                    isContactInGroup = false;
                }

                if (isContactInGroup == false) {
                    break;
                }
            }
            if (isContactInGroup == false) {
                break;
            }
        }

        if (isContactInGroup == true) {
            long now = System.currentTimeMillis();
            GroupData newGroup = new GroupData().withName(String.format("test%s",now))
                    .withHeader(String.format("header%s",now)).withFooter(String.format("footer%s",now));
            app.goTo().groupPage();
            app.group().create(newGroup);

            Groups allGroups = app.db().groups();
            int lastGroupId = allGroups.stream().mapToInt((c) -> c.getId()).max().getAsInt();
            currentGroup = newGroup.withId(lastGroupId);
        }

        app.goTo().gotoHomePage();
        app.contact().addContactToGroup(currentContact, currentGroup);
        app.goTo().groupPageById(currentGroup.getId());

        Contacts afterContacts = app.db().contacts();

        contacts.remove(currentContact);
        currentContact.inGroup(currentGroup);
        contacts.add(currentContact);
        assertThat(contacts, equalTo(afterContacts));
    }
}
