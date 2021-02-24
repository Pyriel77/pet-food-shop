package com.codecool.petshop.service;

import com.codecool.petshop.dao.FoodDao;
import com.codecool.petshop.modell.Food;
import com.codecool.petshop.modell.FoodType;

import java.util.List;
import java.util.stream.Collectors;

public class FoodService implements FoodDao {
    private FoodDao foodDao;

    public FoodService(FoodDao foodDao) {
        this.foodDao = foodDao;
    }

    @Override
    public List<Food> findAll() {
        return foodDao.findAll();
    }

    @Override
    public List<Food> findFoodByBrand(String brand){
        return foodDao.findFoodByBrand(brand).stream().filter(Food::isNotExpired).collect(Collectors.toList());
    }

    @Override
    public List<Food> findFoodByType(FoodType foodType){
        return foodDao.findFoodByType(foodType).stream().filter(Food::isNotExpired).collect(Collectors.toList());
    }

    @Override
    public void insertFoodToDB(Food food) {
        foodDao.insertFoodToDB(food);
    }

    @Override
    public void printFoodList(String title, List<Food> foodList) {
        foodDao.printFoodList(title,foodList);
    }

}
