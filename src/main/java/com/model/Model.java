package com.model;

public class Model {
    private int id;
    private String modelname;
    private String brandname;
    private int listprice;
    private String description;

    public Model(int id, String modelname, String brandname, int listprice, String description) {
        this.id = id;
        this.modelname = modelname;
        this.brandname = brandname;
        this.listprice = listprice;
        this.description = description;
    }

    public Model(String modelname, String brandname, int listprice, String description) {
        this.modelname = modelname;
        this.brandname = brandname;
        this.listprice = listprice;
        this.description = description;
    }

    public Model(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModelname() {
        return modelname;
    }

    public void setModelname(String modelname) {
        this.modelname = modelname;
    }

    public String getBrandname() {
        return brandname;
    }

    public void setBrandname(String brandname) {
        this.brandname = brandname;
    }

    public int getListprice() {
        return listprice;
    }

    public void setListprice(int listprice) {
        this.listprice = listprice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Model{" +
                "id=" + id +
                ", modelname='" + modelname + '\'' +
                ", brandname='" + brandname + '\'' +
                ", listprice=" + listprice +
                ", description='" + description + '\'' +
                '}';
    }
} 