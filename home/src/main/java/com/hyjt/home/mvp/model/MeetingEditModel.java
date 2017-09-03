package com.hyjt.home.mvp.model;

import android.app.Application;
import android.util.Log;

import com.google.gson.Gson;
import com.hyjt.frame.api.BaseJson;
import com.hyjt.frame.di.scope.ActivityScope;
import com.hyjt.frame.integration.IRepositoryManager;
import com.hyjt.frame.mvp.BaseModel;
import com.hyjt.frame.utils.JsonUtils;
import com.hyjt.home.mvp.contract.MeetingEditContract;
import com.hyjt.home.mvp.model.entity.Reqs.MeetingEditReqs;
import com.hyjt.home.mvp.model.entity.Resp.ImgUploadResp;
import com.hyjt.home.mvp.model.entity.Resp.LinkManResp;
import com.hyjt.home.mvp.model.entity.Resp.MeetingMsgResp;
import com.hyjt.home.mvp.model.service.HomeService;

import javax.inject.Inject;

import io.reactivex.Observable;
import okhttp3.MultipartBody;


@ActivityScope
public class MeetingEditModel extends BaseModel implements MeetingEditContract.Model {
    private Gson mGson;
    private Application mApplication;

    @Inject
    public MeetingEditModel(IRepositoryManager repositoryManager, Gson gson, Application application) {
        super(repositoryManager);
        this.mGson = gson;
        this.mApplication = application;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

    @Override
    public Observable<BaseJson<MeetingMsgResp>> getMeetingMsg(String meetingId) {
        Observable<BaseJson<MeetingMsgResp>> meetingMsg = mRepositoryManager.obtainRetrofitService(HomeService.class)
                .getMeetingMsg(meetingId);
        return meetingMsg;
    }

    @Override
    public Observable<BaseJson<Object>> delMeetingMsg(String meetingId) {
        Observable<BaseJson<Object>> delMeetingMsg = mRepositoryManager.obtainRetrofitService(HomeService.class)
                .delMeetingMsg(meetingId);
        return delMeetingMsg;
    }

    @Override
    public Observable<BaseJson<ImgUploadResp>> imgUpload(MultipartBody.Part file) {
        Observable<BaseJson<ImgUploadResp>> imgUpload = mRepositoryManager.obtainRetrofitService(HomeService.class)
                .imgUpload(file);
        return imgUpload;
    }

    @Override
    public Observable<BaseJson<Object>> editMeetingMsg(MeetingEditReqs meetingEditReqs) {
        String id = meetingEditReqs.getId();
        String cm_num = meetingEditReqs.getCM_Num();
        String cm_name = meetingEditReqs.getCM_name();
        String cm_person = meetingEditReqs.getCM_person();
        String cm_promoter = meetingEditReqs.getCM_promoter();
        String cm_starttime = meetingEditReqs.getCM_starttime();
        String cm_room = meetingEditReqs.getCM_room();
        String sysPerson = meetingEditReqs.getSysPerson();
        String sysPersonOld = meetingEditReqs.getSysPersonOld();
        String cm_department = meetingEditReqs.getCM_department();
        String cm_content = meetingEditReqs.getCM_content();
        String fileUploader = meetingEditReqs.getFileUploader();
        String fileUploaderOld = meetingEditReqs.getFileUploaderOld();
        String cm_state = meetingEditReqs.getCM_State();
        String rowsCount = meetingEditReqs.getRowsCount();
        String rows = meetingEditReqs.getRows();

        Observable<BaseJson<Object>> editMeeting = mRepositoryManager.obtainRetrofitService(HomeService.class)
                .editMeetingMsg(id, cm_num, cm_name, cm_person, cm_promoter, cm_starttime, cm_room,
                        sysPerson, sysPersonOld, cm_department, cm_content,
                        fileUploader, fileUploaderOld, cm_state, rowsCount, rows);

        Log.e("httpRqst", JsonUtils.beanToJson(meetingEditReqs));

        return editMeeting;
    }

    @Override
    public Observable<BaseJson<LinkManResp>> getLinkman(String Page, String RP, String SysDepartment) {
        Observable<BaseJson<LinkManResp>> getLinkman = mRepositoryManager.obtainRetrofitService(HomeService.class)
                .getLinkman(Page, RP, SysDepartment);
        return getLinkman;
    }
}