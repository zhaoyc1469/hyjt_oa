package com.hyjt.home.mvp.ui.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
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
import com.hyjt.frame.utils.PermissionUtil;
import com.hyjt.frame.utils.UiUtils;

import com.hyjt.home.di.component.DaggerPsonLoanCreateComponent;
import com.hyjt.home.di.module.PsonLoanCreateModule;
import com.hyjt.home.mvp.contract.PsonLoanCreateContract;
import com.hyjt.home.mvp.model.entity.Reqs.PsonLoanCreateReqs;
import com.hyjt.home.mvp.model.entity.Resp.ImgUploadResp;
import com.hyjt.home.mvp.model.entity.Resp.PLBankAccountResp;
import com.hyjt.home.mvp.model.entity.Resp.PLCompanyResp;
import com.hyjt.home.mvp.model.entity.Resp.PLFristLeaderResp;
import com.hyjt.home.mvp.presenter.PsonLoanCreatePresenter;

import com.hyjt.home.R;
import com.hyjt.home.mvp.ui.adapter.BankAccountAdapter;
import com.hyjt.home.mvp.ui.adapter.ComnStringAdapter;
import com.hyjt.home.mvp.ui.adapter.FrstLdAdapter;
import com.hyjt.home.mvp.ui.view.Constant;
import com.hyjt.home.mvp.ui.view.GetSingleSelectItem;
import com.hyjt.home.utils.ImgUtil;
import com.tbruyelle.rxpermissions2.RxPermissions;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;

import static com.hyjt.frame.utils.Preconditions.checkNotNull;

@Route(path = "/home/PsonLoanCreateActivity")
public class PsonLoanCreateActivity extends BaseActivity<PsonLoanCreatePresenter> implements PsonLoanCreateContract.View {


    private RelativeLayout mRlPsonloan;
    private ImageView mIvTopBack;
    private TextView mTvTitle;
    private ImageView mIvTopSelect;
    private RelativeLayout mRlAddFile;
    private Button mBtnAddFile;
    private String username;
    private EditText mEdtProposer;
    private EditText mEdtCompanyName;
    private EditText mEdtFristLeader;
    private EditText mEdtExpenseReason;
    private EditText mEdtExpenseAmount;
    private EditText mEdtExpenseType;
    private Activity mContext;
    private ProgressDialog progressDialog;
    private PLFristLeaderResp.FlowDetailsBean flowDetailsBean;
    private LinearLayout mLlTransferMsg;
    private EditText mEdtBankAccount;
    private EditText mEdtAccountName;
    private EditText mEdtOpenactBank;
    private EditText mEdtRemark;
    private Button mBtnSelBankAccount;
    private Button mBtnCreateLoan;
    private List<PsonLoanCreateReqs.FilePackBean> FileList = new ArrayList<>();
    private ArrayList<Uri> mFileURLList = new ArrayList<>();
    private LinearLayout mLlFilePack;
    private ScrollView mSlvPsonLoan;

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerPsonLoanCreateComponent
                .builder()
                .appComponent(appComponent)
                .psonLoanCreateModule(new PsonLoanCreateModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(Bundle savedInstanceState) {
        return R.layout.home_activity_pson_loan_create;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        mContext = this;
        SharedPreferences sharedPre = getSharedPreferences("config", MODE_PRIVATE);
        username = sharedPre.getString("username", "");
        mRlPsonloan = (RelativeLayout) findViewById(R.id.rl_psonloan);
        mIvTopBack = (ImageView) findViewById(R.id.iv_top_back);
        mIvTopBack.setOnClickListener(v -> finish());
        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mTvTitle.setText("发起个人借款");
        mTvTitle.setOnClickListener(v -> finish());
        mIvTopSelect = (ImageView) findViewById(R.id.iv_top_select);
        mIvTopSelect.setVisibility(View.GONE);
        mLlTransferMsg = (LinearLayout) findViewById(R.id.ll_transfer_msg);
        mLlTransferMsg.setVisibility(View.GONE);
        mEdtProposer = (EditText) findViewById(R.id.edt_proposer);
        mEdtProposer.setText(username);
        mEdtCompanyName = (EditText) findViewById(R.id.edt_company_name);
        mEdtCompanyName.setOnClickListener(v -> {
            progressDialog = ProgressDialog.show(this, null, "加载公司列表…");
            mPresenter.getPLCompany();
        });
        mEdtFristLeader = (EditText) findViewById(R.id.edt_frist_leader);
        mEdtFristLeader.setOnClickListener(v -> {
            progressDialog = ProgressDialog.show(this, null, "加载首签领导…");
            mPresenter.getFristLeader("个人借款");
        });

        mEdtExpenseReason = (EditText) findViewById(R.id.edt_expense_reason);
        mEdtExpenseAmount = (EditText) findViewById(R.id.edt_expense_amount);

        mEdtExpenseType = (EditText) findViewById(R.id.edt_expense_type);
        mEdtExpenseType.setOnTouchListener(new GetSingleSelectItem(
                this, mEdtExpenseType, "支付方式", Constant.payTypeArr, false));

        mEdtBankAccount = (EditText) findViewById(R.id.edt_bank_account);
        mEdtAccountName = (EditText) findViewById(R.id.edt_account_name);
        mEdtOpenactBank = (EditText) findViewById(R.id.edt_openact_bank);
        mEdtRemark = (EditText) findViewById(R.id.edt_remark);
        mBtnSelBankAccount = (Button) findViewById(R.id.btn_sel_bank_account);
        mBtnSelBankAccount.setVisibility(View.GONE);
        mBtnSelBankAccount.setOnClickListener(v -> {
            progressDialog = ProgressDialog.show(this, null, "加载银行列表…");
            mPresenter.getOpenBank();
        });
        mRlAddFile = (RelativeLayout) findViewById(R.id.rl_add_file);
        mBtnAddFile = (Button) findViewById(R.id.btn_add_file);
        mBtnAddFile.setOnClickListener(v -> {
            Intent intentFile = new Intent(Intent.ACTION_GET_CONTENT);
            intentFile.setType("*/*");//设置类型，我这里是任意类型，任意后缀的可以这样写。
            intentFile.addCategory(Intent.CATEGORY_OPENABLE);
            startActivityForResult(intentFile, 100);
        });
        mLlFilePack = (LinearLayout) findViewById(R.id.ll_file_pack);
        mSlvPsonLoan = (ScrollView) findViewById(R.id.slv_psonloan);
        mBtnCreateLoan = (Button) findViewById(R.id.btn_create_loan);
        mBtnCreateLoan.setOnClickListener(v -> checkNullMsg());
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
                    mLlTransferMsg.setVisibility(View.VISIBLE);
                    mBtnSelBankAccount.setVisibility(View.VISIBLE);
                } else if ("现金".equals(s.toString())) {
                    mLlTransferMsg.setVisibility(View.GONE);
                    mBtnSelBankAccount.setVisibility(View.GONE);
                }
            }
        });
    }

    private void checkNullMsg() {
        if (TextUtils.isEmpty(mEdtCompanyName.getText())) {
            shortToast("请选择公司名称");
        }
        if (TextUtils.isEmpty(mEdtFristLeader.getText())) {
            shortToast("请选择首签领导");
        }
        if (TextUtils.isEmpty(mEdtExpenseReason.getText())) {
            shortToast("请填写借款事由");
        }
        if (TextUtils.isEmpty(mEdtExpenseAmount.getText())) {
            shortToast("请填写借款金额");
        }
        if (TextUtils.isEmpty(mEdtExpenseType.getText())) {
            shortToast("请选择收款方式");
        }

        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this)
                .setTitle("发起借款")
                .setMessage("确定发起个人借款?")
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
        checkNotNull(message);
        UiUtils.snackbarText(message);
        shortToast(message);
    }

    @Override
    public void killMyself() {
        finish();
    }

    @Override
    public RxPermissions getRxPermissions() {
        return new RxPermissions(this);
    }

    @Override
    public void hideLoading() {
        if (progressDialog != null)
            progressDialog.dismiss();
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
        imm.hideSoftInputFromWindow(mEdtFristLeader.getWindowToken(), 0);
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
        mSlvPsonLoan.fullScroll(ScrollView.FOCUS_DOWN);
    }

    @Override
    public void showBankAccount(PLBankAccountResp plBankAccountResp) {

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
        dialog.setTitle("选择公司");
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
        PsonLoanCreateReqs psonLoanCreateReqs = new PsonLoanCreateReqs();
        psonLoanCreateReqs.setCwPcompany(mEdtCompanyName.getText().toString().trim());
        psonLoanCreateReqs.setCwPLeader(mEdtFristLeader.getText().toString().trim());
        psonLoanCreateReqs.setCwPreason(mEdtExpenseReason.getText().toString().trim());
        psonLoanCreateReqs.setCwPmoney(mEdtExpenseAmount.getText().toString().trim());
        psonLoanCreateReqs.setCwPmode(mEdtExpenseType.getText().toString().trim());
        psonLoanCreateReqs.setCwRpname(mEdtAccountName.getText().toString().trim());
        psonLoanCreateReqs.setCwRpbank(mEdtOpenactBank.getText().toString().trim());
        psonLoanCreateReqs.setCwRpnum(mEdtBankAccount.getText().toString().trim());
        psonLoanCreateReqs.setCwPtext(mEdtRemark.getText().toString().trim());
        psonLoanCreateReqs.setFlowid(flowDetailsBean.getFlowid());
        psonLoanCreateReqs.setFilePack(FileList);

        mPresenter.createPsonLoan(psonLoanCreateReqs);
    }


}
