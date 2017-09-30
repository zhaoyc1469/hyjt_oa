package com.hyjt.client.mvp.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hyjt.app.R;
import com.hyjt.client.mvp.ui.adapter.ModuleEditAdapter;
import com.hyjt.db.DbHelper;
import com.hyjt.db.bean.ModuleBeanDb;
import com.hyjt.db.gen.DaoSession;
import com.hyjt.db.gen.ModuleBeanDbDao;
import com.hyjt.frame.base.BaseActivity;
import com.hyjt.frame.di.component.AppComponent;
import com.hyjt.frame.utils.UiUtils;

import com.hyjt.client.di.component.DaggerMdManageComponent;
import com.hyjt.client.di.module.MdManageModule;
import com.hyjt.client.mvp.contract.MdManageContract;
import com.hyjt.client.mvp.presenter.MdManagePresenter;


import java.util.List;

import static com.hyjt.frame.utils.Preconditions.checkNotNull;


public class MdManageActivity extends BaseActivity<MdManagePresenter> implements MdManageContract.View {


    private android.support.v7.widget.RecyclerView mRecyMdManage;
    private DaoSession daoSession;
    private ModuleBeanDbDao moduleBeanDbDao;
    private ModuleEditAdapter manageAdapter;

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

        mRecyMdManage = (RecyclerView) findViewById(R.id.recy_md_manage);
        mRecyMdManage.setLayoutManager(new LinearLayoutManager(this));

        daoSession = DbHelper.getInstance().getDaoSession();
        moduleBeanDbDao = daoSession.getModuleBeanDbDao();
        List<ModuleBeanDb> moduleBeanDbs = moduleBeanDbDao.loadAll();

        manageAdapter = new ModuleEditAdapter(moduleBeanDbs);
        manageAdapter.setOnItemClickListener((itemView, position, moduleBeanDb) -> {
            moduleBeanDb.setIsShow(!moduleBeanDb.getIsShow());
            moduleBeanDbDao.update(moduleBeanDb);
        });
        mRecyMdManage.setAdapter(manageAdapter);
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
