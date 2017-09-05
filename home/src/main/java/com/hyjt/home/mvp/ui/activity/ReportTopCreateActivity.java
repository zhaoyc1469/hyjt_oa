package com.hyjt.home.mvp.ui.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.hyjt.frame.base.BaseActivity;
import com.hyjt.frame.di.component.AppComponent;
import com.hyjt.frame.utils.UiUtils;
import com.hyjt.home.R;
import com.hyjt.home.di.component.DaggerReportTopCreateComponent;
import com.hyjt.home.di.module.ReportTopCreateModule;
import com.hyjt.home.mvp.contract.ReportTopCreateContract;
import com.hyjt.home.mvp.model.entity.Resp.ReportTDetailResp;
import com.hyjt.home.mvp.presenter.ReportTopCreatePresenter;

import static com.hyjt.frame.utils.Preconditions.checkNotNull;


public class ReportTopCreateActivity extends BaseActivity<ReportTopCreatePresenter> implements ReportTopCreateContract.View {


    private android.widget.ImageView mIvTopBack;
    private android.widget.TextView mTvTitle;
    private android.widget.ImageView mIvTopSelect;
    private android.widget.LinearLayout mLlBottomBtn;
    private android.widget.Button mBtnEditReport;
    private android.widget.ScrollView mSlvReport;
    private android.widget.EditText mEdtRpName;
    private android.widget.EditText mEdtRpLeader;
    private android.widget.EditText mEdtContent;
    private android.widget.EditText mEdtIdeaExpect;
    private ProgressDialog progressDialog;

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerReportTopCreateComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .reportTopCreateModule(new ReportTopCreateModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(Bundle savedInstanceState) {
        return R.layout.home_activity_report_top_create; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(Bundle savedInstanceState) {

        mIvTopBack = (ImageView) findViewById(R.id.iv_top_back);
        mIvTopBack.setOnClickListener(v -> finish());
        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mTvTitle.setText("创建汇报");
        mTvTitle.setOnClickListener(v -> finish());
        mIvTopSelect = (ImageView) findViewById(R.id.iv_top_select);
        mIvTopSelect.setVisibility(View.GONE);
        mLlBottomBtn = (LinearLayout) findViewById(R.id.ll_bottom_btn);
        mBtnEditReport = (Button) findViewById(R.id.btn_edit_report);
        mSlvReport = (ScrollView) findViewById(R.id.slv_report);
        mEdtRpName = (EditText) findViewById(R.id.edt_rp_name);
        mEdtRpLeader = (EditText) findViewById(R.id.edt_rp_leader);
        mEdtContent = (EditText) findViewById(R.id.edt_content);
        mEdtIdeaExpect = (EditText) findViewById(R.id.edt_idea_expect);

        mEdtRpName.setText(getUserName());

        mBtnEditReport.setOnClickListener(v -> {
            progressDialog = ProgressDialog.show(this, null, "报告创建中…");
            ReportTDetailResp reportTDetailResp = new ReportTDetailResp();
            reportTDetailResp.setContent(mEdtContent.getText().toString());
            reportTDetailResp.setMind(mEdtIdeaExpect.getText().toString());
            mPresenter.createReport(reportTDetailResp);
        });

        mEdtRpLeader.setOnClickListener(v -> {

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
}
