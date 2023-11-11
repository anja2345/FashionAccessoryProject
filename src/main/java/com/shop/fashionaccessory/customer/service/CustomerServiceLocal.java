package com.shop.fashionaccessory.customer.service;

import com.shop.fashionaccessory.customer.Customer;
import javafx.collections.ObservableList;

import java.util.List;

public interface CustomerServiceLocal {

    CustomerServiceLocal SERVICE = new CustomerService();

    void create(Customer customer);

    void edit(Customer customer);

    void remove(Customer customer);

    void remove(Integer id);

    Customer find(Integer id);

    List<Customer> findAll();
    ObservableList<Customer> loadCustomers();
}
