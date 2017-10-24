package com.hyjt.home.mvp.presenter;

import com.alibaba.android.arouter.launcher.ARouter;
import com.hyjt.frame.api.parseResponse;
import com.hyjt.frame.di.scope.ActivityScope;
import com.hyjt.frame.mvp.BasePresenter;
import com.hyjt.frame.utils.RxLifecycleUtils;
import com.hyjt.home.mvp.contract.BlocNoticeListContract;
import com.hyjt.home.mvp.model.entity.Resp.BlocNoticeListResp;
import com.hyjt.home.mvp.ui.adapter.BlocNoticeAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
import me.jessyan.rxerrorhandler.handler.RetryWithDelay;


@ActivityScope
public class BlocNoticeListPresenter extends BasePresenter<BlocNoticeListContract.Model, BlocNoticeListContract.View> {
    private RxErrorHandler mErrorHandler;
    private BlocNoticeAdapter mAdapter;
    private List<BlocNoticeListResp.RowsBean> bnList = new ArrayList<>();
    private int lastMeetingId = 1;
    private String ForbidEdit = "0";

    @Inject
    public BlocNoticeListPresenter(BlocNoticeListContract.Model model, BlocNoticeListContract.View rootView
            , RxErrorHandler handler) {
        super(model, rootView);
        this.mErrorHandler = handler;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
    }

    public void requestBNList(final boolean pullToRefresh) {

        if (mAdapter == null) {
            mAdapter = new BlocNoticeAdapter(bnList);
            mRootView.setAdapter(mAdapter);
            mAdapter.setOnItemClickListener((view, viewType, data, position) -> {
                String id = bnList.get(position).getId();
                ARouter.getInstance().build("/home/BNoticeEditActivity")
                        .withString("id", id)
                        .withString("ForbidEdit", ForbidEdit)
                        .navigation();
            });
        }

        if (pullToRefresh) {
            lastMeetingId = 1;
            mRootView.showLoading();
        } else
            lastMeetingId++;


        mModel.getBNoticeList(lastMeetingId, 10)
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
                        mRootView.endLoadMore(ForbidEdit);//隐藏下拉加载更多的进度条
                }).observeOn(AndroidSchedulers.mainThread())
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
                .subscribe(new ErrorHandleSubscriber<BlocNoticeListResp>(mErrorHandler) {
                    @Override
                    public void onNext(@NonNull BlocNoticeListResp bNoticeListResp) {
                        if (pullToRefresh) {
                            bnList.clear();
                            bnList.addAll(bNoticeListResp.getRows());
                            mAdapter.notifyDataSetChanged();
                        } else {
                            bnList.addAll(bNoticeListResp.getRows());
                            mAdapter.notifyDataSetChanged();
                        }
                        if (bNoticeListResp.getRows().size() == 0) {
                            mRootView.endLoad();
                        }
                    }
                });
    }


    public void requestBNLimits() {
        mModel.getBNoticeLimits()
                .map(new parseResponse<>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(() -> mRootView.LoadList())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Object object) {
                        ForbidEdit = "1";
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}