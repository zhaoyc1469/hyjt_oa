package com.hyjt.home.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.hyjt.home.di.component.DaggerAddressBookComponent;
import com.hyjt.home.di.module.AddressBookModule;
import com.hyjt.home.mvp.contract.AddressBookContract;
import com.hyjt.home.mvp.model.entity.Reqs.StaffNameIdKey;
import com.hyjt.home.mvp.presenter.AddressBookPresenter;
import com.hyjt.home.mvp.ui.adapter.StaffListAdapter;
import com.hyjt.home.mvp.ui.view.AddressBookSelPop;
import com.hyjt.home.mvp.ui.view.StaffMsgPop;
import com.hyjt.home.mvp.ui.view.treelistview.Node;
import com.paginate.Paginate;

import org.simple.eventbus.Subscriber;
import org.simple.eventbus.ThreadMode;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

import static com.hyjt.frame.utils.Preconditions.checkNotNull;

@Route(path = "/home/AddressBookActivity")
public class AddressBookActivity extends BaseActivity<AddressBookPresenter> implements AddressBookContract.View, SwipeRefreshLayout.OnRefreshListener {


    private android.widget.RelativeLayout mRlAddressBook;
    private android.widget.ImageView mIvTopBack;
    private android.widget.TextView mTvTitle;
    private android.widget.ImageView mIvTopSelect;
    private android.support.v4.widget.SwipeRefreshLayout mSrlAddressBook;
    private android.support.v7.widget.RecyclerView mRecyAddressBook;
    private String staffState;
    private StaffNameIdKey dept = new StaffNameIdKey();
    private Paginate mPaginate;
    private boolean isLoadingMore;
    private LinearLayoutManager linearLayoutManager;
//    private String endTime;
//    private String startTime;
//    private String education;
    private String deptId;
    private String name;
    private AddressBookSelPop addressBookSelPop;


    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerAddressBookComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .addressBookModule(new AddressBookModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(Bundle savedInstanceState) {
        return R.layout.home_activity_address_book; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        Intent intent = getIntent();
        staffState = intent.getStringExtra("state");

        mRlAddressBook = (RelativeLayout) findViewById(R.id.rl_address_book);
        mIvTopBack = (ImageView) findViewById(R.id.iv_top_back);
        mIvTopBack.setOnClickListener(v -> finish());
        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mTvTitle.setText("通讯录");
        mTvTitle.setOnClickListener(v -> finish());
        mIvTopSelect = (ImageView) findViewById(R.id.iv_top_select);
        mIvTopSelect.setOnClickListener(v -> mPresenter.requestDept());
        mSrlAddressBook = (SwipeRefreshLayout) findViewById(R.id.srl_address_book);
        mRecyAddressBook = (RecyclerView) findViewById(R.id.recy_address_book);

        mPresenter.getAddressBook(true, staffState, "", "", "", "", "");
    }


    @Override
    public void showLoading() {
        Observable.just(1)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(integer -> mSrlAddressBook.setRefreshing(true));
    }

    @Override
    public void hideLoading() {
        mSrlAddressBook.setRefreshing(false);
    }

    @Override
    @Subscriber(tag = "Refresh_List", mode = ThreadMode.MAIN)
    public void refreshList(RefreshListEvent refreshListEvent) {
        onRefresh();
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
    public void setAdapter(StaffListAdapter adapter) {
        linearLayoutManager = new LinearLayoutManager(this);
        mRecyAddressBook.setLayoutManager(linearLayoutManager);
        mRecyAddressBook.setAdapter(adapter);
        mSrlAddressBook.setOnRefreshListener(this);
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
                    mPresenter.getAddressBook(false, staffState, deptId, name, "", "", "");
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

            mPaginate = Paginate.with(mRecyAddressBook, callbacks)
                    .setLoadingTriggerThreshold(0)
                    .build();
            mPaginate.setHasMoreDataToLoad(false);
        }
    }

    @Override
    public void onRefresh() {
        this.deptId = "";
        this.name = "";
//        this.education = "";
//        this.startTime = "";
//        this.endTime = "";
        mPresenter.getAddressBook(true, staffState, "", "", "", "", "");
    }

    @Override
    public void showDeptTree(List<Node> deptList) {
        addressBookSelPop = new AddressBookSelPop(this, deptList, dept, findViewById(R.id.rl_address_book));
        addressBookSelPop.setSelStafListener((deptId, name) -> {
            this.deptId = deptId;
            this.name = name;

            mPresenter.getAddressBook(true, staffState, deptId, name, "", "", "");
        });
        addressBookSelPop.showAtLocation(findViewById(R.id.rl_address_book),
                Gravity.CENTER | Gravity.CENTER_HORIZONTAL, 0, 0);
    }

    @Override
    public void addressPop(List<String> cell) {
        StaffMsgPop staffMsgPop = new StaffMsgPop(this, cell);

        staffMsgPop.showAtLocation(findViewById(R.id.rl_address_book),
                Gravity.CENTER | Gravity.CENTER_HORIZONTAL, 0, 0);
    }
}
