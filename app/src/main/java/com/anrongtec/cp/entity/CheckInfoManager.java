package com.anrongtec.cp.entity;

import org.litepal.crud.DataSupport;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by wuwenbo
 * 2018/7/23
 */

public class CheckInfoManager extends DataSupport implements Serializable {

    /**
     * code : 000
     * data : {"ryxxList":[{"sfzzz":"北京","xm":"dsa","mz":"汉族","sfzh":"11123421112"}],"zbryList":[{"pfirstid":"sdasdasd","rylb":"类别","xm":"lisi","pverid":"asd","clfs":"处置方式","rwmc":"中标任务名称","aqms":"中标描述","sfzh":"11123421112"}],"zbclList":[{"date":"Fri Jul 20 10:38:26 CST 2018","pfirstid":"2018-07-20 10:38:26","clsbdm":"cLSBDM","fdjh":"fdjh","cphm":"冀H132322P","pverid":"2018-07-20 10:38:26","clfs":"aa","cllb":"leibie","hphm":"冀H132322P","clys":"cLYS","czsfzh":"czsfzh","czxm":"cZXM"}],"sfzh":"11123421112","hphm":"冀H132322P","hpzl":"02"}
     */

    public String code;
    public DataBean data;

    public static class DataBean {
        /**
         * ryxxList : [{"sfzzz":"北京","xm":"dsa","mz":"汉族","sfzh":"11123421112"}]
         * zbryList : [{"pfirstid":"sdasdasd","rylb":"类别","xm":"lisi","pverid":"asd","clfs":"处置方式","rwmc":"中标任务名称","aqms":"中标描述","sfzh":"11123421112"}]
         * zbclList : [{"date":"Fri Jul 20 10:38:26 CST 2018","pfirstid":"2018-07-20 10:38:26","clsbdm":"cLSBDM","fdjh":"fdjh","cphm":"冀H132322P","pverid":"2018-07-20 10:38:26","clfs":"aa","cllb":"leibie","hphm":"冀H132322P","clys":"cLYS","czsfzh":"czsfzh","czxm":"cZXM"}]
         * sfzh : 11123421112
         * hphm : 冀H132322P
         * hpzl : 02
         */

        public String sfzh;
        public String hphm;
        public String hpzl;
        public ArrayList<RyxxListBean> ryxxList;
        public ArrayList<ZbryListBean> zbryList;
        public ArrayList<ZbclListBean> zbclList;

        public static class RyxxListBean extends DataSupport implements Serializable{
            /**
             * sfzzz : 北京
             * xm : dsa
             * mz : 汉族
             * sfzh : 11123421112
             */

            public String sfzzz;
            public String xm;
            public String mz;
            public String sfzh;
        }

        public static class ZbryListBean extends DataSupport implements Serializable{
            /**
             * pfirstid : sdasdasd
             * rylb : 类别
             * xm : lisi
             * pverid : asd
             * clfs : 处置方式
             * rwmc : 中标任务名称
             * aqms : 中标描述
             * sfzh : 11123421112
             */

            public String pfirstid;
            public String rylb;
            public String xm;
            public String pverid;
            public String clfs;
            public String rwmc;
            public String aqms;
            public String sfzh;
        }

        public static class ZbclListBean extends DataSupport implements Serializable{
            /**
             * date : Fri Jul 20 10:38:26 CST 2018
             * pfirstid : 2018-07-20 10:38:26
             * clsbdm : cLSBDM
             * fdjh : fdjh
             * cphm : 冀H132322P
             * pverid : 2018-07-20 10:38:26
             * clfs : aa
             * cllb : leibie
             * hphm : 冀H132322P
             * clys : cLYS
             * czsfzh : czsfzh
             * czxm : cZXM
             */

            public String date;
            public String pfirstid;
            public String clsbdm;
            public String fdjh;
            public String cphm;
            public String pverid;
            public String clfs;
            public String cllb;
            public String hphm;
            public String clys;
            public String czsfzh;
            public String czxm;
        }
    }
}
