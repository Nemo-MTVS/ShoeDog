package com.model;

public class Colors {
    private int color_id;
    private String color_name;

    @Override
    public String toString() {
        return "Colors{" +
                "color_id=" + color_id +
                ", color_name='" + color_name + '\'' +
                '}';
    }

    public Colors(int color_id, String color_name) {
        this.color_id = color_id;
        this.color_name = color_name;
    }

    public Colors(String color_name) {
        this.color_name = color_name;
    }


    public int getColor_id() {
        return color_id;
    }

    public void setColor_id(int color_id) {
        this.color_id = color_id;
    }

    public String getColor_name() {
        return color_name;
    }

    public void setColor_name(String color_name) {
        this.color_name = color_name;
    }
}
