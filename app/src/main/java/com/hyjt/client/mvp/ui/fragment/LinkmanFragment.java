package com.hyjt.client.mvp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hyjt.app.R;
import com.hyjt.client.di.component.DaggerLinkmanComponent;
import com.hyjt.client.di.module.LinkmanModule;
import com.hyjt.client.mvp.contract.LinkmanContract;
import com.hyjt.client.mvp.presenter.LinkmanPresenter;
import com.hyjt.frame.base.BaseFragment;
import com.hyjt.frame.di.component.AppComponent;
import com.hyjt.frame.utils.UiUtils;
import com.hyjt.home.mvp.model.entity.Reqs.StaffNameIdKey;
import com.hyjt.home.mvp.ui.adapter.StaffListAdapter;
import com.hyjt.home.mvp.ui.view.AddressBookSelPop;
import com.hyjt.home.mvp.ui.view.StaffMsgPop;
import com.hyjt.home.mvp.ui.view.treelistview.Node;
import com.paginate.Paginate;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

import static com.hyjt.frame.utils.Preconditions.checkNotNull;


public class LinkmanFragment extends BaseFragment<LinkmanPresenter> implements LinkmanContract.View, SwipeRefreshLayout.OnRefreshListener {

    private TextView mTvTitle;
    private ImageView mIvTopSelect;
    private android.support.v4.widget.SwipeRefreshLayout mSrlAddressBook;
    private android.support.v7.widget.RecyclerView mRecyAddressBook;
    private LinearLayoutManager linearLayoutManager;
    private Paginate mPaginate;
    private String deptId;
    private String name;
    private String staffState = "在职";
    private boolean isLoadingMore;
    private AddressBookSelPop addressBookSelPop;
    private StaffNameIdKey dept = new StaffNameIdKey();


    public static LinkmanFragment newInstance() {
        LinkmanFragment fragment = new LinkmanFragment();
        return fragment;
    }

    @Override
    public void setupFragmentComponent(AppComponent appComponent) {
        DaggerLinkmanComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .linkmanModule(new LinkmanModule(this))
                .build()
                .inject(this);
    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_linkman, container, false);
        mTvTitle = (TextView) inflate.findViewById(R.id.tv_title);
        mIvTopSelect = (ImageView) inflate.findViewById(R.id.iv_top_select);
        mIvTopSelect.setOnClickListener(v -> mPresenter.requestDept());

        mSrlAddressBook = (SwipeRefreshLayout) inflate.findViewById(R.id.srl_address_book);
        mRecyAddressBook = (RecyclerView) inflate.findViewById(R.id.recy_address_book);
        mPresenter.getAddressBook(true, staffState, "", "", "", "", "");

        return inflate;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        mTvTitle.setText("联系人");
    }

    @Override
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
        UiUtils.snackbarText(message);
    }


    @Override
    public void killMyself() {

    }

    @Override
    public void setAdapter(StaffListAdapter adapter) {
        linearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyAddressBook.setLayoutManager(linearLayoutManager);
        mRecyAddressBook.setAdapter(adapter);
        mSrlAddressBook.setOnRefreshListener(this);
        initPaginate();
    }

    @Override
    public void startLoadMore() {
//        Observable.just(1)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(integer -> mSrlAddressBook.setRefreshing(true));
        isLoadingMore = true;
    }

    @Override
    public void endLoadMore() {
//        mSrlAddressBook.setRefreshing(false);
        isLoadingMore = false;
    }

    @Override
    public void endLoad() {
        mPaginate.setHasMoreDataToLoad(false);
    }

    @Override
    public void showDeptTree(List<Node> deptList) {
        addressBookSelPop = new AddressBookSelPop(getActivity(), deptList, dept, getActivity().findViewById(R.id.rl_linkman));
        addressBookSelPop.setSelStafListener((deptId, name) -> {
            this.deptId = deptId;
            this.name = name;

            mPresenter.getAddressBook(true, staffState, deptId, name, "", "", "");
        });
        addressBookSelPop.showAtLocation(getActivity().findViewById(R.id.rl_linkman),
                Gravity.CENTER | Gravity.CENTER_HORIZONTAL, 0, 0);
    }

    @Override
    public void addressPop(List<String> cell) {

        StaffMsgPop staffMsgPop = new StaffMsgPop(getActivity(), cell);

        staffMsgPop.showAtLocation(getActivity().findViewById(R.id.rl_linkman),
                Gravity.CENTER | Gravity.CENTER_HORIZONTAL, 0, 0);
    }

    @Override
    public void showLoading() {
//        isLoadingMore = true;
        Observable.just(1)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(integer -> mSrlAddressBook.setRefreshing(true));
    }


    @Override
    public void hideLoading() {
//        isLoadingMore = false;
        mSrlAddressBook.setRefreshing(false);
    }

    @Override
    public void refreshList() {

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
}
