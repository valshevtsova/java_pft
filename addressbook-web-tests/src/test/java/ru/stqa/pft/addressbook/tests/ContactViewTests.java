package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import java.util.Arrays;
import java.util.stream.Collectors;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactViewTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions () {
        app.goTo().gotoHomePage();
        if (! app.contact().isThereAContact()) {
            app.contact().create(new ContactData()
                    .withFirstname("Ivan").withLastname("Ivanov").withNickname("ivan").withHomephone("+375172020327")
                    .withEmail("ivan@biosistema.com"), true);
        }
    }

    @Test
    public void  testContactView() {
        app.goTo().gotoHomePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
        ContactData contactInfoFromViewPage = app.contact().infoFromViewPage(contact);
        //assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
        assertThat(mergeInfo(contactInfoFromEditForm), equalTo(contactInfoFromViewPage.getAllInfo()));
    }

    private String mergeInfo(ContactData contactInfoFromEditForm) {
        String lastPlusFirstName = contactInfoFromEditForm.getFirstname() + ' ' + contactInfoFromEditForm.getLastname();
        String trimAddressData = Arrays.asList(contactInfoFromEditForm.getAddress().split("\n"))
                .stream()
                .map(ContactAddressTests::cleaned)
                .collect(Collectors.joining("\n"));

        String glueInfo =  Arrays.asList(
                lastPlusFirstName.trim(),
                trimAddressData,
                "\n",
                addPrefix(contactInfoFromEditForm.getHomephone(),"H:"),
                addPrefix(contactInfoFromEditForm.getMobilephone(),"M:"),
                addPrefix(contactInfoFromEditForm.getWorkphone(),"W:"),
                "\n",
                contactInfoFromEditForm.getEmail().trim(),
                contactInfoFromEditForm.getEmail2().trim(),
                contactInfoFromEditForm.getEmail3().trim())
                .stream().filter((s) -> ! s.equals(""))
                .collect(Collectors.joining("\n"));
        return glueInfo.replace("\n\n\n", "\n\n");
    }

    private String addPrefix(String phone, String prefix) {

        return prefix + ' ' + phone;
    }

    public static String cleaned (String address) {
        return address.trim();
    }
}
