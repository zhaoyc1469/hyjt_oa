package com.hyjt.home.mvp.ui.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
import com.hyjt.frame.base.BaseActivity;
import com.hyjt.frame.di.component.AppComponent;
import com.hyjt.frame.event.RefreshListEvent;
import com.hyjt.frame.utils.UiUtils;

import com.hyjt.home.di.component.DaggerApplyExpenseEditComponent;
import com.hyjt.home.di.module.ApplyExpenseEditModule;
import com.hyjt.home.mvp.contract.ApplyExpenseEditContract;
import com.hyjt.home.mvp.model.entity.Reqs.ClNodeApproveReqs;
import com.hyjt.home.mvp.model.entity.Resp.ApplyExpDetailResp;
import com.hyjt.home.mvp.model.entity.Resp.ApplyExpTypeResp;
import com.hyjt.home.mvp.model.entity.Resp.CompLoanDetailResp;
import com.hyjt.home.mvp.model.entity.Resp.PLCompBankAccountResp;
import com.hyjt.home.mvp.model.entity.Resp.PLCompanyResp;
import com.hyjt.home.mvp.model.entity.Resp.PLFristLeaderResp;
import com.hyjt.home.mvp.presenter.ApplyExpenseEditPresenter;

import com.hyjt.home.R;
import com.hyjt.home.mvp.ui.adapter.ComnStringAdapter;
import com.hyjt.home.mvp.ui.adapter.FrstLdAdapter;
import com.hyjt.home.mvp.ui.adapter.PlCompBankActAdapter;


import org.simple.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import static com.hyjt.frame.utils.Preconditions.checkNotNull;

@Route(path = "/home/ApplyExpenseEditActivity")
public class ApplyExpenseEditActivity extends BaseActivity<ApplyExpenseEditPresenter> implements ApplyExpenseEditContract.View {


    private String applyExpId;
    private String applyExpType;
    private ApplyExpDetailResp applyExpDetailResp;
    private ProgressDialog progressDialog;
    private android.widget.RelativeLayout mRlApplyExp;
    private android.widget.ImageView mIvTopBack;
    private android.widget.TextView mTvTitle;
    private android.widget.ImageView mIvTopSelect;
    private android.widget.LinearLayout mLlBottomBtn;
    private android.widget.Button mBtnEditLoan;
    private android.widget.Button mBtnDelLoan;
    private android.widget.ScrollView mSlvApplyexp;
    private android.widget.EditText mEdtProposer;
    private android.widget.EditText mEdtApplyTime;
    private android.widget.EditText mEdtCompanyName;
    private android.widget.EditText mEdtFristLeader;
    private android.widget.EditText mEdtExpenseReason;
    private android.widget.EditText mEdtExpenseAmount;
    private android.widget.RadioGroup mRgExpState;
    private android.widget.RadioButton mRbAgree;
    private android.widget.RadioButton mRbRefuse;
    private android.widget.EditText mEdtExpenseType;
    private android.widget.EditText mEdtSureMoney;
    private android.widget.EditText mEdtRemark;
    private android.widget.LinearLayout mLlFlowNode;
    private android.widget.LinearLayout mLlWriteoff;
    private android.widget.LinearLayout mLlReceiver;
    private android.widget.LinearLayout mLlFilePack;
    private android.widget.RelativeLayout mRlSendAccount;
    private android.widget.EditText mEdtReceiverAccount;
    private android.widget.RelativeLayout mRlTellerConfirm;
    private android.widget.ImageView mIvTellerSign;
    private android.widget.Button mBtnTellerConfirm;
    private android.widget.RelativeLayout mRlReceiverConfirm;
    private android.widget.ImageView mIvReceiverSign;
    private android.widget.Button mBtnReceiverConfirm;
    private ApplyExpenseEditActivity mContext;
    private PLCompBankAccountResp.BankPackBean bankPackBean;
    private String currentPerson = "";
    private List<ApplyExpDetailResp.FlowPackBean> flowPack = new ArrayList<>();
    private List<ApplyExpDetailResp.WriteOffPackBean> writeOffPack = new ArrayList<>();
    private PLFristLeaderResp.FlowDetailsBean flowDetailsBean = new PLFristLeaderResp.FlowDetailsBean();
    private EditText mEdtMoneyType;
    //    private List<ApplyExpDetailResp.ReceivePack> writeOffPack = new ArrayList<>();

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerApplyExpenseEditComponent
                .builder()
                .appComponent(appComponent)
                .applyExpenseEditModule(new ApplyExpenseEditModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(Bundle savedInstanceState) {
        return R.layout.home_activity_apply_expense_edit;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        mContext = this;
        Intent intent = getIntent();
        applyExpId = intent.getStringExtra("ApplyExpId");
        applyExpType = intent.getStringExtra("ApplyExpType");
        mPresenter.getApplyExpDetail(applyExpId);
        initView();


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
    public void showLoading(String msg) {
        progressDialog = ProgressDialog.show(this, null, msg);
    }

    @Override
    public void showAEDetail(ApplyExpDetailResp applyExpDetailResp) {
        this.applyExpDetailResp = applyExpDetailResp;
        flowDetailsBean.setFlowid(applyExpDetailResp.getFlowid());
        currentPerson = applyExpDetailResp.getCurrentPerson();

        mEdtProposer.setText(applyExpDetailResp.getCwEpersonal());
        mEdtCompanyName.setText(applyExpDetailResp.getCwEcompany());
        mEdtFristLeader.setText(applyExpDetailResp.getCwELeader());
        mEdtApplyTime.setText(applyExpDetailResp.getSqDate());

//        mEdtExpenseReason = (EditText) findViewById(R.id.edt_expense_reason);
        mEdtExpenseAmount.setText(applyExpDetailResp.getCwEmoney());
//        mEdtExpenseType.setText(applyExpDetailResp.getCwE());
//        mRgExpState = (RadioGroup) findViewById(R.id.rg_exp_state);
//        mRbAgree = (RadioButton) findViewById(R.id.rb_agree);
//        mRbRefuse = (RadioButton) findViewById(R.id.rb_refuse);
        mEdtExpenseType.setText(applyExpDetailResp.getCwEPayMode());
        mEdtSureMoney.setText(applyExpDetailResp.getCwEPayMoney());
        mEdtRemark.setText(applyExpDetailResp.getCwEPayMode());

        if (!"提交".equals(applyExpDetailResp.getCwEState()) ||
                !"1".equals(applyExpType)) {
            mLlBottomBtn.setVisibility(View.GONE);
            mEdtProposer.setFocusable(false);
            mEdtApplyTime.setFocusable(false);
            mEdtCompanyName.setFocusable(false);
            mEdtFristLeader.setFocusable(false);
            mEdtExpenseReason.setFocusable(false);
            mEdtExpenseAmount.setFocusable(false);
            mEdtMoneyType.setFocusable(false);
            mEdtExpenseType.setFocusable(false);
            mEdtSureMoney.setFocusable(false);
            mEdtRemark.setFocusable(false);
        } else {
            mEdtCompanyName.setOnClickListener(v -> mPresenter.getAECompany());
            mEdtFristLeader.setOnClickListener(v -> mPresenter.getFristLeader("费用报销"));
            mEdtMoneyType.setOnClickListener(v -> mPresenter.getExpType());
        }

        flowPack.clear();
        flowPack.addAll(applyExpDetailResp.getFlowPack());
        for (int position = 0; position < flowPack.size(); position++) {
            setFlowPack(position);
        }

        if ("审批完成".equals(applyExpDetailResp.getFlowState())) {
            if (!"0".equals(applyExpDetailResp.getCashierQren())) {
                mBtnTellerConfirm.setVisibility(View.GONE);
                Glide.with(this).load(getString(R.string.home_base_url) +
                        applyExpDetailResp.getCashierQren()).into(mIvTellerSign);
            } else {
                if ("3".equals(applyExpType)) {
                    mBtnTellerConfirm.setOnClickListener(v -> {
                        progressDialog = ProgressDialog.show(this, null, "出纳人员确认中…");
                        mPresenter.tellerConfirm(applyExpId);
                    });
                    mRlSendAccount.setVisibility(View.VISIBLE);
                } else {
                    mRlTellerConfirm.setVisibility(View.GONE);
                }
            }
            if (!"0".equals(applyExpDetailResp.getCwEpersonalQren())) {
                mBtnReceiverConfirm.setVisibility(View.GONE);
                Glide.with(this).load(getString(R.string.home_base_url) +
                        applyExpDetailResp.getCwEpersonalQren()).into(mIvReceiverSign);
            } else {
                if ("1".equals(applyExpType) && !"0".equals(applyExpDetailResp.getCashierQren())) {
                    mBtnReceiverConfirm.setOnClickListener(v -> {
                        progressDialog = ProgressDialog.show(this, null, "收款人员确认中…");
                        mPresenter.receiverConfirm(applyExpId);
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

    private void initView() {
        mRlApplyExp = (RelativeLayout) findViewById(R.id.rl_apply_exp);
        mIvTopBack = (ImageView) findViewById(R.id.iv_top_back);
        mIvTopBack.setOnClickListener(v -> finish());
        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mTvTitle.setText("费用报销");
        mTvTitle.setOnClickListener(v -> finish());
        mIvTopSelect = (ImageView) findViewById(R.id.iv_top_select);
        mIvTopSelect.setVisibility(View.GONE);
        mLlBottomBtn = (LinearLayout) findViewById(R.id.ll_bottom_btn);
        mBtnEditLoan = (Button) findViewById(R.id.btn_edit_loan);
        mBtnDelLoan = (Button) findViewById(R.id.btn_del_loan);
        mSlvApplyexp = (ScrollView) findViewById(R.id.slv_applyexp);
        mEdtProposer = (EditText) findViewById(R.id.edt_proposer);
        mEdtApplyTime = (EditText) findViewById(R.id.edt_apply_time);
        mEdtCompanyName = (EditText) findViewById(R.id.edt_company_name);
        mEdtFristLeader = (EditText) findViewById(R.id.edt_frist_leader);
        mEdtExpenseReason = (EditText) findViewById(R.id.edt_expense_reason);
        mEdtExpenseAmount = (EditText) findViewById(R.id.edt_expense_amount);
        mRgExpState = (RadioGroup) findViewById(R.id.rg_exp_state);
        mRbAgree = (RadioButton) findViewById(R.id.rb_agree);
        mRbRefuse = (RadioButton) findViewById(R.id.rb_refuse);
        mEdtExpenseType = (EditText) findViewById(R.id.edt_expense_type);
        mEdtSureMoney = (EditText) findViewById(R.id.edt_sure_money);
        mEdtRemark = (EditText) findViewById(R.id.edt_remark);
        mLlFlowNode = (LinearLayout) findViewById(R.id.ll_flow_node);
        mLlWriteoff = (LinearLayout) findViewById(R.id.ll_writeoff);
        mLlReceiver = (LinearLayout) findViewById(R.id.ll_receiver);
        mLlFilePack = (LinearLayout) findViewById(R.id.ll_file_pack);
        mRlSendAccount = (RelativeLayout) findViewById(R.id.rl_send_account);
        mEdtReceiverAccount = (EditText) findViewById(R.id.edt_receiver_account);
        mRlTellerConfirm = (RelativeLayout) findViewById(R.id.rl_teller_confirm);
        mIvTellerSign = (ImageView) findViewById(R.id.iv_teller_sign);
        mBtnTellerConfirm = (Button) findViewById(R.id.btn_teller_confirm);
        mRlReceiverConfirm = (RelativeLayout) findViewById(R.id.rl_receiver_confirm);
        mIvReceiverSign = (ImageView) findViewById(R.id.iv_receiver_sign);
        mBtnReceiverConfirm = (Button) findViewById(R.id.btn_receiver_confirm);
        mEdtMoneyType = (EditText) findViewById(R.id.edt_money_type);
    }


    private void setFlowPack(int position) {

        View inflate = LayoutInflater.from(this).inflate(R.layout.home_add_plflownode, null);
        ApplyExpDetailResp.FlowPackBean flowPackBean = flowPack.get(position);

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
        mBtnSelAct.setOnClickListener(v -> mPresenter.selApplyExpBankAct(mEdtSendAccount));

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
                            nodeApr.setId(applyExpId);
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
                            nodeApr.setId(applyExpId);
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
        if ("1".equals(applyExpType) || "3".equals(applyExpType)) {
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
        imm.hideSoftInputFromWindow(mEdtCompanyName.getWindowToken(), 0);
        accAlert.show();
    }

    @Override
    public void showExpTypeList(ApplyExpTypeResp applyExpTypeResp) {

        List<String> modePackList = applyExpTypeResp.getModePack();

        AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.home_dialog_sel_list, null);
        dialog.setTitle("选择费用类型");
        dialog.setView(layout).setCancelable(false)
                .setNegativeButton("取消", (dialog1, id) -> dialog1.cancel());
        AlertDialog accAlert = dialog.create();
        ListView accList = (ListView) layout.findViewById(R.id.accList);

        ComnStringAdapter comnStringAdapter = new ComnStringAdapter(mContext, modePackList);
        accList.setAdapter(comnStringAdapter);

        comnStringAdapter.setItemClickListener(position -> {
            mEdtMoneyType.setText(modePackList.get(position));
            accAlert.dismiss();
        });
        InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(mEdtMoneyType.getWindowToken(), 0);
        accAlert.show();
    }

}
