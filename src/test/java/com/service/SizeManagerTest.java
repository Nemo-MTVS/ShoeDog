package com.service;

import com.model.Sizes;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SizeManagerTest {
    private final SizeManager sizeManager = new SizeManager();

    @Test
    void getSizeById() {
        // Test existing size
        Sizes size = sizeManager.getSizeById(1);
        assertNotNull(size);
        assertEquals(220, size.getSize_detail());
        
        // Test non-existing size
        Sizes nonExistingSize = sizeManager.getSizeById(999);
        assertNull(nonExistingSize);
    }

    @Test
    void getSizeByDetail() {
        // Test existing size
        Sizes size = sizeManager.getSizeByDetail(220);
        assertNotNull(size);
        assertEquals(1, size.getSize_id());
        
        // Test non-existing size
        Sizes nonExistingSize = sizeManager.getSizeByDetail(999);
        assertNull(nonExistingSize);
    }

    @Test
    void getAllSizes() {
        Sizes[] sizes = sizeManager.getAllSizes();
        assertEquals(9, sizes.length);
        assertEquals(220, sizes[0].getSize_detail());
        assertEquals(300, sizes[8].getSize_detail());
    }

    @Test
    void getSizesInRange() {
        // Test valid range
        Sizes[] sizes = sizeManager.getSizesInRange(230, 250);
        assertEquals(3, sizes.length);
        assertEquals(230, sizes[0].getSize_detail());
        assertEquals(250, sizes[2].getSize_detail());
        
        // Test range outside bounds
        Sizes[] outOfBounds = sizeManager.getSizesInRange(200, 350);
        assertEquals(9, outOfBounds.length); // Should return all available sizes
    }

    @Test
    void updateSizeDetail() {
        // Test successful update
        boolean success = sizeManager.updateSizeDetail(1, 225);
        System.out.println();
        assertTrue(success);
        assertEquals(225, sizeManager.getSizeById(1).getSize_detail());
        
        // Test update with non-existing size
        boolean failure = sizeManager.updateSizeDetail(999, 225);
        assertFalse(failure);
        
        // Test update with invalid size
        boolean invalidSize = sizeManager.updateSizeDetail(1, 999);
        assertFalse(invalidSize);
    }

    @Test
    void isSizeAvailable() {
        assertTrue(sizeManager.isSizeAvailable(220));
        assertTrue(sizeManager.isSizeAvailable(230));
        assertTrue(sizeManager.isSizeAvailable(300));
        assertFalse(sizeManager.isSizeAvailable(225));
        assertFalse(sizeManager.isSizeAvailable(310));
    }

    @Test
    void getSizeCount() {
        assertEquals(9, sizeManager.getSizeCount());
    }

    @Test
    void getAvailableSizes() {
        int[] sizes = sizeManager.getAvailableSizes();
        assertEquals(9, sizes.length);
        assertEquals(220, sizes[0]);
        assertEquals(300, sizes[8]);
    }
} 