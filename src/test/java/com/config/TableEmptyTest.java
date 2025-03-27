package com.config;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TableEmptyTest {
    private static final Logger logger = LoggerFactory.getLogger(TableEmptyTest.class);
    private final JDBCConnection jdbcConnection = new JDBCConnection();

    @Test
    void testTablesAreEmpty() {
        List<String> tables = List.of("stock", "model", "color", "sizes");
        List<String> nonEmptyTables = new ArrayList<>();

        try (Connection conn = jdbcConnection.getConnection()) {
            Statement stmt = conn.createStatement();
            
            for (String table : tables) {
                ResultSet rs = stmt.executeQuery("SELECT COUNT(*) as count FROM " + table);
                if (rs.next() && rs.getInt("count") > 0) {
                    nonEmptyTables.add(table);
                    logger.info("Table '{}' is not empty", table);
                } else {
                    logger.info("Table '{}' is empty", table);
                }
            }
        } catch (Exception e) {
            logger.error("Error checking table contents: {}", e.getMessage());
            fail("Failed to check table contents: " + e.getMessage());
        }

        assertTrue(nonEmptyTables.isEmpty(), 
            "The following tables are not empty: " + String.join(", ", nonEmptyTables));
    }
} 