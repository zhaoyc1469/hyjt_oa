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
import com.hyjt.frame.widget.imageloader.ImageLoader;
import com.hyjt.home.mvp.contract.MeetingEditContract;
import com.hyjt.home.mvp.model.entity.Reqs.MeetingEditReqs;
import com.hyjt.home.mvp.model.entity.Reqs.StaffNameIdKey;
import com.hyjt.home.mvp.model.entity.Resp.ImgUploadResp;
import com.hyjt.home.mvp.model.entity.Resp.LinkManResp;
import com.hyjt.home.mvp.model.entity.Resp.MeetingMsgResp;
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
public class MeetingEditPresenter extends BasePresenter<MeetingEditContract.Model, MeetingEditContract.View> {
    private RxErrorHandler mErrorHandler;
    private Application mApplication;
    private ImageLoader mImageLoader;
    private AppManager mAppManager;

    @Inject
    public MeetingEditPresenter(MeetingEditContract.Model model, MeetingEditContract.View rootView
            , RxErrorHandler handler, Application application
            , ImageLoader imageLoader, AppManager appManager) {
        super(model, rootView);
        this.mErrorHandler = handler;
        this.mApplication = application;
        this.mImageLoader = imageLoader;
        this.mAppManager = appManager;
    }

    public void getMeetingMsg(String meetingId) {

        mModel.getMeetingMsg(meetingId)
                .map(new parseResponse<>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ErrorHandleSubscriber<MeetingMsgResp>(mErrorHandler) {
                    @Override
                    public void onNext(@NonNull MeetingMsgResp meetingMsgResp) {
                        mRootView.setMeetingMsg(meetingMsgResp);
                    }
                });

        Log.e("getMeetingMsg", meetingId);
    }


    public void getLinkmanMsg(String SysDepartment, EditText selEdit, StaffNameIdKey staffNameId, boolean moreCheck) {
        mModel.getLinkman("1", "9999", SysDepartment)
                .map(new parseResponse<>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
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
                            nameSendAry[i] = cell.get(0) + "|" + cell.get(1) + "^";
                        }

                        mRootView.getLinkmanOk(nameAry, nameSendAry, selEdit, staffNameId, moreCheck);
                    }
                });
    }

    public void delMeetingMsg(String meetingId) {
        mModel.delMeetingMsg(meetingId)
                .map(objectBaseJson -> {
                    if ("200".equals(objectBaseJson.getCode())) {
                        return "删除成功";
                    } else if ("201".equals(objectBaseJson.getCode())) {
                        return "删除其他";
                    } else {
                        return "删除失败";
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ErrorHandleSubscriber<String>(mErrorHandler) {
                    @Override
                    public void onNext(@NonNull String s) {
                        mRootView.showMessage(s);
                    }
                });
    }


    public void editMeetingMsg(MeetingEditReqs meetingEditReqs) {
        mModel.editMeetingMsg(meetingEditReqs)
                .map(new parseResponse<>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterTerminate(() -> mRootView.hideUpload())//隐藏上拉刷新的进度条
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ErrorHandleSubscriber<Object>(mErrorHandler) {
                    @Override
                    public void onNext(@NonNull Object Object) {
                        mRootView.showMessage("编辑成功");
                    }
                });
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
                            .doAfterTerminate(() -> {
                                if (ImgUploadList.size() == filesPaths.size() - 1) {
                                    mRootView.hideUpload();//隐藏
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


    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mAppManager = null;
        this.mImageLoader = null;
        this.mApplication = null;
    }

}