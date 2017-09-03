package com.hyjt.home.mvp.ui.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.hyjt.frame.base.BaseActivity;
import com.hyjt.frame.di.component.AppComponent;
import com.hyjt.frame.utils.UiUtils;
import com.hyjt.home.R;
import com.hyjt.home.di.component.DaggerStaffMsgComponent;
import com.hyjt.home.di.module.StaffMsgModule;
import com.hyjt.home.mvp.contract.StaffMsgContract;
import com.hyjt.home.mvp.model.entity.Reqs.StafEduReqs;
import com.hyjt.home.mvp.model.entity.Reqs.StafWorkExpReqs;
import com.hyjt.home.mvp.model.entity.Reqs.StaffNameIdKey;
import com.hyjt.home.mvp.model.entity.Resp.StaffMsgResp;
import com.hyjt.home.mvp.presenter.StaffMsgPresenter;
import com.hyjt.home.mvp.ui.adapter.StaffImgAdapter;
import com.hyjt.home.mvp.ui.view.Constant;
import com.hyjt.home.mvp.ui.view.DatePickDialog;
import com.hyjt.home.mvp.ui.view.DeptTreePop;
import com.hyjt.home.mvp.ui.view.GetSingleSelectItem;
import com.hyjt.home.mvp.ui.view.treelistview.Node;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.hyjt.frame.utils.Preconditions.checkNotNull;


@Route(path = "/home/StaffMsgActivity")
public class StaffMsgActivity extends BaseActivity<StaffMsgPresenter> implements StaffMsgContract.View {

    private String staffId;
    private android.widget.RelativeLayout mRlEditStaff;
    private android.widget.ImageView mIvTopBack;
    private android.widget.TextView mTvTitle;
    private android.widget.ImageView mIvTopSelect;
    private android.widget.Button mBtnEditStaff;
    private android.widget.Button mBtnDelStaff;
    private android.widget.EditText mEdtSelDept;
    private EditText mEdtStaffId;
    private android.widget.EditText mEdtName;
    private android.widget.EditText mEdtSex;
    private android.widget.EditText mEdtBirthday;
    private android.widget.EditText mEdtNation;
    private android.widget.EditText mEdtPoliticsStatus;
    private android.widget.EditText mEdtMarriageStatus;
    private android.widget.EditText mEdtCensusRegister;
    private android.widget.EditText mEdtNativePlace;
    private android.widget.EditText mEdtCurrentAddress;
    private android.widget.EditText mEdtIdentityCard;
    private android.widget.EditText mEdtForeignLanguage;
    private android.widget.EditText mEdtComputerLevel;
    private android.widget.EditText mEdtMostEducation;
    private android.widget.EditText mEdtDuty;
    private android.widget.EditText mEdtPost;
    private android.widget.EditText mEdtPostTitle;
    private android.widget.EditText mEdtEntryTime;
    private android.widget.EditText mEdtStaffStatus;
    private android.widget.EditText mEdtIsLive;
    private android.widget.EditText mEdtRecordAddress;
    private android.widget.EditText mEdtSignTime;
    private android.widget.EditText mEdtExpireTime;
    private android.widget.EditText mEdtIsInsured;
    private android.widget.EditText mEdtInsuredAddress;
    private android.widget.EditText mEdtPhoneNum;
    private android.widget.EditText mEdtOtherPhonenum;
    private android.widget.EditText mEdtOtherOffnum;
    private android.widget.EditText mEdtQq;
    private android.widget.EditText mEdtMailbox;
    private android.widget.EditText mEdtRemark;
    private android.widget.EditText mEdtRightsDuty;
    private android.widget.EditText mEdtResponsibility;
    private android.widget.EditText mEdtFamilyState;
    private android.widget.EditText mEdtDimissionTime;
    private android.widget.Button mBtnAddEducation;
    private android.widget.Button mBtnAddWorkexp;
    private android.widget.LinearLayout mLlEduPack;
    private android.widget.LinearLayout mLlWorkExp;
    private android.widget.LinearLayout mLlFilePack;
    private android.widget.RelativeLayout mRlStafFile;
    private StaffMsgResp staffMsgResp;

    private List<StafEduReqs> stafEduList = new ArrayList<>();
    private List<StafWorkExpReqs> stafWorkExpList = new ArrayList<>();
    private List<StaffMsgResp.FilePackBean> stafFilePackList = new ArrayList<>();
    private List<StaffMsgResp.FilePackBean> stafPicList = new ArrayList<>();
    private ScrollView mSlvStaff;
    private StaffNameIdKey DeptIdStr = new StaffNameIdKey();
    private DeptTreePop deptTreePop;
    private RecyclerView mRecyPic;
    private ProgressDialog progressDialog;


    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerStaffMsgComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .staffMsgModule(new StaffMsgModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(Bundle savedInstanceState) {
        return R.layout.home_activity_staff_msg; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        Intent intent = getIntent();
        staffId = intent.getStringExtra("id");
        mPresenter.getStaffMsg(staffId);

        mSlvStaff = (ScrollView) findViewById(R.id.slv_staff);
        mRlEditStaff = (RelativeLayout) findViewById(R.id.rl_edit_staff);
        mIvTopBack = (ImageView) findViewById(R.id.iv_top_back);
        mIvTopBack.setOnClickListener(v -> finish());
        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mTvTitle.setText("员工信息");
        mIvTopSelect = (ImageView) findViewById(R.id.iv_top_select);
        mIvTopSelect.setVisibility(View.GONE);
        mBtnEditStaff = (Button) findViewById(R.id.btn_edit_staff);
        mBtnEditStaff.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this)
                    .setTitle("保存修改")
                    .setMessage("确认提交编辑结果?")
                    .setPositiveButton("确定", (dialog, which) -> {
                        sendEditStaf();
                    }).setNegativeButton("返回", (dialog, which) -> {
                        dialog.dismiss();
                    });
            builder.show();
        });
        mBtnDelStaff = (Button) findViewById(R.id.btn_del_staff);
        mBtnDelStaff.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this)
                    .setTitle("删除纪要")
                    .setMessage("确定删除该条员工信息?")
                    .setPositiveButton("确定", (dialog, which) -> {
                        sendDelStaf();
                    }).setNegativeButton("返回", (dialog, which) -> {
                        dialog.dismiss();
                    });
            builder.show();
        });
        mEdtSelDept = (EditText) findViewById(R.id.edt_sel_dept);
        mEdtSelDept.setOnClickListener(v -> mPresenter.requestDept());
        mEdtStaffId = (EditText) findViewById(R.id.edt_staff_id);
        mEdtName = (EditText) findViewById(R.id.edt_name);
        mEdtSex = (EditText) findViewById(R.id.edt_sex);
        mEdtBirthday = (EditText) findViewById(R.id.edt_birthday);
        setTimeEdit(mEdtBirthday);
        mEdtNation = (EditText) findViewById(R.id.edt_nation);
        mEdtPoliticsStatus = (EditText) findViewById(R.id.edt_politics_status);
        mEdtMarriageStatus = (EditText) findViewById(R.id.edt_marriage_status);
        mEdtCensusRegister = (EditText) findViewById(R.id.edt_census_register);
        mEdtNativePlace = (EditText) findViewById(R.id.edt_native_place);
        mEdtCurrentAddress = (EditText) findViewById(R.id.edt_current_address);
        mEdtIdentityCard = (EditText) findViewById(R.id.edt_identity_card);
        mEdtForeignLanguage = (EditText) findViewById(R.id.edt_foreign_language);
        mEdtComputerLevel = (EditText) findViewById(R.id.edt_computer_level);
        mEdtMostEducation = (EditText) findViewById(R.id.edt_most_education);
        mEdtDuty = (EditText) findViewById(R.id.edt_duty);
        mEdtPost = (EditText) findViewById(R.id.edt_post);
        mEdtPostTitle = (EditText) findViewById(R.id.edt_post_title);
        mEdtEntryTime = (EditText) findViewById(R.id.edt_entry_time);
        setTimeEdit(mEdtEntryTime);
        mEdtStaffStatus = (EditText) findViewById(R.id.edt_staff_status);
        mEdtIsLive = (EditText) findViewById(R.id.edt_is_live);
        mEdtRecordAddress = (EditText) findViewById(R.id.edt_record_address);
        mEdtSignTime = (EditText) findViewById(R.id.edt_sign_time);
        setTimeEdit(mEdtSignTime);
        mEdtExpireTime = (EditText) findViewById(R.id.edt_expire_time);
        setTimeEdit(mEdtExpireTime);
        mEdtIsInsured = (EditText) findViewById(R.id.edt_is_insured);
        mEdtInsuredAddress = (EditText) findViewById(R.id.edt_insured_address);
        mEdtPhoneNum = (EditText) findViewById(R.id.edt_phone_num);
        mEdtOtherPhonenum = (EditText) findViewById(R.id.edt_other_phonenum);
        mEdtOtherOffnum = (EditText) findViewById(R.id.edt_other_offnum);
        mEdtQq = (EditText) findViewById(R.id.edt_qq);
        mEdtMailbox = (EditText) findViewById(R.id.edt_mailbox);
        mEdtRemark = (EditText) findViewById(R.id.edt_remark);
        mEdtRightsDuty = (EditText) findViewById(R.id.edt_rights_duty);
        mEdtResponsibility = (EditText) findViewById(R.id.edt_responsibility);
        mEdtFamilyState = (EditText) findViewById(R.id.edt_family_state);
        mEdtDimissionTime = (EditText) findViewById(R.id.edt_dimission_time);
        setTimeEdit(mEdtDimissionTime);
        mBtnAddEducation = (Button) findViewById(R.id.btn_add_education);
        mBtnAddEducation.setOnClickListener(v -> addEducation());
        mBtnAddWorkexp = (Button) findViewById(R.id.btn_add_workexp);
        mBtnAddWorkexp.setOnClickListener(v -> addWorkExp());
        mLlEduPack = (LinearLayout) findViewById(R.id.ll_edu_pack);
        mLlWorkExp = (LinearLayout) findViewById(R.id.ll_work_exp);
        mRlStafFile = (RelativeLayout) findViewById(R.id.rl_staf_file);
        mRecyPic = (RecyclerView) findViewById(R.id.recy_staff_pic);
        UiUtils.configRecycleView(mRecyPic, new GridLayoutManager(this, 2));
        mLlFilePack = (LinearLayout) findViewById(R.id.ll_file_pack);

        setSelectItem();
    }

    private void setSelectItem() {
        mEdtSex.setOnTouchListener(new GetSingleSelectItem(
                this, mEdtSex, "性别", Constant.sexArr, false));
        mEdtNation.setOnTouchListener(new GetSingleSelectItem(
                this, mEdtNation, "民族", Constant.nationArr, false));
        mEdtPoliticsStatus.setOnTouchListener(new GetSingleSelectItem(
                this, mEdtPoliticsStatus, "政治面貌", Constant.politicsStatusArr, false));
        mEdtMarriageStatus.setOnTouchListener(new GetSingleSelectItem(
                this, mEdtMarriageStatus, "婚育状况", Constant.marriageStatusArr, false));
        mEdtForeignLanguage.setOnTouchListener(new GetSingleSelectItem(
                this, mEdtForeignLanguage, "外语等级", Constant.foreignLanguageArr, false));
        mEdtComputerLevel.setOnTouchListener(new GetSingleSelectItem(
                this, mEdtComputerLevel, "计算机等级", Constant.computerLevelArr, false));
        mEdtMostEducation.setOnTouchListener(new GetSingleSelectItem(
                this, mEdtMostEducation, "最高学历", Constant.mostEducationArr, false));
        mEdtPost.setOnTouchListener(new GetSingleSelectItem(
                this, mEdtPost, "职位", Constant.postArr, false));
        mEdtPostTitle.setOnTouchListener(new GetSingleSelectItem(
                this, mEdtPostTitle, "职称", Constant.postTitleArr, false));
        mEdtStaffStatus.setOnTouchListener(new GetSingleSelectItem(
                this, mEdtStaffStatus, "职工状态", Constant.staffStatusArr, false));
        mEdtIsLive.setOnTouchListener(new GetSingleSelectItem(
                this, mEdtIsLive, "是否住宿", Constant.isLiveArr, false));
        mEdtIsInsured.setOnTouchListener(new GetSingleSelectItem(
                this, mEdtIsInsured, "是否参保", Constant.isInsuredArr, false));
    }

    @Override
    public void showDeptTree(List<Node> deptList) {
        deptTreePop = new DeptTreePop(this, deptList, mEdtSelDept, DeptIdStr);
        deptTreePop.showAtLocation(findViewById(R.id.rl_edit_staff),
                Gravity.CENTER | Gravity.CENTER_HORIZONTAL, 0, 0);
    }

    private void sendEditStaf() {
        staffMsgResp.setDepartment(DeptIdStr.getSendKey());
        staffMsgResp.setName(mEdtName.getText().toString());
        staffMsgResp.setE_sex(mEdtSex.getText().toString());
        staffMsgResp.setE_birth(mEdtBirthday.getText().toString());
        staffMsgResp.setE_nation(mEdtNation.getText().toString());
        staffMsgResp.setE_relation(mEdtPoliticsStatus.getText().toString());
        staffMsgResp.setE_marry(mEdtMarriageStatus.getText().toString());
        staffMsgResp.setE_family(mEdtCensusRegister.getText().toString());
        staffMsgResp.setE_ffamily(mEdtNativePlace.getText().toString());
        staffMsgResp.setE_address(mEdtCurrentAddress.getText().toString());
        staffMsgResp.setE_card(mEdtIdentityCard.getText().toString());
        staffMsgResp.setE_langu(mEdtForeignLanguage.getText().toString());
        staffMsgResp.setE_compu(mEdtComputerLevel.getText().toString());
        staffMsgResp.setState(mEdtMostEducation.getText().toString());
        staffMsgResp.setE_post(mEdtDuty.getText().toString());
        staffMsgResp.setE_postion(mEdtPost.getText().toString());
        staffMsgResp.setE_propost(mEdtPostTitle.getText().toString());
        staffMsgResp.setE_workdate(mEdtEntryTime.getText().toString());
        staffMsgResp.setE_state(mEdtStaffStatus.getText().toString());
        staffMsgResp.setE_stay(mEdtIsLive.getText().toString());
        staffMsgResp.setE_doc(mEdtRecordAddress.getText().toString());
        staffMsgResp.setE_singtime(mEdtSignTime.getText().toString());
        staffMsgResp.setE_maturetime(mEdtExpireTime.getText().toString());
        staffMsgResp.setE_insure(mEdtIsInsured.getText().toString());
        staffMsgResp.setE_insureL(mEdtInsuredAddress.getText().toString());
        staffMsgResp.setMobilePhoneNumber(mEdtPhoneNum.getText().toString());
        staffMsgResp.setE_innerPhone(mEdtOtherPhonenum.getText().toString());
        staffMsgResp.setPhoneNumber(mEdtOtherOffnum.getText().toString());
        staffMsgResp.setE_QQ(mEdtQq.getText().toString());
        staffMsgResp.setEmailAddress(mEdtMailbox.getText().toString());
        staffMsgResp.setRemark(mEdtRemark.getText().toString());
        staffMsgResp.setQzy(mEdtRightsDuty.getText().toString());
        staffMsgResp.setZzrange(mEdtResponsibility.getText().toString());
        staffMsgResp.setFamilyRelation(mEdtFamilyState.getText().toString());
        staffMsgResp.setLefttime(mEdtDimissionTime.getText().toString());

        getEduPack();
        getWorkExpPack();

        staffMsgResp.setEduPack(stafEduList);
        staffMsgResp.setWorkPack(stafWorkExpList);

        mPresenter.sendEditStaf(staffMsgResp);
    }


    private void sendDelStaf() {
        mPresenter.sendDelStaf(staffMsgResp.getId());
    }

    private void addEducation() {
        int size = stafEduList.size();
        StafEduReqs stafEduReqs = new StafEduReqs();

        View inflate = LayoutInflater.from(this).inflate(R.layout.home_add_staf_edu, null);
        Button btnDelEdu = (Button) inflate.findViewById(R.id.btn_del_edu);
        btnDelEdu.setOnClickListener(v -> {
            if (stafEduList.size() > size) {
                mLlEduPack.removeView(inflate);
                stafEduList.remove(size);
            }
        });
        EditText education = (EditText) inflate.findViewById(R.id.edt_education);
        education.setOnTouchListener(new GetSingleSelectItem(
                this, education, "最高学历", Constant.educationArr, false));

        EditText graduationTime = (EditText) inflate.findViewById(R.id.edt_graduation_time);
        setTimeEdit(graduationTime);

        mLlEduPack.addView(inflate);
        stafEduList.add(stafEduReqs);
        mSlvStaff.fullScroll(ScrollView.FOCUS_DOWN);
    }

    private void addWorkExp() {
        int size = stafWorkExpList.size();
        StafWorkExpReqs stafWorkExpReqs = new StafWorkExpReqs();

        View inflate = LayoutInflater.from(this).inflate(R.layout.home_add_staf_workexp, null);
        Button btnDelEdu = (Button) inflate.findViewById(R.id.btn_del_workexp);
        btnDelEdu.setOnClickListener(v -> {
            if (stafWorkExpList.size() > size) {
                mLlWorkExp.removeView(inflate);
                stafWorkExpList.remove(size);
            }
        });
        EditText startTime = (EditText) inflate.findViewById(R.id.edt_start_time);
        setTimeEdit(startTime);

        EditText endTime = (EditText) inflate.findViewById(R.id.edt_end_time);
        setTimeEdit(endTime);

        mLlWorkExp.addView(inflate);
        stafWorkExpList.add(stafWorkExpReqs);
        mSlvStaff.fullScroll(ScrollView.FOCUS_DOWN);
    }

    @Override
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
        UiUtils.snackbarText(message);
        shortToast(message);

    }

    @Override
    public void killMyself() {
        finish();
    }

    private void setTimeEdit(EditText finishTime) {
        finishTime.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                Calendar c = Calendar.getInstance();
                new DatePickDialog(this, c).setOnDateTimeSetListener((dp, year, monthOfYear, dayOfMonth) -> {
                    finishTime.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                    // 保存选择后时间
                    c.set(Calendar.YEAR, year);
                    c.set(Calendar.MONTH, monthOfYear);
                    c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                });
            }
            return true;
        });
    }

    @Override
    public void setStaffMsg(StaffMsgResp staffMsgResp) {
        this.staffMsgResp = staffMsgResp;

        DeptIdStr.setSendKey(staffMsgResp.getDepartmentOld());
        mEdtSelDept.setText(staffMsgResp.getDepartment());
        mEdtStaffId.setText(staffMsgResp.getE_id());
        mEdtName.setText(staffMsgResp.getName());
        mEdtSex.setText(staffMsgResp.getE_sex());
        mEdtBirthday.setText(staffMsgResp.getE_birth());
        mEdtNation.setText(staffMsgResp.getE_nation());
        mEdtPoliticsStatus.setText(staffMsgResp.getE_relation());
        mEdtMarriageStatus.setText(staffMsgResp.getE_marry());
        mEdtCensusRegister.setText(staffMsgResp.getE_family());
        mEdtNativePlace.setText(staffMsgResp.getE_ffamily());
        mEdtCurrentAddress.setText(staffMsgResp.getE_address());
        mEdtIdentityCard.setText(staffMsgResp.getE_card());
        mEdtForeignLanguage.setText(staffMsgResp.getE_langu());
        mEdtComputerLevel.setText(staffMsgResp.getE_compu());
        mEdtMostEducation.setText(staffMsgResp.getState());
        mEdtDuty.setText(staffMsgResp.getE_post());
        mEdtPost.setText(staffMsgResp.getE_postion());
        mEdtPostTitle.setText(staffMsgResp.getE_propost());
        mEdtEntryTime.setText(staffMsgResp.getE_workdate());
        mEdtStaffStatus.setText(staffMsgResp.getE_state());
        mEdtIsLive.setText(staffMsgResp.getE_stay());
        mEdtRecordAddress.setText(staffMsgResp.getE_doc());
        mEdtSignTime.setText(staffMsgResp.getE_singtime());
        mEdtExpireTime.setText(staffMsgResp.getE_maturetime());
        mEdtIsInsured.setText(staffMsgResp.getE_insure());
        mEdtInsuredAddress.setText(staffMsgResp.getE_insureL());
        mEdtPhoneNum.setText(staffMsgResp.getMobilePhoneNumber());
        mEdtOtherPhonenum.setText(staffMsgResp.getE_innerPhone());
        mEdtOtherOffnum.setText(staffMsgResp.getPhoneNumber());
        mEdtQq.setText(staffMsgResp.getE_QQ());
        mEdtMailbox.setText(staffMsgResp.getEmailAddress());
        mEdtRemark.setText(staffMsgResp.getRemark());
        mEdtRightsDuty.setText(staffMsgResp.getQzy());
        mEdtResponsibility.setText(staffMsgResp.getZzrange());
        mEdtFamilyState.setText(staffMsgResp.getFamilyRelation());
        mEdtDimissionTime.setText(staffMsgResp.getLefttime());

        if (staffMsgResp.getEduPack() != null && staffMsgResp.getEduPack().size() > 0) {
            stafEduList = staffMsgResp.getEduPack();
            for (int i = 0; i < stafEduList.size(); i++) {
                setEduPack(i);
            }
        }
        if (staffMsgResp.getWorkPack() != null && staffMsgResp.getWorkPack().size() > 0) {
            stafWorkExpList = staffMsgResp.getWorkPack();
            for (int i = 0; i < stafWorkExpList.size(); i++) {
                setWorkExp(i);
            }
        }
        if (staffMsgResp.getFilePack() != null && staffMsgResp.getFilePack().size() > 0) {
            stafFilePackList = staffMsgResp.getFilePack();
            for (int i = 0; i < stafFilePackList.size(); i++) {
//                String isphoto = stafFilePackList.get(i).getIsphoto();
                if ("0".equals(stafFilePackList.get(i).getIsPhoto())) {
                    setFilePack(i);
                } else {
                    stafPicList.add(stafFilePackList.get(i));
                }
            }
            if (stafPicList.size() != 0) {
                StaffImgAdapter staffImgAdapter = new StaffImgAdapter(this, stafPicList);
                mRecyPic.setAdapter(staffImgAdapter);
                staffImgAdapter.setShowBigListener(position -> {
                    ARouter.getInstance().build("/home/ImageViewerActivity").withString("imageUrl"
                            , getString(R.string.home_base_url) + stafPicList.get(position).getFilePath()).navigation();
                });
            } else {
                mRecyPic.setVisibility(View.GONE);
            }
            if ((stafFilePackList.size() - stafPicList.size()) == 0) {
                mRlStafFile.setVisibility(View.GONE);
            }
        } else {
            mRlStafFile.setVisibility(View.GONE);
            mRecyPic.setVisibility(View.GONE);
        }
    }

    @Override
    public void startLoading() {
        progressDialog = ProgressDialog.show(this, null, "正在载入员工信息…");
    }

    @Override
    public void endLoading() {
        progressDialog.dismiss();
    }

    private void setEduPack(int post) {
        StafEduReqs stafEduReqs = stafEduList.get(post);
        View inflate = LayoutInflater.from(this).inflate(R.layout.home_add_staf_edu, null);
        Button btnDelEdu = (Button) inflate.findViewById(R.id.btn_del_edu);
        btnDelEdu.setOnClickListener(v -> {
            if (stafEduList.size() > post) {
                mLlEduPack.removeView(inflate);
                stafEduList.remove(post);
            }
        });

        EditText school = (EditText) inflate.findViewById(R.id.edt_school);
        school.setText(stafEduReqs.getSchool());

        EditText graduationTime = (EditText) inflate.findViewById(R.id.edt_graduation_time);
        setTimeEdit(graduationTime);
        graduationTime.setText(stafEduReqs.getDate());

        EditText major = (EditText) inflate.findViewById(R.id.edt_major);
        major.setText(stafEduReqs.getMajor());

        EditText education = (EditText) inflate.findViewById(R.id.edt_education);
        education.setText(stafEduReqs.getDegree());
        education.setOnTouchListener(new GetSingleSelectItem(
                this, education, "学历", Constant.educationArr, false));


        mLlEduPack.addView(inflate);
    }

    private void setWorkExp(int post) {
        StafWorkExpReqs stafWorkExpReqs = stafWorkExpList.get(post);

        View inflate = LayoutInflater.from(this).inflate(R.layout.home_add_staf_workexp, null);
        Button btnDelEdu = (Button) inflate.findViewById(R.id.btn_del_workexp);
        btnDelEdu.setOnClickListener(v -> {
            if (stafWorkExpList.size() > post) {
                mLlWorkExp.removeView(inflate);
                stafWorkExpList.remove(post);
            }
        });

        EditText company = (EditText) inflate.findViewById(R.id.edt_company);
        company.setText(stafWorkExpReqs.getCompany());

        EditText workPost = (EditText) inflate.findViewById(R.id.edt_work_post);
        workPost.setText(stafWorkExpReqs.getPostion());

        EditText startTime = (EditText) inflate.findViewById(R.id.edt_start_time);
        setTimeEdit(startTime);
        startTime.setText(stafWorkExpReqs.getWorkStart());

        EditText endTime = (EditText) inflate.findViewById(R.id.edt_end_time);
        setTimeEdit(endTime);
        endTime.setText(stafWorkExpReqs.getWorkEnd());


        EditText workDescribe = (EditText) inflate.findViewById(R.id.edt_work_describe);
//        stafWorkExpReqs.setWork_degree(workDescribe.getText().toString().trim());
        workDescribe.setText(stafWorkExpReqs.getWork_degree());

        mLlWorkExp.addView(inflate);
    }

    private void setFilePack(int post) {
        StaffMsgResp.FilePackBean filePackBean = stafFilePackList.get(post);

        View inflate = LayoutInflater.from(this).inflate(R.layout.home_add_staf_filepack, null);

        TextView certificate = (TextView) inflate.findViewById(R.id.tv_certificate);
        certificate.setText(filePackBean.getFileName());
        TextView certificateFile = (TextView) inflate.findViewById(R.id.tv_certificate_file);
        certificateFile.setText(Html.fromHtml("<a href=\"http://test.cemtn.cn/\">" + filePackBean.getFilePath() + "</a>"));
        certificateFile.setOnClickListener(v -> {
            // 弹出一个选择浏览器的框，选择浏览器再进入
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.home_base_url) + filePackBean.getFilePath()));
//            Intent intent= new Intent();
//            intent.setAction(Intent.ACTION_VIEW);
//            Uri content_url = Uri.parse();
//            intent.setData(content_url);
            startActivity(intent);
        });

        mLlFilePack.addView(inflate);
    }

    private void getEduPack() {
        for (int i = 0; i < mLlEduPack.getChildCount(); i++) {
            LinearLayout childAt = (LinearLayout) mLlEduPack.getChildAt(i);
            StafEduReqs stafEduReqs = stafEduList.get(i);

            EditText school = (EditText) childAt.findViewById(R.id.edt_school);
            stafEduReqs.setSchool(school.getText().toString().trim());

            EditText graduationTime = (EditText) childAt.findViewById(R.id.edt_graduation_time);
            stafEduReqs.setDate(graduationTime.getText().toString().trim());

            EditText major = (EditText) childAt.findViewById(R.id.edt_major);
            stafEduReqs.setMajor(major.getText().toString().trim());

            EditText education = (EditText) childAt.findViewById(R.id.edt_education);
            stafEduReqs.setDegree(education.getText().toString().trim());
        }
    }

    private void getWorkExpPack() {
        for (int i = 0; i < mLlWorkExp.getChildCount(); i++) {
            LinearLayout childAt = (LinearLayout) mLlWorkExp.getChildAt(i);
            StafWorkExpReqs stafWorkExpReqs = stafWorkExpList.get(i);

            EditText company = (EditText) childAt.findViewById(R.id.edt_company);
            stafWorkExpReqs.setCompany(company.getText().toString().trim());

            EditText workPost = (EditText) childAt.findViewById(R.id.edt_work_post);
            stafWorkExpReqs.setPostion(workPost.getText().toString().trim());

            EditText startTime = (EditText) childAt.findViewById(R.id.edt_start_time);
            stafWorkExpReqs.setWorkStart(startTime.getText().toString().trim());

            EditText endTime = (EditText) childAt.findViewById(R.id.edt_end_time);
            stafWorkExpReqs.setWorkEnd(endTime.getText().toString().trim());

            EditText workDescribe = (EditText) childAt.findViewById(R.id.edt_work_describe);
            stafWorkExpReqs.setWork_degree(workDescribe.getText().toString().trim());
        }
    }
}
