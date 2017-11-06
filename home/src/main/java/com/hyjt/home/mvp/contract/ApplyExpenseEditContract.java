package com.hyjt.home.mvp.contract;


import android.widget.EditText;

import com.hyjt.frame.api.BaseJson;
import com.hyjt.frame.mvp.IModel;
import com.hyjt.frame.mvp.IView;
import com.hyjt.home.mvp.model.entity.Reqs.BaseIdReqs;
import com.hyjt.home.mvp.model.entity.Reqs.BaseTypeReqs;
import com.hyjt.home.mvp.model.entity.Reqs.ClNodeApproveReqs;
import com.hyjt.home.mvp.model.entity.Resp.ApplyExpDetailResp;
import com.hyjt.home.mvp.model.entity.Resp.ApplyExpTypeResp;
import com.hyjt.home.mvp.model.entity.Resp.PLBankAccountResp;
import com.hyjt.home.mvp.model.entity.Resp.PLCompBankAccountResp;
import com.hyjt.home.mvp.model.entity.Resp.PLCompanyResp;
import com.hyjt.home.mvp.model.entity.Resp.PLFristLeaderResp;

import io.reactivex.Flowable;
import io.reactivex.Observable;


public interface ApplyExpenseEditContract {

    interface View extends IView {

        void hideLoading();

        void showLoading(String msg);

        void showAEDetail(ApplyExpDetailResp applyExpDetailResp);

        void showAprBankAccount(PLCompBankAccountResp compBankAccountResp, EditText editText);

        void loadFlowNode(PLFristLeaderResp plFristLeaderResp);

        void showCompanyList(PLCompanyResp plCompanyResp);

        void showExpTypeList(ApplyExpTypeResp applyExpTypeResp);
    }

    interface Model extends IModel {


        Observable<BaseJson<ApplyExpDetailResp>> getApplyExpDetail(BaseIdReqs baseIdReqs);

        Observable<BaseJson<PLCompBankAccountResp>> applyExpBankAccount();

        Observable<BaseJson<Object>> flowNodeApprove(ClNodeApproveReqs clNodeApproveReqs);

        Observable<BaseJson<PLFristLeaderResp>> getFristLeader(BaseTypeReqs baseTypeReqs);

        Observable<BaseJson<ApplyExpTypeResp>> getApplyExpTypeList();

        Observable<BaseJson<PLCompanyResp>> getCompany();

        Observable<BaseJson<Object>> aeTellerConfirm(BaseIdReqs baseIdReqs);

        Observable<BaseJson<Object>> aeReceiverConfirm(BaseIdReqs baseIdReqs);

    }
}