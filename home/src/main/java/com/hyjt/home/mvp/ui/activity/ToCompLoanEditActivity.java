package com.hyjt.home.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.hyjt.frame.base.BaseActivity;
import com.hyjt.frame.di.component.AppComponent;
import com.hyjt.home.di.component.DaggerToCompLoanEditComponent;
import com.hyjt.home.di.module.ToCompLoanEditModule;
import com.hyjt.home.mvp.contract.ToCompLoanEditContract;
import com.hyjt.home.mvp.model.entity.Resp.CompLoanDetailResp;
import com.hyjt.home.mvp.presenter.ToCompLoanEditPresenter;

import com.hyjt.home.R;



@Route(path = "/home/ToCompLoanEditActivity")
public class ToCompLoanEditActivity extends BaseActivity<ToCompLoanEditPresenter> implements ToCompLoanEditContract.View {


    private String compLoanId;

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerToCompLoanEditComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .toCompLoanEditModule(new ToCompLoanEditModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(Bundle savedInstanceState) {
        return R.layout.home_activity_to_comp_loan_edit; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        Intent intent = getIntent();
        compLoanId = intent.getStringExtra("CompLoanId");
        mPresenter.getCompLoanDetail(compLoanId);
    }

    @Override
    public void showMessage(@NonNull String message) {
    }

    @Override
    public void killMyself() {
        finish();
    }


    @Override
    public void hideLoading() {

    }

    @Override
    public void showCLDetail(CompLoanDetailResp compLoanDetailResp) {

    }
}
