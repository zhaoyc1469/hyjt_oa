package com.hyjt.home.mvp.contract;


import android.widget.EditText;

import com.hyjt.frame.api.BaseJson;
import com.hyjt.frame.mvp.IModel;
import com.hyjt.frame.mvp.IView;
import com.hyjt.home.mvp.model.entity.Reqs.StaffNameIdKey;
import com.hyjt.home.mvp.model.entity.Resp.ImgUploadResp;
import com.hyjt.home.mvp.model.entity.Resp.LinkManResp;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;

public interface MeetingLogContract {
    //对于经常使用的关于UI的方法可以定义到BaseView中,如显示隐藏进度条,和显示文字消息
    interface View extends IView {

        void imgUploadOk(List<ImgUploadResp> imgUploadList);

        void hideUpload();

//        void getLinkmanOk(LinkManResp linkManResp);
        void getLinkmanOk(String[] nameAry, String[] nameSendAry, EditText selEdit, StaffNameIdKey staffNameId, boolean moreCheck);


        //申请权限
        RxPermissions getRxPermissions();

    }

    //Model层定义接口,外部只需关心model返回的数据,无需关心内部细节,及是否使用缓存
    interface Model extends IModel {

        Observable<BaseJson<LinkManResp>> getLinkman(String Page, String RP, String SysDepartment);

        Observable<BaseJson<ImgUploadResp>> imgUpload(MultipartBody.Part file);

        Observable<BaseJson<StaffNameIdKey>> sendMeetingMsg(String CM_name,
                                                            String CM_person,
                                                            String CM_promoter,
                                                            String CM_starttime,
                                                            String CM_room,
                                                            String SysPerson,
                                                            String CM_department,
                                                            String CM_content,
                                                            String FileUploader,
                                                            String CM_State,
                                                            String rowsCount,
                                                            String Rows);




//        Observable<BaseJson<staffNameId>> sendMeetingMsg(MeetingMsgReqs meetingMsgReqs);
    }
}