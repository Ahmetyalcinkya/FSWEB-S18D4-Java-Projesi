package com.workintech.s18d4.service;

import com.workintech.s18d4.entity.Address;

import java.util.List;

public interface AddressService {
    List<Address> getAllAddresses();
    Address getAddress(long id);
    Address saveAddress(Address address); // save ve update aynı method içerisinde bulunuyor.
    Address deleteAddress(long id);
}
