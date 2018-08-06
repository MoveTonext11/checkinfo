package com.anrongtec.cp.entity;

import com.google.gson.annotations.SerializedName;

import org.litepal.annotation.Column;
import org.litepal.crud.DataSupport;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author libo
 * @ClassName: PersonCheckEntity
 * @Description: 人员核查实体类
 * @date 2018/5/14
 **/

public class PersonCheckEntity extends DataSupport implements Serializable{
    private int id;// 记录表id

    public String getLocationX() {
        return locationX;
    }

    public void setLocationX(String locationX) {
        this.locationX = locationX;
    }

    public String getLocationY() {
        return locationY;
    }

    public void setLocationY(String locationY) {
        this.locationY = locationY;
    }

    private String locationX;
    private String locationY;
    private String checkeventId;// 事件id
    private String sfhm;//  人员身份证号
    private String czsfhm;//  车主身份证号
    private String xm;// 姓名
    private String zp;//照片
    private String mz;// 民族
    private String xb;// 性别
    private String csrq;// 出生日期
    private String sfzzz;//
    private String qfjg;//
    private String yxqx;// 简要详情
    private String province;// 行政区划省
    private String city;// 市
    private String county;// 县
    private Long pcsj;// 人员核查时间
    private Long clpcsj;// 车辆核查时间
    private String sjly;// 数据来源
    private String rylb; // 人员类别
    private Timestamp createTimeBack;// 创建时间
    private Timestamp registTimeStart;// 查询起始时间
    private Timestamp registTimeEnd; // 查询结束时间
    @SerializedName("userid")
    private String policeNum; // 警员号
    private String equiptId; // 设备标识号
    private String checkpointName;//  固定检查站
    private String checkpointNames;//检查站名称
    private String upCheckpointNames;//上级检查站名称
    private String checkScopeIds;// 检查站 范围选择
    private String addressName; //
    private String addres; // 检查站 核查地点
    private String allgenerateId;// 人员核查表 整体列控库的id
    private String allpackage;// 人员核查表 整体列控库的版本
    private String taskNames;// 核查中标人员表 任务名称
    private String clallpackage;// 车辆核查表 整体列控库的版本
    private String cltaskNames;// 车辆核查中标人员表 任务名称
    private String taskId;// 核查中标人员表 任务ID
    private String policeName;// 警员名称
    private String cllb;// 车辆类别
    private String checkpointDisName; // main 条件查询固定检查站
    private String vsfhm;//身份证号
    private String userId;//操作员警号(即警员编号)
    private String regionType;//身份证号
    private String czfs;//个人的处置方式
    @SerializedName("remark")
    private String remarkDesc;//描述(布控原因)
    private String crsfzh;//存疑身份证号人员
    private String cph;//车牌号
    private String hpzl;//号牌种类
    private Timestamp startTimeBack;// 开始时间
    private Timestamp endTimeBack;// 结束时间
    private String checkPointId;//所属检查站id
    private String checUser;//核查人,如果null则认为是当前警员姓名,有可能是辅警
    private String clys;//车辆颜色
    private String carId;//车辆核查id
    private String czxm;//车辆核查表姓名
    private String strType;//确定是否有page页签 通过页签判断查询的范围
    private String perLevel;//人员级别
    private String bklxrName;//布控联系人
    private String bkrCall; //布控人电话
    private String bkdw;//布控单位


    @Column(nullable = false)
    public Long inAppDate;     //移动端存储时间

    public Long getInAppDate() {
        return inAppDate;
    }

    public void setInAppDate(Long inAppDate) {
        this.inAppDate = inAppDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCheckeventId() {
        return checkeventId;
    }

    public void setCheckeventId(String checkeventId) {
        this.checkeventId = checkeventId;
    }

    public String getSfhm() {
        return sfhm;
    }

    public void setSfhm(String sfhm) {
        this.sfhm = sfhm;
    }

    public String getCzsfhm() {
        return czsfhm;
    }

    public void setCzsfhm(String czsfhm) {
        this.czsfhm = czsfhm;
    }

    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }

    public String getZp() {
        return zp;
    }

    public void setZp(String zp) {
        this.zp = zp;
    }

    public String getMz() {
        return mz;
    }

    public void setMz(String mz) {
        this.mz = mz;
    }

    public String getXb() {
        return xb;
    }

    public void setXb(String xb) {
        this.xb = xb;
    }

    public String getCsrq() {
        return csrq;
    }

    public void setCsrq(String csrq) {
        this.csrq = csrq;
    }

    public String getSfzzz() {
        return sfzzz;
    }

    public void setSfzzz(String sfzzz) {
        this.sfzzz = sfzzz;
    }

    public String getQfjg() {
        return qfjg;
    }

    public void setQfjg(String qfjg) {
        this.qfjg = qfjg;
    }

    public String getYxqx() {
        return yxqx;
    }

    public void setYxqx(String yxqx) {
        this.yxqx = yxqx;
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

    public Long getPcsj() {
        return pcsj;
    }

    public void setPcsj(Long pcsj) {
        this.pcsj = pcsj;
    }

    public Long getClpcsj() {
        return clpcsj;
    }

    public void setClpcsj(Long clpcsj) {
        this.clpcsj = clpcsj;
    }

    public String getSjly() {
        return sjly;
    }

    public void setSjly(String sjly) {
        this.sjly = sjly;
    }

    public String getRylb() {
        return rylb;
    }

    public void setRylb(String rylb) {
        this.rylb = rylb;
    }

    public Timestamp getCreateTimeBack() {
        return createTimeBack;
    }

    public void setCreateTimeBack(Timestamp createTimeBack) {
        this.createTimeBack = createTimeBack;
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

    public String getUserid() {
        return policeNum;
    }

    public void setUserid(String PoliceNum) {
        this.policeNum = PoliceNum;
    }

    public String getEquiptId() {
        return equiptId;
    }

    public void setEquiptId(String equiptId) {
        this.equiptId = equiptId;
    }

    public String getCheckpointName() {
        return checkpointName;
    }

    public void setCheckpointName(String checkpointName) {
        this.checkpointName = checkpointName;
    }

    public String getCheckpointNames() {
        return checkpointNames;
    }

    public void setCheckpointNames(String checkpointNames) {
        this.checkpointNames = checkpointNames;
    }

    public String getUpCheckpointNames() {
        return upCheckpointNames;
    }

    public void setUpCheckpointNames(String upCheckpointNames) {
        this.upCheckpointNames = upCheckpointNames;
    }

    public String getCheckScopeIds() {
        return checkScopeIds;
    }

    public void setCheckScopeIds(String checkScopeIds) {
        this.checkScopeIds = checkScopeIds;
    }

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    public String getAddres() {
        return addres;
    }

    public void setAddres(String addres) {
        this.addres = addres;
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

    public String getTaskNames() {
        return taskNames;
    }

    public void setTaskNames(String taskNames) {
        this.taskNames = taskNames;
    }

    public String getClallpackage() {
        return clallpackage;
    }

    public void setClallpackage(String clallpackage) {
        this.clallpackage = clallpackage;
    }

    public String getCltaskNames() {
        return cltaskNames;
    }

    public void setCltaskNames(String cltaskNames) {
        this.cltaskNames = cltaskNames;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getPoliceName() {
        return policeName;
    }

    public void setPoliceName(String policeName) {
        this.policeName = policeName;
    }

    public String getCllb() {
        return cllb;
    }

    public void setCllb(String cllb) {
        this.cllb = cllb;
    }

    public String getCheckpointDisName() {
        return checkpointDisName;
    }

    public void setCheckpointDisName(String checkpointDisName) {
        this.checkpointDisName = checkpointDisName;
    }

    public String getVsfhm() {
        return vsfhm;
    }

    public void setVsfhm(String vsfhm) {
        this.vsfhm = vsfhm;
    }

    public String getRegionType() {
        return regionType;
    }

    public void setRegionType(String regionType) {
        this.regionType = regionType;
    }

    public String getCzfs() {
        return czfs;
    }

    public void setCzfs(String czfs) {
        this.czfs = czfs;
    }

    public String getRemarkDesc() {
        return remarkDesc;
    }

    public void setRemark(String remark) {
        this.remarkDesc = remark;
    }

    public String getCrsfzh() {
        return crsfzh;
    }

    public void setCrsfzh(String crsfzh) {
        this.crsfzh = crsfzh;
    }

    public String getCph() {
        return cph;
    }

    public void setCph(String cph) {
        this.cph = cph;
    }

    public String getHpzl() {
        return hpzl;
    }

    public void setHpzl(String hpzl) {
        this.hpzl = hpzl;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Timestamp getStartTimeBack() {
        return startTimeBack;
    }

    public void setStartTimeBack(Timestamp startTimeBack) {
        this.startTimeBack = startTimeBack;
    }

    public Timestamp getEndTimeBack() {
        return endTimeBack;
    }

    public void setEndTimeBack(Timestamp endTimeBack) {
        this.endTimeBack = endTimeBack;
    }

    public String getCheckPointId() {
        return checkPointId;
    }

    public void setCheckPointId(String checkPointId) {
        this.checkPointId = checkPointId;
    }

    public String getChecUser() {
        return checUser;
    }

    public void setChecUser(String checUser) {
        this.checUser = checUser;
    }

    public String getClys() {
        return clys;
    }

    public void setClys(String clys) {
        this.clys = clys;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public String getCzxm() {
        return czxm;
    }

    public void setCzxm(String czxm) {
        this.czxm = czxm;
    }

    public String getStrType() {
        return strType;
    }

    public void setStrType(String strType) {
        this.strType = strType;
    }

    public String getPerLevel() {
        return perLevel;
    }

    public void setPerLevel(String perLevel) {
        this.perLevel = perLevel;
    }

    public String getBklxrName() {
        return bklxrName;
    }

    public void setBklxrName(String bklxrName) {
        this.bklxrName = bklxrName;
    }

    public String getBkrCall() {
        return bkrCall;
    }

    public void setBkrCall(String bkrCall) {
        this.bkrCall = bkrCall;
    }

    public String getBkdw() {
        return bkdw;
    }

    public void setBkdw(String bkdw) {
        this.bkdw = bkdw;
    }
}
