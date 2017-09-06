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
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.hyjt.frame.base.BaseActivity;
import com.hyjt.frame.di.component.AppComponent;
import com.hyjt.frame.utils.UiUtils;
import com.hyjt.home.R;
import com.hyjt.home.di.component.DaggerSLConsultCreateComponent;
import com.hyjt.home.di.module.SLConsultCreateModule;
import com.hyjt.home.mvp.contract.SLConsultCreateContract;
import com.hyjt.home.mvp.model.entity.Reqs.StaffNameIdKey;
import com.hyjt.home.mvp.model.entity.Resp.ReportTDetailResp;
import com.hyjt.home.mvp.model.entity.Resp.SLConsultDetailResp;
import com.hyjt.home.mvp.presenter.SLConsultCreatePresenter;
import com.hyjt.home.mvp.ui.view.DepartmentPop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.hyjt.frame.utils.Preconditions.checkNotNull;

@Route(path = "/home/SLConsultCreateActivity")
public class SLConsultCreateActivity extends BaseActivity<SLConsultCreatePresenter> implements SLConsultCreateContract.View {


    private android.widget.RelativeLayout rlSlcCreate;
    private android.widget.ImageView ivTopBack;
    private android.widget.TextView tvTitle;
    private android.widget.ImageView ivTopSelect;
    private android.widget.LinearLayout llBottomBtn;
    private android.widget.Button btnEditConsult;
    private android.widget.ScrollView slvConsult;
    private android.widget.EditText edtRpName;
    private android.widget.EditText edtRpLeader;
    private android.widget.EditText edtContent;
    private android.widget.EditText edtIdeaExpect;
    private SLConsultCreateActivity mContext;
    private ProgressDialog progressDialog;
    private StaffNameIdKey receiver = new StaffNameIdKey();

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerSLConsultCreateComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .sLConsultCreateModule(new SLConsultCreateModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(Bundle savedInstanceState) {
        return R.layout.home_activity_slconsult_create; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(Bundle savedInstanceState) {

        mContext = this;

        rlSlcCreate = (RelativeLayout) findViewById(R.id.rl_slc_create);
        ivTopBack = (ImageView) findViewById(R.id.iv_top_back);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        ivTopSelect = (ImageView) findViewById(R.id.iv_top_select);
        llBottomBtn = (LinearLayout) findViewById(R.id.ll_bottom_btn);
        btnEditConsult = (Button) findViewById(R.id.btn_edit_consult);
        slvConsult = (ScrollView) findViewById(R.id.slv_consult);
        edtRpName = (EditText) findViewById(R.id.edt_rp_name);
        edtRpLeader = (EditText) findViewById(R.id.edt_rp_leader);
        edtContent = (EditText) findViewById(R.id.edt_content);
        edtIdeaExpect = (EditText) findViewById(R.id.edt_idea_expect);


        edtRpName.setText(getUserName());

        btnEditConsult.setOnClickListener(v -> {
            android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this)
                    .setTitle("创建协商")
                    .setMessage("确定创建该条协商信息?")
                    .setPositiveButton("确定", (dialog, which) -> {
                        progressDialog = ProgressDialog.show(this, null, "协商创建中…");
                        SLConsultDetailResp slConsultDetailResp = new SLConsultDetailResp();
                        slConsultDetailResp.setWorkerId(receiver.getSendKey());
                        slConsultDetailResp.setContent(edtContent.getText().toString());
                        slConsultDetailResp.setMind(edtIdeaExpect.getText().toString());
                        mPresenter.createConsult(slConsultDetailResp);
                    }).setNegativeButton("返回", (dialog, which) -> {
                        dialog.dismiss();
                    });
            builder.show();
        });

        edtRpLeader.setOnClickListener(v -> selStaff(mContext, edtRpLeader, receiver, false));
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
        departmentPop.showAtLocation(findViewById(R.id.rl_slc_create),
                Gravity.CENTER | Gravity.CENTER_HORIZONTAL, 0, 0);
    }

}
