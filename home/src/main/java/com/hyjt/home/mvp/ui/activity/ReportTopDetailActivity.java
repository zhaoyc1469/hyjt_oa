package com.hyjt.home.mvp.ui.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
import com.hyjt.frame.base.BaseActivity;
import com.hyjt.frame.di.component.AppComponent;
import com.hyjt.frame.event.RefreshListEvent;
import com.hyjt.frame.utils.UiUtils;
import com.hyjt.home.R;
import com.hyjt.home.di.component.DaggerReportTopDetailComponent;
import com.hyjt.home.di.module.ReportTopDetailModule;
import com.hyjt.home.mvp.contract.ReportTopDetailContract;
import com.hyjt.home.mvp.model.entity.Resp.ReportTDetailResp;
import com.hyjt.home.mvp.presenter.ReportTopDetailPresenter;

import org.simple.eventbus.EventBus;

import static com.hyjt.frame.utils.Preconditions.checkNotNull;

@Route(path = "/home/ReportTopDetailActivity")
public class ReportTopDetailActivity extends BaseActivity<ReportTopDetailPresenter> implements ReportTopDetailContract.View {


    private android.widget.ImageView mIvTopBack;
    private android.widget.TextView mTvTitle;
    private android.widget.ImageView mIvTopSelect;
    private android.widget.LinearLayout mLlBottomBtn;
    private android.widget.Button mBtnEditReport;
    private android.widget.Button mBtnDelReport;
    private android.widget.ScrollView mSlvReport;
    private android.widget.EditText mEdtRpNum;
    private android.widget.EditText mEdtRpName;
    private android.widget.EditText mEdtRpTime;
    private android.widget.EditText mEdtRpLeader;
    private android.widget.EditText mEdtContent;
    private android.widget.EditText mEdtIdeaExpect;
    private android.widget.ImageView mIvRpNameSignature;
    private android.widget.EditText mEdtApproveTime;
    private android.widget.EditText mEdtLeaderIdea;
    private android.widget.ImageView mIvLeaderSignature;
    private String reportId;
    private ProgressDialog progressDialog;
    private ReportTDetailResp mReportTopDetail;

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerReportTopDetailComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .reportTopDetailModule(new ReportTopDetailModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(Bundle savedInstanceState) {
        return R.layout.home_activity_report_top_detail; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(Bundle savedInstanceState) {

        Intent intent = getIntent();
        reportId = intent.getStringExtra("Id");

        mIvTopBack = (ImageView) findViewById(R.id.iv_top_back);
        mIvTopBack.setOnClickListener(v -> finish());
        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mTvTitle.setText("汇报详情");
        mTvTitle.setOnClickListener(v -> finish());
        mIvTopSelect = (ImageView) findViewById(R.id.iv_top_select);
        mIvTopSelect.setVisibility(View.GONE);
        mLlBottomBtn = (LinearLayout) findViewById(R.id.ll_bottom_btn);
        mBtnEditReport = (Button) findViewById(R.id.btn_edit_report);
        mBtnDelReport = (Button) findViewById(R.id.btn_del_report);
        mSlvReport = (ScrollView) findViewById(R.id.slv_report);
        mEdtRpNum = (EditText) findViewById(R.id.edt_rp_num);
        mEdtRpName = (EditText) findViewById(R.id.edt_rp_name);
        mEdtRpTime = (EditText) findViewById(R.id.edt_rp_time);
        mEdtRpLeader = (EditText) findViewById(R.id.edt_rp_leader);
        mEdtContent = (EditText) findViewById(R.id.edt_content);
        mEdtIdeaExpect = (EditText) findViewById(R.id.edt_idea_expect);
        mIvRpNameSignature = (ImageView) findViewById(R.id.iv_rp_name_signature);
        mEdtApproveTime = (EditText) findViewById(R.id.edt_approve_time);
        mEdtLeaderIdea = (EditText) findViewById(R.id.edt_leader_idea);
        mIvLeaderSignature = (ImageView) findViewById(R.id.iv_leader_signature);


        mBtnDelReport.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this)
                    .setTitle("删除报告")
                    .setMessage("确定删除该条报告信息?")
                    .setPositiveButton("确定", (dialog, which) -> {
                        progressDialog = ProgressDialog.show(this, null, "报告删除中…");
                        mPresenter.getReportDel(reportId);
                    }).setNegativeButton("返回", (dialog, which) -> {
                        dialog.dismiss();
                    });
            builder.show();
        });

        mBtnEditReport.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this)
                    .setTitle("报告编辑")
                    .setMessage("确定编辑该条报告信息?")
                    .setPositiveButton("确定", (dialog, which) -> {
                        progressDialog = ProgressDialog.show(this, null, "报告编辑中…");
                        mReportTopDetail.setContent(mEdtContent.getText().toString());
                        mReportTopDetail.setMind(mEdtIdeaExpect.getText().toString());
                        mReportTopDetail.setBossMind(mEdtLeaderIdea.getText().toString());
                        mPresenter.getReportEdit(mReportTopDetail);
                    }).setNegativeButton("返回", (dialog, which) -> {
                        dialog.dismiss();
                    });
            builder.show();
        });

        progressDialog = ProgressDialog.show(this, null, "报告内容加载中…");
        mPresenter.getReportDetail(reportId);
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
        EventBus.getDefault().post(new RefreshListEvent(), "Refresh_List");
    }

    @Override
    public void hideLoading() {
        if (progressDialog != null)
            progressDialog.dismiss();
    }

    @Override
    public void setRTDetail(ReportTDetailResp rtDetail) {
        mReportTopDetail = rtDetail;

        mEdtRpNum.setText(rtDetail.getPID());
        mEdtRpName.setText(rtDetail.getCreatePerson());
        mEdtRpTime.setText(rtDetail.getCreateTime());
        mEdtRpLeader.setText(rtDetail.getBoss());
        mEdtContent.setText(rtDetail.getContent());
        mEdtIdeaExpect.setText(rtDetail.getMind());

        if (!"待审核".equals(rtDetail.getState())) {
            mEdtApproveTime.setText(rtDetail.getBossTime());
        }

        mEdtLeaderIdea.setText(rtDetail.getBossMind());

        if (!TextUtils.isEmpty(rtDetail.getCreatePersonSign())) {
            mIvRpNameSignature.setVisibility(View.VISIBLE);
            Glide.with(this).load(getString(R.string.home_base_url) + rtDetail.getCreatePersonSign())
                    .into(mIvRpNameSignature);
        }
        if (!TextUtils.isEmpty(rtDetail.getBossSign())) {
            mIvLeaderSignature.setVisibility(View.VISIBLE);
            Glide.with(this).load(getString(R.string.home_base_url) + rtDetail.getBossSign())
                    .into(mIvLeaderSignature);
        }

        if ("待审核".equals(rtDetail.getState())){
            if (getUserName().equals(rtDetail.getCreatePerson())) {
                mBtnEditReport.setText("保存");
                mEdtLeaderIdea.setFocusable(false);
                mEdtLeaderIdea.setEnabled(false);

            } else if (getUserName().equals(rtDetail.getBoss())) {
                mBtnEditReport.setText("审批");
                mBtnDelReport.setVisibility(View.GONE);

                mReportTopDetail.setState("已审核");

                mEdtContent.setFocusable(false);
                mEdtContent.setEnabled(false);
                mEdtIdeaExpect.setFocusable(false);
                mEdtIdeaExpect.setEnabled(false);
            }
            mReportTopDetail.setBossTime("");
        } else {

            mEdtContent.setFocusable(false);
            mEdtContent.setEnabled(false);
            mEdtIdeaExpect.setFocusable(false);
            mEdtIdeaExpect.setEnabled(false);
            mEdtLeaderIdea.setFocusable(false);
            mEdtLeaderIdea.setEnabled(false);

            mLlBottomBtn.setVisibility(View.GONE);
        }


    }
}
