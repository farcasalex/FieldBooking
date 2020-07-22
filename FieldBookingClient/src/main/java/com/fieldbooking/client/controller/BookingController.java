package com.fieldbooking.client.controller;

import com.fieldbooking.client.entity.Availability;
import com.fieldbooking.client.entity.Reservation;
import com.fieldbooking.client.gateway.impl.AvailabilityGatewayImpl;
import com.fieldbooking.client.gateway.impl.ReservationGatewayImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/booking")
public class BookingController {
    private final AvailabilityGatewayImpl availabilityGateway;
    private final ReservationGatewayImpl reservationGateway;

    @Autowired
    public BookingController(AvailabilityGatewayImpl availabilityGateway, ReservationGatewayImpl reservationGateway) {
        this.availabilityGateway = availabilityGateway;
        this.reservationGateway = reservationGateway;
    }

    @GetMapping("/availabilities")
    public ModelAndView getBooking(ModelAndView mav, @ModelAttribute("reservation") Reservation reservation) {
        List<Availability> availabilities = availabilityGateway.findAll();
        mav.addObject("availabilities", availabilities);
        mav.setViewName("book");
        return mav;
    }

    @PostMapping("/new")
    public ModelAndView addField(@ModelAttribute("reservation") Reservation reservation)  {
        availabilityGateway.delete(reservation.getId());
        reservation.setId(null);
        reservationGateway.save(reservation);

        ModelAndView mav = new ModelAndView("user_details");
        List<Reservation> reservations = reservationGateway.findByUserId(reservation.getUser().getId());
        mav.addObject("reservations", reservations);
        return mav;
    }
}
