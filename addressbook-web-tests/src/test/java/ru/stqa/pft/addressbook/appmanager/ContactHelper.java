package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void initContactCreation() {
        click(By.linkText("add new"));
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("nickname"), contactData.getNickname());
        type(By.name("home"), contactData.getHomephone());
        type(By.name("email"), contactData.getEmail());
        type(By.name("address"), contactData.getAddress());
        attach(By.name("photo"), contactData.getPhoto());

        if(creation) {
            if (contactData.getGroups().size() > 0 ){
                Assert.assertTrue(contactData.getGroups().size() == 1);
                new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroups().iterator().next().getName());
            } else {
                Assert.assertTrue(isElementPresent(By.name("new_group")));
            }
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void submitContactForm() {
        click(By.name("submit"));
    }

    public void submitAddContactToGroup() {
        click(By.name("add"));
    }

    public void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[value = '"+ id  +"']")).click();
    }

    public void deleteSelectedContacts() {
        click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
    }

    public void initContactModification(int index) {
        click(By.xpath("//table[@id='maintable']/tbody/tr["+ index +"]/td[8]/a/img"));
    }

    public void submitContactModification() {
        click(By.name("update"));
    }

    public void create(ContactData contact, boolean creation) {
        initContactCreation();
        fillContactForm(contact, creation);
        submitContactForm();
        contactCache = null;
    }

    public void modify(ContactData contact, boolean creation) {
        //initContactModification(contact.getIndex());
        initContactModificationById(contact.getId());
        fillContactForm(contact, false);
        submitContactModification();
        contactCache = null;
    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        deleteSelectedContacts();
        contactCache = null;
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public int count() {
        return wd.findElements(By.name("entry")).size();
    }

    private Contacts contactCache = null;

    public Contacts all() {
        if (contactCache != null) {
            return new Contacts(contactCache);
        }
        contactCache = new Contacts();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        int index = 2;
        for (WebElement element : elements) {
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            String firstname = element.findElement(By.xpath("//tr["+ index +"]/td[3]")).getText();
            String lastname = element.findElement(By.xpath("//tr["+ index +"]/td[2]")).getText();
            String allPhones = element.findElement(By.xpath("//tr["+ index +"]/td[6]")).getText();
            String allEmails = element.findElement(By.xpath("//tr["+ index +"]/td[5]")).getText();
            String address = element.findElement(By.xpath("//tr["+ index +"]/td[4]")).getText();
            contactCache.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname).withIndex(index)
                    .withAllPhones(allPhones).withAllEmails(allEmails).withAddress(address));
            index++;
        }
        return new Contacts(contactCache);
    }

    public ContactData infoFromEditForm(ContactData contact){
        initContactModificationById(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");
        wd.navigate().back();
        return  new ContactData().withId(contact.getId()).withFirstname(firstname).withLastname(lastname)
                .withHomephone(home).withMobilephone(mobile).withWorkphone(work).withEmail(email).withEmail2(email2).withtEmail3(email3).withAddress(address);
    }

    public void addContactToGroup(ContactData contact, GroupData group) {
        selectContactById(contact.getId());
        new Select(wd.findElement(By.name("to_group"))).selectByVisibleText(group.getName());
        submitAddContactToGroup();

    }

    public ContactData infoFromViewPage(ContactData contact) {
        initContactViewById(contact.getId());
        String allInfo = wd.findElement(By.id("content")).getText();
        return  new ContactData().withAllInfo(allInfo);
    }

    private void initContactViewById(int id) {
        wd.findElement(By.xpath(String.format("//input[@value='%s']/../../td[7]/a",id))).click();
    }

    private void initContactModificationById (int id){
        wd.findElement(By.xpath(String.format("//input[@value='%s']/../../td[8]/a",id))).click();
    }

}
