package zhtsample.zht.com.offlinecheck.util;

import android.content.Context;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.util.Log;

import zhtsample.zht.com.offlinecheck.AppConfig;

/**
 * Created by MSI on 2017/7/12.
 */

public class MediaScannerUtil {

    private static final String ANRONGTEC_DB = AppConfig.DB_PATH + "anrongtec.db";
    private static final String BACKUP_RECORD_DB = AppConfig.DB_PATH + "backupRecord.db";
    private static final String CHECK_RECORD_UPLOAD_DB = AppConfig.DB_PATH + "checkRecordUpload.db";
    private static final String DEV_RECORD_UPLOAD_DB = AppConfig.DB_PATH + "devRecordUpload.db";
    private static final String WHOLE_PACKAGE_ZIP = AppConfig.DB_PATH + "wholePackage.zip";
    private static final String LOCAL_PACKAGE_ZIP = AppConfig.DB_PATH + "localPackage.zip";
    private static final String LICENSE_FILE = AppConfig.LICENSE_FILE;

    private static final String LOG = AppConfig.LOG_PATH+"log.txt";

    public static final String[] PACKAGES = new String[]{WHOLE_PACKAGE_ZIP, LOCAL_PACKAGE_ZIP};
    public static final String[] DBFILES = new String[]{ANRONGTEC_DB, BACKUP_RECORD_DB, CHECK_RECORD_UPLOAD_DB, DEV_RECORD_UPLOAD_DB};

    private static void scanFiles(final Context ctx, final String[] files) {
        new Thread(){
            @Override
            public void run() {
                MediaScannerConnection.scanFile(ctx, files, null, new MediaScannerConnection.OnScanCompletedListener() {
                    @Override
                    public void onScanCompleted(String path, Uri uri) {
                        Log.i("media", path+ "Media Scan Completed");
                    }
                });
            }
        }.start();

    }

    /**
     * 扫描授权
     * @param ctx
     */
    public static void scanLicense(Context ctx){
        scanFiles(ctx, new String[]{LICENSE_FILE});
    }

    /**
     * 扫描布控包
     * @param ctx
     */
    public static void scanPackages(Context ctx){
        scanFiles(ctx, PACKAGES);
    }

    /**
     * 扫描布控文件
     * @param ctx
     */
    public static void scanDbFiles(Context ctx){
        scanFiles(ctx, DBFILES);
    }

    /**
     * 扫描文件夹
     */
    public static void scanLog(Context ctx){
        scanFiles(ctx, new String[]{LOG});
    }

}
