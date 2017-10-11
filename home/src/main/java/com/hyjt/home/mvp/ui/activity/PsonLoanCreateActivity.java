package com.hyjt.home.mvp.ui.activity;

import android.app.Activity;
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
import com.hyjt.home.mvp.presenter.PsonLoanCreatePresenter;

import com.hyjt.home.R;
import com.hyjt.home.mvp.ui.adapter.AccessoryAdapter;
import com.hyjt.home.mvp.ui.view.DepartmentPop;
import com.hyjt.home.mvp.ui.view.DeptTreePop;
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


    private android.widget.RelativeLayout mRlPsonloan;
    private android.widget.ImageView mIvTopBack;
    private android.widget.TextView mTvTitle;
    private android.widget.ImageView mIvTopSelect;
    private android.support.v7.widget.RecyclerView mRecyAccessory;
    private List<AccessoryResq> accessoryResqsList = new ArrayList<>();
    private AccessoryAdapter accessoryAdapter;
    private RelativeLayout mRlAddFile;
    private android.widget.Button mBtnAddFile;
    private String username;
    private android.widget.EditText mEdtProposer;
    private android.widget.EditText mEdtCompanyName;
    private android.widget.EditText mEdtDepartment;
    private android.widget.EditText mEdtFristLeader;
    private android.widget.EditText mEdtExpenseReason;
    private android.widget.EditText mEdtExpenseAmount;
    private android.widget.EditText mEdtExpenseType;
    private DeptTreePop deptTreePop;
    private StaffNameIdKey DeptIdStr = new StaffNameIdKey();
    private StaffNameIdKey receiver = new StaffNameIdKey();
    private Activity mContext;

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
        mEdtDepartment = (EditText) findViewById(R.id.edt_department);

        mEdtDepartment.setOnClickListener(v -> mPresenter.requestDept());
        mEdtFristLeader = (EditText) findViewById(R.id.edt_frist_leader);
        mEdtFristLeader.setOnClickListener(v -> selStaff(this, mEdtFristLeader, receiver, false));
        mEdtExpenseReason = (EditText) findViewById(R.id.edt_expense_reason);
        mEdtExpenseAmount = (EditText) findViewById(R.id.edt_expense_amount);
        mEdtExpenseType = (EditText) findViewById(R.id.edt_expense_type);

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
                if (!TextUtils.isEmpty(accessoryResq.getLoadUrl())){
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
    public void showDeptTree(List<Node> deptList) {
        deptTreePop = new DeptTreePop(this, deptList, mEdtDepartment, DeptIdStr);
        deptTreePop.showAtLocation(findViewById(R.id.rl_psonloan),
                Gravity.CENTER | Gravity.CENTER_HORIZONTAL, 0, 0);
    }

    @Override
    public void getLinkmanOk(String[] nameAry, String[] nameSendAry, EditText selEdit, StaffNameIdKey staffNameId, boolean moreCheck) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.home_dialog_sel_list, null);
        dialog.setTitle("选择人员");
        dialog.setView(layout)
                .setCancelable(false)
                .setNegativeButton("取消", (dialog1, id) -> dialog1.cancel());
        AlertDialog accAlert = dialog.create();
        ListView accList = (ListView) layout.findViewById(R.id.accList);

        List<Map<String, String>> itemData = new ArrayList<>();
        Map<String, String> mp;
        for (String item : nameAry) {
            mp = new HashMap<>();
            mp.put("name", item);
            itemData.add(mp);
        }
        SimpleAdapter czadapter = new SimpleAdapter(mContext, itemData,
                R.layout.home_dialog_sel_item, new String[]{"name"},
                new int[]{R.id.tv_simple_text});
        accList.setAdapter(czadapter);

        if (moreCheck) {
            accList.setOnItemClickListener((arg0, arg1, arg2, arg3) -> {
                Map<String, String> m = itemData.get(arg2);
                String str = selEdit.getText().toString();
                if ("".equals(str)) {
                    selEdit.setText(m.get("name"));
                    staffNameId.setSendKey(nameSendAry[arg2]);
                } else {
                    if (str.contains(m.get("name"))) {
                        accAlert.dismiss();
                        return;
                    }
                    selEdit.setText(str + "," + m.get("name"));
                    String sendKey = staffNameId.getSendKey();
                    if (!TextUtils.isEmpty(sendKey))
                        sendKey = sendKey.replace("&", "-");
                    staffNameId.setSendKey(sendKey + nameSendAry[arg2]);
                }
                Log.e("selStaff", staffNameId.getSendKey());
                accAlert.dismiss();
            });
        } else {
            accList.setOnItemClickListener((arg0, arg1, arg2, arg3) -> {
                Map<String, String> m = itemData.get(arg2);
                selEdit.setText(m.get("name"));
                staffNameId.setSendKey(nameSendAry[arg2]);
                Log.e("selStaff", staffNameId.getSendKey());
                accAlert.dismiss();
            });
        }
        InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(selEdit.getWindowToken(), 0);
        accAlert.show();
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

    /**
     * 选择员工弹窗
     *
     * @param mContext           上下文
     * @param mEdtMeetingCompere edittext
     * @param meetingCompere     后台需要的数据类型
     * @param b                  是否多选
     */
    private void selStaff(Context mContext, EditText mEdtMeetingCompere, StaffNameIdKey meetingCompere, boolean b) {
        DepartmentPop departmentPop = new DepartmentPop(mContext, mEdtMeetingCompere, meetingCompere, b);
        departmentPop.setOnItemClickListener((Department, editText, meetingPerson, moreCheck) -> {
            departmentPop.dismiss();
            mPresenter.getLinkmanMsg(Department, editText, meetingPerson, moreCheck);
        });
        departmentPop.showAtLocation(findViewById(R.id.rl_psonloan),
                Gravity.CENTER | Gravity.CENTER_HORIZONTAL, 0, 0);
    }
}
