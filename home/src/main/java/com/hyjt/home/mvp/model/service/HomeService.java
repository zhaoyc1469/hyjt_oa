package com.hyjt.home.mvp.model.service;

import com.hyjt.frame.api.BaseJson;
import com.hyjt.home.mvp.model.entity.Reqs.BaseTypeReqs;
import com.hyjt.home.mvp.model.entity.Reqs.CompLoanContractReqs;
import com.hyjt.home.mvp.model.entity.Reqs.CompLoanCreateReqs;
import com.hyjt.home.mvp.model.entity.Reqs.CompLoanListReqs;
import com.hyjt.home.mvp.model.entity.Reqs.LUReqsListReqs;
import com.hyjt.home.mvp.model.entity.Reqs.PlNodeApproveReqs;
import com.hyjt.home.mvp.model.entity.Reqs.PsonLoanCreateReqs;
import com.hyjt.home.mvp.model.entity.Reqs.PsonLoanListReqs;
import com.hyjt.home.mvp.model.entity.Resp.CIdeaDetailResp;
import com.hyjt.home.mvp.model.entity.Resp.CIdeaListResp;
import com.hyjt.home.mvp.model.entity.Reqs.BaseIdReqs;
import com.hyjt.home.mvp.model.entity.Reqs.CEmailSendReqs;
import com.hyjt.home.mvp.model.entity.Reqs.CIdeaListReqs;
import com.hyjt.home.mvp.model.entity.Reqs.EmailListReqs;
import com.hyjt.home.mvp.model.entity.Reqs.ReportTopListReqs;
import com.hyjt.home.mvp.model.entity.Reqs.SLConsultListReqs;
import com.hyjt.home.mvp.model.entity.Reqs.SReportListReqs;
import com.hyjt.home.mvp.model.entity.Reqs.StaffNameIdKey;
import com.hyjt.home.mvp.model.entity.Resp.BNoticeDetailsResp;
import com.hyjt.home.mvp.model.entity.Resp.BlocNoticeListResp;
import com.hyjt.home.mvp.model.entity.Resp.CEmailDetailResp;
import com.hyjt.home.mvp.model.entity.Resp.CEmailListResp;
import com.hyjt.home.mvp.model.entity.Resp.ChildrenBean;
import com.hyjt.home.mvp.model.entity.Resp.CompLoanDetailResp;
import com.hyjt.home.mvp.model.entity.Resp.CompLoanListResp;
import com.hyjt.home.mvp.model.entity.Resp.CpContractListResp;
import com.hyjt.home.mvp.model.entity.Resp.ImgUploadResp;
import com.hyjt.home.mvp.model.entity.Resp.LUReqsDetailResp;
import com.hyjt.home.mvp.model.entity.Resp.LUReqsListResp;
import com.hyjt.home.mvp.model.entity.Resp.LinkManResp;
import com.hyjt.home.mvp.model.entity.Resp.MeetingListResp;
import com.hyjt.home.mvp.model.entity.Resp.MeetingMsgResp;
import com.hyjt.home.mvp.model.entity.Resp.PLBankAccountResp;
import com.hyjt.home.mvp.model.entity.Resp.PLCompBankAccountResp;
import com.hyjt.home.mvp.model.entity.Resp.PLCompanyResp;
import com.hyjt.home.mvp.model.entity.Resp.PLFlowNodeResp;
import com.hyjt.home.mvp.model.entity.Resp.PLFristLeaderResp;
import com.hyjt.home.mvp.model.entity.Resp.PsonLoanDetailResp;
import com.hyjt.home.mvp.model.entity.Resp.PsonLoanListResp;
import com.hyjt.home.mvp.model.entity.Resp.ReportTDetailResp;
import com.hyjt.home.mvp.model.entity.Resp.ReportTListResp;
import com.hyjt.home.mvp.model.entity.Resp.SLConsultDetailResp;
import com.hyjt.home.mvp.model.entity.Resp.SLConsultListResp;
import com.hyjt.home.mvp.model.entity.Resp.SReportDetailResp;
import com.hyjt.home.mvp.model.entity.Resp.SReportListResp;
import com.hyjt.home.mvp.model.entity.Resp.StaffListResp;
import com.hyjt.home.mvp.model.entity.Resp.StaffMsgResp;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;


public interface HomeService {

    @GET("/APIConferencemanage/GetData")
    Observable<BaseJson<MeetingListResp>> getMeetingList(@Query("Page") int Page, @Query("Rows") int Rows,
                                                         @Query("Department") String Department,
                                                         @Query("CM_Num") String CM_Num,
                                                         @Query("CM_name") String CM_name,
                                                         @Query("CM_promoter") String CM_promoter);

    @GET("/APIConferencemanage/Details")
    Observable<BaseJson<MeetingMsgResp>> getMeetingMsg(@Query("Id") String Id);

    @Multipart
    @POST("/res/APIFileUploader.ashx?folder=/up")
    Observable<BaseJson<ImgUploadResp>> imgUpload(@Part MultipartBody.Part file);

    @POST("/APISysPerson/FlexigridList")
    Observable<BaseJson<LinkManResp>> getLinkman(@Query("Page") String Page, @Query("RP") String RP, @Query("SysDepartment") String SysDepartment);

    @FormUrlEncoded
    @POST("/APIConferencemanage/CreateOnOnePage")
    Observable<BaseJson<StaffNameIdKey>> sendMeetingMsg(@Field("CM_name") String CM_name,
                                                        @Field("CM_person") String CM_person,
                                                        @Field("CM_promoter") String CM_promoter,
                                                        @Field("CM_starttime") String CM_starttime,
                                                        @Field("CM_room") String CM_room,
                                                        @Field("SysPerson") String SysPerson,
                                                        @Field("CM_department") String CM_department,
                                                        @Field("CM_content") String CM_content,
                                                        @Field("FileUploader") String FileUploader,
                                                        @Field("CM_State") String CM_State,
                                                        @Field("rowsCount") String rowsCount,
                                                        @Field("Rows") String Rows);

    @GET("/APIConferencemanage/Delete")
    Observable<BaseJson<Object>> delMeetingMsg(@Query("Id") String id);

    @POST("/APIConferencemanage/EditOnOnePage")
    @FormUrlEncoded
    Observable<BaseJson<Object>> editMeetingMsg(@Field("Id") String id,
                                                @Field("CM_Num") String CM_Num,
                                                @Field("CM_name") String CM_name,
                                                @Field("CM_person") String CM_person,
                                                @Field("CM_promoter") String CM_promoter,
                                                @Field("CM_starttime") String CM_starttime,
                                                @Field("CM_room") String CM_room,
                                                @Field("SysPerson") String SysPerson,
                                                @Field("SysPersonOld") String SysPersonOld,
                                                @Field("CM_department") String CM_department,
                                                @Field("CM_content") String CM_content,
                                                @Field("FileUploader") String FileUploader,
                                                @Field("FileUploaderOld") String FileUploaderOld,
                                                @Field("CM_State") String CM_State,
                                                @Field("rowsCount") String rowsCount,
                                                @Field("Rows") String Rows);


    @GET("/APIConferencemanage/GetTxData")
    Observable<BaseJson<MeetingListResp>> getWSolveList(@Query("Page") int Page, @Query("Rows") int Rows,
                                                        @Query("CM_Num") String CM_Num,
                                                        @Query("CM_name") String CM_name,
                                                        @Query("CM_promoter") String CM_promoter);

    @GET("/APIConferencemanage/GetCTxData")
    Observable<BaseJson<MeetingListResp>> getLSolveList(@Query("Page") int Page, @Query("Rows") int Rows,
                                                        @Query("CM_Num") String CM_Num,
                                                        @Query("CM_name") String CM_name,
                                                        @Query("CM_promoter") String CM_promoter);

    @GET("/APIConferencemanage/GetDbData")
    Observable<BaseJson<MeetingListResp>> getSSolveList(@Query("Page") int Page, @Query("Rows") int Rows,
                                                        @Query("CM_Num") String CM_Num,
                                                        @Query("CM_name") String CM_name,
                                                        @Query("CM_promoter") String CM_promoter);

    @GET("/APIhr/FlexigridList")
    Observable<BaseJson<StaffListResp>> getStaffList(@Query("Page") int Page, @Query("Rows") int Rows,
                                                     @Query("E_State") String E_State,
                                                     @Query("Sys_Id") String Sys_Id,
                                                     @Query("MyName") String MyName,
                                                     @Query("State") String State,
                                                     @Query("E_maturetimeStart_Time") String E_maturetimeStart_Time,
                                                     @Query("E_maturetimeEnd_Time") String E_maturetimeEnd_Time);

    @GET("/APIhr/GetTree")
    Observable<BaseJson<List<ChildrenBean>>> getDeptList();

    @FormUrlEncoded
    @POST("/APIhr/Create")
//    @POST("/APIConferenceManage/test")
    Observable<BaseJson<Object>> createStaff(@Field("Department") String Department,
                                             @Field("Name") String Name,
                                             @Field("E_sex") String E_sex,
                                             @Field("E_birth") String E_birth,
                                             @Field("E_nation") String E_nation,
                                             @Field("E_relation") String E_relation,
                                             @Field("E_marry") String E_marry,
                                             @Field("E_family") String E_family,
                                             @Field("E_ffamily") String E_ffamily,
                                             @Field("E_address") String E_address,
                                             @Field("E_card") String E_card,
                                             @Field("E_langu") String E_langu,
                                             @Field("E_compu") String E_compu,
                                             @Field("State") String State,
                                             @Field("E_post") String E_post,
                                             @Field("E_postion") String E_postion,
                                             @Field("E_propost") String E_propost,
                                             @Field("E_workdate") String E_workdate,
                                             @Field("E_state") String E_state,
                                             @Field("E_stay") String E_stay,
                                             @Field("E_doc") String E_doc,
                                             @Field("E_singtime") String E_singtime,
                                             @Field("E_maturetime") String E_maturetime,
                                             @Field("E_insure") String E_insure,
                                             @Field("E_insureL") String E_insureL,
                                             @Field("MobilePhoneNumber") String MobilePhoneNumber,
                                             @Field("E_innerPhone") String E_innerPhone,
                                             @Field("PhoneNumber") String PhoneNumber,
                                             @Field("E_QQ") String E_QQ,
                                             @Field("EmailAddress") String EmailAddress,
                                             @Field("Remark") String Remark,
                                             @Field("qzy") String qzy,
                                             @Field("zzrange") String zzrange,
                                             @Field("familyRelation") String familyRelation,
                                             @Field("lefttime") String lefttime,
                                             @Field("EduPack") String EduPack,
                                             @Field("WorkPack") String WorkExp);

    @GET("/APIhr/Details")
    Observable<BaseJson<StaffMsgResp>> getStaffMsg(@Query("Id") String id);

    @GET("/APIhr/Delete")
    Observable<BaseJson<Object>> delStaffMsg(@Query("Id") String id);

    @FormUrlEncoded
    @POST("/APIhr/Edit")
//    @POST("/APIConferenceManage/test")
    Observable<BaseJson<Object>> editStaffMsg(@Field("Id") String id,
                                              @Field("Department") String Department,
                                              @Field("Name") String Name,
                                              @Field("E_sex") String E_sex,
                                              @Field("E_birth") String E_birth,
                                              @Field("E_nation") String E_nation,
                                              @Field("E_relation") String E_relation,
                                              @Field("E_marry") String E_marry,
                                              @Field("E_family") String E_family,
                                              @Field("E_ffamily") String E_ffamily,
                                              @Field("E_address") String E_address,
                                              @Field("E_card") String E_card,
                                              @Field("E_langu") String E_langu,
                                              @Field("E_compu") String E_compu,
                                              @Field("State") String State,
                                              @Field("E_post") String E_post,
                                              @Field("E_postion") String E_postion,
                                              @Field("E_propost") String E_propost,
                                              @Field("E_workdate") String E_workdate,
                                              @Field("E_state") String E_state,
                                              @Field("E_stay") String E_stay,
                                              @Field("E_doc") String E_doc,
                                              @Field("E_singtime") String E_singtime,
                                              @Field("E_maturetime") String E_maturetime,
                                              @Field("E_insure") String E_insure,
                                              @Field("E_insureL") String E_insureL,
                                              @Field("MobilePhoneNumber") String MobilePhoneNumber,
                                              @Field("E_innerPhone") String E_innerPhone,
                                              @Field("PhoneNumber") String PhoneNumber,
                                              @Field("E_QQ") String E_QQ,
                                              @Field("EmailAddress") String EmailAddress,
                                              @Field("Remark") String Remark,
                                              @Field("qzy") String qzy,
                                              @Field("zzrange") String zzrange,
                                              @Field("familyRelation") String familyRelation,
                                              @Field("lefttime") String lefttime,
                                              @Field("EduPack") String EduPack,
                                              @Field("WorkPack") String WorkPack,
                                              @Field("FilePack") String FilePack);


    @GET("/apihr/TongXunList")
    Observable<BaseJson<StaffListResp>> getAddressBook (@Query("Page") int Page, @Query("Rows") int Rows,
                                                 @Query("E_State") String E_State,
                                                 @Query("Sys_Id") String Sys_Id,
                                                 @Query("MyName") String MyName,
                                                 @Query("State") String State,
                                                 @Query("E_maturetimeStart_Time") String E_maturetimeStart_Time,
                                                 @Query("E_maturetimeEnd_Time") String E_maturetimeEnd_Time);

    @GET("/APImsg/GetData")
    Observable<BaseJson<BlocNoticeListResp>> getBNoticeList(@Query("Page") int Page, @Query("Rows") int Rows);

    @GET("/APImsg/Details")
    Observable<BaseJson<BNoticeDetailsResp>> getBNoticeDetail(@Query("Id") String Id);

    @GET("/APImsg/Try")
    Observable<BaseJson<Object>> getBNoticeLimits();

    @GET("/APImsg/Create")
    Observable<BaseJson<Object>> createBNotice(@Query("Title") String Title, @Query("Message") String Message, @Query("FileUpload") String FileUpload);

    @FormUrlEncoded
    @POST("/APImsg/Edit")
    Observable<BaseJson<Object>> editBNotice(@Field("Id") String Id, @Field("Title") String Title, @Field("Message") String Message,
                                             @Field("FileUpload") String FileUpload, @Field("FileUploaderIdOld") String FileUploaderIdOld);

    @GET("/APImsg/Delete")
    Observable<BaseJson<Object>> delBNotice(@Query("Id") String Id);

    @POST("/APISysletter/GetData")
    Observable<BaseJson<CEmailListResp>> getEmailList(@Body EmailListReqs emailListReqs);

    @POST("/APISysletter/Details")
    Observable<BaseJson<CEmailDetailResp>> getEmailDetails(@Body BaseIdReqs baseIdReqs);

    @POST("/APISysletter/Create")
    Observable<BaseJson<Object>> sendEmail(@Body CEmailSendReqs cEmailSendReqs);

    @POST("/APIWorkingConference/GetData")
    Observable<BaseJson<ReportTListResp>> reportTopList(@Body ReportTopListReqs reportTopListReqs);

    @POST("/APIWorkingConference/Details")
    Observable<BaseJson<ReportTDetailResp>> reportTopDetail(@Body BaseIdReqs baseIdReqs);

    @POST("/APIWorkingConference/Create")
    Observable<BaseJson<Object>> reportTopCreate(@Body ReportTDetailResp reportTDetail);

    @POST("/APIWorkingConference/Edit")
    Observable<BaseJson<Object>> reportTopEdit(@Body ReportTDetailResp reportTDetail);

    @POST("/APIWorkingConference/Delete")
    Observable<BaseJson<Object>> reportTopDelete(@Body BaseIdReqs baseIdReqs);

    @POST("/APIWorkingConsult/GetData")
    Observable<BaseJson<SLConsultListResp>> SLConsultList(@Body SLConsultListReqs sLConsultListReqs);

    @POST("/APIWorkingConsult/Details")
    Observable<BaseJson<SLConsultDetailResp>> SLConsultDetail(@Body BaseIdReqs baseIdReqs);

    @POST("/APIWorkingConsult/Edit")
    Observable<BaseJson<Object>> SLConsultEdit(@Body SLConsultDetailResp sLConsultListReqs);

    @POST("/APIWorkingConsult/Delete")
    Observable<BaseJson<Object>> SLConsultDel(@Body BaseIdReqs baseIdReqs);

    @POST("/APIWorkingConsult/Create")
    Observable<BaseJson<Object>> SLConsultCreate(@Body SLConsultDetailResp consultDetail);



    @POST("/APILeapfrogReport/GetData")
    Observable<BaseJson<SReportListResp>> skipReportList(@Body SReportListReqs sReportListReqs);

    @POST("/APILeapfrogReport/Details")
    Observable<BaseJson<SReportDetailResp>> skipReportDetail(@Body BaseIdReqs baseIdReqs);

    @POST("/APILeapfrogReport/Create")
    Observable<BaseJson<Object>> skipReportCreate(@Body SReportDetailResp reportDetail);

    @POST("/APILeapfrogReport/Edit")
    Observable<BaseJson<Object>> skipReportEdit(@Body SReportDetailResp reportDetail);

    @POST("/APILeapfrogReport/Delete")
    Observable<BaseJson<Object>> skipReportDelete(@Body BaseIdReqs baseIdReqs);



    @POST("/APIYuangongxianji/GetData")
    Observable<BaseJson<CIdeaListResp>> contributeIdeaList(@Body CIdeaListReqs cIdeaListReqs);

    @POST("/APIYuangongxianji/Details")
    Observable<BaseJson<CIdeaDetailResp>> contributeIdeaDetail(@Body BaseIdReqs baseIdReqs);

    @POST("/APIYuangongxianji/Create")
    Observable<BaseJson<Object>> contributeIdeaCreate(@Body CIdeaDetailResp cIdeaDetail);

    @POST("/APIYuangongxianji/Edit")
    Observable<BaseJson<Object>> contributeIdeaEdit(@Body CIdeaDetailResp cIdeaDetail);

    @POST("/APIYuangongxianji/Delete")
    Observable<BaseJson<Object>> contributeIdeaDelete(@Body BaseIdReqs baseIdReqs);


    @POST("/APIUnionAppeal/GetData")
    Observable<BaseJson<LUReqsListResp>> laborUnionReqsList(@Body LUReqsListReqs LUReqsListReqs);

    @POST("/APIUnionAppeal/Details")
    Observable<BaseJson<LUReqsDetailResp>> laborUnionReqsDetail(@Body BaseIdReqs baseIdReqs);

    @POST("/APIUnionAppeal/Create")
    Observable<BaseJson<Object>> laborUnionReqsCreate(@Body LUReqsDetailResp LUReqsDetail);

    @POST("/APIUnionAppeal/Edit")
    Observable<BaseJson<Object>> laborUnionReqsEdit(@Body LUReqsDetailResp LUReqsDetail);

    @POST("/APIUnionAppeal/Delete")
    Observable<BaseJson<Object>> laborUnionReqsDelete(@Body BaseIdReqs baseIdReqs);


    @POST("/APICwPersonalJK/GetData")
    Observable<BaseJson<PsonLoanListResp>> psonLoanReqsList(@Body PsonLoanListReqs PsonLoanListReqs);


    //获取银行账户
    @POST("/APICwPersonalJK/GetBank")
    Observable<BaseJson<PLBankAccountResp>> psonLoanBankAcount();

    //获取流程节点
    @POST("/APICwPersonalJK/GetFlowDetails")
    Observable<BaseJson<PsonLoanListResp>> psonLoanFlowNode(@Body PsonLoanListReqs PsonLoanListReqs);

    //首签领导
    @POST("/APICwPersonalJK/GetFlowList")
    Observable<BaseJson<PLFristLeaderResp>> psonLoanFlowLeader(@Body BaseTypeReqs baseTypeReqs);

    //个人借款公司选择
    @POST("/APICwPersonalJK/GetCompany")
    Observable<BaseJson<PLCompanyResp>> psonLoanCompany();

    //个人借款详情
    @POST("/APICwPersonalJK/Details")
    Observable<BaseJson<PsonLoanDetailResp>> psonLoanDetail(@Body BaseIdReqs PsonLoanReqs);

    //个人借款创建
    @POST("/APICwPersonalJK/CreateOnOnePage")
    Observable<BaseJson<Object>> psonLoanCreate(@Body PsonLoanCreateReqs PsonLoanReqs);

    //个人借款删除
    @POST("/APICwPersonalJK/Delete")
    Observable<BaseJson<Object>> psonLoanDel(@Body BaseIdReqs BaseIdReqs);

    //个人借款编辑
    @POST("/APICwPersonalJK/EditOnOnePage")
    Observable<BaseJson<Object>> psonLoanEdit(@Body PsonLoanCreateReqs PsonLoanReqs);

    //个人借款出纳确认
    @POST("/APICwPersonalJK/EditChuNa")
    Observable<BaseJson<Object>> plTellerConfirm(@Body BaseIdReqs BaseIdReqs);

    //个人借款收款人确认
    @POST("/APICwPersonalJK/Editshoukuan")
    Observable<BaseJson<Object>> plReceiverConfirm(@Body BaseIdReqs BaseIdReqs);

    //选择出款银行
    @POST("/APICwPersonalJK/GetCompanyBank")
    Observable<BaseJson<PLCompBankAccountResp>> plCompBankAccount();

    //选择审批
    @POST("/APICwFlowInstanceNode/EditInstanceNode")
    Observable<BaseJson<Object>> plFlowNodeApr(@Body PlNodeApproveReqs PlNodeApproveReqs);


    @POST("/APICwCompanyJK/GetData")
    Observable<BaseJson<CompLoanListResp>> compLoanReqsList(@Body CompLoanListReqs compLoanListReqs);

    //对公借款详情
    @POST("/APICwCompanyJK/Details")
    Observable<BaseJson<CompLoanDetailResp>> compLoanDetail(@Body BaseIdReqs PsonLoanReqs);

    //对公借款创建
    @POST("/APICwCompanyJK/CreateOnOnePage")
    Observable<BaseJson<Object>> compLoanCreate(@Body CompLoanCreateReqs compLoanCreateReqs);

    //对公借款删除
    @POST("/APICwCompanyJK/Delete")
    Observable<BaseJson<Object>> compLoanDel(@Body BaseIdReqs BaseIdReqs);

    //对公借款合同及供应商列表获取
    @POST("/APICwContract/GetData")
    Observable<BaseJson<CpContractListResp>> compLoanContract(@Body CompLoanContractReqs compLoanContractReqs);
}