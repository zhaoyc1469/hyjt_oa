package com.hyjt.home.mvp.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.hyjt.frame.base.BaseActivity;
import com.hyjt.frame.di.component.AppComponent;
import com.hyjt.frame.utils.UiUtils;
import com.hyjt.home.R;
import com.hyjt.home.di.component.DaggerSkipReportListComponent;
import com.hyjt.home.di.module.SkipReportListModule;
import com.hyjt.home.mvp.contract.SkipReportListContract;
import com.hyjt.home.mvp.presenter.SkipReportListPresenter;

import static com.hyjt.frame.utils.Preconditions.checkNotNull;

@Route(path = "/home/SkipReportListActivity")
public class SkipReportListActivity extends BaseActivity<SkipReportListPresenter> implements SkipReportListContract.View {


    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerSkipReportListComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .skipReportListModule(new SkipReportListModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(Bundle savedInstanceState) {
        return R.layout.home_activity_skip_report_list; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }


    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

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


    @Override
    public void setAdapter() {

    }

    @Override
    public void startLoadMore() {

    }

    @Override
    public void endLoadMore() {

    }

    @Override
    public void endLoad() {

    }
}
