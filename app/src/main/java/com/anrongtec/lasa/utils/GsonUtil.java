package com.anrongtec.lasa.utils;

import com.anrongtec.lasa.interfaces.result.DataResult;
import com.google.gson.Gson;

/**
 * Gson工具类
 */
public class GsonUtil {

    private static Gson gson;

    private static Gson getInstance() {
        if (gson == null) {
            gson = new Gson().newBuilder().create();
        }
        return gson;
    }

    /**
     * @param json
     * @param type
     * @param <T>
     * @return
     */
    public static <T> DataResult<T> decode(String json, java.lang.reflect.Type type) {
        try {
            return getInstance().fromJson(json, type);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @param json
     * @param clas
     * @return
     */
    public static <T> T decode(String json, Class<T> clas) {
        try {
            return getInstance().fromJson(json, clas);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String toJson(Object entity) {
        return getInstance().toJson(entity);
    }


}
