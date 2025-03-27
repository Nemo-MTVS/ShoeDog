package com.service;

import com.config.JDBCConnection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;

import static org.junit.jupiter.api.Assertions.*;

class DataInsertionTest {

    private static final Logger logger = LoggerFactory.getLogger(DataInsertionTest.class);
    private final JDBCConnection jdbcConnection = new JDBCConnection();

    @BeforeEach
    void setUp() {
        // Clear all tables before each test
        try (Connection conn = jdbcConnection.getConnection()) {
            Statement stmt = conn.createStatement();
            stmt.execute("DELETE FROM stock");
            stmt.execute("DELETE FROM model");
            stmt.execute("DELETE FROM color");
            stmt.execute("DELETE FROM sizes");
            logger.info("All tables cleared before test");
        } catch (Exception e) {
            logger.error("Error clearing tables: {}", e.getMessage());
            fail("Failed to clear tables: " + e.getMessage());
        }
    }

    @Test
    void testInsertModel() {
        try (Connection conn = jdbcConnection.getConnection()) {
            // Insert a new model
            String insertSql = "INSERT INTO model (modelname, brandname, listprice, description) VALUES (?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(insertSql)) {
                pstmt.setString(1, "Air Max");
                pstmt.setString(2, "Nike");
                pstmt.setInt(3, 12999);
                pstmt.setString(4, "Classic Air Max sneaker");
                pstmt.executeUpdate();
            }

            // Verify the insertion
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) as count FROM model");
            assertTrue(rs.next());
            assertEquals(1, rs.getInt("count"), "Model table should have exactly one record");

            // Verify the data
            rs = stmt.executeQuery("SELECT * FROM model WHERE modelname = 'Air Max'");
            assertTrue(rs.next());
            assertEquals("Air Max", rs.getString("modelname"));
            assertEquals("Nike", rs.getString("brandname"));
            assertEquals(12999, rs.getInt("listprice"));
            assertEquals("Classic Air Max sneaker", rs.getString("description"));
            
            logger.info("Model insertion test passed");
        } catch (Exception e) {
            logger.error("Error in model insertion test: {}", e.getMessage());
            fail("Failed to insert model: " + e.getMessage());
        }
    }

    @Test
    void testInsertColor() {
        try (Connection conn = jdbcConnection.getConnection()) {
            // Insert a new color
            String insertSql = "INSERT INTO color (color) VALUES (?)";
            try (PreparedStatement pstmt = conn.prepareStatement(insertSql)) {
                pstmt.setString(1, "Red");
                pstmt.executeUpdate();
            }

            // Verify the insertion
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) as count FROM color");
            assertTrue(rs.next());
            assertEquals(1, rs.getInt("count"), "Color table should have exactly one record");

            // Verify the data
            rs = stmt.executeQuery("SELECT * FROM color WHERE color = 'Red'");
            assertTrue(rs.next());
            assertEquals("Red", rs.getString("color"));
            
            logger.info("Color insertion test passed");
        } catch (Exception e) {
            logger.error("Error in color insertion test: {}", e.getMessage());
            fail("Failed to insert color: " + e.getMessage());
        }
    }

    @Test
    void testInsertSize() {
        try (Connection conn = jdbcConnection.getConnection()) {
            // Insert a new size
            String insertSql = "INSERT INTO sizes (size) VALUES (?)";
            try (PreparedStatement pstmt = conn.prepareStatement(insertSql)) {
                pstmt.setInt(1, 42);
                pstmt.executeUpdate();
            }

            // Verify the insertion
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) as count FROM sizes");
            assertTrue(rs.next());
            assertEquals(1, rs.getInt("count"), "Sizes table should have exactly one record");

            // Verify the data
            rs = stmt.executeQuery("SELECT * FROM sizes WHERE size = 42");
            assertTrue(rs.next());
            assertEquals(42, rs.getInt("size"));
            
            logger.info("Size insertion test passed");
        } catch (Exception e) {
            logger.error("Error in size insertion test: {}", e.getMessage());
            fail("Failed to insert size: " + e.getMessage());
        }
    }

    @Test
    void testInsertStock() {
        try (Connection conn = jdbcConnection.getConnection()) {
            // First insert required related data
            Statement stmt = conn.createStatement();
            
            // Insert model
            stmt.execute("INSERT INTO model (modelname, brandname, listprice, description) VALUES ('Air Max', 'Nike', 12999, 'Classic Air Max sneaker')");
            ResultSet rs = stmt.executeQuery("SELECT id FROM model WHERE modelname = 'Air Max'");
            assertTrue(rs.next());
            int modelId = rs.getInt("id");

            // Insert color
            stmt.execute("INSERT INTO color (color) VALUES ('Red')");
            rs = stmt.executeQuery("SELECT id FROM color WHERE color = 'Red'");
            assertTrue(rs.next());
            int colorId = rs.getInt("id");

            // Insert size
            stmt.execute("INSERT INTO sizes (size) VALUES (42)");
            rs = stmt.executeQuery("SELECT id FROM sizes WHERE size = 42");
            assertTrue(rs.next());
            int sizeId = rs.getInt("id");

            // Insert stock
            String insertSql = "INSERT INTO stock (model_id, color, size, stock) VALUES (?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(insertSql)) {
                pstmt.setInt(1, modelId);
                pstmt.setInt(2, colorId);
                pstmt.setInt(3, sizeId);
                pstmt.setInt(4, 10);
                pstmt.executeUpdate();
            }

            // Verify the insertion
            rs = stmt.executeQuery("SELECT COUNT(*) as count FROM stock");
            assertTrue(rs.next());
            assertEquals(1, rs.getInt("count"), "Stock table should have exactly one record");

            // Verify the data
            rs = stmt.executeQuery("SELECT * FROM stock WHERE model_id = " + modelId);
            assertTrue(rs.next());
            assertEquals(modelId, rs.getInt("model_id"));
            assertEquals(colorId, rs.getInt("color"));
            assertEquals(sizeId, rs.getInt("size"));
            assertEquals(10, rs.getInt("stock"));
            
            logger.info("Stock insertion test passed");
        } catch (Exception e) {
            logger.error("Error in stock insertion test: {}", e.getMessage());
            fail("Failed to insert stock: " + e.getMessage());
        }
    }
} 