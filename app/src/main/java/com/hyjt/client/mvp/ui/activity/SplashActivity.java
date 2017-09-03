package com.hyjt.client.mvp.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.alibaba.android.arouter.launcher.ARouter;
import com.hyjt.app.R;
import com.hyjt.client.di.component.DaggerSplashComponent;
import com.hyjt.client.di.module.SplashModule;
import com.hyjt.client.mvp.contract.SplashContract;
import com.hyjt.client.mvp.presenter.SplashPresenter;
import com.hyjt.frame.base.BaseActivity;
import com.hyjt.frame.di.component.AppComponent;
import com.hyjt.frame.utils.UiUtils;

import cn.jpush.android.api.JPushInterface;

import static com.hyjt.frame.utils.Preconditions.checkNotNull;


public class SplashActivity extends BaseActivity<SplashPresenter> implements SplashContract.View {


    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerSplashComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .splashModule(new SplashModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(Bundle savedInstanceState) {
        return R.layout.activity_splash; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(Bundle savedInstanceState) {

        JPushInterface.init(getApplicationContext());
        JPushInterface.resumePush(getApplicationContext());


        // 测试用正式版去掉
        ARouter.openDebug();

        ARouter.getInstance()
                .build("/app/LoginActivity")
                .navigation();
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
