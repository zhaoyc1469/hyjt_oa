package com.hyjt.home.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.hyjt.frame.base.BaseActivity;
import com.hyjt.frame.di.component.AppComponent;
import com.hyjt.frame.utils.UiUtils;

import com.hyjt.home.di.component.DaggerContributeIdeaListComponent;
import com.hyjt.home.di.module.ContributeIdeaListModule;
import com.hyjt.home.mvp.contract.ContributeIdeaListContract;
import com.hyjt.home.mvp.presenter.ContributeIdeaListPresenter;

import com.hyjt.home.R;


import static com.hyjt.frame.utils.Preconditions.checkNotNull;

@Route(path = "/home/ContributeIdeaListActivity")
public class ContributeIdeaListActivity extends BaseActivity<ContributeIdeaListPresenter> implements ContributeIdeaListContract.View {


    private android.widget.ImageView mIvTopBack;
    private android.widget.TextView mTvTitle;
    private android.widget.ImageView mIvTopSelect;
    private android.widget.LinearLayout mLlContributeType;
    private android.widget.Button mBtnWaitType;
    private android.widget.Button mBtnReadedType;
    private android.widget.Button mBtnNewReport;
    private android.support.v4.widget.SwipeRefreshLayout mSrlContributeList;
    private android.support.v7.widget.RecyclerView mRecyContributeList;
    private String type;

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerContributeIdeaListComponent
                .builder()
                .appComponent(appComponent)
                .contributeIdeaListModule(new ContributeIdeaListModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(Bundle savedInstanceState) {
        return R.layout.home_activity_contribute_idea_list;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        Intent intent = getIntent();
        type = intent.getStringExtra("type");

        mIvTopBack = (ImageView) findViewById(R.id.iv_top_back);
        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mIvTopSelect = (ImageView) findViewById(R.id.iv_top_select);
        mLlContributeType = (LinearLayout) findViewById(R.id.ll_contribute_type);
        mBtnWaitType = (Button) findViewById(R.id.btn_wait_type);
        mBtnReadedType = (Button) findViewById(R.id.btn_readed_type);
        mBtnNewReport = (Button) findViewById(R.id.btn_new_report);
        mSrlContributeList = (SwipeRefreshLayout) findViewById(R.id.srl_contribute_list);
        mRecyContributeList = (RecyclerView) findViewById(R.id.recy_contribute_list);
    }


    @Override
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
        UiUtils.snackbarText(message);
        shortToast(message);
    }


    @Override
    public void killMyself() {
        finish();
    }


}
