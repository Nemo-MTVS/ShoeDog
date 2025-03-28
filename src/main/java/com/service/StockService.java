package com.service;

import com.dao.StockDao;
import com.model.Stock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class StockService {
    private static final Logger log = LoggerFactory.getLogger(StockService.class);
    private final StockDao stockDao;

    public StockService(Connection connection) {
        this.stockDao = new StockDao(connection);
    }

    public void addStock(Stock stock) throws SQLException {
        log.info("Adding new stock: {}", stock);
        if (!isValidSize(stock.getSizeId())) {
            throw new IllegalArgumentException("Invalid shoe size. Must be from 220 to 300" );
        }
        stockDao.insertStock(stock);
    }

    public Stock getStockById(int id) throws SQLException {
        log.info("Fetching stock with ID: {}", id);
        return stockDao.getStockById(id);
    }

    public List<Stock> getStockByModel(int modelId) throws SQLException {
        log.info("Fetching stocks for model ID: {}", modelId);
        return stockDao.getStocksByModelId(modelId);
    }

    public List<Stock> getAllStocks() throws SQLException {
        log.info("Fetching all stocks");
        return stockDao.getAllStocks();
    }

    public boolean updateStockQuantity(int id, int newQuantity) throws SQLException {
        log.info("Updating stock quantity for ID: {} to {}", id, newQuantity);
        Stock stock = getStockById(id);
        if (stock != null) {
            return stockDao.updateStockQuantity(id, newQuantity);
        }
        log.warn("Stock not found with ID: {}", id);
        return false;
    }

    public boolean updateStockDetails(Stock updatedStock) throws SQLException {
        log.info("Updating stock details: {}", updatedStock);
        Stock existingStock = getStockById(updatedStock.getId());
        if (existingStock != null) {
            if (!isValidSize(updatedStock.getSizeId())) {
                throw new IllegalArgumentException("Invalid shoe size. Must be from 220 to 300");
            }
            return stockDao.updateStock(updatedStock);
        }
        log.warn("Stock not found with ID: {}", updatedStock.getId());
        return false;
    }

    // ============= Size Related Methods =============
    private static final int MIN_SIZE = 220;
    private static final int MAX_SIZE = 300;
    private static final int SIZE_INCREMENT = 10;

    private boolean isValidSize(int size) {
        return size >= MIN_SIZE && size <= MAX_SIZE && (size - MIN_SIZE) % SIZE_INCREMENT == 0;
    }

    public int getSizeId(int size) {
        if (!isValidSize(size)) {
            return -1;
        }
        return (size - MIN_SIZE) / SIZE_INCREMENT;
    }

    public int getSizeFromId(int id) {
        if (id < 0 || id > (MAX_SIZE - MIN_SIZE) / SIZE_INCREMENT) {
            return -1;
        }
        return MIN_SIZE + (id * SIZE_INCREMENT);
    }
}
