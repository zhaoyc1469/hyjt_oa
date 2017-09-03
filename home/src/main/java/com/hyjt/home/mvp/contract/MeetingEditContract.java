package com.hyjt.home.mvp.contract;


import android.widget.EditText;

import com.hyjt.frame.api.BaseJson;
import com.hyjt.frame.mvp.IModel;
import com.hyjt.frame.mvp.IView;
import com.hyjt.home.mvp.model.entity.Reqs.MeetingEditReqs;
import com.hyjt.home.mvp.model.entity.Reqs.StaffNameIdKey;
import com.hyjt.home.mvp.model.entity.Resp.ImgUploadResp;
import com.hyjt.home.mvp.model.entity.Resp.LinkManResp;
import com.hyjt.home.mvp.model.entity.Resp.MeetingMsgResp;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;

public interface MeetingEditContract {
    //对于经常使用的关于UI的方法可以定义到BaseView中,如显示隐藏进度条,和显示文字消息
    interface View extends IView {
        //设置会议信息
        void setMeetingMsg(MeetingMsgResp meetingMsgResp);
        //得到联系人
        void getLinkmanOk(String[] nameAry, String[] nameSendAry, EditText selEdit, StaffNameIdKey staffNameId, boolean moreCheck);
        //删除会议纪要
        void delMeetingMsg(String meetingId);

        void hideUpload();

        void imgUploadOk(List<ImgUploadResp> imgUploadList);

        RxPermissions getRxPermissions();

    }

    //Model层定义接口,外部只需关心model返回的数据,无需关心内部细节,及是否使用缓存
    interface Model extends IModel {

        Observable<BaseJson<LinkManResp>> getLinkman(String Page, String RP, String SysDepartment);

        Observable<BaseJson<MeetingMsgResp>> getMeetingMsg(String meetingId);

        Observable<BaseJson<Object>> delMeetingMsg(String meetingId);

        Observable<BaseJson<ImgUploadResp>> imgUpload(MultipartBody.Part file);

        Observable<BaseJson<Object>> editMeetingMsg(MeetingEditReqs meetingEditReqs);

    }
}