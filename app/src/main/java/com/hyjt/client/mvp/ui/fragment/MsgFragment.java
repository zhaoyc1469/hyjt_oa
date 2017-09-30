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
import com.hyjt.client.mvp.model.entity.Bean.ModuleBean;
import com.hyjt.client.mvp.ui.adapter.ModuleAdapter;
import com.hyjt.db.DbHelper;
import com.hyjt.db.bean.ModuleBeanDb;
import com.hyjt.db.gen.DaoSession;
import com.hyjt.db.gen.ModuleBeanDbDao;
import com.hyjt.frame.base.BaseFragment;
import com.hyjt.frame.di.component.AppComponent;
import com.hyjt.frame.event.OutLoginEvent;
import com.hyjt.frame.event.RefModuleEvent;
import com.hyjt.frame.utils.UiUtils;

import org.simple.eventbus.Subscriber;
import org.simple.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import static com.hyjt.frame.utils.Preconditions.checkNotNull;


public class MsgFragment extends BaseFragment<MsgPresenter> implements MsgContract.View {


    private TextView mTvTitle;
    private ImageView mIvTopSelect;
    private RecyclerView mRecyGsgl;
    private RecyclerView mRecyYwgl;
    private RecyclerView mRecyYwsq;
    private RecyclerView mRecyRsgl;
    private RecyclerView mRecyXmgl;
    private RecyclerView mRecyCwgl;
    private RecyclerView mRecyZlgl;
    private DaoSession daoSession;
    private ModuleBeanDbDao moduleBeanDbDao;
    private List<ModuleBean> moduleGsgl;
    private List<ModuleBean> moduleYwgl;
    private List<ModuleBean> moduleYwsq;
    private List<ModuleBean> moduleRsgl;
    private List<ModuleBean> moduleXmgl;
    private List<ModuleBean> moduleCwgl;
    private List<ModuleBean> moduleZlgl;
    private ModuleAdapter GsglAdapter;
    private ModuleAdapter YwglAdapter;
    private ModuleAdapter YwsqAdapter;
    private ModuleAdapter RsglAdapter;
    private ModuleAdapter XmglAdapter;
    private ModuleAdapter CwglAdapter;
    private ModuleAdapter ZlglAdapter;

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
        mRecyGsgl.setLayoutManager(new GridLayoutManager(getContext(), 5));
        mRecyYwgl = (RecyclerView) inflate.findViewById(R.id.recy_ywgl);
        mRecyYwgl.setLayoutManager(new GridLayoutManager(getContext(), 5));
        mRecyYwsq = (RecyclerView) inflate.findViewById(R.id.recy_ywsq);
        mRecyYwsq.setLayoutManager(new GridLayoutManager(getContext(), 5));
        mRecyRsgl = (RecyclerView) inflate.findViewById(R.id.recy_rsgl);
        mRecyRsgl.setLayoutManager(new GridLayoutManager(getContext(), 5));
        mRecyXmgl = (RecyclerView) inflate.findViewById(R.id.recy_xmgl);
        mRecyXmgl.setLayoutManager(new GridLayoutManager(getContext(), 5));
        mRecyCwgl = (RecyclerView) inflate.findViewById(R.id.recy_cwgl);
        mRecyCwgl.setLayoutManager(new GridLayoutManager(getContext(), 5));
        mRecyZlgl = (RecyclerView) inflate.findViewById(R.id.recy_zlgl);
        mRecyZlgl.setLayoutManager(new GridLayoutManager(getContext(), 5));

        daoSession = DbHelper.getInstance().getDaoSession();
        moduleBeanDbDao = daoSession.getModuleBeanDbDao();

        moduleGsgl = new ArrayList<>();
        moduleYwgl = new ArrayList<>();
        moduleYwsq = new ArrayList<>();
        moduleRsgl = new ArrayList<>();
        moduleXmgl = new ArrayList<>();
        moduleCwgl = new ArrayList<>();
        moduleZlgl = new ArrayList<>();

        GsglAdapter = new ModuleAdapter(moduleGsgl, getActivity());
        YwglAdapter = new ModuleAdapter(moduleYwgl, getActivity());
        YwsqAdapter = new ModuleAdapter(moduleYwsq, getActivity());
        RsglAdapter = new ModuleAdapter(moduleRsgl, getActivity());
        XmglAdapter = new ModuleAdapter(moduleXmgl, getActivity());
        CwglAdapter = new ModuleAdapter(moduleCwgl, getActivity());
        ZlglAdapter = new ModuleAdapter(moduleZlgl, getActivity());

        mRecyGsgl.setAdapter(GsglAdapter);
        mRecyYwgl.setAdapter(YwglAdapter);
        mRecyYwsq.setAdapter(YwsqAdapter);
        mRecyRsgl.setAdapter(RsglAdapter);
        mRecyXmgl.setAdapter(XmglAdapter);
        mRecyCwgl.setAdapter(CwglAdapter);
        mRecyZlgl.setAdapter(ZlglAdapter);

        mRecyGsgl.setNestedScrollingEnabled(false);
        mRecyYwgl.setNestedScrollingEnabled(false);
        mRecyYwsq.setNestedScrollingEnabled(false);
        mRecyRsgl.setNestedScrollingEnabled(false);
        mRecyXmgl.setNestedScrollingEnabled(false);
        mRecyCwgl.setNestedScrollingEnabled(false);
        mRecyZlgl.setNestedScrollingEnabled(false);

        loadModuleList();

        return inflate;
    }

    private void loadModuleList() {
        List<ModuleBeanDb> moduleBeanDbs = moduleBeanDbDao.loadAll();
        moduleGsgl.clear();
        moduleYwgl.clear();
        moduleYwsq.clear();
        moduleRsgl.clear();
        moduleXmgl.clear();
        moduleCwgl.clear();
        moduleZlgl.clear();
        for (ModuleBeanDb moduleBeanDb : moduleBeanDbs) {
            if (moduleBeanDb.getIsShow()) {
                switch (moduleBeanDb.getType()) {
                    case 1:
                        moduleGsgl.add(new ModuleBean(moduleBeanDb.getImg(), moduleBeanDb.getName()));
                        break;
                    case 2:
                        moduleYwgl.add(new ModuleBean(moduleBeanDb.getImg(), moduleBeanDb.getName()));
                        break;
                    case 3:
                        moduleYwsq.add(new ModuleBean(moduleBeanDb.getImg(), moduleBeanDb.getName()));
                        break;
                    case 4:
                        moduleRsgl.add(new ModuleBean(moduleBeanDb.getImg(), moduleBeanDb.getName()));
                        break;
                    case 5:
                        moduleXmgl.add(new ModuleBean(moduleBeanDb.getImg(), moduleBeanDb.getName()));
                        break;
                    case 6:
                        moduleCwgl.add(new ModuleBean(moduleBeanDb.getImg(), moduleBeanDb.getName()));
                        break;
                    case 7:
                        moduleZlgl.add(new ModuleBean(moduleBeanDb.getImg(), moduleBeanDb.getName()));
                        break;
                }
            }
        }
        GsglAdapter.notifyDataSetChanged();
        YwglAdapter.notifyDataSetChanged();
        YwsqAdapter.notifyDataSetChanged();
        RsglAdapter.notifyDataSetChanged();
        XmglAdapter.notifyDataSetChanged();
        CwglAdapter.notifyDataSetChanged();
        ZlglAdapter.notifyDataSetChanged();
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

    @Subscriber(tag = "Ref_Module", mode = ThreadMode.MAIN)
    public void refModule(RefModuleEvent RefModuleEvent){
        loadModuleList();
    }
}
