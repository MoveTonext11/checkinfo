package com.anrongtec.lasa.entity;

/**
 * @author libo
 * @Description: 布控车辆信息
 * @date 2018/5/11
 */

public class CarControlInfoEntity {
    /**
     * carNumber : 吉R12345
     * carPerId : 220122199312317812
     * carPerName : ddddddd
     * clsbdm : null
     * fdjh : null
     * clys : null
     * dispose : null
     * keyType : 违停
     * remark : null
     * sourceTime : null
     * entryTime : null
     * dataSource : 紧急布控
     * lxr : dddd
     * lxdh : 15910293751
     * hpzl : 02
     */

    private String carNumber;
    private String carPerId;
    private String carPerName;
    private String clsbdm;
    private String fdjh;
    private String clys;
    private String dispose;
    private String keyType;
    private String remark;
    private Long sourceTime;
    private Long entryTime;
    private String dataSource;
    private String lxr;
    private String lxdh;
    private String hpzl;

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getCarPerId() {
        return carPerId;
    }

    public void setCarPerId(String carPerId) {
        this.carPerId = carPerId;
    }

    public String getCarPerName() {
        return carPerName;
    }

    public void setCarPerName(String carPerName) {
        this.carPerName = carPerName;
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

    public String getDispose() {
        return dispose;
    }

    public void setDispose(String dispose) {
        this.dispose = dispose;
    }

    public String getKeyType() {
        return keyType;
    }

    public void setKeyType(String keyType) {
        this.keyType = keyType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getSourceTime() {
        return sourceTime;
    }

    public void setSourceTime(Long sourceTime) {
        this.sourceTime = sourceTime;
    }

    public Long getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(Long entryTime) {
        this.entryTime = entryTime;
    }

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    public String getLxr() {
        return lxr;
    }

    public void setLxr(String lxr) {
        this.lxr = lxr;
    }

    public String getLxdh() {
        return lxdh;
    }

    public void setLxdh(String lxdh) {
        this.lxdh = lxdh;
    }

    public String getHpzl() {
        return hpzl;
    }

    public void setHpzl(String hpzl) {
        this.hpzl = hpzl;
    }
}
