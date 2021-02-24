package com.codecool.petshop;

import com.codecool.petshop.dao.FoodDao;
import com.codecool.petshop.service.FoodService;
import com.codecool.petshop.service.JdbcManager;

import static com.codecool.petshop.modell.FoodType.*;

public class Main {

    static FoodDao foodService = new FoodService(new JdbcManager());


    public static void main(String[] args) {
//        Date expiration = Date.valueOf("2020-10-10");
//        Date date = Date.valueOf("2020-11-11");
//        System.out.println(date.before(expiration));
//        System.out.println(expiration.toString()+":"+Date.valueOf(LocalDate.now()));
//        System.out.println(expiration.after(Date.valueOf(LocalDate.now())));


        //foodService.insertFoodToDB(new CatFood(0,"BeautyFood","Wishcash",Date.valueOf("2020-11-11")));

        foodService.printFoodList("All foods list:",foodService.findAll());
        foodService.printFoodList("Wishcash" + " brand foods:",foodService.findFoodByBrand("Wishcash"));
        foodService.printFoodList("CATFOOD" + " type foods:",foodService.findFoodByType(CATFOOD));

    }
}
