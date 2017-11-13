package com.hyjt.home.mvp.ui.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
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
import com.hyjt.frame.utils.PermissionUtil;
import com.hyjt.frame.utils.UiUtils;

import com.hyjt.home.di.component.DaggerApplyExpenseEditComponent;
import com.hyjt.home.di.module.ApplyExpenseEditModule;
import com.hyjt.home.mvp.contract.ApplyExpenseEditContract;
import com.hyjt.home.mvp.model.entity.Reqs.ApplyExpCreateReqs;
import com.hyjt.home.mvp.model.entity.Reqs.ClNodeApproveReqs;
import com.hyjt.home.mvp.model.entity.Resp.AEExpMoneyResp;
import com.hyjt.home.mvp.model.entity.Resp.ApplyExpDetailResp;
import com.hyjt.home.mvp.model.entity.Resp.ApplyExpTypeResp;
import com.hyjt.home.mvp.model.entity.Resp.CompLoanListResp;
import com.hyjt.home.mvp.model.entity.Resp.ImgUploadResp;
import com.hyjt.home.mvp.model.entity.Resp.PLBankAccountResp;
import com.hyjt.home.mvp.model.entity.Resp.PLCompBankAccountResp;
import com.hyjt.home.mvp.model.entity.Resp.PLCompanyResp;
import com.hyjt.home.mvp.model.entity.Resp.PLFristLeaderResp;
import com.hyjt.home.mvp.model.entity.Resp.PsonLoanListResp;
import com.hyjt.home.mvp.presenter.ApplyExpenseEditPresenter;

import com.hyjt.home.R;
import com.hyjt.home.mvp.ui.adapter.BankAccountAdapter;
import com.hyjt.home.mvp.ui.adapter.ComnStringAdapter;
import com.hyjt.home.mvp.ui.adapter.CompLoanAlertAdapter;
import com.hyjt.home.mvp.ui.adapter.FrstLdAdapter;
import com.hyjt.home.mvp.ui.adapter.PlCompBankActAdapter;
import com.hyjt.home.mvp.ui.adapter.PsonLoanAlertAdapter;
import com.hyjt.home.mvp.ui.view.Constant;
import com.hyjt.home.mvp.ui.view.GetSingleSelectItem;
import com.hyjt.home.utils.ImgUtil;
import com.tbruyelle.rxpermissions2.RxPermissions;


import org.simple.eventbus.EventBus;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;

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
    private List<ApplyExpDetailResp.ReceivePackBean> receivePack = new ArrayList<>();
    private List<ApplyExpDetailResp.FilePackBean> filePack = new ArrayList<>();
    private PLFristLeaderResp.FlowDetailsBean flowDetailsBean = new PLFristLeaderResp.FlowDetailsBean();
    private EditText mEdtMoneyType;
    private boolean canEdit;

    private List<ApplyExpCreateReqs.FilePackBean> FileList = new ArrayList<>();
    private List<ApplyExpCreateReqs.WriteoffPackBean> WriteoffList = new ArrayList<>();
    private List<ApplyExpCreateReqs.ReceivePackBean> ReceiveList = new ArrayList<>();
    private ApplyExpCreateReqs applyExpCreateReqs;
    private RelativeLayout mRlAddReceive;
    private Button mBtnAddReceive;
    private RelativeLayout mRlAddFile;
    private TextView mTvFilePack;
    private Button mBtnAddFile;


    private ArrayList<Uri> mFileURLList = new ArrayList<>();
    private RelativeLayout mRlAddWriteoff;
    private Button mBtnAddWriteoff;
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
        mEdtExpenseReason.setText(applyExpDetailResp.getCwEreason());
        mEdtExpenseAmount.setText(applyExpDetailResp.getCwEmoney());

        if ("是".equals(applyExpDetailResp.getCwEWriteoff())) {
            mRbAgree.setChecked(true);
        } else {
            mRbRefuse.setChecked(true);
        }
        mEdtMoneyType.setText(applyExpDetailResp.getCwEmode());
        mEdtExpenseType.setText(applyExpDetailResp.getCwEPayMode());
        mEdtSureMoney.setText(applyExpDetailResp.getCwEPayMoney());
        mEdtRemark.setText(applyExpDetailResp.getCwEtext());

        if (!"提交".equals(applyExpDetailResp.getCwEState()) ||
                !"1".equals(applyExpType)) {

            canEdit = false;
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

            mRbAgree.setClickable(false);
            mRbRefuse.setClickable(false);

            mBtnAddReceive.setVisibility(View.GONE);
            mBtnAddWriteoff.setVisibility(View.GONE);
            mBtnAddFile.setVisibility(View.GONE);
        } else {
            canEdit = true;
            mEdtExpenseType.setOnTouchListener(new GetSingleSelectItem(
                    this, mEdtExpenseType, "支付方式", Constant.payTypeArr, false));
            if ("转账".equals(applyExpDetailResp.getCwEPayMode())) {
                mLlReceiver.setVisibility(View.VISIBLE);
            }

            mEdtExpenseType.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                }

                @Override
                public void afterTextChanged(Editable s) {
                    if ("转账".equals(s.toString())) {
                        mRlAddReceive.setVisibility(View.VISIBLE);
                    } else if ("现金".equals(s.toString())) {
                        mRlAddReceive.setVisibility(View.GONE);
                    }
                }
            });

            mEdtCompanyName.setOnClickListener(v -> mPresenter.getAECompany());
            mEdtFristLeader.setOnClickListener(v -> mPresenter.getFristLeader("费用报销"));
            mEdtMoneyType.setOnClickListener(v -> mPresenter.getExpType());
        }

        flowPack.clear();
        flowPack.addAll(applyExpDetailResp.getFlowPack());
        for (int position = 0; position < flowPack.size(); position++) {
            setFlowPack(position);
        }

        writeOffPack.clear();
        writeOffPack.addAll(applyExpDetailResp.getWriteOffPack());
        for (int position = 0; position < writeOffPack.size(); position++) {
            setWriteOffPack(position);
        }


        receivePack.clear();
        receivePack.addAll(applyExpDetailResp.getReceivePack());
        if (receivePack.size() > 0)
            mRlAddReceive.setVisibility(View.VISIBLE);
        for (int position = 0; position < receivePack.size(); position++) {
            setReceivePack(position);
        }

        filePack.clear();
        filePack.addAll(applyExpDetailResp.getFilePack());
        for (int position = 0; position < filePack.size(); position++) {
            setFilePack(position);
        }

        if ("审批完成".equals(applyExpDetailResp.getFlowState())) {

            if (!"0".equals(applyExpDetailResp.getCashierQren())) {
                mBtnTellerConfirm.setVisibility(View.GONE);
                Glide.with(this).load(getString(R.string.home_base_url) +
                        applyExpDetailResp.getCashierQren()).into(mIvTellerSign);
            } else {
                if ("3".equals(applyExpType)) {
                    mBtnTellerConfirm.setOnClickListener(v -> {

                        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                                .setTitle("出纳人员确认")
                                .setMessage("是否确认出纳?")
                                .setPositiveButton("确定", (dialog, which) -> {
                                    progressDialog = ProgressDialog.show(this, null, "出纳人员确认中…");
                                    mPresenter.tellerConfirm(applyExpId);
                                })
                                .setNegativeButton("返回", (dialog, which) -> dialog.dismiss());
                        builder.show();
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

                        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                                .setTitle("收款人员确认")
                                .setMessage("是否确认已收款?")
                                .setPositiveButton("确定", (dialog, which) -> {
                                    progressDialog = ProgressDialog.show(this, null, "收款人员确认中…");
                                    mPresenter.receiverConfirm(applyExpId);
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
        mBtnEditLoan.setOnClickListener(v -> checkEditExp());
        mBtnDelLoan = (Button) findViewById(R.id.btn_del_loan);
        mBtnDelLoan.setOnClickListener(v -> {
            android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this)
                    .setTitle("删除报销申请")
                    .setMessage("确定删除该条报销申请?")
                    .setPositiveButton("确定", (dialog, which) -> mPresenter.delApplyExp(applyExpId))
                    .setNegativeButton("返回", (dialog, which) -> dialog.dismiss());
            builder.show();
        });
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
        mRlAddReceive = (RelativeLayout) findViewById(R.id.rl_add_receive);
        mBtnAddReceive = (Button) findViewById(R.id.btn_add_receive);
        mRlAddFile = (RelativeLayout) findViewById(R.id.rl_add_file);
        mTvFilePack = (TextView) findViewById(R.id.tv_file_pack);
        mBtnAddFile = (Button) findViewById(R.id.btn_add_file);

        mBtnAddReceive.setOnClickListener(v -> addReceiver());
        mBtnAddFile.setOnClickListener(v -> {
            Intent intentFile = new Intent(Intent.ACTION_GET_CONTENT);
            intentFile.setType("*/*");//设置类型，我这里是任意类型，任意后缀的可以这样写。
            intentFile.addCategory(Intent.CATEGORY_OPENABLE);
            startActivityForResult(intentFile, 100);
        });
        mBtnAddFile.setVisibility(View.GONE);
        mRlAddWriteoff = (RelativeLayout) findViewById(R.id.rl_add_writeoff);
        mBtnAddWriteoff = (Button) findViewById(R.id.btn_add_writeoff);
        mBtnAddWriteoff.setOnClickListener(v -> addWriteOffPack());

        mRgExpState.setOnCheckedChangeListener((group, checkedId) -> {
            Log.e("checkedId", "" + checkedId);
            if (R.id.rb_agree == checkedId) {
                mRlAddWriteoff.setVisibility(View.VISIBLE);
            } else {
                mRlAddWriteoff.setVisibility(View.GONE);
            }
        });
    }
    private void addWriteOffPack() {
        mEdtSureMoney.setText("");
        View inflate = LayoutInflater.from(this).inflate(R.layout.home_add_ae_write_off, null);


        EditText mEdtWriteoffNum = (EditText) inflate.findViewById(R.id.edt_writeoff_num);
        EditText mEdtBorrowMoney = (EditText) inflate.findViewById(R.id.edt_borrow_money);
        EditText mEdtUnpayed = (EditText) inflate.findViewById(R.id.edt_unpayd);
        EditText mEdtPayed = (EditText) inflate.findViewById(R.id.edt_payd);
        EditText mEdtWriteoffMoney = (EditText) inflate.findViewById(R.id.edt_writeoff_money);
        Button mBtnToComp = (Button) inflate.findViewById(R.id.btn_to_comp);
        Button mBtnToPson = (Button) inflate.findViewById(R.id.btn_to_pson);
        mBtnToComp.setOnClickListener(v -> mPresenter.getCompLoanList(mEdtWriteoffNum, mEdtBorrowMoney, mEdtUnpayed, mEdtPayed));
        mBtnToPson.setOnClickListener(v -> mPresenter.getPsonLoanList(mEdtWriteoffNum, mEdtBorrowMoney, mEdtUnpayed, mEdtPayed));
        //删除布局
        Button delWriteoff = (Button) inflate.findViewById(R.id.btn_del_writeoff);
        delWriteoff.setOnClickListener(v -> {
//            if (meetingQuestionList.size()>size){
            mLlWriteoff.removeView(inflate);
//                meetingQuestionList.remove(size);
//            }
        });

        mLlWriteoff.addView(inflate);
        mSlvApplyexp.fullScroll(ScrollView.FOCUS_DOWN);
    }

    private void addReceiver() {
        View inflate = LayoutInflater.from(this).inflate(R.layout.home_add_ae_receiver, null);


        Button mBtnSelAccount = (Button) inflate.findViewById(R.id.btn_sel_account);
        EditText mEdtAccountName = (EditText) inflate.findViewById(R.id.edt_account_name);
        EditText mEdtOpenactBank = (EditText) inflate.findViewById(R.id.edt_openact_bank);
        EditText mEdtBankAccount = (EditText) inflate.findViewById(R.id.edt_bank_account);
        EditText mEdtMoney = (EditText) inflate.findViewById(R.id.edt_money);

        mBtnSelAccount.setOnClickListener(v -> mPresenter.getReceiveBank(mEdtAccountName, mEdtOpenactBank, mEdtBankAccount));

        //删除布局
        Button delWriteoff = (Button) inflate.findViewById(R.id.btn_del_receive);
        delWriteoff.setOnClickListener(v -> mLlReceiver.removeView(inflate));

        mLlReceiver.addView(inflate);
        mSlvApplyexp.fullScroll(ScrollView.FOCUS_DOWN);
    }

    private void checkEditExp() {
        applyExpCreateReqs = new ApplyExpCreateReqs();

        if (TextUtils.isEmpty(mEdtCompanyName.getText())) {
            shortToast("请选择公司名称");
            return;
        }
        if (TextUtils.isEmpty(mEdtFristLeader.getText())) {
            shortToast("请选择首签领导");
            return;
        }
        if (TextUtils.isEmpty(mEdtExpenseReason.getText())) {
            shortToast("请填写借款事由");
        }
        if (TextUtils.isEmpty(mEdtExpenseAmount.getText())) {
            shortToast("请填写借款金额");
            return;
        }
        if (TextUtils.isEmpty(mEdtExpenseType.getText())) {
            shortToast("请选择收款方式");
            return;
        }
        if (!mRbAgree.isChecked() && !mRbRefuse.isChecked()) {
            shortToast("请选择是否核销");
            return;
        }
        if (TextUtils.isEmpty(mEdtSureMoney.getText())) {
            shortToast("请计算实付金额");
            return;
        }

        if (mRbAgree.isChecked()) {

            WriteoffList.clear();
            for (int i = 0; i < mLlWriteoff.getChildCount(); i++) {
                View inflate = mLlWriteoff.getChildAt(i);
                ApplyExpCreateReqs.WriteoffPackBean applyExp = new ApplyExpCreateReqs.WriteoffPackBean();

                EditText mEdtWriteoffNum = (EditText) inflate.findViewById(R.id.edt_writeoff_num);
                EditText mEdtBorrowMoney = (EditText) inflate.findViewById(R.id.edt_borrow_money);
                EditText mEdtWriteoffMoney = (EditText) inflate.findViewById(R.id.edt_writeoff_money);
                if (TextUtils.isEmpty(mEdtWriteoffNum.getText())) {
                    showMessage("借款单编号为必选项");
                    return;
                }
                if (TextUtils.isEmpty(mEdtBorrowMoney.getText())) {
                    showMessage("借款单金额为必选项");
                    return;
                }
                if (TextUtils.isEmpty(mEdtWriteoffMoney.getText())) {
                    showMessage("本次核销金额为必填项");
                    return;
                }
                applyExp.setCwWid(mEdtWriteoffNum.getText().toString());
                applyExp.setCwWidMoney(mEdtBorrowMoney.getText().toString());
                applyExp.setCwWwiteoffMoney(mEdtWriteoffMoney.getText().toString());
                WriteoffList.add(applyExp);
            }
        } else {
            WriteoffList.clear();
        }

        if ("转账".equals(mEdtExpenseType.getText().toString())) {

            ReceiveList.clear();
            for (int i = 0; i < mLlReceiver.getChildCount(); i++) {
                View inflate = mLlReceiver.getChildAt(i);
                ApplyExpCreateReqs.ReceivePackBean applyExpRec = new ApplyExpCreateReqs.ReceivePackBean();
                EditText mEdtAccountName = (EditText) inflate.findViewById(R.id.edt_account_name);
                EditText mEdtOpenactBank = (EditText) inflate.findViewById(R.id.edt_openact_bank);
                EditText mEdtBankAccount = (EditText) inflate.findViewById(R.id.edt_bank_account);
                EditText mEdtMoney = (EditText) inflate.findViewById(R.id.edt_money);

                if (TextUtils.isEmpty(mEdtAccountName.getText())) {
                    showMessage("收款信息户名为必选项");
                    return;
                }
                if (TextUtils.isEmpty(mEdtOpenactBank.getText())) {
                    showMessage("收款信息开户行为必选项");
                    return;
                }
                if (TextUtils.isEmpty(mEdtBankAccount.getText())) {
                    showMessage("收款信息账号为必填项");
                    return;
                }
                if (TextUtils.isEmpty(mEdtMoney.getText())) {
                    showMessage("收款信息金额为必填项");
                    return;
                }
                applyExpRec.setCwRbank(mEdtOpenactBank.getText().toString());
                applyExpRec.setCwRmoney(mEdtMoney.getText().toString());
                applyExpRec.setCwRname(mEdtAccountName.getText().toString());
                applyExpRec.setCwRnum(mEdtBankAccount.getText().toString());
                ReceiveList.add(applyExpRec);
            }
        } else {
            ReceiveList.clear();
        }


        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this)
                .setTitle("报销申请")
                .setMessage("确定编辑报销申请?")
                .setPositiveButton("确定", (dialog, which) -> {
//                    if (mFileURLList.size() > 0) {
//                        progressDialog = ProgressDialog.show(this, null, "正在上传附件…");
//                        mPresenter.sendFile(mFileURLList);
//                    } else {
//                        fileUploadOk(new ArrayList<>());
//                    }
                    fileUploadOk(new ArrayList<>());
                }).setNegativeButton("返回", (dialog, which) -> dialog.dismiss());
        builder.show();

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

        if (!getUserName().equals(currentPerson) || !currentPerson.equals(flowPackBean.getNodePerson().trim()) || !"2".equals(applyExpType)) {
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

    private void setWriteOffPack(int position) {
        View inflate = LayoutInflater.from(this).inflate(R.layout.home_add_ae_write_off, null);
        ApplyExpDetailResp.WriteOffPackBean writeOffPackBean = writeOffPack.get(position);

        RelativeLayout mRlSelAct = (RelativeLayout) inflate.findViewById(R.id.rl_sel_act);
        EditText mEdtWriteoffNum = (EditText) inflate.findViewById(R.id.edt_writeoff_num);
        EditText mEdtBorrowMoney = (EditText) inflate.findViewById(R.id.edt_borrow_money);
        EditText mEdtUnpayed = (EditText) inflate.findViewById(R.id.edt_unpayd);
        EditText mEdtPayed = (EditText) inflate.findViewById(R.id.edt_payd);
        EditText mEdtWriteoffMoney = (EditText) inflate.findViewById(R.id.edt_writeoff_money);
        Button mBtnToComp = (Button) inflate.findViewById(R.id.btn_to_comp);
        Button mBtnToPson = (Button) inflate.findViewById(R.id.btn_to_pson);
        mEdtWriteoffNum.setText(writeOffPackBean.getCwWid());
        mEdtBorrowMoney.setText(writeOffPackBean.getCwWidMoney());
        mEdtUnpayed.setText(writeOffPackBean.getUnPayed());
        mEdtPayed.setText(writeOffPackBean.getPayed());
        mEdtWriteoffMoney.setText(writeOffPackBean.getCwWwiteoffMoney());

        //删除布局
        Button delWriteoff = (Button) inflate.findViewById(R.id.btn_del_writeoff);
        delWriteoff.setVisibility(View.GONE);
        delWriteoff.setOnClickListener(v -> mLlWriteoff.removeView(inflate));

        if (canEdit) {
            mBtnToComp.setOnClickListener(v -> mPresenter.getCompLoanList(mEdtWriteoffNum, mEdtBorrowMoney, mEdtUnpayed, mEdtPayed));
            mBtnToPson.setOnClickListener(v -> mPresenter.getPsonLoanList(mEdtWriteoffNum, mEdtBorrowMoney, mEdtUnpayed, mEdtPayed));

        } else {
            mRlSelAct.setVisibility(View.GONE);
            delWriteoff.setVisibility(View.GONE);
            mEdtWriteoffMoney.setFocusable(false);
        }

        mLlWriteoff.addView(inflate);

    }

    private void setReceivePack(int position) {
        View inflate = LayoutInflater.from(this).inflate(R.layout.home_add_ae_receiver, null);

        ApplyExpDetailResp.ReceivePackBean receivePackBean = receivePack.get(position);

        EditText mEdtAccountName = (EditText) inflate.findViewById(R.id.edt_account_name);
        EditText mEdtOpenactBank = (EditText) inflate.findViewById(R.id.edt_openact_bank);
        EditText mEdtBankAccount = (EditText) inflate.findViewById(R.id.edt_bank_account);
        EditText mEdtMoney = (EditText) inflate.findViewById(R.id.edt_money);

        mEdtAccountName.setText(receivePackBean.getCwRname());
        mEdtOpenactBank.setText(receivePackBean.getCwRbank());
        mEdtBankAccount.setText(receivePackBean.getCwRnum());
        mEdtMoney.setText(receivePackBean.getCwRmoney());


        if (canEdit) {
            Button mBtnSelAccount = (Button) inflate.findViewById(R.id.btn_sel_account);
            mBtnSelAccount.setOnClickListener(v -> mPresenter.getReceiveBank(mEdtAccountName, mEdtOpenactBank, mEdtBankAccount));
            Button delWriteoff = (Button) inflate.findViewById(R.id.btn_del_receive);
            delWriteoff.setOnClickListener(v -> {
                if (position >= receivePack.size())
                    receivePack.remove(position);
                mLlReceiver.removeView(inflate);
            });
        } else {
            RelativeLayout mRlSelReciver = (RelativeLayout) inflate.findViewById(R.id.rl_sel_receiver);
            mRlSelReciver.setVisibility(View.GONE);
            Button delWriteoff = (Button) inflate.findViewById(R.id.btn_del_receive);
            delWriteoff.setVisibility(View.GONE);
        }

        mLlReceiver.addView(inflate);
    }

    private void setFilePack(int position) {
        ApplyExpDetailResp.FilePackBean filePackBean = filePack.get(position);
        View inflate = LayoutInflater.from(this).inflate(R.layout.home_add_comn_filepack, null);
        TextView certificate = (TextView) inflate.findViewById(R.id.tv_certificate);
        certificate.setText(filePackBean.getFileName());
        certificate.setOnClickListener(v -> {
            // 弹出一个选择浏览器的框，选择浏览器再进入
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.home_url_othfile) + filePackBean.getFilePath()));
            startActivity(intent);
        });
        Button DelFile = (Button) inflate.findViewById(R.id.btn_del_file);
        DelFile.setVisibility(View.GONE);
        mLlFilePack.addView(inflate);
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
        accAlert.show();
    }

    @Override
    public void showExpPMoneyList(PsonLoanListResp psonLoanListResp, EditText mEdtWriteoffNum, EditText mEdtBorrowMoney, EditText mEdtUnpayed, EditText mEdtPayed) {
        List<PsonLoanListResp.RowsBean> rows = psonLoanListResp.getRows();
        if (rows == null || rows.size() == 0) {
            new AlertDialog.Builder(this).setTitle("提示")
                    .setMessage("您没有个人借款信息！")
                    .setPositiveButton("确定", (dialog, which) -> finish()).show();
            return;
        }

        AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.home_dialog_sel_list, null);
        dialog.setTitle("选择借款信息");
        dialog.setView(layout).setCancelable(false)
                .setNegativeButton("取消", (dialog1, id) -> dialog1.cancel());
        AlertDialog accAlert = dialog.create();
        ListView accList = (ListView) layout.findViewById(R.id.accList);

        PsonLoanAlertAdapter psonLoanAlertAdapter = new PsonLoanAlertAdapter(mContext, rows);
        accList.setAdapter(psonLoanAlertAdapter);

        psonLoanAlertAdapter.setItemClickListener(position -> {
            mEdtWriteoffNum.setText(rows.get(position).getCwPnum());
            mEdtBorrowMoney.setText(rows.get(position).getCwPmoney());
            mPresenter.getExpMoney(rows.get(position).getCwPnum(), mEdtUnpayed, mEdtPayed);
            accAlert.dismiss();
        });
        accAlert.show();
    }

    @Override
    public void showExpCMoneyList(CompLoanListResp compLoanListResp, EditText mEdtWriteoffNum, EditText mEdtBorrowMoney, EditText mEdtUnpayed, EditText mEdtPayed) {

        List<CompLoanListResp.RowsBean> rows = compLoanListResp.getRows();
        if (rows == null || rows.size() == 0) {
            new AlertDialog.Builder(this).setTitle("提示")
                    .setMessage("您没有对公借款信息！")
                    .setPositiveButton("确定", (dialog, which) -> finish()).show();
            return;
        }
        AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.home_dialog_sel_list, null);
        dialog.setTitle("选择借款信息");
        dialog.setView(layout).setCancelable(false)
                .setNegativeButton("取消", (dialog1, id) -> dialog1.cancel());
        AlertDialog accAlert = dialog.create();
        ListView accList = (ListView) layout.findViewById(R.id.accList);

        CompLoanAlertAdapter compLoanAlertAdapter = new CompLoanAlertAdapter(mContext, rows);
        accList.setAdapter(compLoanAlertAdapter);

        compLoanAlertAdapter.setItemClickListener(position -> {
            mEdtWriteoffNum.setText(rows.get(position).getCwCnum());
            mEdtBorrowMoney.setText(rows.get(position).getCwCmoney());
            mPresenter.getExpMoney(rows.get(position).getCwCnum(), mEdtUnpayed, mEdtPayed);
            accAlert.dismiss();
        });
        accAlert.show();
    }

    @Override
    public void showExpMoney(AEExpMoneyResp aeexpMoneyResp, EditText mEdtUnpayed, EditText mEdtPayed) {
        mEdtUnpayed.setText(aeexpMoneyResp.getUnPay());
        mEdtPayed.setText(aeexpMoneyResp.getPayed());
    }

    @Override
    public void showBankAccount(PLBankAccountResp plBankAccountResp, EditText mEdtAccountName, EditText mEdtOpenactBank, EditText mEdtBankAccount) {
        List<PLBankAccountResp.BankPackBean> bankPack = plBankAccountResp.getBankPack();
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

        BankAccountAdapter bankAccountAdapter = new BankAccountAdapter(mContext, bankPack);
        accList.setAdapter(bankAccountAdapter);

        bankAccountAdapter.setItemClickListener(position -> {
            mEdtAccountName.setText(bankPack.get(position).getBankPerson());
            mEdtBankAccount.setText(bankPack.get(position).getBankNum());
            mEdtOpenactBank.setText(bankPack.get(position).getBankName());
            accAlert.dismiss();
        });
        accAlert.show();
    }


    @Override
    public void fileUploadOk(List<ImgUploadResp> imgUploadList) {

        if (imgUploadList.size() > 0) {
            for (int i = 0; i < imgUploadList.size(); i++) {
                FileList.add(new ApplyExpCreateReqs.FilePackBean(imgUploadList.get(i).getId()
                        , imgUploadList.get(i).getName()));
            }
        }
        applyExpCreateReqs.setId(applyExpId);
        applyExpCreateReqs.setCwEcompany(mEdtCompanyName.getText().toString().trim());
        applyExpCreateReqs.setCwELeader(mEdtFristLeader.getText().toString().trim());
        applyExpCreateReqs.setCwEreason(mEdtExpenseReason.getText().toString().trim());
        applyExpCreateReqs.setCwEmoney(mEdtExpenseAmount.getText().toString().trim());
        applyExpCreateReqs.setCwEmode(mEdtMoneyType.getText().toString().trim());
        applyExpCreateReqs.setCwEWriteoff(mRbRefuse.isChecked() ? "否" : "是");
        applyExpCreateReqs.setCwEPayMode(mEdtExpenseType.getText().toString().trim());
        applyExpCreateReqs.setCwEPayMoney(mEdtSureMoney.getText().toString().trim());
        applyExpCreateReqs.setCwEtext(mEdtRemark.getText().toString().trim());
        applyExpCreateReqs.setFlowid(flowDetailsBean.getFlowid());
        applyExpCreateReqs.setWriteoffPack(WriteoffList);
        applyExpCreateReqs.setReceivePack(ReceiveList);
//        applyExpCreateReqs.setFilePack();
        List<ApplyExpDetailResp.FilePackBean> filePack = applyExpDetailResp.getFilePack();
        for (int i = 0; i < filePack.size(); i++) {
            ApplyExpDetailResp.FilePackBean filePackBean = filePack.get(i);
            FileList.add(new ApplyExpCreateReqs.FilePackBean(filePackBean.getFileId(), filePackBean.getFileName()));
        }
        applyExpCreateReqs.setFilePack(FileList);



        mPresenter.editApplyExp(applyExpCreateReqs);

    }

    @Override
    public RxPermissions getRxPermissions() {
        return new RxPermissions(this);
    }


    private void addFilePack(int position) {

        View inflate = LayoutInflater.from(getApplicationContext()).inflate(R.layout.home_add_comn_filepack, null);
        TextView certificate = (TextView) inflate.findViewById(R.id.tv_certificate);
        Button DelFile = (Button) inflate.findViewById(R.id.btn_del_file);
        DelFile.setOnClickListener(v -> {
            mLlFilePack.removeView(inflate);
            mFileURLList.remove(position - 1);
        });


        RxErrorHandler build = RxErrorHandler.builder()
                .with(getApplicationContext())
                .responseErrorListener((context, t) -> {
                }).build();


        PermissionUtil.externalStorage(new PermissionUtil.RequestPermission() {
            @Override
            public void onRequestPermissionSuccess() {
                Uri uri = mFileURLList.get(position - 1);
                String imageAbsolutePath = ImgUtil.getImageAbsolutePath(getApplicationContext(), uri);
                File file = new File(imageAbsolutePath);
                certificate.setText("" + file.getName());
            }

            @Override
            public void onRequestPermissionFailure() {
                shortToast("您需要打开存储权限");
            }
        }, getRxPermissions(), build);

        mLlFilePack.addView(inflate);
        mSlvApplyexp.fullScroll(ScrollView.FOCUS_DOWN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            if (resultCode == Activity.RESULT_OK) {
                Uri uri = data.getData();
                mFileURLList.add(uri);
                addFilePack(mFileURLList.size());
            }
        }
    }
}
