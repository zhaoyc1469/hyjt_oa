package com.hyjt.home.mvp.presenter;

import android.app.Application;
import android.widget.EditText;

import com.hyjt.frame.api.parseResponse;
import com.hyjt.frame.di.scope.ActivityScope;
import com.hyjt.frame.mvp.BasePresenter;
import com.hyjt.frame.integration.AppManager;


import com.hyjt.frame.widget.imageloader.ImageLoader;


import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

import javax.inject.Inject;

import com.hyjt.home.mvp.contract.ContributeIdeaCreateContract;
import com.hyjt.home.mvp.model.entity.Reqs.StaffNameIdKey;
import com.hyjt.home.mvp.model.entity.Resp.CIdeaDetailResp;
import com.hyjt.home.mvp.model.entity.Resp.LinkManResp;
import com.hyjt.home.mvp.model.entity.Resp.SReportDetailResp;

import java.util.List;


@ActivityScope
public class ContributeIdeaCreatePresenter extends BasePresenter<ContributeIdeaCreateContract.Model, ContributeIdeaCreateContract.View> {
    private RxErrorHandler mErrorHandler;
    private Application mApplication;
    private ImageLoader mImageLoader;
    private AppManager mAppManager;

    @Inject
    public ContributeIdeaCreatePresenter(ContributeIdeaCreateContract.Model model, ContributeIdeaCreateContract.View rootView
            , RxErrorHandler handler, Application application
            , ImageLoader imageLoader, AppManager appManager) {
        super(model, rootView);
        this.mErrorHandler = handler;
        this.mApplication = application;
        this.mImageLoader = imageLoader;
        this.mAppManager = appManager;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mAppManager = null;
        this.mImageLoader = null;
        this.mApplication = null;
    }

    public void getLinkmanMsg(String SysDepartment, EditText selEdit, StaffNameIdKey staffNameId, boolean moreCheck) {
        mModel.getLinkman("1", "9999", SysDepartment)
                .map(new parseResponse<>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ErrorHandleSubscriber<LinkManResp>(mErrorHandler) {
                    @Override
                    public void onNext(@NonNull LinkManResp linkManResp) {
                        LinkManResp.DataBean.RowsBean[] linkman = linkManResp.getData().getRows().toArray
                                (new LinkManResp.DataBean.RowsBean[linkManResp.getData().getRows().size()]);

                        String[] nameAry = new String[linkman.length];
                        String[] nameSendAry = new String[linkman.length];

//                        ^员工唯一码|姓名^
                        for (int i = 0; i < linkman.length; i++) {
                            LinkManResp.DataBean.RowsBean item = linkman[i];
                            List<String> cell = item.getCell();
                            nameAry[i] = cell.get(1);
                            nameSendAry[i] = cell.get(0);
                        }
                        mRootView.getLinkmanOk(nameAry, nameSendAry, selEdit, staffNameId, moreCheck);
                    }
                });
    }

    public void createContribute(CIdeaDetailResp cIdeaDetail){

        mModel.createContribute(cIdeaDetail)
                .map(new parseResponse<>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterTerminate(() -> {
                    mRootView.hideLoading();//隐藏
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ErrorHandleSubscriber<Object>(mErrorHandler) {
                    @Override
                    public void onNext(@NonNull Object obj) {
                        mRootView.showMessage("创建成功");
                        mRootView.killMyself();
                    }
                });
    }

}
