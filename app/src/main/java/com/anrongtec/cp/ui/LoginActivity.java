package com.anrongtec.cp.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.anrongtec.cp.R;
import com.blankj.utilcode.util.BarUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * 登录页面activity
 * @author 程潇宇
 * @date 2018-5-9
 */
public class LoginActivity extends BaseActivity {

	@BindView(R.id.iv_login_icon)
	ImageView ivIcon;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitleShow(false);
		BarUtils.setStatusBarAlpha(this, 0);
		setContentView(R.layout.activity_login);
		ButterKnife.bind(this);



	}

	@OnClick(R.id.iv_login_icon)
	public void onClick(View v) {
		switch(v.getId()){
		    case  R.id.iv_login_icon:
			    start(this, MainActivity.class);
		        break;
		    default:
		        break;
		}
	}
}

