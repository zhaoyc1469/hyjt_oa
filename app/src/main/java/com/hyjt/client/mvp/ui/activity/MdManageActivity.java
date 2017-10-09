package com.hyjt.client.mvp.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.hyjt.app.R;
import com.hyjt.client.mvp.ui.adapter.ModuleEditAdapter;
import com.hyjt.db.DbHelper;
import com.hyjt.db.bean.ModuleBeanDb;
import com.hyjt.db.gen.DaoSession;
import com.hyjt.db.gen.ModuleBeanDbDao;
import com.hyjt.frame.base.BaseActivity;
import com.hyjt.frame.di.component.AppComponent;
import com.hyjt.frame.event.RefModuleEvent;
import com.hyjt.frame.utils.UiUtils;

import com.hyjt.client.di.component.DaggerMdManageComponent;
import com.hyjt.client.di.module.MdManageModule;
import com.hyjt.client.mvp.contract.MdManageContract;
import com.hyjt.client.mvp.presenter.MdManagePresenter;


import org.simple.eventbus.EventBus;

import java.util.List;

import static com.hyjt.frame.utils.Preconditions.checkNotNull;


public class MdManageActivity extends BaseActivity<MdManagePresenter> implements MdManageContract.View {


    private android.support.v7.widget.RecyclerView mRecyMdManage;
    private DaoSession daoSession;
    private ModuleBeanDbDao moduleBeanDbDao;
    private ModuleEditAdapter manageAdapter;
    private android.widget.Button mBtnEditCommit;
    private TextView mTvTitle;
    private ImageView mIvTopSelect;
    private ImageView mIvTopBack;

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
        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mTvTitle.setText("模块编辑");
        mTvTitle.setOnClickListener(v -> {
            EventBus.getDefault().post(new RefModuleEvent(), "Ref_Module");
            finish();
        });
        mIvTopBack = (ImageView) findViewById(R.id.iv_top_back);
        mIvTopBack.setOnClickListener(v -> {
            EventBus.getDefault().post(new RefModuleEvent(), "Ref_Module");
            finish();
        });
        mIvTopSelect = (ImageView) findViewById(R.id.iv_top_select);
        mIvTopSelect.setVisibility(View.GONE);

        mRecyMdManage = (RecyclerView) findViewById(R.id.recy_md_manage);
        mBtnEditCommit = (Button) findViewById(R.id.btn_edit_commit);
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

        mBtnEditCommit.setVisibility(View.GONE);
        mBtnEditCommit.setOnClickListener(v -> EventBus.getDefault().post(new RefModuleEvent(), "Ref_Module"));
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        EventBus.getDefault().post(new RefModuleEvent(), "Ref_Module");
    }
}
