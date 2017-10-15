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
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TabHost;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.hyjt.frame.base.BaseActivity;
import com.hyjt.frame.di.component.AppComponent;
import com.hyjt.frame.utils.PermissionUtil;
import com.hyjt.frame.utils.UiUtils;

import com.hyjt.home.di.component.DaggerPsonLoanCreateComponent;
import com.hyjt.home.di.module.PsonLoanCreateModule;
import com.hyjt.home.mvp.contract.PsonLoanCreateContract;
import com.hyjt.home.mvp.model.entity.AccessoryResq;
import com.hyjt.home.mvp.model.entity.Reqs.StaffNameIdKey;
import com.hyjt.home.mvp.model.entity.Resp.PLFristLeaderResp;
import com.hyjt.home.mvp.presenter.PsonLoanCreatePresenter;

import com.hyjt.home.R;
import com.hyjt.home.mvp.ui.adapter.AccessoryAdapter;
import com.hyjt.home.mvp.ui.adapter.FrstLdAdapter;
import com.hyjt.home.mvp.ui.view.Constant;
import com.hyjt.home.mvp.ui.view.DepartmentPop;
import com.hyjt.home.mvp.ui.view.DeptTreePop;
import com.hyjt.home.mvp.ui.view.GetSingleSelectItem;
import com.hyjt.home.mvp.ui.view.treelistview.Node;
import com.hyjt.home.utils.ImgUtil;
import com.tbruyelle.rxpermissions2.RxPermissions;


import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;

import static com.hyjt.frame.utils.Preconditions.checkNotNull;

@Route(path = "/home/PsonLoanCreateActivity")
public class PsonLoanCreateActivity extends BaseActivity<PsonLoanCreatePresenter> implements PsonLoanCreateContract.View {


    private RelativeLayout mRlPsonloan;
    private ImageView mIvTopBack;
    private TextView mTvTitle;
    private ImageView mIvTopSelect;
    private RecyclerView mRecyAccessory;
    private List<AccessoryResq> accessoryResqsList = new ArrayList<>();
    private AccessoryAdapter accessoryAdapter;
    private RelativeLayout mRlAddFile;
    private Button mBtnAddFile;
    private String username;
    private EditText mEdtProposer;
    private EditText mEdtCompanyName;
    private EditText mEdtFristLeader;
    private EditText mEdtExpenseReason;
    private EditText mEdtExpenseAmount;
    private EditText mEdtExpenseType;
    private StaffNameIdKey receiver = new StaffNameIdKey();
    private Activity mContext;
    private ProgressDialog progressDialog;
    private PLFristLeaderResp.FlowDetailsBean flowDetailsBean;

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

        mEdtProposer = (EditText) findViewById(R.id.edt_proposer);
        mEdtProposer.setText(username);
        mEdtCompanyName = (EditText) findViewById(R.id.edt_company_name);
        mEdtFristLeader = (EditText) findViewById(R.id.edt_frist_leader);

        mEdtFristLeader.setOnClickListener(v -> {
            progressDialog = ProgressDialog.show(this, null, "加载审批流程…");
            mPresenter.getFristLeader("个人借款");
        });

        mEdtExpenseReason = (EditText) findViewById(R.id.edt_expense_reason);
        mEdtExpenseAmount = (EditText) findViewById(R.id.edt_expense_amount);

        mEdtExpenseType = (EditText) findViewById(R.id.edt_expense_type);
        mEdtExpenseType.setOnTouchListener(new GetSingleSelectItem(
                this, mEdtExpenseType, "支付方式", Constant.payTypeArr, false));

        mRlAddFile = (RelativeLayout) findViewById(R.id.rl_add_file);
        mBtnAddFile = (Button) findViewById(R.id.btn_add_file);
        mBtnAddFile.setOnClickListener(v -> {
            Intent intentFile = new Intent(Intent.ACTION_GET_CONTENT);
            intentFile.setType("*/*");//设置类型，我这里是任意类型，任意后缀的可以这样写。
            intentFile.addCategory(Intent.CATEGORY_OPENABLE);
            startActivityForResult(intentFile, 100);
        });

        mRecyAccessory = (RecyclerView) findViewById(R.id.recy_accessory);
        mRecyAccessory.setLayoutManager(new LinearLayoutManager(this));
        mRecyAccessory.setNestedScrollingEnabled(false);
        accessoryAdapter = new AccessoryAdapter(accessoryResqsList);
        mRecyAccessory.setAdapter(accessoryAdapter);
        accessoryAdapter.setOnItemClickListener(new AccessoryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, AccessoryResq accessoryResq) {
                if (!TextUtils.isEmpty(accessoryResq.getLoadUrl())) {
                    // 弹出一个选择浏览器的框，选择浏览器再进入
                    Intent intent = new Intent(Intent.ACTION_VIEW,
                            Uri.parse(getString(R.string.home_base_url) + accessoryResq.getLoadUrl()));
                    startActivity(intent);
                }
            }

            @Override
            public void onDelClick(int position, AccessoryResq accessoryResq) {
                accessoryResqsList.remove(position);
                accessoryAdapter.notifyDataSetChanged();
            }
        });
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            if (resultCode == Activity.RESULT_OK) {
                Uri uri = data.getData();

                RxErrorHandler build = RxErrorHandler.builder()
                        .with(getApplicationContext())
                        .responseErrorListener((context, t) -> {
                        }).build();
                PermissionUtil.externalStorage(new PermissionUtil.RequestPermission() {
                    @Override
                    public void onRequestPermissionSuccess() {
                        AccessoryResq accessoryResq = new AccessoryResq();
                        accessoryResq.setUri(uri);
                        String imageAbsolutePath = ImgUtil.getImageAbsolutePath(getApplicationContext(), uri);
                        File file = new File(imageAbsolutePath);
                        accessoryResq.setName(file.getName());
                        accessoryResqsList.add(accessoryResq);
                        accessoryAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onRequestPermissionFailure() {
                        shortToast("您需要打开存储权限");
                    }
                }, getRxPermissions(), build);
            }
        }
    }

}
