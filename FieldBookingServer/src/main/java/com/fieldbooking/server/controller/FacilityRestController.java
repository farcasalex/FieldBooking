package com.fieldbooking.server.controller;


import com.fieldbooking.server.model.entity.Facility;
import com.fieldbooking.server.service.implementation.FacilityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rest/facility")
public class FacilityRestController {

    private final FacilityServiceImpl facilityService;

    @Autowired
    public FacilityRestController(FacilityServiceImpl facilityService) {
        this.facilityService = facilityService;
    }

    @GetMapping("/list")
    public List<Facility> list() {
        return facilityService.findAll();
    }

    @GetMapping("/byId")
    public Optional<Facility> byId(Long id) {
        return facilityService.findById(id);
    }

    @PostMapping("/save")
    public void save(@RequestBody @Valid Facility facility, BindingResult result) {
        facilityService.save(facility);
    }

    @DeleteMapping("/delete")
    public void delete(Long id) {
        facilityService.delete(id);
    }

    @GetMapping("/byAddress")
    public List<Facility> searchByAddress(String address) {
        return facilityService.searchByAddress(address);
    }
}
