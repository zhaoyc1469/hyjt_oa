package com.hyjt.home.mvp.contract;


import com.hyjt.frame.api.BaseJson;
import com.hyjt.frame.mvp.IListView;
import com.hyjt.frame.mvp.IModel;
import com.hyjt.home.mvp.model.entity.Reqs.CIdeaListReqs;
import com.hyjt.home.mvp.model.entity.Reqs.LUReqsListReqs;
import com.hyjt.home.mvp.model.entity.Resp.CIdeaListResp;
import com.hyjt.home.mvp.model.entity.Resp.LUReqsListResp;
import com.hyjt.home.mvp.ui.adapter.LUAppealAdapter;

import io.reactivex.Observable;


public interface LaborUnionReqsListContract {

    interface View extends IListView {

        void setAdapter(LUAppealAdapter LUAppealAdapter);

        void startLoadMore();

        void endLoadMore();

        void endLoad();
    }

    interface Model extends IModel {

        Observable<BaseJson<LUReqsListResp>> getLuList(LUReqsListReqs LUReqsListReqs);

    }
}
