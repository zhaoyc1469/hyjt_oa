package com.hyjt.home.mvp.contract;


import com.hyjt.frame.api.BaseJson;
import com.hyjt.frame.mvp.IListView;
import com.hyjt.frame.mvp.IModel;
import com.hyjt.frame.mvp.IView;
import com.hyjt.home.mvp.model.entity.Reqs.ApplyExpenseListReqs;
import com.hyjt.home.mvp.model.entity.Reqs.CompLoanListReqs;
import com.hyjt.home.mvp.model.entity.Resp.ApplyExpenseListResp;
import com.hyjt.home.mvp.model.entity.Resp.CompLoanListResp;
import com.hyjt.home.mvp.ui.adapter.ApplyExpenseAdapter;

import io.reactivex.Observable;


public interface ApplyExpenseListContract {

    interface View extends IListView {
        void setAdapter(ApplyExpenseAdapter mAdapter);

        void startLoadMore();

        void endLoadMore();

        void endLoad();

        void showNoLimits();
    }

    interface Model extends IModel {

        Observable<BaseJson<ApplyExpenseListResp>> getApplyExpList(ApplyExpenseListReqs applyExpenseListReqs);
    }
}
