package com.ppro.pproprojectfinal.controller;

import com.ppro.pproprojectfinal.model.Food;
import com.ppro.pproprojectfinal.model.FoodRepository;
import com.ppro.pproprojectfinal.model.UserRepository;
import com.ppro.pproprojectfinal.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;


@Controller
@RequestMapping("/formSubmitFood")
public class FoodRegistrationController{

    @Autowired
    FoodRepository foodRepository;

    @PostMapping()
    public String createFood(@RequestParam String foodName,
                             @RequestParam Date foodDate,
                             @RequestParam Integer foodPrice,
                             @RequestParam String foodDescription) {
        Food food = new Food();
        food.setFoodDate(foodDate);
        food.setFoodName(foodName);
        food.setFoodPrice(foodPrice);
        food.setFoodDescription(foodDescription);
        food.setLocationID(1);
        foodRepository.save(food);
        return "redirect:/managerView";
    }

}
