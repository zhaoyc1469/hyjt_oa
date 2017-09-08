package com.hyjt.home.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.hyjt.frame.base.BaseActivity;
import com.hyjt.frame.di.component.AppComponent;
import com.hyjt.frame.utils.UiUtils;

import com.hyjt.home.di.component.DaggerContributeIdeaCreateComponent;
import com.hyjt.home.di.module.ContributeIdeaCreateModule;
import com.hyjt.home.mvp.contract.ContributeIdeaCreateContract;
import com.hyjt.home.mvp.presenter.ContributeIdeaCreatePresenter;

import com.hyjt.home.R;


import static com.hyjt.frame.utils.Preconditions.checkNotNull;


public class ContributeIdeaCreateActivity extends BaseActivity<ContributeIdeaCreatePresenter> implements ContributeIdeaCreateContract.View {


    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerContributeIdeaCreateComponent
                .builder()
                .appComponent(appComponent)
                .contributeIdeaCreateModule(new ContributeIdeaCreateModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(Bundle savedInstanceState) {
        return R.layout.activity_contribute_idea_create;
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
