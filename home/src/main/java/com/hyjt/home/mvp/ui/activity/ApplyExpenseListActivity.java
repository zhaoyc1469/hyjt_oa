package com.hyjt.home.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.hyjt.frame.base.BaseActivity;
import com.hyjt.frame.di.component.AppComponent;
import com.hyjt.frame.utils.UiUtils;

import com.hyjt.home.di.component.DaggerApplyExpenseListComponent;
import com.hyjt.home.di.module.ApplyExpenseListModule;
import com.hyjt.home.mvp.contract.ApplyExpenseListContract;
import com.hyjt.home.mvp.presenter.ApplyExpenseListPresenter;

import com.hyjt.home.R;
import com.hyjt.home.mvp.ui.adapter.ApplyExpenseAdapter;
import com.hyjt.home.mvp.ui.view.ApplyExpSelPop;
import com.hyjt.home.mvp.ui.view.CompLoanSelPop;
import com.paginate.Paginate;


import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

import static com.hyjt.frame.utils.Preconditions.checkNotNull;

@Route(path = "/home/ApplyExpenseListActivity")
public class ApplyExpenseListActivity extends BaseActivity<ApplyExpenseListPresenter> implements ApplyExpenseListContract.View, SwipeRefreshLayout.OnRefreshListener {


    private android.widget.RelativeLayout mRlApplyExpenseList;
    private android.widget.ImageView mIvTopBack;
    private android.widget.TextView mTvTitle;
    private android.widget.ImageView mIvTopSelect;
    private android.widget.LinearLayout mLlMode;
    private android.widget.Button mBtnWaitType;
    private android.widget.Button mBtnReadedType;
    private android.widget.Button mBtnNewAexpense;
    private android.support.v4.widget.SwipeRefreshLayout mSrlAexpenseList;
    private android.support.v7.widget.RecyclerView mRecyAexpenseList;
    private String Type;
    private String Mode;
    private Paginate mPaginate;
    private boolean isLoadingMore;
    private ApplyExpSelPop applyExpSelPop;
    private String CwEpersonal;
    private String CwEcompany;
    private String CwEdepartment;
    private String CwEnum;
    private String Start;
    private String End;

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerApplyExpenseListComponent
                .builder()
                .appComponent(appComponent)
                .applyExpenseListModule(new ApplyExpenseListModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(Bundle savedInstanceState) {
        return R.layout.home_activity_apply_expense_list;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        Intent intent = getIntent();
        Type = intent.getStringExtra("type");
        Mode = Type.equals("3") ? "0" : "1";
        initView();

        mPresenter.getApplyExpList(true, Type, Mode, "", "", "", "", "", "");
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


    private void initView() {
        mRlApplyExpenseList = (RelativeLayout) findViewById(R.id.rl_apply_expense_list);
        mIvTopBack = (ImageView) findViewById(R.id.iv_top_back);
        mIvTopBack.setOnClickListener(v -> finish());
        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mTvTitle.setText("费用报销列表");
        mTvTitle.setOnClickListener(v -> finish());
        mIvTopSelect = (ImageView) findViewById(R.id.iv_top_select);
        mIvTopSelect.setOnClickListener(v -> popSel());
        mLlMode = (LinearLayout) findViewById(R.id.ll_mode);
        mBtnWaitType = (Button) findViewById(R.id.btn_wait_type);
        mBtnReadedType = (Button) findViewById(R.id.btn_readed_type);
        mBtnNewAexpense = (Button) findViewById(R.id.btn_new_aexpense);
        mSrlAexpenseList = (SwipeRefreshLayout) findViewById(R.id.srl_aexpense_list);
        mRecyAexpenseList = (RecyclerView) findViewById(R.id.recy_aexpense_list);
    }


    private void popSel() {
        applyExpSelPop = new ApplyExpSelPop(this, Type);
        applyExpSelPop.setSelCmListener((personal, company, department, num, start, end) -> {
            CwEpersonal = personal;
            CwEcompany = company;
            CwEdepartment = department;
            CwEnum = num;
            Start = start;
            End = end;
            mPresenter.getApplyExpList(false, Type, Mode, CwEpersonal,
                    CwEcompany, CwEdepartment, CwEnum, Start, End);

        });
        applyExpSelPop.showAtLocation(findViewById(R.id.rl_comploan),
                Gravity.CENTER | Gravity.CENTER_HORIZONTAL, 0, 0);
    }

    @Override
    public void showLoading() {
        Observable.just(1)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(integer -> mSrlAexpenseList.setRefreshing(true));
    }

    @Override
    public void hideLoading() {
        mSrlAexpenseList.setRefreshing(false);
    }

    @Override
    public void setAdapter(ApplyExpenseAdapter mAdapter) {
        mRecyAexpenseList.setLayoutManager(new LinearLayoutManager(this));
        mRecyAexpenseList.setAdapter(mAdapter);
        mSrlAexpenseList.setOnRefreshListener(this);
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
    public void showNoLimits() {
        new AlertDialog.Builder(this).setTitle("权限提示")//设置对话框标题
                .setMessage("您没有权限查看出纳信息模块！")//设置显示的内容
                .setPositiveButton("确定", (dialog, which) -> finish()).show();//在按键响应事件中显示此对话框

    }

    private void initPaginate() {
        if (mPaginate == null) {
            Paginate.Callbacks callbacks = new Paginate.Callbacks() {
                @Override
                public void onLoadMore() {
                    mPresenter.getApplyExpList(false, Type, Mode, CwEpersonal,
                            CwEcompany, CwEdepartment, CwEnum, Start, End);
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

            mPaginate = Paginate.with(mRecyAexpenseList, callbacks)
                    .setLoadingTriggerThreshold(0)
                    .build();
            mPaginate.setHasMoreDataToLoad(false);
        }
    }

    @Override
    public void onRefresh() {
        CwEpersonal = "";
        CwEcompany = "";
        CwEdepartment = "";
        CwEnum = "";
        Start = "";
        End = "";
        mPresenter.getApplyExpList(true, Type, Mode, CwEpersonal,
                CwEcompany, CwEdepartment, CwEnum, Start, End);
    }
}
