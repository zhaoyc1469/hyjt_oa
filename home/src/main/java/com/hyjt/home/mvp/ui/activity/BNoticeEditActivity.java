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
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.hyjt.frame.base.BaseActivity;
import com.hyjt.frame.di.component.AppComponent;
import com.hyjt.frame.utils.PermissionUtil;
import com.hyjt.frame.utils.UiUtils;
import com.hyjt.home.R;
import com.hyjt.home.di.component.DaggerBNoticeEditComponent;
import com.hyjt.home.di.module.BNoticeEditModule;
import com.hyjt.home.mvp.contract.BNoticeEditContract;
import com.hyjt.home.mvp.model.entity.Resp.BNoticeDetailsResp;
import com.hyjt.home.mvp.model.entity.Resp.ImgUploadResp;
import com.hyjt.home.mvp.presenter.BNoticeEditPresenter;
import com.hyjt.home.utils.ImgUtil;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;

import static com.hyjt.frame.utils.Preconditions.checkNotNull;

@Route(path = "/home/BNoticeEditActivity")
public class BNoticeEditActivity extends BaseActivity<BNoticeEditPresenter> implements BNoticeEditContract.View {


    private String bNoticeId;
    private android.widget.ImageView mIvTopBack;
    private android.widget.TextView mTvTitle;
    private android.widget.ImageView mIvTopSelect;
    private android.widget.LinearLayout mLlBottomBtn;
    private android.widget.Button mBtnEditBnotice;
    private android.widget.Button mBtnDelBnotice;
    private android.widget.EditText mEdtTitle;
    private android.widget.EditText mEdtContent;
    private android.widget.EditText mEdtCreateMan;
    private android.widget.EditText mEdtCreateDate;
    private android.widget.LinearLayout mLlFilePack;
    private String ForbidEdit;
    private ProgressDialog progressDialog;
    private RxPermissions mRxPermissions;
    private Button mBtnAddFile;
    private List<BNoticeDetailsResp.FilePackBean> mFilePackList = new ArrayList<>();
    private ArrayList<Uri> mFileURLList = new ArrayList<>();
//    private ArrayList<String> mFileNameList = new ArrayList<>();
    private ScrollView mSlvBnotice;
    private BNoticeDetailsResp bNoticeDetailsResp;
    private String FileUploader = "";
    private RelativeLayout mRlAddFile;


    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerBNoticeEditComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .bNoticeEditModule(new BNoticeEditModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(Bundle savedInstanceState) {
        return R.layout.home_activity_bnotice_edit; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        Intent intent = getIntent();
        bNoticeId = intent.getStringExtra("id");
        ForbidEdit = intent.getStringExtra("ForbidEdit");

        this.mRxPermissions = new RxPermissions(this);

        mIvTopBack = (ImageView) findViewById(R.id.iv_top_back);
        mIvTopBack.setOnClickListener(v -> finish());
        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mTvTitle.setOnClickListener(v -> finish());
        mTvTitle.setText("集团公告");
        mIvTopSelect = (ImageView) findViewById(R.id.iv_top_select);
        mIvTopSelect.setVisibility(View.GONE);
        mLlBottomBtn = (LinearLayout) findViewById(R.id.ll_bottom_btn);
        mBtnEditBnotice = (Button) findViewById(R.id.btn_edit_bnotice);
        mBtnDelBnotice = (Button) findViewById(R.id.btn_del_bnotice);

        mEdtTitle = (EditText) findViewById(R.id.edt_title);
        mEdtContent = (EditText) findViewById(R.id.edt_content);
        mEdtCreateMan = (EditText) findViewById(R.id.edt_create_man);
        mEdtCreateDate = (EditText) findViewById(R.id.edt_create_date);
        mLlFilePack = (LinearLayout) findViewById(R.id.ll_file_pack);
        mRlAddFile = (RelativeLayout) findViewById(R.id.rl_add_file);
        mBtnAddFile = (Button) findViewById(R.id.btn_del_file);
        mSlvBnotice = (ScrollView) findViewById(R.id.slv_bnotice);

        if ("1".equals(ForbidEdit)) {
            mBtnEditBnotice.setOnClickListener(v -> {
                AlertDialog.Builder builder = new AlertDialog.Builder(this)
                        .setTitle("编辑公告")
                        .setMessage("确定编辑公告信息?")
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
            mBtnDelBnotice.setOnClickListener(v -> {
                AlertDialog.Builder builder = new AlertDialog.Builder(this)
                        .setTitle("删除公告")
                        .setMessage("确定删除公告信息?")
                        .setPositiveButton("确定", (dialog, which) -> {
                            progressDialog = ProgressDialog.show(this, null, "正在删除…");
                            mPresenter.delBNotice(bNoticeDetailsResp);
                        }).setNegativeButton("返回", (dialog, which) -> {
                            dialog.dismiss();
                        });
                builder.show();
            });
            mBtnAddFile.setOnClickListener(v -> {
                Intent intentFile = new Intent(Intent.ACTION_GET_CONTENT);
                intentFile.setType("*/*");//设置类型，我这里是任意类型，任意后缀的可以这样写。
                intentFile.addCategory(Intent.CATEGORY_OPENABLE);
                startActivityForResult(intentFile, 100);
            });
        } else {


            mBtnAddFile.setVisibility(View.GONE);
            mLlBottomBtn.setVisibility(View.GONE);
            mEdtContent.setFocusable(false);
            mEdtTitle.setFocusable(false);
        }

        mPresenter.getBNoticeMsg(bNoticeId);

    }


    @Override
    public void showLoading() {
        progressDialog = ProgressDialog.show(this, null, "正在载入公告信息…");
    }

    @Override
    public void hideLoading() {
        if (progressDialog != null)
            progressDialog.dismiss();
    }

    @Override
    public void setBNotice(BNoticeDetailsResp bNoticeDetails) {
        bNoticeDetailsResp = bNoticeDetails;

        mEdtTitle.setText(bNoticeDetails.getTitle());
        mEdtContent.setText(bNoticeDetails.getMessage());
        mEdtCreateMan.setText(bNoticeDetails.getCreater());
        mEdtCreateDate.setText(bNoticeDetails.getCreatetime());

        if (bNoticeDetails.getFilePack() != null && bNoticeDetails.getFilePack().size() > 0) {
            mFilePackList = bNoticeDetails.getFilePack();
            for (int i = 0; i < bNoticeDetails.getFilePack().size(); i++) {
                setFilePack(i);
            }
        } else {
            if (!"1".equals(ForbidEdit)){
                mRlAddFile.setVisibility(View.GONE);
            }
        }

    }

    @Override
    public void fileUploadOk(List<ImgUploadResp> imgUploadList) {

        progressDialog = ProgressDialog.show(this, null, "正在上传公告信息...");

        bNoticeDetailsResp.setMessage(mEdtContent.getText().toString().trim());
        bNoticeDetailsResp.setTitle(mEdtTitle.getText().toString());

        for (int i = 0; i < imgUploadList.size(); i++) {
            ImgUploadResp imgUploadResp = imgUploadList.get(i);
            FileUploader = FileUploader + "^" + imgUploadResp.getId()
                    + "|" + imgUploadResp.getName();
        }

        for (int i = 0; i < mLlFilePack.getChildCount(); i++) {
            if (mLlFilePack.getChildCount() - mFileURLList.size() > i) {
                getFilePack(i);
            }
        }
        bNoticeDetailsResp.setFileUploader(FileUploader);
        mPresenter.editBNotice(bNoticeDetailsResp);

    }

    @Override
    public RxPermissions getRxPermissions() {
        return mRxPermissions;
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


    private void addFilePack(int position) {

        BNoticeDetailsResp.FilePackBean filePackBean = new BNoticeDetailsResp.FilePackBean();
        View inflate = LayoutInflater.from(getApplicationContext()).inflate(R.layout.home_add_bnotice_filepack, null);

        TextView certificateFile = (TextView) inflate.findViewById(R.id.tv_certificate_file);
        TextView certificate = (TextView) inflate.findViewById(R.id.tv_certificate);
        certificate.setVisibility(View.GONE);
        Button DelFile = (Button) inflate.findViewById(R.id.btn_del_file);
        if ("1".equals(ForbidEdit)){
            DelFile.setOnClickListener(v -> {
                mLlFilePack.removeView(inflate);
                mFilePackList.remove(position - 1);
                mFileURLList.remove(position - 1);
            });
        } else {
            DelFile.setVisibility(View.GONE);
        }



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
        mFilePackList.add(filePackBean);
        mSlvBnotice.fullScroll(ScrollView.FOCUS_DOWN);
    }


    private void setFilePack(int position) {

        BNoticeDetailsResp.FilePackBean filePackBean = mFilePackList.get(position);
        View inflate = LayoutInflater.from(this).inflate(R.layout.home_add_bnotice_filepack, null);

        TextView certificateFile = (TextView) inflate.findViewById(R.id.tv_certificate_file);
        EditText certificate = (EditText) inflate.findViewById(R.id.tv_certificate);
        certificate.setText(filePackBean.getFileName());
        certificateFile.setText(filePackBean.getFileId());
        certificateFile.setOnClickListener(v -> {
            // 弹出一个选择浏览器的框，选择浏览器再进入
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.home_base_url) + filePackBean.getFilePath()));
            startActivity(intent);
        });

        Button DelFile = (Button) inflate.findViewById(R.id.btn_del_file);
        if ("1".equals(ForbidEdit)){
            DelFile.setOnClickListener(v -> {
                mLlFilePack.removeView(inflate);
                mFilePackList.remove(position);
            });
        } else {
            DelFile.setVisibility(View.GONE);


        }

        mLlFilePack.addView(inflate);
    }

    private void getFilePack(int position) {

        LinearLayout childAt = (LinearLayout) mLlFilePack.getChildAt(position);
        TextView certificateFile = (TextView) childAt.findViewById(R.id.tv_certificate_file);
        EditText certificate = (EditText) childAt.findViewById(R.id.tv_certificate);

        FileUploader = FileUploader + "^" + certificateFile.getText().toString()
                + "|" + certificate.getText().toString();


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
