package com.service;

import com.model.Brands;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BrandManager {
    private final List<Brands> brands = new ArrayList<>();

    // Create
    public void addBrand(Brands brand) {
        brands.add(brand);
    }

    // Read
    public Brands getBrandById(int brandId) {
        return brands.stream()
                .filter(brand -> brand.getBrand_id() == brandId)
                .findFirst()
                .orElse(null);
    }

    public Brands getBrandByName(String brandName) {
        return brands.stream()
                .filter(brand -> brand.getBrand_name().equalsIgnoreCase(brandName))
                .findFirst()
                .orElse(null);
    }

    public List<Brands> getAllBrands() {
        return new ArrayList<>(brands);
    }

    // Update
    public boolean updateBrandName(int brandId, String newName) {
        Brands brand = getBrandById(brandId);
        if (brand != null) {
            brand.setBrand_name(newName);
            return true;
        }
        return false;
    }

    // Delete
    public boolean removeBrand(int brandId) {
        return brands.removeIf(brand -> brand.getBrand_id() == brandId);
    }
} 