package com.hyjt.home.mvp.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.hyjt.frame.base.BaseActivity;
import com.hyjt.frame.di.component.AppComponent;
import com.hyjt.frame.utils.UiUtils;
import com.hyjt.home.di.component.DaggerToCompLoanCreateComponent;
import com.hyjt.home.di.module.ToCompLoanCreateModule;
import com.hyjt.home.mvp.contract.ToCompLoanCreateContract;
import com.hyjt.home.mvp.model.entity.Reqs.CompLoanCreateReqs;
import com.hyjt.home.mvp.model.entity.Resp.CpContractListResp;
import com.hyjt.home.mvp.model.entity.Resp.PLFristLeaderResp;
import com.hyjt.home.mvp.presenter.ToCompLoanCreatePresenter;

import com.hyjt.home.R;
import com.hyjt.home.mvp.ui.adapter.CLContractAdapter;

import java.util.List;

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
    private CpContractListResp.RowsBean ContractRowsBean;
    private ToCompLoanCreateActivity mContext;

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
        mBtnCreateLoan.setOnClickListener(v -> sendCompLoanCreate());
    }

    private void sendCompLoanCreate() {
        if (TextUtils.isEmpty(mEdtCompanyName.getText())){
            showMessage("公司名称不能为空");
            return;
        }
        if (TextUtils.isEmpty(mEdtFristLeader.getText())){
            showMessage("首签领导不能为空");
            return;
        }
        if (TextUtils.isEmpty(mEdtExpenseReason.getText())){
            showMessage("借款事由不能为空");
            return;
        }
        if (TextUtils.isEmpty(mEdtExpenseAmount.getText())){
            showMessage("借款金额不能为空");
            return;
        }
        if (TextUtils.isEmpty(mEdtExpenseType.getText())){
            showMessage("收款方式不能为空");
            return;
        }
        if (TextUtils.isEmpty(mEdtCompactNub.getText())){
            showMessage("合同编号不能为空");
            return;
        }
        CompLoanCreateReqs clCreateReqs = new CompLoanCreateReqs();
        clCreateReqs.setCwCcompany(mEdtCompanyName.getText().toString());
        clCreateReqs.setCwCLeader(mEdtFristLeader.getText().toString());
        clCreateReqs.setCwCreason(mEdtExpenseReason.getText().toString());
        clCreateReqs.setCwCmoney(mEdtExpenseAmount.getText().toString());
        clCreateReqs.setCwCmode(mEdtExpenseType.getText().toString());
//        clCreateReqs.setCwC_id(mEdtCompactNub.getText().toString());
        clCreateReqs.setCwCOnum(mEdtCompactNub.getText().toString());
//        CwCcontracTime
//        clCreateReqs.setCwCcontracTime(mEdtCompanyName);



        mPresenter.createCompLoan(clCreateReqs);
    }


    @Override
    public void showMessage(@NonNull String message) {
        shortToast(message);
        UiUtils.snackbarText(message);
    }

    @Override
    public void killMyself() {
        finish();
    }


    private void initView() {
        mContext = this;
        mRlTocomploan = (RelativeLayout) findViewById(R.id.rl_tocomploan);
        mIvTopBack = (ImageView) findViewById(R.id.iv_top_back);
        mIvTopBack.setOnClickListener(v -> finish());
        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mTvTitle.setText("对公借款申请");
        mTvTitle.setOnClickListener(v -> finish());
        mIvTopSelect = (ImageView) findViewById(R.id.iv_top_select);
        mIvTopSelect.setVisibility(View.GONE);
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
        List<CpContractListResp.RowsBean> rowsBeanList = cpContractListResp.getRows();
        ContractRowsBean = new CpContractListResp.RowsBean();

        AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.home_dialog_sel_list, null);
        dialog.setTitle("选择首签领导");
        dialog.setView(layout).setCancelable(false)
                .setNegativeButton("取消", (dialog1, id) -> dialog1.cancel());
        AlertDialog accAlert = dialog.create();
        ListView accList = (ListView) layout.findViewById(R.id.accList);

        CLContractAdapter contractAdapter = new CLContractAdapter(mContext, rowsBeanList);
        accList.setAdapter(contractAdapter);

        contractAdapter.setItemClickListener(position -> {
            CpContractListResp.RowsBean bean = rowsBeanList.get(position);
            mEdtFristLeader.setText(bean.getCwCOnum());
            this.ContractRowsBean.setCwCOnum(bean.getCwCOnum());
            accAlert.dismiss();
        });
        InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(mEdtFristLeader.getWindowToken(), 0);
        accAlert.show();
    }
}
