package com.ppro.pproprojectfinal.controller;


import com.ppro.pproprojectfinal.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping()
public class LoginController {


    @Autowired
    UserRepository userRepository;

    @Autowired
    LocationRepository locationRepository;



    @PostMapping("/loginGet")
    public String loginUser(@RequestParam String username, @RequestParam String password, RedirectAttributes redirectAttributes) {
        User user = userRepository.findByUsername(username);
        String tempRole ="";
        String tempLokace ="";

        switch (user.getRoleID()){
            case 1:
                tempRole = "Admin";
                break;
            case 2:
                tempRole = "Manažer";
                break;
            case 3:
                tempRole = "Zaměstnanec";
                break;
            case 4:
                tempRole = "Uživatel";
                break;
        }
        Location location = locationRepository.findByid(user.getLocationID());

        tempLokace= location.getLocationName();

        redirectAttributes.addAttribute("username",user.getUsername());
        redirectAttributes.addAttribute("role",tempRole);
        redirectAttributes.addAttribute("locationName",tempLokace);
        redirectAttributes.addAttribute("locationID",user.getLocationID());

        if (user != null && password.equals(user.getUserPw())) {
            if (user.getRoleID() == 1){

                return "redirect:/adminView";
            }

            if (user.getRoleID() == 2){
                return "redirect:/managerView";
            }

            if (user.getRoleID() == 3){
                return "redirect:/employeeView";
            }

            if (user.getRoleID() == 4){

                return "redirect:/userView";
            }
            return "redirect:/welcome";
        } else {
            // Failed login, handle appropriately (e.g., show error message)
            return "redirect:/login?error";
        }




    }


}
