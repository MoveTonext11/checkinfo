package com.anrongtec.cp.interfaces.result;

import com.anrongtec.cp.entity.AJZCertificateEntity;
import com.anrongtec.cp.entity.CheckInfoBaseEntity;
import com.anrongtec.cp.entity.CheckInfoZBCarEntity;
import com.anrongtec.cp.entity.CheckInfoZBPersonEntity;

import java.io.Serializable;
import java.util.List;

/**
 * 核查比对信息接口  格式
 */
public class CheckInfoDataResult implements Serializable {
    /**
     * 返回码
     */
    private String code;

    /**
     * 返回信息
     */
    private String msg;

    /**
     * 返回的数据
     */
    private ListData data;


    public String getResultCode() {
        return code;
    }

    public void setResultCode(String resultCode) {
        this.code = resultCode;
    }

    public String getResultMsg() {
        return msg;
    }

    public void setResultMsg(String resultMsg) {
        this.msg = resultMsg;
    }

    public ListData getData() {
        return data;
    }

    public void setData(ListData data) {
        this.data = data;
    }

    public class ListData {
        /**
         * 人员基本信息
         */
        private List<CheckInfoBaseEntity> ryxxList;
        /**
         * 无证化当前信息
         */
        private List<CheckInfoBaseEntity> thisRyxxList;

        public List<CheckInfoBaseEntity> getThisRyxxList() {
            return thisRyxxList;
        }

        public void setThisRyxxList(List<CheckInfoBaseEntity> thisRyxxList) {
            this.thisRyxxList = thisRyxxList;
        }

        /**
         * 中标人员
         */
        private List<CheckInfoZBPersonEntity> zbryList;

        /**
         * 中标车辆
         */
        private List<CheckInfoZBCarEntity> zbclList;
        /**
         * 安检证实体
         */
        private AJZCertificateEntity ajz;

        private String zt;

        public String getZt() {
            return zt;
        }

        public void setZt(String zt) {
            this.zt = zt;
        }

        public AJZCertificateEntity getAjz() {
            return ajz;
        }

        public void setAjz(AJZCertificateEntity ajz) {
            this.ajz = ajz;
        }

        public List<CheckInfoBaseEntity> getRyxxList() {
            return ryxxList;
        }

        public void setRyxxList(List<CheckInfoBaseEntity> ryxxList) {
            this.ryxxList = ryxxList;
        }

        public List<CheckInfoZBPersonEntity> getZbryList() {
            return zbryList;
        }

        public void setZbryList(List<CheckInfoZBPersonEntity> zbryList) {
            this.zbryList = zbryList;
        }

        public List<CheckInfoZBCarEntity> getZbclList() {
            return zbclList;
        }

        public void setZbclList(List<CheckInfoZBCarEntity> zbclList) {
            this.zbclList = zbclList;
        }
    }

}
