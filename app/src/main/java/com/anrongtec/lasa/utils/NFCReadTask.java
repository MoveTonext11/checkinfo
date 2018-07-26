package com.anrongtec.lasa.utils;

import android.content.Intent;
import android.os.AsyncTask;

import org.codehaus.jackson.map.ObjectMapper;

import cn.com.senter.model.IdentityCardZ;

/**
 * Created by Administrator on 2018/6/28.
 */


public class NFCReadTask extends AsyncTask<Void, Void, String> {

    private Intent mIntent = null;
    private NFCReaderHelper mNFCReaderHelper;
    IdentityCardZ mIdentityCardZ;

    public NFCReadTask(Intent i, NFCReaderHelper helper) {
        mNFCReaderHelper = helper;
        mIntent = i;
    }

    @Override
    protected String doInBackground(Void... params) {
        String strCardInfo = mNFCReaderHelper.readCardWithIntent(mIntent);
        return strCardInfo;
    }

    @Override
    protected void onPostExecute(String strCardInfo) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            mIdentityCardZ = objectMapper.readValue(strCardInfo, IdentityCardZ.class);
        } catch (Exception e) {
            return;
        }
        //具体逻辑操作返回数据
        nfcMessage.BackMessage(mIdentityCardZ);
        super.onPostExecute(strCardInfo);
    }

    NfcMessage nfcMessage;
    public void textmessage(NfcMessage nfc){
        this.nfcMessage=nfc;
    }
    public interface NfcMessage {
        void BackMessage(IdentityCardZ s);
    }
    public void BackMessage(IdentityCardZ s) {
    }
}
