package com.fieldbooking.client.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@org.springframework.stereotype.Controller
@RequestMapping
public class IndexController {
    @GetMapping("/index")
    public ModelAndView getWelcomePage(HttpSession session){
        session.setAttribute("currentUser", null);
        return new ModelAndView("index");
    }
}
