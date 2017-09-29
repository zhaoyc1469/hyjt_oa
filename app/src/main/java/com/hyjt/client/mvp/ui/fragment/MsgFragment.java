package com.hyjt.client.mvp.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.hyjt.client.mvp.ui.adapter.Bean.ModuleBean;
import com.hyjt.client.mvp.ui.adapter.ModuleAdapter;
import com.hyjt.frame.base.BaseFragment;
import com.hyjt.frame.di.component.AppComponent;
import com.hyjt.frame.utils.UiUtils;

import java.util.ArrayList;
import java.util.List;

import static com.hyjt.frame.utils.Preconditions.checkNotNull;


public class MsgFragment extends BaseFragment<MsgPresenter> implements MsgContract.View {


    private TextView mTvTitle;
    private ImageView mIvTopSelect;
    private RecyclerView mRecyGsgl;
    private RecyclerView recyYwgl;
    private RecyclerView recyYwsq;
    private RecyclerView recyRsgl;
    private RecyclerView recyXmgl;
    private RecyclerView recyCwgl;
    private RecyclerView recyZlgl;
    private RecyclerView mRecyYwgl;
    private RecyclerView mRecyYwsq;
    private RecyclerView mRecyRsgl;
    private RecyclerView mRecyXmgl;
    private RecyclerView mRecyCwgl;
    private RecyclerView mRecyZlgl;

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

        mRecyGsgl = (RecyclerView) inflate.findViewById(R.id.recy_gsgl);
        mRecyGsgl.setLayoutManager(new GridLayoutManager(getContext(),5));
        mRecyYwgl = (RecyclerView) inflate.findViewById(R.id.recy_ywgl);
        mRecyYwgl.setLayoutManager(new GridLayoutManager(getContext(),5));
        mRecyYwsq = (RecyclerView) inflate.findViewById(R.id.recy_ywsq);
        mRecyYwsq.setLayoutManager(new GridLayoutManager(getContext(),5));
        mRecyRsgl = (RecyclerView) inflate.findViewById(R.id.recy_rsgl);
        mRecyRsgl.setLayoutManager(new GridLayoutManager(getContext(),5));
        mRecyXmgl = (RecyclerView) inflate.findViewById(R.id.recy_xmgl);
        mRecyXmgl.setLayoutManager(new GridLayoutManager(getContext(),5));
        mRecyCwgl = (RecyclerView) inflate.findViewById(R.id.recy_cwgl);
        mRecyCwgl.setLayoutManager(new GridLayoutManager(getContext(),5));
        mRecyZlgl = (RecyclerView) inflate.findViewById(R.id.recy_zlgl);
        mRecyZlgl.setLayoutManager(new GridLayoutManager(getContext(),5));

        List<ModuleBean> moduleBeanList = new ArrayList<>();
        moduleBeanList.add(new ModuleBean("h_gszl", "公司干了"));
        moduleBeanList.add(new ModuleBean("h_gszl", "公司干了"));
        moduleBeanList.add(new ModuleBean("h_gszl", "公司干了"));
        moduleBeanList.add(new ModuleBean("h_gszl", "公司干了"));
        moduleBeanList.add(new ModuleBean("h_gszl", "公司干了"));
        moduleBeanList.add(new ModuleBean("h_gszl", "公司干了"));
        moduleBeanList.add(new ModuleBean("h_gszl", "公司干了"));
        moduleBeanList.add(new ModuleBean("h_gszl", "公司干了"));
        mRecyGsgl.setAdapter(new ModuleAdapter(moduleBeanList, getActivity()));
        mRecyYwgl.setAdapter(new ModuleAdapter(moduleBeanList, getActivity()));
        mRecyYwsq.setAdapter(new ModuleAdapter(moduleBeanList, getActivity()));
        mRecyRsgl.setAdapter(new ModuleAdapter(moduleBeanList, getActivity()));
        mRecyXmgl.setAdapter(new ModuleAdapter(moduleBeanList, getActivity()));
        mRecyCwgl.setAdapter(new ModuleAdapter(moduleBeanList, getActivity()));
        mRecyZlgl.setAdapter(new ModuleAdapter(moduleBeanList, getActivity()));

        mRecyGsgl.setNestedScrollingEnabled(false);
        mRecyYwgl.setNestedScrollingEnabled(false);
        mRecyYwsq.setNestedScrollingEnabled(false);
        mRecyRsgl.setNestedScrollingEnabled(false);
        mRecyXmgl.setNestedScrollingEnabled(false);
        mRecyCwgl.setNestedScrollingEnabled(false);
        mRecyZlgl.setNestedScrollingEnabled(false);


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

    public int getImageId(String imageName) {
        Context ctx = getActivity().getBaseContext();
        int resId = getResources().getIdentifier(imageName, "mipmap", ctx.getPackageName());
        return resId;
    }

}
