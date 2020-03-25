package com.DELL.QK.domain;

import com.DELL.QK.service.Status;

public class Programmer extends Employee {
    public int getMemberId() {
        return memberId;
    }

    public Status getStatus() {
        return status;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    public Programmer() {
    }

    public Programmer(int id, String name, int age, double salary, Equipment equipment) {
        super(id, name, age, salary);
        this.equipment = equipment;
    }

    @Override
    public String toString() {
        return getDetail() + "\t程序员\t" + status + "\t\t\t\t\t" + equipment.getDescription();
    }

    private int memberId;//开发团队内id
    private Status status = Status.FREE;
    private Equipment equipment;
}
