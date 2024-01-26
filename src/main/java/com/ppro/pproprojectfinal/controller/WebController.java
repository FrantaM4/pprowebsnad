package com.ppro.pproprojectfinal.controller;

import com.ppro.pproprojectfinal.model.*;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Controller
public class WebController {

    @Autowired
    FoodRepository foodRepository;

    @Autowired
    FoodByUserRepository foodByUserRepository;



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
            String tempString = foodByUserRepository.findByUserId((Long)session.getAttribute("userID")).getFoodListString();
            String[] foodArray = tempString.split(",");
            System.out.println(foodArray[0]);

            List<Food> foodList = new ArrayList<>();
            List<Food> foodListUser = new ArrayList<>();
            Food todaysFood = null;

            for (int i = 0; i < foodArray.length; i++){
                Food food = foodRepository.findByid((Integer.parseInt(foodArray[i])));
                System.out.println(food.getFoodName());
                foodList.add(food);
            }

            for (Food food : foodList) {
                LocalDate databaseLocalDate = food.getFoodDate().toLocalDate();

                LocalDate currentDate = LocalDate.now();

                int comparisonResult = databaseLocalDate.compareTo(currentDate);

                if (comparisonResult < 0) {
                    System.out.println("The database date is before the current date.");
                } else if (comparisonResult > 0) {
                    foodListUser.add(food);
                    System.out.println("The database date is after the current date.");
                } else {
                    todaysFood = food;
                    foodListUser.add(food);
                }


                if (todaysFood == null || session.getAttribute("roleID") == null) {
                    model.addAttribute("todayFood", "nemáte obědnáno");
                } else {
                    model.addAttribute("todayFood", todaysFood.getFoodName() + " za " + todaysFood.getFoodPrice() + "kč");
                }
            }
            //order list podle data
            session.setAttribute("userFoodList", foodListUser);

            model.addAttribute("username", session.getAttribute("username"));
            model.addAttribute("role", session.getAttribute("role"));
            model.addAttribute("locationName", session.getAttribute("locationName"));
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

    //TODO PRIDAT OPRAVNENI


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

    @GetMapping("/foodByUserGet")
    public String foodByUserGet(HttpSession session, Model model) {
        List<String> itemList =  new ArrayList<>();
        for (Food food : (List<Food>) session.getAttribute("userFoodList"))
            itemList.add(food.getFoodName()+ " za: " + food.getFoodPrice()+"kč " + "dne: " + food.getFoodDate().toString() + " " + food.getFoodDescription() );
        model.addAttribute("itemList", itemList);
        return "foodByUserView";
    }

    @Transactional
    @GetMapping("/foodByUserModify")
    public String foodByUserModify(Model model, HttpSession session) {
        List<Food> allFoodList = foodRepository.findAllByLocationID((int)session.getAttribute("locationID"));

        for (Food foodFromList : (List<Food>) session.getAttribute("userFoodList")){
            Food foodToModify = foodRepository.findByid(Math.toIntExact(foodFromList.getId()));
            foodToModify.removePortion();
            foodRepository.save(foodToModify);
        }

        foodByUserRepository.deleteByUserId((Long)session.getAttribute("userID"));
        model.addAttribute("itemList", allFoodList);
        return "foodByUserModify";
    }

    /*@PostMapping("/submitCheckboxForm")
    public String submitCheckboxForm(List<String> selectedItems) {
        // Handle the selected items (you can do something with them)
        System.out.println("Selected items: " + selectedItems);

        // Redirect to a page or return a view as needed
        return "redirect:/userView";
    }*/

}
