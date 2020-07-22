package com.fieldbooking.client.controller;

import com.fieldbooking.client.entity.Availability;
import com.fieldbooking.client.entity.Field;
import com.fieldbooking.client.gateway.impl.AvailabilityGatewayImpl;
import com.fieldbooking.client.gateway.impl.FieldGatewayImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/field")
public class FieldController {
    private final FieldGatewayImpl fieldGateway;
    private final AvailabilityGatewayImpl availabilityGateway;

    @Autowired
    public FieldController(FieldGatewayImpl fieldGateway, AvailabilityGatewayImpl availabilityGateway) {
        this.fieldGateway = fieldGateway;
        this.availabilityGateway = availabilityGateway;
    }

    @GetMapping("/{id}")
    public ModelAndView details(@PathVariable("id") Long id, ModelAndView mav,
                                @ModelAttribute("field") Field field, @ModelAttribute("availability") Availability availability) {
        List<Field> fields = fieldGateway.findByFacilityId(id);
        List<Availability> availabilities = availabilityGateway.findByFacilityId(id);

        if (!fields.isEmpty()) {
            mav.addObject("fields", fields);
            mav.addObject("availabilities", availabilities);
            mav.setViewName("details");
            return mav;
        }
        else{
            return new ModelAndView("404");
        }
    }

    @PostMapping("/new/field")
    public ModelAndView addField(@ModelAttribute("field") Field field)  {
        fieldGateway.save(field);
        return new ModelAndView("redirect:/field/" + field.getFacility().getId());
    }

    @PostMapping("/new/availability")
    public ModelAndView addAvailability(@ModelAttribute("availability") Availability availability)  {
        availabilityGateway.save(availability);
        return new ModelAndView("redirect:/field/" + fieldGateway.findById(availability.getField().getId()).get().getFacility().getId());
    }
}
