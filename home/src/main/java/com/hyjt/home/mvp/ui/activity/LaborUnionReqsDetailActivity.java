package com.hyjt.home.mvp.ui.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.hyjt.frame.base.BaseActivity;
import com.hyjt.frame.di.component.AppComponent;
import com.hyjt.frame.utils.UiUtils;

import com.hyjt.home.di.component.DaggerLaborUnionReqsDetailComponent;
import com.hyjt.home.di.module.LaborUnionReqsDetailModule;
import com.hyjt.home.mvp.contract.LaborUnionReqsDetailContract;
import com.hyjt.home.mvp.model.entity.Resp.LUReqsDetailResp;
import com.hyjt.home.mvp.presenter.LaborUnionReqsDetailPresenter;

import com.hyjt.home.R;


import static com.hyjt.frame.utils.Preconditions.checkNotNull;


@Route(path = "/home/LaborUnionReqsDetailActivity")
public class LaborUnionReqsDetailActivity extends BaseActivity<LaborUnionReqsDetailPresenter> implements LaborUnionReqsDetailContract.View {


    private LaborUnionReqsDetailActivity mContext;
    private String LUId;
    private android.widget.ImageView ivTopBack;
    private android.widget.TextView tvTitle;
    private android.widget.ImageView ivTopSelect;
    private android.widget.LinearLayout llBottobtn;
    private android.widget.Button btnEditReport;
    private android.widget.Button btnDelReport;
    private android.widget.ScrollView slvReport;
    private android.widget.EditText edtRpNum;
    private android.widget.EditText edtLuPerson;
    private android.widget.EditText edtRpLeader;
    private android.widget.EditText edtRpTitle;
    private android.widget.EditText edtContent;
    private android.widget.EditText edtLuTime;
    private android.widget.EditText edtLeaderIdea;
    private android.widget.RadioGroup rgQuestionState;
    private android.widget.RadioButton rbAgree;
    private android.widget.RadioButton rbRefuse;
    private android.widget.EditText edtApproveTime;
    private ProgressDialog progressDialog;
    private LUReqsDetailResp LUDetail;
    private RelativeLayout mRlIsAgree;

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerLaborUnionReqsDetailComponent
                .builder()
                .appComponent(appComponent)
                .laborUnionReqsDetailModule(new LaborUnionReqsDetailModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(Bundle savedInstanceState) {
        return R.layout.home_activity_labor_union_reqs_detail;
    }

    @Override
    public void initData(Bundle savedInstanceState) {

        mContext = this;
        Intent intent = getIntent();
        LUId = intent.getStringExtra("Id");


        ivTopBack = (ImageView) findViewById(R.id.iv_top_back);
        ivTopBack.setOnClickListener(view -> finish());
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvTitle.setOnClickListener(view -> finish());
        tvTitle.setText("公会诉求");
        ivTopSelect = (ImageView) findViewById(R.id.iv_top_select);
        ivTopSelect.setVisibility(View.GONE);
        llBottobtn = (LinearLayout) findViewById(R.id.ll_bottom_btn);
        btnEditReport = (Button) findViewById(R.id.btn_edit_report);
        btnDelReport = (Button) findViewById(R.id.btn_del_report);
        slvReport = (ScrollView) findViewById(R.id.slv_report);
        edtRpNum = (EditText) findViewById(R.id.edt_rp_num);
        edtLuPerson = (EditText) findViewById(R.id.edt_lu_person);
        edtRpLeader = (EditText) findViewById(R.id.edt_rp_leader);
        edtRpTitle = (EditText) findViewById(R.id.edt_rp_title);
        edtContent = (EditText) findViewById(R.id.edt_content);
        edtLuTime = (EditText) findViewById(R.id.edt_lu_time);
        edtLeaderIdea = (EditText) findViewById(R.id.edt_leader_idea);
        mRlIsAgree = (RelativeLayout) findViewById(R.id.rl_is_agree);
        rgQuestionState = (RadioGroup) findViewById(R.id.rg_question_state);
        rbAgree = (RadioButton) findViewById(R.id.rb_agree);
        rbRefuse = (RadioButton) findViewById(R.id.rb_refuse);
        edtApproveTime = (EditText) findViewById(R.id.edt_approve_time);

        // 此页面去除此功能
        mRlIsAgree.setVisibility(View.GONE);

        progressDialog = ProgressDialog.show(mContext, null, "诉求内容加载中…");
        mPresenter.LUDetail(LUId);

        btnEditReport.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this)
                    .setTitle("汇报编辑")
                    .setMessage("确定编辑该条汇报信息?")
                    .setPositiveButton("确定", (dialog, which) -> {
                        progressDialog = ProgressDialog.show(this, null, "汇报内容编辑中…");
                        LUDetail.setTitle(edtRpTitle.getText().toString());
                        LUDetail.setAppealContent(edtContent.getText().toString());
                        LUDetail.setReplyContent(edtLeaderIdea.getText().toString());
                        if (rbAgree.isChecked()){
                            LUDetail.setAgree("同意");
                        } else if (rbRefuse.isChecked()){
                            LUDetail.setAgree("不同意");
                        } else {
                            LUDetail.setAgree("lep");
                        }
                        LUDetail.setReplyTime("");
                        mPresenter.LUEdit(LUDetail);
                    }).setNegativeButton("返回", (dialog, which) -> dialog.dismiss());
            builder.show();
        });


        btnDelReport.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this)
                    .setTitle("删除汇报")
                    .setMessage("确定删除该条汇报信息?")
                    .setPositiveButton("确定", (dialog, which) -> {
                        progressDialog = ProgressDialog.show(this, null, "汇报删除中…");
                        mPresenter.LUDel(LUId);
                    }).setNegativeButton("返回", (dialog, which) -> dialog.dismiss());
            builder.show();
        });
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
    public void hideLoading() {
        if (progressDialog != null)
            progressDialog.dismiss();
    }

    @Override
    public void setLUDetail(LUReqsDetailResp detail) {
        LUDetail = detail;

        edtRpNum.setText(detail.getNumber());
        edtLuPerson.setText(detail.getAppealPerson());
        edtRpLeader.setText(detail.getBoss());
        edtRpTitle.setText(detail.getTitle());
        edtLuTime.setText(detail.getAppealTime());
        edtContent.setText(detail.getAppealContent());
        edtLeaderIdea.setText(detail.getReplyContent());




        if ("待批示".equals(detail.getState())) {
            if (getUserName().equals(detail.getBoss())){
                edtRpTitle.setFocusable(false);
                edtRpTitle.setEnabled(false);
                edtContent.setFocusable(false);
                edtContent.setEnabled(false);
                btnEditReport.setText("批示");
                this.LUDetail.setState("已批示");
                btnDelReport.setVisibility(View.GONE);
            } else if (getUserName().equals(detail.getAppealPerson())){
                edtLeaderIdea.setFocusable(false);
                edtLeaderIdea.setEnabled(false);
                rbAgree.setClickable(false);
                rbRefuse.setClickable(false);
                btnEditReport.setText("编辑");
            }
        } else if ("已批示".equals(detail.getState())) {
            edtRpTitle.setFocusable(false);
            edtRpTitle.setEnabled(false);
            edtContent.setFocusable(false);
            edtContent.setEnabled(false);
            edtLeaderIdea.setFocusable(false);
            edtLeaderIdea.setEnabled(false);
            rbAgree.setClickable(false);
            rbRefuse.setClickable(false);
            llBottobtn.setVisibility(View.GONE);
            edtApproveTime.setText(detail.getReplyTime());
        }

        if ("同意".equals(detail.getAgree())) {
            rbAgree.setChecked(true);
//            rbRefuse.setChecked(false);
        } else if ("不同意".equals(detail.getAgree())) {
//            rbAgree.setChecked(false);
            rbRefuse.setChecked(true);
        }
    }
}
