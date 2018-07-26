package com.anrongtec.lasa.entity;

/**
 * @author libo
 * @Description: 核查比对信息接口 输入参数Json实体
 * @date 2018/5/22
 */

public class CheckInfoParamtersEntity {

    /**
     * username : anrong
     * password : anrong
     * sbid : 44-37-E6-62-64-F3
     * sfzh : 140224199101288618
     * cphm : 吉SSS645
     * hpzl : 02
     */

    private String sfzh;
    private String cphm;
    private String hpzl;
    private String sbid;               //手机IMEI号


    public CheckInfoParamtersEntity() {
    }

    public CheckInfoParamtersEntity(String sbid, String sfzh,
                                    String cphm, String hpzl) {
        this.sbid = sbid;
        this.sfzh = sfzh;
        this.cphm = cphm;
        this.hpzl = hpzl;
    }


    public String getSfzh() {
        return sfzh;
    }

    public String getSbid() {
        return sbid;
    }

    public void setSbid(String sbid) {
        this.sbid = sbid;
    }

    public void setSfzh(String sfzh) {
        this.sfzh = sfzh;
    }

    public String getCphm() {
        return cphm;
    }

    public void setCphm(String cphm) {
        this.cphm = cphm;
    }

    public String getHpzl() {
        return hpzl;
    }

    public void setHpzl(String hpzl) {
        this.hpzl = hpzl;
    }
}
