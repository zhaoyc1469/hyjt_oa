package com.hyjt.home.mvp.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.hyjt.frame.base.BaseActivity;
import com.hyjt.frame.di.component.AppComponent;
import com.hyjt.frame.utils.UiUtils;

import com.hyjt.home.di.component.DaggerPsonLoanCreateComponent;
import com.hyjt.home.di.module.PsonLoanCreateModule;
import com.hyjt.home.mvp.contract.PsonLoanCreateContract;
import com.hyjt.home.mvp.presenter.PsonLoanCreatePresenter;

import com.hyjt.home.R;


import static com.hyjt.frame.utils.Preconditions.checkNotNull;

@Route(path = "/home/PsonLoanCreateActivity")
public class PsonLoanCreateActivity extends BaseActivity<PsonLoanCreatePresenter> implements PsonLoanCreateContract.View {


    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerPsonLoanCreateComponent
                .builder()
                .appComponent(appComponent)
                .psonLoanCreateModule(new PsonLoanCreateModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(Bundle savedInstanceState) {
        return R.layout.home_activity_pson_loan_create;
    }

    @Override
    public void initData(Bundle savedInstanceState) {

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
