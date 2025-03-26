package com.model;

public class Stock {
    private int shoe_id;
    private int model_id;
    private int color;
    private int size;
    private int stock;

    public Stock(int shoe_id, int model_id, int color, int size, int stock) {
        this.shoe_id = shoe_id;
        this.model_id = model_id;
        this.color = color;
        this.size = size;
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

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "shoe_id=" + shoe_id +
                ", model_id=" + model_id +
                ", color=" + color +
                ", size=" + size +
                ", stock=" + stock +
                '}';
    }
}
