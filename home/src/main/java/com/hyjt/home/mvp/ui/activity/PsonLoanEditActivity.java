package com.hyjt.home.mvp.ui.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
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
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
import com.hyjt.frame.base.BaseActivity;
import com.hyjt.frame.di.component.AppComponent;
import com.hyjt.frame.utils.UiUtils;

import com.hyjt.home.di.component.DaggerPsonLoanEditComponent;
import com.hyjt.home.di.module.PsonLoanEditModule;
import com.hyjt.home.mvp.contract.PsonLoanEditContract;
import com.hyjt.home.mvp.model.entity.Reqs.PsonLoanCreateReqs;
import com.hyjt.home.mvp.model.entity.Resp.PLFristLeaderResp;
import com.hyjt.home.mvp.model.entity.Resp.PsonLoanDetailResp;
import com.hyjt.home.mvp.presenter.PsonLoanEditPresenter;

import com.hyjt.home.R;
import com.hyjt.home.mvp.ui.adapter.FrstLdAdapter;
import com.hyjt.home.mvp.ui.view.Constant;
import com.hyjt.home.mvp.ui.view.GetSingleSelectItem;

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
    private android.widget.EditText mEdtPaymentType;
    private android.widget.EditText mEdtAccountName;
    private android.widget.EditText mEdtOpenactBank;
    private android.widget.EditText mEdtBankAccount;
    private EditText mEdtLoanNum;
    private EditText mEdtLoanTime;
    private List<PsonLoanDetailResp.FlowPackBean> flowPack = new ArrayList<>();
    private List<PsonLoanDetailResp.FilePackBean> filePack = new ArrayList<>();
    private LinearLayout mLLFlowNode;
    private String currentPerson;
    private Button mBtnSelBankAccount;
    private LinearLayout mLlTransferMsg;
    private LinearLayout mLlFilePack;
    private RelativeLayout mRlAddFile;
    private Button mBtnAddFile;
    private RelativeLayout mRlTellerConfirm;
    private ImageView mIvTellerSign;
    private Button mBtnTellerConfirm;
    private RelativeLayout mRlReceiverAccount;
    private EditText mEdtReceiverAccount;
    private ImageView mIvReceiverSign;
    private Button mBtnReceiverConfirm;
    private RelativeLayout mRlReceiverConfirm;
    private EditText mEdtRemark;
    private Button mBtnDelLoan;
    private Button mBtnEditLoan;
    private LinearLayout mLlBottomBtn;
    private ProgressDialog progressDialog;
    private boolean editState;
    private PsonLoanDetailResp psonLoanDetailResp;
    private PLFristLeaderResp.FlowDetailsBean flowDetailsBean;
    private PsonLoanEditActivity mContext;

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
        mContext = this;
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

        mBtnSelBankAccount = (Button) findViewById(R.id.btn_sel_bank_account);
        mBtnSelBankAccount.setVisibility(View.GONE);
        mLlTransferMsg = (LinearLayout) findViewById(R.id.ll_transfer_msg);
        mLlTransferMsg.setVisibility(View.GONE);

        mEdtLoanNum = (EditText) findViewById(R.id.edt_loan_num);
        mEdtLoanTime = (EditText) findViewById(R.id.edt_loan_time);
        mEdtProposer = (EditText) findViewById(R.id.edt_proposer);
        mEdtCompany = (EditText) findViewById(R.id.edt_company);
        mEdtDepartment = (EditText) findViewById(R.id.edt_department);
        mEdtFristLeader = (EditText) findViewById(R.id.edt_frist_leader);
        mEdtLoanReason = (EditText) findViewById(R.id.edt_loan_reason);
        mEdtLoanAmount = (EditText) findViewById(R.id.edt_loan_amount);
        mEdtPaymentType = (EditText) findViewById(R.id.edt_payment_type);
        mEdtAccountName = (EditText) findViewById(R.id.edt_account_name);
        mEdtOpenactBank = (EditText) findViewById(R.id.edt_openact_bank);
        mEdtBankAccount = (EditText) findViewById(R.id.edt_bank_account);
        mLLFlowNode = (LinearLayout) findViewById(R.id.ll_flow_node);
        mLlFilePack = (LinearLayout) findViewById(R.id.ll_file_pack);
        mRlAddFile = (RelativeLayout) findViewById(R.id.rl_add_file);
        mBtnAddFile = (Button) findViewById(R.id.btn_add_file);

        mBtnEditLoan = (Button) findViewById(R.id.btn_edit_loan);
        mBtnEditLoan.setOnClickListener(v -> {
            if (editState) {
                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this)
                        .setTitle("修改借款信息")
                        .setMessage("确定修改该条个人借款?")
                        .setPositiveButton("确定", (dialog, which) -> sendEditPLMsg())
                        .setNegativeButton("返回", (dialog, which) -> dialog.dismiss());
                builder.show();
            } else {
                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this)
                        .setTitle("提交审批信息")
                        .setMessage("确定提交该条审批信息?")
                        .setPositiveButton("确定", (dialog, which) -> {
                            progressDialog = ProgressDialog.show(this, null, "审批信息提交中…");
//                            mPresenter.delPsonLoan(psonLoanId);
                        }).setNegativeButton("返回", (dialog, which) -> dialog.dismiss());
                builder.show();
            }
        });
        mBtnDelLoan = (Button) findViewById(R.id.btn_del_loan);
        mBtnDelLoan.setOnClickListener(v -> {
            android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this)
                    .setTitle("删除借款")
                    .setMessage("确定删除该条个人借款?")
                    .setPositiveButton("确定", (dialog, which) -> {
                        progressDialog = ProgressDialog.show(this, null, "借款信息删除中…");
                        mPresenter.delPsonLoan(psonLoanId);
                    }).setNegativeButton("返回", (dialog, which) -> dialog.dismiss());
            builder.show();
        });
        mLlBottomBtn = (LinearLayout) findViewById(R.id.ll_bottom_btn);

        mRlTellerConfirm = (RelativeLayout) findViewById(R.id.rl_teller_confirm);
        mIvTellerSign = (ImageView) findViewById(R.id.iv_teller_sign);
        mBtnTellerConfirm = (Button) findViewById(R.id.btn_teller_confirm);
        mRlReceiverAccount = (RelativeLayout) findViewById(R.id.rl_receiver_account);
        mEdtReceiverAccount = (EditText) findViewById(R.id.edt_receiver_account);
        mIvReceiverSign = (ImageView) findViewById(R.id.iv_receiver_sign);
        mBtnReceiverConfirm = (Button) findViewById(R.id.btn_receiver_confirm);
        mRlReceiverConfirm = (RelativeLayout) findViewById(R.id.rl_receiver_confirm);
        mEdtRemark = (EditText) findViewById(R.id.edt_remark);
        mPresenter.getPsonLoanDetail(psonLoanId);
    }

    private void sendEditPLMsg() {
        progressDialog = ProgressDialog.show(this, null, "借款信息提交中…");
        PsonLoanCreateReqs psonLoanCreateReqs = new PsonLoanCreateReqs();
        psonLoanCreateReqs.setId(psonLoanDetailResp.getId());
        psonLoanCreateReqs.setCwPcompany(mEdtCompany.getText().toString().trim());
        psonLoanCreateReqs.setCwPLeader(mEdtFristLeader.getText().toString().trim());
        psonLoanCreateReqs.setCwPreason(mEdtLoanReason.getText().toString().trim());
        psonLoanCreateReqs.setCwPmoney(mEdtLoanAmount.getText().toString().trim());
        psonLoanCreateReqs.setCwPmode(mEdtPaymentType.getText().toString().trim());
        psonLoanCreateReqs.setCwRpname(mEdtAccountName.getText().toString().trim());
        psonLoanCreateReqs.setCwRpbank(mEdtOpenactBank.getText().toString().trim());
        psonLoanCreateReqs.setCwRpnum(mEdtBankAccount.getText().toString().trim());

//        psonLoanCreateReqs.setFlowid();
        psonLoanCreateReqs.setCwPtext(mEdtRemark.getText().toString().trim());
        List<PsonLoanDetailResp.FilePackBean> fileDetailList = psonLoanDetailResp.getFilePack();
        List<PsonLoanCreateReqs.FilePackBean> fileCreateList = new ArrayList<>();
        for (PsonLoanDetailResp.FilePackBean filePackBean : fileDetailList ){
            PsonLoanCreateReqs.FilePackBean fileCreate =
                    new PsonLoanCreateReqs.FilePackBean(filePackBean.getFileId(), filePackBean.getFileName());
            fileCreateList.add(fileCreate);

        }
        psonLoanCreateReqs.setFilePack(fileCreateList);
//        psonLoanCreateReqs.setFlowid();
        mPresenter.editPsonLoan(psonLoanCreateReqs);
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
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void showPLDetail(PsonLoanDetailResp psonLoanDetailResp) {
        this.psonLoanDetailResp = psonLoanDetailResp;
        flowDetailsBean.setFlowid(psonLoanDetailResp.getFlowid());
        currentPerson = psonLoanDetailResp.getCurrentPerson().trim();

        if (!getUserName().equals(psonLoanDetailResp.getCwPpersonal())
                || !"提交".equals(psonLoanDetailResp.getCwJKState())) {
            mEdtCompany.setFocusable(false);
            mEdtDepartment.setFocusable(false);
            mEdtFristLeader.setFocusable(false);
            mEdtLoanReason.setFocusable(false);
            mEdtLoanAmount.setFocusable(false);
            mEdtPaymentType.setFocusable(false);
            mEdtAccountName.setFocusable(false);
            mEdtOpenactBank.setFocusable(false);
            mEdtBankAccount.setFocusable(false);
            mEdtRemark.setFocusable(false);
            mBtnDelLoan.setVisibility(View.GONE);
            editState = false;
        } else {
            setBaseMsgOnClick();
        }

        mEdtLoanNum.setText(psonLoanDetailResp.getCwPnum());
        mEdtLoanTime.setText(psonLoanDetailResp.getSqDate());
        mEdtProposer.setText(psonLoanDetailResp.getCwPpersonal());
        mEdtCompany.setText(psonLoanDetailResp.getCwPcompany());
        mEdtDepartment.setText(psonLoanDetailResp.getCwPdepartment());
        mEdtFristLeader.setText(psonLoanDetailResp.getCwPLeader());
        mEdtLoanReason.setText(psonLoanDetailResp.getCwPreason());
        mEdtLoanAmount.setText(psonLoanDetailResp.getCwPmoney());
        mEdtPaymentType.setText(psonLoanDetailResp.getCwPmode());
        mEdtAccountName.setText(psonLoanDetailResp.getCwRpname());
        mEdtOpenactBank.setText(psonLoanDetailResp.getCwRpbank());
        mEdtBankAccount.setText(psonLoanDetailResp.getCwRpnum());
//        mEdtRemark.setText(psonLoanDetailResp.getCw);

        flowPack.clear();
        flowPack.addAll(psonLoanDetailResp.getFlowPack());
        for (int position = 0; position < flowPack.size(); position++) {
            setFlowPack(position);
        }
        filePack.clear();
        if (psonLoanDetailResp.getFilePack() != null && psonLoanDetailResp.getFilePack().size() > 0) {
            filePack.addAll(psonLoanDetailResp.getFilePack());
            for (int position = 0; position < filePack.size(); position++) {
                setFilePack(position);
            }
        } else {
            mRlAddFile.setVisibility(View.GONE);
        }

        if ("审批完成".equals(psonLoanDetailResp.getFlowState())) {
            if (!"0".equals(psonLoanDetailResp.getCashierQren())) {
                mBtnTellerConfirm.setVisibility(View.GONE);
                Glide.with(this).load(getString(R.string.home_base_url) +
                        psonLoanDetailResp.getCashierQren()).into(mIvTellerSign);
            }
            if (!"0".equals(psonLoanDetailResp.getCwPpersonalQren())) {
                mBtnReceiverConfirm.setVisibility(View.GONE);
                Glide.with(this).load(getString(R.string.home_base_url) +
                        psonLoanDetailResp.getCwPpersonalQren()).into(mIvReceiverSign);
            }

        } else {
            mRlReceiverConfirm.setVisibility(View.GONE);
            mRlReceiverAccount.setVisibility(View.GONE);
            mRlTellerConfirm.setVisibility(View.GONE);
        }

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

    private void setBaseMsgOnClick() {
        mEdtCompany.setOnClickListener(v -> {
            progressDialog = ProgressDialog.show(this, null, "加载公司列表…");
            mPresenter.getPLCompany();
        });
        mEdtFristLeader.setOnClickListener(v -> {
            progressDialog = ProgressDialog.show(this, null, "加载首签领导…");
            mPresenter.getFristLeader("个人借款");
        });
        mEdtPaymentType.setOnTouchListener(new GetSingleSelectItem(
                this, mEdtPaymentType, "收款方式", Constant.payTypeArr, false));
        mEdtPaymentType.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                if ("转账".equals(s.toString())){
                    mLlTransferMsg.setVisibility(View.VISIBLE);
                    mBtnSelBankAccount.setVisibility(View.VISIBLE);
                } else if ("现金".equals(s.toString())){
                    mLlTransferMsg.setVisibility(View.GONE);
                    mBtnSelBankAccount.setVisibility(View.GONE);
                }
            }
        });
    }

    private void setFilePack(int position) {
        PsonLoanDetailResp.FilePackBean filePackBean = filePack.get(position);
        View inflate = LayoutInflater.from(this).inflate(R.layout.home_add_comn_filepack, null);
        TextView certificate = (TextView) inflate.findViewById(R.id.tv_certificate);
        certificate.setText(filePackBean.getFileName());
        certificate.setOnClickListener(v -> {
            // 弹出一个选择浏览器的框，选择浏览器再进入
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.home_base_url) + filePackBean.getFilePath()));
            startActivity(intent);
        });
        Button DelFile = (Button) inflate.findViewById(R.id.btn_del_file);
        DelFile.setVisibility(View.GONE);
        mLlFilePack.addView(inflate);
    }


    private void setFlowPack(int position) {

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

        if (!getUserName().equals(currentPerson) || !currentPerson.equals(flowPackBean.getNodePerson().trim())) {
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
