package com.hyjt.home.mvp.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.hyjt.frame.base.BaseActivity;
import com.hyjt.frame.di.component.AppComponent;
import com.hyjt.frame.utils.UiUtils;
import com.hyjt.home.R;
import com.hyjt.home.di.component.DaggerCreateMtComponent;
import com.hyjt.home.di.module.CreateMtModule;
import com.hyjt.home.mvp.contract.CreateMtContract;
import com.hyjt.home.mvp.presenter.CreateMtPresenter;

import static com.hyjt.frame.utils.Preconditions.checkNotNull;


@Route(path = "/home/CreateMtActivity")
public class CreateMtActivity extends BaseActivity<CreateMtPresenter> implements CreateMtContract.View, View.OnClickListener {


    private android.widget.ImageView mIvTopBack;
    private android.widget.TextView mTvTitle;
    private android.widget.TextView mIvTopEdit;
    private android.widget.RelativeLayout mRlTabType;
    private android.widget.TextView mTvIndex1;
    private android.widget.TextView mTvIndex2;
    private TextView mTvIndex3;

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerCreateMtComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .createMtModule(new CreateMtModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(Bundle savedInstanceState) {
        return R.layout.home_activity_create_mt; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        mIvTopBack = (ImageView) findViewById(R.id.iv_top_back);
        mIvTopBack.setOnClickListener(v -> finish());
        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mTvTitle.setText("编辑测试");
        mIvTopEdit = (TextView) findViewById(R.id.iv_top_edit);
        mRlTabType = (RelativeLayout) findViewById(R.id.rl_tab_type);
        mTvIndex1 = (TextView) findViewById(R.id.tv_index1);
        mTvIndex2 = (TextView) findViewById(R.id.tv_index2);
        mTvIndex3 = (TextView) findViewById(R.id.tv_index3);
        mTvIndex1.setOnClickListener(this);
        mTvIndex2.setOnClickListener(this);
        mTvIndex3.setOnClickListener(this);
    }


    @Override
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
        UiUtils.snackbarText(message);
    }

    @Override
    public void killMyself() {
        finish();
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.tv_index1) {
            mTvIndex1.setTextColor(ContextCompat.getColor(this,R.color.home_common_0E8CFF));
            mTvIndex2.setTextColor(ContextCompat.getColor(this,R.color.home_common_FFFFFF));
            mTvIndex3.setTextColor(ContextCompat.getColor(this,R.color.home_common_FFFFFF));

            mTvIndex1.setBackgroundResource(R.drawable.home_bg_tab_left);
            mTvIndex2.setBackground(null);
            mTvIndex3.setBackground(null);
        } else if (i == R.id.tv_index2) {
            mTvIndex1.setTextColor(ContextCompat.getColor(this,R.color.home_common_FFFFFF));
            mTvIndex2.setTextColor(ContextCompat.getColor(this,R.color.home_common_0E8CFF));
            mTvIndex3.setTextColor(ContextCompat.getColor(this,R.color.home_common_FFFFFF));

            mTvIndex1.setBackground(null);
            mTvIndex2.setBackgroundResource(R.drawable.home_bg_tab_middle);
            mTvIndex3.setBackground(null);
        } else if (i == R.id.tv_index3) {
            mTvIndex1.setTextColor(ContextCompat.getColor(this,R.color.home_common_FFFFFF));
            mTvIndex2.setTextColor(ContextCompat.getColor(this,R.color.home_common_FFFFFF));
            mTvIndex3.setTextColor(ContextCompat.getColor(this,R.color.home_common_0E8CFF));

            mTvIndex1.setBackground(null);
            mTvIndex2.setBackground(null);
            mTvIndex3.setBackgroundResource(R.drawable.home_bg_tab_right);
        }

    }
}
