package com.hyjt.home.mvp.ui.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.Gravity;
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
import com.hyjt.frame.base.BaseActivity;
import com.hyjt.frame.di.component.AppComponent;
import com.hyjt.frame.utils.PermissionUtil;
import com.hyjt.frame.utils.UiUtils;
import com.hyjt.home.R;
import com.hyjt.home.di.component.DaggerEmailCreateComponent;
import com.hyjt.home.di.module.EmailCreateModule;
import com.hyjt.home.mvp.contract.EmailCreateContract;
import com.hyjt.home.mvp.model.entity.Reqs.CEmailSendReqs;
import com.hyjt.home.mvp.model.entity.Reqs.FileUploaderId;
import com.hyjt.home.mvp.model.entity.Reqs.StaffNameId;
import com.hyjt.home.mvp.model.entity.Reqs.StaffNameIdKey;
import com.hyjt.home.mvp.model.entity.Resp.CEmailDetailResp;
import com.hyjt.home.mvp.model.entity.Resp.ImgUploadResp;
import com.hyjt.home.mvp.presenter.EmailCreatePresenter;
import com.hyjt.home.mvp.ui.view.DepartmentPop;
import com.hyjt.home.utils.ImgUtil;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;

import static com.hyjt.frame.utils.Preconditions.checkNotNull;

@Route(path = "/home/EmailCreateActivity")
public class EmailCreateActivity extends BaseActivity<EmailCreatePresenter> implements EmailCreateContract.View {


    private android.widget.RelativeLayout mRlCreateEmail;
    private android.widget.ImageView mIvTopBack;
    private android.widget.TextView mTvTitle;
    private android.widget.ImageView mIvTopSelect;
    private android.widget.LinearLayout mLlBottomBtn;
    private android.widget.ScrollView mSlvEmail;
    private android.widget.EditText mEdtTheme;
    private android.widget.EditText mTvReceiver;
    private android.widget.EditText mEdtCmContent;
    private android.widget.RelativeLayout mRlAddFile;
    private android.widget.Button mBtnAddFile;
    private android.widget.LinearLayout mLlFilePack;
    private Button mBtnSend;
    private EmailCreateActivity mContext;
    private StaffNameIdKey receiver = new StaffNameIdKey();
    private CEmailDetailResp cEmailResp;
    private CEmailSendReqs cEmailReqs = new CEmailSendReqs();
    private List<StaffNameId> SysPersonIdList = new ArrayList<>();
    private List<FileUploaderId> FileList = new ArrayList<>();
    private ArrayList<Uri> mFileURLList = new ArrayList<>();
    private ProgressDialog progressDialog;

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerEmailCreateComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .emailCreateModule(new EmailCreateModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(Bundle savedInstanceState) {
        return R.layout.home_activity_email_create; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(Bundle savedInstanceState) {

        Intent intent = getIntent();
        String type = intent.getStringExtra("Type");

        mContext = this;

        mRlCreateEmail = (RelativeLayout) findViewById(R.id.rl_create_email);
        mIvTopBack = (ImageView) findViewById(R.id.iv_top_back);
        mIvTopBack.setOnClickListener(v -> finish());
        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mTvTitle.setText("发送邮件");
        mIvTopSelect = (ImageView) findViewById(R.id.iv_top_select);
        mIvTopSelect.setVisibility(View.GONE);
        mLlBottomBtn = (LinearLayout) findViewById(R.id.ll_bottom_btn);
        mBtnSend = (Button) findViewById(R.id.btn_send);
        mSlvEmail = (ScrollView) findViewById(R.id.slv_email);
        mEdtTheme = (EditText) findViewById(R.id.tv_theme);
        mTvReceiver = (EditText) findViewById(R.id.tv_receiver);
        mEdtCmContent = (EditText) findViewById(R.id.edt_cm_content);
        mRlAddFile = (RelativeLayout) findViewById(R.id.rl_add_file);
        mBtnAddFile = (Button) findViewById(R.id.btn_add_file);
        mLlFilePack = (LinearLayout) findViewById(R.id.ll_file_pack);

        mBtnAddFile.setOnClickListener(v -> {
            Intent intentFile = new Intent(Intent.ACTION_GET_CONTENT);
            intentFile.setType("*/*");//设置类型，我这里是任意类型，任意后缀的可以这样写。
            intentFile.addCategory(Intent.CATEGORY_OPENABLE);
            startActivityForResult(intentFile, 100);
        });


        if ("Create".equals(type)) {
            mTvReceiver.setOnClickListener(v -> {
                selStaff(mContext, mTvReceiver, receiver, true);
            });
            mTvReceiver.setFocusable(false);
        } else if ("Transpond".equals(type)) {
            mTvReceiver.setOnClickListener(v -> {
                selStaff(mContext, mTvReceiver, receiver, true);
            });
            mTvReceiver.setFocusable(false);
            cEmailResp = (CEmailDetailResp) intent.getSerializableExtra("CEmail");
//            mEdtTheme.setFocusable(false);
//            mEdtTheme.setEnabled(false);
            mEdtTheme.setText(cEmailResp.getTheme());
//            mEdtCmContent.setFocusable(false);
//            mEdtCmContent.setEnabled(false);
            mEdtCmContent.setText(cEmailResp.getEmail());

            if (cEmailResp.getFilePack() != null
                    && cEmailResp.getFilePack().size() > 0) {
                FileList.clear();
                for (int i = 0; i < cEmailResp.getFilePack().size(); i++) {
                    setFilePack(i);
                    FileUploaderId fileUploaderId = new FileUploaderId(
                            cEmailResp.getFilePack().get(i).getFileId()
                            , cEmailResp.getFilePack().get(i).getFileName());
                    FileList.add(fileUploaderId);
                }

            }
//            else {
//                mRlAddFile.setVisibility(View.GONE);
//            }


        } else if ("Reply".equals(type)) {
            cEmailResp = (CEmailDetailResp) intent.getSerializableExtra("CEmail");

            mTvReceiver.setFocusable(false);
            mTvReceiver.setEnabled(false);
            mTvReceiver.setText(cEmailResp.getSender());
            SysPersonIdList.add(new StaffNameId(cEmailResp.getSender(), cEmailResp.getSenderId()));


        }

        mBtnSend.setOnClickListener(v -> {

            if (mFileURLList.size() > 0) {
                progressDialog = ProgressDialog.show(this, null, "正在上传附件…");
                mPresenter.sendFile(mFileURLList);
            } else {
                fileUploadOk(new ArrayList<>());
            }
        });


    }

    private void setFilePack(int position) {

        CEmailDetailResp.FilePackBean filePackBean = cEmailResp.getFilePack().get(position);
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
    public void fileUploadOk(List<ImgUploadResp> imgUploadList) {

        if (imgUploadList.size() > 0) {
            for (int i = 0; i < imgUploadList.size(); i++) {
                FileList.add(new FileUploaderId(imgUploadList.get(i).getId()
                        , imgUploadList.get(i).getName()));
            }
        }

        cEmailReqs.setTheme(mEdtTheme.getText().toString());
        cEmailReqs.setEmail(mEdtCmContent.getText().toString());
        cEmailReqs.setSysPersonId(SysPersonIdList);
        cEmailReqs.setFileUploaderId(FileList);

        mPresenter.sendEmail(cEmailReqs);
    }

    @Override
    public void killMyself(int Code) {
        setResult(Code);
        finish();
    }

    private void addFilePack(int position) {

        View inflate = LayoutInflater.from(getApplicationContext()).inflate(R.layout.home_add_email_filepack, null);

        TextView certificate = (TextView) inflate.findViewById(R.id.tv_certificate);

        Button DelFile = (Button) inflate.findViewById(R.id.btn_del_file);

        DelFile.setOnClickListener(v -> {
            mLlFilePack.removeView(inflate);
            mFileURLList.remove(position - 1);
            FileList.remove(position);
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
        mSlvEmail.fullScroll(ScrollView.FOCUS_DOWN);
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
        departmentPop.showAtLocation(findViewById(R.id.rl_create_email),
                Gravity.CENTER | Gravity.CENTER_HORIZONTAL, 0, 0);
    }

    @Override
    public void getLinkmanOk(String[] nameAry, String[] nameSendAry, EditText selEdit, StaffNameIdKey StaffNameIdKey, boolean moreCheck) {
        final boolean bSelect[] = new boolean[nameAry.length];
        DialogInterface.OnMultiChoiceClickListener staffListener = (dialog1, which, isChecked) -> bSelect[which] = isChecked;
        AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(this);
        builder.setMultiChoiceItems(nameAry, bSelect, staffListener);
        AlertDialog alertDialog = builder.create();
        alertDialog.setTitle("人员选择");
        DialogInterface.OnClickListener lis41 = (dialog12, which) -> {
            String str = selEdit.getText().toString();
            String sendKey = StaffNameIdKey.getSendKey();
            for (int i = 0; i < bSelect.length; i++) {
                if (bSelect[i]) {
                    if ("".equals(str)) {
                        str = nameAry[i];
                        StaffNameIdKey.setSendKey(nameSendAry[i]);
                        SysPersonIdList.add(new StaffNameId(nameAry[i], nameSendAry[i]));
                    } else {
                        if (!str.contains(nameAry[i])) {
                            str = str + "," + nameAry[i];
                            if (!TextUtils.isEmpty(sendKey))
                                sendKey = sendKey.replace("&", "-");
                            StaffNameIdKey.setSendKey(sendKey + nameSendAry[i]);
                            SysPersonIdList.add(new StaffNameId(nameAry[i], nameSendAry[i]));
                        }
                    }
                }
            }
            selEdit.setText(str);
        };
        alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "确定", lis41);
        alertDialog.show();
    }

    @Override
    public void hideLoading() {
        if (progressDialog != null)
            progressDialog.dismiss();

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
