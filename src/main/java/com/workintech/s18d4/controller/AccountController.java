package com.workintech.s18d4.controller;

import com.workintech.s18d4.dto.AccountResponse;
import com.workintech.s18d4.entity.Account;
import com.workintech.s18d4.entity.Customer;
import com.workintech.s18d4.service.AccountService;
import com.workintech.s18d4.service.CustomerService;
import com.workintech.s18d4.util.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {

    private AccountService accountService;
    private CustomerService customerService;

    @Autowired
    public AccountController(AccountService accountService, CustomerService customerService) {
        this.accountService = accountService;
        this.customerService = customerService;
    }
    @GetMapping("/")
    public List<AccountResponse> getAccounts(){
        return Converter.findAccounts(accountService.getAllAccounts());
    }
    @GetMapping("/{id}")
    public AccountResponse getAccount(@PathVariable long id){
        return Converter.findAccount(accountService.getAccount(id));
    }
    @PostMapping("/{customerID}")
    public AccountResponse addAccount(@RequestBody Account account, @PathVariable long customerID){
        Customer customer = customerService.getCustomer(customerID);
        if(customer != null){
            customer.addAccount(account);
            account.setCustomer(customer);
        //  customerService.saveCustomer(customer) you can save customer as well.
            return Converter.findAccount(accountService.saveAccount(account));
        } else {
            // You can add your custom exception handler or
            throw new RuntimeException("There is no customer!");
        }
    }
    @PutMapping("/{customerID}")
    public AccountResponse updateAccount(@RequestBody Account account, @PathVariable long customerID){
        Customer customer = customerService.getCustomer(customerID);
        if(customer != null){
            for(Account customerAccount : customer.getAccountList()){
                if(customerAccount.getId() == account.getId()){
//                    int index = customer.getAccountList().indexOf(customerAccount);
//                    customer.getAccountList().set(index, account);
                    account.setCustomer(customer);
                    return Converter.findAccount(accountService.saveAccount(account));
                }
            }
            throw new RuntimeException("There is no account to update !");
        }else {
            throw new RuntimeException("There is no customer!");
        }
    }
    @DeleteMapping("/{id}")
    public AccountResponse deleteAccount(@PathVariable long id){
        Account account = accountService.getAccount(id);
        if(account != null){
            return Converter.findAccount(accountService.deleteAccount(id));
        } else {
            throw new RuntimeException("There is no account to delete !");
        }
    }
}
