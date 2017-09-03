package com.hyjt.home.mvp.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.hyjt.frame.base.BaseActivity;
import com.hyjt.frame.di.component.AppComponent;
import com.hyjt.frame.utils.UiUtils;
import com.hyjt.home.R;
import com.hyjt.home.di.component.DaggerWaitSolveListComponent;
import com.hyjt.home.di.module.WaitSolveListModule;
import com.hyjt.home.mvp.contract.WaitSolveListContract;
import com.hyjt.home.mvp.presenter.WaitSolveListPresenter;
import com.hyjt.home.mvp.ui.adapter.MeetingAdapter;
import com.hyjt.home.mvp.ui.view.MeetingSelPop;
import com.paginate.Paginate;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

import static com.hyjt.frame.utils.Preconditions.checkNotNull;

@Route(path = "/home/WaitSolveListActivity")
public class WaitSolveListActivity extends BaseActivity<WaitSolveListPresenter> implements WaitSolveListContract.View, SwipeRefreshLayout.OnRefreshListener {


    private android.widget.RelativeLayout mRlWSolveList;
    private android.widget.ImageView mIvTopBack;
    private android.widget.TextView mTvTitle;
    private android.support.v4.widget.SwipeRefreshLayout mSrlWSolveList;
    private android.support.v7.widget.RecyclerView mRecyWSolveList;
    private LinearLayoutManager llManager;
    private Paginate mPaginate;
    private boolean isLoadingMore;
    private MeetingSelPop meetingSel;
    private ImageView mIvTopSelect;

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerWaitSolveListComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .waitSolveListModule(new WaitSolveListModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(Bundle savedInstanceState) {
        return R.layout.home_activity_wait_solve_list; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(Bundle savedInstanceState) {

        mRlWSolveList = (RelativeLayout) findViewById(R.id.rl_w_solve_list);
        mIvTopBack = (ImageView) findViewById(R.id.iv_top_back);
        mIvTopBack.setOnClickListener(v -> finish());
        mIvTopSelect = (ImageView) findViewById(R.id.iv_top_select);
        mIvTopSelect.setOnClickListener(v -> popSel());
        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mTvTitle.setText("待解决任务");
        mTvTitle.setOnClickListener(v -> finish());
        mSrlWSolveList = (SwipeRefreshLayout) findViewById(R.id.srl_w_solve_list);
        mRecyWSolveList = (RecyclerView) findViewById(R.id.recy_w_solve_list);

        mPresenter.requestMeetingList(true, "", "", "");
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

    private void popSel() {
        meetingSel = new MeetingSelPop(this);
        meetingSel.setSelCmListener((meetingNum, meetingName, meetingTime) ->
                mPresenter.requestMeetingList(true, meetingNum, meetingName, meetingTime));
        meetingSel.showAtLocation(findViewById(R.id.rl_w_solve_list),
                Gravity.CENTER | Gravity.CENTER_HORIZONTAL, 0, 0);
    }

    @Override
    public void setAdapter(MeetingAdapter adapter) {
        llManager = new LinearLayoutManager(this);
        mRecyWSolveList.setLayoutManager(llManager);
        mRecyWSolveList.setAdapter(adapter);
        mSrlWSolveList.setOnRefreshListener(this);
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
        Log.e("mPaginate", "mPaginateMore");
        mPaginate.setHasMoreDataToLoad(false);
    }

    @Override
    public void showLoading() {
        Observable.just(1)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(integer -> mSrlWSolveList.setRefreshing(true));
    }

    @Override
    public void hideLoading() {
        mSrlWSolveList.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        mPresenter.requestMeetingList(true, "", "", "");
    }

    /**
     * 初始化Paginate,用于加载更多
     */
    private void initPaginate() {
        if (mPaginate == null) {
            Paginate.Callbacks callbacks = new Paginate.Callbacks() {
                @Override
                public void onLoadMore() {
                    mPresenter.requestMeetingList(false, "", "", "");
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

            mPaginate = Paginate.with(mRecyWSolveList, callbacks)
                    .setLoadingTriggerThreshold(0)
                    .build();
            mPaginate.setHasMoreDataToLoad(false);
        }
    }
}
