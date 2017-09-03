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
import com.hyjt.home.di.component.DaggerEmailListComponent;
import com.hyjt.home.di.module.EmailListModule;
import com.hyjt.home.mvp.contract.EmailListContract;
import com.hyjt.home.mvp.presenter.EmailListPresenter;
import com.hyjt.home.mvp.ui.adapter.EmailAdapter;
import com.paginate.Paginate;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

import static com.hyjt.frame.utils.Preconditions.checkNotNull;

@Route(path = "/home/EmailListActivity")
public class EmailListActivity extends BaseActivity<EmailListPresenter> implements EmailListContract.View, SwipeRefreshLayout.OnRefreshListener {

    private LinearLayoutManager linearLayoutManager;
    private Paginate mPaginate;
    private boolean isLoadingMore;
    private android.widget.RelativeLayout mRlEmailList;
    private android.widget.ImageView mIvTopBack;
    private android.widget.TextView mTvTitle;
    private android.widget.ImageView mIvTopSelect;
    private android.widget.Button mBtnNewEmail;
    private SwipeRefreshLayout mSrlEmailList;
    private android.support.v7.widget.RecyclerView mRecyEmailList;
    private String type;
    private Button mBtnAllType;
    private Button mBtnReadedType;
    private Button mBtnUnreadType;
    private android.widget.LinearLayout mLlEmailType;

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerEmailListComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .emailListModule(new EmailListModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(Bundle savedInstanceState) {
        return R.layout.home_activity_email_list; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        Intent intent = getIntent();
        type = intent.getStringExtra("type");

        mRlEmailList = (RelativeLayout) findViewById(R.id.rl_email_list);
        mIvTopBack = (ImageView) findViewById(R.id.iv_top_back);
        mIvTopBack.setOnClickListener(v -> finish());
        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mTvTitle.setOnClickListener(v -> finish());
        mTvTitle.setText("邮件列表");
        mIvTopSelect = (ImageView) findViewById(R.id.iv_top_select);
        mIvTopSelect.setVisibility(View.GONE);
        mBtnNewEmail = (Button) findViewById(R.id.btn_new_email);
        mSrlEmailList = (SwipeRefreshLayout) findViewById(R.id.srl_email_list);
        mRecyEmailList = (RecyclerView) findViewById(R.id.recy_email_list);

        if ("SendBox".equals(type)){
            mLlEmailType = (LinearLayout) findViewById(R.id.ll_email_type);
            mLlEmailType.setVisibility(View.GONE);
        }

        mBtnAllType = (Button) findViewById(R.id.btn_all_type);
        mBtnAllType.setOnClickListener(v -> {
            type = "ReceiveBox";
            mPresenter.getEmailList(true, type);
        });
        mBtnReadedType = (Button) findViewById(R.id.btn_readed_type);
        mBtnReadedType.setOnClickListener(v -> {
            type = "Opened";
            mPresenter.getEmailList(true, type);
        });
        mBtnUnreadType = (Button) findViewById(R.id.btn_unread_type);
        mBtnUnreadType.setOnClickListener(v -> {
            type = "UnRead";
            mPresenter.getEmailList(true, type);
        });
        mBtnNewEmail.setOnClickListener(v ->
                ARouter.getInstance().build("/home/EmailCreateActivity")
                .withString("Type", "Create")
                .navigation());

        mPresenter.getEmailList(true, type);
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
    public void setAdapter(EmailAdapter adapter) {
        linearLayoutManager = new LinearLayoutManager(this);
        mRecyEmailList.setLayoutManager(linearLayoutManager);
        mRecyEmailList.setAdapter(adapter);
        mSrlEmailList.setOnRefreshListener(this);
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
    public void showLoading() {
        Observable.just(1)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(integer -> mSrlEmailList.setRefreshing(true));
    }

    @Override
    public void hideLoading() {
        mSrlEmailList.setRefreshing(false);
    }

    /**
     * 初始化Paginate,用于加载更多
     */
    private void initPaginate() {
        if (mPaginate == null) {
            Paginate.Callbacks callbacks = new Paginate.Callbacks() {
                @Override
                public void onLoadMore() {
                    mPresenter.getEmailList(false, type);
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

            mPaginate = Paginate.with(mRecyEmailList, callbacks)
                    .setLoadingTriggerThreshold(0)
                    .build();
            mPaginate.setHasMoreDataToLoad(false);
        }
    }

    @Override
    public void onRefresh() {
        mPresenter.getEmailList(true, type);
    }

}
