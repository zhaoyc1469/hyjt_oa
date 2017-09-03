package com.hyjt.home.mvp.presenter;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.hyjt.frame.api.parseResponse;
import com.hyjt.frame.di.scope.ActivityScope;
import com.hyjt.frame.integration.AppManager;
import com.hyjt.frame.mvp.BasePresenter;
import com.hyjt.frame.widget.imageloader.ImageLoader;
import com.hyjt.home.mvp.contract.EmailListContract;
import com.hyjt.home.mvp.model.entity.Reqs.EmailListReqs;
import com.hyjt.home.mvp.model.entity.Resp.CEmailListResp;
import com.hyjt.home.mvp.ui.adapter.EmailAdapter;

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
public class EmailListPresenter extends BasePresenter<EmailListContract.Model, EmailListContract.View> {
    private RxErrorHandler mErrorHandler;
    private EmailAdapter mAdapter;
    private List<CEmailListResp.RowsBean> ceList = new ArrayList<>();
    private int pageId = 1;

    @Inject
    public EmailListPresenter(EmailListContract.Model model, EmailListContract.View rootView
            , RxErrorHandler handler, Application application
            , ImageLoader imageLoader, AppManager appManager) {
        super(model, rootView);
        this.mErrorHandler = handler;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
    }

    public void getEmailList(boolean pullToRefresh, String Type) {

        if (mAdapter == null) {
            mAdapter = new EmailAdapter(ceList,Type);
            mRootView.setAdapter(mAdapter);
            mAdapter.setOnItemClickListener((view, viewType, data, position) -> {
                String id = ceList.get(position).getId();
                ARouter.getInstance().build("/home/EmailDetailsActivity")
                        .withString("Id", id)
                        .withString("Type", Type)
                        .navigation();
            });
        }

        if (pullToRefresh) {
            pageId = 1;
            mRootView.showLoading();
        } else
            pageId++;


        mModel.getEmailList(new EmailListReqs("" + pageId, "10", Type))
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
                .doAfterTerminate(() -> {
                    if (pullToRefresh) {
                        if (mRootView != null) {
                            mRootView.hideLoading();//隐藏上拉刷新的进度条
                        }
                    } else
                        mRootView.endLoadMore();//隐藏下拉加载更多的进度条
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ErrorHandleSubscriber<CEmailListResp>(mErrorHandler) {
                    @Override
                    public void onNext(@NonNull CEmailListResp cEmailListResp) {
                        if (pullToRefresh) {
                            ceList.clear();
                            ceList.addAll(cEmailListResp.getRows());
                            mAdapter.notifyDataSetChanged();
                        } else {
                            ceList.addAll(cEmailListResp.getRows());
                            mAdapter.notifyDataSetChanged();
                        }
                        if (cEmailListResp.getRows().size() == 0) {
                            mRootView.endLoad();
                        }
                    }
                });
    }
}