package com.hyjt.home.mvp.contract;


import com.hyjt.frame.api.BaseJson;
import com.hyjt.frame.mvp.IModel;
import com.hyjt.frame.mvp.IView;
import com.hyjt.home.mvp.model.entity.Reqs.CIdeaListReqs;
import com.hyjt.home.mvp.model.entity.Reqs.SReportListReqs;
import com.hyjt.home.mvp.model.entity.Resp.CIdeaListResp;
import com.hyjt.home.mvp.model.entity.Resp.SReportListResp;

import io.reactivex.Observable;


public interface ContributeIdeaListContract {
    interface View extends IView {

    }

    interface Model extends IModel {

        Observable<BaseJson<CIdeaListResp>> getIdeaList(CIdeaListReqs cIdeaListReqs);
    }
}
