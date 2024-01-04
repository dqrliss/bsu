package com.example.gifts_spring;

import java.sql.ResultSet;
import java.sql.SQLException;

public class gift {
    String name;
    double cost;
    public void readGift(ResultSet resultSet) throws SQLException {
        String productName = resultSet.getString("productName");
        double productCost = resultSet.getDouble("productCost");
        name = productName;
        cost = productCost;
    }

}