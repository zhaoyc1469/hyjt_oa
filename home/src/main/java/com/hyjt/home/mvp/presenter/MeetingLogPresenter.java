package com.hyjt.home.mvp.presenter;

import android.app.Application;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;

import com.hyjt.frame.api.parseResponse;
import com.hyjt.frame.di.scope.ActivityScope;
import com.hyjt.frame.integration.AppManager;
import com.hyjt.frame.mvp.BasePresenter;
import com.hyjt.frame.utils.PermissionUtil;
import com.hyjt.frame.utils.RxLifecycleUtils;
import com.hyjt.frame.widget.imageloader.ImageLoader;
import com.hyjt.home.mvp.contract.MeetingLogContract;
import com.hyjt.home.mvp.model.entity.Reqs.MeetingMsgReqs;
import com.hyjt.home.mvp.model.entity.Reqs.StaffNameIdKey;
import com.hyjt.home.mvp.model.entity.Resp.ImgUploadResp;
import com.hyjt.home.mvp.model.entity.Resp.LinkManResp;
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
public class MeetingLogPresenter extends BasePresenter<MeetingLogContract.Model, MeetingLogContract.View> {
    private RxErrorHandler mErrorHandler;
    private Application mApplication;
    private ImageLoader mImageLoader;
    private AppManager mAppManager;

    @Inject
    public MeetingLogPresenter(MeetingLogContract.Model model, MeetingLogContract.View rootView
            , RxErrorHandler handler, Application application
            , ImageLoader imageLoader, AppManager appManager) {
        super(model, rootView);
        this.mErrorHandler = handler;
        this.mApplication = application;
        this.mImageLoader = imageLoader;
        this.mAppManager = appManager;
    }

    public void sendPicture(ArrayList<Uri> listData) {

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
                    mRootView.hideUpload();
                    mRootView.imgUploadOk(ImgUploadList);
                    return;
                }
                // 循环发送
                for (String filePath : filesPaths) {
                    File file = new File(filePath);
                    RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                    MultipartBody.Part filePart = MultipartBody.Part.createFormData("file", file.getName(), requestBody);

                    mModel.imgUpload(filePart)
                            .map(new parseResponse<>())
                            .subscribeOn(Schedulers.io())
                            .retryWhen(new RetryWithDelay(5, 10))
                            .observeOn(AndroidSchedulers.mainThread())
                            .doFinally(() -> {
                                if (ImgUploadList.size() == filesPaths.size() - 1) {
                                    mRootView.hideUpload();//隐藏上拉刷新的进度条
                                }
                            })
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new ErrorHandleSubscriber<ImgUploadResp>(mErrorHandler) {
                                @Override
                                public void onNext(@NonNull ImgUploadResp imgUploadResp) {
                                    ImgUploadList.add(imgUploadResp);
                                    if (ImgUploadList.size() == filesPaths.size()) {
                                        mRootView.imgUploadOk(ImgUploadList);
                                    }
                                }
                            });
                }
            }

            @Override
            public void onRequestPermissionFailure() {
                mRootView.hideUpload();
                mRootView.showMessage("您拒绝存储权限将无法使用此功能,请您去设置打开此权限");
            }
        }, mRootView.getRxPermissions(), mErrorHandler);
    }

    public void getLinkmanMsg(String SysDepartment, EditText selEdit, StaffNameIdKey staffNameId, boolean moreCheck) {
        mModel.getLinkman("1", "9999", SysDepartment)
                .map(new parseResponse<>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
                .subscribe(new ErrorHandleSubscriber<LinkManResp>(mErrorHandler) {
                    @Override
                    public void onNext(@NonNull LinkManResp linkManResp) {
                        LinkManResp.DataBean.RowsBean[] linkman = linkManResp.getData().getRows().toArray
                                (new LinkManResp.DataBean.RowsBean[linkManResp.getData().getRows().size()]);

                        String[] nameAry = new String[linkman.length];
                        String[] nameSendAry = new String[linkman.length];

//                        ^员工唯一码|姓名^
                        for (int i = 0; i < linkman.length; i++) {
                            LinkManResp.DataBean.RowsBean item = linkman[i];
                            List<String> cell = item.getCell();
                            nameAry[i] = cell.get(1);
                            nameSendAry[i] = "^" + cell.get(0) + "|" + cell.get(1);
                        }

                        mRootView.getLinkmanOk(nameAry, nameSendAry, selEdit, staffNameId, moreCheck);
                    }
                });
    }

    public void sendMeetingMsg(MeetingMsgReqs meetingMsgReqs, List<ImgUploadResp> imgUploadList) {
        String FileUploader = "";
        if (imgUploadList.size() > 0) {
            for (ImgUploadResp imgResp : imgUploadList) {
                FileUploader = FileUploader + "^" + imgResp.getId() + "|" + imgResp.getName();
            }
        }


        mModel.sendMeetingMsg(meetingMsgReqs.getCM_name(),
                meetingMsgReqs.getCM_person(),
                meetingMsgReqs.getCM_promoter(),
                meetingMsgReqs.getCM_starttime(),
                meetingMsgReqs.getCM_room(),
                meetingMsgReqs.getSysPerson(),
                meetingMsgReqs.getCM_department(),
                meetingMsgReqs.getCM_content(),
                FileUploader,
                "发布",
                meetingMsgReqs.getRowsCount(),
                meetingMsgReqs.getRows())
                .map(new parseResponse<>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(() -> {
                    mRootView.hideUpload();//隐藏加载中
                }).observeOn(AndroidSchedulers.mainThread()).compose(RxLifecycleUtils.bindToLifecycle(mRootView)).subscribe(new ErrorHandleSubscriber<StaffNameIdKey>(mErrorHandler) {
            @Override
            public void onNext(@NonNull StaffNameIdKey linkManResp) {

                String id = linkManResp.getId();

                Log.e("sendMeetingMsg", id);
                mRootView.showMessage("创建成功");

            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mAppManager = null;
        this.mImageLoader = null;
        this.mApplication = null;
    }


}