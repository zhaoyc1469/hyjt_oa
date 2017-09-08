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
import android.widget.ScrollView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.hyjt.frame.base.BaseActivity;
import com.hyjt.frame.di.component.AppComponent;
import com.hyjt.frame.utils.UiUtils;
import com.hyjt.home.R;
import com.hyjt.home.di.component.DaggerSkipReportDetailComponent;
import com.hyjt.home.di.module.SkipReportDetailModule;
import com.hyjt.home.mvp.contract.SkipReportDetailContract;
import com.hyjt.home.mvp.model.entity.Resp.SReportDetailResp;
import com.hyjt.home.mvp.presenter.SkipReportDetailPresenter;

import static com.hyjt.frame.utils.Preconditions.checkNotNull;

@Route(path = "/home/SkipReportDetailActivity")
public class SkipReportDetailActivity extends BaseActivity<SkipReportDetailPresenter> implements SkipReportDetailContract.View {


    private String srId;
    private SkipReportDetailActivity mContext;
    private ProgressDialog progressDialog;
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
    private SReportDetailResp sReport;

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerSkipReportDetailComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .skipReportDetailModule(new SkipReportDetailModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(Bundle savedInstanceState) {
        return R.layout.home_activity_skip_report_detail; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        mContext = this;
        Intent intent = getIntent();
        srId = intent.getStringExtra("Id");


        mIvTopBack = (ImageView) findViewById(R.id.iv_top_back);
        mIvTopBack.setOnClickListener(v -> finish());
        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mTvTitle.setText("越级汇报");
        mTvTitle.setOnClickListener(v -> finish());
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

        progressDialog = ProgressDialog.show(mContext, null, "汇报内容加载中…");
        mPresenter.sReportDetail(srId);

        mBtnEditReport.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this)
                    .setTitle("汇报编辑")
                    .setMessage("确定编辑该条汇报信息?")
                    .setPositiveButton("确定", (dialog, which) -> {
                        progressDialog = ProgressDialog.show(this, null, "汇报内容编辑中…");
                        sReport.setTitle(mEdtRpTitle.getText().toString());
                        sReport.setReportContent(mEdtContent.getText().toString());
                        sReport.setReplyContent(mEdtLeaderIdea.getText().toString());
                        if (mRbAgree.isChecked()){
                            sReport.setAgree("同意");
                        } else if (mRbRefuse.isChecked()){
                            sReport.setAgree("不同意");
                        } else {
                            sReport.setAgree("lep");
                        }
                        sReport.setReplyTime("");
                        mPresenter.sReportEdit(sReport);
                    }).setNegativeButton("返回", (dialog, which) -> {
                        dialog.dismiss();
                    });
            builder.show();
        });


        mBtnDelReport.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this)
                    .setTitle("删除汇报")
                    .setMessage("确定删除该条汇报信息?")
                    .setPositiveButton("确定", (dialog, which) -> {
                        progressDialog = ProgressDialog.show(this, null, "汇报删除中…");
                        mPresenter.sReportDel(srId);
                    }).setNegativeButton("返回", (dialog, which) -> dialog.dismiss());
            builder.show();
        });
    }

    @Override
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
        UiUtils.snackbarText(message);
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
    public void setSRDetail(SReportDetailResp sReport) {
        this.sReport = sReport;
        mEdtRpNum.setText(sReport.getNumber());
        mEdtRpPerson.setText(sReport.getReportPerson());
        mEdtRpLeader.setText(sReport.getBoss());
        mEdtRpTitle.setText(sReport.getTitle());
        mEdtContent.setText(sReport.getReportContent());
        mEdtRpTime.setText(sReport.getReportTime());

        if ("同意".equals(sReport.getAgree())) {
            mRbAgree.setChecked(true);
            mRbRefuse.setChecked(false);
        } else if ("不同意".equals(sReport.getAgree())) {
            mRbAgree.setChecked(false);
            mRbRefuse.setChecked(true);
        }

        if ("待批示".equals(sReport.getState())) {
            if (getUserName().equals(sReport.getBoss())){
                mEdtRpTitle.setFocusable(false);
                mEdtRpTitle.setEnabled(false);
                mEdtContent.setFocusable(false);
                mEdtContent.setEnabled(false);
                this.sReport.setState("已批示");
            } else if (getUserName().equals(sReport.getReportPerson())){
                mEdtLeaderIdea.setFocusable(false);
                mEdtLeaderIdea.setEnabled(false);
                mRbAgree.setEnabled(false);
                mRbRefuse.setEnabled(false);
            }
        } else if ("已批示".equals(sReport.getState())) {
            mEdtRpTitle.setFocusable(false);
            mEdtRpTitle.setEnabled(false);
            mEdtContent.setFocusable(false);
            mEdtContent.setEnabled(false);
            mEdtLeaderIdea.setFocusable(false);
            mEdtLeaderIdea.setEnabled(false);
            mRbAgree.setEnabled(false);
            mRbRefuse.setEnabled(false);
        }

    }

}
