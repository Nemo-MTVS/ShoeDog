package com.service;

import com.model.Brands;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BrandManagerTest {
    private BrandManager brandManager;

    @BeforeEach
    void setUp() {
        brandManager = new BrandManager();
        brandManager.addBrand(new Brands(1, "Nike"));
        brandManager.addBrand(new Brands(2, "Adidas"));
        brandManager.addBrand(new Brands(3, "NewBalance"));
        brandManager.addBrand(new Brands(4, "Asics"));
    }

    @Test
    void addBrand() {
        Brands newBrand = new Brands(5, "Puma");
        brandManager.addBrand(newBrand);
        
        Brands retrievedBrand = brandManager.getBrandById(5);
        assertNotNull(retrievedBrand);
        assertEquals("Puma", retrievedBrand.getBrand_name());
    }

    @Test
    void getBrandById() {
        // Test existing brand
        Brands brand = brandManager.getBrandById(1);
        assertNotNull(brand);
        assertEquals("Nike", brand.getBrand_name());
        
        // Test non-existing brand
        Brands nonExistingBrand = brandManager.getBrandById(999);
        assertNull(nonExistingBrand);
    }

    @Test
    void getBrandByName() {
        // Test existing brand
        Brands brand = brandManager.getBrandByName("Nike");
        assertNotNull(brand);
        assertEquals(1, brand.getBrand_id());
        
        // Test case-insensitive search
        Brands brandCaseInsensitive = brandManager.getBrandByName("nike");
        assertNotNull(brandCaseInsensitive);
        assertEquals(1, brandCaseInsensitive.getBrand_id());
        
        // Test non-existing brand
        Brands nonExistingBrand = brandManager.getBrandByName("NonExistingBrand");
        assertNull(nonExistingBrand);
    }

    @Test
    void getAllBrands() {
        List<Brands> brands = brandManager.getAllBrands();
        assertEquals(4, brands.size());
        assertTrue(brands.stream().anyMatch(brand -> brand.getBrand_name().equals("Nike")));
        assertTrue(brands.stream().anyMatch(brand -> brand.getBrand_name().equals("Adidas")));
        assertTrue(brands.stream().anyMatch(brand -> brand.getBrand_name().equals("NewBalance")));
        assertTrue(brands.stream().anyMatch(brand -> brand.getBrand_name().equals("Asics")));
    }

    @Test
    void updateBrandName() {
        // Test successful update
        boolean success = brandManager.updateBrandName(1, "Nike Updated");
        assertTrue(success);
        assertEquals("Nike Updated", brandManager.getBrandById(1).getBrand_name());
        
        // Test update with non-existing brand
        boolean failure = brandManager.updateBrandName(999, "Non Existing");
        assertFalse(failure);
    }

    @Test
    void removeBrand() {
        // Test successful removal by ID
        boolean success = brandManager.removeBrand(1);
        assertTrue(success);
        assertNull(brandManager.getBrandById(1));
        System.out.println("After removing brand with ID 1: " + brandManager.getAllBrands());
        
        // Test removal of non-existing brand
        boolean failure = brandManager.removeBrand(999);
        assertFalse(failure);
        
        // Verify remaining brands
        List<Brands> remainingBrands = brandManager.getAllBrands();
        assertEquals(3, remainingBrands.size());
        assertTrue(remainingBrands.stream().anyMatch(brand -> brand.getBrand_name().equals("Adidas")));
        assertTrue(remainingBrands.stream().anyMatch(brand -> brand.getBrand_name().equals("NewBalance")));
        assertTrue(remainingBrands.stream().anyMatch(brand -> brand.getBrand_name().equals("Asics")));
    }
}