package com.ppro.pproprojectfinal.controller;

import com.ppro.pproprojectfinal.model.Food;
import com.ppro.pproprojectfinal.model.FoodRepository;
import com.ppro.pproprojectfinal.model.User;
import com.ppro.pproprojectfinal.model.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
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
    public String adminPage(HttpSession session,Model model) {
        if (session.getAttribute("roleID") != null){
            model.addAttribute("username", session.getAttribute("username"));
            model.addAttribute("role", session.getAttribute("role"));
            //model.addAttribute("locationName", session.getAttribute("username"));

        if ((int)session.getAttribute("roleID") == 1)
            return "adminview";
        else
            return "redirect:/logout";
        }
        return "redirect:/logout";
    }

    @GetMapping("/managerView")
    public String managerPage(HttpSession session,Model model) {
        if (session.getAttribute("roleID") != null) {
            model.addAttribute("username", session.getAttribute("username"));
            model.addAttribute("role", session.getAttribute("role"));
            model.addAttribute("locationName", session.getAttribute("locationName"));

        if ((int)session.getAttribute("roleID") == 2 || session.getAttribute("roleID") == null )
            return "managerview";
        else
            return "redirect:/logout";
        }
        return "redirect:/logout";
    }



    @GetMapping("/userView")
    public String userView(HttpSession session, Model model) {
        if (session.getAttribute("roleID") != null) {
            Integer tempID = (Integer) session.getAttribute("locationID");

            List<Food> foodList = foodRepository.findAllByLocationID(tempID);
            Food todaysFood = null;
            for (Food food : foodList) {
                LocalDate databaseLocalDate = food.getFoodDate().toLocalDate();

                LocalDate currentDate = LocalDate.now();

                int comparisonResult = databaseLocalDate.compareTo(currentDate);

                if (comparisonResult < 0) {
                    System.out.println("The database date is before the current date.");
                } else if (comparisonResult > 0) {
                    System.out.println("The database date is after the current date.");
                } else {
                    todaysFood = food;
                }

            model.addAttribute("username", session.getAttribute("username"));
            model.addAttribute("role", session.getAttribute("role"));
            model.addAttribute("locationName", session.getAttribute("locationName"));
            if (todaysFood == null || session.getAttribute("roleID") == null) {
                model.addAttribute("todayFood", "nemáte obědnáno");
            } else {
                model.addAttribute("todayFood", todaysFood.getFoodName() + " za " + todaysFood.getFoodPrice() + "kč");
            }
            //model.addAttribute("foodList",foodList);*/
        }
        if ((int)session.getAttribute("roleID") == 4 || session.getAttribute("roleID") == null )
            return "userView";
        else
            return "redirect:/logout";
        }
        return "redirect:/logout";
    }

    @GetMapping("/employeeView")
    public String employeePage(Model model, HttpSession session) {
        if (session.getAttribute("roleID") != null) {
            model.addAttribute("username", session.getAttribute("username"));
            model.addAttribute("role", session.getAttribute("role"));
            model.addAttribute("locationName", session.getAttribute("locationName"));

        if ((int)session.getAttribute("roleID") == 3 || session.getAttribute("roleID") == null )
            return "employeeView";
        else
            return "redirect:/logout";
        }
        return "redirect:/logout";

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
