package com.service;

import com.model.Colors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ColorManagerTest {
    private ColorManager colorManager;

    @BeforeEach
    void setUp() {
        colorManager = new ColorManager();
        colorManager.addColor(new Colors(1, "Red"));
        colorManager.addColor(new Colors(2, "Blue"));
        colorManager.addColor(new Colors(3, "Green"));
    }

    @Test
    void addColor() {
        Colors newColor = new Colors(4, "Yellow");
        colorManager.addColor(newColor);
        
        Colors retrievedColor = colorManager.getColorById(4);
        assertNotNull(retrievedColor);
        assertEquals("Yellow", retrievedColor.getColor_name());
    }

    @Test
    void getColorById() {
        // Test existing color
        Colors color = colorManager.getColorById(1);
        assertNotNull(color);
        assertEquals("Red", color.getColor_name());
        
        // Test non-existing color
        Colors nonExistingColor = colorManager.getColorById(999);
        assertNull(nonExistingColor);
    }

    @Test
    void getColorByName() {
        // Test existing color
        Colors color = colorManager.getColorByName("Red");
        assertNotNull(color);
        assertEquals(1, color.getColor_id());
        
        // Test case-insensitive search
        Colors colorCaseInsensitive = colorManager.getColorByName("red");
        assertNotNull(colorCaseInsensitive);
        assertEquals(1, colorCaseInsensitive.getColor_id());
        
        // Test non-existing color
        Colors nonExistingColor = colorManager.getColorByName("NonExistingColor");
        assertNull(nonExistingColor);
    }

    @Test
    void getAllColors() {
        List<Colors> colors = colorManager.getAllColors();
        assertEquals(3, colors.size());
        assertTrue(colors.stream().anyMatch(color -> color.getColor_name().equals("Red")));
        assertTrue(colors.stream().anyMatch(color -> color.getColor_name().equals("Blue")));
        assertTrue(colors.stream().anyMatch(color -> color.getColor_name().equals("Green")));
    }

    @Test
    void updateColorName() {
        // Test successful update
        boolean success = colorManager.updateColorName(1, "Red Updated");
        assertTrue(success);
        assertEquals("Red Updated", colorManager.getColorById(1).getColor_name());
        
        // Test update with non-existing color
        boolean failure = colorManager.updateColorName(999, "Non Existing");
        assertFalse(failure);
    }

    @Test
    void removeColor() {
        // Test successful removal
        boolean success = colorManager.removeColor(1);
        assertTrue(success);
        assertNull(colorManager.getColorById(1));
        
        // Test removal of non-existing color
        boolean failure = colorManager.removeColor(999);
        assertFalse(failure);
    }
} 