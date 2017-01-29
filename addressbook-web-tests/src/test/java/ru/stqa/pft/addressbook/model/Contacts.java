package ru.stqa.pft.addressbook.model;

import com.google.common.collect.ForwardingSet;
import java.util.HashSet;
import java.util.Set;

public class Contacts extends ForwardingSet<ContactData> {

    private Set<ContactData> delegate;

    public Contacts(Contacts contacts) {
        this.delegate = new HashSet<ContactData>(contacts.delegate);
    }

    public Contacts() {
        this.delegate = new HashSet<ContactData>();
    }


    @Override
    protected Set<ContactData> delegate() {
        return delegate;
    }

    public Contacts withAdded (ContactData contact) {
        Contacts groups = new Contacts(this);
        groups.add(contact);
        return groups;
    }

    public Contacts without (ContactData contact) {
        Contacts contacts = new Contacts(this);
        contacts.remove(contact);
        return contacts;
    }
}