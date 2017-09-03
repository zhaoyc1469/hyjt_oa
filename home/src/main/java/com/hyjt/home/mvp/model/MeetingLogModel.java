package com.hyjt.home.mvp.model;

import android.app.Application;

import com.google.gson.Gson;
import com.hyjt.frame.api.BaseJson;
import com.hyjt.frame.di.scope.ActivityScope;
import com.hyjt.frame.integration.IRepositoryManager;
import com.hyjt.frame.mvp.BaseModel;
import com.hyjt.home.mvp.contract.MeetingLogContract;
import com.hyjt.home.mvp.model.entity.Reqs.StaffNameIdKey;
import com.hyjt.home.mvp.model.entity.Resp.ImgUploadResp;
import com.hyjt.home.mvp.model.entity.Resp.LinkManResp;
import com.hyjt.home.mvp.model.service.HomeService;

import javax.inject.Inject;

import io.reactivex.Observable;
import okhttp3.MultipartBody;


@ActivityScope
public class MeetingLogModel extends BaseModel implements MeetingLogContract.Model {
    private Gson mGson;
    private Application mApplication;

    @Inject
    public MeetingLogModel(IRepositoryManager repositoryManager, Gson gson, Application application) {
        super(repositoryManager);
        this.mGson = gson;
        this.mApplication = application;
    }

    @Override
    public Observable<BaseJson<ImgUploadResp>> imgUpload(MultipartBody.Part file) {
        Observable<BaseJson<ImgUploadResp>> imgUpload = mRepositoryManager.obtainRetrofitService(HomeService.class)
                .imgUpload(file);
        return imgUpload;
    }

    @Override
    public Observable<BaseJson<StaffNameIdKey>> sendMeetingMsg(String CM_name, String CM_person, String CM_promoter, String CM_starttime,
                                                               String CM_room, String SysPerson, String CM_department, String CM_content,
                                                               String FileUploader, String CM_State, String rowsCount, String Rows) {
        Observable<BaseJson<StaffNameIdKey>> sendMeetingMsg = mRepositoryManager.obtainRetrofitService(HomeService.class)
                .sendMeetingMsg(CM_name, CM_person, CM_promoter, CM_starttime, CM_room, SysPerson, CM_department, CM_content, FileUploader,
                        CM_State, rowsCount, Rows);



        return sendMeetingMsg;
    }

//    @Override
//    public Observable<BaseJson<staffNameId>> sendMeetingMsg(MeetingMsgReqs meetingMsgReqs) {
//        Observable<BaseJson<staffNameId>> sendMeetingMsg = mRepositoryManager.obtainRetrofitService(HomeService.class)
//                .sendMeetingMsg(meetingMsgReqs);
//        return sendMeetingMsg;
//    }

    @Override
    public Observable<BaseJson<LinkManResp>> getLinkman(String Page, String RP, String SysDepartment) {
        Observable<BaseJson<LinkManResp>> getLinkman = mRepositoryManager.obtainRetrofitService(HomeService.class)
                .getLinkman(Page, RP, SysDepartment);
        return getLinkman;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

}