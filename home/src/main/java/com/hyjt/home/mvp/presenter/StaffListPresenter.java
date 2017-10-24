package com.hyjt.home.mvp.presenter;

import com.alibaba.android.arouter.launcher.ARouter;
import com.hyjt.frame.api.parseResponse;
import com.hyjt.frame.di.scope.ActivityScope;
import com.hyjt.frame.mvp.BasePresenter;
import com.hyjt.frame.utils.RxLifecycleUtils;
import com.hyjt.home.mvp.contract.StaffListContract;
import com.hyjt.home.mvp.model.entity.Resp.ChildrenBean;
import com.hyjt.home.mvp.model.entity.Resp.StaffListResp;
import com.hyjt.home.mvp.ui.adapter.StaffListAdapter;
import com.hyjt.home.mvp.ui.view.treelistview.Dept;
import com.hyjt.home.mvp.ui.view.treelistview.Node;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.internal.operators.observable.ObservableError;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;


@ActivityScope
public class StaffListPresenter extends BasePresenter<StaffListContract.Model, StaffListContract.View> {
    private RxErrorHandler mErrorHandler;
    private StaffListAdapter mAdapter;
    private List<StaffListResp.RowsBean> cmList = new ArrayList<>();
    private int lastMeetingId = 1;
    private int deptId;
    private List<Node> deptList = new ArrayList<>();


    @Inject
    public StaffListPresenter(StaffListContract.Model model, StaffListContract.View rootView
            , RxErrorHandler handler) {
        super(model, rootView);
        this.mErrorHandler = handler;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
    }

    public void getStaffList(final boolean pullToRefresh, String state, String deptId, String Name, String edu, String startTime, String endTime) {

        if (mAdapter == null) {
            mAdapter = new StaffListAdapter(cmList);
            mRootView.setAdapter(mAdapter);
            mAdapter.setOnItemClickListener((view, viewType, data, position) -> {
                String id = cmList.get(position).getCell().get(0);
                ARouter.getInstance().build("/home/StaffMsgActivity")
                        .withString("id", id)
                        .navigation();
            });
        }

        if (pullToRefresh) {
            lastMeetingId = 1;
            mRootView.showLoading();
        } else
            lastMeetingId++;


        mModel.getStaffList(lastMeetingId, 10, state, deptId, Name, edu, startTime, endTime)
                .map(new parseResponse<>())
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(disposable -> {
                    if (pullToRefresh)
                        mRootView.showLoading();//显示上拉刷新的进度条
                    else
                        mRootView.startLoadMore();//显示下拉加载更多的进度条
                }).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(() -> {
                    if (pullToRefresh)
                        mRootView.hideLoading();//隐藏上拉刷新的进度条
                    else
                        mRootView.endLoadMore();//隐藏下拉加载更多的进度条
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<StaffListResp>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull StaffListResp staffListResp) {
                        if (pullToRefresh) {
                            cmList.clear();
                            cmList.addAll(staffListResp.getRows());
                            mAdapter.notifyDataSetChanged();
                        } else {
                            cmList.addAll(staffListResp.getRows());
                            mAdapter.notifyDataSetChanged();
                        }
                        if (staffListResp.getRows().size() == 0) {
                            mRootView.endLoad();
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        if ("202".equals(e.getMessage())) {
                            mRootView.showNoLimits();
//                            mRootView.killMyself();
                        } else {
                            mRootView.showMessage(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void requestDept() {
        mModel.reqsDeptList()
                .map(new parseResponse<>())
                .flatMap(new Function<List<ChildrenBean>, ObservableSource<List<Node>>>() {
                    @Override
                    public ObservableSource<List<Node>> apply(@NonNull List<ChildrenBean> childrenBeanList) throws Exception {
                        deptId = 1;
                        parseDept(childrenBeanList, 0);
                        return ObservableError.just(deptList);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
                .subscribe(new ErrorHandleSubscriber<List<Node>>(mErrorHandler) {
                    @Override
                    public void onNext(@NonNull List<Node> data) {
                        mRootView.showDeptTree(deptList);
                    }
                });
    }

    /**
     * 解析部门数据
     *
     * @param childrenBeanList
     * @param parent
     */
    private void parseDept(List<ChildrenBean> childrenBeanList, int parent) {
        for (ChildrenBean childrenBean : childrenBeanList) {
            deptId++;
            deptList.add(new Dept(deptId, parent, childrenBean.getText(), childrenBean.getId()));
            if (childrenBean.getChildren().size() == 0)
                continue;
            parseDept(childrenBean.getChildren(), deptId);
        }
    }

}