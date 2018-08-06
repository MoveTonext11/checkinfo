package com.anrongtec.cp;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

import com.anrongtec.cp.interfaces.HttpUtil;
import com.blankj.utilcode.util.Utils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cookie.CookieJarImpl;
import com.lzy.okgo.cookie.store.SPCookieStore;
import com.lzy.okgo.https.HttpsUtils;
import com.lzy.okgo.interceptor.HttpLoggingInterceptor;

import org.litepal.LitePal;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;

/**
 * 自定义application
 */
public class MyApplication extends MultiDexApplication {
    public static final long CUSTOM_TIMEOUT = 60000;
    public static Context appContextInstance;

    @Override
    public void onCreate() {
        super.onCreate();

        //工具类初始化
        Utils.init(this);
        //Okgo初始化
        initOkGo();
        //初始化数据库--LitePal
        LitePal.initialize(this);
        //【获取网络请求公共参数】增加获取全局变量的方法
        appContextInstance = getApplicationContext();
        registerActivityLifecycleCallbacks(new HttpUtil.BaseActivityLifecycleCallbacks());
    }


    private void initOkGo() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(CUSTOM_TIMEOUT, TimeUnit.MILLISECONDS)//全局的连接超时时间
                .readTimeout(CUSTOM_TIMEOUT, TimeUnit.MILLISECONDS);//全局的读取超时时间

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor("HTTP LOG");
        //log打印级别，决定了log显示的详细程度
        loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY);
        //log颜色级别，决定了log在控制台显示的颜色
        loggingInterceptor.setColorLevel(Level.INFO);
        builder.addInterceptor(loggingInterceptor);

        //使用bks证书和密码管理客户端证书
        HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory(getResources()
                .openRawResource(R.raw.crtappcenter), "jeff86685751", (X509TrustManager) null);

        builder.sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager);
        //配置https的域名匹配规则
        builder.hostnameVerifier(new SafeHostnameVerifier());

        //使用sp保持cookie，如果cookie不过期，则一直有效
        builder.cookieJar(new CookieJarImpl(new SPCookieStore(this)));
        OkGo.getInstance().init(this)
                .setRetryCount(0)
                .setOkHttpClient(builder.build());
    }

    private class SafeHostnameVerifier implements HostnameVerifier {
        @Override
        public boolean verify(String hostname, SSLSession session) {
            //TODO 验证主机名是否匹配
            //return hostname.equals("server.111.com");
            return true;
        }
    }
}
