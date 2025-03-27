package com.dao;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class StocksDaoTest {

    private Connection connection;
    private StocksDao stocksDao;
    private static final String TEST_TITLE = "Junit Test";

    @BeforeEach
    void setUp() {
        try  {
            connection = JDBCConnection.getConnection();
            connection.setAutoCommit(false); // 트랜잭션 시작, 자동 커밋 비활성화
            stocksDao = new StocksDao(connection); // Connection 전달, 동일 트랜잭션 사용

            PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO stock (model_id, color_id, size_id, stock) VALUES (?, ?, ?, ?)"
            );
            preparedStatement.setInt(1, 1);
            preparedStatement.setInt(2, 1);
            preparedStatement.setInt(3, 1);
            preparedStatement.setInt(4, 100);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Test
    void getAllStocks() {
    }

    @Test
    void getStockById() {
    }

    @AfterAll
    void tearDown() throws SQLException {
        connection.close();
    }
}