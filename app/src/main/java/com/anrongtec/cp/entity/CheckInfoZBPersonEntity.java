package com.anrongtec.cp.entity;

import org.litepal.crud.DataSupport;

import java.io.Serializable;

/**
 * @author libo
 * @Description: 核查比对信息接口——zbryList  中标人员信息
 * @date 2018/5/22
 */

public class CheckInfoZBPersonEntity extends DataSupport implements Serializable {

    private String rylb;            //中标人员类别
    private String clfs;            //中标处置方式
    private String aqms;            //中标案情描述
    private String bklxr;            //中标联系人
    private String bklxfs;            //中标联系人方式
    private String rwmc;            //中标任务名称
    private String rwid;            //中标任务ID
    private String pfirstid;        //初始版本号
    private String pverid;            //布控包ID
    private String xm;
    private String sfzh;


    public PersonCheckEntity addZBPersonInfo(PersonCheckEntity personCheckEntity) {
        personCheckEntity.setRylb(getRYLB());
        personCheckEntity.setCzfs(getCLFS());
        personCheckEntity.setRemark(getAQMS());
        personCheckEntity.setBklxrName(getBKLXR());
        personCheckEntity.setBkrCall(getBKLXFS());
        personCheckEntity.setTaskNames(getRWMC());
        personCheckEntity.setCheckPointId(getRWID());
//        personCheckEntity.setCrsfzh();

        return personCheckEntity;


    }

    public String getRYLB() {
        return rylb;
    }

    public void setRYLB(String RYLB) {
        this.rylb = RYLB;
    }

    public String getCLFS() {
        return clfs;
    }

    public void setCLFS(String CLFS) {
        this.clfs = CLFS;
    }

    public String getAQMS() {
        return aqms;
    }

    public void setAQMS(String AQMS) {
        this.aqms = AQMS;
    }

    public String getBKLXR() {
        return bklxr;
    }

    public void setBKLXR(String BKLXR) {
        this.bklxr = BKLXR;
    }

    public String getBKLXFS() {
        return bklxfs;
    }

    public void setBKLXFS(String BKLXFS) {
        this.bklxfs = BKLXFS;
    }

    public String getRWMC() {
        return rwmc;
    }

    public void setRWMC(String RWMC) {
        this.rwmc = RWMC;
    }

    public String getRWID() {
        return rwid;
    }

    public void setRWID(String RWID) {
        this.rwid = RWID;
    }

    public String getPFIRSTID() {
        return pfirstid;
    }

    public void setPFIRSTID(String PFIRSTID) {
        this.pfirstid = PFIRSTID;
    }

    public String getPVERID() {
        return pverid;
    }

    public void setPVERID(String PVERID) {
        this.pverid = PVERID;
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
}
