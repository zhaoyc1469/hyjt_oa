package com.hyjt.home.mvp.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.hyjt.frame.base.BaseActivity;
import com.hyjt.frame.di.component.AppComponent;
import com.hyjt.frame.utils.UiUtils;

import com.hyjt.home.di.component.DaggerPsonLoanListComponent;
import com.hyjt.home.di.module.PsonLoanListModule;
import com.hyjt.home.mvp.contract.PsonLoanListContract;
import com.hyjt.home.mvp.presenter.PsonLoanListPresenter;

import com.hyjt.home.R;
import com.hyjt.home.mvp.ui.adapter.LUAppealAdapter;
import com.hyjt.home.mvp.ui.adapter.PsonLoanAdapter;
import com.hyjt.home.mvp.ui.view.MeetingSelPop;
import com.hyjt.home.mvp.ui.view.PsonLoanSelPop;
import com.paginate.Paginate;


import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

import static com.hyjt.frame.utils.Preconditions.checkNotNull;

@Route(path = "/home/PsonLoanListActivity")
public class PsonLoanListActivity extends BaseActivity<PsonLoanListPresenter> implements PsonLoanListContract.View, SwipeRefreshLayout.OnRefreshListener {


    private android.widget.ImageView mIvTopBack;
    private android.widget.TextView mTvTitle;
    private android.widget.ImageView mIvTopSelect;
    private android.widget.Button mBtnNewPloan;
    private android.support.v4.widget.SwipeRefreshLayout mSrlPloanList;
    private android.support.v7.widget.RecyclerView mRecyPloanList;
    private Paginate mPaginate;
    private String CwPnum;
    private String Start;
    private String End;
    private boolean isLoadingMore;
    private PsonLoanSelPop psonLoanSelPop;

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerPsonLoanListComponent
                .builder()
                .appComponent(appComponent)
                .psonLoanListModule(new PsonLoanListModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(Bundle savedInstanceState) {
        return R.layout.home_activity_pson_loan_list;
    }

    @Override
    public void initData(Bundle savedInstanceState) {

        mIvTopBack = (ImageView) findViewById(R.id.iv_top_back);
        mIvTopBack.setOnClickListener(v -> finish());
        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mTvTitle.setText("个人借款列表");
        mTvTitle.setOnClickListener(v -> finish());
        mIvTopSelect = (ImageView) findViewById(R.id.iv_top_select);
        mIvTopSelect.setOnClickListener(v -> popSel());
        mBtnNewPloan = (Button) findViewById(R.id.btn_new_ploan);
        mSrlPloanList = (SwipeRefreshLayout) findViewById(R.id.srl_ploan_list);
        mRecyPloanList = (RecyclerView) findViewById(R.id.recy_ploan_list);

        mPresenter.getPsonLoanList(true, "", "", "");
    }

    private void popSel() {

        psonLoanSelPop = new PsonLoanSelPop(this);
        psonLoanSelPop.setSelCmListener((meetingNum, meetingName, meetingTime) ->
                mPresenter.getPsonLoanList(true, CwPnum, Start, End));
        psonLoanSelPop.showAtLocation(findViewById(R.id.rl_meeting_list),
                Gravity.CENTER | Gravity.CENTER_HORIZONTAL, 0, 0);
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
                .subscribe(integer -> mSrlPloanList.setRefreshing(true));
    }

    @Override
    public void hideLoading() {
        mSrlPloanList.setRefreshing(false);
    }

    @Override
    public void setAdapter(PsonLoanAdapter psonLoanAdapter) {
        mRecyPloanList.setLayoutManager(new LinearLayoutManager(this));
        mRecyPloanList.setAdapter(psonLoanAdapter);
        mSrlPloanList.setOnRefreshListener(this);
        initPaginate();
    }

    private void initPaginate() {
        if (mPaginate == null) {
            Paginate.Callbacks callbacks = new Paginate.Callbacks() {
                @Override
                public void onLoadMore() {
                    mPresenter.getPsonLoanList(false, CwPnum, Start, End);
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

            mPaginate = Paginate.with(mRecyPloanList, callbacks)
                    .setLoadingTriggerThreshold(0)
                    .build();
            mPaginate.setHasMoreDataToLoad(false);
        }
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
        mPresenter.getPsonLoanList(true, "", "", "");
    }
}
