package com.hyjt.home.mvp.ui.activity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
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
import com.hyjt.frame.base.BaseActivity;
import com.hyjt.frame.di.component.AppComponent;
import com.hyjt.frame.utils.UiUtils;
import com.hyjt.home.R;
import com.hyjt.home.di.component.DaggerCreateStaffComponent;
import com.hyjt.home.di.module.CreateStaffModule;
import com.hyjt.home.mvp.contract.CreateStaffContract;
import com.hyjt.home.mvp.model.entity.Reqs.StafEduReqs;
import com.hyjt.home.mvp.model.entity.Reqs.StafWorkExpReqs;
import com.hyjt.home.mvp.model.entity.Reqs.StaffMsgReqs;
import com.hyjt.home.mvp.model.entity.Reqs.StaffNameIdKey;
import com.hyjt.home.mvp.presenter.CreateStaffPresenter;
import com.hyjt.home.mvp.ui.view.Constant;
import com.hyjt.home.mvp.ui.view.DatePickDialog;
import com.hyjt.home.mvp.ui.view.DeptTreePop;
import com.hyjt.home.mvp.ui.view.GetSingleSelectItem;
import com.hyjt.home.mvp.ui.view.treelistview.Node;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.hyjt.frame.utils.Preconditions.checkNotNull;


@Route(path = "/home/CreateStaffActivity")
public class CreateStaffActivity extends BaseActivity<CreateStaffPresenter> implements CreateStaffContract.View {


    private android.widget.RelativeLayout mRlCreateStaff;
    private android.widget.ImageView mIvTopBack;
    private android.widget.TextView mTvTitle;
    private android.widget.ImageView mIvTopSelect;
    private android.widget.Button mBtnCreateStaff;
    private android.widget.EditText mEdtSelDept;
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
    private DeptTreePop deptTreePop;
    private StaffNameIdKey DeptIdStr;
    private ScrollView mSlvStaff;
    private List<StafEduReqs> stafEduList = new ArrayList<>();
    private List<StafWorkExpReqs> stafWorkExpList = new ArrayList<>();



    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerCreateStaffComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .createStaffModule(new CreateStaffModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(Bundle savedInstanceState) {
        return R.layout.home_activity_create_staff; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        mSlvStaff = (ScrollView) findViewById(R.id.slv_staff);
        mRlCreateStaff = (RelativeLayout) findViewById(R.id.rl_create_staff);
        mIvTopBack = (ImageView) findViewById(R.id.iv_top_back);
        mIvTopBack.setOnClickListener(v -> finish());
        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mTvTitle.setOnClickListener(v -> finish());
        mTvTitle.setText("创建员工信息");
        mIvTopSelect = (ImageView) findViewById(R.id.iv_top_select);
        mIvTopSelect.setVisibility(View.GONE);
        mBtnCreateStaff = (Button) findViewById(R.id.btn_create_staff);
        mBtnCreateStaff.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this)
                    .setTitle("提交保存")
                    .setMessage("确认提交该条员工信息?")
                    .setPositiveButton("确定", (dialog, which) -> {
                        createStaff();
                    }).setNegativeButton("返回", (dialog, which) -> {
                        dialog.dismiss();
                    });
            builder.show();
        });
        mEdtSelDept = (EditText) findViewById(R.id.edt_sel_dept);
        mEdtSelDept.setOnClickListener(v -> showDeptSel());
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

        setSelectItem();

        DeptIdStr = new StaffNameIdKey();
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

    /**
     *
     */
    private void createStaff() {

        StaffMsgReqs staffMsgReqs = new StaffMsgReqs();

        if (TextUtils.isEmpty(mEdtSelDept.getText())) {
            shortToast("所在部门为必填项");
            return;
        }
        if (TextUtils.isEmpty(mEdtName.getText())) {
            shortToast("职工姓名为必填项");
            return;
        }
        if (TextUtils.isEmpty(mEdtPost.getText())) {
            shortToast("职位为必填项");
            return;
        }
        if (TextUtils.isEmpty(mEdtStaffStatus.getText())) {
            shortToast("职工状态为必填项");
            return;
        }
        staffMsgReqs.setDepartment(DeptIdStr.getSendKey());
        staffMsgReqs.setName(mEdtName.getText().toString());
        staffMsgReqs.setE_sex(mEdtSex.getText().toString());
        staffMsgReqs.setE_birth(mEdtBirthday.getText().toString());
        staffMsgReqs.setE_nation(mEdtNation.getText().toString());
        staffMsgReqs.setE_relation(mEdtPoliticsStatus.getText().toString());
        staffMsgReqs.setE_marry(mEdtMarriageStatus.getText().toString());
        staffMsgReqs.setE_family(mEdtCensusRegister.getText().toString());
        staffMsgReqs.setE_ffamily(mEdtNativePlace.getText().toString());
        staffMsgReqs.setE_address(mEdtCurrentAddress.getText().toString());
        staffMsgReqs.setE_address(mEdtCurrentAddress.getText().toString());
        staffMsgReqs.setE_card(mEdtIdentityCard.getText().toString());
        staffMsgReqs.setE_langu(mEdtForeignLanguage.getText().toString());
        staffMsgReqs.setE_compu(mEdtComputerLevel.getText().toString());
        staffMsgReqs.setState(mEdtMostEducation.getText().toString());
        staffMsgReqs.setE_post(mEdtDuty.getText().toString());
        staffMsgReqs.setE_postion(mEdtPost.getText().toString());
        staffMsgReqs.setE_propost(mEdtPostTitle.getText().toString());
        staffMsgReqs.setE_workdate(mEdtEntryTime.getText().toString());
        staffMsgReqs.setE_state(mEdtStaffStatus.getText().toString());
        staffMsgReqs.setE_stay(mEdtIsLive.getText().toString());
        staffMsgReqs.setE_doc(mEdtRecordAddress.getText().toString());
        staffMsgReqs.setE_singtime(mEdtSignTime.getText().toString());
        staffMsgReqs.setE_maturetime(mEdtExpireTime.getText().toString());
        staffMsgReqs.setE_insure(mEdtIsInsured.getText().toString());
        staffMsgReqs.setE_insureL(mEdtInsuredAddress.getText().toString());
        staffMsgReqs.setMobilePhoneNumber(mEdtPhoneNum.getText().toString());
        staffMsgReqs.setE_innerPhone(mEdtOtherPhonenum.getText().toString());
        staffMsgReqs.setPhoneNumber(mEdtOtherOffnum.getText().toString());
        staffMsgReqs.setE_QQ(mEdtQq.getText().toString());
        staffMsgReqs.setEmailAddress(mEdtMailbox.getText().toString());
        staffMsgReqs.setRemark(mEdtRemark.getText().toString());
        staffMsgReqs.setQzy(mEdtRightsDuty.getText().toString());
        staffMsgReqs.setZzrange(mEdtResponsibility.getText().toString());
        staffMsgReqs.setFamilyRelation(mEdtFamilyState.getText().toString());
        staffMsgReqs.setLefttime(mEdtDimissionTime.getText().toString());

        getEduPackMsg();
        getWorkExpMsg();

        staffMsgReqs.setEduPack(stafEduList);
        staffMsgReqs.setWorkExpPack(stafWorkExpList);

        mPresenter.createStaff(staffMsgReqs);
    }

    private void getEduPackMsg() {
        for (int i = 0; i < mLlEduPack.getChildCount(); i++) {
            LinearLayout childAt = (LinearLayout) mLlEduPack.getChildAt(i);
            StafEduReqs stafEduReqs =  stafEduList.get(i);

            EditText mEdtSchool = (EditText) childAt.findViewById(R.id.edt_school);
            EditText mEdtGraduationTime = (EditText) childAt.findViewById(R.id.edt_graduation_time);
            EditText mEdtMajor = (EditText) childAt.findViewById(R.id.edt_major);
            EditText mEdtEducation = (EditText) childAt.findViewById(R.id.edt_education);

            stafEduReqs.setSchool(mEdtSchool.getText().toString());
            stafEduReqs.setDate(mEdtGraduationTime.getText().toString());
            stafEduReqs.setMajor(mEdtMajor.getText().toString());
            stafEduReqs.setDegree(mEdtEducation.getText().toString());
        }
    }

    private void getWorkExpMsg() {
        for (int i = 0; i < mLlWorkExp.getChildCount(); i++) {
            LinearLayout childAt = (LinearLayout) mLlWorkExp.getChildAt(i);
            StafWorkExpReqs workExp =  stafWorkExpList.get(i);

            EditText mEdtCompany = (EditText) childAt.findViewById(R.id.edt_company);
            EditText mEdtWorkPost = (EditText) childAt.findViewById(R.id.edt_work_post);
            EditText mEdtStartTime = (EditText) childAt.findViewById(R.id.edt_start_time);
            EditText mEdtEndTime = (EditText) childAt.findViewById(R.id.edt_end_time);
            EditText workDescribe = (EditText) childAt.findViewById(R.id.edt_work_describe);

            workExp.setCompany(mEdtCompany.getText().toString());
            workExp.setPostion(mEdtWorkPost.getText().toString());
            workExp.setWorkStart(mEdtStartTime.getText().toString());
            workExp.setWorkEnd(mEdtEndTime.getText().toString());
            workExp.setWork_degree(workDescribe.getText().toString());
        }
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

    private void showDeptSel() {
        mPresenter.requestDept();
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

    @Override
    public void showDeptTree(List<Node> deptList) {
        deptTreePop = new DeptTreePop(this, deptList, mEdtSelDept, DeptIdStr);
        deptTreePop.showAtLocation(findViewById(R.id.rl_create_staff),
                Gravity.CENTER | Gravity.CENTER_HORIZONTAL, 0, 0);
    }
}
