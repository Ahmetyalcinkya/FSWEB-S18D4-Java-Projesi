package com.workintech.s18d4.service;

import com.workintech.s18d4.entity.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> getAllCustomers();
    Customer getCustomer(long id);
    Customer saveCustomer(Customer customer);
    Customer deleteCustomer(long id);
}
