package com.model;

public class Stock {
    private int stockId;
    private int modelId;
    private int colorId;
    private int size;
    private int quantity;

    public Stock(int stockId, int modelId, int colorId, int size, int quantity) {
        this.stockId = stockId;
        this.modelId = modelId;
        this.colorId = colorId;
        this.size = size;
        this.quantity = quantity;
    }

    public int getId() {
        return stockId;
    }

    public void setId(int stockId) {
        this.stockId = stockId;
    }

    public int getModelId() {
        return modelId;
    }

    public void setModelId(int modelId) {
        this.modelId = modelId;
    }

    public int getColorId() {
        return colorId;
    }

    public void setColorId(int colorId) {
        this.colorId = colorId;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return String.format("Stock{id=%d, model=%d, color=%d, size=%d, quantity=%d}",
                stockId, modelId, colorId, size, quantity);
    }
}
