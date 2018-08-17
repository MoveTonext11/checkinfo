package zhtsample.zht.com.offlinecheck;

/**
 * Created by MSI on 2017/6/29.
 */

public interface HcSdkInitCallback {

    void onSuccess(int code);
    void onFail(int code, String msg);

}
