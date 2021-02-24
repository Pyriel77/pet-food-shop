package com.codecool.petshop.modell;

import java.sql.Date;

public class DogFood extends Food{
    public DogFood(long id, String name, String brand, Date expiration) {
        super(id, FoodType.DOGFOOD, name, brand, expiration);
    }
}
