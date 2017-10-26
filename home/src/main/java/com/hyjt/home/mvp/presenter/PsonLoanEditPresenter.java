package com.hyjt.home.mvp.presenter;

import android.app.Application;
import android.util.Log;
import android.widget.EditText;

import com.hyjt.frame.api.parseResponse;
import com.hyjt.frame.di.scope.ActivityScope;
import com.hyjt.frame.mvp.BasePresenter;
import com.hyjt.frame.integration.AppManager;


import com.hyjt.frame.utils.RxLifecycleUtils;
import com.hyjt.frame.widget.imageloader.ImageLoader;


import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.internal.operators.observable.ObservableError;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

import javax.inject.Inject;

import com.hyjt.home.mvp.contract.PsonLoanEditContract;
import com.hyjt.home.mvp.model.entity.Reqs.BaseIdReqs;
import com.hyjt.home.mvp.model.entity.Reqs.BaseTypeReqs;
import com.hyjt.home.mvp.model.entity.Reqs.PlNodeApproveReqs;
import com.hyjt.home.mvp.model.entity.Reqs.PsonLoanCreateReqs;
import com.hyjt.home.mvp.model.entity.Resp.ChildrenBean;
import com.hyjt.home.mvp.model.entity.Resp.PLCompBankAccountResp;
import com.hyjt.home.mvp.model.entity.Resp.PLCompanyResp;
import com.hyjt.home.mvp.model.entity.Resp.PLFlowNodeResp;
import com.hyjt.home.mvp.model.entity.Resp.PLFristLeaderResp;
import com.hyjt.home.mvp.model.entity.Resp.PsonLoanDetailResp;
import com.hyjt.home.mvp.ui.view.treelistview.Node;

import java.util.List;


@ActivityScope
public class PsonLoanEditPresenter extends BasePresenter<PsonLoanEditContract.Model, PsonLoanEditContract.View> {
    private RxErrorHandler mErrorHandler;
    private Application mApplication;

    @Inject
    public PsonLoanEditPresenter(PsonLoanEditContract.Model model, PsonLoanEditContract.View rootView
            , RxErrorHandler handler, Application application) {
        super(model, rootView);
        this.mErrorHandler = handler;
        this.mApplication = application;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mApplication = null;
    }

    public void getPsonLoanDetail(String plId) {
        mModel.getPLDetail(new BaseIdReqs(plId))
                .map(new parseResponse<>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(() -> {
                    if (mRootView != null)
                        mRootView.hideLoading();//隐藏
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ErrorHandleSubscriber<PsonLoanDetailResp>(mErrorHandler) {
                    @Override
                    public void onNext(@NonNull PsonLoanDetailResp psonLoanDetailResp) {
                        mRootView.showPLDetail(psonLoanDetailResp);
                    }
                });
    }

    public void getFristLeader(String plType) {
        mModel.getFristLeader(new BaseTypeReqs(plType))
                .map(new parseResponse<>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(() -> {
                    mRootView.hideLoading();//隐藏
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ErrorHandleSubscriber<PLFristLeaderResp>(mErrorHandler) {
                    @Override
                    public void onNext(@NonNull PLFristLeaderResp plFristLeaderResp) {
                        mRootView.loadFlowNode(plFristLeaderResp);
                    }
                });
    }

    public void getPLCompany() {
        mModel.getPLCompany()
                .map(new parseResponse<>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(() -> {
                    mRootView.hideLoading();//隐藏
                }).observeOn(AndroidSchedulers.mainThread())
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
                .subscribe(new ErrorHandleSubscriber<PLCompanyResp>(mErrorHandler) {
                    @Override
                    public void onNext(@NonNull PLCompanyResp plCompanyResp) {

                    }
                });
    }

    public void delPsonLoan(String psonLoanId) {
        mModel.delPsonLoan(new BaseIdReqs(psonLoanId))
                .map(new parseResponse<>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(() -> {
                    mRootView.hideLoading();//隐藏
                }).observeOn(AndroidSchedulers.mainThread())
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
                .subscribe(new ErrorHandleSubscriber<Object>(mErrorHandler) {
                    @Override
                    public void onNext(@NonNull Object object) {
                        mRootView.showMessage("删除成功");
                        mRootView.killMyself();
                    }
                });
    }

    public void editPsonLoan(PsonLoanCreateReqs psonLoanCreateReqs) {
        Log.e("http_reqs", psonLoanCreateReqs.toString());
        mModel.editPsonLoan(psonLoanCreateReqs)
                .map(new parseResponse<>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(() -> {
                    mRootView.hideLoading();//隐藏
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ErrorHandleSubscriber<Object>(mErrorHandler) {
                    @Override
                    public void onNext(@NonNull Object object) {
                        mRootView.showMessage("编辑成功");
                        mRootView.killMyself();
                    }
                });
    }

    public void flowNodeApr(PlNodeApproveReqs plnodeApproveReqs) {
        Log.e("http_reqs", plnodeApproveReqs.toString());
        mModel.flowNodeApprove(plnodeApproveReqs)
                .map(new parseResponse<>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(() -> {
                    mRootView.hideLoading();//隐藏
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ErrorHandleSubscriber<Object>(mErrorHandler) {
                    @Override
                    public void onNext(@NonNull Object object) {
                        mRootView.showMessage("审批成功");
                        mRootView.killMyself();
                    }
                });
    }

    public void selCompBankAct(EditText editText) {
        mModel.compBankAccount()
                .map(new parseResponse<>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(() -> {
                    mRootView.hideLoading();//隐藏
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ErrorHandleSubscriber<PLCompBankAccountResp>(mErrorHandler) {
                    @Override
                    public void onNext(@NonNull PLCompBankAccountResp CompBankAccountResp) {
                        mRootView.showAprBankAccount(CompBankAccountResp, editText);
                    }
                });
    }

    public void tellerConfirm(String psonLoanId) {
        mModel.plTellerConfirm(new BaseIdReqs(psonLoanId))
                .map(new parseResponse<>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(() -> {
                    mRootView.hideLoading();//隐藏
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ErrorHandleSubscriber<Object>(mErrorHandler) {
                    @Override
                    public void onNext(@NonNull Object obj) {
                        mRootView.showMessage("确认成功");
                        mRootView.killMyself();
                    }
                });
    }


    public void receiverConfirm(String psonLoanId) {
        mModel.plReceiverConfirm(new BaseIdReqs(psonLoanId))
                .map(new parseResponse<>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(() -> {
                    mRootView.hideLoading();//隐藏
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ErrorHandleSubscriber<Object>(mErrorHandler) {
                    @Override
                    public void onNext(@NonNull Object obj) {
                        mRootView.showMessage("确认成功");
                        mRootView.killMyself();
                    }
                });
    }

}
