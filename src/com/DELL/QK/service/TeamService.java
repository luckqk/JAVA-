package com.DELL.QK.service;

import com.DELL.QK.domain.Architect;
import com.DELL.QK.domain.Designer;
import com.DELL.QK.domain.Employee;
import com.DELL.QK.domain.Programmer;

/**
 * @Author AllenQ
 * @Description 关于开发团队成员的管理：添加、删除等
 * @Date 20:45 2020/3/22
 **/
public class TeamService {
    private static int counter = 1;//团队memeberID
    private final int MAX_MEMBER = 5;//团队最大人数
    private Programmer[] team = new Programmer[MAX_MEMBER];//团队情况
    private int total = 0;//团队实际人数
    private int archNum = 0;//团队架构师人数
    private int designNum = 0;//团队设计师人数
    private int pgmrNum = 0;//团队程序员人数


    /**
     * @Author AllenQ
     * @Description 获取当前团队信息
     * @Date 16:06 2020/3/23
     * @Param []
     * @return com.DELL.QK.domain.Programmer[]
     **/
    public Programmer[] getTeam(){
        Programmer[] team = new Programmer[total];
        for(int i = 0; i < total; i++){
            team[i] = this.team[i];
        }
        return team;
    }

    /**
     * @Author AllenQ
     * @Description 增加团队成员
     * @Date 16:08 2020/3/23
     * @Param [e]
     * @return void
     **/
    public void addMember(Employee e) throws TeamException {
//        成员已满，无法添加
        if(total >= MAX_MEMBER){
            throw new TeamException("成员已满，无法添加");
        }
//        该成员不是开发人员，无法添加
        if(!(e instanceof Programmer)){
            throw new TeamException("该成员不是开发人员，无法添加");
        }
//        该员工已在本开发团队中
        if(isExist(e)){
            throw new TeamException("该员工已在本开发团队中");
        }
//        该员工已是某团队成员
//        该员正在休假，无法添加
        Programmer t = (Programmer)e;
        /*BUSY置于前减少了t.getStatus().getNAME()置于前的空指针风险*/
        if("BUSY".equals(t.getStatus().getNAME())){
            throw new TeamException("该员工已是某团队成员");
        }
        if("VACATION".equals(t.getStatus().getNAME())){
            throw new TeamException("该员正在休假，无法添加");
        }
//        团队中至多只能有一名架构师
//        团队中至多只能有两名设计师
//        团队中至多只能有三名程序员
        if(t instanceof Architect){
            if(archNum >= 1)
                throw new TeamException("团队中至多只能有一名架构师");
            else
                archNum++;
        }else if(t instanceof Designer){
            if(designNum >= 2)
                throw new TeamException("团队中至多只能有两名设计师");
            else
                designNum++;
        }else if(t instanceof Programmer){
            if(pgmrNum >= 3)
                throw new TeamException("团队中至多只能有三名程序员");
            else
                pgmrNum++;
        }

        t.setStatus(Status.BUSY);
        t.setMemberId(counter++);
        team[total++] = t;
    }
    
    /**
     * @Author AllenQ
     * @Description 判断员工是否已经为小组成员
     * @Date 21:00 2020/3/23
     * @Param [e]
     * @return boolean
     **/
    private boolean isExist(Employee e) {
        for(int i = 0; i < total; i++){
            if(e.getId() == team[i].getId()){
                return true;
            }
        }
        return false;
    }

    /**
     * @Author AllenQ
     * @Description 去除团队成员
     * @Date 16:09 2020/3/23
     * @Param [memberId]
     * @return void
     **/
    public void removeMember(int memberId) throws TeamException {
        int i = 0;
        for(; i < total; i++){
            if(team[i].getMemberId() == memberId){
                team[i].setStatus(Status.FREE);
                break;
            }
        }
        if(i == total){
            throw new TeamException("找不到指定memberId的员工，删除失败");
        }
        for(int j = i + 1; j < total; j++){
            team[j-1] = team[j];
        }
        team[--total] = null;
    }

}
