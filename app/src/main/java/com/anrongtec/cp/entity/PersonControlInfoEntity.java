package com.anrongtec.cp.entity;

import org.litepal.crud.DataSupport;

/**
 * @author libo
 * @Description: 布控人员信息
 * @date 2018/5/11
 */

public class PersonControlInfoEntity extends DataSupport {
    /**
     * xm : 大灰狼
     * xb : null
     * sfzh : 220322198710023832
     * sourceTime : 1525852267965
     * entryTime : 1525852267965
     * rylb : 涉毒
     * lxr : 杨
     * lxdh : 13383838282
     * jyaq : null
     * dataSource : 紧急布控
     * remark : null
     * hjdqh : null
     */

    private String xm;
    private String xb;
    private String sfzh;
    private long sourceTime;
    private long entryTime;
    private String rylb;
    private String lxr;
    private String lxdh;
    private String jyaq;
    private String dataSource;
    private String remark;
    private String hjdqh;

    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }

    public String getXb() {
        return xb;
    }

    public void setXb(String xb) {
        this.xb = xb;
    }

    public String getSfzh() {
        return sfzh;
    }

    public void setSfzh(String sfzh) {
        this.sfzh = sfzh;
    }

    public long getSourceTime() {
        return sourceTime;
    }

    public void setSourceTime(long sourceTime) {
        this.sourceTime = sourceTime;
    }

    public long getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(long entryTime) {
        this.entryTime = entryTime;
    }

    public String getRylb() {
        return rylb;
    }

    public void setRylb(String rylb) {
        this.rylb = rylb;
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

    public String getJyaq() {
        return jyaq;
    }

    public void setJyaq(String jyaq) {
        this.jyaq = jyaq;
    }

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getHjdqh() {
        return hjdqh;
    }

    public void setHjdqh(String hjdqh) {
        this.hjdqh = hjdqh;
    }
}
