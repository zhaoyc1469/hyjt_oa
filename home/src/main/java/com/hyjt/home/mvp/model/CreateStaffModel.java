package com.hyjt.home.mvp.model;

import android.util.Log;

import com.google.gson.Gson;
import com.hyjt.frame.api.BaseJson;
import com.hyjt.frame.di.scope.ActivityScope;
import com.hyjt.frame.integration.IRepositoryManager;
import com.hyjt.frame.mvp.BaseModel;
import com.hyjt.home.mvp.contract.CreateStaffContract;
import com.hyjt.home.mvp.model.entity.Reqs.StaffMsgReqs;
import com.hyjt.home.mvp.model.entity.Resp.ChildrenBean;
import com.hyjt.home.mvp.model.service.HomeService;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;


@ActivityScope
public class CreateStaffModel extends BaseModel implements CreateStaffContract.Model {
    private Gson mGson;

    @Inject
    public CreateStaffModel(IRepositoryManager repositoryManager, Gson gson) {
        super(repositoryManager);
        this.mGson = gson;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
    }

    @Override
    public Observable<BaseJson<List<ChildrenBean>>> reqsDeptList() {
        Observable<BaseJson<List<ChildrenBean>>> deptList = mRepositoryManager.obtainRetrofitService(HomeService.class)
                .getDeptList();
        return deptList;
    }




    @Override
    public Observable<BaseJson<Object>> createStaffMsg(StaffMsgReqs staffMsgReqs) {
        Log.e("http_staff", staffMsgReqs.toString());

        Log.e("http_staff", mGson.toJson(staffMsgReqs.getEduPack()));
        Log.e("http_staff", mGson.toJson(staffMsgReqs.getWorkExpPack()));
        Observable<BaseJson<Object>> createStaff = mRepositoryManager.obtainRetrofitService(HomeService.class)
                .createStaff(staffMsgReqs.getDepartment(),
                        staffMsgReqs.getName(),
                        staffMsgReqs.getE_sex(),
                        staffMsgReqs.getE_birth(),
                        staffMsgReqs.getE_nation(),
                        staffMsgReqs.getE_relation(),
                        staffMsgReqs.getE_marry(),
                        staffMsgReqs.getE_family(),
                        staffMsgReqs.getE_ffamily(),
                        staffMsgReqs.getE_address(),
                        staffMsgReqs.getE_card(),
                        staffMsgReqs.getE_langu(),
                        staffMsgReqs.getE_compu(),
                        staffMsgReqs.getState(),
                        staffMsgReqs.getE_post(),
                        staffMsgReqs.getE_postion(),
                        staffMsgReqs.getE_propost(),
                        staffMsgReqs.getE_workdate(),
                        staffMsgReqs.getE_state(),
                        staffMsgReqs.getE_stay(),
                        staffMsgReqs.getE_doc(),
                        staffMsgReqs.getE_singtime(),
                        staffMsgReqs.getE_maturetime(),
                        staffMsgReqs.getE_insure(),
                        staffMsgReqs.getE_insureL(),
                        staffMsgReqs.getMobilePhoneNumber(),
                        staffMsgReqs.getE_innerPhone(),
                        staffMsgReqs.getPhoneNumber(),
                        staffMsgReqs.getE_QQ(),
                        staffMsgReqs.getEmailAddress(),
                        staffMsgReqs.getRemark(),
                        staffMsgReqs.getQzy(),
                        staffMsgReqs.getZzrange(),
                        staffMsgReqs.getFamilyRelation(),
                        staffMsgReqs.getLefttime(),
                        mGson.toJson(staffMsgReqs.getEduPack()),
                        mGson.toJson(staffMsgReqs.getWorkExpPack()));
        return createStaff;
    }
}