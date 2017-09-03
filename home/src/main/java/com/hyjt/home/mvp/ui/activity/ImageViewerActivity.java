package com.hyjt.home.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
import com.hyjt.frame.base.BaseActivity;
import com.hyjt.frame.di.component.AppComponent;
import com.hyjt.frame.utils.UiUtils;
import com.hyjt.home.R;
import com.hyjt.home.di.component.DaggerImageViewerComponent;
import com.hyjt.home.di.module.ImageViewerModule;
import com.hyjt.home.mvp.contract.ImageViewerContract;
import com.hyjt.home.mvp.presenter.ImageViewerPresenter;

import static com.hyjt.frame.utils.Preconditions.checkNotNull;

@Route(path = "/home/ImageViewerActivity")
public class ImageViewerActivity extends BaseActivity<ImageViewerPresenter> implements ImageViewerContract.View {


    private String imageUrl;
    private android.widget.ImageView mIvBigImg;

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerImageViewerComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .imageViewerModule(new ImageViewerModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(Bundle savedInstanceState) {
        return R.layout.home_activity_image_viewer; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        mIvBigImg = (ImageView) findViewById(R.id.iv_big_img);

        Intent intent = getIntent();
        imageUrl = intent.getStringExtra("imageUrl");

        Glide.with(this).load(imageUrl).into(mIvBigImg);
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
}
