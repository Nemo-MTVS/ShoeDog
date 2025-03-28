package com.service;

import com.dao.StockDao;
import com.model.Stock;
import com.model.StockInfo;
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

    public boolean updateStockDetails(Stock updatedStock) throws SQLException {
        log.info("Updating stock details: {}", updatedStock);
        Stock existingStock = getStockById(updatedStock.getId());
        if (existingStock != null) {
            return stockDao.updateStock(updatedStock);
        }
        log.warn("Stock not found with ID: {}", updatedStock.getId());
        return false;
    }

    public Stock getStockById(int id) throws SQLException {
        log.info("Fetching stock with ID: {}", id);
        return stockDao.getStockById(id);
    }

    public List<Stock> getStockByModel(int modelId) throws SQLException {
        log.info("Fetching stocks for model ID: {}", modelId);
        return stockDao.getStocksByModelId(modelId);
    }

    public List<StockInfo> getAllStocks() throws SQLException {
        List<StockInfo> stocks = stockDao.getAllStocks();

        if(stocks == null) {
            log.error("조회한 재고의 정보가 없거나 DB와 연결하는 과정에서 오류가 발생했습니다.");
            return null;
        }

        return stockDao.getAllStocks();
    }

    // 재고 새롭게 등록
    public boolean addStock(StockInfo stock) throws SQLException {
        log.info("Adding new stock: {}", stock);
        return stockDao.insertStock(stock);
    }


    // 존재하는 재고 값 변경
    public boolean updateStockQuantity(StockInfo stock) throws SQLException {
        //log.info("Updating stock quantity for Model: {}, color: {}, size: {} Quantity to {}", stock.getModelname(), stock.getColor(), stock.getSize(), stock.getQuantity());
        return stockDao.updateStockQuantity(stock);
    }

//    public boolean updateStockQuantity(int id, int newQuantity) throws SQLException {
//        log.info("Updating stock quantity for ID: {} to {}", id, newQuantity);
//        Stock stock = getStockById(id);
//        if (stock != null) {
//            return stockDao.updateStockQuantity(id, newQuantity);
//        }
//        log.warn("Stock not found with ID: {}", id);
//        return false;
//    }


    // 모델 존재하는지 검증
    public boolean isModelExist(String modelname) throws SQLException {
        List<String> existingModelname = stockDao.getModelnameList();
        if (existingModelname == null || existingModelname.isEmpty()) {
            log.error("모델 정보가 없거나 DB와 연결하는 과정에서 오류가 발생했습니다.");
            return false;
        } else {
            return existingModelname.contains(modelname);  // 모델명이 있으면 true
        }
    }

    // 재고 존재하는지 검증
    public StockInfo isStockExist(StockInfo stock) throws SQLException {
        //log.info("Fetching stock with Model: {}, color: {}, size: {}", stock.getModelname(), stock.getColor(), stock.getSize());
        return stockDao.isStockExist(stock);
    }
}
