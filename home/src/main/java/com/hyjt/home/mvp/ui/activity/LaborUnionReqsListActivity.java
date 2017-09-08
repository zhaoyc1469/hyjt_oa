package com.hyjt.home.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.hyjt.frame.base.BaseActivity;
import com.hyjt.frame.di.component.AppComponent;
import com.hyjt.frame.utils.UiUtils;

import com.hyjt.home.di.component.DaggerLaborUnionReqsListComponent;
import com.hyjt.home.di.module.LaborUnionReqsListModule;
import com.hyjt.home.mvp.contract.LaborUnionReqsListContract;
import com.hyjt.home.mvp.presenter.LaborUnionReqsListPresenter;

import com.hyjt.home.R;
import com.hyjt.home.mvp.ui.adapter.LUAppealAdapter;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import static com.hyjt.frame.utils.Preconditions.checkNotNull;


@Route(path = "/home/LaborUnionReqsListActivity")
public class LaborUnionReqsListActivity extends BaseActivity<LaborUnionReqsListPresenter> implements LaborUnionReqsListContract.View {


    private android.widget.ImageView mIvTopBack;
    private android.widget.TextView mTvTitle;
    private android.widget.ImageView mIvTopSelect;
    private android.widget.LinearLayout mLlLuAppealType;
    private android.widget.Button mBtnWaitType;
    private android.widget.Button mBtnReadedType;
    private android.widget.Button mBtnNewReport;
    private android.support.v4.widget.SwipeRefreshLayout mSrlLuAppealList;
    private android.support.v7.widget.RecyclerView mRecyLuAppealList;
    private String type;

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerLaborUnionReqsListComponent
                .builder()
                .appComponent(appComponent)
                .laborUnionReqsListModule(new LaborUnionReqsListModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(Bundle savedInstanceState) {
        return R.layout.home_activity_labor_union_reqs_list;
    }

    @Override
    public void initData(Bundle savedInstanceState) {

        Intent intent = getIntent();
        type = intent.getStringExtra("type");

        mIvTopBack = (ImageView) findViewById(R.id.iv_top_back);
        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mIvTopSelect = (ImageView) findViewById(R.id.iv_top_select);
        mLlLuAppealType = (LinearLayout) findViewById(R.id.ll_lu_appeal_type);
        mBtnWaitType = (Button) findViewById(R.id.btn_wait_type);
        mBtnReadedType = (Button) findViewById(R.id.btn_readed_type);
        mBtnNewReport = (Button) findViewById(R.id.btn_new_report);
        mSrlLuAppealList = (SwipeRefreshLayout) findViewById(R.id.srl_lu_appeal_list);
        mRecyLuAppealList = (RecyclerView) findViewById(R.id.recy_lu_appeal_list);


        mBtnNewReport.setOnClickListener(v -> ARouter.getInstance()
                .build("/home/LaborUnionReqsCreateActivity").navigation());
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.getLUList(true, type);
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


    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void setAdapter(LUAppealAdapter LUAppealAdapter) {

    }

    @Override
    public void startLoadMore() {

    }

    @Override
    public void endLoadMore() {

    }

    @Override
    public void endLoad() {

    }

}
