package com.model;

public class ShoeModel {
    private int model_id;
    private String model_name;
    private int brand_id;
    private int listPrice;
    private String model_description;

    public ShoeModel(int model_id, String model_name, int brand_id, int listPrice, String model_description) {
        this.model_id = model_id;
        this.model_name = model_name;
        this.brand_id = brand_id;
        this.listPrice = listPrice;
        this.model_description = model_description;
    }

    public int getModel_id() {
        return model_id;
    }

    public void setModel_id(int model_id) {
        this.model_id = model_id;
    }

    public String getModel_name() {
        return model_name;
    }

    public void setModel_name(String model_name) {
        this.model_name = model_name;
    }

    public int getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(int brand_id) {
        this.brand_id = brand_id;
    }

    public int getListPrice() {
        return listPrice;
    }

    public void setListPrice(int listPrice) {
        this.listPrice = listPrice;
    }

    public String getModel_description() {
        return model_description;
    }

    public void setModel_description(String model_description) {
        this.model_description = model_description;
    }


}
