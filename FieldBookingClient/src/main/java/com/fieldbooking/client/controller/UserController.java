package com.fieldbooking.client.controller;

import com.fieldbooking.client.entity.Reservation;
import com.fieldbooking.client.entity.User;
import com.fieldbooking.client.enumeration.AccountState;
import com.fieldbooking.client.enumeration.UserRole;
import com.fieldbooking.client.gateway.impl.FacilityGatewayImpl;
import com.fieldbooking.client.gateway.impl.ReservationGatewayImpl;
import com.fieldbooking.client.gateway.impl.UserGatewayImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserGatewayImpl userGateway;
    private final FacilityGatewayImpl facilityGateway;
    private final ReservationGatewayImpl reservationGateway;

    @Autowired
    public UserController(UserGatewayImpl userGateway, FacilityGatewayImpl facilityGateway, ReservationGatewayImpl reservationGateway) {
        this.userGateway = userGateway;
        this.facilityGateway = facilityGateway;
        this.reservationGateway = reservationGateway;
    }

    @GetMapping("/list")
    public ModelAndView list(ModelAndView mav) {
        List<User> all = userGateway.findAll();

        mav.addObject("users", all);
        mav.setViewName("user/list");
        return mav;
    }

    @PostMapping("/login")
    public ModelAndView login(@ModelAttribute("user") User user, ModelAndView mav, HttpSession session)  {
        User result = userGateway.login(user.getUsername(), user.getPassword());
        if (result != null) {
            mav.addObject("facilities", facilityGateway.findAll());
            mav.setViewName("/regular");
            session.setAttribute("currentUser", result);
            return mav;
        }
        return new ModelAndView("redirect:/user/login?error");
    }

    @GetMapping("/login")
    public ModelAndView getLogin(@ModelAttribute("user") User user, @ModelAttribute("newUser") User newUser, ModelAndView mav, HttpSession session){
        if (session.getAttribute("currentUser") == null) {
            mav.setViewName("/login");
        }
        else {
            mav.setViewName("/regular");
        }
        return mav;
    }

    @GetMapping
    public ModelAndView logout(ModelAndView mav, HttpSession session) {
        session.setAttribute("currentUser", null);
        return new ModelAndView("redirect:/user/login");
    }

    @GetMapping("/details/{id}")
    public ModelAndView getDetails(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView("user_details");
        List<Reservation> reservations = reservationGateway.findByUserId(id);
        mav.addObject("reservations", reservations);
        return mav;
    }

    @PostMapping("/new")
    public ModelAndView register(@ModelAttribute("newUser") User newUser, HttpSession session)  {
        newUser.setAccountState(AccountState.ACTIVE);
        newUser.setRole(UserRole.USER);
        userGateway.save(newUser);
        session.setAttribute("currentUser", null);
        return new ModelAndView("redirect:/user/login");
    }
}
