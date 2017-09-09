package com.hyjt.home.mvp.contract;


import com.hyjt.frame.api.BaseJson;
import com.hyjt.frame.mvp.IModel;
import com.hyjt.frame.mvp.IView;
import com.hyjt.home.mvp.model.entity.Reqs.BaseIdReqs;
import com.hyjt.home.mvp.model.entity.Resp.CIdeaDetailResp;
import com.hyjt.home.mvp.model.entity.Resp.SReportDetailResp;

import io.reactivex.Observable;


public interface ContributeIdeaDetailContract {

    interface View extends IView {

        void hideLoading();

        void setCIDetail(CIdeaDetailResp detail);
    }

    interface Model extends IModel {



        Observable<BaseJson<CIdeaDetailResp>> cIDetail(BaseIdReqs baseIdReqs);

        Observable<BaseJson<Object>> cIDel(BaseIdReqs baseIdReqs);

        Observable<BaseJson<Object>> cIEdit(CIdeaDetailResp detailResp);


    }
}
