package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import java.util.Arrays;
import java.util.stream.Collectors;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddressTests extends TestBase {

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
    public void  testContactAddress() {
        app.goTo().gotoHomePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getAddress(), equalTo(mergeAddress(contactInfoFromEditForm)));
    }

    private String mergeAddress(ContactData contact) {
        return Arrays.asList(contact.getAddress().split("\n"))
                .stream()
                .map(ContactAddressTests::cleaned)
                .collect(Collectors.joining("\n"));
    }

    public static String cleaned (String address) {
        return address.trim();
    }
}
