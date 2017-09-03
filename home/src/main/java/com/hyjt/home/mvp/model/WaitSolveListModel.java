package com.hyjt.home.mvp.model;

import com.hyjt.frame.api.BaseJson;
import com.hyjt.frame.di.scope.ActivityScope;
import com.hyjt.frame.integration.IRepositoryManager;
import com.hyjt.frame.mvp.BaseModel;
import com.hyjt.home.mvp.contract.WaitSolveListContract;
import com.hyjt.home.mvp.model.entity.Resp.MeetingListResp;
import com.hyjt.home.mvp.model.service.HomeService;

import javax.inject.Inject;

import io.reactivex.Observable;


@ActivityScope
public class WaitSolveListModel extends BaseModel implements WaitSolveListContract.Model {


    @Inject
    public WaitSolveListModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public Observable<BaseJson<MeetingListResp>> getWSolveList(int Page, int Rows, String CM_Num, String CM_name, String CM_promoter) {
        Observable<BaseJson<MeetingListResp>> meetingList = mRepositoryManager.obtainRetrofitService(HomeService.class)
                .getWSolveList(Page, Rows, CM_Num, CM_name, CM_promoter);
        return meetingList;
    }
}