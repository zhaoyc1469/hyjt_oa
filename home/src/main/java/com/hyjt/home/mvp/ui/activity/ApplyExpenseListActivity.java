package com.hyjt.home.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.hyjt.frame.base.BaseActivity;
import com.hyjt.frame.di.component.AppComponent;
import com.hyjt.frame.utils.UiUtils;

import com.hyjt.home.di.component.DaggerApplyExpenseListComponent;
import com.hyjt.home.di.module.ApplyExpenseListModule;
import com.hyjt.home.mvp.contract.ApplyExpenseListContract;
import com.hyjt.home.mvp.presenter.ApplyExpenseListPresenter;

import com.hyjt.home.R;


import static com.hyjt.frame.utils.Preconditions.checkNotNull;


public class ApplyExpenseListActivity extends BaseActivity<ApplyExpenseListPresenter> implements ApplyExpenseListContract.View {


    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerApplyExpenseListComponent
                .builder()
                .appComponent(appComponent)
                .applyExpenseListModule(new ApplyExpenseListModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(Bundle savedInstanceState) {
        return R.layout.home_activity_apply_expense_list;
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
