package com.hyjt.home.mvp.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.hyjt.frame.base.BaseActivity;
import com.hyjt.frame.di.component.AppComponent;
import com.hyjt.frame.utils.UiUtils;
import com.hyjt.home.R;
import com.hyjt.home.di.component.DaggerSLConsultListComponent;
import com.hyjt.home.di.module.SLConsultListModule;
import com.hyjt.home.mvp.contract.SLConsultListContract;
import com.hyjt.home.mvp.presenter.SLConsultListPresenter;

import static com.hyjt.frame.utils.Preconditions.checkNotNull;

@Route(path = "/home/SLConsultListActivity")
public class SLConsultListActivity extends BaseActivity<SLConsultListPresenter> implements SLConsultListContract.View {


    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerSLConsultListComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .sLConsultListModule(new SLConsultListModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(Bundle savedInstanceState) {
        return R.layout.home_activity_slconsult_list; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
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
