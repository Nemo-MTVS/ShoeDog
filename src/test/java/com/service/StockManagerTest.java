package com.service;

import com.model.Stock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StockManagerTest {
    private StockManager stockManager;

    @BeforeEach
    void setUp() {
        stockManager = new StockManager();
        stockManager.addShoe(new Stock(1, 1, 1, 1, 10));
        stockManager.addShoe(new Stock(2, 1, 2, 1, 15));
        stockManager.addShoe(new Stock(3, 2, 1, 2, 20));
    }

    @Test
    void addShoe() {
        Stock newShoe = new Stock(4, 2, 2, 2, 25);
        stockManager.addShoe(newShoe);
        
        Stock retrievedShoe = stockManager.getShoeById(4);
        assertNotNull(retrievedShoe);
        assertEquals(25, retrievedShoe.getStock());
    }

    @Test
    void getShoeById() {
        // Test existing shoe
        Stock shoe = stockManager.getShoeById(1);
        assertNotNull(shoe);
        assertEquals(10, shoe.getStock());
        
        // Test non-existing shoe
        Stock nonExistingShoe = stockManager.getShoeById(999);
        assertNull(nonExistingShoe);
    }

    @Test
    void getShoesByModel() {
        List<Stock> shoes = stockManager.getShoesByModel(1);
        assertEquals(2, shoes.size());
        assertTrue(shoes.stream().allMatch(shoe -> shoe.getModel_id() == 1));
    }

    @Test
    void getAllShoes() {
        List<Stock> shoes = stockManager.getAllShoes();
        assertEquals(3, shoes.size());
        assertTrue(shoes.stream().anyMatch(shoe -> shoe.getShoe_id() == 1));
        assertTrue(shoes.stream().anyMatch(shoe -> shoe.getShoe_id() == 2));
        assertTrue(shoes.stream().anyMatch(shoe -> shoe.getShoe_id() == 3));
    }

    @Test
    void updateStock() {
        // Test successful update
        boolean success = stockManager.updateStock(1, 30);
        assertTrue(success);
        assertEquals(30, stockManager.getShoeById(1).getStock());
        
        // Test update with non-existing shoe
        boolean failure = stockManager.updateStock(999, 40);
        assertFalse(failure);
    }

    @Test
    void updateShoeDetails() {
        Stock updatedShoe = new Stock(1, 1, 3, 3, 50);
        boolean success = stockManager.updateShoeDetails(updatedShoe);
        assertTrue(success);
        
        Stock retrievedShoe = stockManager.getShoeById(1);
        assertEquals(3, retrievedShoe.getSize_id());
        assertEquals(3, retrievedShoe.getColor_id());
        assertEquals(50, retrievedShoe.getStock());
    }

    @Test
    void removeShoe() {
        // Test successful removal
        boolean success = stockManager.removeShoe(1);
        assertTrue(success);
        assertNull(stockManager.getShoeById(1));
        
        // Test removal of non-existing shoe
        boolean failure = stockManager.removeShoe(999);
        assertFalse(failure);
    }
} 