package com.hyjt.home.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.hyjt.frame.base.BaseActivity;
import com.hyjt.frame.di.component.AppComponent;
import com.hyjt.frame.event.RefreshListEvent;
import com.hyjt.frame.utils.UiUtils;

import com.hyjt.home.di.component.DaggerApplyExpenseEditComponent;
import com.hyjt.home.di.module.ApplyExpenseEditModule;
import com.hyjt.home.mvp.contract.ApplyExpenseEditContract;
import com.hyjt.home.mvp.presenter.ApplyExpenseEditPresenter;

import com.hyjt.home.R;


import org.simple.eventbus.EventBus;

import static com.hyjt.frame.utils.Preconditions.checkNotNull;

@Route(path = "/home/ApplyExpenseEditActivity")
public class ApplyExpenseEditActivity extends BaseActivity<ApplyExpenseEditPresenter> implements ApplyExpenseEditContract.View {


    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerApplyExpenseEditComponent
                .builder()
                .appComponent(appComponent)
                .applyExpenseEditModule(new ApplyExpenseEditModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(Bundle savedInstanceState) {
        return R.layout.home_activity_apply_expense_edit;
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
        EventBus.getDefault().post(new RefreshListEvent(), "Refresh_List");
    }


}
