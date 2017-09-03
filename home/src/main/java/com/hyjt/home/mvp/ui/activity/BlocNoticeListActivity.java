package com.hyjt.home.mvp.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.hyjt.frame.base.BaseActivity;
import com.hyjt.frame.di.component.AppComponent;
import com.hyjt.frame.utils.UiUtils;
import com.hyjt.home.R;
import com.hyjt.home.di.component.DaggerBlocNoticeListComponent;
import com.hyjt.home.di.module.BlocNoticeListModule;
import com.hyjt.home.mvp.contract.BlocNoticeListContract;
import com.hyjt.home.mvp.presenter.BlocNoticeListPresenter;
import com.hyjt.home.mvp.ui.adapter.BlocNoticeAdapter;
import com.paginate.Paginate;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

import static com.hyjt.frame.utils.Preconditions.checkNotNull;

@Route(path = "/home/BlocNoticeListActivity")
public class BlocNoticeListActivity extends BaseActivity<BlocNoticeListPresenter> implements BlocNoticeListContract.View, SwipeRefreshLayout.OnRefreshListener {


    private android.widget.RelativeLayout mRlNoticeList;
    private android.widget.ImageView mIvTopBack;
    private android.widget.TextView mTvTitle;
    private android.widget.ImageView mIvTopSelect;
    private android.widget.Button mBtnNewNotice;
    private android.support.v4.widget.SwipeRefreshLayout mSrlNoticeList;
    private android.support.v7.widget.RecyclerView mRecyNoticeList;

    private LinearLayoutManager linearLayoutManager;
    private Paginate mPaginate;
    private boolean isLoadingMore;

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerBlocNoticeListComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .blocNoticeListModule(new BlocNoticeListModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(Bundle savedInstanceState) {
        return R.layout.home_activity_bloc_notice_list; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        mRlNoticeList = (RelativeLayout) findViewById(R.id.rl_notice_list);
        mIvTopBack = (ImageView) findViewById(R.id.iv_top_back);
        mIvTopBack.setOnClickListener(v -> finish());
        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mTvTitle.setText("集团公告");
        mTvTitle.setOnClickListener(v -> finish());
        mIvTopSelect = (ImageView) findViewById(R.id.iv_top_select);
        mIvTopSelect.setVisibility(View.GONE);
        mBtnNewNotice = (Button) findViewById(R.id.btn_new_notice);
        mBtnNewNotice.setOnClickListener(v -> ARouter.getInstance().build("/home/BNoticeCreateActivity").navigation());
        mSrlNoticeList = (SwipeRefreshLayout) findViewById(R.id.srl_notice_list);
        mRecyNoticeList = (RecyclerView) findViewById(R.id.recy_notice_list);

        mPresenter.requestBNLimits();
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
    public void setAdapter(BlocNoticeAdapter adapter) {
        linearLayoutManager = new LinearLayoutManager(this);
        mRecyNoticeList.setLayoutManager(linearLayoutManager);
        mRecyNoticeList.setAdapter(adapter);
        mSrlNoticeList.setOnRefreshListener(this);
        initPaginate();
    }

    /**
     * 开始加载更多
     */
    @Override
    public void startLoadMore() {
        isLoadingMore = true;
    }

    /**
     * 结束加载更多
     */
    @Override
    public void endLoadMore(String forbidEdit) {
        isLoadingMore = false;
        if (!"1".equals(forbidEdit)){
            mBtnNewNotice.setVisibility(View.GONE);
        }
    }

    @Override
    public void showLoading() {
        Observable.just(1)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(integer -> mSrlNoticeList.setRefreshing(true));
    }

    @Override
    public void hideLoading() {
        mSrlNoticeList.setRefreshing(false);
    }

    @Override
    public void endLoad() {
        Log.e("mPaginate", "mPaginateMore");
        mPaginate.setHasMoreDataToLoad(false);
//        mPaginate.unbind();
    }

    @Override
    public void LoadList() {
        mPresenter.requestBNList(true);
    }

    /**
     * 初始化Paginate,用于加载更多
     */
    private void initPaginate() {
        if (mPaginate == null) {
            Paginate.Callbacks callbacks = new Paginate.Callbacks() {
                @Override
                public void onLoadMore() {
                    mPresenter.requestBNList(false);
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

            mPaginate = Paginate.with(mRecyNoticeList, callbacks)
                    .setLoadingTriggerThreshold(0)
                    .build();
            mPaginate.setHasMoreDataToLoad(false);
        }
    }

    @Override
    public void onRefresh() {
        mPresenter.requestBNList(true);
    }
}
