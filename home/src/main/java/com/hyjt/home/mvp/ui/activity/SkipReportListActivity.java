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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.hyjt.frame.base.BaseActivity;
import com.hyjt.frame.di.component.AppComponent;
import com.hyjt.frame.utils.UiUtils;
import com.hyjt.home.R;
import com.hyjt.home.di.component.DaggerSkipReportListComponent;
import com.hyjt.home.di.module.SkipReportListModule;
import com.hyjt.home.mvp.contract.SkipReportListContract;
import com.hyjt.home.mvp.presenter.SkipReportListPresenter;
import com.hyjt.home.mvp.ui.adapter.SkipReportAdapter;
import com.paginate.Paginate;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

import static com.hyjt.frame.utils.Preconditions.checkNotNull;

@Route(path = "/home/SkipReportListActivity")
public class SkipReportListActivity extends BaseActivity<SkipReportListPresenter> implements SkipReportListContract.View, SwipeRefreshLayout.OnRefreshListener {


    private android.widget.RelativeLayout rlReportSkipList;
    private android.widget.ImageView ivTopBack;
    private android.widget.TextView tvTitle;
    private android.widget.ImageView ivTopSelect;
    private android.widget.LinearLayout llReportType;
    private android.widget.Button btnWaitType;
    private android.widget.Button btnReadedType;
    private android.widget.Button btnNewReport;
    private android.support.v4.widget.SwipeRefreshLayout srlReportList;
    private android.support.v7.widget.RecyclerView recyReportList;
    private LinearLayoutManager linearLayoutManager;
    private Paginate mPaginate;
    private String type;
    private boolean isLoadingMore;

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerSkipReportListComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .skipReportListModule(new SkipReportListModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(Bundle savedInstanceState) {
        return R.layout.home_activity_skip_report_list; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        Intent intent = getIntent();
        type = intent.getStringExtra("type");

        rlReportSkipList = (RelativeLayout) findViewById(R.id.rl_report_skip_list);
        ivTopBack = (ImageView) findViewById(R.id.iv_top_back);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        ivTopSelect = (ImageView) findViewById(R.id.iv_top_select);
        llReportType = (LinearLayout) findViewById(R.id.ll_report_type);
        btnWaitType = (Button) findViewById(R.id.btn_wait_type);
        btnReadedType = (Button) findViewById(R.id.btn_readed_type);
        btnNewReport = (Button) findViewById(R.id.btn_new_report);
        srlReportList = (SwipeRefreshLayout) findViewById(R.id.srl_report_list);
        recyReportList = (RecyclerView) findViewById(R.id.recy_report_list);

        btnNewReport.setOnClickListener(v -> ARouter.getInstance().build("/home/SkipReportCreateActivity")
                .navigation());

        btnWaitType.setOnClickListener(v -> {
            this.type = "UnRead";
            mPresenter.getReportList(true, "UnRead");
        });

        btnReadedType.setOnClickListener(v -> {
            this.type = "Opened";
            mPresenter.getReportList(true, "Opened");
        });

        if ("Mine".equals(type)){
            llReportType.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.getReportList(true, type);
    }

    @Override
    public void showLoading() {
        Observable.just(1)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(integer -> srlReportList.setRefreshing(true));
    }

    @Override
    public void hideLoading() {
        srlReportList.setRefreshing(false);
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
    public void setAdapter(SkipReportAdapter skipReportAdapter) {

        linearLayoutManager = new LinearLayoutManager(this);
        recyReportList.setLayoutManager(linearLayoutManager);
        recyReportList.setAdapter(skipReportAdapter);
        srlReportList.setOnRefreshListener(this);
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
        mPresenter.getReportList(true, type);
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

            mPaginate = Paginate.with(recyReportList, callbacks)
                    .setLoadingTriggerThreshold(0)
                    .build();
            mPaginate.setHasMoreDataToLoad(false);
        }
    }




}
