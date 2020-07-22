package com.fieldbooking.server.controller;

import com.fieldbooking.server.model.entity.Reservation;
import com.fieldbooking.server.service.implementation.ReservationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rest/reservation")
public class ReservationRestController {

    private final ReservationServiceImpl reservationService;

    @Autowired
    public ReservationRestController(ReservationServiceImpl reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/list")
    public List<Reservation> list() {
        return reservationService.findAll();
    }

    @GetMapping("/byId")
    public Optional<Reservation> byId(Long id) {
        return reservationService.findById(id);
    }

    @PostMapping("/save")
    public void save(@RequestBody @Valid Reservation reservation, BindingResult result) {
        reservationService.save(reservation);
    }

    @DeleteMapping("/delete")
    public void delete(Long id) {
        reservationService.delete(id);
    }

    @GetMapping("/byUserId")
    public List<Reservation> byUserId(Long id) {
        return reservationService.findByUserId(id);
    }
}
