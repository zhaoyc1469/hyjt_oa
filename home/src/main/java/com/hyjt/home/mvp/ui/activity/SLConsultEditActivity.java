package com.hyjt.home.mvp.ui.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.hyjt.frame.base.BaseActivity;
import com.hyjt.frame.di.component.AppComponent;
import com.hyjt.frame.utils.UiUtils;
import com.hyjt.home.R;
import com.hyjt.home.di.component.DaggerSLConsultEditComponent;
import com.hyjt.home.di.module.SLConsultEditModule;
import com.hyjt.home.mvp.contract.SLConsultEditContract;
import com.hyjt.home.mvp.model.entity.Resp.SLConsultDetailResp;
import com.hyjt.home.mvp.presenter.SLConsultEditPresenter;

import static com.hyjt.frame.utils.Preconditions.checkNotNull;

@Route(path = "/home/SLConsultEditActivity")
public class SLConsultEditActivity extends BaseActivity<SLConsultEditPresenter> implements SLConsultEditContract.View {


    private android.widget.ImageView mIvTopBack;
    private android.widget.TextView mTvTitle;
    private android.widget.ImageView mIvTopSelect;
    private android.widget.LinearLayout mLlBottomBtn;
    private android.widget.Button mBtnEditReport;
    private android.widget.Button mBtnDelReport;
    private android.widget.ScrollView mSlvSlconsult;
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
    private String slcId;
    private ProgressDialog progressDialog;

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerSLConsultEditComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .sLConsultEditModule(new SLConsultEditModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(Bundle savedInstanceState) {
        return R.layout.home_activity_slconsult_edit; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(Bundle savedInstanceState) {

        Intent intent = getIntent();
        slcId = intent.getStringExtra("Id");

        mIvTopBack = (ImageView) findViewById(R.id.iv_top_back);
        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mIvTopSelect = (ImageView) findViewById(R.id.iv_top_select);
        mLlBottomBtn = (LinearLayout) findViewById(R.id.ll_bottom_btn);
        mBtnEditReport = (Button) findViewById(R.id.btn_edit_report);
        mBtnDelReport = (Button) findViewById(R.id.btn_del_report);
        mSlvSlconsult = (ScrollView) findViewById(R.id.slv_slconsult);
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
            progressDialog = ProgressDialog.show(this, null, "协商删除中…");
            mPresenter.consultDel(slcId);});

        mPresenter.consultDetail(slcId);
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
    public void setSLCDetail(SLConsultDetailResp sLConsult) {

    }
}
