package com.hyjt.home.mvp.contract;


import android.widget.EditText;

import com.hyjt.frame.api.BaseJson;
import com.hyjt.frame.mvp.IModel;
import com.hyjt.frame.mvp.IView;
import com.hyjt.home.mvp.model.entity.Reqs.ApplyExpCreateReqs;
import com.hyjt.home.mvp.model.entity.Reqs.BaseNumReqs;
import com.hyjt.home.mvp.model.entity.Reqs.BaseTypeReqs;
import com.hyjt.home.mvp.model.entity.Reqs.CompLoanListReqs;
import com.hyjt.home.mvp.model.entity.Reqs.PsonLoanCreateReqs;
import com.hyjt.home.mvp.model.entity.Reqs.PsonLoanListReqs;
import com.hyjt.home.mvp.model.entity.Resp.AEExpMoneyResp;
import com.hyjt.home.mvp.model.entity.Resp.ApplyExpTypeResp;
import com.hyjt.home.mvp.model.entity.Resp.CompLoanListResp;
import com.hyjt.home.mvp.model.entity.Resp.ImgUploadResp;
import com.hyjt.home.mvp.model.entity.Resp.PLBankAccountResp;
import com.hyjt.home.mvp.model.entity.Resp.PLCompanyResp;
import com.hyjt.home.mvp.model.entity.Resp.PLFristLeaderResp;
import com.hyjt.home.mvp.model.entity.Resp.PsonLoanListResp;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;


public interface ApplyExpenseCreateContract {


    interface View extends IView {

        void showLoading(String s);

        void hideLoading();

        void showCompanyList(PLCompanyResp plCompanyResp);

        void loadFlowNode(PLFristLeaderResp plFristLeaderResp);

        void showExpTypeList(ApplyExpTypeResp applyExpTypeResp);

        void fileUploadOk(List<ImgUploadResp> imgUploadList);

        RxPermissions getRxPermissions();

        void showExpCMoneyList(CompLoanListResp compLoanListResp, EditText edtWriteoffNum, EditText mEdtWriteoffNum, EditText mEdtBorrowMoney, EditText mEdtWriteoffMoney);

        void showExpPMoneyList(PsonLoanListResp psonLoanListResp, EditText edtWriteoffNum, EditText mEdtWriteoffNum, EditText mEdtBorrowMoney, EditText mEdtWriteoffMoney);

        void showExpMoney(AEExpMoneyResp aeexpMoneyResp, EditText mEdtPayed, EditText payed);

        void showBankAccount(PLBankAccountResp plBankAccountResp, EditText mEdtAccountName, EditText mEdtOpenactBank, EditText mEdtBankAccount);
    }


    interface Model extends IModel {


        Observable<BaseJson<PLFristLeaderResp>> getFristLeader(BaseTypeReqs baseTypeReqs);

        Observable<BaseJson<ApplyExpTypeResp>> getApplyExpTypeList();

        Observable<BaseJson<PLCompanyResp>> getCompany();

        Observable<BaseJson<ImgUploadResp>> fileUpload(MultipartBody.Part filePart);

        Observable<BaseJson<Object>> createApplyExp(ApplyExpCreateReqs applyExpCreateReqs);

        Observable<BaseJson<PsonLoanListResp>> getPsonLoanList(PsonLoanListReqs psonLoanListReqs);

        Observable<BaseJson<CompLoanListResp>> getCompLoanList(CompLoanListReqs compLoanListReqs);

        Observable<BaseJson<AEExpMoneyResp>> getExpMoney(BaseNumReqs baseNumReqs);

        Observable<BaseJson<PLBankAccountResp>> getReceiveBank();
    }
}
