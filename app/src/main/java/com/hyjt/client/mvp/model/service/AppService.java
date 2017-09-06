package com.hyjt.client.mvp.model.service;

import com.hyjt.client.mvp.model.entity.LoginResp;
import com.hyjt.client.mvp.model.entity.WorkMission;
import com.hyjt.frame.api.BaseJson;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * @author 赵宇驰
 * @time 2017/7/11  21:44
 * @desc ${TODO}
 */

public interface AppService {

    @POST("/APIAccount")
    Observable<BaseJson<LoginResp>> getLoginResp(@Query("PersonName") String PersonName, @Query("Password") String Password, @Query("RememberMe") int RememberMe, @Query("Registration_ID") String registrationID);

    @GET("/APIConferencemanage/GetMission")
    Observable<BaseJson<WorkMission>> getMdMsgNum();

    @GET("/APIAccount/LogOff")
    Observable<BaseJson<Object>> outLogin();
}
