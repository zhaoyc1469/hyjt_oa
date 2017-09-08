package com.hyjt.home.mvp.contract;


import android.widget.EditText;

import com.hyjt.frame.api.BaseJson;
import com.hyjt.frame.mvp.IModel;
import com.hyjt.frame.mvp.IView;
import com.hyjt.home.mvp.model.entity.Reqs.StaffNameIdKey;
import com.hyjt.home.mvp.model.entity.Resp.CIdeaDetailResp;
import com.hyjt.home.mvp.model.entity.Resp.LUReqsDetailResp;
import com.hyjt.home.mvp.model.entity.Resp.LinkManResp;

import io.reactivex.Observable;


public interface LaborUnionReqsCreateContract {

    interface View extends IView {

        void hideLoading();

        void getLinkmanOk(String[] nameAry, String[] nameSendAry, EditText selEdit, StaffNameIdKey staffNameId, boolean moreCheck);

    }

    interface Model extends IModel {

        Observable<BaseJson<LinkManResp>> getLinkman(String Page, String RP, String SysDepartment);

        Observable<BaseJson<Object>> createLUReqs(LUReqsDetailResp LUReqsDetailResp);
    }
}
