package com.hyjt.home.mvp.contract;


import com.hyjt.frame.api.BaseJson;
import com.hyjt.frame.mvp.IListView;
import com.hyjt.frame.mvp.IModel;
import com.hyjt.frame.mvp.IView;
import com.hyjt.home.mvp.model.entity.Reqs.PsonLoanListReqs;
import com.hyjt.home.mvp.model.entity.Resp.PsonLoanListResp;
import com.hyjt.home.mvp.model.entity.Resp.ReportTDetailResp;
import com.hyjt.home.mvp.ui.adapter.LUAppealAdapter;
import com.hyjt.home.mvp.ui.adapter.PsonLoanAdapter;

import java.util.Optional;

import io.reactivex.Observable;


public interface PsonLoanListContract {
    interface View extends IListView {

        void setAdapter(PsonLoanAdapter PsonLoanAdapter);

        void startLoadMore();

        void endLoadMore();

        void endLoad();

        void showNoLimits();
    }

    interface Model extends IModel {

        Observable<BaseJson<PsonLoanListResp>> getPsonLoanList(PsonLoanListReqs psonLoanListReqs);
    }
}
