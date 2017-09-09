package com.hyjt.home.mvp.ui.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.hyjt.frame.base.BaseActivity;
import com.hyjt.frame.di.component.AppComponent;
import com.hyjt.frame.utils.UiUtils;

import com.hyjt.home.di.component.DaggerContributeIdeaDetailComponent;
import com.hyjt.home.di.module.ContributeIdeaDetailModule;
import com.hyjt.home.mvp.contract.ContributeIdeaDetailContract;
import com.hyjt.home.mvp.model.entity.Resp.CIdeaDetailResp;
import com.hyjt.home.mvp.presenter.ContributeIdeaDetailPresenter;

import com.hyjt.home.R;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;


import static com.hyjt.frame.utils.Preconditions.checkNotNull;

@Route(path = "/home/ContributeIdeaDetailActivity")
public class ContributeIdeaDetailActivity extends BaseActivity<ContributeIdeaDetailPresenter> implements ContributeIdeaDetailContract.View {


    private android.widget.ImageView mIvTopBack;
    private android.widget.TextView mTvTitle;
    private android.widget.ImageView mIvTopSelect;
    private android.widget.LinearLayout mLlBottomBtn;
    private android.widget.Button mBtnEditReport;
    private android.widget.Button mBtnDelReport;
    private android.widget.ScrollView mSlvReport;
    private android.widget.EditText mEdtRpNum;
    private android.widget.EditText mEdtRpPerson;
    private android.widget.EditText mEdtRpLeader;
    private android.widget.EditText mEdtRpTitle;
    private android.widget.EditText mEdtContent;
    private android.widget.EditText mEdtRpTime;
    private android.widget.EditText mEdtLeaderIdea;
    private android.widget.RadioGroup mRgQuestionState;
    private android.widget.RadioButton mRbAgree;
    private android.widget.RadioButton mRbRefuse;
    private android.widget.EditText mEdtApproveTime;
    private ContributeIdeaDetailActivity mContext;
    private String CIId;
    private ProgressDialog progressDialog;
    private CIdeaDetailResp CIDetail;

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerContributeIdeaDetailComponent
                .builder()
                .appComponent(appComponent)
                .contributeIdeaDetailModule(new ContributeIdeaDetailModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(Bundle savedInstanceState) {
        return R.layout.home_activity_contribute_idea_detail;
    }

    @Override
    public void initData(Bundle savedInstanceState) {

        mContext = this;
        Intent intent = getIntent();
        CIId = intent.getStringExtra("Id");

        mIvTopBack = (ImageView) findViewById(R.id.iv_top_back);
        mIvTopBack.setOnClickListener(view -> finish());
        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mTvTitle.setOnClickListener(view -> finish());
        mTvTitle.setText("献计献策");
        mIvTopSelect = (ImageView) findViewById(R.id.iv_top_select);
        mIvTopSelect.setVisibility(View.GONE);
        mLlBottomBtn = (LinearLayout) findViewById(R.id.ll_bottom_btn);
        mBtnEditReport = (Button) findViewById(R.id.btn_edit_report);
        mBtnDelReport = (Button) findViewById(R.id.btn_del_report);
        mSlvReport = (ScrollView) findViewById(R.id.slv_report);
        mEdtRpNum = (EditText) findViewById(R.id.edt_rp_num);
        mEdtRpPerson = (EditText) findViewById(R.id.edt_rp_person);
        mEdtRpLeader = (EditText) findViewById(R.id.edt_rp_leader);
        mEdtRpTitle = (EditText) findViewById(R.id.edt_rp_title);
        mEdtContent = (EditText) findViewById(R.id.edt_content);
        mEdtRpTime = (EditText) findViewById(R.id.edt_rp_time);
        mEdtLeaderIdea = (EditText) findViewById(R.id.edt_leader_idea);
        mRgQuestionState = (RadioGroup) findViewById(R.id.rg_question_state);
        mRbAgree = (RadioButton) findViewById(R.id.rb_agree);
        mRbRefuse = (RadioButton) findViewById(R.id.rb_refuse);
        mEdtApproveTime = (EditText) findViewById(R.id.edt_approve_time);


        progressDialog = ProgressDialog.show(mContext, null, "献策内容加载中…");
        mPresenter.cIDetail(CIId);

        mBtnEditReport.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this)
                    .setTitle("汇报编辑")
                    .setMessage("确定编辑该条汇报信息?")
                    .setPositiveButton("确定", (dialog, which) -> {
                        progressDialog = ProgressDialog.show(this, null, "汇报内容编辑中…");
                        CIDetail.setTitle(mEdtRpTitle.getText().toString());
                        CIDetail.setReportContent(mEdtContent.getText().toString());
                        CIDetail.setReplyContent(mEdtLeaderIdea.getText().toString());
                        if (mRbAgree.isChecked()){
                            CIDetail.setAgree("同意");
                        } else if (mRbRefuse.isChecked()){
                            CIDetail.setAgree("不同意");
                        } else {
                            CIDetail.setAgree("lep");
                        }
                        CIDetail.setReplyTime("");
                        mPresenter.cIEdit(CIDetail);
                    }).setNegativeButton("返回", (dialog, which) -> dialog.dismiss());
            builder.show();
        });


        mBtnDelReport.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this)
                    .setTitle("删除汇报")
                    .setMessage("确定删除该条汇报信息?")
                    .setPositiveButton("确定", (dialog, which) -> {
                        progressDialog = ProgressDialog.show(this, null, "汇报删除中…");
                        mPresenter.cIDel(CIId);
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
    public void setCIDetail(CIdeaDetailResp detail) {
        CIDetail = detail;


        mEdtRpNum.setText(detail.getNumber());
        mEdtRpPerson.setText(detail.getReportPerson());
        mEdtRpLeader.setText(detail.getBoss());
        mEdtRpTitle.setText(detail.getTitle());
        mEdtContent.setText(detail.getReportContent());
        mEdtRpTime.setText(detail.getReportTime());
        mEdtContent.setText(detail.getReportContent());
        mEdtRpTime.setText(detail.getReportTime());
        mEdtLeaderIdea.setText(detail.getReplyContent());




        if ("待批示".equals(detail.getState())) {
            if (getUserName().equals(detail.getBoss())){
                mEdtRpTitle.setFocusable(false);
                mEdtRpTitle.setEnabled(false);
                mEdtContent.setFocusable(false);
                mEdtContent.setEnabled(false);
                mBtnEditReport.setText("批示");
                mBtnDelReport.setVisibility(View.GONE);
                this.CIDetail.setState("已批示");
            } else if (getUserName().equals(detail.getReportPerson())){
                mEdtLeaderIdea.setFocusable(false);
                mEdtLeaderIdea.setEnabled(false);
                mRbAgree.setClickable(false);
                mRbRefuse.setClickable(false);
                mBtnEditReport.setText("编辑");
            }
        } else if ("已批示".equals(detail.getState())) {
            mEdtRpTitle.setFocusable(false);
            mEdtRpTitle.setEnabled(false);
            mEdtContent.setFocusable(false);
            mEdtContent.setEnabled(false);
            mEdtLeaderIdea.setFocusable(false);
            mEdtLeaderIdea.setEnabled(false);
            mRbAgree.setClickable(false);
            mRbRefuse.setClickable(false);
            mLlBottomBtn.setVisibility(View.GONE);
            mEdtApproveTime.setText(detail.getReplyTime());
        }

        if ("同意".equals(detail.getAgree())) {
            mRbAgree.setChecked(true);
//            mRbRefuse.setChecked(false);
        } else if ("不同意".equals(detail.getAgree())) {
//            mRbAgree.setChecked(false);
            mRbRefuse.setChecked(true);
        }

    }
}
