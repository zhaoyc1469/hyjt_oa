package com.hyjt.home.mvp.contract;

import com.hyjt.frame.api.BaseJson;
import com.hyjt.frame.mvp.IModel;
import com.hyjt.frame.mvp.IView;
import com.hyjt.home.mvp.model.entity.Resp.CEmailDetailResp;

import io.reactivex.Observable;

public interface EmailDetailsContract {
    //对于经常使用的关于UI的方法可以定义到BaseView中,如显示隐藏进度条,和显示文字消息
    interface View extends IView {

        void setEmailDetail(CEmailDetailResp emailDetail);

        void hideLoading();

//        void getLinkmanOk(String[] nameAry, String[] nameSendAry, staffNameId staffNameId, boolean moreCheck);
    }

    //Model层定义接口,外部只需关心model返回的数据,无需关心内部细节,及是否使用缓存
    interface Model extends IModel {

        Observable<BaseJson<CEmailDetailResp>> getEmailDetails(String Id);

    }
}