package com.codecool.petshop.modell;

import lombok.*;

import java.sql.Date;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public abstract class Food {
    private final long id;
    private final FoodType foodType;
    private final String name;
    private final String brand;
    private final Date expiration;

    public boolean isNotExpired(){
        return this.expiration.after(Date.valueOf(LocalDate.now()));
    }
}
