package com.codecool.petshop.modell;

import java.sql.Date;

public class FishFood extends Food{
    public FishFood(long id, String name, String brand, Date expiration) {
        super(id, FoodType.FISHFOOD, name, brand, expiration);
    }
}
