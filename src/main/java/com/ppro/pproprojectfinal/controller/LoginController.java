package com.ppro.pproprojectfinal.controller;


import com.ppro.pproprojectfinal.model.Location;
import com.ppro.pproprojectfinal.model.LocationRepository;
import com.ppro.pproprojectfinal.model.User;
import com.ppro.pproprojectfinal.model.UserRepository;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping()
public class LoginController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    LocationRepository locationRepository;


    @PostMapping("/loginGet")
    public String loginUser(@RequestParam String username, @RequestParam String password, HttpSession session) {
        User user = userRepository.findByUsername(username);

        if (user != null && password.equals(user.getUserPw())) {


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

            session.setAttribute("username",username);
            session.setAttribute("role",tempRole);
            session.setAttribute("roleID",user.getRoleID());
            session.setAttribute("locationName",tempLokace);
            session.setAttribute("locationID",user.getLocationID());
            session.setAttribute("userID",user.getId());
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
             //Failed login, handle appropriately (e.g., show error message)
            return "redirect:/logout";
        }
    }
    @GetMapping("/logout")
    public String welcome(HttpSession session) {
        session.invalidate();
        return "welcome";
    }

}
