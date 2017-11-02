package com.hyjt.home.mvp.ui.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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
import com.bumptech.glide.Glide;
import com.hyjt.frame.base.BaseActivity;
import com.hyjt.frame.base.delegate.IFragment;
import com.hyjt.frame.di.component.AppComponent;
import com.hyjt.home.di.component.DaggerToCompLoanEditComponent;
import com.hyjt.home.di.module.ToCompLoanEditModule;
import com.hyjt.home.mvp.contract.ToCompLoanEditContract;
import com.hyjt.home.mvp.model.entity.Reqs.ClNodeApproveReqs;
import com.hyjt.home.mvp.model.entity.Reqs.PlNodeApproveReqs;
import com.hyjt.home.mvp.model.entity.Resp.CompLoanDetailResp;
import com.hyjt.home.mvp.model.entity.Resp.CompLoanListResp;
import com.hyjt.home.mvp.model.entity.Resp.PLCompBankAccountResp;
import com.hyjt.home.mvp.model.entity.Resp.PLFristLeaderResp;
import com.hyjt.home.mvp.model.entity.Resp.PsonLoanDetailResp;
import com.hyjt.home.mvp.presenter.ToCompLoanEditPresenter;

import com.hyjt.home.R;
import com.hyjt.home.mvp.ui.adapter.PlCompBankActAdapter;

import java.util.ArrayList;
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
    private List<PsonLoanDetailResp.FilePackBean> filePack = new ArrayList<>();
    private ToCompLoanEditActivity mContext;
    private LinearLayout mLlFlowNode;
    private RelativeLayout mRlSendAccount;
    private EditText mEdtReceiverAccount;
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


        if (!"提交".equals(compLoanDetailResp.getFlowState()) ||
                !getUserName().equals(compLoanDetailResp.getCwCpersonal())) {
            mRlSelSupplier.setVisibility(View.GONE);
            mBtnSelContract.setVisibility(View.GONE);
            mLlBottomBtn.setVisibility(View.GONE);
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
        }


        if ("审批完成".equals(compLoanDetailResp.getFlowState())) {
            if (!"0".equals(compLoanDetailResp.getCashierQren())) {
                mBtnTellerConfirm.setVisibility(View.GONE);
                Glide.with(this).load(getString(R.string.home_base_url) +
                        compLoanDetailResp.getCashierQren()).into(mIvTellerSign);
            } else {
                if ("3".equals(compLoanType)) {
                    mBtnTellerConfirm.setOnClickListener(v -> {
                        progressDialog = ProgressDialog.show(this, null, "出纳人员确认中…");
                        mPresenter.tellerConfirm(compLoanId);
                    });
                    mRlSendAccount.setVisibility(View.VISIBLE);
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
                        progressDialog = ProgressDialog.show(this, null, "收款人员确认中…");
                        mPresenter.receiverConfirm(compLoanId);
                    });
                } else {
                    mRlReceiverConfirm.setVisibility(View.GONE);
                }
            }
        } else {
            mRlReceiverConfirm.setVisibility(View.GONE);
            mRlSendAccount.setVisibility(View.GONE);
            mRlTellerConfirm.setVisibility(View.GONE);
        }
    }

    @Override
    public void showAprBankAccount(PLCompBankAccountResp compBankAccountResp, EditText editText) {

        List<PLCompBankAccountResp.BankPackBean> bankPack = compBankAccountResp.getBankPack();
        this.bankPackBean = new PLCompBankAccountResp.BankPackBean();

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
            editText.setText(flowDetailsBean.getBankName() + flowDetailsBean.getBankNum());
            this.bankPackBean.setBankName(flowDetailsBean.getBankName());
            this.bankPackBean.setBankNum(flowDetailsBean.getBankNum());
            accAlert.dismiss();
        });
        InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
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
        mEdtReceiverAccount = (EditText) findViewById(R.id.edt_receiver_account);
        mRlTellerConfirm = (RelativeLayout) findViewById(R.id.rl_teller_confirm);
        mIvTellerSign = (ImageView) findViewById(R.id.iv_teller_sign);
        mBtnTellerConfirm = (Button) findViewById(R.id.btn_teller_confirm);
        mRlReceiverConfirm = (RelativeLayout) findViewById(R.id.rl_receiver_confirm);
        mIvReceiverSign = (ImageView) findViewById(R.id.iv_receiver_sign);
        mBtnReceiverConfirm = (Button) findViewById(R.id.btn_receiver_confirm);
    }


    private void setFlowPack(int position) {

        View inflate = LayoutInflater.from(this).inflate(R.layout.home_add_plflownode, null);
        CompLoanDetailResp.FlowPackBean flowPackBean = flowPack.get(position);

        TextView mTvNodeName = (TextView) inflate.findViewById(R.id.tv_node_name);
        EditText mEdtApprover = (EditText) inflate.findViewById(R.id.edt_approver);
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
        mBtnSelAct.setOnClickListener(v -> mPresenter.selCompBankAct(mEdtSendAccount));

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

        if (!getUserName().equals(currentPerson) || !currentPerson.equals(flowPackBean.getNodePerson().trim())) {
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