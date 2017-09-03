package com.hyjt.home.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.hyjt.frame.base.BaseActivity;
import com.hyjt.frame.di.component.AppComponent;
import com.hyjt.frame.utils.UiUtils;
import com.hyjt.home.R;
import com.hyjt.home.di.component.DaggerStaffListComponent;
import com.hyjt.home.di.module.StaffListModule;
import com.hyjt.home.mvp.contract.StaffListContract;
import com.hyjt.home.mvp.model.entity.Reqs.StaffNameIdKey;
import com.hyjt.home.mvp.presenter.StaffListPresenter;
import com.hyjt.home.mvp.ui.adapter.StaffListAdapter;
import com.hyjt.home.mvp.ui.view.MeetingSelPop;
import com.hyjt.home.mvp.ui.view.StaffSelPop;
import com.hyjt.home.mvp.ui.view.treelistview.Node;
import com.paginate.Paginate;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

import static com.hyjt.frame.utils.Preconditions.checkNotNull;


@Route(path = "/home/StaffListActivity")
public class StaffListActivity extends BaseActivity<StaffListPresenter> implements StaffListContract.View, SwipeRefreshLayout.OnRefreshListener {


    private android.widget.RelativeLayout mRlStaffList;
    private android.widget.ImageView mIvTopBack;
    private android.widget.TextView mTvTitle;
    private android.widget.ImageView mIvTopSelect;
    private android.support.v4.widget.SwipeRefreshLayout mSrlStaffList;
    private android.support.v7.widget.RecyclerView mRecyStaffList;
    private LinearLayoutManager linearLayoutManager;
    private Button mBtnNewStaff;
    private Paginate mPaginate;
    private boolean isLoadingMore;
    private MeetingSelPop meetingSel;
    private String staffState;
    private StaffSelPop staffSelPop;
    private StaffNameIdKey dept = new StaffNameIdKey();
    private String endTime;
    private String startTime;
    private String education;
    private String deptId;
    private String name;

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerStaffListComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .staffListModule(new StaffListModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(Bundle savedInstanceState) {
        return R.layout.home_activity_staff_list; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        Intent intent = getIntent();
        staffState = intent.getStringExtra("state");

        mRlStaffList = (RelativeLayout) findViewById(R.id.rl_staff_list);
        mIvTopBack = (ImageView) findViewById(R.id.iv_top_back);
        mIvTopBack.setOnClickListener(v -> finish());
        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mTvTitle.setText("员工列表");
        mTvTitle.setOnClickListener(v -> finish());
        mIvTopSelect = (ImageView) findViewById(R.id.iv_top_select);
        mIvTopSelect.setOnClickListener(v -> mPresenter.requestDept());
        mBtnNewStaff = (Button) findViewById(R.id.btn_new_staff);
        mBtnNewStaff.setOnClickListener(v -> ARouter.getInstance().build("/home/CreateStaffActivity").navigation());
        mSrlStaffList = (SwipeRefreshLayout) findViewById(R.id.srl_staff_list);
        mRecyStaffList = (RecyclerView) findViewById(R.id.recy_staff_list);

        mPresenter.getStaffList(true, staffState, "", "", "", "", "");
    }


    @Override
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
        UiUtils.snackbarText(message);
        if ("202".equals(message))
            Toast.makeText(this, "您没有权限进行此操作", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void killMyself() {
        finish();
    }

    @Override
    public void showLoading() {
        Observable.just(1)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(integer -> mSrlStaffList.setRefreshing(true));
    }

    @Override
    public void hideLoading() {
        mSrlStaffList.setRefreshing(false);
    }

    @Override
    public void setAdapter(StaffListAdapter adapter) {
        linearLayoutManager = new LinearLayoutManager(this);
        mRecyStaffList.setLayoutManager(linearLayoutManager);
        mRecyStaffList.setAdapter(adapter);
        mSrlStaffList.setOnRefreshListener(this);
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
    public void showNoLimits() {
        new AlertDialog.Builder(this).setTitle("权限提示")//设置对话框标题
                .setMessage("您没有权限查看员工信息模块！")//设置显示的内容
                .setPositiveButton("确定", (dialog, which) -> finish()).show();//在按键响应事件中显示此对话框
    }

    @Override
    public void showDeptTree(List<Node> deptList) {
        staffSelPop = new StaffSelPop(this, deptList, dept, findViewById(R.id.rl_staff_list));
        staffSelPop.setSelStafListener((deptId, name, education, startTime, endTime) -> {
            this.deptId = deptId;
            this.name = name;
            this.education = education;
            this.startTime = startTime;
            this.endTime = endTime;

            mPresenter.getStaffList(true, staffState, deptId, name, education, startTime, endTime);
        });
        staffSelPop.showAtLocation(findViewById(R.id.rl_staff_list),
                Gravity.CENTER | Gravity.CENTER_HORIZONTAL, 0, 0);
    }

    /**
     * 初始化Paginate,用于加载更多
     */
    private void initPaginate() {
        if (mPaginate == null) {
            Paginate.Callbacks callbacks = new Paginate.Callbacks() {
                @Override
                public void onLoadMore() {
                    mPresenter.getStaffList(false, staffState, deptId, name, education, startTime, endTime);
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

            mPaginate = Paginate.with(mRecyStaffList, callbacks)
                    .setLoadingTriggerThreshold(0)
                    .build();
            mPaginate.setHasMoreDataToLoad(false);
        }
    }

    @Override
    public void onRefresh() {
        this.deptId = "";
        this.name = "";
        this.education = "";
        this.startTime = "";
        this.endTime = "";
        mPresenter.getStaffList(true, staffState, "", "", "", "", "");
    }

}

