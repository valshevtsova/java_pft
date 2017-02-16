package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "addressbook")
public class ContactData {
    @Expose
    @Column(name = "firstname")
    private String firstname;

    @Expose
    @Column(name = "lastname")
    private String lastname;

    @Expose
    @Column(name="nickname")
    private String nickname;

    @Expose
    @Column(name = "home")
    @Type(type = "text")
    private String homephone;

    @Column(name = "work")
    @Type(type = "text")
    private String workphone;

    @Column(name = "mobile")
    @Type(type = "text")
    private String mobilephone;

    @Transient
    private String allPhones;

    @Transient
    private String allEmails;

    @Expose
    @Type(type = "text")
    private String email;

    @Type(type = "text")
    private String email2;

    @Type(type = "text")
    private String email3;

    @Expose
    @Type(type = "text")
    private String address;

    @Transient
    private String allInfo;

    @Id
    @Column(name = "id")
    private int id = Integer.MAX_VALUE;

    @Transient
    private int index;

    @Column(name="photo")
    @Type(type = "text")
    private String photo;

    @ManyToMany (fetch = FetchType.EAGER)
    @JoinTable(name = "address_in_groups",
            joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "group_id"))

    private Set<GroupData> groups = new HashSet<GroupData>();

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getNickname() {
        return nickname;
    }

    public String getHomephone() {
        return homephone;
    }

    public String getAllPhones() {
        return allPhones;
    }

    public String getAllEmails() {
        return allEmails;
    }

    public String getEmail() {
        return email;
    }

    public String getEmail2() {
        return email2;
    }

    public String getEmail3() {
        return email3;
    }

    public String getAddress() {
        return address;
    }

    public String getAllInfo() {
        return allInfo;
    }

    public int getId() {
        return id;
    }

    public int getIndex() {
        return index;
    }

    public String getWorkphone() {
        return workphone;
    }

    public String getMobilephone() {
        return mobilephone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        if (id != that.id) return false;
        if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
        if (lastname != null ? !lastname.equals(that.lastname) : that.lastname != null) return false;
        if (homephone != null ? !homephone.equals(that.homephone) : that.homephone != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        return groups != null ? groups.equals(that.groups) : that.groups == null;
    }

    @Override
    public int hashCode() {
        int result = firstname != null ? firstname.hashCode() : 0;
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        result = 31 * result + (homephone != null ? homephone.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + id;
        result = 31 * result + (groups != null ? groups.hashCode() : 0);
        return result;
    }

    public File getPhoto() {
        if (photo == null)
            return null;

        return new File(photo);
    }

    public Groups getGroups() {
        return new Groups(groups);
    }

    public ContactData withId(int id) {
        this.id = id;
        return this;
    }

    public ContactData withIndex(int index) {
        this.index = index;
        return this;
    }

    public ContactData withFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public ContactData withLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public ContactData withNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public ContactData withHomephone(String homephone) {
        this.homephone = homephone;
        return this;
    }

    public ContactData withWorkphone(String workphone) {
        this.workphone = workphone;
        return this;
    }

    public ContactData withMobilephone(String mobilephone) {
        this.mobilephone = mobilephone;
        return this;
    }

    public ContactData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return  this;
    }

    public ContactData withAllEmails(String allEmails) {
        this.allEmails = allEmails;
        return  this;
    }

    public ContactData withEmail(String email) {
        this.email = email;
        return this;
    }

    public ContactData withEmail2(String email2) {
        this.email2 = email2;
        return this;
    }

    public ContactData withtEmail3(String email3) {
        this.email3 = email3;
        return this;
    }

    public ContactData withAddress(String address) {
        this.address = address;
        return this;
    }

    public ContactData withAllInfo(String allInfo) {
        this.allInfo = allInfo;
        return this;
    }

    public ContactData withPhoto(File photo) {
        this.photo = photo.getPath();
        return this;
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", id=" + id +

                '}';
    }

    public ContactData inGroup(GroupData group) {
        groups.add(group);
        return this;
    }

    public ContactData withoutGroup(GroupData group) {
        groups.remove(group);
        return this;
    }
}
