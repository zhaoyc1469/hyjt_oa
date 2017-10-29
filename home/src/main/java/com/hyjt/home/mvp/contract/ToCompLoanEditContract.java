package com.hyjt.home.mvp.contract;


import android.widget.EditText;

import com.hyjt.frame.api.BaseJson;
import com.hyjt.frame.mvp.IModel;
import com.hyjt.frame.mvp.IView;
import com.hyjt.home.mvp.model.entity.Reqs.BaseIdReqs;
import com.hyjt.home.mvp.model.entity.Reqs.ClNodeApproveReqs;
import com.hyjt.home.mvp.model.entity.Reqs.PlNodeApproveReqs;
import com.hyjt.home.mvp.model.entity.Resp.CompLoanDetailResp;
import com.hyjt.home.mvp.model.entity.Resp.PLBankAccountResp;
import com.hyjt.home.mvp.model.entity.Resp.PLCompBankAccountResp;
import com.hyjt.home.mvp.model.entity.Resp.PsonLoanDetailResp;

import io.reactivex.Observable;

public interface ToCompLoanEditContract {
    //对于经常使用的关于UI的方法可以定义到IView中,如显示隐藏进度条,和显示文字消息
    interface View extends IView {

        void hideLoading();

        void showCLDetail(CompLoanDetailResp compLoanDetailResp);

        void showAprBankAccount(PLCompBankAccountResp compBankAccountResp, EditText editText);
    }

    //Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节,即是否使用缓存
    interface Model extends IModel {

        Observable<BaseJson<CompLoanDetailResp>> getCLDetail(BaseIdReqs baseIdReqs);

        Observable<BaseJson<Object>> delCLDetail(BaseIdReqs baseIdReqs);

        Observable<BaseJson<PLCompBankAccountResp>> compBankAccount();

        Observable<BaseJson<Object>> flowNodeApprove(ClNodeApproveReqs clNodeApproveReqs);

        Observable<BaseJson<Object>> clTellerConfirm(BaseIdReqs baseIdReqs);

        Observable<BaseJson<Object>> clReceiverConfirm(BaseIdReqs baseIdReqs);

    }
}
