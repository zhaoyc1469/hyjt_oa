package com.hyjt.home.mvp.contract;


import android.widget.EditText;

import com.hyjt.frame.api.BaseJson;
import com.hyjt.frame.mvp.IModel;
import com.hyjt.frame.mvp.IView;
import com.hyjt.home.mvp.model.entity.Reqs.BaseIdReqs;
import com.hyjt.home.mvp.model.entity.Reqs.BaseTypeReqs;
import com.hyjt.home.mvp.model.entity.Reqs.PlNodeApproveReqs;
import com.hyjt.home.mvp.model.entity.Reqs.PsonLoanCreateReqs;
import com.hyjt.home.mvp.model.entity.Resp.PLBankAccountResp;
import com.hyjt.home.mvp.model.entity.Resp.PLCompBankAccountResp;
import com.hyjt.home.mvp.model.entity.Resp.PLCompanyResp;
import com.hyjt.home.mvp.model.entity.Resp.PLFlowNodeResp;
import com.hyjt.home.mvp.model.entity.Resp.PLFristLeaderResp;
import com.hyjt.home.mvp.model.entity.Resp.PsonLoanDetailResp;

import io.reactivex.Observable;


public interface PsonLoanEditContract {

    interface View extends IView {

        void hideLoading();

        void showPLDetail(PsonLoanDetailResp psonLoanDetailResp);

        void loadFlowNode(PLFristLeaderResp plFristLeaderResp);

        void showAprBankAccount(PLCompBankAccountResp compBankAccountResp, EditText editText);
    }

    interface Model extends IModel {

        Observable<BaseJson<PsonLoanDetailResp>> getPLDetail(BaseIdReqs baseIdReqs);

        Observable<BaseJson<PLFristLeaderResp>> getFristLeader(BaseTypeReqs baseTypeReqs);

        Observable<BaseJson<PLBankAccountResp>> getBankAccount();

        Observable<BaseJson<PLCompanyResp>> getPLCompany();

        Observable<BaseJson<Object>> delPsonLoan(BaseIdReqs baseIdReqs);

        Observable<BaseJson<Object>> editPsonLoan(PsonLoanCreateReqs psonLoanCreateReqs);

        Observable<BaseJson<Object>> flowNodeApprove(PlNodeApproveReqs PlNodeApproveReqs);

        Observable<BaseJson<PLCompBankAccountResp>> compBankAccount();

        Observable<BaseJson<Object>> plTellerConfirm(BaseIdReqs baseIdReqs);

        Observable<BaseJson<Object>> plReceiverConfirm(BaseIdReqs baseIdReqs);
    }
}
