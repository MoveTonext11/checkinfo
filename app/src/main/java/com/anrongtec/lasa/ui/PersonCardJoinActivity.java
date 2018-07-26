package com.anrongtec.lasa.ui;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.anrongtec.lasa.R;
import com.anrongtec.lasa.ui.fragment.CheckPersonFragment;
import com.anrongtec.lasa.utils.HBUtils;
import com.anrongtec.ocr.OcrPersonActivity;
import com.blankj.utilcode.util.ToastUtils;

import java.io.File;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PersonCardJoinActivity extends BaseActivity {


    private static final String TAG = PersonCardJoinActivity.class.getSimpleName();
    private static final int CROP_PICTURE = 30;
    @BindView(R.id.iv_scan_IDCard)
    ImageView ivScanIDCard;         //扫描证件照图标

    @BindView(R.id.iv_add_image)
    ImageView ivAddImage;           //添加现场照片
    @BindView(R.id.btn_comp)
    Button btnComp;                 //提交按钮
    @BindView(R.id.tv_resultData)
    TextView tvResultData;          //返回结果信息
    //获取证件照的头像资源
    Bitmap bitmap;
    //OCR 证件照存储的路径
    private static final String PATH = Environment.getExternalStorageDirectory() +
            "/alpha/SIDCard/";
    //系统相册路径
    private String path = Environment.getExternalStorageDirectory() +
            File.separator + Environment.DIRECTORY_DCIM + File.separator;

    //返回码，相机
    private static final int RESULT_CAMERA = 200;

    Uri photoUri = null;
    Uri cropImageUri=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_card_join);
        ButterKnife.bind(this);
        setTitle("人证合一");

    }


    @OnClick({R.id.iv_scan_IDCard, R.id.iv_add_image, R.id.btn_comp})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_scan_IDCard:
                scanIdCard(v);
                break;
            case R.id.iv_add_image:
                launchCamera();
                break;
            case R.id.btn_comp:
                ToastUtils.showShort("该功能暂未开发，后续更新");
                break;
        }
    }

    private void launchCamera() {
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            File file = new File(getExternalCacheDir(), "out_image.jpg");
            try {
                if (!file.exists()) {
                    file.delete();
                }
                file.createNewFile();
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);


                photoUri = Uri.fromFile(file);

                intent.putExtra(MediaStore.Images.Media.ORIENTATION, 0);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);

                startActivityForResult(intent, RESULT_CAMERA);
            } catch (IOException e) {

                e.printStackTrace();
            }
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    public void scanIdCard(View view) {
        Intent intent = new Intent(this, OcrPersonActivity.class);
        startActivityForResult(intent, CheckPersonFragment.REQUEST_PERSON_CODE);
    }

    /**
     * 图片裁剪
     *
     * @param
     */
    public void startPhotoZoom(Uri uri) {
        File CropPhoto = new File(getExternalCacheDir(), "crop_image.jpg");
        try {
            if (CropPhoto.exists()) {
                CropPhoto.delete();
            }
            CropPhoto.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
       cropImageUri = Uri.fromFile(CropPhoto);
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION); //添加这一句表示对目标应用临时授权该Uri所代表的文件
        }
        // 下面这个crop=true是设置在开启的Intent中设置显示的VIEW可裁剪
        intent.putExtra("crop", "true");
        intent.putExtra("scale", true);

        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);

        intent.putExtra("outputX", 100);
        intent.putExtra("outputY", 150);

        intent.putExtra("return-data", false);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, cropImageUri);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true); // no face detection
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        System.out.println("输出的Code： " + requestCode + "输入Result ： " + resultCode);
        if (resultCode != RESULT_OK) {
            return;
        }
        if (requestCode == CheckPersonFragment.REQUEST_PERSON_CODE) {
            String headImageUrl = data.getStringExtra("headImage");
            Log.d(TAG, "ORC返回的数据信息是:" + data.getStringExtra("recogResult") + "\t" +
                    headImageUrl);
            bitmap = HBUtils.getBitmapRes(headImageUrl);
            ivScanIDCard.setImageBitmap(bitmap);
        }
        if (requestCode == RESULT_CAMERA) {
            startPhotoZoom(photoUri);
            System.out.println("相册地址："+photoUri+"\t另一个地址"+cropImageUri);
//            bitmap = HBUtils.getBitmapRes("storage/emulated/0/Android/data/com.anrongtec.hbhc/cache/out_image.jpg");


            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(),
                            photoUri);
                ivAddImage.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
