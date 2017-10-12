package com.hyjt.home.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.hyjt.frame.base.BaseActivity;
import com.hyjt.frame.di.component.AppComponent;
import com.hyjt.frame.utils.UiUtils;

import com.hyjt.home.di.component.DaggerPsonLoanEditComponent;
import com.hyjt.home.di.module.PsonLoanEditModule;
import com.hyjt.home.mvp.contract.PsonLoanEditContract;
import com.hyjt.home.mvp.presenter.PsonLoanEditPresenter;

import com.hyjt.home.R;


import static com.hyjt.frame.utils.Preconditions.checkNotNull;


@Route(path = "/home/PsonLoanEditActivity")
public class PsonLoanEditActivity extends BaseActivity<PsonLoanEditPresenter> implements PsonLoanEditContract.View {


    private android.widget.RelativeLayout mRlPsonloan;
    private android.widget.ImageView mIvTopBack;
    private android.widget.TextView mTvTitle;
    private android.widget.ImageView mIvTopSelect;

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerPsonLoanEditComponent
                .builder()
                .appComponent(appComponent)
                .psonLoanEditModule(new PsonLoanEditModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(Bundle savedInstanceState) {
        return R.layout.home_activity_pson_loan_edit;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        mRlPsonloan = (RelativeLayout) findViewById(R.id.rl_psonloan);
        mIvTopBack = (ImageView) findViewById(R.id.iv_top_back);
        mIvTopBack.setOnClickListener(v -> finish());
        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mTvTitle.setText("个人借款");
        mTvTitle.setOnClickListener(v -> finish());
        mIvTopSelect = (ImageView) findViewById(R.id.iv_top_select);
        mIvTopSelect.setVisibility(View.GONE);

//        mPresenter.
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
