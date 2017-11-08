package com.hyjt.home.mvp.presenter;

import android.app.Application;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;

import com.alibaba.android.arouter.launcher.ARouter;
import com.hyjt.frame.api.parseResponse;
import com.hyjt.frame.di.scope.ActivityScope;
import com.hyjt.frame.mvp.BasePresenter;
import com.hyjt.frame.integration.AppManager;


import com.hyjt.frame.utils.PermissionUtil;
import com.hyjt.frame.utils.RxLifecycleUtils;
import com.hyjt.frame.widget.imageloader.ImageLoader;


import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
import me.jessyan.rxerrorhandler.handler.RetryWithDelay;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import javax.inject.Inject;

import com.hyjt.home.mvp.contract.ApplyExpenseCreateContract;
import com.hyjt.home.mvp.model.entity.Reqs.ApplyExpCreateReqs;
import com.hyjt.home.mvp.model.entity.Reqs.BaseNumReqs;
import com.hyjt.home.mvp.model.entity.Reqs.BaseTypeReqs;
import com.hyjt.home.mvp.model.entity.Reqs.CompLoanListReqs;
import com.hyjt.home.mvp.model.entity.Reqs.PsonLoanListReqs;
import com.hyjt.home.mvp.model.entity.Resp.AEExpMoneyResp;
import com.hyjt.home.mvp.model.entity.Resp.ApplyExpTypeResp;
import com.hyjt.home.mvp.model.entity.Resp.CompLoanListResp;
import com.hyjt.home.mvp.model.entity.Resp.ImgUploadResp;
import com.hyjt.home.mvp.model.entity.Resp.PLBankAccountResp;
import com.hyjt.home.mvp.model.entity.Resp.PLCompanyResp;
import com.hyjt.home.mvp.model.entity.Resp.PLFristLeaderResp;
import com.hyjt.home.mvp.model.entity.Resp.PsonLoanListResp;
import com.hyjt.home.mvp.ui.adapter.CompLoanAdapter;
import com.hyjt.home.mvp.ui.adapter.PsonLoanAdapter;
import com.hyjt.home.utils.ImgUtil;

import java.io.File;
import java.lang.annotation.Repeatable;
import java.util.ArrayList;
import java.util.List;


@ActivityScope
public class ApplyExpenseCreatePresenter extends BasePresenter<ApplyExpenseCreateContract.Model, ApplyExpenseCreateContract.View> {
    private RxErrorHandler mErrorHandler;
    private Application mApplication;
    private ImageLoader mImageLoader;
    private AppManager mAppManager;

    @Inject
    public ApplyExpenseCreatePresenter(ApplyExpenseCreateContract.Model model, ApplyExpenseCreateContract.View rootView
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


    public void sendFile(ArrayList<Uri> mFileURLList) {
        List<ImgUploadResp> ImgUploadList = new ArrayList<>();
        // 请求外部存储权限用于适配android6.0的权限管理机制
        PermissionUtil.externalStorage(new PermissionUtil.RequestPermission() {
            @Override
            public void onRequestPermissionSuccess() {
                //request permission success, do something.
                // 转换成绝对路径
                ArrayList<String> filesPaths = new ArrayList<>();
                for (Uri uri : mFileURLList) {
                    String imageAbsolutePath = ImgUtil.getImageAbsolutePath(mApplication, uri);
                    if (!TextUtils.isEmpty(imageAbsolutePath)) {
                        filesPaths.add(imageAbsolutePath);
                        Log.e("imageAbsolutePath", imageAbsolutePath);
                    }
                }
                if (filesPaths.size() == 0) {
                    mRootView.hideLoading();
                    mRootView.fileUploadOk(ImgUploadList);
                    return;
                }
                // 循环发送
                for (int i = 0; i < filesPaths.size(); i++) {
                    String filePath = filesPaths.get(i);
                    File file = new File(filePath);
                    RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                    MultipartBody.Part filePart = MultipartBody.Part.createFormData("file",
                            file.getName(), requestBody);

                    mModel.fileUpload(filePart)
                            .map(new parseResponse<>())
                            .subscribeOn(Schedulers.io())
//                            .repeat(filesPaths.size())
                            .retryWhen(new RetryWithDelay(5, 10))
                            .observeOn(AndroidSchedulers.mainThread())
                            .doFinally(() -> {
                                if (ImgUploadList.size() == filesPaths.size() - 1) {
                                    mRootView.hideLoading();//隐藏
                                }
                            })
                            .observeOn(AndroidSchedulers.mainThread())
                            .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
                            .subscribe(new ErrorHandleSubscriber<ImgUploadResp>(mErrorHandler) {
                                @Override
                                public void onNext(@NonNull ImgUploadResp imgUploadResp) {
                                    ImgUploadList.add(imgUploadResp);
                                    if (ImgUploadList.size() == filesPaths.size()) {
                                        mRootView.fileUploadOk(ImgUploadList);
                                    }
                                }
                            });
                }
            }

            @Override
            public void onRequestPermissionFailure() {
                mRootView.hideLoading();
                mRootView.showMessage("您拒绝存储权限将无法使用此功能,请您去设置打开此权限");
            }
        }, mRootView.getRxPermissions(), mErrorHandler);
    }


    public void createApplyExp(ApplyExpCreateReqs applyExpCreateReqs) {
        Log.e("http_reqs", applyExpCreateReqs.toString());
        mModel.createApplyExp(applyExpCreateReqs)
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
                        mRootView.showMessage("创建成功");
                        mRootView.killMyself();
                    }
                });
    }

    public void getPsonLoanList(EditText mEdtWriteoffNum, EditText mEdtBorrowMoney, EditText mEdtPayed, EditText Payed) {

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
                                , mEdtBorrowMoney, mEdtPayed, Payed);
                    }
                });
    }


    public void getCompLoanList(EditText mEdtWriteoffNum, EditText mEdtBorrowMoney, EditText mEdtPayed, EditText Payed) {

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
                                , mEdtBorrowMoney, mEdtPayed, Payed);
                    }

                });

    }

    public void getExpMoney(String num, EditText mEdtPayed, EditText Payed) {

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
                        mRootView.showExpMoney(aeexpMoneyResp, mEdtPayed, Payed);
                    }

                });
    }
}
