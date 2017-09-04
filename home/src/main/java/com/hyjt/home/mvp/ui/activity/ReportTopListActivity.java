package com.hyjt.home.mvp.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.hyjt.frame.base.BaseActivity;
import com.hyjt.frame.di.component.AppComponent;
import com.hyjt.frame.utils.UiUtils;
import com.hyjt.home.R;
import com.hyjt.home.di.component.DaggerReportTopListComponent;
import com.hyjt.home.di.module.ReportTopListModule;
import com.hyjt.home.mvp.contract.ReportTopListContract;
import com.hyjt.home.mvp.presenter.ReportTopListPresenter;

import static com.hyjt.frame.utils.Preconditions.checkNotNull;

@Route(path = "/home/ReportTopListActivity")
public class ReportTopListActivity extends BaseActivity<ReportTopListPresenter> implements ReportTopListContract.View {


    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerReportTopListComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .reportTopListModule(new ReportTopListModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(Bundle savedInstanceState) {
        return R.layout.home_activity_report_top_list; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
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


    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
