package com.anrongtec.cp.db;


import android.content.ContentValues;

import org.litepal.crud.DataSupport;

import java.util.Collection;
import java.util.List;

/**
 * 数据库相关操作
 */
public class LitePalUtils {

    public static LitePalUtils getInstance() {
        return LazyHolder.instance;
    }

    private static class LazyHolder {
        private static LitePalUtils instance = new LitePalUtils();
    }


    //*************************************  增删改查  **********************************************
    public static <T extends DataSupport> void saveAll(Collection<T> collection) {
        DataSupport.saveAll(collection);
    }

    public static <T extends DataSupport> void save(T t) {
        t.save();
    }


    /**
     * 更新
     *
     * @param modelClass 实体类
     * @param values     更新的键值对（列名和值）
     * @param conditions 条件。   第一个参数为条件， 以后的参数为占位符对应参数
     */
    public static void updateAll(Class<?> modelClass, ContentValues values, String... conditions) {
//		ContentValues values = new ContentValues();
//		values.put("title", "今日iPhone6 Plus发布");
//		//conditions参数的后面两个参数  分别替换第一个参数的两个“？”
//		DataSupport.updateAll(modelClass, values, "title = ? and commentcount > ?", "今日iPhone6发布",
// "0");
        DataSupport.updateAll(modelClass, values, conditions);
    }

    /**
     * 更新
     *
     * @param t          类对象
     * @param conditions 查询条件
     */
    public static <T extends DataSupport> void updateAll(T t, String... conditions) {
        //这种用法注意： 如果更新之后的值是对象默认的值，则不会进行更新。
        //比如对象默认的Name是null，   如果t.setName(null);再去update则无效。
        //应该用t.setToDefault("Name");
        t.updateAll(conditions);
    }


    /**
     * 删除
     *
     * @param modelClass 类
     * @param conditions 查询条件
     * @return -1为失败
     */
    public static <T> int deleteAll(Class<T> modelClass, String... conditions) {
        return DataSupport.deleteAll(modelClass, conditions);
    }

    /**
     * 删除
     *
     * @param t 已经存放到数据库的类对象
     * @return -1为失败
     */
    public static <T extends DataSupport> int deleteAll(T t) {
        if (t.isSaved()) {
            return t.delete();
        }
        return -1;
    }

    /**
     * 查询
     *
     * @param modelClass 类
     * @param conditions 查询条件
     * @param page       页码
     * @param pageSize   每页条数
     * @param <T>
     * @return
     */
    public static <T> List<T> query(Class<T> modelClass, String sortBy, int
            page, int pageSize, String ... conditions) {
//		List<T> list = DataSupport.select("title", "content")
//				.where("commentcount > ?", "0")
//				.order("publishdate desc").limit(10).offset(10)
//				.find(modelClass);
        List<T> list = DataSupport
                .where(conditions)
                .order(sortBy)
                .limit(pageSize).offset((page - 1) * pageSize)
                .find(modelClass);
        return list;
    }


}
