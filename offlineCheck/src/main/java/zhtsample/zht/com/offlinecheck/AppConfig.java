package zhtsample.zht.com.offlinecheck;


import android.os.Environment;

/**
 * app的配置文件
 * Created by MSI on 2017/6/14.
 */

public class AppConfig {
    ///---------------------------------------------- 演示模式 --------------------------------------------------

    //演示模式,  只做测试使用, 不用同步, 安装就能使用;
    public static final boolean DEMO_MODE = true;
    //默认db文件
    public static final String DEFAULT_DB = "test.ar";

    //------------------------------------------------------------------------------------------------------------
    //根目录
    public static final String DB_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + "/offlineCheck/";
    //日志目录
    public static final String LOG_PATH = DB_PATH+"log/";
    //日志文件
    public static final String LOG_FILE_PATH = LOG_PATH+"log.txt";
    //授权文件
    public static final String LICENSE_FILE = DB_PATH+"anrongtec.license";


    //日志文件超过该长度, 下次启动app则删除该日志
    public static final long LOG_SIZE = 5*1024*1024L;

    //自动删除核查记录的时间点, 即删除多少天以前的记录;  如果小于SYNCHRONIZATION_TIME, 则默认设置为SYNCHRONIZATION_TIME
    //只会对备份记录进行操作, 而需要回传的记录不会有影响
    public static final long AUTO_DELETE_RECORD_TIME = 90*24*3600*1000L;

    //------------------------------------    一般配置信息   ------------------------------------------------------
    //同步时间间隔, 小于1天, 设置会默认为1天
    public static final long SYNCHRONIZATION_TIME = DEMO_MODE ? 99999*24*3600*1000L : 15*24*3600*1000L;

    //是否启用本地布控模块, 本地布控就是,某地区自己布控的人员信息,不回传,不统计,只做核查用
    //影响: main页面右上方是否支持切换,
    public static final boolean LOCAL_DATA_MODE = false;

}
