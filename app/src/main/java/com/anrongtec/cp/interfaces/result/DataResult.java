package com.anrongtec.cp.interfaces.result;

import java.io.Serializable;

/**
 * 接口数据格式类。
 *
 * @param <T>
 */
public class DataResult<T> implements Serializable {
    public static final String DATARESULTSUCCESS = "000";
    public static final String DATARESULTERROR = "0001";
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
    private T data;

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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
