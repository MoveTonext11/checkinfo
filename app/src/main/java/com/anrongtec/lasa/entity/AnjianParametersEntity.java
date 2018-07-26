package com.anrongtec.lasa.entity;

/**
 * @author libo
 * @Description: 无证化安检 输入参数Json实体
 * @date 2018/5/22
 */

public class AnjianParametersEntity {

    /**
     * sfzh : 140224199101288618
     * cphm : 吉SSS645
     * hpzl : 02
     * checkId : 222222
     * checkName : 22222
     * date : 2
     * gz : 1
     * checkPointId : null
     * ldqy : 1
     * name : sss
     * sszdld : 第二道防线
     * rank : null
     * excuse : null
     */


    private String sfzh;        //被核查身份证号码多个用,隔开
    private String cphm;
    private String hpzl;
    private int date;       //有效时间（天）
    private int gz;         //关注----0（关注）1（不关注）
    private int ldqy;               //1(第一联动区域)2（第二联动区域）4（秦皇岛
    private String sbid;                //手机IMEI号
    private String name;                //检查站名称
    private String sszdld;              //联动区域类别
    private String excuse;            //定级理由

    public int getLdqy() {
        return ldqy;
    }

    public void setLdqy(int ldqy) {
        this.ldqy = ldqy;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSszdld() {
        return sszdld;
    }

    public void setSszdld(String sszdld) {
        this.sszdld = sszdld;
    }


    public String getExcuse() {
        return excuse;
    }

    public void setExcuse(String excuse) {
        this.excuse = excuse;
    }

    public AnjianParametersEntity() {
    }

    public AnjianParametersEntity(String sbid, String sfzh,
                                          String cphm, String hpzl, int
                                          date, int gz, int ldqy, String name, String sszdld,
                                  String excuse) {
        this.sbid = sbid;
        this.sfzh = sfzh;
        this.cphm = cphm;
        this.hpzl = hpzl;
        this.date = date;
        this.gz = gz;
        this.ldqy = ldqy;
        this.name = name;
        this.sszdld = sszdld;
        this.excuse = excuse;
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

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getGz() {
        return gz;
    }

    public void setGz(int gz) {
        this.gz = gz;
    }
}
