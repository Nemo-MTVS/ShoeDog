package com.dao;

import com.model.Model;
import com.util.QueryUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ModelDao {
    private final Connection connection;
    public ModelDao(Connection connection) {
        this.connection = connection;
    }

    public List<Model> getAllModels() {
        List<Model> models = new ArrayList<>();
        String query = QueryUtil.getQuery("getAllModels");

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
    // ID로 특정 모델 조회
    public Model getModelById(int modelId) {
        String query = QueryUtil.getQuery("getModelById");
        Model model = null;

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, modelId);

            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    model = new Model(
                            rs.getInt("model_id"),
                            rs.getString("modelname"),
                            rs.getString("brandname"),
                            rs.getInt("listprice"),
                            rs.getString("description")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return model;
    }

    // 모델 이름으로 특정 모델 조회
    public Model getModelByName(String modelname) {
        String query = QueryUtil.getQuery("getModelByName");
        Model model = null;

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, modelname);

            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    model = new Model(
                            rs.getInt("model_id"),
                            rs.getString("modelname"),
                            rs.getString("brandname"),
                            rs.getInt("listprice"),
                            rs.getString("description")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return model;
    }

    // 모델 추가
    public boolean addModel(Model model) {
        String query = QueryUtil.getQuery("addModel");

        try (PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, model.getModelname());
            ps.setString(2, model.getBrandname());
            ps.setInt(3, model.getListprice());
            ps.setString(4, model.getDescription());

            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 모델 수정
    public boolean updateModel(Model model) {
        String query = QueryUtil.getQuery("updateModel");

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, model.getModelname());
            ps.setString(2, model.getBrandname());
            ps.setInt(3, model.getListprice());
            ps.setString(4, model.getDescription());
            ps.setInt(5, model.getId());

            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}

