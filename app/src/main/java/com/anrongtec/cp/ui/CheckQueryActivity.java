package com.anrongtec.cp.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

import com.anrongtec.cp.R;
import com.anrongtec.cp.adapter.RecordPersonAdapter;
import com.anrongtec.cp.interfaces.HttpInterfaces;
import com.anrongtec.cp.interfaces.HttpUrl;
import com.anrongtec.cp.interfaces.callback.StringDialogCallback;
import com.anrongtec.cp.manager.CheckHestory;
import com.anrongtec.cp.utils.DateTools;
import com.anrongtec.cp.utils.GsonUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 核查记录界面（新）  人员核查车辆核查整合界面
 * 2018年7月19日09:38:48
 */
public class CheckQueryActivity extends BaseActivity {

    private RecyclerView rv_control_checkquery;
    private BaseQuickAdapter<CheckHestory, BaseViewHolder> baseQuickAdapter;
    private List<CheckHestory> listPerson;
    int page = 1;
    boolean checkDate = true;
    private ToggleButton tb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_query);
        setTitle("核查核录");
        initView();
        //默认显示数据
        getDate(checkDate);
        //框架填充
        initData();
    }

    /**
     * 适配框架填充
     */
    private void initData() {
        baseQuickAdapter = new RecordPersonAdapter(R.layout.item_check_record_person, listPerson);
        rv_control_checkquery.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager
                .VERTICAL, false));
        baseQuickAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                //跳转到详情页面
//                DetailInfoActivity.start(CheckQueryActivity.this, listPerson.get(position));
            }
        });
        baseQuickAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                getDate(checkDate);
            }
        }, rv_control_checkquery);
        rv_control_checkquery.setAdapter(baseQuickAdapter);
        if (listPerson.isEmpty()) {
            baseQuickAdapter.setEmptyView(getLayoutInflater().inflate(R.layout.fragment_empty, null));
        }
    }

    /**
     * 数据请求接口的切换
     * @param ischecked
     */
    private void getDate(boolean ischecked) {
        if (ischecked) {
            getPersonData();
        } else {
            getServiceDate();
        }
    }

    /**
     * 布局填充
     */
    private void initView() {
        listPerson = new ArrayList<>();
        rv_control_checkquery = (RecyclerView) findViewById(R.id.rv_control_checkquery);
        //获取切换按钮控件
        tb = (ToggleButton) findViewById(R.id.tb);
        //设置默认数据
        tb.setText("历史核查");
//        tb.setBackgroundResource(R.drawable.bg_keyboard_btn);
        //设置切换监听
        tb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                tb.setTextOff("今日核查");
                tb.setTextOn("历史核查");
                checkDate = isChecked;
                //根据按钮的选择进行切换数据的接口
                getDate(checkDate);
            }
        });
    }

    /**
     * 获取数据  本地存储
     */
    private void getPersonData() {
        //请求服务器接口数据   暂无接口
        HashMap<String, String> hashMap = new HashMap<>();
        long l = System.currentTimeMillis();
        //数据获取
        hashMap.put("startDate", DateTools.endToDate(String.valueOf(l)));
        hashMap.put("userId", "130828198708260234");
        hashMap.put("endDate", DateTools.endToDate(String.valueOf(l)));
        HttpInterfaces.checkhestory(HttpUrl.CheckHestory, hashMap, new StringDialogCallback(this, "数据获取中...") {
            @Override
            public void onSuccess(Response<String> response) {
                String body = response.body();
                CheckHestory decode = GsonUtil.decode(body, CheckHestory.class);
                listPerson.add(decode);//查询到的数据存储到集合中
            }
        });
    }

    /**
     * 获取服务器数据
     */
    private void getServiceDate() {
        //请求服务器接口数据   暂无接口
        HashMap<String, String> hashMap = new HashMap<>();
        long l = System.currentTimeMillis();
        //数据获取
        hashMap.put("startDate", DateTools.startToDate(String.valueOf(l)));
        hashMap.put("userId", "130828198708260234");
        hashMap.put("endDate", DateTools.endToDate(String.valueOf(l)));
        //测试数据
//        hashMap.put("startDate", "2017-04-26");
//        hashMap.put("endDate", "2017-09-28");
        HttpInterfaces.checkhestory(HttpUrl.CheckHestory, hashMap, new StringDialogCallback(this, "数据获取中...") {
            @Override
            public void onSuccess(Response<String> response) {
                String body = response.body();
                CheckHestory decode = GsonUtil.decode(body, CheckHestory.class);
                listPerson.add(decode);//查询到的数据存储到集合中
            }
        });
    }
}
