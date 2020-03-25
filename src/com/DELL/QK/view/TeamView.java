package com.DELL.QK.view;

import com.DELL.QK.domain.Employee;
import com.DELL.QK.domain.Programmer;
import com.DELL.QK.service.NameListService;
import com.DELL.QK.service.TeamException;
import com.DELL.QK.service.TeamService;
import com.sun.org.apache.xpath.internal.functions.FuncFalse;

/**
 * @ClassName TeamView
 * @Description 交互界面显示
 * @Author AllenQ
 * @Date 2020/3/24 10:55
 * @Version 1.0
 **/
public class TeamView {
    private NameListService listSvc = new NameListService();
    private TeamService teamSvc = new TeamService();
    /**
     * @Author AllenQ
     * @Description 主界面显示及控制方法
     * @Date 10:59 2020/3/24
     * @Param []
     * @return void
     **/
    public void enterMainMenu(){
        boolean loopFlag = true;
        char choice = 0;
        while(loopFlag){
            if(choice != '1')
                listAllEmployees();
            System.out.print("1-团队列表  2-添加团队成员  3-删除团队成员 4-退出   请选择(1-4)：");
            choice = TSUtility.readMenuSelection();
            switch(choice){
                case '1':
                    getTeam();
                    break;
                case '2':
                    addMember();
                    break;
                case '3':
                    deleteMember();
                    break;
                case '4':
                    System.out.println("确认是否退出Y/N");
                    char isExit = TSUtility.readConfirmSelection();
                    if(isExit == 'Y')
                        loopFlag = false;
                    break;
            }
        }
        
    }
    /**
     * @Author AllenQ
     * @Description 以表格形式列出公司所有成员
     * @Date 10:59 2020/3/24
     * @Param []
     * @return void
     **/
    private void listAllEmployees(){
        System.out.println("-------------------------------------开发团队调度软件--------------------------------------\n");
        System.out.print("ID\t姓名\t年龄\t工资\t职位\t状态\t奖金\t股票\t领用设备\n");
        Employee[] employees = listSvc.getAllEmployees();
        for(int i = 0; i < employees.length; i++){
            System.out.println(employees[i]);
        }
        System.out.println("----------------------------------------------------------------------------------------------");
    }
    /**
     * @Author AllenQ
     * @Description 显示团队成员列表操作
     * @Date 11:00 2020/3/24
     * @Param []
     * @return void
     **/
    private void getTeam(){
        System.out.println("--------------------团队成员列表---------------------\n");
        System.out.println("TDI/ID\t姓名\t年龄\t工资\t职位\t奖金\t股票");
        Programmer[] team = teamSvc.getTeam();
        if(team == null || team.length == 0){
            System.out.println("团队目前无成员");
        }else{
            for(int i = 0; i < team.length; i++){
                System.out.println(team[i].getMemberId() + "/" + team[i].toString());
            }
        }
        System.out.println("-----------------------------------------------------");
    }
    /**
     * @Author AllenQ
     * @Description 实现添加成员操作
     * @Date 11:00 2020/3/24
     * @Param []
     * @return void
     **/
    private void addMember(){
        System.out.print("请输入要添加的员工ID：");
        int memberID = TSUtility.readInt();
        try{
            Employee employee = listSvc.getEmployee(memberID);
            teamSvc.addMember(employee);
            System.out.println("添加成功");
        }catch(TeamException e){
            System.out.println("添加失败，原因:" + e.getMessage());
        }
        TSUtility.readReturn();
    }
    /**
     * @Author AllenQ
     * @Description 实现删除成员操作
     * @Date 11:00 2020/3/24
     * @Param []
     * @return void
     **/
    private void deleteMember(){
        System.out.print("请输入要添加的员工TID：");
        int memberID = TSUtility.readInt();
        System.out.println("确认是否要删除Y/N");
        char isDele = TSUtility.readConfirmSelection();
        if(isDele == 'N')
            return;
        try{
            teamSvc.removeMember(memberID);
            System.out.println("删除成功");
        }catch(TeamException e){
            System.out.println("删除失败，原因:" + e.getMessage());
        }
        TSUtility.readReturn();
    }
    
    public static void main(String[] args){
        TeamView a = new TeamView();
        a.enterMainMenu();
    }
    
}
