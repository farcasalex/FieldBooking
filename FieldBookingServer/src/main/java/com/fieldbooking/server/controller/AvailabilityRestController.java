package com.fieldbooking.server.controller;

import com.fieldbooking.server.model.entity.Availability;
import com.fieldbooking.server.service.implementation.AvailabilityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rest/availability")
public class AvailabilityRestController {

    private final AvailabilityServiceImpl availabilityService;

    @Autowired
    public AvailabilityRestController(AvailabilityServiceImpl availabilityService) {
        this.availabilityService = availabilityService;
    }

    @GetMapping("/list")
    public List<Availability> list() {
        return availabilityService.findAll();
    }

    @GetMapping("/byId")
    public Optional<Availability> byId(Long id) {
        return availabilityService.findById(id);
    }

    @PostMapping("/save")
    public Availability save(@RequestBody @Valid Availability availability, BindingResult result) {
        return availabilityService.save(availability);
    }

    @PostMapping("/delete")
    public void delete(@RequestBody @Valid Long id) {
        availabilityService.delete(id);
    }

    @GetMapping("/byFacilityId")
    public List<Availability> byFacilityId(Long id) {
        return availabilityService.findAllByFieldFacilityId(id);
    }
}
