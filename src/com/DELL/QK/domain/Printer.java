package com.DELL.QK.domain;

public class Printer implements Equipment {
    public Printer(String model, String type) {
        this.model = model;
        this.type = type;
    }

    public Printer() {
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getModel() {
        return model;
    }

    public String getType() {
        return type;
    }

    private String model;//机器的型号
    private String type;//机器的类型

    @Override
    public String getDescription() {
        return model + "(" +type + ")";
    }
}
