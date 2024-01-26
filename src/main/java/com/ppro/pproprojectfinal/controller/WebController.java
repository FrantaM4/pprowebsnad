package com.ppro.pproprojectfinal.controller;

import com.ppro.pproprojectfinal.model.Food;
import com.ppro.pproprojectfinal.model.FoodRepository;
import com.ppro.pproprojectfinal.model.User;
import com.ppro.pproprojectfinal.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class WebController {

    @Autowired
    FoodRepository foodRepository;

    @GetMapping("/welcome")
    public String welcome() {
        return "welcome";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/registration")
    public String registrationForm() {
        return "registrationForm";
    }

    @GetMapping("/adminView")
    public String adminPage(@RequestParam("username") String username, @RequestParam("role") String role,@RequestParam("locationName") String locationName,Model model) {
        model.addAttribute("username", username);
        model.addAttribute("role", role);
        //model.addAttribute("locationName", locationName);
        return "adminview";
    }

    @GetMapping("/managerView")
    public String managerPage(@RequestParam("username") String username, @RequestParam("role") String role,@RequestParam("locationName") String locationName,Model model) {
        model.addAttribute("username", username);
        model.addAttribute("role", role);
        model.addAttribute("locationName", locationName);
        return "managerview";
    }






    @GetMapping("/userView")
    public String userView(@RequestParam("username") String username, @RequestParam("role") String role,@RequestParam("locationName") String locationName,@RequestParam("locationID") Integer locationID, Model model) {
        List<Food> foodList = foodRepository.findAllByLocationID(locationID);
        model.addAttribute("username", username);
        model.addAttribute("role", role);
        model.addAttribute("locationName", locationName);
        model.addAttribute("foodList",foodList);
        return "userView";
    }

    @GetMapping("/employeeView")
    public String employeePage(@RequestParam("username") String username, @RequestParam("role") String role,@RequestParam("locationName") String locationName,Model model) {
        model.addAttribute("username", username);
        model.addAttribute("role", role);
        model.addAttribute("locationName", locationName);
        return "employeeView";
    }

    @GetMapping("/locationRegistrationForm")
    public String locationRegistrationForm() {
        return "locationRegistrationForm";
    }

    @GetMapping("/managerRegistrationForm")
    public String managerRegistrationForm() {
        return "managerRegistrationForm";
    }

    @GetMapping("/employeeRegistrationForm")
    public String employeeRegistrationForm() {
        return "employeeRegistrationForm";
    }

    @GetMapping("/foodRegistrationForm")
    public String foodRegistrationForm() {
        return "foodRegistrationForm";
    }

}
