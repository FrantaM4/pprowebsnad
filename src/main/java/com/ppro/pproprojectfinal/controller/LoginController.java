package com.ppro.pproprojectfinal.controller;


import com.ppro.pproprojectfinal.model.User;
import com.ppro.pproprojectfinal.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/loginGet")
public class LoginController {

    @Autowired
    UserRepository userRepository;



    @PostMapping()
    public String loginUser(@RequestParam String username, @RequestParam String password) {
        User user = userRepository.findByUsername(username);

        if (user != null && password.equals(user.getUserPw())) {
            if (user.getRoleID() == 1)
                return "redirect:/adminView";
            if (user.getRoleID() == 2)
                return "redirect:/managerView";
            if (user.getRoleID() == 3)
                return "redirect:/employeeView";
            if (user.getRoleID() == 4)
                return "redirect:/userView";
            return "redirect:/welcome";
        } else {
            // Failed login, handle appropriately (e.g., show error message)
            return "redirect:/login?error";
        }
    }
}
