package com.ppro.pproprojectfinal.controller;

import com.ppro.pproprojectfinal.model.UserRepository;
import com.ppro.pproprojectfinal.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/formSubmit")
public class UserRegistrationController {
    @Autowired
    UserRepository userRepository;

    @PostMapping()
    public String createUser(@RequestParam String username,
                             @RequestParam String userPw,
                             @RequestParam Integer locationID) {
        User user = new User();
        user.setUsername(username);
        user.setUserPw(userPw);
        user.setLocationID(locationID);
        user.setRoleID(4);
        userRepository.save(user);
        return "redirect:/welcome";
    }

}
