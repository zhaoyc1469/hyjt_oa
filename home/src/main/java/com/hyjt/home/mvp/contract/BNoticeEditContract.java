package com.hyjt.home.mvp.contract;

import com.hyjt.frame.api.BaseJson;
import com.hyjt.frame.mvp.IModel;
import com.hyjt.frame.mvp.IView;
import com.hyjt.home.mvp.model.entity.Resp.BNoticeDetailsResp;
import com.hyjt.home.mvp.model.entity.Resp.ImgUploadResp;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;

public interface BNoticeEditContract {
    //对于经常使用的关于UI的方法可以定义到BaseView中,如显示隐藏进度条,和显示文字消息
    interface View extends IView {

        void showLoading();

        void hideLoading();

        void setBNotice(BNoticeDetailsResp bNoticeDetails);

        void fileUploadOk(List<ImgUploadResp> imgUploadList);

        RxPermissions getRxPermissions();
    }

    //Model层定义接口,外部只需关心model返回的数据,无需关心内部细节,及是否使用缓存
    interface Model extends IModel {

        Observable<BaseJson<BNoticeDetailsResp>> getBNoticeDetail(String Id);

        Observable<BaseJson<Object>> delBNotice(String Id);

        Observable<BaseJson<Object>> editBNotice(BNoticeDetailsResp bNoticeDetailsResp);

        Observable<BaseJson<ImgUploadResp>> fileUpload(MultipartBody.Part filePart);
    }
}