package com.hyjt.home.mvp.contract;


import com.hyjt.frame.api.BaseJson;
import com.hyjt.frame.mvp.IModel;
import com.hyjt.frame.mvp.IView;
import com.hyjt.home.mvp.model.entity.Reqs.BaseIdReqs;
import com.hyjt.home.mvp.model.entity.Reqs.BaseTypeReqs;
import com.hyjt.home.mvp.model.entity.Reqs.PsonLoanCreateReqs;
import com.hyjt.home.mvp.model.entity.Resp.PLBankAccountResp;
import com.hyjt.home.mvp.model.entity.Resp.PLCompanyResp;
import com.hyjt.home.mvp.model.entity.Resp.PLFristLeaderResp;
import com.hyjt.home.mvp.model.entity.Resp.PsonLoanDetailResp;

import io.reactivex.Observable;


public interface PsonLoanEditContract {

    interface View extends IView {

        void hideLoading();

        void showPLDetail(PsonLoanDetailResp psonLoanDetailResp);

        void loadFlowNode(PLFristLeaderResp plFristLeaderResp);
    }

    interface Model extends IModel {

        Observable<BaseJson<PsonLoanDetailResp>> getPLDetail(BaseIdReqs baseIdReqs);

        Observable<BaseJson<PLFristLeaderResp>> getFristLeader(BaseTypeReqs baseTypeReqs);

        Observable<BaseJson<PLBankAccountResp>> getBankAccount();

        Observable<BaseJson<PLCompanyResp>> getPLCompany();

        Observable<BaseJson<Object>> delPsonLoan(BaseIdReqs baseIdReqs);

        Observable<BaseJson<Object>> editPsonLoan(PsonLoanCreateReqs psonLoanCreateReqs);
    }
}
