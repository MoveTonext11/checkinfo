package com.anrongtec.ocr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * @author huiliu
 */
public class OcrMainActivity extends AppCompatActivity {

    private TextView mTv_message;
    public static final int REQUEST_PERSON_CODE = 10;
    public static final int REQUEST_CAR_CODE = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ocr_main);
        mTv_message = (TextView) findViewById(R.id.tv_message);
    }

    public void scanIdCard(View view) {
        Intent intent = new Intent(this, OcrPersonActivity.class);
        startActivityForResult(intent, REQUEST_PERSON_CODE);
    }

    public void scanCarId(View view) {
        Intent intent = new Intent(this, OcrCarActivity.class);
        startActivityForResult(intent, REQUEST_CAR_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_PERSON_CODE && resultCode == RESULT_OK) {
            String recogResult = data.getStringExtra("recogResult");
            String recogName = data.getStringExtra("recogName");
            String recogSex = data.getStringExtra("recogSex");
            String recogBorn = data.getStringExtra("recogBorn");
            String recogAdress = data.getStringExtra("recogAdress");
            String recogNation = data.getStringExtra("recogNation");
            mTv_message.setText("身份证号："+recogResult);
        } else if (requestCode == REQUEST_CAR_CODE && resultCode == RESULT_OK) {
            String carResult = data.getStringExtra("carResult");
            mTv_message.setText("车牌号：" + carResult);
        }
    }
}
