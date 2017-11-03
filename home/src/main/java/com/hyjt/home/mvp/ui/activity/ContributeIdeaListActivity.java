package com.hyjt.home.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.hyjt.frame.base.BaseActivity;
import com.hyjt.frame.di.component.AppComponent;
import com.hyjt.frame.event.RefreshListEvent;
import com.hyjt.frame.utils.UiUtils;

import com.hyjt.home.di.component.DaggerContributeIdeaListComponent;
import com.hyjt.home.di.module.ContributeIdeaListModule;
import com.hyjt.home.mvp.contract.ContributeIdeaListContract;
import com.hyjt.home.mvp.presenter.ContributeIdeaListPresenter;

import com.hyjt.home.R;
import com.hyjt.home.mvp.ui.adapter.ContributeIAdapter;
import com.paginate.Paginate;


import org.simple.eventbus.Subscriber;
import org.simple.eventbus.ThreadMode;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

import static com.hyjt.frame.utils.Preconditions.checkNotNull;

@Route(path = "/home/ContributeIdeaListActivity")
public class ContributeIdeaListActivity extends BaseActivity<ContributeIdeaListPresenter> implements ContributeIdeaListContract.View, SwipeRefreshLayout.OnRefreshListener {


    private android.widget.ImageView mIvTopBack;
    private android.widget.TextView mTvTitle;
    private android.widget.ImageView mIvTopSelect;
    private android.widget.LinearLayout mLlContributeType;
    private android.widget.Button mBtnWaitType;
    private android.widget.Button mBtnReadedType;
    private android.widget.Button mBtnNewReport;
    private android.support.v4.widget.SwipeRefreshLayout mSrlContributeList;
    private android.support.v7.widget.RecyclerView mRecyContributeList;
    private String type;
    private boolean isLoadingMore;
    private Paginate mPaginate;
    private LinearLayoutManager linearLayoutManager;

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerContributeIdeaListComponent
                .builder()
                .appComponent(appComponent)
                .contributeIdeaListModule(new ContributeIdeaListModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(Bundle savedInstanceState) {
        return R.layout.home_activity_contribute_idea_list;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        Intent intent = getIntent();
        type = intent.getStringExtra("type");

        mIvTopBack = (ImageView) findViewById(R.id.iv_top_back);
        mIvTopBack.setOnClickListener(v -> finish());
        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mTvTitle.setOnClickListener(v -> finish());
        mTvTitle.setText("献计献策");
        mIvTopSelect = (ImageView) findViewById(R.id.iv_top_select);
        mIvTopSelect.setVisibility(View.GONE);
        mLlContributeType = (LinearLayout) findViewById(R.id.ll_contribute_type);
        mBtnWaitType = (Button) findViewById(R.id.btn_wait_type);
        mBtnReadedType = (Button) findViewById(R.id.btn_readed_type);
        mBtnNewReport = (Button) findViewById(R.id.btn_new_report);
        mSrlContributeList = (SwipeRefreshLayout) findViewById(R.id.srl_contribute_list);
        mRecyContributeList = (RecyclerView) findViewById(R.id.recy_contribute_list);
        mSrlContributeList.setOnRefreshListener(this);

        mBtnNewReport.setOnClickListener(v -> ARouter.getInstance()
                .build("/home/ContributeIdeaCreateActivity").navigation());

        mBtnWaitType.setOnClickListener(v -> {
            this.type = "UnRead";
            mPresenter.getControbuteList(true, "UnRead");
        });

        mBtnReadedType.setOnClickListener(v -> {
            this.type = "Opened";
            mPresenter.getControbuteList(true, "Opened");
        });

        if ("Mine".equals(type)){
            mLlContributeType.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.getControbuteList(true, type);
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
                .subscribe(integer -> mSrlContributeList.setRefreshing(true));
    }

    @Override
    public void hideLoading() {
        mSrlContributeList.setRefreshing(false);
    }

    @Override
    @Subscriber(tag = "Refresh_List", mode = ThreadMode.MAIN)
    public void refreshList(RefreshListEvent refreshListEvent) {
        onRefresh();
    }


    @Override
    public void setAdapter(ContributeIAdapter contributeIAdapter) {
        linearLayoutManager = new LinearLayoutManager(this);
        mRecyContributeList.setLayoutManager(linearLayoutManager);
        mRecyContributeList.setAdapter(contributeIAdapter);
        mSrlContributeList.setOnRefreshListener(this);
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

    @Override
    public void onRefresh() {
        mPresenter.getControbuteList(true, type);
    }

    /**
     * 初始化Paginate,用于加载更多
     */
    private void initPaginate() {
        if (mPaginate == null) {
            Paginate.Callbacks callbacks = new Paginate.Callbacks() {
                @Override
                public void onLoadMore() {
                    mPresenter.getControbuteList(false, type);
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

            mPaginate = Paginate.with(mRecyContributeList, callbacks)
                    .setLoadingTriggerThreshold(0)
                    .build();
            mPaginate.setHasMoreDataToLoad(false);
        }
    }
}
