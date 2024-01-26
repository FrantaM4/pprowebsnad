package com.ppro.pproprojectfinal.model;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FoodRepository extends JpaRepository<Food, Long> {

   List<Food> findAllByLocationID(Integer id);

   Food findByid(Integer id);

}
