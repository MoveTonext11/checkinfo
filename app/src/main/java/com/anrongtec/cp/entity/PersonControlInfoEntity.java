package com.anrongtec.cp.entity;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * @author libo
 * @Description: 布控人员信息
 * @date 2018/5/11
 */

public class PersonControlInfoEntity extends DataSupport {

    /**
     * code : 000
     * data : {"pagingInfo":{"endIdx":1,"pageNum":1,"pageRows":1,"startIdx":1,"totalPages":2,"totalRows":2},"resultList":[{"dataSource":"紧急布控","entryTime":1533546004003,"jyaq":"测试","lxdh":"111","lxr":"000","remark":"123","rylb":"其他重点非访","sfzh":"372922199101085415","sourceTime":1533546004002,"xb":"1","xm":"仵文博"}]}
     */

    public String code;
    public DataBean data;

    public static class DataBean {
        /**
         * pagingInfo : {"endIdx":1,"pageNum":1,"pageRows":1,"startIdx":1,"totalPages":2,"totalRows":2}
         * resultList : [{"dataSource":"紧急布控","entryTime":1533546004003,"jyaq":"测试","lxdh":"111","lxr":"000","remark":"123","rylb":"其他重点非访","sfzh":"372922199101085415","sourceTime":1533546004002,"xb":"1","xm":"仵文博"}]
         */

        public PagingInfoBean pagingInfo;
        public List<ResultListBean> resultList;

        public static class PagingInfoBean {
            /**
             * endIdx : 1
             * pageNum : 1
             * pageRows : 1
             * startIdx : 1
             * totalPages : 2
             * totalRows : 2
             */

            public int endIdx;
            public int pageNum;
            public int pageRows;
            public int startIdx;
            public int totalPages;
            public int totalRows;
        }

        public static class ResultListBean {
            /**
             * dataSource : 紧急布控
             * entryTime : 1533546004003
             * jyaq : 测试
             * lxdh : 111
             * lxr : 000
             * remark : 123
             * rylb : 其他重点非访
             * sfzh : 372922199101085415
             * sourceTime : 1533546004002
             * xb : 1
             * xm : 仵文博
             */

            public String dataSource;
            public long entryTime;
            public String jyaq;
            public String lxdh;
            public String lxr;
            public String remark;
            public String rylb;
            public String sfzh;
            public long sourceTime;
            public String xb;
            public String xm;
        }
    }

    @Override
    public String toString() {
        return "PersonControlInfoEntity{" +
                "code='" + code + '\'' +
                ", data=" + data +
                '}';
    }
}
