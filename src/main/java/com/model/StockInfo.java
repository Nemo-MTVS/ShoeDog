package com.model;

public class StockInfo {
    private int stockId;
    private int modelId;
    private int colorId;
    private String modelname;
    private String color;
    private int size;
    private int quantity;

    public StockInfo(int stockId, int modelId, int colorId, String modelname, String color, int size, int quantity) {
        this.stockId = stockId;
        this.modelId = modelId;
        this.colorId = colorId;
        this.modelname = modelname;
        this.color = color;
        this.size = size;
        this.quantity = quantity;
    }

    public StockInfo(String modelname, String color, int size) {
        this.modelname = modelname;
        this.color = color;
        this.size = size;
    }


    public int getStockId() {
        return stockId;
    }

    public void setStockId(int stockId) {
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

    public String getModelname() {
        return modelname;
    }

    public void setModelname(String modelname) {
        this.modelname = modelname;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
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
        return "StockInfo{" +
                "stockId=" + stockId +
                ", modelname='" + modelname + '\'' +
                ", color='" + color + '\'' +
                ", size=" + size +
                ", quantity=" + quantity +
                '}';
    }
}
