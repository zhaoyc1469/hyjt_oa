package com.hyjt.home.mvp.model;


import com.hyjt.frame.api.BaseJson;
import com.hyjt.frame.di.scope.ActivityScope;
import com.hyjt.frame.integration.IRepositoryManager;
import com.hyjt.frame.mvp.BaseModel;
import com.hyjt.home.mvp.contract.SuperviseSolveListContract;
import com.hyjt.home.mvp.model.entity.Resp.MeetingListResp;
import com.hyjt.home.mvp.model.service.HomeService;

import javax.inject.Inject;

import io.reactivex.Observable;

@ActivityScope
public class SuperviseSolveListModel extends BaseModel implements SuperviseSolveListContract.Model {


    @Inject
    public SuperviseSolveListModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public Observable<BaseJson<MeetingListResp>> getSSolveList(int Page, int Rows, String CM_Num, String CM_name, String CM_promoter) {
        Observable<BaseJson<MeetingListResp>> meetingList = mRepositoryManager.obtainRetrofitService(HomeService.class)
                .getSSolveList(Page, Rows, CM_Num, CM_name, CM_promoter);
        return meetingList;
    }
}