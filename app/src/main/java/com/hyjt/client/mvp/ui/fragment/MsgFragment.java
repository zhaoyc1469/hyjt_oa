package com.hyjt.client.mvp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hyjt.app.R;
import com.hyjt.client.di.component.DaggerMsgComponent;
import com.hyjt.client.di.module.MsgModule;
import com.hyjt.client.mvp.contract.MsgContract;
import com.hyjt.client.mvp.presenter.MsgPresenter;
import com.hyjt.frame.base.BaseFragment;
import com.hyjt.frame.di.component.AppComponent;
import com.hyjt.frame.utils.UiUtils;

import static com.hyjt.frame.utils.Preconditions.checkNotNull;


public class MsgFragment extends BaseFragment<MsgPresenter> implements MsgContract.View {


    private TextView mTvTitle;
    private ImageView mIvTopSelect;

    public static MsgFragment newInstance() {
        MsgFragment fragment = new MsgFragment();
        return fragment;
    }

    @Override
    public void setupFragmentComponent(AppComponent appComponent) {
        DaggerMsgComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .msgModule(new MsgModule(this))
                .build()
                .inject(this);
    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_msg, container, false);
        mTvTitle = (TextView) inflate.findViewById(R.id.tv_title);
        mIvTopSelect = (ImageView) inflate.findViewById(R.id.iv_top_select);
        return inflate;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        mTvTitle.setText("消息");
    }

    @Override
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
        UiUtils.snackbarText(message);
    }

    @Override
    public void killMyself() {
    }
}
