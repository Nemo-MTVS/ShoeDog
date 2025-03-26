package com.model;

public class Sizes {
    private int size_id;
    private int size_detail;

    public Sizes(int size_id, int size_detail) {
        this.size_id = size_id;
        this.size_detail = size_detail;
    }

    public int getSize_id() {
        return size_id;
    }

    public void setSize_id(int size_id) {
        this.size_id = size_id;
    }

    public int getSize_detail() {
        return size_detail;
    }

    public void setSize_detail(int size_detail) {
        this.size_detail = size_detail;
    }
}
