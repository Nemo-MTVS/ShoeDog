package com.model;

public class ModelColorLink {
    private int model_id;
    private int color_id;

    public ModelColorLink(int model_id, int color_id) {
        this.model_id = model_id;
        this.color_id = color_id;
    }

    public int getModel_id() {
        return model_id;
    }

    public void setModel_id(int model_id) {
        this.model_id = model_id;
    }

    public int getColor_id() {
        return color_id;
    }

    public void setColor_id(int color_id) {
        this.color_id = color_id;
    }
}
