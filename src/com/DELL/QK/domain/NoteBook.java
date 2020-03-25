package com.DELL.QK.domain;

public class NoteBook implements Equipment {
    public NoteBook(String model, String price) {
        this.model = model;
        this.price = price;
    }

    public NoteBook() {
    }

    public String getModel() {
        return model;
    }

    public String getPrice() {
        return price;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    private String model;//机器型号
    private String price;//机器价格

    @Override
    public String getDescription() {
        return model + "(" + price + ")";
    }
}
