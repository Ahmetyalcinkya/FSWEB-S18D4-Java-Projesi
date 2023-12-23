package com.workintech.s18d4.controller;

import com.workintech.s18d4.dto.AddressResponse;
import com.workintech.s18d4.entity.Address;
import com.workintech.s18d4.exceptions.CustomerException;
import com.workintech.s18d4.service.AddressService;
import com.workintech.s18d4.util.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {

    private AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping("/")
    public List<AddressResponse> getAll(){
        return Converter.findAddresses(addressService.getAllAddresses());
    }
    @GetMapping("/{id}")
    public AddressResponse getAddress(@PathVariable long id){
        return Converter.findAddress(addressService.getAddress(id));
    }
    @PostMapping("/")
    public AddressResponse addAddress(@RequestBody Address address){
        return Converter.findAddress(addressService.saveAddress(address));
    }
    @PutMapping("/{id}")
    public AddressResponse updateAddress(@RequestBody Address address, @PathVariable long id){
        Address foundAddress = addressService.getAddress(id);
        if(foundAddress != null){
            address.setId(id);
            return Converter.findAddress(addressService.saveAddress(foundAddress));
        }

        throw new CustomerException("The address not found.", HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/{id}")
    public AddressResponse deleteAddress(@PathVariable long id){
        return Converter.findAddress(addressService.deleteAddress(id));
    }
}
