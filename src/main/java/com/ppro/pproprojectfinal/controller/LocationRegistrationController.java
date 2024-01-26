package com.ppro.pproprojectfinal.controller;


import com.ppro.pproprojectfinal.model.Location;
import com.ppro.pproprojectfinal.model.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/formSubmitLocation")
public class LocationRegistrationController {

    @Autowired
    LocationRepository locationRepository;

    @PostMapping()
    public String createLocation(@RequestParam String locationName) {
        Location location = new Location();
        location.setLocationName(locationName);
        locationRepository.save(location);
        return "redirect:/adminView";
    }

}