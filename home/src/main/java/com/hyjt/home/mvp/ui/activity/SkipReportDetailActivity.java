package com.hyjt.home.mvp.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.hyjt.frame.base.BaseActivity;
import com.hyjt.frame.di.component.AppComponent;
import com.hyjt.frame.utils.UiUtils;
import com.hyjt.home.R;
import com.hyjt.home.di.component.DaggerSkipReportDetailComponent;
import com.hyjt.home.di.module.SkipReportDetailModule;
import com.hyjt.home.mvp.contract.SkipReportDetailContract;
import com.hyjt.home.mvp.presenter.SkipReportDetailPresenter;

import static com.hyjt.frame.utils.Preconditions.checkNotNull;


public class SkipReportDetailActivity extends BaseActivity<SkipReportDetailPresenter> implements SkipReportDetailContract.View {


    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerSkipReportDetailComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .skipReportDetailModule(new SkipReportDetailModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(Bundle savedInstanceState) {
        return R.layout.home_activity_skip_report_detail; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
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
