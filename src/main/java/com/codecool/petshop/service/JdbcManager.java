package com.codecool.petshop.service;

import com.codecool.petshop.dao.FoodDao;
import com.codecool.petshop.modell.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class JdbcManager implements FoodDao {
    static final String DB_URL = "jdbc:postgresql://localhost:5432/PetShop";
    static final String USER = "ic";
    static final String PASS = "ic";


    @Override
    public List<Food> findAll() {
        String SQL = "SELECT * FROM petfood";
        List<Food> foodList = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            ResultSet resultSet = conn.createStatement().executeQuery(SQL);

            while (resultSet.next()){
                foodList.add( makeFoodFromSQLQuery(resultSet) );
            }
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return foodList;
    }

    @Override
    public List<Food> findFoodByBrand(String findBrand) {
        List<Food> foodList = new ArrayList<>();
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)){
            String sql = "SELECT * FROM petfood WHERE brand = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,findBrand);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                foodList.add( makeFoodFromSQLQuery(resultSet) );
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return foodList;
    }


    @Override
    public List<Food> findFoodByType(FoodType findType) {
        String SQL = "SELECT * FROM petfood WHERE foodtype = '"+findType+"'";
        List<Food> foodList = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            ResultSet resultSet = conn.createStatement().executeQuery(SQL);

            while (resultSet.next()){
                foodList.add( makeFoodFromSQLQuery(resultSet) );
            }
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return foodList;
    }


    @Override
    public void insertFoodToDB(Food food) {
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)){
            String sql = "INSERT INTO petfood (foodtype,itemname,brand,expiration) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,food.getFoodType().name());
            preparedStatement.setString(2,food.getName());
            preparedStatement.setString(3,food.getBrand());
            preparedStatement.setDate(4,food.getExpiration());
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            rs.next();
            //food.setId(rs.getInt(1));
        } catch (SQLException throwables) {
            throw new RuntimeException("Error while adding new food.", throwables);
        }
    }

    @Override
    public void printFoodList (String title, List<Food> foodList) {
        System.out.println("\n"+title+"\n"+"---------------------------------------------------");
        for (Food food : foodList) {
            System.out.printf("%2d. %-15s %-10s %10s (%s)%n",
                    food.getId(),
                    food.getName(),
                    food.getBrand(),
                    food.getExpiration().toString(),
                    food.getFoodType().name()
            );
        }
    }

    public Food makeFoodFromSQLQuery(ResultSet resultSet){
        Food resFood = null;
        try{
            long id = resultSet.getLong(1);
            FoodType foodType = FoodType.valueOf(resultSet.getString(2));
            String name = resultSet.getString(3);
            String brand = resultSet.getString(4);
            Date expDate = resultSet.getDate(5);
            switch (foodType){
                case CATFOOD: resFood = new CatFood(id, name, brand, expDate); break;
                case DOGFOOD: resFood = new DogFood(id, name, brand, expDate); break;
                case FISHFOOD: resFood = new FishFood(id, name, brand, expDate); break;
            }
//System.out.printf("[%2d. %-15s %-10s %10s (%s)]%n",id,name,brand,expDate,foodType);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return resFood;
    }


}
