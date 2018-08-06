package com.anrongtec.cp.db;


import com.anrongtec.cp.entity.CheckInfoManager;

import java.util.List;

/**
 * @author libo
 * @Description:
 * @date 2018/5/21
 */

public class PersonDao {

    /**
     * 核查人员查询
     *
     * @param sortBy
     * @param page
     * @param pageSize
     * @param conditions
     * @return
     */
    public static List<CheckInfoManager> queryVerifyPersons(String sortBy, int
            page, int pageSize, String... conditions) {
        return LitePalUtils.query(CheckInfoManager.class, sortBy, page, pageSize, conditions);
    }
}
