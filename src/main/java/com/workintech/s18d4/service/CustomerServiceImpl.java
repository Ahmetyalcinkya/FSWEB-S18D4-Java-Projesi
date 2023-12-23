package com.workintech.s18d4.service;

import com.workintech.s18d4.entity.Customer;
import com.workintech.s18d4.exceptions.CustomerException;
import com.workintech.s18d4.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomer(long id) {
        Optional<Customer> optionalCustomer =customerRepository.findById(id);
        if(optionalCustomer.isPresent()){
            return optionalCustomer.get();
        }
        throw new CustomerException("The customer not found.", HttpStatus.NOT_FOUND);
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer deleteCustomer(long id) {
        Customer customer = getCustomer(id);
        if (customer != null) {
            customerRepository.delete(customer);
            return customer;
        }
        throw new CustomerException("The customer not found.", HttpStatus.NOT_FOUND);
    }
}
