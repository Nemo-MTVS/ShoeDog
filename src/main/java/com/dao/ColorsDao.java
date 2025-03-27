package com.dao;

import com.model.Colors;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ColorsDao {
    private final Connection connection;

    public ColorsDao(Connection connection) {
        this.connection = connection;
    }

    // 모든 색상 조회
    public List<Colors> getAllColors() {
        List<Colors> colors = new ArrayList<>();
        String query = "select * from color ORDER BY color_id ASC;";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                colors.add(new Colors(
                        rs.getInt("color_id"),
                        rs.getString("color")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return colors;
    }

    // id로 특정 색상 조회
    public Colors getColorById(int colorId) {
        String query = "select * from color where color_id = ?";
        Colors color = null;

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, colorId);

            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    color = new Colors(
                            rs.getInt("color_id"),
                            rs.getString("color")
                    );
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return color;
    }

    // 색상명으로 특정 색상 조회
    public Colors getColorByName(String colorName) {
        String query = "select * from color where color = ?";
        Colors color = null;

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, colorName);

            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    color = new Colors(
                            rs.getInt("color_id"),
                            rs.getString("color")
                    );
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return color;
    }

    // 색상 추가
    public boolean addColor(Colors color) {
        String query = "INSERT INTO color (color) VALUES (?)";

        try (PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, color.getColor_name());

            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
