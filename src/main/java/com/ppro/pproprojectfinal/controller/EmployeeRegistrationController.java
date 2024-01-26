package com.ppro.pproprojectfinal.controller;

import com.ppro.pproprojectfinal.model.UserRepository;
import com.ppro.pproprojectfinal.model.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/formSubmitEmployee")
public class EmployeeRegistrationController {
    @Autowired
    UserRepository userRepository;

    @PostMapping()
    public String createUser(@RequestParam String username,
                             @RequestParam String userPw,
                             HttpSession session) {
        User user = new User();
        user.setUsername(username);
        user.setUserPw(userPw);
        user.setRoleID(3);
        user.setLocationID((int)session.getAttribute("locationID"));
        userRepository.save(user);
        return "redirect:/managerView";
    }

}
