package com.DELL.QK.domain;

public class Architect extends Designer{
    public Architect() {
    }

    public Architect(int id, String name, int age, double salary, Equipment equipment, double bonus, int stock) {
        super(id, name, age, salary, equipment, bonus);
        this.stock = stock;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return getDetail() + "\t架构师\t" + getStatus() + "\t" + getBonus() + "\t" + getStock()
                + "\t" + getEquipment().getDescription();
    }

    private int stock;

}
