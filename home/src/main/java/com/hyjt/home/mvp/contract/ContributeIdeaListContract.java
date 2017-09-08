package com.hyjt.home.mvp.contract;


import com.hyjt.frame.api.BaseJson;
import com.hyjt.frame.mvp.IListView;
import com.hyjt.frame.mvp.IModel;
import com.hyjt.frame.mvp.IView;
import com.hyjt.home.mvp.model.entity.Reqs.CIdeaListReqs;
import com.hyjt.home.mvp.model.entity.Reqs.SReportListReqs;
import com.hyjt.home.mvp.model.entity.Resp.CIdeaListResp;
import com.hyjt.home.mvp.model.entity.Resp.SReportListResp;
import com.hyjt.home.mvp.ui.adapter.ContributeIAdapter;
import com.hyjt.home.mvp.ui.adapter.SkipReportAdapter;

import io.reactivex.Observable;


public interface ContributeIdeaListContract {

    interface View extends IListView {

        void setAdapter(ContributeIAdapter contributeIAdapter);

        void startLoadMore();

        void endLoadMore();

        void endLoad();
    }

    interface Model extends IModel {

        Observable<BaseJson<CIdeaListResp>> getIdeaList(CIdeaListReqs cIdeaListReqs);
    }
}
