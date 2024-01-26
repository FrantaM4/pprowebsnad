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
import java.util.List;


@Controller
@RequestMapping()
public class FoodRegistrationController{

    @Autowired
    FoodRepository foodRepository;

    @PostMapping("/formSubmitFood")
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
        food.setPortionNumber(0);
        foodRepository.save(food);

        return "redirect:/managerView";
    }

    @PostMapping("/foodListProcess")
    public String processFoodList(@RequestParam(name = "selectedItems", required = false) List<String> selectedItems) {
        if (selectedItems != null) {

            for(String foodID : selectedItems){

                System.out.println(foodID);
                Food food = foodRepository.findByid(Integer.parseInt(foodID));
                food.addPortion();
                foodRepository.save(food);

            }
        } else {
            System.out.println("No items selected");
        }
        return "redirect:/welcome";
    }

}
