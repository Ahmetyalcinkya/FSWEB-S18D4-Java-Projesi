package com.workintech.s18d4.controller;

import com.workintech.s18d4.dto.CustomerResponse;
import com.workintech.s18d4.entity.Customer;
import com.workintech.s18d4.service.CustomerService;
import com.workintech.s18d4.util.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
    @GetMapping("/")
    public List<CustomerResponse> getAllCustomers(){
        return Converter.findCustomers(customerService.getAllCustomers());
    }
    @GetMapping("/{id}")
    public CustomerResponse getCustomer(@PathVariable long id){
        return Converter.findCustomer(customerService.getCustomer(id));
    }
    @PostMapping("/")
    public CustomerResponse addCustomer(@RequestBody Customer customer){
        return Converter.findCustomer(customerService.saveCustomer(customer));
    }
    @PutMapping("/{id}")
    public CustomerResponse updateCustomer(@RequestBody Customer customer, @PathVariable long id){
        Customer foundCustomer = customerService.getCustomer(id);
        if(foundCustomer != null){
            customer.setId(id);
            return Converter.findCustomer(customerService.saveCustomer(foundCustomer));
        }
        //TODO [Ahmet] throw exception
        return null;
    }
    @DeleteMapping("/{id}")
    public CustomerResponse deleteCustomer(@PathVariable long id){
        return Converter.findCustomer(customerService.deleteCustomer(id));
    }
}
