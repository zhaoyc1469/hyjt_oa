package com.hyjt.home.mvp.contract;


import com.hyjt.frame.api.BaseJson;
import com.hyjt.frame.mvp.IModel;
import com.hyjt.frame.mvp.IView;
import com.hyjt.home.mvp.model.entity.Reqs.BaseIdReqs;
import com.hyjt.home.mvp.model.entity.Reqs.BaseTypeReqs;
import com.hyjt.home.mvp.model.entity.Reqs.CompLoanContractReqs;
import com.hyjt.home.mvp.model.entity.Reqs.CompLoanCreateReqs;
import com.hyjt.home.mvp.model.entity.Resp.CompLoanDetailResp;
import com.hyjt.home.mvp.model.entity.Resp.CpContractListResp;
import com.hyjt.home.mvp.model.entity.Resp.CpSupplierListResp;
import com.hyjt.home.mvp.model.entity.Resp.ImgUploadResp;
import com.hyjt.home.mvp.model.entity.Resp.PLBankAccountResp;
import com.hyjt.home.mvp.model.entity.Resp.PLCompanyResp;
import com.hyjt.home.mvp.model.entity.Resp.PLFlowNodeResp;
import com.hyjt.home.mvp.model.entity.Resp.PLFristLeaderResp;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;

public interface ToCompLoanCreateContract {
    //对于经常使用的关于UI的方法可以定义到IView中,如显示隐藏进度条,和显示文字消息
    interface View extends IView {

        void hideLoading();

        void showContractList(CpContractListResp cpContractListResp);

        void fileUploadOk(List<ImgUploadResp> imgUploadList);

        RxPermissions getRxPermissions();

//        void showBankAccount(PLBankAccountResp plBankAccountResp);

        void showCompanyList(PLCompanyResp plCompanyResp);

        void loadFlowNode(PLFristLeaderResp plFristLeaderResp);

        void showSupplierList(CpSupplierListResp cpSupplierListResp);
    }

    //Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节,即是否使用缓存
    interface Model extends IModel {

        Observable<BaseJson<CpContractListResp>> getCLContractList(CompLoanContractReqs compLoanContractReqs);

        Observable<BaseJson<Object>> CompLoanCreate(CompLoanCreateReqs compLoanCreateReqs);

        Observable<BaseJson<PLFristLeaderResp>> getFristLeader(BaseTypeReqs baseTypeReqs);

        Observable<BaseJson<PLBankAccountResp>> getBankAccount();

        Observable<BaseJson<PLCompanyResp>> getTCLCompany();

        Observable<BaseJson<ImgUploadResp>> fileUpload(MultipartBody.Part filePart);

        Observable<BaseJson<CpSupplierListResp>> getCLSupplierList(CompLoanContractReqs compLoanContractReqs);
    }
}
