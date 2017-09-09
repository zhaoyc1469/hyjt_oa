package com.hyjt.home.mvp.contract;


import com.hyjt.frame.api.BaseJson;
import com.hyjt.frame.mvp.IModel;
import com.hyjt.frame.mvp.IView;
import com.hyjt.home.mvp.model.entity.Reqs.BaseIdReqs;
import com.hyjt.home.mvp.model.entity.Resp.CIdeaDetailResp;
import com.hyjt.home.mvp.model.entity.Resp.LUReqsDetailResp;

import io.reactivex.Observable;


public interface LaborUnionReqsDetailContract {
    interface View extends IView {


        void hideLoading();

        void setLUDetail(LUReqsDetailResp detail);

    }

    interface Model extends IModel {



        Observable<BaseJson<LUReqsDetailResp>> LUDetail(BaseIdReqs baseIdReqs);

        Observable<BaseJson<Object>> LUDel(BaseIdReqs baseIdReqs);

        Observable<BaseJson<Object>> LUEdit(LUReqsDetailResp detailResp);

    }
}
