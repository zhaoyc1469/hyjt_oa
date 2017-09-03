package com.hyjt.home.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.hyjt.frame.base.BaseActivity;
import com.hyjt.frame.di.component.AppComponent;
import com.hyjt.frame.utils.UiUtils;

import com.hyjt.home.di.component.DaggerSLConsultCreateComponent;
import com.hyjt.home.di.module.SLConsultCreateModule;
import com.hyjt.home.mvp.contract.SLConsultCreateContract;
import com.hyjt.home.mvp.presenter.SLConsultCreatePresenter;

import com.hyjt.home.R;

import static com.hyjt.frame.utils.Preconditions.checkNotNull;


public class SLConsultCreateActivity extends BaseActivity<SLConsultCreatePresenter> implements SLConsultCreateContract.View {


    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerSLConsultCreateComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .sLConsultCreateModule(new SLConsultCreateModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(Bundle savedInstanceState) {
        return R.layout.home_activity_slconsult_create; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }


    @Override
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
        UiUtils.snackbarText(message);
    }

    @Override
    public void killMyself() {
        finish();
    }


}
