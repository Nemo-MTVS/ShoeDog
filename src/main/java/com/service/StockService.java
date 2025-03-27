package com.service;

import com.model.Stock;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StockService {
    private final List<Stock> totalStock = new ArrayList<Stock>();

    public void addShoe(Stock stock) {
        totalStock.add(stock);
    }

    public Stock getShoeById(int shoeId) {
        return totalStock.stream()
                .filter(stock -> stock.getShoe_id() == shoeId)
                .findFirst()
                .orElse(null);
    }

    public List<Stock> getShoesByModel(int modelId) {
        return totalStock.stream()
                .filter(stock -> stock.getModel_id() == modelId)
                .collect(Collectors.toList());
    }

    public List<Stock> getAllShoes() {
        return new ArrayList<>(totalStock);
    }

    public boolean updateStock(int shoeId, int newStockQuantity) {
        Stock stock = getShoeById(shoeId);
        if (stock != null) {
            stock.setStock(newStockQuantity);
            return true;
        }
        return false;
    }
//
//    public boolean updateShoeDetails(Stock updatedStock) {
//        Stock existingStock = getShoeById(updatedStock.getShoe_id());
//        if (existingStock != null) {
//            existingStock.setModel_id(updatedStock.getModel_id());
//            existingStock.setSize_id(updatedStock.getSize_id());
//            existingStock.setColor_id(updatedStock.getColor_id());
//            existingStock.setStock(updatedStock.getStock());
//            return true;
//        }
//        return false;
//    }

    public boolean removeShoe(int shoeId) {
        return totalStock.removeIf(stock -> stock.getShoe_id() == shoeId);
    }

    public boolean removeShoe(Stock stock) {
        return totalStock.remove(stock);
    }

    public String listShoes() {
        return totalStock.toString();
    }
}
