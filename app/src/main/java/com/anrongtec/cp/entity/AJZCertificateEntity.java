package com.anrongtec.cp.entity;

/**
 * @author libo
 * @Description: 解析无证化安检返回的安检证信息实体
 * @date 2018/5/23
 */

public class AJZCertificateEntity {

    private String xh;            //序号		--安检证表
    private String checkeventId;// 事件id
    private String hcjg;        //核查结果
    private String ajzh;     //安检证号D[Z]****(年份)*(安保类别)*******(顺序号)
    private String ajzlb;       // 安检证类别(代码，代码类别为03)
    private String abrw;        //安保任务类别(代码，代码类别04)
    private String sqlb;         //申请类别(代码，代码类别05)
    private String fzjg;       // 发证机关，APP申请的可为空系统自动认为是省厅(130000000000)
    private String fzjgmc;         //发证机关名称
    private String hpzl;            //号牌种类(GA24.7)
    private String hphm;            // 号牌号码
    private String carPerid;        //车主身份证号
    private String carPername;       //车主姓名
    private String clsbdm;            //车辆识别代码
    private String fdjh;        //车辆发动机号
    private String clys;        //车辆颜色
    private String clpp1;         //车辆品牌1
    private String clpp2;            //车辆品牌2
    private String jdczt;            //机动车状态
    private String xszzmz;        //身份证正面照
    private String clzmz;       //车辆正面照
    private String jszzmz;            //驾驶证正面照
    private String brczzp;        //本人持证照
    private String sqrsfzmhm;        //申请人身份证明号码
    private String sqrxm;        //申请人姓名
    private String sqrlxdh;       //申请人联系电话
    private String zjcx;            //准驾车型
    private String jszzt;        //驾驶证状态
    private String jbr;        //经办人
    private Long sqrq;       //申请时间
    private String sqzt;            //申请状态,1成功,0失败
    private String sqsbyy;        //申请失败原因
    private Long yxqs;        //有效期始
    private Long yxqz;        //有效期止
    private String rysl;       //人员数量
    private String sts;            //状态,1新增(初次申领) 2 修改 3删除(注销安检证)
    private Long stsTime;        //状态修改时间
    private String ajzdyurl;        //安检证打印URL
    private String dycs;        //打印次数
    private int gz;          //关注0（关注)1(正常)

    private int ldqy;    //所属区域编号
    private String name;//检查站名称
    private String sszdld;//安检证联动区域

    private String rank;//车辆所属级别
    private String excuse;//定级理由

    public String getXh() {
        return xh;
    }

    public void setXh(String xh) {
        this.xh = xh;
    }

    public String getCheckeventId() {
        return checkeventId;
    }

    public void setCheckeventId(String checkeventId) {
        this.checkeventId = checkeventId;
    }

    public String getHcjg() {
        return hcjg;
    }

    public void setHcjg(String hcjg) {
        this.hcjg = hcjg;
    }

    public String getAjzh() {
        return ajzh;
    }

    public void setAjzh(String ajzh) {
        this.ajzh = ajzh;
    }

    public String getAjzlb() {
        return ajzlb;
    }

    public void setAjzlb(String ajzlb) {
        this.ajzlb = ajzlb;
    }

    public String getAbrw() {
        return abrw;
    }

    public void setAbrw(String abrw) {
        this.abrw = abrw;
    }

    public String getSqlb() {
        return sqlb;
    }

    public void setSqlb(String sqlb) {
        this.sqlb = sqlb;
    }

    public String getFzjg() {
        return fzjg;
    }

    public void setFzjg(String fzjg) {
        this.fzjg = fzjg;
    }

    public String getFzjgmc() {
        return fzjgmc;
    }

    public void setFzjgmc(String fzjgmc) {
        this.fzjgmc = fzjgmc;
    }

    public String getHpzl() {
        return hpzl;
    }

    public void setHpzl(String hpzl) {
        this.hpzl = hpzl;
    }

    public String getHphm() {
        return hphm;
    }

    public void setHphm(String hphm) {
        this.hphm = hphm;
    }

    public String getCarPerid() {
        return carPerid;
    }

    public void setCarPerid(String carPerid) {
        this.carPerid = carPerid;
    }

    public String getCarPername() {
        return carPername;
    }

    public void setCarPername(String carPername) {
        this.carPername = carPername;
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

    public String getClpp1() {
        return clpp1;
    }

    public void setClpp1(String clpp1) {
        this.clpp1 = clpp1;
    }

    public String getClpp2() {
        return clpp2;
    }

    public void setClpp2(String clpp2) {
        this.clpp2 = clpp2;
    }

    public String getJdczt() {
        return jdczt;
    }

    public void setJdczt(String jdczt) {
        this.jdczt = jdczt;
    }

    public String getXszzmz() {
        return xszzmz;
    }

    public void setXszzmz(String xszzmz) {
        this.xszzmz = xszzmz;
    }

    public String getClzmz() {
        return clzmz;
    }

    public void setClzmz(String clzmz) {
        this.clzmz = clzmz;
    }

    public String getJszzmz() {
        return jszzmz;
    }

    public void setJszzmz(String jszzmz) {
        this.jszzmz = jszzmz;
    }

    public String getBrczzp() {
        return brczzp;
    }

    public void setBrczzp(String brczzp) {
        this.brczzp = brczzp;
    }

    public String getSqrsfzmhm() {
        return sqrsfzmhm;
    }

    public void setSqrsfzmhm(String sqrsfzmhm) {
        this.sqrsfzmhm = sqrsfzmhm;
    }

    public String getSqrxm() {
        return sqrxm;
    }

    public void setSqrxm(String sqrxm) {
        this.sqrxm = sqrxm;
    }

    public String getSqrlxdh() {
        return sqrlxdh;
    }

    public void setSqrlxdh(String sqrlxdh) {
        this.sqrlxdh = sqrlxdh;
    }

    public String getZjcx() {
        return zjcx;
    }

    public void setZjcx(String zjcx) {
        this.zjcx = zjcx;
    }

    public String getJszzt() {
        return jszzt;
    }

    public void setJszzt(String jszzt) {
        this.jszzt = jszzt;
    }

    public String getJbr() {
        return jbr;
    }

    public void setJbr(String jbr) {
        this.jbr = jbr;
    }

    public Long getSqrq() {
        return sqrq;
    }

    public void setSqrq(Long sqrq) {
        this.sqrq = sqrq;
    }

    public String getSqzt() {
        return sqzt;
    }

    public void setSqzt(String sqzt) {
        this.sqzt = sqzt;
    }

    public String getSqsbyy() {
        return sqsbyy;
    }

    public void setSqsbyy(String sqsbyy) {
        this.sqsbyy = sqsbyy;
    }

    public Long getYxqs() {
        return yxqs;
    }

    public void setYxqs(Long yxqs) {
        this.yxqs = yxqs;
    }

    public Long getYxqz() {
        return yxqz;
    }

    public void setYxqz(Long yxqz) {
        this.yxqz = yxqz;
    }

    public String getRysl() {
        return rysl;
    }

    public void setRysl(String rysl) {
        this.rysl = rysl;
    }

    public String getSts() {
        return sts;
    }

    public void setSts(String sts) {
        this.sts = sts;
    }

    public Long getStsTime() {
        return stsTime;
    }

    public void setStsTime(Long stsTime) {
        this.stsTime = stsTime;
    }

    public String getAjzdyurl() {
        return ajzdyurl;
    }

    public void setAjzdyurl(String ajzdyurl) {
        this.ajzdyurl = ajzdyurl;
    }

    public String getDycs() {
        return dycs;
    }

    public void setDycs(String dycs) {
        this.dycs = dycs;
    }

    public int getGz() {
        return gz;
    }

    public void setGz(int gz) {
        this.gz = gz;
    }

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

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getExcuse() {
        return excuse;
    }

    public void setExcuse(String excuse) {
        this.excuse = excuse;
    }
}
