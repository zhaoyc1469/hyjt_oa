package com.hyjt.home.mvp.presenter;

import com.alibaba.android.arouter.launcher.ARouter;
import com.hyjt.frame.api.parseResponse;
import com.hyjt.frame.di.scope.ActivityScope;
import com.hyjt.frame.mvp.BasePresenter;
import com.hyjt.frame.utils.RxLifecycleUtils;
import com.hyjt.home.mvp.contract.LongSolveListContract;
import com.hyjt.home.mvp.model.entity.Resp.MeetingListResp;
import com.hyjt.home.mvp.ui.adapter.MeetingAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
import me.jessyan.rxerrorhandler.handler.RetryWithDelay;


@ActivityScope
public class LongSolveListPresenter extends BasePresenter<LongSolveListContract.Model, LongSolveListContract.View> {
    private RxErrorHandler mErrorHandler;
    private MeetingAdapter mAdapter;
    private List<MeetingListResp.RowsBean> cmList = new ArrayList<>();
    private int lastMeetingId = 1;

    @Inject
    public LongSolveListPresenter(LongSolveListContract.Model model, LongSolveListContract.View rootView
            , RxErrorHandler handler) {
        super(model, rootView);
        this.mErrorHandler = handler;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
    }

    public void requestMeetingList(final boolean pullToRefresh
            , String CM_Num, String CM_name, String CM_promoter) {

        if (mAdapter == null) {
            mAdapter = new MeetingAdapter(cmList);
            mRootView.setAdapter(mAdapter);
            mAdapter.setOnItemClickListener((view, viewType, data, position) -> {
                String id = cmList.get(position).getId();
                ARouter.getInstance().build("/home/MeetingEditActivity")
                        .withString("id", id)
                        .navigation();
            });
        }

        if (pullToRefresh) {
            lastMeetingId = 1;
            mRootView.showLoading();
        } else
            lastMeetingId++;


        mModel.getLSolveList(lastMeetingId, 10, CM_Num, CM_name, CM_promoter)
                .map(new parseResponse<>())
                .subscribeOn(Schedulers.io())
                .retryWhen(new RetryWithDelay(3, 2))//遇到错误时重试,第一个参数为重试几次,第二个参数为重试的间隔
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
                }).observeOn(AndroidSchedulers.mainThread())
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
                .subscribe(new ErrorHandleSubscriber<MeetingListResp>(mErrorHandler) {
                    @Override
                    public void onNext(@NonNull MeetingListResp meetingListResp) {
                        if (pullToRefresh) {
                            cmList.clear();
                            cmList.addAll(meetingListResp.getRows());
                            mAdapter.notifyDataSetChanged();
                        } else {
                            cmList.addAll(meetingListResp.getRows());
                            mAdapter.notifyDataSetChanged();
                        }
                        if (meetingListResp.getRows().size() == 0) {
                            mRootView.endLoad();
                        }
                    }
                });
    }

}