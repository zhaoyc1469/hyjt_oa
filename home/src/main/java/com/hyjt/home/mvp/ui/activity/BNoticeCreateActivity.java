package com.hyjt.home.mvp.ui.activity;

import android.app.Activity;
import android.app.AlertDialog;
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
import android.widget.ScrollView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.hyjt.frame.base.BaseActivity;
import com.hyjt.frame.di.component.AppComponent;
import com.hyjt.frame.utils.PermissionUtil;
import com.hyjt.frame.utils.UiUtils;
import com.hyjt.home.R;
import com.hyjt.home.di.component.DaggerBNoticeCreateComponent;
import com.hyjt.home.di.module.BNoticeCreateModule;
import com.hyjt.home.mvp.contract.BNoticeCreateContract;
import com.hyjt.home.mvp.model.entity.Resp.BNoticeDetailsResp;
import com.hyjt.home.mvp.model.entity.Resp.ImgUploadResp;
import com.hyjt.home.mvp.presenter.BNoticeCreatePresenter;
import com.hyjt.home.utils.ImgUtil;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;

import static com.hyjt.frame.utils.Preconditions.checkNotNull;

@Route(path = "/home/BNoticeCreateActivity")
public class BNoticeCreateActivity extends BaseActivity<BNoticeCreatePresenter> implements BNoticeCreateContract.View {


    private android.widget.ImageView mIvTopBack;
    private android.widget.TextView mTvTitle;
    private android.widget.ImageView mIvTopSelect;
    private android.widget.LinearLayout mLlBottomBtn;
    private android.widget.Button mBtnCreateBnotice;
    private android.widget.ScrollView mSlvBnotice;
    private android.widget.EditText mEdtTitle;
    private android.widget.EditText mEdtContent;
    private android.widget.Button mBtnAddFile;
    private android.widget.LinearLayout mLlFilePack;
    private ArrayList<Uri> mFileURLList = new ArrayList<>();
    private ProgressDialog progressDialog;

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerBNoticeCreateComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .bNoticeCreateModule(new BNoticeCreateModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(Bundle savedInstanceState) {
        return R.layout.home_activity_bnotice_create; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        mIvTopBack = (ImageView) findViewById(R.id.iv_top_back);
        mIvTopBack.setOnClickListener(v -> finish());
        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mTvTitle.setText("创建公告");
        mTvTitle.setOnClickListener(v -> finish());
        mIvTopSelect = (ImageView) findViewById(R.id.iv_top_select);
        mIvTopSelect.setVisibility(View.GONE);
        mLlBottomBtn = (LinearLayout) findViewById(R.id.ll_bottom_btn);
        mBtnCreateBnotice = (Button) findViewById(R.id.btn_create_bnotice);
        mBtnCreateBnotice.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this)
                    .setTitle("创建公告")
                    .setMessage("确定创建公告信息?")
                    .setPositiveButton("确定", (dialog, which) -> {
                        if (mFileURLList.size() > 0) {
                            progressDialog = ProgressDialog.show(this, null, "正在上传附件…");
                            mPresenter.sendFile(mFileURLList);
                        } else {
                            fileUploadOk(new ArrayList<>());
                        }
                    }).setNegativeButton("返回", (dialog, which) -> {
                        dialog.dismiss();
                    });
            builder.show();
        });
        mSlvBnotice = (ScrollView) findViewById(R.id.slv_bnotice);
        mEdtTitle = (EditText) findViewById(R.id.edt_title);
        mEdtContent = (EditText) findViewById(R.id.edt_content);
        mBtnAddFile = (Button) findViewById(R.id.btn_add_file);
        mBtnAddFile.setOnClickListener(v -> {
            Intent intentFile = new Intent(Intent.ACTION_GET_CONTENT);
            intentFile.setType("*/*");//设置类型，我这里是任意类型，任意后缀的可以这样写。
            intentFile.addCategory(Intent.CATEGORY_OPENABLE);
            startActivityForResult(intentFile, 100);
        });
        mLlFilePack = (LinearLayout) findViewById(R.id.ll_file_pack);
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
    public void killMyself(int Code) {
        setResult(Code);
        finish();
    }


    private void addFilePack(int position) {

        View inflate = LayoutInflater.from(getApplicationContext()).inflate(R.layout.home_add_bnotice_filepack, null);

        TextView certificateFile = (TextView) inflate.findViewById(R.id.tv_certificate_file);
        TextView certificate = (TextView) inflate.findViewById(R.id.tv_certificate);
        certificate.setVisibility(View.GONE);
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
                certificateFile.setText("" + file.getName());
            }

            @Override
            public void onRequestPermissionFailure() {
                shortToast("您需要打开存储权限");
            }
        }, getRxPermissions(), build);

        mLlFilePack.addView(inflate);
        mSlvBnotice.fullScroll(ScrollView.FOCUS_DOWN);
    }

    @Override
    public void hideLoading() {
        if (progressDialog != null)
            progressDialog.dismiss();
    }

    @Override
    public void fileUploadOk(List<ImgUploadResp> imgUploadList) {
        String FileUploader = "";
        if (imgUploadList.size() > 0) {
            for (ImgUploadResp imgResp : imgUploadList) {
                FileUploader = FileUploader + "^" + imgResp.getId() + "|" + imgResp.getName();
            }
        }

        BNoticeDetailsResp bNoticeDetailsResp = new BNoticeDetailsResp();
        bNoticeDetailsResp.setTitle(mEdtTitle.getText().toString());
        bNoticeDetailsResp.setMessage(mEdtContent.getText().toString());
        bNoticeDetailsResp.setFileUploader(FileUploader);
        mPresenter.sendBNotice(bNoticeDetailsResp);
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
                mFileURLList.add(uri);

                addFilePack(mFileURLList.size());

            }
        }
    }

}


