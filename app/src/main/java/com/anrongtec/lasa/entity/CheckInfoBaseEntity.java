package com.anrongtec.lasa.entity;

import org.litepal.crud.DataSupport;

import java.io.Serializable;

/**
 * @author libo
 * @Description: 核查比对信息接口——ryxxList 人员基本信息
 * @date 2018/5/22
 */

public class CheckInfoBaseEntity extends DataSupport implements Serializable {
    private String xm;    //姓名
    private String sfzh;//身份证号
    private String mz;//民族
    private String sfzzz;//身份证住址
    private byte[] XP;// 照片

    private String xb;// 性别
    private String id; // id
    private String checkevent_id;// 事件id
    private String rylb;// 人员类别
    private String task_names;// 中标任务
    private String checkpoint_id; // 检查站id
    private String check_user;// 核查人
    private String equipt_id;// 涉笔id
    private String dept_name;// 组织机构名称
    private String pcsj;// 盘查时间
    private String xpurl; //照片路径

    public String getXpUrl() {
        return xpurl;
    }

    public void setXpUrl(String xpUrl) {
        this.xpurl = xpUrl;
    }

    public PersonCheckEntity BaseEntityToPerCheckEntity(PersonCheckEntity person) {
        person.setXm(getXM());
        person.setCheckeventId(getCheckevent_id());
        person.setSfhm(getSFZH());

//        person.setCzsfhm(getcz);
        person.setXb(getXb());
        person.setMz(getMZ());
        person.setZp(getXpUrl());
        person.setAddressName(getSfzzz());
        person.setRylb(getRylb());
        person.setTaskNames(getTask_names());
        person.setCheckPointId(getCheckpoint_id());
        person.setPoliceName(getCheck_user());
        person.setCheckpointName(getDept_name());
//        person.setStartTimeBack(getPcsj());
        return person;
    }

    public CarCheckEntity BaseEntityToCarCheckEntity(CarCheckEntity car) {
        car.setCzxm(getXM());
        car.setCheckeventId(getCheckevent_id());
        car.setCzsfhm(getSFZH());

//        person.setCzsfhm(getcz);
        car.setXb(getXb());
        car.setZp(getXP());
        car.setTaskNames(getTask_names());
        car.setCheckPointId(getCheckpoint_id());
        car.setPoliceName(getCheck_user());
        car.setCheckpointName(getDept_name());
//        person.setStartTimeBack(getPcsj());
        return car;
    }

    public String getXM() {
        return xm;
    }

    public void setXM(String XM) {
        this.xm = XM;
    }

    public String getSFZH() {
        return sfzh;
    }

    public void setSFZH(String SFZH) {
        this.sfzh = SFZH;
    }

    public String getMZ() {
        return mz;
    }

    public void setMZ(String MZ) {
        this.mz = MZ;
    }

    public String getSfzzz() {
        return sfzzz;
    }

    public void setSfzzz(String sfzzz) {
        this.sfzzz = sfzzz;
    }

    public byte[] getXP() {
        return XP;
    }

    public void setXP(byte[] XP) {
        this.XP = XP;
    }

    public String getXb() {
        return xb;
    }

    public void setXb(String xb) {
        this.xb = xb;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCheckevent_id() {
        return checkevent_id;
    }

    public void setCheckevent_id(String checkevent_id) {
        this.checkevent_id = checkevent_id;
    }

    public String getRylb() {
        return rylb;
    }

    public void setRylb(String rylb) {
        this.rylb = rylb;
    }

    public String getTask_names() {
        return task_names;
    }

    public void setTask_names(String task_names) {
        this.task_names = task_names;
    }

    public String getCheckpoint_id() {
        return checkpoint_id;
    }

    public void setCheckpoint_id(String checkpoint_id) {
        this.checkpoint_id = checkpoint_id;
    }

    public String getCheck_user() {
        return check_user;
    }

    public void setCheck_user(String check_user) {
        this.check_user = check_user;
    }

    public String getEquipt_id() {
        return equipt_id;
    }

    public void setEquipt_id(String equipt_id) {
        this.equipt_id = equipt_id;
    }

    public String getDept_name() {
        return dept_name;
    }

    public void setDept_name(String dept_name) {
        this.dept_name = dept_name;
    }

    public String getPcsj() {
        return pcsj;
    }

    public void setPcsj(String pcsj) {
        this.pcsj = pcsj;
    }
}
