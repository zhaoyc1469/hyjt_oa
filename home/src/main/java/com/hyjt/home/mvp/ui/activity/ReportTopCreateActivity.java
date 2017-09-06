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
import com.hyjt.home.R;
import com.hyjt.home.di.component.DaggerReportTopCreateComponent;
import com.hyjt.home.di.module.ReportTopCreateModule;
import com.hyjt.home.mvp.contract.ReportTopCreateContract;
import com.hyjt.home.mvp.model.entity.Reqs.StaffNameIdKey;
import com.hyjt.home.mvp.model.entity.Resp.ReportTDetailResp;
import com.hyjt.home.mvp.presenter.ReportTopCreatePresenter;
import com.hyjt.home.mvp.ui.view.DepartmentPop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.hyjt.frame.utils.Preconditions.checkNotNull;

@Route(path = "/home/ReportTopCreateActivity")
public class ReportTopCreateActivity extends BaseActivity<ReportTopCreatePresenter> implements ReportTopCreateContract.View {


    private android.widget.ImageView mIvTopBack;
    private android.widget.TextView mTvTitle;
    private android.widget.ImageView mIvTopSelect;
    private android.widget.LinearLayout mLlBottomBtn;
    private android.widget.Button mBtnEditReport;
    private android.widget.ScrollView mSlvReport;
    private android.widget.EditText mEdtRpName;
    private android.widget.EditText mEdtRpLeader;
    private android.widget.EditText mEdtContent;
    private android.widget.EditText mEdtIdeaExpect;
    private ProgressDialog progressDialog;
    private ReportTopCreateActivity mContext;
    private StaffNameIdKey receiver = new StaffNameIdKey();


    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerReportTopCreateComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .reportTopCreateModule(new ReportTopCreateModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(Bundle savedInstanceState) {
        return R.layout.home_activity_report_top_create; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(Bundle savedInstanceState) {

        mContext = this;

        mIvTopBack = (ImageView) findViewById(R.id.iv_top_back);
        mIvTopBack.setOnClickListener(v -> finish());
        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mTvTitle.setText("创建汇报");
        mTvTitle.setOnClickListener(v -> finish());
        mIvTopSelect = (ImageView) findViewById(R.id.iv_top_select);
        mIvTopSelect.setVisibility(View.GONE);
        mLlBottomBtn = (LinearLayout) findViewById(R.id.ll_bottom_btn);
        mBtnEditReport = (Button) findViewById(R.id.btn_edit_report);
        mSlvReport = (ScrollView) findViewById(R.id.slv_report);
        mEdtRpName = (EditText) findViewById(R.id.edt_rp_name);
        mEdtRpLeader = (EditText) findViewById(R.id.edt_rp_leader);
        mEdtContent = (EditText) findViewById(R.id.edt_content);
        mEdtIdeaExpect = (EditText) findViewById(R.id.edt_idea_expect);

        mEdtRpName.setText(getUserName());

        mBtnEditReport.setOnClickListener(v -> {
            progressDialog = ProgressDialog.show(this, null, "报告创建中…");
            ReportTDetailResp reportTDetailResp = new ReportTDetailResp();
            reportTDetailResp.setBossId(receiver.getSendKey());
            reportTDetailResp.setContent(mEdtContent.getText().toString());
            reportTDetailResp.setMind(mEdtIdeaExpect.getText().toString());
            mPresenter.createReport(reportTDetailResp);
        });

        mEdtRpLeader.setOnClickListener(v -> selStaff(mContext, mEdtRpLeader, receiver, false));

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
        if (progressDialog != null)
            progressDialog.dismiss();
    }

    @Override
    public void getLinkmanOk(String[] nameAry, String[] nameSendAry, EditText selEdit, StaffNameIdKey StaffNameIdKey, boolean moreCheck) {
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
                    StaffNameIdKey.setSendKey(nameSendAry[arg2]);
                } else {
                    if (str.contains(m.get("name"))) {
                        accAlert.dismiss();
                        return;
                    }
                    selEdit.setText(str + "," + m.get("name"));
                    String sendKey = StaffNameIdKey.getSendKey();
                    if (!TextUtils.isEmpty(sendKey))
                        sendKey = sendKey.replace("&", "-");
                    StaffNameIdKey.setSendKey(sendKey + nameSendAry[arg2]);
                }
                Log.e("selStaff", StaffNameIdKey.getSendKey());
                accAlert.dismiss();
            });
        } else {
            accList.setOnItemClickListener((arg0, arg1, arg2, arg3) -> {
                Map<String, String> m = itemData.get(arg2);
                selEdit.setText(m.get("name"));
                StaffNameIdKey.setSendKey(nameSendAry[arg2]);
                Log.e("selStaff", StaffNameIdKey.getSendKey());
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
        departmentPop.showAtLocation(findViewById(R.id.rl_rpt_create),
                Gravity.CENTER | Gravity.CENTER_HORIZONTAL, 0, 0);
    }
}
