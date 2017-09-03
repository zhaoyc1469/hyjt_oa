package com.hyjt.home.mvp.model;

import android.util.Log;

import com.google.gson.Gson;
import com.hyjt.frame.api.BaseJson;
import com.hyjt.frame.di.scope.ActivityScope;
import com.hyjt.frame.integration.IRepositoryManager;
import com.hyjt.frame.mvp.BaseModel;
import com.hyjt.home.mvp.contract.StaffMsgContract;
import com.hyjt.home.mvp.model.entity.Resp.ChildrenBean;
import com.hyjt.home.mvp.model.entity.Resp.StaffMsgResp;
import com.hyjt.home.mvp.model.service.HomeService;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;


@ActivityScope
public class StaffMsgModel extends BaseModel implements StaffMsgContract.Model {
    private Gson mGson;

    @Inject
    public StaffMsgModel(IRepositoryManager repositoryManager, Gson gson) {
        super(repositoryManager);
        this.mGson = gson;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
    }

    @Override
    public Observable<BaseJson<StaffMsgResp>> getStaffMsg(String Id) {
        Observable<BaseJson<StaffMsgResp>> getStaffMsg = mRepositoryManager.obtainRetrofitService(HomeService.class)
                .getStaffMsg(Id);
        return getStaffMsg;
    }

    @Override
    public Observable<BaseJson<Object>> delStaffMsg(String Id) {
        Observable<BaseJson<Object>> delStaffMsg = mRepositoryManager.obtainRetrofitService(HomeService.class)
                .delStaffMsg(Id);
        return delStaffMsg;
    }

    @Override
    public Observable<BaseJson<Object>> editStaffMsg(StaffMsgResp staffMsgResp) {
        Log.e("http_staffMsgResp", staffMsgResp.toString());

        Log.e("http_EduPack", mGson.toJson(staffMsgResp.getEduPack()));
        Log.e("http_WorkExpPack", mGson.toJson(staffMsgResp.getWorkPack()));
        Log.e("http_FilePack", mGson.toJson(staffMsgResp.getFilePack()));
        Observable<BaseJson<Object>> editStaffMsg = mRepositoryManager.obtainRetrofitService(HomeService.class)
                .editStaffMsg(staffMsgResp.getId(),
                        staffMsgResp.getDepartment(),
                        staffMsgResp.getName(),
                        staffMsgResp.getE_sex(),
                        staffMsgResp.getE_birth(),
                        staffMsgResp.getE_nation(),
                        staffMsgResp.getE_relation(),
                        staffMsgResp.getE_marry(),
                        staffMsgResp.getE_family(),
                        staffMsgResp.getE_ffamily(),
                        staffMsgResp.getE_address(),
                        staffMsgResp.getE_card(),
                        staffMsgResp.getE_langu(),
                        staffMsgResp.getE_compu(),
                        staffMsgResp.getState(),
                        staffMsgResp.getE_post(),
                        staffMsgResp.getE_postion(),
                        staffMsgResp.getE_propost(),
                        staffMsgResp.getE_workdate(),
                        staffMsgResp.getE_state(),
                        staffMsgResp.getE_stay(),
                        staffMsgResp.getE_doc(),
                        staffMsgResp.getE_singtime(),
                        staffMsgResp.getE_maturetime(),
                        staffMsgResp.getE_insure(),
                        staffMsgResp.getE_insureL(),
                        staffMsgResp.getMobilePhoneNumber(),
                        staffMsgResp.getE_innerPhone(),
                        staffMsgResp.getPhoneNumber(),
                        staffMsgResp.getE_QQ(),
                        staffMsgResp.getEmailAddress(),
                        staffMsgResp.getRemark(),
                        staffMsgResp.getQzy(),
                        staffMsgResp.getZzrange(),
                        staffMsgResp.getFamilyRelation(),
                        staffMsgResp.getLefttime(),
                        mGson.toJson(staffMsgResp.getEduPack()),
                        mGson.toJson(staffMsgResp.getWorkPack()),
                        mGson.toJson(staffMsgResp.getFilePack()));
        return editStaffMsg;
    }

    @Override
    public Observable<BaseJson<List<ChildrenBean>>> reqsDeptList() {
        Observable<BaseJson<List<ChildrenBean>>> deptList = mRepositoryManager.obtainRetrofitService(HomeService.class)
                .getDeptList();
        return deptList;
    }
}