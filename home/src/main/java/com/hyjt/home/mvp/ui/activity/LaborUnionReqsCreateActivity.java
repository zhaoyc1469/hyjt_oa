package com.hyjt.home.mvp.ui.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
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
import android.widget.ScrollView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.hyjt.frame.base.BaseActivity;
import com.hyjt.frame.di.component.AppComponent;
import com.hyjt.frame.utils.UiUtils;

import com.hyjt.home.di.component.DaggerLaborUnionReqsCreateComponent;
import com.hyjt.home.di.module.LaborUnionReqsCreateModule;
import com.hyjt.home.mvp.contract.LaborUnionReqsCreateContract;
import com.hyjt.home.mvp.model.entity.Reqs.StaffNameIdKey;
import com.hyjt.home.mvp.model.entity.Resp.CIdeaDetailResp;
import com.hyjt.home.mvp.model.entity.Resp.LUReqsDetailResp;
import com.hyjt.home.mvp.presenter.LaborUnionReqsCreatePresenter;

import com.hyjt.home.R;
import com.hyjt.home.mvp.ui.view.DepartmentPop;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.hyjt.frame.utils.Preconditions.checkNotNull;

@Route(path = "/home/LaborUnionReqsCreateActivity")
public class LaborUnionReqsCreateActivity extends BaseActivity<LaborUnionReqsCreatePresenter> implements LaborUnionReqsCreateContract.View {


    private android.widget.ImageView mIvTopBack;
    private android.widget.TextView mTvTitle;
    private android.widget.ImageView mIvTopSelect;
    private android.widget.LinearLayout mLlBottomBtn;
    private android.widget.Button mBtnEditCIdea;
    private android.widget.ScrollView mSlvCIdea;
    private android.widget.EditText mEdtRpLeader;
    private android.widget.EditText mEdtRpTitle;
    private android.widget.EditText mEdtContent;
    private LaborUnionReqsCreateActivity mContext;
    private ProgressDialog progressDialog;
    private StaffNameIdKey receiver = new StaffNameIdKey();

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerLaborUnionReqsCreateComponent
                .builder()
                .appComponent(appComponent)
                .laborUnionReqsCreateModule(new LaborUnionReqsCreateModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(Bundle savedInstanceState) {
        return R.layout.home_activity_labor_union_reqs_create;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        mContext = this;

        mIvTopBack = (ImageView) findViewById(R.id.iv_top_back);
        mIvTopBack.setOnClickListener(v -> finish());
        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mTvTitle.setOnClickListener(v -> finish());
        mTvTitle.setText("工会诉求");
        mIvTopSelect = (ImageView) findViewById(R.id.iv_top_select);
        mIvTopSelect.setVisibility(View.GONE);
        mLlBottomBtn = (LinearLayout) findViewById(R.id.ll_bottom_btn);
        mBtnEditCIdea = (Button) findViewById(R.id.btn_edit_cIdea);
        mSlvCIdea = (ScrollView) findViewById(R.id.slv_cIdea);
        mEdtRpLeader = (EditText) findViewById(R.id.edt_rp_leader);
        mEdtRpTitle = (EditText) findViewById(R.id.edt_rp_title);
        mEdtContent = (EditText) findViewById(R.id.edt_content);


        mEdtRpLeader.setOnClickListener(v -> selStaff(this, mEdtRpLeader, receiver, false));

        mBtnEditCIdea.setOnClickListener(v -> {
            if (checkStr()){
                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this)
                        .setTitle("创建工会诉求")
                        .setMessage("确定创建该条诉求信息?")
                        .setPositiveButton("确定", (dialog, which) -> {
                            progressDialog = ProgressDialog.show(this, null, "诉求信息创建中…");
                            LUReqsDetailResp detail = new LUReqsDetailResp();
                            detail.setBossId(receiver.getSendKey());
                            detail.setTitle(mEdtRpTitle.getText().toString());
                            detail.setReportContent(mEdtContent.getText().toString());
                            mPresenter.createLUappeal(detail);
                        }).setNegativeButton("返回", (dialog, which) -> dialog.dismiss());
                builder.show();
            }
        });
    }


    @Override
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
        UiUtils.snackbarText(message);
        shortToast(message);
    }

    private boolean checkStr() {
        if (getUserName().equals(mEdtRpLeader)){
            shortToast("您不能向自己发起献策");
            return false;
        }
        if ("".equals(mEdtRpTitle.getText().toString())){
            shortToast("请你填写献策标题");
            return false;
        }
        return true;
    }


    @Override
    public void killMyself() {
        finish();
    }

    @Override
    public void hideLoading() {
        if (progressDialog != null)
            progressDialog.dismiss();
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
        departmentPop.showAtLocation(findViewById(R.id.rl_contribute),
                Gravity.CENTER | Gravity.CENTER_HORIZONTAL, 0, 0);
    }

}
