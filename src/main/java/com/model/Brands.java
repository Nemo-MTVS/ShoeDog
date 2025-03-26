package com.model;

import java.util.Objects;

public class Brands {
    private int brand_id;
    private String brand_name;

    public Brands(int brand_id, String brand_name) {
        this.brand_id = brand_id;
        this.brand_name = brand_name;
    }

    public int getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(int brand_id) {
        this.brand_id = brand_id;
    }

    public String getBrand_name() {
        return brand_name;
    }

    public void setBrand_name(String brand_name) {
        this.brand_name = brand_name;
    }

    @Override
    public String toString() {
        return "Brand[id=" + brand_id + ", name='" + brand_name + "']";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Brands brands = (Brands) o;
        return brand_id == brands.brand_id && 
               Objects.equals(brand_name, brands.brand_name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand_id, brand_name);
    }
}
