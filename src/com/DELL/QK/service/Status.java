package com.DELL.QK.service;

/*记录成员状态*/
public class Status {
    public String getNAME() {
        return NAME;
    }

    private final String NAME;

    private Status(String NAME) {
        this.NAME = NAME;
    }
    public static final Status FREE = new Status("FREE");
    public static final Status BUSY = new Status("BUSY");
    public static final Status VACATION = new Status("VACATION");

    @Override
    public String toString() {
        return getNAME();
    }
}
