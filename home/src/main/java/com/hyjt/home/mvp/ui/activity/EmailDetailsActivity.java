package com.hyjt.home.mvp.ui.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.hyjt.frame.base.BaseActivity;
import com.hyjt.frame.di.component.AppComponent;
import com.hyjt.frame.utils.UiUtils;
import com.hyjt.home.R;
import com.hyjt.home.di.component.DaggerEmailDetailsComponent;
import com.hyjt.home.di.module.EmailDetailsModule;
import com.hyjt.home.mvp.contract.EmailDetailsContract;
import com.hyjt.home.mvp.model.entity.Resp.CEmailDetailResp;
import com.hyjt.home.mvp.presenter.EmailDetailsPresenter;

import static com.hyjt.frame.utils.Preconditions.checkNotNull;

@Route(path = "/home/EmailDetailsActivity")
public class EmailDetailsActivity extends BaseActivity<EmailDetailsPresenter> implements EmailDetailsContract.View {


    private String emailId;
    private android.widget.RelativeLayout mRlCreateStaff;
    private android.widget.ImageView mIvTopBack;
    private android.widget.TextView mTvTitle;
    private android.widget.ImageView mIvTopSelect;
    private android.widget.LinearLayout mLlBottomBtn;
    private android.widget.Button mBtnReply;
    private android.widget.Button mBtnTranspond;
    private android.widget.ScrollView mSlvEmail;
    private android.widget.TextView mTvTheme;
    private android.widget.TextView mTvReceiver;
    private android.widget.TextView mTvSender;
    private android.widget.TextView mTvSendTime;
    private android.widget.EditText mEdtCmContent;
    private android.widget.RelativeLayout mRlAddFile;
    private android.widget.LinearLayout mLlFilePack;
    private CEmailDetailResp cEmailDetailResp;
    private ProgressDialog progressDialog;
    private String type;

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerEmailDetailsComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .emailDetailsModule(new EmailDetailsModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(Bundle savedInstanceState) {
        return R.layout.home_activity_email_details; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        Intent intent = getIntent();
        emailId = intent.getStringExtra("Id");
        type = intent.getStringExtra("Type");

        mRlCreateStaff = (RelativeLayout) findViewById(R.id.rl_create_staff);
        mIvTopBack = (ImageView) findViewById(R.id.iv_top_back);
        mIvTopBack.setOnClickListener(v -> finish());
        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mTvTitle.setOnClickListener(v -> finish());
        mTvTitle.setText("内部邮件");
        mIvTopSelect = (ImageView) findViewById(R.id.iv_top_select);
        mIvTopSelect.setVisibility(View.GONE);
        mLlBottomBtn = (LinearLayout) findViewById(R.id.ll_bottom_btn);
        mBtnReply = (Button) findViewById(R.id.btn_reply);
        mBtnTranspond = (Button) findViewById(R.id.btn_transpond);
        mSlvEmail = (ScrollView) findViewById(R.id.slv_email);
        mTvTheme = (TextView) findViewById(R.id.tv_theme);
        mTvReceiver = (TextView) findViewById(R.id.tv_receiver);
        mTvSender = (TextView) findViewById(R.id.tv_sender);
        mTvSendTime = (TextView) findViewById(R.id.tv_send_time);
        mEdtCmContent = (EditText) findViewById(R.id.edt_cm_content);
        mRlAddFile = (RelativeLayout) findViewById(R.id.rl_add_file);
        mLlFilePack = (LinearLayout) findViewById(R.id.ll_file_pack);

        progressDialog = ProgressDialog.show(this, null, "邮件加载中…");
        mPresenter.reqsEmDetail(emailId);

        if ("SendBox".equals(type)) {
            mBtnReply.setVisibility(View.GONE);
        }

        mBtnReply.setOnClickListener(v -> {
            ARouter.getInstance().build("/home/EmailCreateActivity")
                    .withString("Type", "Reply")
                    .withSerializable("CEmail", cEmailDetailResp)
                    .navigation(this, 200);
        });
        mBtnTranspond.setOnClickListener(v -> {
            ARouter.getInstance().build("/home/EmailCreateActivity")
                    .withString("Type", "Transpond")
                    .withSerializable("CEmail", cEmailDetailResp)
                    .navigation(this, 200);
        });
    }

    @Override
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
        UiUtils.snackbarText(message);
    }


    @Override
    public void killMyself() {
        finish();
    }


    @Override
    public void setEmailDetail(CEmailDetailResp emailDetail) {

        cEmailDetailResp = emailDetail;

        mTvTheme.setText(emailDetail.getTheme());
        mTvReceiver.setText(emailDetail.getReceiver());
        mTvSender.setText(emailDetail.getSender());
        mTvSendTime.setText(emailDetail.getSendTime());
        mEdtCmContent.setText(emailDetail.getEmail());

        if (cEmailDetailResp.getFilePack() != null
                && cEmailDetailResp.getFilePack().size() > 0) {
            for (int i = 0; i < cEmailDetailResp.getFilePack().size(); i++) {
                setFilePack(i);
            }
        } else {
            mRlAddFile.setVisibility(View.GONE);
        }
    }

    @Override
    public void hideLoading() {
        if (progressDialog != null)
            progressDialog.dismiss();

    }


    private void setFilePack(int position) {

        CEmailDetailResp.FilePackBean filePackBean = cEmailDetailResp.getFilePack().get(position);
        View inflate = LayoutInflater.from(this).inflate(R.layout.home_add_email_filepack, null);

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


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 200){
            if (resultCode == 400) {
                killMyself();
            }
        }
    }

}
