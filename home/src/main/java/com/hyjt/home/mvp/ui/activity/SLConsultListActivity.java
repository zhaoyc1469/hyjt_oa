package com.hyjt.home.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.hyjt.frame.base.BaseActivity;
import com.hyjt.frame.di.component.AppComponent;
import com.hyjt.frame.utils.UiUtils;
import com.hyjt.home.R;
import com.hyjt.home.di.component.DaggerSLConsultListComponent;
import com.hyjt.home.di.module.SLConsultListModule;
import com.hyjt.home.mvp.contract.SLConsultListContract;
import com.hyjt.home.mvp.presenter.SLConsultListPresenter;

import static com.hyjt.frame.utils.Preconditions.checkNotNull;

@Route(path = "/home/SLConsultListActivity")
public class SLConsultListActivity extends BaseActivity<SLConsultListPresenter> implements SLConsultListContract.View {


    private android.widget.RelativeLayout mRlSlconsultList;
    private android.widget.ImageView mIvTopBack;
    private android.widget.TextView mTvTitle;
    private android.widget.ImageView mIvTopSelect;
    private android.widget.LinearLayout mLlSlconsultType;
    private android.widget.Button mBtnWaitType;
    private android.widget.Button mBtnReadedType;
    private android.widget.Button mBtnNewSlconsult;
    private android.support.v4.widget.SwipeRefreshLayout mSrlSlconsultList;
    private android.support.v7.widget.RecyclerView mRecySlconsultList;
    private String type;

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerSLConsultListComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .sLConsultListModule(new SLConsultListModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(Bundle savedInstanceState) {
        return R.layout.home_activity_slconsult_list; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(Bundle savedInstanceState) {

        Intent intent = getIntent();
        type = intent.getStringExtra("type");

        mRlSlconsultList = (RelativeLayout) findViewById(R.id.rl_slconsult_list);
        mIvTopBack = (ImageView) findViewById(R.id.iv_top_back);
        mIvTopBack.setOnClickListener(v -> finish());
        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mTvTitle.setText("平级协商");
        mTvTitle.setOnClickListener(v -> finish());
        mIvTopSelect = (ImageView) findViewById(R.id.iv_top_select);
        mIvTopSelect.setVisibility(View.GONE);
        mLlSlconsultType = (LinearLayout) findViewById(R.id.ll_slconsult_type);
        mBtnWaitType = (Button) findViewById(R.id.btn_wait_type);
        mBtnReadedType = (Button) findViewById(R.id.btn_readed_type);
        mBtnNewSlconsult = (Button) findViewById(R.id.btn_new_slconsult);
        mSrlSlconsultList = (SwipeRefreshLayout) findViewById(R.id.srl_slconsult_list);
        mRecySlconsultList = (RecyclerView) findViewById(R.id.recy_slconsult_list);

        mBtnNewSlconsult.setOnClickListener(v -> ARouter.getInstance().build("/home/SLConsultCreateActivity")
                .navigation());

        mBtnWaitType.setOnClickListener(v -> {
            this.type = "UnRead";
            mPresenter.getSLCList(true, "UnRead");
        });

        mBtnReadedType.setOnClickListener(v -> {
            this.type = "Opened";
            mPresenter.getSLCList(true, "Opened");
        });

        mPresenter.getSLCList(true, type);
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

}
