package com.hyjt.home.mvp.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.hyjt.frame.base.BaseActivity;
import com.hyjt.frame.di.component.AppComponent;
import com.hyjt.frame.event.RefreshListEvent;
import com.hyjt.frame.utils.UiUtils;
import com.hyjt.home.R;
import com.hyjt.home.di.component.DaggerMeetingListComponent;
import com.hyjt.home.di.module.MeetingListModule;
import com.hyjt.home.mvp.contract.MeetingListContract;
import com.hyjt.home.mvp.presenter.MeetingListPresenter;
import com.hyjt.home.mvp.ui.adapter.MeetingAdapter;
import com.hyjt.home.mvp.ui.view.MeetingSelPop;
import com.paginate.Paginate;

import org.simple.eventbus.Subscriber;
import org.simple.eventbus.ThreadMode;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

import static com.hyjt.frame.utils.Preconditions.checkNotNull;


@Route(path = "/home/MeetingListActivity")
public class MeetingListActivity extends BaseActivity<MeetingListPresenter> implements MeetingListContract.View, View.OnClickListener,
        SwipeRefreshLayout.OnRefreshListener {


    private android.widget.ImageView mIvTopBack;
    private android.widget.TextView mTvTitle;
    private android.widget.ImageView mIvTopSelect;
    private android.widget.Button mBtnNewMeeting;
    private android.support.v4.widget.SwipeRefreshLayout mSrlMeetingList;
    private android.support.v7.widget.RecyclerView mRecyMeetingList;
    private String meetingBloc;
    private LinearLayoutManager linearLayoutManager;
    private Paginate mPaginate;
    private boolean isLoadingMore;
    private MeetingSelPop meetingSel;

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerMeetingListComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .meetingListModule(new MeetingListModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(Bundle savedInstanceState) {
        return R.layout.home_activity_meeting_list; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        meetingBloc = getIntent().getStringExtra("bloc");

        mIvTopBack = (ImageView) findViewById(R.id.iv_top_back);
        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mIvTopSelect = (ImageView) findViewById(R.id.iv_top_select);
        mBtnNewMeeting = (Button) findViewById(R.id.btn_new_meeting);
        mSrlMeetingList = (SwipeRefreshLayout) findViewById(R.id.srl_meeting_list);
        mRecyMeetingList = (RecyclerView) findViewById(R.id.recy_meeting_list);
        mIvTopBack.setOnClickListener(this);
        mIvTopSelect.setOnClickListener(this);
        mBtnNewMeeting.setOnClickListener(this);
        mTvTitle.setText("会议纪要");
        // 加载数据
        mPresenter.requestMeetingList(true, meetingBloc, "", "", "");
    }


    @Override
    public void onRefresh() {
        mPresenter.requestMeetingList(true, meetingBloc, "", "", "");
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
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.iv_top_back) {
            finish();
        } else if (i == R.id.tv_title) {
            finish();
        } else if (i == R.id.iv_top_select) {
            popSel();
        } else if (i == R.id.btn_new_meeting) {
            ARouter.getInstance().build("/home/MeetingLogActivity").navigation();
        }
    }

    private void popSel() {
        meetingSel = new MeetingSelPop(this);
        meetingSel.setSelCmListener((meetingNum, meetingName, meetingTime) ->
                mPresenter.requestMeetingList(true, meetingBloc, meetingNum, meetingName, meetingTime));
        meetingSel.showAtLocation(findViewById(R.id.rl_meeting_list),
                Gravity.CENTER | Gravity.CENTER_HORIZONTAL, 0, 0);
    }

    @Override
    public void setAdapter(MeetingAdapter adapter) {
        linearLayoutManager = new LinearLayoutManager(this);
        mRecyMeetingList.setLayoutManager(linearLayoutManager);
        mRecyMeetingList.setAdapter(adapter);
        mSrlMeetingList.setOnRefreshListener(this);
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
    public void endLoadMore() {
        isLoadingMore = false;
    }

    @Override
    public void showLoading() {
        Observable.just(1)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(integer -> mSrlMeetingList.setRefreshing(true));
    }

    @Override
    public void hideLoading() {
        mSrlMeetingList.setRefreshing(false);
    }

    @Override
    @Subscriber(tag = "Refresh_List", mode = ThreadMode.MAIN)
    public void refreshList(RefreshListEvent refreshListEvent) {
        onRefresh();
    }


    @Override
    public void endLoad() {
        Log.e("mPaginate", "mPaginateMore");
        mPaginate.setHasMoreDataToLoad(false);
//        mPaginate.unbind();
    }

    /**
     * 初始化Paginate,用于加载更多
     */
    private void initPaginate() {
        if (mPaginate == null) {
            Paginate.Callbacks callbacks = new Paginate.Callbacks() {
                @Override
                public void onLoadMore() {
                    mPresenter.requestMeetingList(false, meetingBloc, "", "", meetingBloc);
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

            mPaginate = Paginate.with(mRecyMeetingList, callbacks)
                    .setLoadingTriggerThreshold(0)
                    .build();
            mPaginate.setHasMoreDataToLoad(false);
        }
    }

}
