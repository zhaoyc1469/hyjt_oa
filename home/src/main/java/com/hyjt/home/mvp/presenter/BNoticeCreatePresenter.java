package com.hyjt.home.mvp.presenter;

import android.app.Application;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

import com.hyjt.frame.api.parseResponse;
import com.hyjt.frame.di.scope.ActivityScope;
import com.hyjt.frame.integration.AppManager;
import com.hyjt.frame.mvp.BasePresenter;
import com.hyjt.frame.utils.PermissionUtil;
import com.hyjt.frame.widget.imageloader.ImageLoader;
import com.hyjt.home.mvp.contract.BNoticeCreateContract;
import com.hyjt.home.mvp.model.entity.Resp.BNoticeDetailsResp;
import com.hyjt.home.mvp.model.entity.Resp.ImgUploadResp;
import com.hyjt.home.utils.ImgUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
import me.jessyan.rxerrorhandler.handler.RetryWithDelay;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;


@ActivityScope
public class BNoticeCreatePresenter extends BasePresenter<BNoticeCreateContract.Model, BNoticeCreateContract.View> {
    private RxErrorHandler mErrorHandler;
    private Application mApplication;
    private ImageLoader mImageLoader;
    private AppManager mAppManager;

    @Inject
    public BNoticeCreatePresenter(BNoticeCreateContract.Model model, BNoticeCreateContract.View rootView
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


    public void sendFile(ArrayList<Uri> listData) {

        List<ImgUploadResp> ImgUploadList = new ArrayList<>();
        // 请求外部存储权限用于适配android6.0的权限管理机制
        PermissionUtil.externalStorage(new PermissionUtil.RequestPermission() {
            @Override
            public void onRequestPermissionSuccess() {
                //request permission success, do something.

                // 转换成绝对路径
                ArrayList<String> filesPaths = new ArrayList<>();
                for (Uri uri : listData) {
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
                            .retryWhen(new RetryWithDelay(5, 10))
                            .observeOn(AndroidSchedulers.mainThread())
                            .doFinally(() -> {
                                if (ImgUploadList.size() == filesPaths.size() - 1) {
                                    mRootView.hideLoading();//隐藏
                                }
                            })
                            .observeOn(AndroidSchedulers.mainThread())
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

    public void sendBNotice(BNoticeDetailsResp bNoticeDetailsResp){
        mModel.sendBNotice(bNoticeDetailsResp)
                .map(new parseResponse<>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(() -> {
                    mRootView.hideLoading();//隐藏
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ErrorHandleSubscriber<Object>(mErrorHandler) {
                    @Override
                    public void onNext(@NonNull Object bNoticeDetails) {
                        mRootView.showMessage("创建完成");
                        mRootView.killMyself();
                    }
                });

    }

}