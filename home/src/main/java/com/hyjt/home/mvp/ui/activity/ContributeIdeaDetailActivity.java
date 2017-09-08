package com.hyjt.home.mvp.ui.activity;

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
import com.hyjt.home.mvp.presenter.ContributeIdeaDetailPresenter;

import com.hyjt.home.R;
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
        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mIvTopSelect = (ImageView) findViewById(R.id.iv_top_select);
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


}
