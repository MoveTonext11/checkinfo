package com.anrongtec.lasa.entity;

import org.litepal.crud.DataSupport;

import java.io.Serializable;

/**
 * @author libo
 * @Description: 核查比对信息接口——zbclList  中标车辆信息
 * @date 2018/5/22
 */

public class CheckInfoZBCarEntity extends DataSupport implements Serializable {

    private String cllb;            //中标车辆类别
    private String clfs;            //中标处理方式
    private String aqms;            //中标案情描述
    private String bklxr;            //中标布控联系人
    private String bklxfs;            //中标布控联系方式
    private String rwmc;            //中标任务名称
    private String rwid;            //中标任务ID
    private String pfirstid;        //中标初始版本号
    private String pverid;            //中标版本号
    private String czsfzh;            //车主身份证号
    private String czxm;            //车主姓名
    private String clsbdm;            //车辆识别代码
    private String clys;            //车辆颜色
    private String fdjh;            //发动机号
    private String cphm;            //车牌号


    public CarCheckEntity addZBCarInfo(CarCheckEntity carCheckEntity) {
        carCheckEntity.setFdjh(getFDJH());
        carCheckEntity.setClsbdm(getCLSBDM());
        carCheckEntity.setCzxm(getCZXM());
        carCheckEntity.setCzsfhm(getCZSFZH());
        carCheckEntity.setCllb(getCLLB());
        carCheckEntity.setClys(getCLYS());
        carCheckEntity.setCph(getCPHM());
        carCheckEntity.setDescCar(getAQMS());
        carCheckEntity.setContacts(getBKLXR());
        carCheckEntity.setContactCall(getBKLXFS());
        carCheckEntity.setTaskNames(getRWMC());
        carCheckEntity.setCheckPointId(getRWID());
//        personCheckEntity.setCrsfzh();

        return carCheckEntity;


    }


    public String getCLLB() {
        return cllb;
    }

    public void setCLLB(String CLLB) {
        this.cllb = CLLB;
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

    public String getCZSFZH() {
        return czsfzh;
    }

    public void setCZSFZH(String CZSFZH) {
        this.czsfzh = CZSFZH;
    }

    public String getCZXM() {
        return czxm;
    }

    public void setCZXM(String CZXM) {
        this.czxm = CZXM;
    }

    public String getCLSBDM() {
        return clsbdm;
    }

    public void setCLSBDM(String CLSBDM) {
        this.clsbdm = CLSBDM;
    }

    public String getCLYS() {
        return clys;
    }

    public void setCLYS(String CLYS) {
        this.clys = CLYS;
    }

    public String getFDJH() {
        return fdjh;
    }

    public void setFDJH(String FDJH) {
        this.fdjh = FDJH;
    }

    public String getCPHM() {
        return cphm;
    }

    public void setCPHM(String CPHM) {
        this.cphm = CPHM;
    }
}
