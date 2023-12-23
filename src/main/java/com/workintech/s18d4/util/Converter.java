package com.workintech.s18d4.util;

import com.workintech.s18d4.dto.AccountResponse;
import com.workintech.s18d4.dto.AddressResponse;
import com.workintech.s18d4.dto.CustomerResponse;
import com.workintech.s18d4.entity.Account;
import com.workintech.s18d4.entity.Address;
import com.workintech.s18d4.entity.Customer;

import java.util.ArrayList;
import java.util.List;

public class Converter {

    public static List<CustomerResponse> findCustomers(List<Customer> customers){
        List<CustomerResponse> customerResponseList = new ArrayList<>();

        for(Customer customer: customers){
            customerResponseList.add(new CustomerResponse(customer.getId(), customer.getFirstName(),
                    customer.getLastName(), customer.getEmail()));
        }
        return customerResponseList;
    }
    public static CustomerResponse findCustomer(Customer customer){
        return new CustomerResponse(customer.getId(), customer.getFirstName(), customer.getLastName(), customer.getEmail());
    }

    public static List<AddressResponse> findAddresses(List<Address> addresses){
        List<AddressResponse> addressResponseList = new ArrayList<>();

        for(Address address: addresses){
            addressResponseList.add(new AddressResponse(address.getCountry(), address.getCity(),
                    address.getStreet(), address.getNo(), address.getDescription()));
        }
        return addressResponseList;
    }
    public static AddressResponse findAddress(Address address){
        return new AddressResponse(address.getCountry(), address.getCity(), address.getStreet(), address.getNo(), address.getDescription());
    }

    public static List<AccountResponse> findAccounts(List<Account> accounts){
        List<AccountResponse> accountResponsesList = new ArrayList<>();

        for(Account account: accounts){
            accountResponsesList.add(new AccountResponse(account.getAccountName(), account.getMoneyAmount(),
                    new CustomerResponse(account.getCustomer().getId(), account.getCustomer().getFirstName(),
                            account.getCustomer().getLastName(), account.getCustomer().getEmail())));
        }
        return accountResponsesList;
    }
    public static AccountResponse findAccount(Account account){
        return new AccountResponse(account.getAccountName(), account.getMoneyAmount(),
                new CustomerResponse(account.getCustomer().getId(), account.getCustomer().getFirstName(),
                            account.getCustomer().getLastName(), account.getCustomer().getEmail()));
    }
}
