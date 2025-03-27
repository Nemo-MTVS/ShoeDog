package com.dao;

import com.model.Stock;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StocksDao {
    private Connection connection; // Connection을 멤버 변수로 유지.

    /*
    * Stock Data Access Object*/
    public StocksDao(Connection connection) {
        this.connection = connection;
    }

    /*
    get All Stocks
     */
    public List<Stock> getAllStocks() {
        List<Stock> stocks = new ArrayList<>();
        // 빈 ArrayList 설정

        String sql = "select * from stock";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                stocks.add(new Stock(
                    resultSet.getInt("id"),
                    resultSet.getInt("model_id"),
                    resultSet.getInt("color_id"),
                    resultSet.getInt("size_id"),
                    resultSet.getInt("stock")
                ));
            }

        } catch (SQLException e) {
            System.out.println("Get All Stock Exception");
            e.printStackTrace();
        }
        return stocks;
    }

    public Stock getStockById(int id) {
        String sql = "select * from stock where shoe_id = ?";
        Stock stock = null;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet resultSet = ps.executeQuery()) {
                if (resultSet.next()) {
                    stock = new Stock(
                            resultSet.getInt("shoe_id"),
                            resultSet.getInt("model_id"),
                            resultSet.getInt("color"),
                            resultSet.getInt("size"),
                            resultSet.getInt("stock")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("getLesson() 실행 중 오류 발생");
            e.printStackTrace();
        }
        return stock;
    }
}
