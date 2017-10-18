package com.hyjt.frame.base;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDexApplication;

import com.hyjt.frame.base.delegate.AppDelegate;
import com.hyjt.frame.di.component.AppComponent;


/**
 * 本项目由
 * mvp
 * +dagger2
 * +retrofit
 * +rxjava
 * +androideventbus
 * +butterknife组成
 * 请配合官方wiki文档https://github.com/JessYanCoding/MVPArms/wiki,学习本框架
 */
public class BaseApplication extends MultiDexApplication implements App {
    private AppDelegate mAppDelegate;

    private static Context context;
//    private DaoSession daoSession;

    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        this.mAppDelegate = new AppDelegate(this);
        this.mAppDelegate.onCreate();
    }

    /**
     * 在模拟环境中程序终止时会被调用
     */
    @Override
    public void onTerminate() {
        super.onTerminate();
        if (mAppDelegate != null)
            this.mAppDelegate.onTerminate();
    }


    /**
     * 将AppComponent返回出去,供其它地方使用, AppComponent接口中声明的方法返回的实例,在getAppComponent()拿到对象后都可以直接使用
     *
     * @return
     */
    @Override
    public AppComponent getAppComponent() {
        return mAppDelegate.getAppComponent();
    }

}
