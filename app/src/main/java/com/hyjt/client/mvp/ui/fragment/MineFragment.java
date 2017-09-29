package com.hyjt.client.mvp.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hyjt.app.R;
import com.hyjt.client.di.component.DaggerMineComponent;
import com.hyjt.client.di.module.MineModule;
import com.hyjt.client.mvp.contract.MineContract;
import com.hyjt.client.mvp.presenter.MinePresenter;
import com.hyjt.frame.base.BaseFragment;
import com.hyjt.frame.di.component.AppComponent;
import com.hyjt.frame.utils.UiUtils;

import static com.hyjt.frame.utils.Preconditions.checkNotNull;


public class MineFragment extends BaseFragment<MinePresenter> implements MineContract.View {


    private TextView mTvTitle;
    private ImageView mIvTopSelect;
    private android.widget.RelativeLayout mRlGsgl;
    private TextView mTvGsglExpand;
    private android.widget.LinearLayout mLlGsgl;
    private android.widget.LinearLayout mLlGsjg;
    private android.widget.RelativeLayout mRlGsjg;
    private TextView mTvGsjgNum;
    private TextView mTvGsjg;
    private android.widget.LinearLayout mLlGszl;
    private android.widget.RelativeLayout mRlGszl;
    private ImageView mIvGszl;
    private TextView mTvGszlNum;
    private TextView mTvGszl;
    private android.widget.LinearLayout mLlBmzl;
    private android.widget.RelativeLayout mRlBmzl;
    private ImageView mIvBmzl;
    private TextView mTvBmzlNum;
    private TextView mTvBmzl;
    private android.widget.LinearLayout mLlZfwz;
    private android.widget.RelativeLayout mRlZfwz;
    private ImageView mIvZfwz;
    private TextView mTvZfwzNum;
    private TextView mTvZfwz;
    private android.widget.LinearLayout mLlGswz;
    private android.widget.RelativeLayout mRlGswz;
    private ImageView mIvGswz;
    private TextView mTvGswzNum;
    private TextView mTvGswz;
    private android.widget.LinearLayout mLlGsglChild;
    private android.widget.LinearLayout mLlHylt;
    private android.widget.RelativeLayout mRlHylt;
    private ImageView mIvHylt;
    private TextView mTvHyltNum;
    private TextView mTvHylt;
    private android.widget.LinearLayout mLlYjhb;
    private android.widget.RelativeLayout mRlYjhb;
    private ImageView mIvYjhb;
    private android.widget.RelativeLayout mRlYjhbNum;
    private TextView mTvYjhbNum;
    private TextView mTvYjhb;
    private android.widget.LinearLayout mLlXjxc;
    private android.widget.RelativeLayout mRlXjxc;
    private ImageView mIvXjxc;
    private android.widget.RelativeLayout mRlXjxcNum;
    private TextView mTvXjxcNum;
    private TextView mTvXjxc;
    private android.widget.LinearLayout mLlGhsq;
    private android.widget.RelativeLayout mRlGhsq;
    private ImageView mIvGhsq;
    private android.widget.RelativeLayout mRlGhsqNum;
    private TextView mTvGhsqNum;
    private TextView mTvGhsq;
    private android.widget.LinearLayout mLlTxl;
    private android.widget.RelativeLayout mRlTxl;
    private ImageView mIvTxl;
    private TextView mTvTxlNum;
    private TextView mTvTxl;

    public static MineFragment newInstance() {
        MineFragment fragment = new MineFragment();
        return fragment;
    }

    @Override
    public void setupFragmentComponent(AppComponent appComponent) {
        DaggerMineComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .mineModule(new MineModule(this))
                .build()
                .inject(this);
    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_mine, container, false);
        mTvTitle = (TextView) inflate.findViewById(R.id.tv_title);
        mIvTopSelect = (ImageView) inflate.findViewById(R.id.iv_top_select);

        //
        mRlGsgl = (RelativeLayout) inflate.findViewById(R.id.rl_gsgl);
        mTvGsglExpand = (TextView) inflate.findViewById(R.id.tv_gsgl_expand);
        mLlGsgl = (LinearLayout) inflate.findViewById(R.id.ll_gsgl);
        mLlGsjg = (LinearLayout) inflate.findViewById(R.id.ll_gsjg);
        mRlGsjg = (RelativeLayout) inflate.findViewById(R.id.rl_gsjg);
        mTvGsjgNum = (TextView) inflate.findViewById(R.id.tv_gsjg_num);
        mTvGsjg = (TextView) inflate.findViewById(R.id.tv_gsjg);
        mLlGszl = (LinearLayout) inflate.findViewById(R.id.ll_gszl);
        mRlGszl = (RelativeLayout) inflate.findViewById(R.id.rl_gszl);
        mIvGszl = (ImageView) inflate.findViewById(R.id.iv_gszl);
        mTvGszlNum = (TextView) inflate.findViewById(R.id.tv_gszl_num);
        mTvGszl = (TextView) inflate.findViewById(R.id.tv_gszl);
        mLlBmzl = (LinearLayout) inflate.findViewById(R.id.ll_bmzl);
        mRlBmzl = (RelativeLayout) inflate.findViewById(R.id.rl_bmzl);
        mIvBmzl = (ImageView) inflate.findViewById(R.id.iv_bmzl);
        mTvBmzlNum = (TextView) inflate.findViewById(R.id.tv_bmzl_num);
        mTvBmzl = (TextView) inflate.findViewById(R.id.tv_bmzl);
        mLlZfwz = (LinearLayout) inflate.findViewById(R.id.ll_zfwz);
        mRlZfwz = (RelativeLayout) inflate.findViewById(R.id.rl_zfwz);
        mIvZfwz = (ImageView) inflate.findViewById(R.id.iv_zfwz);
        mTvZfwzNum = (TextView) inflate.findViewById(R.id.tv_zfwz_num);
        mTvZfwz = (TextView) inflate.findViewById(R.id.tv_zfwz);
        mLlGswz = (LinearLayout) inflate.findViewById(R.id.ll_gswz);
        mRlGswz = (RelativeLayout) inflate.findViewById(R.id.rl_gswz);
        mIvGswz = (ImageView) inflate.findViewById(R.id.iv_gswz);
        mTvGswzNum = (TextView) inflate.findViewById(R.id.tv_gswz_num);
        mTvGswz = (TextView) inflate.findViewById(R.id.tv_gswz);
        mLlGsglChild = (LinearLayout) inflate.findViewById(R.id.ll_gsgl_child);
        mLlHylt = (LinearLayout) inflate.findViewById(R.id.ll_hylt);
        mRlHylt = (RelativeLayout) inflate.findViewById(R.id.rl_hylt);
        mIvHylt = (ImageView) inflate.findViewById(R.id.iv_hylt);
        mTvHyltNum = (TextView) inflate.findViewById(R.id.tv_hylt_num);
        mTvHylt = (TextView) inflate.findViewById(R.id.tv_hylt);
        mLlYjhb = (LinearLayout) inflate.findViewById(R.id.ll_yjhb);
        mRlYjhb = (RelativeLayout) inflate.findViewById(R.id.rl_yjhb);
        mIvYjhb = (ImageView) inflate.findViewById(R.id.iv_yjhb);
        mRlYjhbNum = (RelativeLayout) inflate.findViewById(R.id.rl_yjhb_num);
        mTvYjhbNum = (TextView) inflate.findViewById(R.id.tv_yjhb_num);
        mTvYjhb = (TextView) inflate.findViewById(R.id.tv_yjhb);
        mLlXjxc = (LinearLayout) inflate.findViewById(R.id.ll_xjxc);
        mRlXjxc = (RelativeLayout) inflate.findViewById(R.id.rl_xjxc);
        mIvXjxc = (ImageView) inflate.findViewById(R.id.iv_xjxc);
        mRlXjxcNum = (RelativeLayout) inflate.findViewById(R.id.rl_xjxc_num);
        mTvXjxcNum = (TextView) inflate.findViewById(R.id.tv_xjxc_num);
        mTvXjxc = (TextView) inflate.findViewById(R.id.tv_xjxc);
        mLlGhsq = (LinearLayout) inflate.findViewById(R.id.ll_ghsq);
        mRlGhsq = (RelativeLayout) inflate.findViewById(R.id.rl_ghsq);
        mIvGhsq = (ImageView) inflate.findViewById(R.id.iv_ghsq);
        mRlGhsqNum = (RelativeLayout) inflate.findViewById(R.id.rl_ghsq_num);
        mTvGhsqNum = (TextView) inflate.findViewById(R.id.tv_ghsq_num);
        mTvGhsq = (TextView) inflate.findViewById(R.id.tv_ghsq);
        mLlTxl = (LinearLayout) inflate.findViewById(R.id.ll_txl);
        mRlTxl = (RelativeLayout) inflate.findViewById(R.id.rl_txl);
        mIvTxl = (ImageView) inflate.findViewById(R.id.iv_txl);
        mTvTxlNum = (TextView) inflate.findViewById(R.id.tv_txl_num);
        mTvTxl = (TextView) inflate.findViewById(R.id.tv_txl);
        mLlGsgl.setOnClickListener(v -> {
            mLlGsgl.addView(mLlGsgl.getChildAt(0));
            mLlGsgl.removeView(mLlGsgl.getChildAt(0));
//            View blankView = LayoutInflater.from(getActivity()).inflate(R.layout.item_blank_modules, null);
//            mLlGsgl.addView(blankView);
        });
        return inflate;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        mTvTitle.setText("我的");
    }


    @Override
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
        UiUtils.snackbarText(message);
    }

    @Override
    public void killMyself() {

    }

    public int getImageId(String imageName) {
        Context ctx = getActivity().getBaseContext();
        int resId = getResources().getIdentifier(imageName, "mipmap", ctx.getPackageName());
        return resId;
    }
}
