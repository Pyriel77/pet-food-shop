package com.codecool.petshop.modell;

import java.sql.Date;

public class CatFood extends Food{
    public CatFood(long id, String name, String brand, Date expiration) {
        super(id, FoodType.CATFOOD, name, brand, expiration);
    }
}
