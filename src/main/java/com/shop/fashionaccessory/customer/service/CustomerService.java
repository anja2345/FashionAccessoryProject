package com.shop.fashionaccessory.customer.service;

import com.shop.fashionaccessory.customer.Customer;
import com.shop.fashionaccessory.service.BaseService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

 class CustomerService extends BaseService<Customer> implements CustomerServiceLocal {

    public CustomerService() {
        super(Customer.class);
    }

    @Override
    public ObservableList<Customer> loadCustomers() {
        List<Customer> customerList=findAll();
        return FXCollections.observableList(customerList);

    }
}
