package com.hyjt.client.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.hyjt.app.R;
import com.hyjt.frame.base.BaseActivity;
import com.hyjt.frame.di.component.AppComponent;
import com.hyjt.frame.utils.UiUtils;

import com.hyjt.client.di.component.DaggerMdManageComponent;
import com.hyjt.client.di.module.MdManageModule;
import com.hyjt.client.mvp.contract.MdManageContract;
import com.hyjt.client.mvp.presenter.MdManagePresenter;



import static com.hyjt.frame.utils.Preconditions.checkNotNull;


public class MdManageActivity extends BaseActivity<MdManagePresenter> implements MdManageContract.View {


    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerMdManageComponent
                .builder()
                .appComponent(appComponent)
                .mdManageModule(new MdManageModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(Bundle savedInstanceState) {
        return R.layout.activity_md_manage;
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
