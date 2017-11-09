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


import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

import javax.inject.Inject;

import com.hyjt.home.mvp.contract.ApplyExpenseEditContract;
import com.hyjt.home.mvp.model.entity.Reqs.ApplyExpCreateReqs;
import com.hyjt.home.mvp.model.entity.Reqs.BaseIdReqs;
import com.hyjt.home.mvp.model.entity.Reqs.BaseNumReqs;
import com.hyjt.home.mvp.model.entity.Reqs.BaseTypeReqs;
import com.hyjt.home.mvp.model.entity.Reqs.ClNodeApproveReqs;
import com.hyjt.home.mvp.model.entity.Reqs.CompLoanListReqs;
import com.hyjt.home.mvp.model.entity.Reqs.PsonLoanListReqs;
import com.hyjt.home.mvp.model.entity.Resp.AEExpMoneyResp;
import com.hyjt.home.mvp.model.entity.Resp.ApplyExpDetailResp;
import com.hyjt.home.mvp.model.entity.Resp.ApplyExpTypeResp;
import com.hyjt.home.mvp.model.entity.Resp.CompLoanDetailResp;
import com.hyjt.home.mvp.model.entity.Resp.CompLoanListResp;
import com.hyjt.home.mvp.model.entity.Resp.PLBankAccountResp;
import com.hyjt.home.mvp.model.entity.Resp.PLCompBankAccountResp;
import com.hyjt.home.mvp.model.entity.Resp.PLCompanyResp;
import com.hyjt.home.mvp.model.entity.Resp.PLFristLeaderResp;
import com.hyjt.home.mvp.model.entity.Resp.PsonLoanListResp;


@ActivityScope
public class ApplyExpenseEditPresenter extends BasePresenter<ApplyExpenseEditContract.Model, ApplyExpenseEditContract.View> {
    private RxErrorHandler mErrorHandler;
    private Application mApplication;
    private ImageLoader mImageLoader;
    private AppManager mAppManager;

    @Inject
    public ApplyExpenseEditPresenter(ApplyExpenseEditContract.Model model, ApplyExpenseEditContract.View rootView
            , RxErrorHandler handler, Application application
            , ImageLoader imageLoader, AppManager appManager) {
        super(model, rootView);
        this.mErrorHandler = handler;
        this.mApplication = application;
        this.mImageLoader = imageLoader;
        this.mAppManager = appManager;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mAppManager = null;
        this.mImageLoader = null;
        this.mApplication = null;
    }

    public void getApplyExpDetail(String applyExpId) {
        mModel.getApplyExpDetail(new BaseIdReqs(applyExpId))
                .map(new parseResponse<>())
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(disposable -> mRootView.showLoading("报销详情加载中..."))
                .subscribeOn(AndroidSchedulers.mainThread())
                .doFinally(() -> mRootView.hideLoading())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
                .subscribe(new ErrorHandleSubscriber<ApplyExpDetailResp>(mErrorHandler) {
                    @Override
                    public void onNext(@NonNull ApplyExpDetailResp applyExpDetailResp) {
                        mRootView.showAEDetail(applyExpDetailResp);
                    }
                });
    }

    public void selApplyExpBankAct(EditText mEdtSendAccount) {

        mModel.applyExpBankAccount()
                .map(new parseResponse<>())
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(disposable -> mRootView.showLoading("银行账户加载中..."))
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(() -> mRootView.hideLoading())
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
                .subscribe(new ErrorHandleSubscriber<PLCompBankAccountResp>(mErrorHandler) {
                    @Override
                    public void onNext(@NonNull PLCompBankAccountResp CompBankAccountResp) {
                        mRootView.showAprBankAccount(CompBankAccountResp, mEdtSendAccount);
                    }
                });
    }

    public void flowNodeApr(ClNodeApproveReqs nodeApr) {

        Log.e("http_reqs", nodeApr.toString());
        mModel.flowNodeApprove(nodeApr)
                .map(new parseResponse<>())
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(disposable -> mRootView.showLoading("审批确认中..."))
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(() -> mRootView.hideLoading())
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
                .subscribe(new ErrorHandleSubscriber<Object>(mErrorHandler) {
                    @Override
                    public void onNext(@NonNull Object object) {
                        mRootView.showMessage("审批成功");
                        mRootView.killMyself();
                    }
                });
    }



    public void getFristLeader(String plType) {
        mModel.getFristLeader(new BaseTypeReqs(plType))
                .map(new parseResponse<>())
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(disposable -> mRootView.showLoading("首签领导加载中..."))
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(() -> mRootView.hideLoading())
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
                .subscribe(new ErrorHandleSubscriber<PLFristLeaderResp>(mErrorHandler) {
                    @Override
                    public void onNext(@NonNull PLFristLeaderResp plFristLeaderResp) {
                        mRootView.loadFlowNode(plFristLeaderResp);
                    }
                });
    }

    public void getAECompany() {
        mModel.getCompany()
                .map(new parseResponse<>())
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(disposable -> mRootView.showLoading("公司信息加载中..."))
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(() -> mRootView.hideLoading())
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
                .subscribe(new ErrorHandleSubscriber<PLCompanyResp>(mErrorHandler) {
                    @Override
                    public void onNext(@NonNull PLCompanyResp plCompanyResp) {
                        mRootView.showCompanyList(plCompanyResp);
                    }
                });
    }


    public void tellerConfirm(String applyExpId) {
        mModel.aeTellerConfirm(new BaseIdReqs(applyExpId))
                .map(new parseResponse<>())
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(disposable -> mRootView.showLoading("出纳确认中..."))
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(() -> mRootView.hideLoading())
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
                .subscribe(new ErrorHandleSubscriber<Object>(mErrorHandler) {
                    @Override
                    public void onNext(@NonNull Object obj) {
                        mRootView.showMessage("确认成功");
                        mRootView.killMyself();
                    }
                });
    }


    public void receiverConfirm(String applyExpId) {
        mModel.aeReceiverConfirm(new BaseIdReqs(applyExpId))
                .map(new parseResponse<>())
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(disposable -> mRootView.showLoading("收款人确认中..."))
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(() -> mRootView.hideLoading())
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
                .subscribe(new ErrorHandleSubscriber<Object>(mErrorHandler) {
                    @Override
                    public void onNext(@NonNull Object obj) {
                        mRootView.showMessage("确认成功");
                        mRootView.killMyself();
                    }
                });
    }


    public void getExpType() {

        mModel.getApplyExpTypeList()
                .map(new parseResponse<>())
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(disposable -> mRootView.showLoading("加载费用类型..."))
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(() -> mRootView.hideLoading())
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
                .subscribe(new ErrorHandleSubscriber<ApplyExpTypeResp>(mErrorHandler) {
                    @Override
                    public void onNext(@NonNull ApplyExpTypeResp applyExpTypeResp) {
                        mRootView.showExpTypeList(applyExpTypeResp);
                    }
                });
    }

    public void getCompLoanList(EditText mEdtWriteoffNum, EditText mEdtBorrowMoney, EditText mEdtUnpayed, EditText mEdtPayed) {
        mModel.getCompLoanList(new CompLoanListReqs("1", "9999", "", "", "",
                "1", "", "", "", ""))
                .map(new parseResponse<>())
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(disposable -> mRootView.showLoading("对公借款加载中..."))
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(() -> mRootView.hideLoading())
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
                .subscribe(new ErrorHandleSubscriber<CompLoanListResp>(mErrorHandler) {
                    @Override
                    public void onNext(@NonNull CompLoanListResp compLoanListResp) {
                        mRootView.showExpCMoneyList(compLoanListResp, mEdtWriteoffNum
                                , mEdtBorrowMoney, mEdtUnpayed, mEdtPayed);
                    }
                });

    }

    public void getPsonLoanList(EditText mEdtWriteoffNum, EditText mEdtBorrowMoney, EditText mEdtUnpayed, EditText mEdtPayed) {
        mModel.getPsonLoanList(new PsonLoanListReqs("1", "9999", "", "", "",
                "1", "", "", "", ""))
                .map(new parseResponse<>())
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(disposable -> mRootView.showLoading("个人借款加载中..."))
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(() -> mRootView.hideLoading())
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
                .subscribe(new ErrorHandleSubscriber<PsonLoanListResp>(mErrorHandler) {
                    @Override
                    public void onNext(@NonNull PsonLoanListResp psonLoanListResp) {
                        mRootView.showExpPMoneyList(psonLoanListResp, mEdtWriteoffNum
                                , mEdtBorrowMoney, mEdtUnpayed, mEdtPayed);
                    }
                });

    }

    public void getExpMoney(String num, EditText mEdtUnpayed, EditText mEdtPayed) {

        mModel.getExpMoney(new BaseNumReqs(num))
                .map(new parseResponse<>())
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(disposable -> mRootView.showLoading("加载核销金额..."))
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(() -> mRootView.hideLoading())
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
                .subscribe(new ErrorHandleSubscriber<AEExpMoneyResp>(mErrorHandler) {
                    @Override
                    public void onNext(@NonNull AEExpMoneyResp aeexpMoneyResp) {
                        mRootView.showExpMoney(aeexpMoneyResp, mEdtUnpayed, mEdtPayed);
                    }

                });
    }



    public void getReceiveBank(EditText mEdtAccountName, EditText mEdtOpenactBank, EditText mEdtBankAccount){

        mModel.getReceiveBank()
                .map(new parseResponse<>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(() -> {
                    mRootView.hideLoading();//隐藏
                }).observeOn(AndroidSchedulers.mainThread())
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
                .subscribe(new ErrorHandleSubscriber<PLBankAccountResp>(mErrorHandler) {
                    @Override
                    public void onNext(@NonNull PLBankAccountResp plBankAccountResp) {
                        mRootView.showBankAccount(plBankAccountResp, mEdtAccountName, mEdtOpenactBank, mEdtBankAccount);
                    }
                });
    }

    public void delApplyExp(String applyExpId) {
        mModel.delApplyExp(new BaseIdReqs(applyExpId))
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


    public void editApplyExp(ApplyExpCreateReqs applyExpCreateReqs){
        Log.e("http_reqs", applyExpCreateReqs.toString());
        mModel.editApplyExp(applyExpCreateReqs)
                .map(new parseResponse<>())
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(disposable -> mRootView.showLoading("费用报销创建中..."))
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(() -> mRootView.hideLoading())
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
                .subscribe(new ErrorHandleSubscriber<Object>(mErrorHandler) {
                    @Override
                    public void onNext(@NonNull Object obj) {
                        mRootView.showMessage("编辑成功");
                        mRootView.killMyself();
                    }
                });
    }
}
