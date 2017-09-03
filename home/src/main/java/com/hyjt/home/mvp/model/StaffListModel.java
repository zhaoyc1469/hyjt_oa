package com.hyjt.home.mvp.model;

import com.hyjt.frame.api.BaseJson;
import com.hyjt.frame.di.scope.ActivityScope;
import com.hyjt.frame.integration.IRepositoryManager;
import com.hyjt.frame.mvp.BaseModel;
import com.hyjt.home.mvp.contract.StaffListContract;
import com.hyjt.home.mvp.model.entity.Resp.ChildrenBean;
import com.hyjt.home.mvp.model.entity.Resp.StaffListResp;
import com.hyjt.home.mvp.model.service.HomeService;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;


@ActivityScope
public class StaffListModel extends BaseModel implements StaffListContract.Model {

    @Inject
    public StaffListModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public Observable<BaseJson<List<ChildrenBean>>> reqsDeptList() {
        Observable<BaseJson<List<ChildrenBean>>> deptList = mRepositoryManager.obtainRetrofitService(HomeService.class)
                .getDeptList();
        return deptList;
    }

    @Override
    public Observable<BaseJson<StaffListResp>> getStaffList(int Page, int RP, String State, String deptId, String Name, String edu, String startTime, String endTime) {
        Observable<BaseJson<StaffListResp>> getStaffList = mRepositoryManager.obtainRetrofitService(HomeService.class)
                .getStaffList(Page, RP, State, deptId, Name, edu, startTime, endTime);
        return getStaffList;
    }

}