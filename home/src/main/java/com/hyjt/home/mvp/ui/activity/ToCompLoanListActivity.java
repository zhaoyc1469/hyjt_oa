package com.hyjt.home.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
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
import com.hyjt.frame.event.RefreshListEvent;
import com.hyjt.frame.utils.UiUtils;
import com.hyjt.home.di.component.DaggerToCompLoanListComponent;
import com.hyjt.home.di.module.ToCompLoanListModule;
import com.hyjt.home.mvp.contract.ToCompLoanListContract;
import com.hyjt.home.mvp.presenter.ToCompLoanListPresenter;

import com.hyjt.home.R;
import com.hyjt.home.mvp.ui.adapter.CompLoanAdapter;
import com.hyjt.home.mvp.ui.view.CompLoanSelPop;
import com.hyjt.home.mvp.ui.view.PsonLoanSelPop;
import com.paginate.Paginate;

import org.simple.eventbus.Subscriber;
import org.simple.eventbus.ThreadMode;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

import static com.hyjt.frame.utils.Preconditions.checkNotNull;

@Route(path = "/home/ToCompLoanListActivity")
public class ToCompLoanListActivity extends BaseActivity<ToCompLoanListPresenter> implements ToCompLoanListContract.View, SwipeRefreshLayout.OnRefreshListener {


    private android.widget.RelativeLayout mRlComploan;
    private android.widget.ImageView mIvTopBack;
    private android.widget.TextView mTvTitle;
    private android.widget.ImageView mIvTopSelect;
    private android.widget.LinearLayout mLlMode;
    private android.widget.Button mBtnWaitType;
    private android.widget.Button mBtnReadedType;
    private android.widget.Button mBtnNewCloan;
    private android.support.v4.widget.SwipeRefreshLayout mSrlCloanList;
    private android.support.v7.widget.RecyclerView mRecyCloanList;
    private String Type;
    private String Mode;
    private CompLoanSelPop compLoanSelPop;
    private String CwCpersonal;
    private String CwCcompany;
    private String CwCdepartment;
    private String CwCnum;
    private String Start;
    private String End;
    private boolean isLoadingMore;
    private Paginate mPaginate;

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerToCompLoanListComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .toCompLoanListModule(new ToCompLoanListModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(Bundle savedInstanceState) {
        return R.layout.home_activity_to_comp_loan_list; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        Intent intent = getIntent();
        Type = intent.getStringExtra("type");
        Mode = "0";

        mRlComploan = (RelativeLayout) findViewById(R.id.rl_comploan);
        mIvTopBack = (ImageView) findViewById(R.id.iv_top_back);
        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mIvTopSelect = (ImageView) findViewById(R.id.iv_top_select);
        mIvTopBack.setOnClickListener(v -> finish());
        mTvTitle.setText("对公借款列表");
        mTvTitle.setOnClickListener(v -> finish());
        mIvTopSelect.setOnClickListener(v -> popSel());
        mLlMode = (LinearLayout) findViewById(R.id.ll_mode);
        mBtnWaitType = (Button) findViewById(R.id.btn_wait_type);
        mBtnReadedType = (Button) findViewById(R.id.btn_readed_type);
        mBtnNewCloan = (Button) findViewById(R.id.btn_new_cloan);
        mSrlCloanList = (SwipeRefreshLayout) findViewById(R.id.srl_cloan_list);
        mRecyCloanList = (RecyclerView) findViewById(R.id.recy_cloan_list);
        mBtnNewCloan.setOnClickListener(v -> ARouter.getInstance()
                .build("/home/ToCompLoanCreateActivity").navigation());

        mBtnWaitType.setOnClickListener(v -> {
            Mode = "0";
            mPresenter.getCompLoanList(true, Type, Mode, CwCpersonal,
                    CwCcompany, CwCdepartment, CwCnum, Start, End);
        });
        mBtnReadedType.setOnClickListener(v -> {
            Mode = "1";
            mPresenter.getCompLoanList(true, Type, Mode, CwCpersonal,
                    CwCcompany, CwCdepartment, CwCnum, Start, End);
        });
        switch (Type) {
            case "1":
                mLlMode.setVisibility(View.GONE);
                break;
            case "2":
                mBtnWaitType.setText("未审批");
                mBtnReadedType.setText("已审批");
                mBtnNewCloan.setVisibility(View.GONE);
                break;
            case "3":
                mBtnWaitType.setText("待确认");
                mBtnReadedType.setText("全部");
                mBtnNewCloan.setVisibility(View.GONE);
                break;
        }
        mPresenter.getCompLoanList(true, Type, Mode, "", "", "", "", "", "");

    }


    private void popSel() {
        compLoanSelPop = new CompLoanSelPop(this, Type);
        compLoanSelPop.setSelCmListener((personal, company, department, num, start, end) -> {
            CwCpersonal = personal;
            CwCcompany = company;
            CwCdepartment = department;
            CwCnum = num;
            Start = start;
            End = end;
            mPresenter.getCompLoanList(true, Type, Mode, CwCpersonal,
                    CwCcompany, CwCdepartment, CwCnum, Start, End);

        });
        compLoanSelPop.showAtLocation(findViewById(R.id.rl_comploan),
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
                .subscribe(integer -> mSrlCloanList.setRefreshing(true));
    }

    @Override
    public void hideLoading() {
        mSrlCloanList.setRefreshing(false);
    }

    @Override
    @Subscriber(tag = "Refresh_List", mode = ThreadMode.MAIN)
    public void refreshList(RefreshListEvent refreshListEvent) {
        onRefresh();
    }

    @Override
    public void setAdapter(CompLoanAdapter CompLoanAdapter) {
        mRecyCloanList.setLayoutManager(new LinearLayoutManager(this));
        mRecyCloanList.setAdapter(CompLoanAdapter);
        mSrlCloanList.setOnRefreshListener(this);
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
                    mPresenter.getCompLoanList(false, Type, Mode, CwCpersonal,
                            CwCcompany, CwCdepartment, CwCnum, Start, End);
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

            mPaginate = Paginate.with(mRecyCloanList, callbacks)
                    .setLoadingTriggerThreshold(0)
                    .build();
            mPaginate.setHasMoreDataToLoad(false);
        }
    }

    @Override
    public void onRefresh() {
        mPresenter.getCompLoanList(true, Type, Mode, "", "", "", "", "", "");
    }
}
