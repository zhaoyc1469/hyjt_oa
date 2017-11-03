package com.hyjt.home.mvp.ui.activity;

import android.os.Bundle;
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
import com.hyjt.frame.event.RefreshListEvent;
import com.hyjt.frame.utils.UiUtils;
import com.hyjt.home.R;
import com.hyjt.home.di.component.DaggerSuperviseSolveListComponent;
import com.hyjt.home.di.module.SuperviseSolveListModule;
import com.hyjt.home.mvp.contract.SuperviseSolveListContract;
import com.hyjt.home.mvp.presenter.SuperviseSolveListPresenter;
import com.hyjt.home.mvp.ui.adapter.MeetingAdapter;
import com.hyjt.home.mvp.ui.view.MeetingSelPop;
import com.paginate.Paginate;

import org.simple.eventbus.Subscriber;
import org.simple.eventbus.ThreadMode;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

import static com.hyjt.frame.utils.Preconditions.checkNotNull;


@Route(path = "/home/SuperviseSolveListActivity")
public class SuperviseSolveListActivity extends BaseActivity<SuperviseSolveListPresenter> implements SuperviseSolveListContract.View, SwipeRefreshLayout.OnRefreshListener {

    private android.widget.RelativeLayout mRlSSolveList;
    private android.widget.ImageView mIvTopBack;
    private android.widget.TextView mTvTitle;
    private android.support.v4.widget.SwipeRefreshLayout mSrlSSolveList;
    private android.support.v7.widget.RecyclerView mRecySSolveList;
    private LinearLayoutManager llManager;
    private Paginate mPaginate;
    private boolean isLoadingMore;
    private MeetingSelPop meetingSel;
    private ImageView mIvTopSelect;

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerSuperviseSolveListComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .superviseSolveListModule(new SuperviseSolveListModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(Bundle savedInstanceState) {
        return R.layout.home_activity_s_solve_list; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(Bundle savedInstanceState) {

        mRlSSolveList = (RelativeLayout) findViewById(R.id.rl_s_solve_list);
        mIvTopBack = (ImageView) findViewById(R.id.iv_top_back);
        mIvTopBack.setOnClickListener(v -> finish());
        mIvTopSelect = (ImageView) findViewById(R.id.iv_top_select);
        mIvTopSelect.setOnClickListener(v -> popSel());
        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mTvTitle.setText("督办任务");
        mTvTitle.setOnClickListener(v -> finish());
        mSrlSSolveList = (SwipeRefreshLayout) findViewById(R.id.srl_s_solve_list);
        mRecySSolveList = (RecyclerView) findViewById(R.id.recy_s_solve_list);

        mPresenter.requestMeetingList(true, "", "", "");
    }

    @Override
    public void showMessage(String message) {
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
        meetingSel.showAtLocation(findViewById(R.id.rl_s_solve_list),
                Gravity.CENTER | Gravity.CENTER_HORIZONTAL, 0, 0);
    }

    @Override
    public void setAdapter(MeetingAdapter adapter) {
        llManager = new LinearLayoutManager(this);
        mRecySSolveList.setLayoutManager(llManager);
        mRecySSolveList.setAdapter(adapter);
        mSrlSSolveList.setOnRefreshListener(this);
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
                .subscribe(integer -> mSrlSSolveList.setRefreshing(true));
    }

    @Override
    public void hideLoading() {
        mSrlSSolveList.setRefreshing(false);
    }

    @Override
    @Subscriber(tag = "Refresh_List", mode = ThreadMode.MAIN)
    public void refreshList(RefreshListEvent refreshListEvent) {
        onRefresh();
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

            mPaginate = Paginate.with(mRecySSolveList, callbacks)
                    .setLoadingTriggerThreshold(0)
                    .build();
            mPaginate.setHasMoreDataToLoad(false);
        }
    }
}
