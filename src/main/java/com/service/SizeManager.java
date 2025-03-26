package com.service;

import com.model.Sizes;

public class SizeManager {
    private final Sizes[] sizes;
    private static final int BASE_SIZE = 220;
    private static final int INCREMENT = 10;
    private static final int SIZE_COUNT = 9;  // 220 to 300 with 10-unit increments

    public SizeManager() {
        sizes = new Sizes[SIZE_COUNT];
        for (int i = 0; i < SIZE_COUNT; i++) {
            // Size = 220 + i*10
            // i=0: 220 + 0*10 = 220
            // i=1: 220 + 1*10 = 230
            // i=2: 220 + 2*10 = 240
            // and so on...
            int sizeDetail = BASE_SIZE + (i * INCREMENT);
            sizes[i] = new Sizes(i + 1, sizeDetail);
        }
    }

    // Read
    public Sizes getSizeById(int sizeId) {
        if (sizeId < 1 || sizeId > SIZE_COUNT) {
            return null;
        }
        return sizes[sizeId - 1];
    }

    public Sizes getSizeByDetail(int sizeDetail) {
        // Check if sizeDetail follows the pattern: 220 + i*10
        if ((sizeDetail - BASE_SIZE) % INCREMENT != 0) {
            return null;
        }
        int i = (sizeDetail - BASE_SIZE) / INCREMENT;
        if (i < 0 || i >= SIZE_COUNT) {
            return null;
        }
        return sizes[i];
    }

    public Sizes[] getAllSizes() {
        return sizes.clone();
    }

    public Sizes[] getSizesInRange(int minSize, int maxSize) {
        // Convert min and max to indices
        int minIndex = (minSize - BASE_SIZE) / INCREMENT;
        int maxIndex = (maxSize - BASE_SIZE) / INCREMENT;
        
        // Validate indices
        if (minIndex < 0) minIndex = 0;
        if (maxIndex >= SIZE_COUNT) maxIndex = SIZE_COUNT - 1;
        if (minIndex > maxIndex) {
            return new Sizes[0];
        }
        
        Sizes[] result = new Sizes[maxIndex - minIndex + 1];
        System.arraycopy(sizes, minIndex, result, 0, result.length);
        return result;
    }

    // Update
    public boolean updateSizeDetail(int sizeId, int newSizeDetail) {
        if (!isValidSize(newSizeDetail)) {
            return false;
        }
        Sizes size = getSizeById(sizeId);
        if (size != null) {
            size.setSize_detail(newSizeDetail);
            return true;
        }
        return false;
    }

    // Validation
    private boolean isValidSize(int size) {
        // Check if size follows the pattern: 220 + i*10
        return (size - BASE_SIZE) % INCREMENT == 0 && 
               size >= BASE_SIZE && 
               size <= (BASE_SIZE + (SIZE_COUNT - 1) * INCREMENT);
    }

    // Utility methods
    public boolean isSizeAvailable(int sizeDetail) {
        return isValidSize(sizeDetail);
    }

    public int getSizeCount() {
        return SIZE_COUNT;
    }

    public int[] getAvailableSizes() {
        int[] result = new int[SIZE_COUNT];
        for (int i = 0; i < SIZE_COUNT; i++) {
            result[i] = BASE_SIZE + (i * INCREMENT);
        }
        return result;
    }
} 