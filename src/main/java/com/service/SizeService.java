//package com.service;
//
//import com.dao.ModelDao;
//import com.dao.SizeDao;
//import com.model.Size;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.sql.Connection;
//
//public class SizeService {
//    private static Logger log = LoggerFactory.getLogger(SizeService.class);
//    private final SizeDao sizeDao;
//    private Connection connection;
//
//    private final Size[] sizes;
//    private static final int BASE_SIZE = 220;
//    private static final int INCREMENT = 10;
//    private static final int SIZE_COUNT = 9;  // 220 to 300 with 10-unit increments
//
//    public SizeService() {
//        sizes = new Size[SIZE_COUNT];
//        for (int i = 0; i < SIZE_COUNT; i++) {
//            int sizeDetail = BASE_SIZE + (i * INCREMENT);
//            sizes[i] = new Size(i + 1, sizeDetail);
//        }
//    }
//
//    // Read
//    public Size getSizeById(int sizeId) {
//        if (sizeId < 1 || sizeId > SIZE_COUNT) {
//            return null;
//        }
//        return sizes[sizeId - 1];
//    }
//
//    public Size getSizeByDetail(int sizeDetail) {
//        // Check if sizeDetail follows the pattern: 220 + i*10
//        if ((sizeDetail - BASE_SIZE) % INCREMENT != 0) {
//            return null;
//        }
//        int i = (sizeDetail - BASE_SIZE) / INCREMENT;
//        if (i < 0 || i >= SIZE_COUNT) {
//            return null;
//        }
//        return sizes[i];
//    }
//
//    public Size[] getAllSizes() {
//        return sizes.clone();
//    }
//
//    public Size[] getSizesInRange(int minSize, int maxSize) {
//        // Convert min and max to indices
//        int minIndex = (minSize - BASE_SIZE) / INCREMENT;
//        int maxIndex = (maxSize - BASE_SIZE) / INCREMENT;
//
//        // Validate indices
//        if (minIndex < 0) minIndex = 0;
//        if (maxIndex >= SIZE_COUNT) maxIndex = SIZE_COUNT - 1;
//        if (minIndex > maxIndex) {
//            return new Size[0];
//        }
//
//        Size[] result = new Size[maxIndex - minIndex + 1];
//        System.arraycopy(sizes, minIndex, result, 0, result.length);
//        return result;
//    }
//
//    // Update
//    public boolean updateSizeDetail(int sizeId, int newSizeDetail) {
//        if (!isValidSize(newSizeDetail)) {
//            return false;
//        }
//        Size size = getSizeById(sizeId);
//        if (size != null) {
//            size.setSize_detail(newSizeDetail);
//            return true;
//        }
//        return false;
//    }
//
//    // Validation
//    private boolean isValidSize(int size) {
//        // Check if size follows the pattern: 220 + i*10
//        return (size - BASE_SIZE) % INCREMENT == 0 &&
//               size >= BASE_SIZE &&
//               size <= (BASE_SIZE + (SIZE_COUNT - 1) * INCREMENT);
//    }
//
//    // Utility methods
//    public boolean isSizeAvailable(int sizeDetail) {
//        return isValidSize(sizeDetail);
//    }
//
//    public int getSizeCount() {
//        return SIZE_COUNT;
//    }
//
//    public int[] getAvailableSizes() {
//        int[] result = new int[SIZE_COUNT];
//        for (int i = 0; i < SIZE_COUNT; i++) {
//            result[i] = BASE_SIZE + (i * INCREMENT);
//        }
//        return result;
//    }
//}