package com.dao;

import com.model.Model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ShoeModelDao {
    private final Connection connection;
    public ShoeModelDao(Connection connection) {
        this.connection = connection;
    }

    public List<Model> getAllModels() {
        List<Model> models = new ArrayList<>();
        String query = "select * from model";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                models.add(new Model(
                        rs.getInt("model_id"),
                        rs.getString("modelname"),
                        rs.getString("brandname"),
                        rs.getInt("listprice"),
                        rs.getString("description")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return models;
    }
}

