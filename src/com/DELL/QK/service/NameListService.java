package com.DELL.QK.service;

import com.DELL.QK.domain.*;
import org.omg.PortableInterceptor.INACTIVE;

/**
 * 负责将Data中的数据封装到Employee[]数组中，同时提供相关操作Employee[]的方法
 */
public class NameListService {
    private Employee[] employees;

    /*初始化Employee[]*/
    public NameListService() {
        employees = new Employee[Data.EMPLOYEES.length];

        for(int i = 0; i < Data.EMPLOYEES.length; i++){
            Integer type = Integer.parseInt(Data.EMPLOYEES[i][0]);
            int id = Integer.parseInt(Data.EMPLOYEES[i][1]);
            String name = Data.EMPLOYEES[i][2];
            int age = Integer.parseInt(Data.EMPLOYEES[i][3]);
            double salary = Double.parseDouble(Data.EMPLOYEES[i][4]);

            Equipment equipment;
            double bonus;
            int stock;

            switch(type){
                case Data.EMPLOYEE://10
                    employees[i] = new Employee(id, name, age, salary);
                    break;
                case Data.PROGRAMMER://11
                    equipment = getEquipment(i);
                    employees[i] = new Programmer(id, name, age, salary, equipment);
                    break;
                case Data.DESIGNER://12
                    equipment = getEquipment(i);
                    bonus = Double.parseDouble(Data.EMPLOYEES[i][4]);
                    employees[i] = new Designer(id, name, age, salary, equipment, bonus);
                    break;
                case Data.ARCHITECT://13
                    equipment = getEquipment(i);
                    bonus = Double.parseDouble(Data.EMPLOYEES[i][4]);
                    stock = Integer.parseInt(Data.EMPLOYEES[i][5]);
                    employees[i] = new Architect(id, name, age, salary, equipment, bonus, stock);
                    break;
            }
        }
    }

    /*获得id的设备*/
    public Equipment getEquipment(int id){
        int eType = Integer.parseInt(Data.EQUIPMENTS[id][0]);
        String model;
        switch(eType){
            case Data.PC://21
                model = Data.EQUIPMENTS[id][1];
                String display = Data.EQUIPMENTS[id][2];
                return new PC(model, display);
            case Data.NOTEBOOK://22
                model = Data.EQUIPMENTS[id][1];
                String price = Data.EQUIPMENTS[id][2];
                return new NoteBook(model, price);
            case Data.PRINTER://23
                String name = Data.EQUIPMENTS[id][1];
                String type = Data.EQUIPMENTS[id][2];
                return new Printer(name, type);
        }
        return null;
    }
    /**
     * @Author AllenQ
     * @Description 获取当前所有员工
     * @Date 21:15 2020/3/22
     * @Param []
     * @return com.DELL.QK.domain.Employee[]
     **/
    public Employee[] getAllEmployees(){
        return employees;
    }
    /**
     * @Author AllenQ
     * @Description 获取指定ID的员工对象
     * @Date 21:16 2020/3/22
     * @Param [id]
     * @return com.DELL.QK.domain.Employee
     **/
    public Employee getEmployee(int id) throws TeamException {
//        Employee employee;
        for(int i = 0; i < Data.EMPLOYEES.length; i++){
            if(employees[i].getId() == id)
                return employees[i];
        }
        throw new TeamException("找不到指定的员工");
    }

}
