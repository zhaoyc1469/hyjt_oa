package com.hyjt.home.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.hyjt.frame.base.BaseActivity;
import com.hyjt.frame.di.component.AppComponent;
import com.hyjt.frame.utils.UiUtils;

import com.hyjt.home.di.component.DaggerLaborUnionReqsCreateComponent;
import com.hyjt.home.di.module.LaborUnionReqsCreateModule;
import com.hyjt.home.mvp.contract.LaborUnionReqsCreateContract;
import com.hyjt.home.mvp.presenter.LaborUnionReqsCreatePresenter;

import com.hyjt.home.R;


import static com.hyjt.frame.utils.Preconditions.checkNotNull;


public class LaborUnionReqsCreateActivity extends BaseActivity<LaborUnionReqsCreatePresenter> implements LaborUnionReqsCreateContract.View {


    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerLaborUnionReqsCreateComponent
                .builder()
                .appComponent(appComponent)
                .laborUnionReqsCreateModule(new LaborUnionReqsCreateModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(Bundle savedInstanceState) {
        return R.layout.activity_labor_union_reqs_create;
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
