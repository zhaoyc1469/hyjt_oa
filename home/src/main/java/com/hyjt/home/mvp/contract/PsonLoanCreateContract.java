package com.hyjt.home.mvp.contract;


import android.widget.EditText;

import com.hyjt.frame.api.BaseJson;
import com.hyjt.frame.mvp.IModel;
import com.hyjt.frame.mvp.IView;
import com.hyjt.home.mvp.model.entity.Reqs.BaseTypeReqs;
import com.hyjt.home.mvp.model.entity.Reqs.PsonLoanCreateReqs;
import com.hyjt.home.mvp.model.entity.Reqs.StaffNameIdKey;
import com.hyjt.home.mvp.model.entity.Resp.ChildrenBean;
import com.hyjt.home.mvp.model.entity.Resp.ImgUploadResp;
import com.hyjt.home.mvp.model.entity.Resp.LinkManResp;
import com.hyjt.home.mvp.model.entity.Resp.PLBankAccountResp;
import com.hyjt.home.mvp.model.entity.Resp.PLCompanyResp;
import com.hyjt.home.mvp.model.entity.Resp.PLFlowNodeResp;
import com.hyjt.home.mvp.model.entity.Resp.PLFristLeaderResp;
import com.hyjt.home.mvp.model.entity.Resp.PsonLoanDetailResp;
import com.hyjt.home.mvp.ui.view.treelistview.Node;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;


public interface PsonLoanCreateContract {

    interface View extends IView {

        RxPermissions getRxPermissions();

        void hideLoading();

        void loadFlowNode(PLFristLeaderResp plFristLeaderResp);

        void showCompanyList(PLCompanyResp plCompanyResp);

        void showBankAccount(PLBankAccountResp plBankAccountResp);

        void fileUploadOk(List<ImgUploadResp> imgUploadList);
    }

    interface Model extends IModel {

        Observable<BaseJson<PLFristLeaderResp>> getFristLeader(BaseTypeReqs baseTypeReqs);

        Observable<BaseJson<PLBankAccountResp>> getBankAccount();

        Observable<BaseJson<PLFlowNodeResp>> getFlowNode();

        Observable<BaseJson<PLCompanyResp>> getPLCompany();

        Observable<BaseJson<Object>> createPsonLoan(PsonLoanCreateReqs psonLoanDetailResp);

        Observable<BaseJson<ImgUploadResp>> fileUpload(MultipartBody.Part filePart);
    }
}
