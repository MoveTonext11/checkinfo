package com.anrongtec.cp.ui;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.anrongtec.cp.R;
import com.blankj.utilcode.util.ToastUtils;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author libo
 * @ClassName: CountAnalyzeActivity
 * @Description: 统计分析页面
 * @date 2018/5/13
 **/
public class CountAnalyzeActivity extends BaseActivity {


    public final static String TAG = CountAnalyzeActivity.class.getSimpleName();
    //日,周,月,年,高级查询按钮
    @BindView(R.id.btn_day)
    Button btnDay;
    @BindView(R.id.btn_week)
    Button btnWeek;
    @BindView(R.id.btn_month)
    Button btnMonth;
    @BindView(R.id.btn_year)
    Button btnYear;
    @BindView(R.id.btn_highQuery)
    Button btnHighQuery;

    @BindView(R.id.tv_count_carsTotalIn)
    TextView tvCountCarsTotalIn;        //核查车数量
    @BindView(R.id.tv_count_isCarsTotalIn)
    TextView tvCountIsCarsTotalIn;      //中标车辆数

    @BindView(R.id.tv_count_personsTotalIn)
    TextView tvCountPersonsTotalIn;     //核查人员数
    @BindView(R.id.tv_count_isPersonsTotalIn)
    TextView tvCountIsPersonsTotalIn;   //中标人员数
    //自定义Dialog View
    TextView startDate, endDate;

    //数据信息，更新统计数据
    String carsTotal, carsIsTotal, personTotal, personIsTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_analyze);
        ButterKnife.bind(this);
        setTitle(R.string.main_function_count_analyze);

        initView();

    }


    private void initView() {
        tvCountCarsTotalIn.setText(addComma("120"));
        tvCountIsCarsTotalIn.setText(addComma("12"));
        tvCountPersonsTotalIn.setText(addComma("200"));
        tvCountIsPersonsTotalIn.setText(addComma("5"));

    }

    @OnClick({R.id.btn_day, R.id.btn_week, R.id.btn_month, R.id.btn_year, R.id.btn_highQuery})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_day:
                carsTotal = "120";
                carsIsTotal = "12";
                personTotal = "200";
                personIsTotal = "5";
                break;
            case R.id.btn_week:
                carsTotal = "900";
                carsIsTotal = "20";
                personTotal = "800";
                personIsTotal = "29";
                break;
            case R.id.btn_month:
                carsTotal = "1800";
                carsIsTotal = "40";
                personTotal = "1000";
                personIsTotal = "44";
                break;
            case R.id.btn_year:
                carsTotal = "18067";
                carsIsTotal = "100";
                personTotal = "70000";
                personIsTotal = "46";
                break;
            case R.id.btn_highQuery:
                newDialog();
                carsTotal = "18067";
                carsIsTotal = "100";
                personTotal = "70000";
                personIsTotal = "46";
                break;


        }
        tvCountCarsTotalIn.setText(addComma(carsTotal));
        tvCountIsCarsTotalIn.setText(addComma(carsIsTotal));
        tvCountPersonsTotalIn.setText(addComma(personTotal));
        tvCountIsPersonsTotalIn.setText(addComma(personIsTotal));

    }

    private void newDialog() {
        View inputView = LayoutInflater.from(this).inflate(R.layout.date_select_layout, null);
        startDate = inputView.findViewById(R.id.start_date);
        endDate = inputView.findViewById(R.id.end_date);
        startDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog(1);
            }
        });
        endDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog(2);
            }
        });
        new MaterialDialog.Builder(CountAnalyzeActivity.this)
                .title("高级查询")
                .titleColor(getResources().getColor(R.color.colorAccent))
                .positiveText("确定")
                .negativeText("取消")
                .canceledOnTouchOutside(false)
                .customView(inputView, false)
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction
                            which) {
                    }
                })
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction
                            which) {
                        String split = startDate.getText().toString().replace("-", "");

//                        if (start <= end) {
//                            ToastUtils.showShort("调用接口返回更新数据");
//                            carsTotal = "180";
//                            carsIsTotal = "10";
//                            personTotal = "700";
//                            personIsTotal = "46";
//                        }
                    }
                })
                .show();
    }

    /**
     * 弹出选择日期dialog
     *
     * @param flag
     * @return
     */
    private void showDateDialog(final int flag) {

        Calendar d = Calendar.getInstance(Locale.CHINA);
        // 创建一个日历引用d，通过静态方法getInstance() 从指定时区 Locale.CHINA 获得一个日期实例
        Date myDate = new Date();
        // 创建一个Date实例
        d.setTime(myDate);
        // 设置日历的时间，把一个新建Date实例myDate传入
        int year = d.get(Calendar.YEAR);
        int month = d.get(Calendar.MONTH);
        int day = d.get(Calendar.DAY_OF_MONTH);
        //初始化默认日期year, month, day
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog
                .OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                String month = String.valueOf(monthOfYear + 1);
                String day = String.valueOf(dayOfMonth);
                if (month.length() < 2) {
                    month = 0 + month;
                }
                if (day.length() < 2) {
                    day = 0 + day;
                }
                System.out.println("输出月份: " + month + "输出日期：" + day);
                if (flag == 1) {
                    startDate.setText(String.format(Locale.CHINA, "%d-%s-%s", year, month, day));
                } else {
                    endDate.setText(String.format(Locale.CHINA, "%d-%s-%s", year, month, day));
                    int start = Integer.parseInt(startDate.getText().toString().replace("-",
                            ""));
                    int end = Integer.parseInt(endDate.getText().toString().replace("-", ""));
                    if (start > end) {
                        ToastUtils.showShort("开始时间大于结束时间，请重写选择");
                        endDate.setError("开始时间大于结束时间，请重写选择");
                    }
                }
            }
        }, year, month, day);

        datePickerDialog.setMessage("请选择日期");
        datePickerDialog.show();
    }

    /**
     * 数字三位一个逗号
     *
     * @param str
     * @return
     */
    private String addComma(String str) {
        DecimalFormat decimalFormat = new DecimalFormat(",###");
        return decimalFormat.format(Double.parseDouble(str));
    }
}
