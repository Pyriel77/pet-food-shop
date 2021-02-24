package com.codecool.petshop.dao;

import com.codecool.petshop.modell.Food;
import com.codecool.petshop.modell.FoodType;

import java.util.List;

public interface FoodDao {
    List<Food> findAll();
    List<Food> findFoodByBrand(String brand);
    List<Food> findFoodByType(FoodType foodType);
    void insertFoodToDB(Food food);
    void printFoodList (String title, List<Food> foodList);
}
