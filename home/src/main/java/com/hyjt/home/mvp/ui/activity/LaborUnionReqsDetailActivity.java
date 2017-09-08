package com.hyjt.home.mvp.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.hyjt.frame.base.BaseActivity;
import com.hyjt.frame.di.component.AppComponent;
import com.hyjt.frame.utils.UiUtils;

import com.hyjt.home.di.component.DaggerLaborUnionReqsDetailComponent;
import com.hyjt.home.di.module.LaborUnionReqsDetailModule;
import com.hyjt.home.mvp.contract.LaborUnionReqsDetailContract;
import com.hyjt.home.mvp.presenter.LaborUnionReqsDetailPresenter;

import com.hyjt.home.R;


import static com.hyjt.frame.utils.Preconditions.checkNotNull;


@Route(path = "/home/LaborUnionReqsDetailActivity")
public class LaborUnionReqsDetailActivity extends BaseActivity<LaborUnionReqsDetailPresenter> implements LaborUnionReqsDetailContract.View {


    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerLaborUnionReqsDetailComponent
                .builder()
                .appComponent(appComponent)
                .laborUnionReqsDetailModule(new LaborUnionReqsDetailModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(Bundle savedInstanceState) {
        return R.layout.home_activity_labor_union_reqs_detail;
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
