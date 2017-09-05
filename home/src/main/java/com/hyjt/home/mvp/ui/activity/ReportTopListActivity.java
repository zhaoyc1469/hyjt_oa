package com.hyjt.home.mvp.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.hyjt.frame.base.BaseActivity;
import com.hyjt.frame.di.component.AppComponent;
import com.hyjt.frame.utils.UiUtils;
import com.hyjt.home.R;
import com.hyjt.home.di.component.DaggerReportTopListComponent;
import com.hyjt.home.di.module.ReportTopListModule;
import com.hyjt.home.mvp.contract.ReportTopListContract;
import com.hyjt.home.mvp.presenter.ReportTopListPresenter;
import com.hyjt.home.mvp.ui.adapter.ReportTopAdapter;
import com.paginate.Paginate;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

import static com.hyjt.frame.utils.Preconditions.checkNotNull;

@Route(path = "/home/ReportTopListActivity")
public class ReportTopListActivity extends BaseActivity<ReportTopListPresenter> implements ReportTopListContract.View, SwipeRefreshLayout.OnRefreshListener {


    private android.widget.RelativeLayout mRlReportTopList;
    private android.widget.ImageView mIvTopBack;
    private android.widget.TextView mTvTitle;
    private android.widget.ImageView mIvTopSelect;
    private android.widget.LinearLayout mLlReportType;
    private android.widget.Button mBtnAllType;
    private android.widget.Button mBtnReadedType;
    private android.widget.Button mBtnNewReport;
    private android.support.v4.widget.SwipeRefreshLayout mSrlReportList;
    private android.support.v7.widget.RecyclerView mRecyReportList;
    private LinearLayoutManager linearLayoutManager;
    private String type;
    private Paginate mPaginate;
    private boolean isLoadingMore;


    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerReportTopListComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .reportTopListModule(new ReportTopListModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(Bundle savedInstanceState) {
        return R.layout.home_activity_report_top_list; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        mRlReportTopList = (RelativeLayout) findViewById(R.id.rl_report_top_list);
        mIvTopBack = (ImageView) findViewById(R.id.iv_top_back);
        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mIvTopSelect = (ImageView) findViewById(R.id.iv_top_select);
        mLlReportType = (LinearLayout) findViewById(R.id.ll_report_type);
        mBtnAllType = (Button) findViewById(R.id.btn_all_type);
        mBtnReadedType = (Button) findViewById(R.id.btn_readed_type);
        mBtnNewReport = (Button) findViewById(R.id.btn_new_report);
        mSrlReportList = (SwipeRefreshLayout) findViewById(R.id.srl_report_list);
        mRecyReportList = (RecyclerView) findViewById(R.id.recy_report_list);

        mBtnNewReport.setOnClickListener(v -> ARouter.getInstance().build("/home/ReportTopCreateActivity")
                .navigation());


        mPresenter.getReportList(true, "Mine");
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
    public void showLoading() {
        Observable.just(1)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(integer -> mSrlReportList.setRefreshing(true));
    }

    @Override
    public void hideLoading() {
        mSrlReportList.setRefreshing(false);
    }

    @Override
    public void setAdapter(ReportTopAdapter adapter) {
        linearLayoutManager = new LinearLayoutManager(this);
        mRecyReportList.setLayoutManager(linearLayoutManager);
        mRecyReportList.setAdapter(adapter);
        mSrlReportList.setOnRefreshListener(this);
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
                    mPresenter.getReportList(false, type);
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

            mPaginate = Paginate.with(mRecyReportList, callbacks)
                    .setLoadingTriggerThreshold(0)
                    .build();
            mPaginate.setHasMoreDataToLoad(false);
        }
    }

    @Override
    public void onRefresh() {
        mPresenter.getReportList(true, type);
    }
}
