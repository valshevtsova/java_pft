package ru.stqa.pft.m2.model;

import com.google.common.collect.ForwardingSet;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by ruslan on 28.01.2017.
 */
public class Customers extends ForwardingSet<CustomerData> {

    private Set<CustomerData> delegate;

    public Customers(Customers customers) {
        this.delegate = new HashSet<CustomerData>(customers.delegate);
    }

    @Override
    protected Set<CustomerData> delegate() {
        return delegate;
    }

    public Customers withAdded(CustomerData customer){
        Customers customers = new Customers(this);
        customers.add(customer);
        return customers;
    }

    public Customers without(CustomerData customer){
        Customers customers = new Customers(this);
        customers.remove(customer);
        return customers;
    }
}
