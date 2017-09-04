package com.hyjt.home.mvp.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.hyjt.frame.base.BaseActivity;
import com.hyjt.frame.di.component.AppComponent;
import com.hyjt.frame.utils.UiUtils;
import com.hyjt.home.R;
import com.hyjt.home.di.component.DaggerSkipReportCreateComponent;
import com.hyjt.home.di.module.SkipReportCreateModule;
import com.hyjt.home.mvp.contract.SkipReportCreateContract;
import com.hyjt.home.mvp.presenter.SkipReportCreatePresenter;

import static com.hyjt.frame.utils.Preconditions.checkNotNull;

@Route(path = "/home/SkipReportCreateActivity")
public class SkipReportCreateActivity extends BaseActivity<SkipReportCreatePresenter> implements SkipReportCreateContract.View {


    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerSkipReportCreateComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .skipReportCreateModule(new SkipReportCreateModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(Bundle savedInstanceState) {
        return R.layout.home_activity_skip_report_create; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
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
