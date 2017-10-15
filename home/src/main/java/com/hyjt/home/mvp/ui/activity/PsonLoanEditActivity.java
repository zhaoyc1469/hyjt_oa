package com.hyjt.home.mvp.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.hyjt.frame.base.BaseActivity;
import com.hyjt.frame.di.component.AppComponent;
import com.hyjt.frame.utils.UiUtils;

import com.hyjt.home.di.component.DaggerPsonLoanEditComponent;
import com.hyjt.home.di.module.PsonLoanEditModule;
import com.hyjt.home.mvp.contract.PsonLoanEditContract;
import com.hyjt.home.mvp.model.entity.Resp.CEmailDetailResp;
import com.hyjt.home.mvp.model.entity.Resp.PsonLoanDetailResp;
import com.hyjt.home.mvp.presenter.PsonLoanEditPresenter;

import com.hyjt.home.R;

import java.util.ArrayList;
import java.util.List;

import static com.hyjt.frame.utils.Preconditions.checkNotNull;


@Route(path = "/home/PsonLoanEditActivity")
public class PsonLoanEditActivity extends BaseActivity<PsonLoanEditPresenter> implements PsonLoanEditContract.View {


    private android.widget.RelativeLayout mRlPsonloan;
    private android.widget.ImageView mIvTopBack;
    private android.widget.TextView mTvTitle;
    private android.widget.ImageView mIvTopSelect;
    private Intent intent;
    private String psonLoanId;
    private android.widget.EditText mEdtProposer;
    private android.widget.EditText mEdtCompany;
    private android.widget.EditText mEdtDepartment;
    private android.widget.EditText mEdtFristLeader;
    private android.widget.EditText mEdtLoanReason;
    private android.widget.EditText mEdtLoanAmount;
    private android.widget.EditText mEdtPaymentTerm;
    private android.widget.EditText mEdtAccountName;
    private android.widget.EditText mEdtOpenactBank;
    private android.widget.EditText mEdtBankAccount;
    private EditText mEdtLoanNum;
    private EditText mEdtLoanTime;
    private List<PsonLoanDetailResp.FlowPackBean> flowPack = new ArrayList<>();
    private LinearLayout mLLFlowNode;
    private String currentPerson;

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerPsonLoanEditComponent
                .builder()
                .appComponent(appComponent)
                .psonLoanEditModule(new PsonLoanEditModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(Bundle savedInstanceState) {
        return R.layout.home_activity_pson_loan_edit;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        Intent intent = getIntent();
        psonLoanId = intent.getStringExtra("PsonLoanId");
        mRlPsonloan = (RelativeLayout) findViewById(R.id.rl_psonloan);
        mIvTopBack = (ImageView) findViewById(R.id.iv_top_back);
        mIvTopBack.setOnClickListener(v -> finish());
        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mTvTitle.setText("个人借款");
        mTvTitle.setOnClickListener(v -> finish());
        mIvTopSelect = (ImageView) findViewById(R.id.iv_top_select);
        mIvTopSelect.setVisibility(View.GONE);

        mEdtLoanNum = (EditText) findViewById(R.id.edt_loan_num);
        mEdtLoanTime = (EditText) findViewById(R.id.edt_loan_time);
        mEdtProposer = (EditText) findViewById(R.id.edt_proposer);
        mEdtCompany = (EditText) findViewById(R.id.edt_company);
        mEdtDepartment = (EditText) findViewById(R.id.edt_department);
        mEdtFristLeader = (EditText) findViewById(R.id.edt_frist_leader);
        mEdtLoanReason = (EditText) findViewById(R.id.edt_loan_reason);
        mEdtLoanAmount = (EditText) findViewById(R.id.edt_loan_amount);
        mEdtPaymentTerm = (EditText) findViewById(R.id.edt_payment_term);
        mEdtAccountName = (EditText) findViewById(R.id.edt_account_name);
        mEdtOpenactBank = (EditText) findViewById(R.id.edt_openact_bank);
        mEdtBankAccount = (EditText) findViewById(R.id.edt_bank_account);
        mLLFlowNode = (LinearLayout) findViewById(R.id.ll_flow_node);
        mPresenter.getrPsonLoanDetail(psonLoanId);
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

    }

    @Override
    public void showPLDetail(PsonLoanDetailResp psonLoanDetailResp) {
        currentPerson = psonLoanDetailResp.getCurrentPerson().trim();

//        if (!getUserName().equals(currentPerson) || !currentPerson.equals(psonLoanDetailResp.getCwJKState().trim())){
//            mRbAgree.setClickable(false);
//            mRbRefuse.setClickable(false);
//            mEdtApprover.setEnabled(false);
//            mEdtApprover.setFocusable(false);
//            mEdtRemark.setEnabled(false);
//            mEdtRemark.setFocusable(false);
//        }

        mEdtLoanNum.setText(psonLoanDetailResp.getCwPnum());
        mEdtLoanTime.setText(psonLoanDetailResp.getSqDate());
        mEdtProposer.setText(psonLoanDetailResp.getCwPpersonal());
        mEdtCompany.setText(psonLoanDetailResp.getCwPcompany());
        mEdtDepartment.setText(psonLoanDetailResp.getCwPdepartment());
        mEdtFristLeader.setText(psonLoanDetailResp.getCwPLeader());
        mEdtLoanReason.setText(psonLoanDetailResp.getCwPreason());
        mEdtLoanAmount.setText(psonLoanDetailResp.getCwPmoney());
        mEdtPaymentTerm.setText(psonLoanDetailResp.getCwPmode());
        mEdtAccountName.setText(psonLoanDetailResp.getCwRpname());
        mEdtOpenactBank.setText(psonLoanDetailResp.getCwRpbank());
        mEdtBankAccount.setText(psonLoanDetailResp.getCwRpnum());

        flowPack.clear();
        flowPack.addAll(psonLoanDetailResp.getFlowPack());
        for (int position = 0; position<flowPack.size(); position++){
            setFilePack(position);
        }



    }

    private void setFilePack(int position) {

        View inflate = LayoutInflater.from(this).inflate(R.layout.home_add_plflownode, null);
        PsonLoanDetailResp.FlowPackBean flowPackBean = flowPack.get(position);

        TextView mTvNodeName = (TextView) inflate.findViewById(R.id.tv_node_name);
        EditText mEdtApprover = (EditText) inflate.findViewById(R.id.edt_approver);
        RadioGroup mRgAprState = (RadioGroup) inflate.findViewById(R.id.rg_apr_state);
        RadioButton mRbAgree = (RadioButton) inflate.findViewById(R.id.rb_agree);
        RadioButton mRbRefuse = (RadioButton) inflate.findViewById(R.id.rb_refuse);
        EditText mEdtRemark = (EditText) inflate.findViewById(R.id.edt_remark);

        mTvNodeName.setText(flowPackBean.getNodeName());
        mEdtApprover.setText(flowPackBean.getNodePerson());

        if (!getUserName().equals(currentPerson) || !currentPerson.equals(flowPackBean.getNodePerson().trim())){
            mRbAgree.setClickable(false);
            mRbRefuse.setClickable(false);
            mEdtApprover.setEnabled(false);
            mEdtApprover.setFocusable(false);
            mEdtRemark.setEnabled(false);
            mEdtRemark.setFocusable(false);
        }

        if ("同意".equals(flowPackBean.getNodeMemo())) {
            mRbAgree.setChecked(true);
        } else if ("不同意".equals(flowPackBean.getNodeMemo())) {
            mRbRefuse.setChecked(true);
        }
        mEdtRemark.setText(flowPackBean.getNodeMemotext());

        mLLFlowNode.addView(inflate);
    }

}
