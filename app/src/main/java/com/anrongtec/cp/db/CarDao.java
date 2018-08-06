package com.anrongtec.cp.db;

import com.anrongtec.cp.entity.CarCheckEntity;

import java.util.List;

/**
 * @author libo
 * @Description:
 * @date 2018/5/21
 */

public class CarDao {

    /**
     * 核查车辆查询
     *
     * @param sortBy
     * @param page
     * @param pageSize
     * @param conditions
     * @return
     */
    public static List<CarCheckEntity> queryVerifyCars(String sortBy, int
            page, int pageSize, String... conditions) {
        return LitePalUtils.query(CarCheckEntity.class, sortBy, page, pageSize, conditions);
    }
}
