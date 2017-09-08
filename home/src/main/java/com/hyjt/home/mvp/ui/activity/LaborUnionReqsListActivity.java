package com.hyjt.home.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.hyjt.frame.base.BaseActivity;
import com.hyjt.frame.di.component.AppComponent;
import com.hyjt.frame.utils.UiUtils;

import com.hyjt.home.di.component.DaggerLaborUnionReqsListComponent;
import com.hyjt.home.di.module.LaborUnionReqsListModule;
import com.hyjt.home.mvp.contract.LaborUnionReqsListContract;
import com.hyjt.home.mvp.presenter.LaborUnionReqsListPresenter;

import com.hyjt.home.R;


import static com.hyjt.frame.utils.Preconditions.checkNotNull;


public class LaborUnionReqsListActivity extends BaseActivity<LaborUnionReqsListPresenter> implements LaborUnionReqsListContract.View {


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
