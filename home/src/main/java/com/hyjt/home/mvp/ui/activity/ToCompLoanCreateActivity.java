package com.hyjt.home.mvp.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.hyjt.frame.base.BaseActivity;
import com.hyjt.frame.di.component.AppComponent;
import com.hyjt.home.di.component.DaggerToCompLoanCreateComponent;
import com.hyjt.home.di.module.ToCompLoanCreateModule;
import com.hyjt.home.mvp.contract.ToCompLoanCreateContract;
import com.hyjt.home.mvp.presenter.ToCompLoanCreatePresenter;

import com.hyjt.home.R;


public class ToCompLoanCreateActivity extends BaseActivity<ToCompLoanCreatePresenter> implements ToCompLoanCreateContract.View {


    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerToCompLoanCreateComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .toCompLoanCreateModule(new ToCompLoanCreateModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(Bundle savedInstanceState) {
        return R.layout.home_activity_to_comp_loan_create; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }


    @Override
    public void showMessage(@NonNull String message) {
    }

    @Override
    public void killMyself() {
        finish();
    }


}
