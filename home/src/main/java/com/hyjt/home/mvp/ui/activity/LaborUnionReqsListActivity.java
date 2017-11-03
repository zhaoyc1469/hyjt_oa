package com.hyjt.home.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.hyjt.frame.base.BaseActivity;
import com.hyjt.frame.di.component.AppComponent;
import com.hyjt.frame.event.RefreshListEvent;
import com.hyjt.frame.utils.UiUtils;

import com.hyjt.home.di.component.DaggerLaborUnionReqsListComponent;
import com.hyjt.home.di.module.LaborUnionReqsListModule;
import com.hyjt.home.mvp.contract.LaborUnionReqsListContract;
import com.hyjt.home.mvp.presenter.LaborUnionReqsListPresenter;

import com.hyjt.home.R;
import com.hyjt.home.mvp.ui.adapter.LUAppealAdapter;
import com.paginate.Paginate;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import org.simple.eventbus.Subscriber;
import org.simple.eventbus.ThreadMode;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

import static com.hyjt.frame.utils.Preconditions.checkNotNull;


@Route(path = "/home/LaborUnionReqsListActivity")
public class LaborUnionReqsListActivity extends BaseActivity<LaborUnionReqsListPresenter> implements LaborUnionReqsListContract.View, SwipeRefreshLayout.OnRefreshListener {


    private android.widget.ImageView mIvTopBack;
    private android.widget.TextView mTvTitle;
    private android.widget.ImageView mIvTopSelect;
    private android.widget.LinearLayout mLlLuAppealType;
    private android.widget.Button mBtnWaitType;
    private android.widget.Button mBtnReadedType;
    private android.widget.Button mBtnNewReport;
    private android.support.v4.widget.SwipeRefreshLayout mSrlLuAppealList;
    private android.support.v7.widget.RecyclerView mRecyLuAppealList;
    private String type;
    private boolean isLoadingMore;
    private Paginate mPaginate;
    private LinearLayoutManager linearLayoutManager;

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerLaborUnionReqsListComponent
                .builder()
                .appComponent(appComponent)
                .laborUnionReqsListModule(new LaborUnionReqsListModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(Bundle savedInstanceState) {
        return R.layout.home_activity_labor_union_reqs_list;
    }

    @Override
    public void initData(Bundle savedInstanceState) {

        Intent intent = getIntent();
        type = intent.getStringExtra("type");

        mIvTopBack = (ImageView) findViewById(R.id.iv_top_back);
        mIvTopBack.setOnClickListener(v -> finish());
        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mTvTitle.setText("工会诉求");
        mTvTitle.setOnClickListener(v -> finish());
        mIvTopSelect = (ImageView) findViewById(R.id.iv_top_select);
        mIvTopSelect.setVisibility(View.GONE);
        mLlLuAppealType = (LinearLayout) findViewById(R.id.ll_lu_appeal_type);
        mBtnWaitType = (Button) findViewById(R.id.btn_wait_type);
        mBtnReadedType = (Button) findViewById(R.id.btn_readed_type);
        mBtnNewReport = (Button) findViewById(R.id.btn_new_report);
        mSrlLuAppealList = (SwipeRefreshLayout) findViewById(R.id.srl_lu_appeal_list);
        mRecyLuAppealList = (RecyclerView) findViewById(R.id.recy_lu_appeal_list);


        mBtnNewReport.setOnClickListener(v -> ARouter.getInstance()
                .build("/home/LaborUnionReqsCreateActivity").navigation());

        mBtnWaitType.setOnClickListener(v -> {
            this.type = "UnRead";
            mPresenter.getLUList(true, "UnRead");
        });

        mBtnReadedType.setOnClickListener(v -> {
            this.type = "Opened";
            mPresenter.getLUList(true, "Opened");
        });

        if ("Mine".equals(type)){
            mLlLuAppealType.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.getLUList(true, type);
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
    public void showLoading() {
        Observable.just(1)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(integer -> mSrlLuAppealList.setRefreshing(true));
    }

    @Override
    public void hideLoading() {
        mSrlLuAppealList.setRefreshing(false);
    }

    @Override
    @Subscriber(tag = "Refresh_List", mode = ThreadMode.MAIN)
    public void refreshList(RefreshListEvent refreshListEvent) {
        onRefresh();
    }


    @Override
    public void setAdapter(LUAppealAdapter LUAppealAdapter) {

        linearLayoutManager = new LinearLayoutManager(this);
        mRecyLuAppealList.setLayoutManager(linearLayoutManager);
        mRecyLuAppealList.setAdapter(LUAppealAdapter);
        mSrlLuAppealList.setOnRefreshListener(this);
        initPaginate();
    }

    @Override
    public void startLoadMore() {
        isLoadingMore = true;
    }

    @Override
    public void endLoadMore() {
        isLoadingMore = false;
    }

    @Override
    public void endLoad() {
        mPaginate.setHasMoreDataToLoad(false);
    }


    /**
     * 初始化Paginate,用于加载更多
     */
    private void initPaginate() {
        if (mPaginate == null) {
            Paginate.Callbacks callbacks = new Paginate.Callbacks() {
                @Override
                public void onLoadMore() {
                    mPresenter.getLUList(false, type);
                }

                @Override
                public boolean isLoading() {
                    return isLoadingMore;
                }

                @Override
                public boolean hasLoadedAllItems() {
                    return false;
                }
            };

            mPaginate = Paginate.with(mRecyLuAppealList, callbacks)
                    .setLoadingTriggerThreshold(0)
                    .build();
            mPaginate.setHasMoreDataToLoad(false);
        }
    }

    @Override
    public void onRefresh() {
        mPresenter.getLUList(true, type);
    }
}
