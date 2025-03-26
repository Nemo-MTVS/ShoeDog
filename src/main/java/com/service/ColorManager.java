package com.service;

import com.model.Colors;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ColorManager {
    private final List<Colors> colors = new ArrayList<>();

    // Create
    public void addColor(Colors color) {
        colors.add(color);
    }

    // Read
    public Colors getColorById(int colorId) {
        return colors.stream()
                .filter(color -> color.getColor_id() == colorId)
                .findFirst()
                .orElse(null);
    }

    public Colors getColorByName(String colorName) {
        return colors.stream()
                .filter(color -> color.getColor_name().equalsIgnoreCase(colorName))
                .findFirst()
                .orElse(null);
    }

    public List<Colors> getAllColors() {
        return new ArrayList<>(colors);
    }

    // Update
    public boolean updateColorName(int colorId, String newName) {
        Colors color = getColorById(colorId);
        if (color != null) {
            color.setColor_name(newName);
            return true;
        }
        return false;
    }

    // Delete
    public boolean removeColor(int colorId) {
        return colors.removeIf(color -> color.getColor_id() == colorId);
    }

    public boolean removeColor(Colors color) {
        return colors.remove(color);
    }
} 