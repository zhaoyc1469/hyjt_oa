package com.hyjt.client.mvp.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.alibaba.android.arouter.launcher.ARouter;
import com.hyjt.app.R;
import com.hyjt.client.di.component.DaggerSplashComponent;
import com.hyjt.client.di.module.SplashModule;
import com.hyjt.client.mvp.contract.SplashContract;
import com.hyjt.client.mvp.presenter.SplashPresenter;
import com.hyjt.db.DbHelper;
import com.hyjt.db.bean.ModuleBeanDb;
import com.hyjt.db.gen.DaoSession;
import com.hyjt.db.gen.ModuleBeanDbDao;
import com.hyjt.frame.base.BaseActivity;
import com.hyjt.frame.base.BaseApplication;
import com.hyjt.frame.di.component.AppComponent;
import com.hyjt.frame.utils.UiUtils;

import cn.jpush.android.api.JPushInterface;

import static com.hyjt.frame.utils.Preconditions.checkNotNull;


public class SplashActivity extends BaseActivity<SplashPresenter> implements SplashContract.View {


    private DaoSession daoSession;
    private ModuleBeanDbDao moduleBeanDbDao;

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

        //初始化推送
        JPushInterface.init(getApplicationContext());
        JPushInterface.resumePush(getApplicationContext());

        // 测试用正式版去掉
//        ARouter.openDebug();


        daoSession = DbHelper.getInstance().getDaoSession();
        moduleBeanDbDao = daoSession.getModuleBeanDbDao();
        if (moduleBeanDbDao.loadAll().size() <= 0) {
            moduleBeanDbDao.insert(new ModuleBeanDb("公司架构", 1, true, "h_gsjg", 0));
            moduleBeanDbDao.insert(new ModuleBeanDb("公司治理", 1, true, "h_gsjg", 0));
            moduleBeanDbDao.insert(new ModuleBeanDb("部门治理", 1, true, "h_gsjg", 0));
            moduleBeanDbDao.insert(new ModuleBeanDb("政府网站", 1, true, "h_gsjg", 0));
            moduleBeanDbDao.insert(new ModuleBeanDb("公司网站", 1, true, "h_gswz", 0));

            moduleBeanDbDao.insert(new ModuleBeanDb("会议纪要", 2, true, "h_hyjy", 0));
            moduleBeanDbDao.insert(new ModuleBeanDb("待解决任务", 2, true, "h_djjrw", 0));
            moduleBeanDbDao.insert(new ModuleBeanDb("长期待解决", 2, true, "h_cqdjj", 0));
            moduleBeanDbDao.insert(new ModuleBeanDb("督办任务", 2, true, "h_dbrw", 0));
            moduleBeanDbDao.insert(new ModuleBeanDb("客户关系", 2, true, "h_khgx", 0));
            moduleBeanDbDao.insert(new ModuleBeanDb("来访消息", 2, true, "h_lfxx", 0));

            moduleBeanDbDao.insert(new ModuleBeanDb("借款申请", 3, true, "h_jksq", 0));
            moduleBeanDbDao.insert(new ModuleBeanDb("招聘", 4, true, "h_jksq", 0));
            moduleBeanDbDao.insert(new ModuleBeanDb("项目档案", 5, true, "h_jksq", 0));
            moduleBeanDbDao.insert(new ModuleBeanDb("借款审批", 6, true, "h_jksq", 0));
            moduleBeanDbDao.insert(new ModuleBeanDb("报告", 7, true, "h_jksq", 0));
        }


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
