package com.model;

public class Stock {
    private int shoe_id;
    private int model_id;
    private int size_id;
    private int color_id;
    private int stock;

    public Stock(int shoe_id, int model_id, int size_id, int color_id, int stock) {
        this.shoe_id = shoe_id;
        this.model_id = model_id;
        this.size_id = size_id;
        this.color_id = color_id;
        this.stock = stock;
    }

    public int getShoe_id() {
        return shoe_id;
    }

    public void setShoe_id(int shoe_id) {
        this.shoe_id = shoe_id;
    }

    public int getModel_id() {
        return model_id;
    }

    public void setModel_id(int model_id) {
        this.model_id = model_id;
    }

    public int getSize_id() {
        return size_id;
    }

    public void setSize_id(int size_id) {
        this.size_id = size_id;
    }

    public int getColor_id() {
        return color_id;
    }

    public void setColor_id(int color_id) {
        this.color_id = color_id;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Shoe"
                + "shoe_id=" + shoe_id + '\''
                + ", model_id=" + model_id + '\''
                + ", size_id=" + size_id + '\''
                + ", color_id=" + color_id + '\''
                + ", stock=" + stock + '\''
                + "}";
    }
}
