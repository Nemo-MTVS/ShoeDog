package com.dao;

import com.model.Model;
import com.model.Stock;
import com.model.StockInfo;
import com.util.QueryUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StockDao {
    private Connection connection;

    public StockDao(Connection connection) {
        this.connection = connection;
    }


    public List<StockInfo> getAllStocks() throws SQLException {
        List<StockInfo> stocks = new ArrayList<>();
        String sql = QueryUtil.getQuery("getAllStocks");

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                stocks.add(new StockInfo(
                        rs.getInt("id"),
                        rs.getInt("model_id"),
                        rs.getInt("color_id"),
                        rs.getString("modelname"),
                        rs.getString("color"),
                        rs.getInt("size"),
                        rs.getInt("quantity")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stocks;
    }

    public Stock getStockById(int id) throws SQLException {
        String sql = QueryUtil.getQuery("getStockById");
        
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
        String sql = QueryUtil.getQuery("getStocksByModelId");

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
        String sql = QueryUtil.getQuery("updateStock");
        
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, stock.getModelId());
            ps.setInt(2, stock.getColorId());
            ps.setInt(3, stock.getSizeId());
            ps.setInt(4, stock.getQuantity());
            ps.setInt(5, stock.getId());
            
            return ps.executeUpdate() > 0;
        }
    }

    // 재고 새롭게 등록
    public boolean insertStock(StockInfo stock) throws SQLException {
        String sql = QueryUtil.getQuery("addStock");

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, stock.getModelname());
            ps.setString(2, stock.getColor());
            ps.setInt(3, stock.getSize());
            ps.setInt(4, stock.getQuantity());

            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
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



//    public boolean updateStockQuantity(int id, int newQuantity) throws SQLException {
//        String sql = QueryUtil.getQuery("updateStockQuantity");
//
//        try (PreparedStatement ps = connection.prepareStatement(sql)) {
//            ps.setInt(1, newQuantity);
//            ps.setInt(2, id);
//            return ps.executeUpdate() > 0;
//        }
//    }



    // 등록되어 있는 모델 이름 조회
    public List<String> getModelnameList() {
        List<String> modelnames = new ArrayList<>();
        String query = QueryUtil.getQuery("getModelnameList");

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                modelnames.add(rs.getString("modelname"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return modelnames;
    }


    // 재고 존재하는지 검증
    public StockInfo isStockExist(StockInfo stock) throws SQLException {
        StockInfo stockInfo = null;
        String query = QueryUtil.getQuery("isStockExist");
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, stock.getModelname());
            ps.setString(2, stock.getColor());
            ps.setInt(3, stock.getSize());

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                stockInfo = new StockInfo(
                        rs.getInt("id"),
                        rs.getInt("model_id"),
                        rs.getInt("color_id"),
                        rs.getString("modelname"),
                        rs.getString("color"),
                        rs.getInt("size"),
                        rs.getInt("quantity")
                );
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return stockInfo;
    }

    // 존재하는 재고 값 변경
    public boolean updateStockQuantity(StockInfo stock) throws SQLException {
        String sql = QueryUtil.getQuery("updateStockQuantity");

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, stock.getQuantity());
            ps.setString(2, stock.getModelname());
            ps.setString(3, stock.getColor());
            ps.setInt(4, stock.getSize());

            int affectedRows = ps.executeUpdate();
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


}
