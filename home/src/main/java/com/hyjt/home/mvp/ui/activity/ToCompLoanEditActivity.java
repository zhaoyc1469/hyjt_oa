package com.hyjt.home.mvp.ui.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
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
import com.bumptech.glide.Glide;
import com.hyjt.frame.base.BaseActivity;
import com.hyjt.frame.di.component.AppComponent;
import com.hyjt.frame.event.RefreshListEvent;
import com.hyjt.home.di.component.DaggerToCompLoanEditComponent;
import com.hyjt.home.di.module.ToCompLoanEditModule;
import com.hyjt.home.mvp.contract.ToCompLoanEditContract;
import com.hyjt.home.mvp.model.entity.Reqs.ClNodeApproveReqs;
import com.hyjt.home.mvp.model.entity.Reqs.CompLoanCreateReqs;
import com.hyjt.home.mvp.model.entity.Resp.CompLoanDetailResp;
import com.hyjt.home.mvp.model.entity.Resp.CpContractListResp;
import com.hyjt.home.mvp.model.entity.Resp.CpSupplierListResp;
import com.hyjt.home.mvp.model.entity.Resp.PLCompBankAccountResp;
import com.hyjt.home.mvp.model.entity.Resp.PLFristLeaderResp;
import com.hyjt.home.mvp.presenter.ToCompLoanEditPresenter;

import com.hyjt.home.R;
import com.hyjt.home.mvp.ui.adapter.CLContractAdapter;
import com.hyjt.home.mvp.ui.adapter.CLSupplierAdapter;
import com.hyjt.home.mvp.ui.adapter.FrstLdAdapter;
import com.hyjt.home.mvp.ui.adapter.PlCompBankActAdapter;
import com.hyjt.home.mvp.ui.view.DatePickDialog;

import org.simple.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


@Route(path = "/home/ToCompLoanEditActivity")
public class ToCompLoanEditActivity extends BaseActivity<ToCompLoanEditPresenter> implements ToCompLoanEditContract.View {


    private String compLoanId;
    private android.widget.RelativeLayout mRlComploan;
    private android.widget.ImageView mIvTopBack;
    private android.widget.TextView mTvTitle;
    private android.widget.ImageView mIvTopSelect;
    private android.widget.LinearLayout mLlBottomBtn;
    private android.widget.ScrollView mSlvComploan;
    private android.widget.EditText mEdtProposer;
    private android.widget.EditText mEdtCompanyName;
    private android.widget.EditText mEdtFristLeader;
    private android.widget.EditText mEdtExpenseReason;
    private android.widget.EditText mEdtExpenseAmount;
    private android.widget.Button mBtnSelContract;
    private android.widget.EditText mEdtCompactNub;
    private android.widget.EditText mEdtCreditPeriod;
    private android.widget.Button mBtnSelSupplier;
    private android.widget.EditText mEdtSupplier;
    private android.widget.EditText mEdtExpenseType;
    private android.widget.EditText mEdtOpenactBank;
    private android.widget.EditText mEdtBankAccount;
    private android.widget.EditText mEdtRemark;
    private android.widget.RelativeLayout mRlAddFile;
    private android.widget.Button mBtnAddFile;
    private android.widget.LinearLayout mLlFilePack;
    private EditText mEdtApplyTime;
    private RelativeLayout mRlSelSupplier;
    private ProgressDialog progressDialog;
    private String compLoanType;
    private List<CompLoanDetailResp.FlowPackBean> flowPack = new ArrayList<>();
    private ToCompLoanEditActivity mContext;
    private LinearLayout mLlFlowNode;
    private RelativeLayout mRlSendAccount;
    private EditText mEdtSendAccount;
    private RelativeLayout mRlTellerConfirm;
    private ImageView mIvTellerSign;
    private Button mBtnTellerConfirm;
    private RelativeLayout mRlReceiverConfirm;
    private ImageView mIvReceiverSign;
    private Button mBtnReceiverConfirm;
    private String currentPerson = "";
    private CompLoanDetailResp compLoanDetailResp;
    private PLFristLeaderResp.FlowDetailsBean flowDetailsBean = new PLFristLeaderResp.FlowDetailsBean();
    private PLCompBankAccountResp.BankPackBean bankPackBean;
    private Button mBtnEditLoan;
    private Button mBtnDelLoan;
    private CpSupplierListResp.SupPackBean supPackBean = new CpSupplierListResp.SupPackBean();
    private CpContractListResp.RowsBean ContractRowsBean = new CpContractListResp.RowsBean();
    private RelativeLayout mRlSendBank;
    private EditText mEdtSendBank;

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerToCompLoanEditComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .toCompLoanEditModule(new ToCompLoanEditModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(Bundle savedInstanceState) {
        return R.layout.home_activity_to_comp_loan_edit; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        mContext = this;
        Intent intent = getIntent();
        compLoanId = intent.getStringExtra("CompLoanId");
        compLoanType = intent.getStringExtra("CompLoanType");
        initView();

        progressDialog = ProgressDialog.show(this, null, "加载中…");
        mPresenter.getCompLoanDetail(compLoanId);
    }

    @Override
    public void showMessage(@NonNull String message) {
        shortToast(message);
    }

    @Override
    public void killMyself() {
        finish();
        EventBus.getDefault().post(new RefreshListEvent(), "Refresh_List");
    }


    @Override
    public void hideLoading() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void showCLDetail(CompLoanDetailResp compLoanDetailResp) {

        this.compLoanDetailResp = compLoanDetailResp;
        flowDetailsBean.setFlowid(compLoanDetailResp.getFlowid());
        ContractRowsBean.setCwC_id(compLoanDetailResp.getCwC_id());
        ContractRowsBean.setCwCOnum(compLoanDetailResp.getCwCOnum());
        supPackBean.setCwCSupplierID(compLoanDetailResp.getCwCSupplierID());


        if (!TextUtils.isEmpty(compLoanDetailResp.getCurrentPerson())) {
            currentPerson = compLoanDetailResp.getCurrentPerson().trim();
        }

        mEdtProposer.setText(compLoanDetailResp.getCwCpersonal());
        mEdtApplyTime.setText(compLoanDetailResp.getSqDate());
        mEdtCompanyName.setText(compLoanDetailResp.getCwCcompany());
        mEdtFristLeader.setText(compLoanDetailResp.getCwCLeader());
        mEdtExpenseReason.setText(compLoanDetailResp.getCwCreason());
        mEdtExpenseAmount.setText(compLoanDetailResp.getCwCmoney());
        mEdtCompactNub.setText(compLoanDetailResp.getCwC_id());
        mEdtCompactNub.setText(compLoanDetailResp.getCwCOnum());
        mEdtCreditPeriod.setText(compLoanDetailResp.getCwCcontracTime());
        mEdtSupplier.setText(compLoanDetailResp.getCwCSupplierI());
        mEdtExpenseType.setText(compLoanDetailResp.getCwCmode());

        mEdtOpenactBank.setText(compLoanDetailResp.getCwCSupbank());
        mEdtBankAccount.setText(compLoanDetailResp.getCwCSupnum());
        mEdtRemark.setText(compLoanDetailResp.getCwCmodetext());

        flowPack.clear();
        flowPack.addAll(compLoanDetailResp.getFlowPack());
        for (int position = 0; position < flowPack.size(); position++) {
            setFlowPack(position);
        }


//        if (!"提交".equals(compLoanDetailResp.getCwJKState()) ||
//                !getUserName().equals(compLoanDetailResp.getCwCpersonal())) {
//        }

        if (!"提交".equals(compLoanDetailResp.getCwJKState()) ||
                !"1".equals(compLoanType)) {
            mLlBottomBtn.setVisibility(View.GONE);
            mRlSelSupplier.setVisibility(View.GONE);
            mBtnSelContract.setVisibility(View.GONE);
            mBtnSelContract.setVisibility(View.GONE);
            mEdtProposer.setFocusable(false);
            mEdtApplyTime.setFocusable(false);
            mEdtCompanyName.setFocusable(false);
            mEdtFristLeader.setFocusable(false);
            mEdtExpenseReason.setFocusable(false);
            mEdtExpenseAmount.setFocusable(false);
            mEdtCompactNub.setFocusable(false);
            mEdtCreditPeriod.setFocusable(false);
            mEdtSupplier.setFocusable(false);
            mEdtExpenseType.setFocusable(false);
            mEdtOpenactBank.setFocusable(false);
            mEdtBankAccount.setFocusable(false);
            mEdtRemark.setFocusable(false);
        } else {
            setTimeEdit(mEdtCreditPeriod);
            mEdtCompanyName.setOnClickListener(v -> mPresenter.getCLCompany());
            mEdtFristLeader.setOnClickListener(v -> mPresenter.getFristLeader("对公预付款借款"));
            mBtnSelContract.setOnClickListener(v -> mPresenter.getContractList("0"));
            mBtnSelSupplier.setOnClickListener(v -> mPresenter.getSupplierList("1"));
        }


        mEdtSendBank.setText(compLoanDetailResp.getCwBname());
        mEdtSendAccount.setText(compLoanDetailResp.getCwBnum());


        if ("审批完成".equals(compLoanDetailResp.getFlowState())) {
            if (!"0".equals(compLoanDetailResp.getCashierQren())) {
                mBtnTellerConfirm.setVisibility(View.GONE);
                Glide.with(this).load(getString(R.string.home_base_url) +
                        compLoanDetailResp.getCashierQren()).into(mIvTellerSign);
            } else {
                if ("3".equals(compLoanType)) {
                    mBtnTellerConfirm.setOnClickListener(v -> {
                        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                                .setTitle("出纳人员确认")
                                .setMessage("是否确认出纳?")
                                .setPositiveButton("确定", (dialog, which) -> {
                                    progressDialog = ProgressDialog.show(this, null, "出纳人员确认中…");
                                    mPresenter.tellerConfirm(compLoanId);
                                })
                                .setNegativeButton("返回", (dialog, which) -> dialog.dismiss());
                        builder.show();
                    });
                    mRlSendAccount.setVisibility(View.VISIBLE);
                    mRlSendBank.setVisibility(View.VISIBLE);
                } else {
                    mRlTellerConfirm.setVisibility(View.GONE);
                }
            }
            if (!"0".equals(compLoanDetailResp.getCwCpersonalQren())) {
                mBtnReceiverConfirm.setVisibility(View.GONE);
                Glide.with(this).load(getString(R.string.home_base_url) +
                        compLoanDetailResp.getCwCpersonalQren()).into(mIvReceiverSign);
            } else {
                if ("1".equals(compLoanType) && !"0".equals(compLoanDetailResp.getCashierQren())) {
                    mBtnReceiverConfirm.setOnClickListener(v -> {

                        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                                .setTitle("收款人员确认")
                                .setMessage("是否确认已收款?")
                                .setPositiveButton("确定", (dialog, which) -> {
                                    progressDialog = ProgressDialog.show(this, null, "收款人员确认中…");
                                    mPresenter.receiverConfirm(compLoanId);
                                })
                                .setNegativeButton("返回", (dialog, which) -> dialog.dismiss());
                        builder.show();
                    });
                } else {
                    mRlReceiverConfirm.setVisibility(View.GONE);
                }
            }
        } else {
            mRlReceiverConfirm.setVisibility(View.GONE);
            mRlSendAccount.setVisibility(View.GONE);
            mRlSendBank.setVisibility(View.GONE);
            mRlTellerConfirm.setVisibility(View.GONE);
        }
    }


    private void setTimeEdit(EditText finishTime) {
        finishTime.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                Calendar c = Calendar.getInstance();
                new DatePickDialog(mContext, c).setOnDateTimeSetListener((dp, year, monthOfYear, dayOfMonth) -> {
                    finishTime.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                    // 保存选择后时间
                    c.set(Calendar.YEAR, year);
                    c.set(Calendar.MONTH, monthOfYear);
                    c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                });
            }
            return true;
        });
    }

    @Override
    public void showContractList(CpContractListResp cpContractListResp) {
        List<CpContractListResp.RowsBean> rowsBeanList = cpContractListResp.getRows();
        ContractRowsBean = new CpContractListResp.RowsBean();

        AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.home_dialog_sel_list, null);
        dialog.setTitle("  选择合同  ");
        dialog.setView(layout).setCancelable(false)
                .setNegativeButton("取消", (dialog1, id) -> dialog1.cancel());
        AlertDialog accAlert = dialog.create();
        ListView accList = (ListView) layout.findViewById(R.id.accList);

        CLContractAdapter contractAdapter = new CLContractAdapter(mContext, rowsBeanList);
        accList.setAdapter(contractAdapter);

        contractAdapter.setItemClickListener(position -> {
            CpContractListResp.RowsBean bean = rowsBeanList.get(position);
            mEdtCompactNub.setText(bean.getCwCOnum());
            mEdtSupplier.setText(bean.getCwCSupplierI());
            mEdtOpenactBank.setText(bean.getCwCSupbank());
            mEdtBankAccount.setText(bean.getCwCSupnum());
            this.supPackBean.setCwCSupplierID(bean.getCwCSupplierID());
            this.ContractRowsBean.setCwCOnum(bean.getCwCOnum());
            this.ContractRowsBean.setCwC_id(bean.getCwC_id());
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
        dialog.setTitle("  选择供应商  ");
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
    public void showAprBankAccount(PLCompBankAccountResp compBankAccountResp, EditText mEdtNodeSendBank, EditText mEdtSendAccount) {

        List<PLCompBankAccountResp.BankPackBean> bankPack = compBankAccountResp.getBankPack();
        this.bankPackBean = new PLCompBankAccountResp.BankPackBean();

        if (bankPack == null || bankPack.size() == 0) {
            new AlertDialog.Builder(this).setTitle("提示")//设置对话框标题
                    .setMessage("您没有预存银行账户信息！")//设置显示的内容
                    .setPositiveButton("确定", (dialog, which) -> dialog.dismiss()).show();
            return;
        }

        AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.home_dialog_sel_list, null);
        dialog.setTitle("选择银行账户");
        dialog.setView(layout).setCancelable(false)
                .setNegativeButton("取消", (dialog1, id) -> dialog1.cancel());
        AlertDialog accAlert = dialog.create();
        ListView accList = (ListView) layout.findViewById(R.id.accList);

        PlCompBankActAdapter compBankActAdapter = new PlCompBankActAdapter(mContext, bankPack);
        accList.setAdapter(compBankActAdapter);

        compBankActAdapter.setItemClickListener(position -> {
            PLCompBankAccountResp.BankPackBean flowDetailsBean = bankPack.get(position);
            mEdtNodeSendBank.setText(flowDetailsBean.getBankName());
            mEdtSendAccount.setText(flowDetailsBean.getBankNum());
            this.bankPackBean.setBankName(flowDetailsBean.getBankName());
            this.bankPackBean.setBankNum(flowDetailsBean.getBankNum());
            accAlert.dismiss();
        });
        accAlert.show();
    }

    private void initView() {
        mRlComploan = (RelativeLayout) findViewById(R.id.rl_comploan);
        mIvTopBack = (ImageView) findViewById(R.id.iv_top_back);
        mIvTopBack.setOnClickListener(v -> finish());
        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mTvTitle.setText("对公借款详情");
        mTvTitle.setOnClickListener(v -> finish());
        mIvTopSelect = (ImageView) findViewById(R.id.iv_top_select);
        mIvTopSelect.setVisibility(View.GONE);
        mLlBottomBtn = (LinearLayout) findViewById(R.id.ll_bottom_btn);
        mSlvComploan = (ScrollView) findViewById(R.id.slv_comploan);
        mEdtProposer = (EditText) findViewById(R.id.edt_proposer);
        mEdtApplyTime = (EditText) findViewById(R.id.edt_apply_time);
        mEdtCompanyName = (EditText) findViewById(R.id.edt_company_name);
        mEdtFristLeader = (EditText) findViewById(R.id.edt_frist_leader);
        mEdtExpenseReason = (EditText) findViewById(R.id.edt_expense_reason);
        mEdtExpenseAmount = (EditText) findViewById(R.id.edt_expense_amount);
        mBtnSelContract = (Button) findViewById(R.id.btn_sel_contract);
        mEdtCompactNub = (EditText) findViewById(R.id.edt_compact_nub);
        mEdtCreditPeriod = (EditText) findViewById(R.id.edt_credit_period);
        mBtnSelSupplier = (Button) findViewById(R.id.btn_sel_supplier);
        mEdtSupplier = (EditText) findViewById(R.id.edt_supplier);
        mEdtExpenseType = (EditText) findViewById(R.id.edt_expense_type);
        mEdtOpenactBank = (EditText) findViewById(R.id.edt_openact_bank);
        mEdtBankAccount = (EditText) findViewById(R.id.edt_bank_account);
        mEdtRemark = (EditText) findViewById(R.id.edt_remark);
        mRlAddFile = (RelativeLayout) findViewById(R.id.rl_add_file);
        mBtnAddFile = (Button) findViewById(R.id.btn_add_file);
        mLlFilePack = (LinearLayout) findViewById(R.id.ll_file_pack);
        mRlSelSupplier = (RelativeLayout) findViewById(R.id.rl_sel_supplier);
        mLlFlowNode = (LinearLayout) findViewById(R.id.ll_flow_node);
        mRlSendAccount = (RelativeLayout) findViewById(R.id.rl_send_account);
        mEdtSendAccount = (EditText) findViewById(R.id.edt_send_account);
        mRlTellerConfirm = (RelativeLayout) findViewById(R.id.rl_teller_confirm);
        mIvTellerSign = (ImageView) findViewById(R.id.iv_teller_sign);
        mBtnTellerConfirm = (Button) findViewById(R.id.btn_teller_confirm);
        mRlReceiverConfirm = (RelativeLayout) findViewById(R.id.rl_receiver_confirm);
        mIvReceiverSign = (ImageView) findViewById(R.id.iv_receiver_sign);
        mBtnReceiverConfirm = (Button) findViewById(R.id.btn_receiver_confirm);
        mBtnEditLoan = (Button) findViewById(R.id.btn_edit_loan);
        mBtnDelLoan = (Button) findViewById(R.id.btn_del_loan);


        mBtnEditLoan.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this)
                    .setTitle("编辑确认")
                    .setMessage("是否编辑该条对公借款?")
                    .setPositiveButton("确定", (dialog, which) -> editCloan())
                    .setNegativeButton("返回", (dialog, which) -> dialog.dismiss());
            builder.show();
        });
        mBtnDelLoan.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this)
                    .setTitle("删除确认")
                    .setMessage("是否删除该条对公借款?")
                    .setPositiveButton("确定", (dialog, which) -> mPresenter.delCompLoanDetail(compLoanId))
                    .setNegativeButton("返回", (dialog, which) -> dialog.dismiss());
            builder.show();
        });

        mRlSendBank = (RelativeLayout) findViewById(R.id.rl_send_bank);
        mEdtSendBank = (EditText) findViewById(R.id.edt_send_bank);
    }

    private void editCloan() {
        CompLoanCreateReqs clCreateReqs = new CompLoanCreateReqs();
        clCreateReqs.setId(compLoanId);
        clCreateReqs.setCwCnum(compLoanDetailResp.getCwCnum());
        clCreateReqs.setCwPcompany(mEdtCompanyName.getText().toString());
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
        mPresenter.editCompLoanDetail(clCreateReqs);
    }


    private void setFlowPack(int position) {

        View inflate = LayoutInflater.from(this).inflate(R.layout.home_add_plflownode, null);
        CompLoanDetailResp.FlowPackBean flowPackBean = flowPack.get(position);

        TextView mTvNodeName = (TextView) inflate.findViewById(R.id.tv_node_name);
        EditText mEdtApprover = (EditText) inflate.findViewById(R.id.edt_approver);
        RelativeLayout mRlNodeSendBank = (RelativeLayout) inflate.findViewById(R.id.rl_node_send_bank);
        EditText mEdtNodeSendBank = (EditText) inflate.findViewById(R.id.edt_node_send_bank);
        RelativeLayout mRlAccount = (RelativeLayout) inflate.findViewById(R.id.rl_company_act);
        RelativeLayout mRlSelAct = (RelativeLayout) inflate.findViewById(R.id.rl_sel_act);
        LinearLayout mLlAccount = (LinearLayout) inflate.findViewById(R.id.ll_company_act);
        RelativeLayout mRlRemark = (RelativeLayout) inflate.findViewById(R.id.rl_remark);
        EditText mEdtSendAccount = (EditText) inflate.findViewById(R.id.edt_send_account);
        Button mBtnSelAct = (Button) inflate.findViewById(R.id.btn_sel_bank_account);
        LinearLayout mLlAprBtn = (LinearLayout) inflate.findViewById(R.id.ll_apr_btn);
        ImageView mIvSign = (ImageView) inflate.findViewById(R.id.iv_teller_sign);
        mLlAprBtn.setVisibility(View.GONE);
        Button mBtnAgree = (Button) inflate.findViewById(R.id.btn_agree);
        Button mBtnRefuse = (Button) inflate.findViewById(R.id.btn_refuse);
        TextView mTvAprState = (TextView) inflate.findViewById(R.id.tv_apr_state);
        mTvAprState.setVisibility(View.GONE);
        EditText mEdtRemark = (EditText) inflate.findViewById(R.id.edt_remark);

        mTvNodeName.setText(flowPackBean.getNodeName());
        mEdtApprover.setText(flowPackBean.getNodePerson());
        if ("1".equals(flowPackBean.getIsBank())) {
            mRlRemark.setVisibility(View.GONE);
            mLlAccount.setVisibility(View.VISIBLE);
        } else {
            mRlRemark.setVisibility(View.VISIBLE);
            mLlAccount.setVisibility(View.GONE);
        }
        mBtnSelAct.setOnClickListener(v -> mPresenter.selCompBankAct(mEdtNodeSendBank, mEdtSendAccount));

        if ("同意".equals(flowPackBean.getNodeMemo())) {
            mTvAprState.setVisibility(View.VISIBLE);
            mTvAprState.setText(flowPackBean.getNodeMemo());
            mEdtApprover.setVisibility(View.GONE);
            Glide.with(mContext).load(getString(R.string.home_base_url) +
                    flowPackBean.getNodeSign()).into(mIvSign);
            mIvSign.setVisibility(View.VISIBLE);
        } else if ("不同意".equals(flowPackBean.getNodeMemo())) {
            mTvAprState.setVisibility(View.VISIBLE);
            mTvAprState.setText(flowPackBean.getNodeMemo());
            mEdtApprover.setVisibility(View.GONE);
            Glide.with(mContext).load(getString(R.string.home_base_url) +
                    flowPackBean.getNodeSign()).into(mIvSign);
            mIvSign.setVisibility(View.VISIBLE);
        } else {
            mLlAprBtn.setVisibility(View.VISIBLE);
            mTvAprState.setText("未审批");
            mBtnAgree.setOnClickListener(v -> {
                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this)
                        .setTitle("审批通过")
                        .setMessage("审批通过?")
                        .setPositiveButton("确定", (dialog, which) -> {
                            ClNodeApproveReqs nodeApr = new ClNodeApproveReqs();
                            nodeApr.setId(compLoanId);
                            nodeApr.setNodeMemo("同意");
                            if (bankPackBean != null) {
                                nodeApr.setCwBname(bankPackBean.getBankName() != null ? bankPackBean.getBankName() : "");
                                nodeApr.setCwBnum(bankPackBean.getBankNum() != null ? bankPackBean.getBankNum() : "");
                            } else {
                                nodeApr.setCwBname("");
                                nodeApr.setCwBnum("");
                            }
                            nodeApr.setNodeMemotext(mEdtRemark.getText().toString().trim());

                            mPresenter.flowNodeApr(nodeApr);
                        }).setNegativeButton("返回", (dialog, which) -> dialog.dismiss());
                builder.show();
            });
            mBtnRefuse.setOnClickListener(v -> {
                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this)
                        .setTitle("审批拒绝")
                        .setMessage("审批拒绝?")
                        .setPositiveButton("确定", (dialog, which) -> {
                            ClNodeApproveReqs nodeApr = new ClNodeApproveReqs();
                            nodeApr.setId(compLoanId);
                            nodeApr.setNodeMemo("不同意");

                            if (bankPackBean != null) {
                                nodeApr.setCwBname(bankPackBean.getBankName() != null ? bankPackBean.getBankName() : "");
                                nodeApr.setCwBnum(bankPackBean.getBankNum() != null ? bankPackBean.getBankNum() : "");
                            } else {
                                nodeApr.setCwBname("");
                                nodeApr.setCwBnum("");
                            }
                            nodeApr.setNodeMemotext("" + mEdtRemark.getText().toString().trim());
                            mPresenter.flowNodeApr(nodeApr);
                        }).setNegativeButton("返回", (dialog, which) -> dialog.dismiss());
                builder.show();
            });
        }
        mEdtRemark.setText(flowPackBean.getNodeMemotext());
        if (!TextUtils.isEmpty(flowPackBean.getCwBname())) {
            mEdtSendAccount.setText(flowPackBean.getCwBname() + " " + flowPackBean.getCwBnum());
        }
        if ("1".equals(compLoanType) || "3".equals(compLoanType)) {
            mLlAccount.setVisibility(View.GONE);
        }

        if (!getUserName().equals(currentPerson) || !currentPerson.equals(flowPackBean.getNodePerson().trim()) || !"2".equals(compLoanType)) {
            mEdtApprover.setEnabled(false);
            mEdtApprover.setFocusable(false);
            mEdtRemark.setEnabled(false);
            mEdtRemark.setFocusable(false);
            mEdtSendAccount.setEnabled(false);
            mEdtSendAccount.setFocusable(false);
            mLlAprBtn.setVisibility(View.GONE);
//            mRlRemark.setVisibility(View.GONE);
//            mLlAccount.setVisibility(View.GONE);
            mRlSelAct.setVisibility(View.GONE);
            mTvAprState.setVisibility(View.VISIBLE);
        }

        mLlFlowNode.addView(inflate);
    }


//    private void setFilePack(int position) {
//        PsonLoanDetailResp.FilePackBean filePackBean = filePack.get(position);
//        View inflate = LayoutInflater.from(this).inflate(R.layout.home_add_comn_filepack, null);
//        TextView certificate = (TextView) inflate.findViewById(R.id.tv_certificate);
//        certificate.setText(filePackBean.getFileName());
//        certificate.setOnClickListener(v -> {
//            // 弹出一个选择浏览器的框，选择浏览器再进入
//            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.home_url_othfile) + filePackBean.getFilePath()));
//            startActivity(intent);
//        });
//        Button DelFile = (Button) inflate.findViewById(R.id.btn_del_file);
//        DelFile.setVisibility(View.GONE);
//        mLlFilePack.addView(inflate);
//    }
}
