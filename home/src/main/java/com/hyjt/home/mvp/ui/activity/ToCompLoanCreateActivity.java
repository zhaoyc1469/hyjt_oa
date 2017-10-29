package com.hyjt.home.mvp.ui.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
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
import com.hyjt.frame.utils.PermissionUtil;
import com.hyjt.frame.utils.UiUtils;
import com.hyjt.home.di.component.DaggerToCompLoanCreateComponent;
import com.hyjt.home.di.module.ToCompLoanCreateModule;
import com.hyjt.home.mvp.contract.ToCompLoanCreateContract;
import com.hyjt.home.mvp.model.entity.Reqs.CompLoanCreateReqs;
import com.hyjt.home.mvp.model.entity.Reqs.PsonLoanCreateReqs;
import com.hyjt.home.mvp.model.entity.Resp.CpContractListResp;
import com.hyjt.home.mvp.model.entity.Resp.CpSupplierListResp;
import com.hyjt.home.mvp.model.entity.Resp.ImgUploadResp;
import com.hyjt.home.mvp.model.entity.Resp.PLBankAccountResp;
import com.hyjt.home.mvp.model.entity.Resp.PLCompanyResp;
import com.hyjt.home.mvp.model.entity.Resp.PLFristLeaderResp;
import com.hyjt.home.mvp.presenter.ToCompLoanCreatePresenter;

import com.hyjt.home.R;
import com.hyjt.home.mvp.ui.adapter.BankAccountAdapter;
import com.hyjt.home.mvp.ui.adapter.CLContractAdapter;
import com.hyjt.home.mvp.ui.adapter.CLSupplierAdapter;
import com.hyjt.home.mvp.ui.adapter.ComnStringAdapter;
import com.hyjt.home.mvp.ui.adapter.FrstLdAdapter;
import com.hyjt.home.mvp.ui.view.Constant;
import com.hyjt.home.mvp.ui.view.DateTimePickDialog;
import com.hyjt.home.mvp.ui.view.GetSingleSelectItem;
import com.hyjt.home.utils.ImgUtil;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;

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
    private android.widget.EditText mEdtAccountName;
    private android.widget.EditText mEdtOpenactBank;
    private android.widget.EditText mEdtBankAccount;
    private android.widget.EditText mEdtRemark;
    private Button mBtnSelContract;
    private Button mBtnSelSupplier;
    private EditText mEdtSupplier;
    private CpContractListResp.RowsBean ContractRowsBean;
    private ToCompLoanCreateActivity mContext;
    private List<PsonLoanCreateReqs.FilePackBean> FileList = new ArrayList<>();
    private ArrayList<Uri> mFileURLList = new ArrayList<>();
    private ProgressDialog progressDialog;
    private PLFristLeaderResp.FlowDetailsBean flowDetailsBean;
    private CpSupplierListResp.SupPackBean supPackBean;

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

        mEdtExpenseType.setOnTouchListener(new GetSingleSelectItem(
                this, mEdtExpenseType, "支付方式", Constant.compPayTypeArr, false));
        mEdtProposer.setText(getUserName());
        setTimeEdit(mEdtCreditPeriod);
        mEdtCompanyName.setOnClickListener(v -> mPresenter.getTCLCompany());
        mEdtFristLeader.setOnClickListener(v -> {
            progressDialog = ProgressDialog.show(this, null, "加载首签领导…");
            mPresenter.getFristLeader("对公预付款借款");
        });
        mBtnSelContract.setOnClickListener(v -> mPresenter.getContractList("0"));
        mBtnSelSupplier.setOnClickListener(v -> mPresenter.getSupplierList("1"));
        mBtnCreateLoan.setOnClickListener(v -> sendCompLoanCreate());
    }

    private void sendCompLoanCreate() {
        if (TextUtils.isEmpty(mEdtCompanyName.getText())) {
            showMessage("公司名称不能为空");
            return;
        }
        if (TextUtils.isEmpty(mEdtFristLeader.getText())) {
            showMessage("首签领导不能为空");
            return;
        }
        if (TextUtils.isEmpty(mEdtExpenseReason.getText())) {
            showMessage("借款事由不能为空");
            return;
        }
        if (TextUtils.isEmpty(mEdtExpenseAmount.getText())) {
            showMessage("借款金额不能为空");
            return;
        }
        if (TextUtils.isEmpty(mEdtExpenseType.getText())) {
            showMessage("收款方式不能为空");
            return;
        }
        if (TextUtils.isEmpty(mEdtCompactNub.getText())) {
            showMessage("合同编号不能为空");
            return;
        }

        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this)
                .setTitle("发起借款")
                .setMessage("确定发起对公借款?")
                .setPositiveButton("确定", (dialog, which) -> {
                    if (mFileURLList.size() > 0) {
                        progressDialog = ProgressDialog.show(this, null, "正在上传附件…");
                        mPresenter.sendFile(mFileURLList);
                    } else {
                        fileUploadOk(new ArrayList<>());
                    }
                }).setNegativeButton("返回", (dialog, which) -> dialog.dismiss());
        builder.show();
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
        mEdtAccountName = (EditText) findViewById(R.id.edt_account_name);
        mEdtOpenactBank = (EditText) findViewById(R.id.edt_openact_bank);
        mEdtBankAccount = (EditText) findViewById(R.id.edt_bank_account);
        mEdtRemark = (EditText) findViewById(R.id.edt_remark);
        mBtnSelContract = (Button) findViewById(R.id.btn_sel_contract);
        mBtnSelSupplier = (Button) findViewById(R.id.btn_sel_supplier);
        mEdtSupplier = (EditText) findViewById(R.id.edt_supplier);
    }

    @Override
    public void hideLoading() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void showContractList(CpContractListResp cpContractListResp) {
        List<CpContractListResp.RowsBean> rowsBeanList = cpContractListResp.getRows();
        ContractRowsBean = new CpContractListResp.RowsBean();

        AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.home_dialog_sel_list, null);
        dialog.setTitle("选择合同");
        dialog.setView(layout).setCancelable(false)
                .setNegativeButton("取消", (dialog1, id) -> dialog1.cancel());
        AlertDialog accAlert = dialog.create();
        ListView accList = (ListView) layout.findViewById(R.id.accList);

        CLContractAdapter contractAdapter = new CLContractAdapter(mContext, rowsBeanList);
        accList.setAdapter(contractAdapter);

        contractAdapter.setItemClickListener(position -> {
            CpContractListResp.RowsBean bean = rowsBeanList.get(position);
            mEdtCompactNub.setText(bean.getCwCOnum());
            this.ContractRowsBean.setCwCOnum(bean.getCwCOnum());
            this.ContractRowsBean.setCwC_id(bean.getCwC_id());
            accAlert.dismiss();
        });
        InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(mEdtFristLeader.getWindowToken(), 0);
        accAlert.show();
    }

    @Override
    public void fileUploadOk(List<ImgUploadResp> imgUploadList) {
        if (imgUploadList.size() > 0) {
            for (int i = 0; i < imgUploadList.size(); i++) {
                FileList.add(new PsonLoanCreateReqs.FilePackBean(imgUploadList.get(i).getId()
                        , imgUploadList.get(i).getName()));
            }
        }

        CompLoanCreateReqs clCreateReqs = new CompLoanCreateReqs();
        clCreateReqs.setCwCcompany(mEdtCompanyName.getText().toString());
        clCreateReqs.setCwCLeader(mEdtFristLeader.getText().toString());
        clCreateReqs.setCwCreason(mEdtExpenseReason.getText().toString());
        clCreateReqs.setCwCmoney(mEdtExpenseAmount.getText().toString());
        clCreateReqs.setCwCmode(mEdtExpenseType.getText().toString());
        clCreateReqs.setCwC_id(ContractRowsBean.getCwC_id());
        clCreateReqs.setCwCOnum(mEdtCompactNub.getText().toString());
        clCreateReqs.setCwCcontracTime(mEdtCreditPeriod.getText().toString());

        clCreateReqs.setCwCSupplierId(supPackBean.getCwCSupplierID());
        clCreateReqs.setCwCSupplierI(mEdtSupplier.getText().toString());
        clCreateReqs.setCwCSupbank(mEdtOpenactBank.getText().toString());
        clCreateReqs.setCwCSupnum(mEdtBankAccount.getText().toString());
        clCreateReqs.setCwCmodetext(mEdtRemark.getText().toString());
        clCreateReqs.setFlowid(flowDetailsBean.getFlowid());

        mPresenter.createCompLoan(clCreateReqs);
    }



    private void setTimeEdit(EditText finishTime) {
        finishTime.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                Calendar c = Calendar.getInstance();
                new DateTimePickDialog(mContext, c).setOnDateTimeSetListener((dp, tp, year, monthOfYear, dayOfMonth, hourOfDay, minute) -> {
                    finishTime.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth + " "
                            + hourOfDay + ":" + minute + ":00");
                    // 保存选择后时间
                    c.set(Calendar.YEAR, year);
                    c.set(Calendar.MONTH, monthOfYear);
                    c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    c.set(Calendar.HOUR_OF_DAY, hourOfDay);
                    c.set(Calendar.MINUTE, minute);
                });
            }
            return true;
        });
    }

    @Override
    public RxPermissions getRxPermissions() {
        return new RxPermissions(this);
    }

    @Override
    public void showCompanyList(PLCompanyResp plCompanyResp) {
        List<String> companyList = plCompanyResp.getCompany();

        AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.home_dialog_sel_list, null);
        dialog.setTitle("选择公司");
        dialog.setView(layout).setCancelable(false)
                .setNegativeButton("取消", (dialog1, id) -> dialog1.cancel());
        AlertDialog accAlert = dialog.create();
        ListView accList = (ListView) layout.findViewById(R.id.accList);

        ComnStringAdapter comnStringAdapter = new ComnStringAdapter(mContext, companyList);
        accList.setAdapter(comnStringAdapter);

        comnStringAdapter.setItemClickListener(position -> {
            mEdtCompanyName.setText(companyList.get(position));
            accAlert.dismiss();
        });
        InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(mEdtFristLeader.getWindowToken(), 0);
        accAlert.show();
    }

    @Override
    public void loadFlowNode(PLFristLeaderResp plFristLeaderResp) {

        List<PLFristLeaderResp.FlowDetailsBean> flowDetails = plFristLeaderResp.getFlowDetails();
        flowDetailsBean = new PLFristLeaderResp.FlowDetailsBean();

        AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.home_dialog_sel_list, null);
        dialog.setTitle("选择首签领导");
        dialog.setView(layout).setCancelable(false)
                .setNegativeButton("取消", (dialog1, id) -> dialog1.cancel());
        AlertDialog accAlert = dialog.create();
        ListView accList = (ListView) layout.findViewById(R.id.accList);

        FrstLdAdapter frstLdAdapter = new FrstLdAdapter(mContext, flowDetails);
        accList.setAdapter(frstLdAdapter);

        frstLdAdapter.setItemClickListener(position -> {
            PLFristLeaderResp.FlowDetailsBean flowDetailsBean = flowDetails.get(position);
            mEdtFristLeader.setText(flowDetailsBean.getLeader());
            this.flowDetailsBean.setFlowid(flowDetailsBean.getFlowid());
            accAlert.dismiss();
        });
        InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(mEdtFristLeader.getWindowToken(), 0);
        accAlert.show();
    }

    @Override
    public void showSupplierList(CpSupplierListResp cpSupplierListResp) {

        List<CpSupplierListResp.SupPackBean> supPack = cpSupplierListResp.getSupPack();
        supPackBean = new CpSupplierListResp.SupPackBean();

        AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.home_dialog_sel_list, null);
        dialog.setTitle("选择合同");
        dialog.setView(layout).setCancelable(false)
                .setNegativeButton("取消", (dialog1, id) -> dialog1.cancel());
        AlertDialog accAlert = dialog.create();
        ListView accList = (ListView) layout.findViewById(R.id.accList);

        CLSupplierAdapter SupplierAdapter = new CLSupplierAdapter(mContext, supPack);
        accList.setAdapter(SupplierAdapter);

        SupplierAdapter.setItemClickListener(position -> {
            CpSupplierListResp.SupPackBean bean = supPack.get(position);
            mEdtSupplier.setText(bean.getCwCSupplierI());
            mEdtOpenactBank.setText(bean.getCwCSupbank());
            mEdtBankAccount.setText(bean.getCwCSupnum());
            this.supPackBean.setCwCSupplierID(bean.getCwCSupplierID());
            accAlert.dismiss();
        });
        InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(mEdtFristLeader.getWindowToken(), 0);
        accAlert.show();
    }

}
