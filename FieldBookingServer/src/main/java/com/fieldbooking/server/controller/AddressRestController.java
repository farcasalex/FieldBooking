package com.fieldbooking.server.controller;

import com.fieldbooking.server.model.entity.Address;
import com.fieldbooking.server.service.implementation.AddressServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rest/address")
public class AddressRestController {

    private final AddressServiceImpl addressService;

    @Autowired
    public AddressRestController(AddressServiceImpl addressService) {
        this.addressService = addressService;
    }

    @GetMapping("/list")
    public List<Address> list() {
        return addressService.findAll();
    }

    @GetMapping("/byId")
    public Optional<Address> byId(Long id) {
        return addressService.findById(id);
    }

    @PostMapping("/save")
    public void save(@RequestBody @Valid Address address, BindingResult result) {
        addressService.save(address);
    }

    @DeleteMapping("/delete")
    public void delete(Long id) {
        addressService.delete(id);
    }
}
