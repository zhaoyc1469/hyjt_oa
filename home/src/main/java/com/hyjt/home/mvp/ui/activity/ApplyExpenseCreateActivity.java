package com.hyjt.home.mvp.ui.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.IdRes;
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
import com.hyjt.frame.base.BaseActivity;
import com.hyjt.frame.di.component.AppComponent;
import com.hyjt.frame.event.RefreshListEvent;
import com.hyjt.frame.utils.PermissionUtil;
import com.hyjt.frame.utils.UiUtils;

import com.hyjt.home.di.component.DaggerApplyExpenseCreateComponent;
import com.hyjt.home.di.module.ApplyExpenseCreateModule;
import com.hyjt.home.mvp.contract.ApplyExpenseCreateContract;
import com.hyjt.home.mvp.model.entity.Reqs.ApplyExpCreateReqs;
import com.hyjt.home.mvp.model.entity.Resp.AEExpMoneyResp;
import com.hyjt.home.mvp.model.entity.Resp.ApplyExpTypeResp;
import com.hyjt.home.mvp.model.entity.Resp.CompLoanListResp;
import com.hyjt.home.mvp.model.entity.Resp.ImgUploadResp;
import com.hyjt.home.mvp.model.entity.Resp.PLBankAccountResp;
import com.hyjt.home.mvp.model.entity.Resp.PLCompanyResp;
import com.hyjt.home.mvp.model.entity.Resp.PLFristLeaderResp;
import com.hyjt.home.mvp.model.entity.Resp.PsonLoanListResp;
import com.hyjt.home.mvp.presenter.ApplyExpenseCreatePresenter;

import com.hyjt.home.R;
import com.hyjt.home.mvp.ui.adapter.BankAccountAdapter;
import com.hyjt.home.mvp.ui.adapter.ComnStringAdapter;
import com.hyjt.home.mvp.ui.adapter.CompLoanAlertAdapter;
import com.hyjt.home.mvp.ui.adapter.FrstLdAdapter;
import com.hyjt.home.mvp.ui.adapter.PsonLoanAlertAdapter;
import com.hyjt.home.mvp.ui.view.Constant;
import com.hyjt.home.mvp.ui.view.GetSingleSelectItem;
import com.hyjt.home.utils.ImgUtil;
import com.tbruyelle.rxpermissions2.RxPermissions;


import org.simple.eventbus.EventBus;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;

import static com.hyjt.frame.utils.Preconditions.checkNotNull;

@Route(path = "/home/ApplyExpenseCreateActivity")
public class ApplyExpenseCreateActivity extends BaseActivity<ApplyExpenseCreatePresenter> implements ApplyExpenseCreateContract.View {


    private android.widget.RelativeLayout mRlApplyExp;
    private android.widget.ImageView mIvTopBack;
    private android.widget.TextView mTvTitle;
    private android.widget.ImageView mIvTopSelect;
    private android.widget.LinearLayout mLlBottomBtn;
    private android.widget.Button mBtnCreate;
    private android.widget.ScrollView mSlvApplyexp;
    private android.widget.EditText mEdtProposer;
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
    private ProgressDialog progressDialog;
    private PLFristLeaderResp.FlowDetailsBean flowDetailsBean;
    private ApplyExpenseCreateActivity mContext;
    private ArrayList<Uri> mFileURLList = new ArrayList<>();
    private List<ApplyExpCreateReqs.FilePackBean> FileList = new ArrayList<>();
    private List<ApplyExpCreateReqs.WriteoffPackBean> WriteoffList = new ArrayList<>();
    private List<ApplyExpCreateReqs.ReceivePackBean> ReceiveList = new ArrayList<>();
    private EditText mEdtMoneyType;
    private RelativeLayout mRlAddFile;
    private Button mBtnAddFile;
    private LinearLayout mLlFilePack;
    private RelativeLayout mRlAddWriteoff;
    private Button mBtnAddWriteoff;
    private LinearLayout mLlWriteoff;
    private RelativeLayout mRlAddReceive;
    private Button mBtnAddReceive;
    private LinearLayout mLlReceive;
    private ApplyExpCreateReqs applyExpCreateReqs;
    private Button mBtnCompute;
    private BigDecimal surePayMoney = new BigDecimal(0);

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerApplyExpenseCreateComponent
                .builder()
                .appComponent(appComponent)
                .applyExpenseCreateModule(new ApplyExpenseCreateModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(Bundle savedInstanceState) {
        return R.layout.home_activity_apply_expense_create;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        mContext = this;
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


    private void initView() {
        mRlApplyExp = (RelativeLayout) findViewById(R.id.rl_apply_exp);
        mIvTopBack = (ImageView) findViewById(R.id.iv_top_back);
        mIvTopBack.setOnClickListener(v -> finish());
        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mTvTitle.setOnClickListener(v -> finish());
        mTvTitle.setText("发起报销申请");
        mIvTopSelect = (ImageView) findViewById(R.id.iv_top_select);
        mIvTopSelect.setVisibility(View.GONE);
        mLlBottomBtn = (LinearLayout) findViewById(R.id.ll_bottom_btn);
        mBtnCreate = (Button) findViewById(R.id.btn_create);
        mBtnCompute = (Button) findViewById(R.id.btn_compute);
        mBtnCreate.setOnClickListener(v -> checkApplyExp());
        mBtnCompute.setOnClickListener(v -> {
            surePayMoney = new BigDecimal(mEdtExpenseAmount.getText().toString());
            if (mRbAgree.isChecked()) {
                for (int i = 0; i < mLlWriteoff.getChildCount(); i++) {
                    View inflate = mLlWriteoff.getChildAt(i);
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
                    surePayMoney = surePayMoney.subtract(new BigDecimal(mEdtWriteoffMoney.getText().toString()));
                }
                mEdtSureMoney.setText(surePayMoney.toString());
            } else if (mRbRefuse.isChecked()) {
                mEdtSureMoney.setText(mEdtExpenseAmount.getText());
            }
        });
        mSlvApplyexp = (ScrollView) findViewById(R.id.slv_applyexp);
        mEdtProposer = (EditText) findViewById(R.id.edt_proposer);
        mEdtProposer.setText(getUserName());
        mEdtCompanyName = (EditText) findViewById(R.id.edt_company_name);
        mEdtCompanyName.setOnClickListener(v -> mPresenter.getAECompany());
        mEdtFristLeader = (EditText) findViewById(R.id.edt_frist_leader);
        mEdtFristLeader.setOnClickListener(v -> mPresenter.getFristLeader("费用报销"));
        mEdtExpenseReason = (EditText) findViewById(R.id.edt_expense_reason);
        mEdtExpenseAmount = (EditText) findViewById(R.id.edt_expense_amount);
        mRgExpState = (RadioGroup) findViewById(R.id.rg_exp_state);
        mRbAgree = (RadioButton) findViewById(R.id.rb_agree);
        mRbRefuse = (RadioButton) findViewById(R.id.rb_refuse);
        mEdtExpenseType = (EditText) findViewById(R.id.edt_expense_type);
        mEdtExpenseType.setOnTouchListener(new GetSingleSelectItem(
                this, mEdtExpenseType, "支付方式", Constant.payTypeArr, false));
        mEdtSureMoney = (EditText) findViewById(R.id.edt_sure_money);
        mEdtRemark = (EditText) findViewById(R.id.edt_remark);
        mEdtMoneyType = (EditText) findViewById(R.id.edt_money_type);
        mEdtMoneyType.setOnClickListener(v -> mPresenter.getExpType());
        mRlAddFile = (RelativeLayout) findViewById(R.id.rl_add_file);
        mBtnAddFile = (Button) findViewById(R.id.btn_add_file);
        mBtnAddFile.setOnClickListener(v -> {
            Intent intentFile = new Intent(Intent.ACTION_GET_CONTENT);
            intentFile.setType("*/*");//设置类型，我这里是任意类型，任意后缀的可以这样写。
            intentFile.addCategory(Intent.CATEGORY_OPENABLE);
            startActivityForResult(intentFile, 100);
        });
        mLlFilePack = (LinearLayout) findViewById(R.id.ll_file_pack);
        mRlAddWriteoff = (RelativeLayout) findViewById(R.id.rl_add_writeoff);
        mBtnAddWriteoff = (Button) findViewById(R.id.btn_add_writeoff);
        mBtnAddWriteoff.setOnClickListener(v -> addWriteOffPack());
        mLlWriteoff = (LinearLayout) findViewById(R.id.ll_writeoff);
        mRlAddReceive = (RelativeLayout) findViewById(R.id.rl_add_receive);
        mBtnAddReceive = (Button) findViewById(R.id.btn_add_receive);
        mBtnAddReceive.setOnClickListener(v -> addReceiver());
        mLlReceive = (LinearLayout) findViewById(R.id.ll_receive);


        mRgExpState.setOnCheckedChangeListener((group, checkedId) -> {
            Log.e("checkedId", "" + checkedId);
            if (R.id.rb_agree == checkedId) {
                mRlAddWriteoff.setVisibility(View.VISIBLE);
            } else {
                mRlAddWriteoff.setVisibility(View.GONE);
            }
        });

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

        mEdtExpenseAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                getSureMoney();
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
        delWriteoff.setOnClickListener(v -> mLlWriteoff.removeView(inflate));

        mEdtWriteoffMoney.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                getSureMoney();
            }
        });

        mLlWriteoff.addView(inflate);
        mSlvApplyexp.fullScroll(ScrollView.FOCUS_DOWN);
    }

    private void getSureMoney() {
        if (TextUtils.isEmpty(mEdtExpenseAmount.getText())) {
            surePayMoney = new BigDecimal(0);
        } else {
            surePayMoney = new BigDecimal(mEdtExpenseAmount.getText().toString());
        }
        if (mRbAgree.isChecked()) {
            for (int i = 0; i < mLlWriteoff.getChildCount(); i++) {
                View inflate = mLlWriteoff.getChildAt(i);
                EditText mEdtWriteoffMoney = (EditText) inflate.findViewById(R.id.edt_writeoff_money);
                BigDecimal bigDecimal;
                if (TextUtils.isEmpty(mEdtWriteoffMoney.getText())) {
                    bigDecimal = new BigDecimal(0);
                } else {
                    bigDecimal = new BigDecimal(mEdtWriteoffMoney.getText().toString());
                }
                surePayMoney = surePayMoney.subtract(bigDecimal);
            }
            mEdtSureMoney.setText(surePayMoney.toString());
        } else {
            mEdtSureMoney.setText(surePayMoney.toString());
        }

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
        delWriteoff.setOnClickListener(v -> mLlReceive.removeView(inflate));

        mLlReceive.addView(inflate);
        mSlvApplyexp.fullScroll(ScrollView.FOCUS_DOWN);
    }

    private void checkApplyExp() {
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
            for (int i = 0; i < mLlReceive.getChildCount(); i++) {
                View inflate = mLlReceive.getChildAt(i);
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
                .setMessage("确定发起报销申请?")
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
    public void showLoading(String msg) {
        progressDialog = ProgressDialog.show(this, null, msg);
    }

    @Override
    public void hideLoading() {
        if (progressDialog != null)
            progressDialog.dismiss();
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
    public void fileUploadOk(List<ImgUploadResp> imgUploadList) {

        if (imgUploadList.size() > 0) {
            for (int i = 0; i < imgUploadList.size(); i++) {
                FileList.add(new ApplyExpCreateReqs.FilePackBean(imgUploadList.get(i).getId()
                        , imgUploadList.get(i).getName()));
            }
        }

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
        applyExpCreateReqs.setFilePack(FileList);


        mPresenter.createApplyExp(applyExpCreateReqs);
    }

    @Override
    public RxPermissions getRxPermissions() {
        return new RxPermissions(this);
    }

    @Override
    public void showExpCMoneyList(CompLoanListResp compLoanListResp, EditText mEdtWriteoffNum, EditText mEdtBorrowMoney, EditText mEdtUnpayed, EditText mEdtPayed) {

        List<CompLoanListResp.RowsBean> rows = compLoanListResp.getRows();
        if (rows == null || rows.size() == 0) {
            new AlertDialog.Builder(this).setTitle("提示")
                    .setMessage("您没有对公借款信息！")
                    .setPositiveButton("确定", (dialog, which) -> dialog.dismiss()).show();
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
    public void showExpPMoneyList(PsonLoanListResp psonLoanListResp, EditText mEdtWriteoffNum, EditText mEdtBorrowMoney, EditText mEdtUnpayed, EditText mEdtPayed) {
        List<PsonLoanListResp.RowsBean> rows = psonLoanListResp.getRows();
        if (rows == null || rows.size() == 0) {
            new AlertDialog.Builder(this).setTitle("提示")
                    .setMessage("您没有个人借款信息！")
                    .setPositiveButton("确定", (dialog, which) -> dialog.dismiss()).show();
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
