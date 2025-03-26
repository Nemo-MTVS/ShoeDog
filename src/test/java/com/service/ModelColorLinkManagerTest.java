package com.service;

import com.model.ModelColorLink;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ModelColorLinkManagerTest {
    private ModelColorLinkManager linkManager;

    @BeforeEach
    void setUp() {
        linkManager = new ModelColorLinkManager();
        linkManager.addModelColorLink(1, 1);
        linkManager.addModelColorLink(1, 2);
        linkManager.addModelColorLink(2, 1);
    }

    @Test
    void addModelColorLink() {
        linkManager.addModelColorLink(3, 3);
        assertTrue(linkManager.isLinkExists(3, 3));
    }

    @Test
    void addModelColorLinks() {
        List<Integer> colorIds = Arrays.asList(3, 4, 5);
        linkManager.addModelColorLinks(3, colorIds);
        
        assertTrue(linkManager.isLinkExists(3, 3));
        assertTrue(linkManager.isLinkExists(3, 4));
        assertTrue(linkManager.isLinkExists(3, 5));
    }

    @Test
    void addModelColorLinksVarargs() {
        linkManager.addModelColorLinks(3, 3, 4, 5);
        
        assertTrue(linkManager.isLinkExists(3, 3));
        assertTrue(linkManager.isLinkExists(3, 4));
        assertTrue(linkManager.isLinkExists(3, 5));
    }

    @Test
    void getLinksByModelId() {
        List<ModelColorLink> links = linkManager.getLinksByModelId(1);
        assertEquals(2, links.size());
        assertTrue(links.stream().allMatch(link -> link.getModel_id() == 1));
    }

    @Test
    void getLinksByColorId() {
        List<ModelColorLink> links = linkManager.getLinksByColorId(1);
        assertEquals(2, links.size());
        assertTrue(links.stream().allMatch(link -> link.getColor_id() == 1));
    }

    @Test
    void getColorIdsForModel() {
        List<Integer> colorIds = linkManager.getColorIdsForModel(1);
        assertEquals(2, colorIds.size());
        assertTrue(colorIds.contains(1));
        assertTrue(colorIds.contains(2));
    }

    @Test
    void getModelIdsForColor() {
        List<Integer> modelIds = linkManager.getModelIdsForColor(1);
        assertEquals(2, modelIds.size());
        assertTrue(modelIds.contains(1));
        assertTrue(modelIds.contains(2));
    }

    @Test
    void getAllLinks() {
        List<ModelColorLink> links = linkManager.getAllLinks();
        assertEquals(3, links.size());
    }

    @Test
    void updateModelColorLink() {
        // Test successful update
        boolean success = linkManager.updateModelColorLink(1, 1, 3);
        assertTrue(success);
        assertTrue(linkManager.isLinkExists(1, 3));
        assertFalse(linkManager.isLinkExists(1, 1));
        
        // Test update with non-existing link
        boolean failure = linkManager.updateModelColorLink(999, 1, 3);
        assertFalse(failure);
    }

    @Test
    void removeModelColorLink() {
        // Test successful removal
        boolean success = linkManager.removeModelColorLink(1, 1);
        assertTrue(success);
        assertFalse(linkManager.isLinkExists(1, 1));
        
        // Test removal of non-existing link
        boolean failure = linkManager.removeModelColorLink(999, 999);
        assertFalse(failure);
    }

    @Test
    void removeAllLinksForModel() {
        boolean success = linkManager.removeAllLinksForModel(1);
        assertTrue(success);
        assertEquals(0, linkManager.getLinksByModelId(1).size());
    }

    @Test
    void removeAllLinksForColor() {
        boolean success = linkManager.removeAllLinksForColor(1);
        assertTrue(success);
        assertEquals(0, linkManager.getLinksByColorId(1).size());
    }

    @Test
    void isLinkExists() {
        assertTrue(linkManager.isLinkExists(1, 1));
        assertTrue(linkManager.isLinkExists(1, 2));
        assertFalse(linkManager.isLinkExists(999, 999));
    }

    @Test
    void getLinkCount() {
        assertEquals(3, linkManager.getLinkCount());
    }

    @Test
    void clearAllLinks() {
        linkManager.clearAllLinks();
        assertEquals(0, linkManager.getLinkCount());
    }
} 