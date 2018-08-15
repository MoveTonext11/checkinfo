package com.anrongtec.cp.manager;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wuwenbo
 * 2018/7/26
 */
public class CheckHestory implements Serializable {

    public List<?> listBidCarRecord;
    public List<ListBidPersionRecordBean> listBidPersionRecord;
    public List<ListCarRecordBean> listCarRecord;
    public List<ListPersionRecordBean> listPersionRecord;

    public static class ListBidPersionRecordBean {
        /**
         * checkeventId : 867ecc8aa97b4231b7bdb572cd4f6dac
         * hcjlId : 028c15850267409b9e9a6ad9a45f0159
         * id : 7649cb873d02443bacef55a07c2d3f5d
         * pckssj : 1534238320000
         * sfzh : 372922196903295410
         */

        public String checkeventId;
        public String hcjlId;
        public String id;
        public long pckssj;
        public String sfzh;
    }

    public static class ListCarRecordBean {
        /**
         * allgenerateId : 2018-08-14 17:11:12
         * allpackageVer : 2018-08-14 17:11:12
         * checkeventId : 8ce1983a7fc74c9e8057f70c1cd170a2
         * createTime : 1534237882000
         * hpzl : 02
         * id : 0a29c7487c6d4913a6c8f3be1dd14047
         * pcsj : 1534237872000
         */

        public String allgenerateId;
        public String allpackageVer;
        public String checkeventId;
        public long createTime;
        public String hpzl;
        public String id;
        public long pcsj;

    }

    public static class ListPersionRecordBean {
        /**
         * allgenerateId : 2018-08-14 17:11:12
         * allpackageVer : 2018-08-14 17:11:12
         * checkeventId : 8ce1983a7fc74c9e8057f70c1cd170a2
         * createTime : 1534237882000
         * id : 34294b73987b407fb3b04cb60d40aa56
         * pcsj : 1534237872000
         * sfhm : 372922199101085415
         */

        public String allgenerateId;
        public String allpackageVer;
        public String checkeventId;
        public long createTime;
        public String id;
        public long pcsj;
        public String sfhm;
    }

    @Override
    public String toString() {
        return "CheckHestory{" +
                "listBidCarRecord=" + listBidCarRecord +
                ", listBidPersionRecord=" + listBidPersionRecord +
                ", listCarRecord=" + listCarRecord +
                ", listPersionRecord=" + listPersionRecord +
                '}';
    }
}
