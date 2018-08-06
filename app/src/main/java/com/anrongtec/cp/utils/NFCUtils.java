package com.anrongtec.cp.utils;

import android.app.Activity;
import android.content.Context;

import cn.com.senter.helper.ShareReferenceSaver;

/**
 * Created by Administrator on 2018/6/28.
 */

public  class NFCUtils {
    public static NFCUtils NFCUtils;
    private String server_address = "";
    private int server_port = 0;
    private final static String SERVER_KEY1 = "CN.COM.SENTER.SERVER_KEY1";
    private final static String PORT_KEY1 = "CN.COM.SENTER.PORT_KEY1";
    private NFCReaderHelper mNFCReaderHelper;

    public static NFCUtils getinstance(){
        if (NFCUtils==null){
            return new NFCUtils();
        }
        return NFCUtils;
    }


    //创建对象   读取
    public  NFCReaderHelper createHfcHelper(Context context){
        mNFCReaderHelper = new NFCReaderHelper(context, null);
        return mNFCReaderHelper;
    }

    public void initShareReference(Activity activity) {
        if (this.server_address.length() <= 0) {
            if (ShareReferenceSaver.getData(activity, SERVER_KEY1).trim().length() <= 0) {
                this.server_address = "172.168.30.50";
            } else {
                this.server_address = ShareReferenceSaver.getData(activity, SERVER_KEY1);
            }
            if (ShareReferenceSaver.getData(activity, PORT_KEY1).trim().length() <= 0) {
                this.server_port = 10002;
            } else {
                this.server_port = Integer.valueOf(ShareReferenceSaver.getData(activity, PORT_KEY1));
            }
        }
        mNFCReaderHelper.setServerAddress(this.server_address);
        mNFCReaderHelper.setServerPort(this.server_port);

    }
}
