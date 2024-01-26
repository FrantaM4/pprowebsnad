package com.ppro.pproprojectfinal.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoodByUserRepository extends JpaRepository<FoodByUser, Long> {

    FoodByUser findByUserId(Long id);

    void deleteByUserId(Long userId);
}