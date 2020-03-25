package com.DELL.QK.service;

/**
 * @ClassName TeamException
 * @Description 自定义异常类
 * @Author AllenQ
 * @Date 2020/3/22 21:26
 * @Version 1.0
 **/
public class TeamException extends Exception{
    static final long serialVersionUID = -338751624229948L;

    public TeamException() {
    }

    public TeamException(String message) {
        super(message);
    }
}
