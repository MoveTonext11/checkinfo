package com.anrongtec.lasa.manager;

import org.litepal.crud.DataSupport;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wuwenbo
 * 2018/7/26
 */
public class CheckHestory extends DataSupport implements Serializable {

    public List<ListCarRecordBean> listCarRecord;
    public List<?> listBidCarRecord;
    public List<?> listBidPersionRecord;
    public List<?> listPersionRecord;

    public static class ListCarRecordBean {
        /**
         * id : 47500f1ee03945a486b4a148378a8a9d
         * checkeventId : 55b31a0b88624f7fad5a41d335fd681c
         * cp : 冀HYY505
         * pcsj : 1506399120000
         * createTime : 1466993058000
         * allgenerateId : 813875bb82fd4501b49f10a12de7509c
         * allpackageVer : 2016-06-26-18-27-31
         * hpzl : 02
         */

        public String id;
        public String checkeventId;
        public String cp;
        public long pcsj;
        public long createTime;
        public String allgenerateId;
        public String allpackageVer;
        public String hpzl;
    }
}
