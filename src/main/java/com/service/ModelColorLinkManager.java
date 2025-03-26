package com.service;

import com.model.ModelColorLink;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ModelColorLinkManager {
    private final List<ModelColorLink> modelColorLinks = new ArrayList<>();

    // Create
    public void addModelColorLink(int modelId, int colorId) {
        modelColorLinks.add(new ModelColorLink(modelId, colorId));
    }

    public void addModelColorLinks(int modelId, List<Integer> colorIds) {
        for (int colorId : colorIds) {
            modelColorLinks.add(new ModelColorLink(modelId, colorId));
        }
    }

    public void addModelColorLinks(int modelId, int... colorIds) {
        for (int colorId : colorIds) {
            modelColorLinks.add(new ModelColorLink(modelId, colorId));
        }
    }

    // Read
    public List<ModelColorLink> getLinksByModelId(int modelId) {
        return modelColorLinks.stream()
                .filter(link -> link.getModel_id() == modelId)
                .collect(Collectors.toList());
    }

    public List<ModelColorLink> getLinksByColorId(int colorId) {
        return modelColorLinks.stream()
                .filter(link -> link.getColor_id() == colorId)
                .collect(Collectors.toList());
    }

    public List<Integer> getColorIdsForModel(int modelId) {
        return getLinksByModelId(modelId).stream()
                .map(ModelColorLink::getColor_id)
                .collect(Collectors.toList());
    }

    public List<Integer> getModelIdsForColor(int colorId) {
        return getLinksByColorId(colorId).stream()
                .map(ModelColorLink::getModel_id)
                .collect(Collectors.toList());
    }

    public List<ModelColorLink> getAllLinks() {
        return new ArrayList<>(modelColorLinks);
    }

    // Update
    public boolean updateModelColorLink(int modelId, int oldColorId, int newColorId) {
        return modelColorLinks.stream()
                .filter(link -> link.getModel_id() == modelId && link.getColor_id() == oldColorId)
                .findFirst()
                .map(link -> {
                    link.setColor_id(newColorId);
                    return true;
                })
                .orElse(false);
    }

    // Delete
    public boolean removeModelColorLink(int modelId, int colorId) {
        return modelColorLinks.removeIf(link -> 
            link.getModel_id() == modelId && link.getColor_id() == colorId);
    }

    public boolean removeAllLinksForModel(int modelId) {
        return modelColorLinks.removeIf(link -> link.getModel_id() == modelId);
    }

    public boolean removeAllLinksForColor(int colorId) {
        return modelColorLinks.removeIf(link -> link.getColor_id() == colorId);
    }

    // Validation
    public boolean isLinkExists(int modelId, int colorId) {
        return modelColorLinks.stream()
                .anyMatch(link -> link.getModel_id() == modelId && link.getColor_id() == colorId);
    }

    // Utility methods
    public int getLinkCount() {
        return modelColorLinks.size();
    }

    public void clearAllLinks() {
        modelColorLinks.clear();
    }
} 