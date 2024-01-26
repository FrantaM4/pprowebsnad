package com.ppro.pproprojectfinal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

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
    public String adminPage() {
        return "adminview";
    }

    @GetMapping("/managerView")
    public String managerPage() {
        return "managerview";
    }

    @GetMapping("/userView")
    public String userPage() {
        return "userView";
    }

    @GetMapping("/employeeView")
    public String employeePage() {
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
