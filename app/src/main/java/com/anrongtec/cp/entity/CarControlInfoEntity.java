package com.anrongtec.cp.entity;

import java.util.List;

/**
 * @author libo
 * @Description: 布控车辆信息
 * @date 2018/5/11
 */

public class CarControlInfoEntity {

    public String code;
    public DataBean data;

    public static class DataBean {

        public PagingInfoBean pagingInfo;
        public List<ResultListBean> resultList;

        public static class PagingInfoBean {
            public int endIdx;
            public int pageNum;
            public int pageRows;
            public int startIdx;
            public int totalPages;
            public int totalRows;
        }

        public static class ResultListBean {

            public String carNumber;
            public String carPerId;
            public String carPerName;
            public String clys;
            public String dataSource;
            public String hpzl;
            public String keyType;
            public String lxdh;
            public String lxr;
            public String remark;
            public long sourceTime;
        }
    }
}
