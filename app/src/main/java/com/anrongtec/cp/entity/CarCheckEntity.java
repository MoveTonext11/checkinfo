package com.anrongtec.cp.entity;

import org.litepal.annotation.Column;
import org.litepal.crud.DataSupport;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author libo
 * @ClassName: CarCheckEntity
 * @Description: 车辆核查实体
 * @date 2018/5/11
 */

public class CarCheckEntity extends DataSupport implements Serializable {
    private Long id;

    private String checkeventId;

    private String cp;

    private String clsbdm;

    private String fdjh;

    private String clys;

    private String czsfhm;

    private String czxm;

    private String province;

    private String city;

    private String county;

    private String count;

    private String sfhm;

    private String xm;

    private String mz;

    private String csrq;

    private Long pcsj;

    private String sjly;

    private Timestamp createTime;

    private String allgenerateId;// 人员核查表 整体列控库的id

    private String allpackage;// 人员核查表 整体列控库的版本

    private String checkpointNames;// 固定检查站名称
    private String UpCheckpointNames;// 上级检查站名称

    private Timestamp registTimeStart;// 查询起始时间

    private Timestamp registTimeEnd; // 查询结束时间

    private String cllb; // 车辆类别

    private String dsName; // 地市名称

    private String userid; // 警员号

    private String equiptId; // 设备标识号

    private String checkScopeIds;// 检查站 范围选择

    private String policeName;// 警员名称

    private String addres; // 核查事件地点

    private String checkpointDisName; // main 条件查询固定检查站

    private String checkpointName;// 所属机构

    private String taskNames;// 核查中标人员表 任务名称

    private String taskId;// 核查中标人员表 任务ID

    private byte[] zp;//照片

    private String xb;// 车主性别
    private String descCar;// 中标车辆描述
    private String hpzl;// 车辆号牌种类
    private String cph;//存疑车牌号
    private String checkPointId;//检查站id

    private String contacts;//布控人
    private String contactCall;//布控联系人电话
    private String deptName;//

    @Column(nullable = false)
    public Long inAppDate;     //移动端存储时间


    public Long getInAppDate() {
        return inAppDate;
    }

    public void setInAppDate(Long inAppDate) {
        this.inAppDate = inAppDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCheckeventId() {
        return checkeventId;
    }

    public void setCheckeventId(String checkeventId) {
        this.checkeventId = checkeventId;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getClsbdm() {
        return clsbdm;
    }

    public void setClsbdm(String clsbdm) {
        this.clsbdm = clsbdm;
    }

    public String getFdjh() {
        return fdjh;
    }

    public void setFdjh(String fdjh) {
        this.fdjh = fdjh;
    }

    public String getClys() {
        return clys;
    }

    public void setClys(String clys) {
        this.clys = clys;
    }

    public String getCzsfhm() {
        return czsfhm;
    }

    public void setCzsfhm(String czsfhm) {
        this.czsfhm = czsfhm;
    }

    public String getCzxm() {
        return czxm;
    }

    public void setCzxm(String czxm) {
        this.czxm = czxm;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getSfhm() {
        return sfhm;
    }

    public void setSfhm(String sfhm) {
        this.sfhm = sfhm;
    }

    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }

    public String getMz() {
        return mz;
    }

    public void setMz(String mz) {
        this.mz = mz;
    }

    public String getCsrq() {
        return csrq;
    }

    public void setCsrq(String csrq) {
        this.csrq = csrq;
    }

    public Long getPcsj() {
        return pcsj;
    }

    public void setPcsj(Long pcsj) {
        this.pcsj = pcsj;
    }

    public String getSjly() {
        return sjly;
    }

    public void setSjly(String sjly) {
        this.sjly = sjly;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getAllgenerateId() {
        return allgenerateId;
    }

    public void setAllgenerateId(String allgenerateId) {
        this.allgenerateId = allgenerateId;
    }

    public String getAllpackage() {
        return allpackage;
    }

    public void setAllpackage(String allpackage) {
        this.allpackage = allpackage;
    }

    public String getCheckpointNames() {
        return checkpointNames;
    }

    public void setCheckpointNames(String checkpointNames) {
        this.checkpointNames = checkpointNames;
    }

    public String getUpCheckpointNames() {
        return UpCheckpointNames;
    }

    public void setUpCheckpointNames(String upCheckpointNames) {
        UpCheckpointNames = upCheckpointNames;
    }

    public Timestamp getRegistTimeStart() {
        return registTimeStart;
    }

    public void setRegistTimeStart(Timestamp registTimeStart) {
        this.registTimeStart = registTimeStart;
    }

    public Timestamp getRegistTimeEnd() {
        return registTimeEnd;
    }

    public void setRegistTimeEnd(Timestamp registTimeEnd) {
        this.registTimeEnd = registTimeEnd;
    }

    public String getCllb() {
        return cllb;
    }

    public void setCllb(String cllb) {
        this.cllb = cllb;
    }

    public String getDsName() {
        return dsName;
    }

    public void setDsName(String dsName) {
        this.dsName = dsName;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getEquiptId() {
        return equiptId;
    }

    public void setEquiptId(String equiptId) {
        this.equiptId = equiptId;
    }

    public String getCheckScopeIds() {
        return checkScopeIds;
    }

    public void setCheckScopeIds(String checkScopeIds) {
        this.checkScopeIds = checkScopeIds;
    }

    public String getPoliceName() {
        return policeName;
    }

    public void setPoliceName(String policeName) {
        this.policeName = policeName;
    }

    public String getAddres() {
        return addres;
    }

    public void setAddres(String addres) {
        this.addres = addres;
    }

    public String getCheckpointDisName() {
        return checkpointDisName;
    }

    public void setCheckpointDisName(String checkpointDisName) {
        this.checkpointDisName = checkpointDisName;
    }

    public String getCheckpointName() {
        return checkpointName;
    }

    public void setCheckpointName(String checkpointName) {
        this.checkpointName = checkpointName;
    }

    public String getTaskNames() {
        return taskNames;
    }

    public void setTaskNames(String taskNames) {
        this.taskNames = taskNames;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public byte[] getZp() {
        return zp;
    }

    public void setZp(byte[] zp) {
        this.zp = zp;
    }

    public String getXb() {
        return xb;
    }

    public void setXb(String xb) {
        this.xb = xb;
    }

    public String getDescCar() {
        return descCar;
    }

    public void setDescCar(String descCar) {
        this.descCar = descCar;
    }

    public String getHpzl() {
        return hpzl;
    }

    public void setHpzl(String hpzl) {
        this.hpzl = hpzl;
    }

    public String getCph() {
        return cph;
    }

    public void setCph(String cph) {
        this.cph = cph;
    }

    public String getCheckPointId() {
        return checkPointId;
    }

    public void setCheckPointId(String checkPointId) {
        this.checkPointId = checkPointId;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getContactCall() {
        return contactCall;
    }

    public void setContactCall(String contactCall) {
        this.contactCall = contactCall;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }
}
