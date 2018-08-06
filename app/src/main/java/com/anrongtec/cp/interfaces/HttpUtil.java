package com.anrongtec.cp.interfaces;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.request.PostRequest;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * http请求类
 * cxy
 */

public class HttpUtil {

    /**
     * 通用get请求
     *
     * @param url      请求地址
     * @param map      参数
     * @param callback 回调方法
     * @param tag      类对象的hashCode
     */
    public static void get(@NonNull String url, Map<String, String> map, AbsCallback callback,
                           Object tag) {
        OkGo.<String>get(url)
                .tag(tag)
                .params(map)
                .execute(callback);
    }


    /**
     * Map 参数 Post 请求
     *
     * @param url
     * @param params
     * @param callback
     * @param tag
     */
    public static void post(String url, Map<String, String> params, AbsCallback callback, Object
            tag) {
        OkGo.<String>post(url)
                .tag(tag)
//                .isMultipart(true)
                .params(params)
                .execute(callback);
    }

    /**
     * Json 参数 Post 请求
     *
     * @param url      请求地址
     * @param json     请求的字符串
     * @param callback 回调方法
     * @param tag      类对象的hashCode
     */
    public static void post(String url, String json, AbsCallback callback, Object
            tag) {
        OkGo.<String>post(url)
                .tag(tag)
//                .isMultipart(true)
                .params("jsonObj", json)
                .execute(callback);
    }

    /**
     * 传文件
     *
     * @param url       请求地址
     * @param filePaths 文件路径集合
     * @param callback  回调方法
     * @param tag       类对象的hashCode
     */
    public static void postFile(String url, Map<String, String> params, List<String> filePaths,
                                AbsCallback callback, Object tag) {

        PostRequest request = OkGo.<String>post(url);
        if (tag != null)
            request.tag(tag);

        for (Map.Entry<String, String> entry : params.entrySet()) {
            request.params(entry.getKey(), entry.getValue());
        }

        for (int i = 0; i < filePaths.size(); i++) {
            request.params("file" + i, new File(filePaths.get(i)));
        }
        request.execute(callback);

    }

    /**
     * 按tag取消请求
     *
     * @param tag 类对象的hashCode
     */
    public static void cancel(Object tag) {
        OkGo.getInstance().cancelTag(tag);
    }

    /**
     * 取消所有请求
     */
    public static void cancelAll() {
        OkGo.getInstance().cancelAll();
    }


    /**
     * Activity声明周期回调
     */
    public static class BaseActivityLifecycleCallbacks implements Application
            .ActivityLifecycleCallbacks {

        @Override
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

        }

        @Override
        public void onActivityStarted(Activity activity) {

        }

        @Override
        public void onActivityResumed(Activity activity) {

        }

        @Override
        public void onActivityPaused(Activity activity) {

        }

        @Override
        public void onActivityStopped(Activity activity) {

        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

        }

        @Override
        public void onActivityDestroyed(Activity activity) {
            cancel(activity.hashCode());
        }
    }


}
