package com.fieldbooking.server.controller;

import com.fieldbooking.server.model.entity.Field;
import com.fieldbooking.server.service.implementation.FieldServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rest/field")
public class FieldRestController {

    private final FieldServiceImpl fieldService;

    @Autowired
    public FieldRestController(FieldServiceImpl fieldService) {
        this.fieldService = fieldService;
    }

    @GetMapping("/list")
    public List<Field> list() {
        return fieldService.findAll();
    }

    @GetMapping("/byId")
    public Optional<Field> byId(Long id) {
        return fieldService.findById(id);
    }

    @PostMapping("/save")
    public void save(@RequestBody @Valid Field field, BindingResult result) {
        fieldService.save(field);
    }

    @DeleteMapping("/delete")
    public void delete(Long id) {
        fieldService.delete(id);
    }

    @GetMapping("/byAddress")
    public List<Field> searchByAddress(String address) {
        return fieldService.searchByAddress(address);
    }

    @GetMapping("/byFacilityId")
    public List<Field> byFacilityId(Long id) {
        return fieldService.findByFacilityId(id);
    }
}
