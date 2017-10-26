package com.hyjt.home.mvp.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
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
import com.hyjt.home.di.component.DaggerToCompLoanCreateComponent;
import com.hyjt.home.di.module.ToCompLoanCreateModule;
import com.hyjt.home.mvp.contract.ToCompLoanCreateContract;
import com.hyjt.home.mvp.model.entity.Resp.CpContractListResp;
import com.hyjt.home.mvp.presenter.ToCompLoanCreatePresenter;

import com.hyjt.home.R;

@Route(path = "/home/ToCompLoanCreateActivity")
public class ToCompLoanCreateActivity extends BaseActivity<ToCompLoanCreatePresenter> implements ToCompLoanCreateContract.View {

    private android.widget.RelativeLayout mRlTocomploan;
    private android.widget.ImageView mIvTopBack;
    private android.widget.TextView mTvTitle;
    private android.widget.ImageView mIvTopSelect;
    private android.widget.LinearLayout mLlBottomBtn;
    private android.widget.Button mBtnCreateLoan;
    private android.widget.ScrollView mSlvPsonloan;
    private android.widget.EditText mEdtProposer;
    private android.widget.EditText mEdtCompanyName;
    private android.widget.EditText mEdtFristLeader;
    private android.widget.EditText mEdtExpenseReason;
    private android.widget.EditText mEdtExpenseAmount;
    private android.widget.EditText mEdtCompactNub;
    private android.widget.EditText mEdtCreditPeriod;
    private android.widget.EditText mEdtExpenseType;
    private android.widget.Button mBtnSelBankAccount;
    private android.widget.LinearLayout mLlTransferMsg;
    private android.widget.EditText mEdtAccountName;
    private android.widget.EditText mEdtOpenactBank;
    private android.widget.EditText mEdtBankAccount;
    private android.widget.EditText mEdtRemark;
    private android.widget.RelativeLayout mRlAddFile;
    private android.widget.Button mBtnAddFile;
    private android.widget.LinearLayout mLlFilePack;
    private Button mBtnSelContract;
    private Button mBtnSelSupplier;
    private EditText mEdtSupplier;

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerToCompLoanCreateComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .toCompLoanCreateModule(new ToCompLoanCreateModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(Bundle savedInstanceState) {
        return R.layout.home_activity_to_comp_loan_create; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        initView();
        mBtnSelContract.setOnClickListener(v -> mPresenter.getContractList("0"));
        mBtnSelSupplier.setOnClickListener(v -> mPresenter.getContractList("1"));
    }


    @Override
    public void showMessage(@NonNull String message) {
    }

    @Override
    public void killMyself() {
        finish();
    }


    private void initView() {
        mRlTocomploan = (RelativeLayout) findViewById(R.id.rl_tocomploan);
        mIvTopBack = (ImageView) findViewById(R.id.iv_top_back);
        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mIvTopSelect = (ImageView) findViewById(R.id.iv_top_select);
        mLlBottomBtn = (LinearLayout) findViewById(R.id.ll_bottom_btn);
        mBtnCreateLoan = (Button) findViewById(R.id.btn_create_loan);
        mSlvPsonloan = (ScrollView) findViewById(R.id.slv_psonloan);
        mEdtProposer = (EditText) findViewById(R.id.edt_proposer);
        mEdtCompanyName = (EditText) findViewById(R.id.edt_company_name);
        mEdtFristLeader = (EditText) findViewById(R.id.edt_frist_leader);
        mEdtExpenseReason = (EditText) findViewById(R.id.edt_expense_reason);
        mEdtExpenseAmount = (EditText) findViewById(R.id.edt_expense_amount);
        mEdtCompactNub = (EditText) findViewById(R.id.edt_compact_nub);
        mEdtCreditPeriod = (EditText) findViewById(R.id.edt_credit_period);
        mEdtExpenseType = (EditText) findViewById(R.id.edt_expense_type);
        mBtnSelBankAccount = (Button) findViewById(R.id.btn_sel_bank_account);
        mLlTransferMsg = (LinearLayout) findViewById(R.id.ll_transfer_msg);
        mEdtAccountName = (EditText) findViewById(R.id.edt_account_name);
        mEdtOpenactBank = (EditText) findViewById(R.id.edt_openact_bank);
        mEdtBankAccount = (EditText) findViewById(R.id.edt_bank_account);
        mEdtRemark = (EditText) findViewById(R.id.edt_remark);
        mRlAddFile = (RelativeLayout) findViewById(R.id.rl_add_file);
        mBtnAddFile = (Button) findViewById(R.id.btn_add_file);
        mLlFilePack = (LinearLayout) findViewById(R.id.ll_file_pack);
        mBtnSelContract = (Button) findViewById(R.id.btn_sel_contract);
        mBtnSelSupplier = (Button) findViewById(R.id.btn_sel_supplier);
        mEdtSupplier = (EditText) findViewById(R.id.edt_supplier);
    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showContractList(CpContractListResp cpContractListResp) {

    }
}
