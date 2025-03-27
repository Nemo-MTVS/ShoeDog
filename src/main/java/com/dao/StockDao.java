package com.dao;

import com.model.Stock;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StockDao {
    private Connection connection;

    public StockDao(Connection connection) {
        this.connection = connection;
    }

    public void insertStock(Stock stock) throws SQLException {
        String sql = "INSERT INTO stock (model_id, color_id, size_id, quantity) VALUES (?, ?, ?, ?)";
        
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, stock.getModelId());
            ps.setInt(2, stock.getColorId());
            ps.setInt(3, stock.getSizeId());
            ps.setInt(4, stock.getQuantity());
            ps.executeUpdate();
        }
    }

    public List<Stock> getAllStocks() throws SQLException {
        List<Stock> stocks = new ArrayList<>();
        String sql = "SELECT * FROM stock";

        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                stocks.add(mapResultSetToStock(rs));
            }
        }
        return stocks;
    }

    public Stock getStockById(int id) throws SQLException {
        String sql = "SELECT * FROM stock WHERE id = ?";
        
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToStock(rs);
                }
            }
        }
        return null;
    }

    public List<Stock> getStocksByModelId(int modelId) throws SQLException {
        List<Stock> stocks = new ArrayList<>();
        String sql = "SELECT * FROM stock WHERE model_id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, modelId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    stocks.add(mapResultSetToStock(rs));
                }
            }
        }
        return stocks;
    }

    public boolean updateStock(Stock stock) throws SQLException {
        String sql = "UPDATE stock SET model_id = ?, color_id = ?, size_id = ?, quantity = ? WHERE id = ?";
        
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, stock.getModelId());
            ps.setInt(2, stock.getColorId());
            ps.setInt(3, stock.getSizeId());
            ps.setInt(4, stock.getQuantity());
            ps.setInt(5, stock.getId());
            
            return ps.executeUpdate() > 0;
        }
    }

    public boolean updateStockQuantity(int id, int newQuantity) throws SQLException {
        String sql = "UPDATE stock SET quantity = ? WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, newQuantity);
            ps.setInt(2, id);
            return ps.executeUpdate() > 0;
        }
    }

    private Stock mapResultSetToStock(ResultSet rs) throws SQLException {
        return new Stock(
            rs.getInt("id"),
            rs.getInt("model_id"),
            rs.getInt("color_id"),
            rs.getInt("size_id"),
            rs.getInt("quantity")
        );
    }
}
