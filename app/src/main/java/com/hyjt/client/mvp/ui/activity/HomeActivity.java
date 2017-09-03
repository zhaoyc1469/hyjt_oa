package com.hyjt.client.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.hyjt.app.R;
import com.hyjt.client.di.component.DaggerHomeComponent;
import com.hyjt.client.di.module.HomeModule;
import com.hyjt.client.mvp.contract.HomeContract;
import com.hyjt.client.mvp.presenter.HomePresenter;
import com.hyjt.client.mvp.ui.fragment.LinkmanFragment;
import com.hyjt.client.mvp.ui.fragment.MineFragment;
import com.hyjt.client.mvp.ui.fragment.MsgFragment;
import com.hyjt.client.mvp.ui.fragment.WorkFragment;
import com.hyjt.client.mvp.ui.view.BottomBar;
import com.hyjt.client.mvp.ui.view.BottomBarTab;
import com.hyjt.frame.base.BaseActivity;
import com.hyjt.frame.di.component.AppComponent;
import com.hyjt.frame.utils.FragmentUtils;
import com.hyjt.frame.utils.UiUtils;

import java.util.ArrayList;
import java.util.List;

import static com.hyjt.frame.utils.Preconditions.checkNotNull;


@Route(path = "/app/HomeActivity")
public class HomeActivity extends BaseActivity<HomePresenter> implements HomeContract.View {


    private BottomBar mBottomBar;
    private List<Fragment> mFragments;

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerHomeComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .homeModule(new HomeModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(Bundle savedInstanceState) {
        return R.layout.activity_home; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        //组件模式在此初始化
//        ARouter.init(getApplication());
//        ARouter.openDebug();



        mBottomBar = (BottomBar) findViewById(R.id.bottomBar);
        mBottomBar.addItem(new BottomBarTab(this, R.mipmap.iv_work, "工作"))
                .addItem(new BottomBarTab(this, R.mipmap.iv_msg, "消息"))
                .addItem(new BottomBarTab(this, R.mipmap.iv_linkman, "联系人"))
                .addItem(new BottomBarTab(this, R.mipmap.iv_mine, "我的"));

        mBottomBar.setOnTabSelectedListener(new BottomBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position, int prePosition) {
                FragmentUtils.hideAllShowFragment(mFragments.get(position));
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {

            }
        });

        //处理Activity的重建（recreate），恢复Fragment
        WorkFragment workFragment;
        MsgFragment messageFragment;
        LinkmanFragment linkmanFragment;
        MineFragment mineFragment;
        if (savedInstanceState == null) {
            workFragment = WorkFragment.newInstance();
            messageFragment = MsgFragment.newInstance();
            linkmanFragment = LinkmanFragment.newInstance();
            mineFragment = MineFragment.newInstance();
        } else {
            FragmentManager fm = getSupportFragmentManager();
            workFragment = (WorkFragment) FragmentUtils.findFragment(fm, WorkFragment.class);
            messageFragment = (MsgFragment) FragmentUtils.findFragment(fm, MsgFragment.class);
            linkmanFragment = (LinkmanFragment) FragmentUtils.findFragment(fm, MineFragment.class);
            mineFragment = (MineFragment) FragmentUtils.findFragment(fm, MineFragment.class);
        }
        if (mFragments == null) {
            mFragments = new ArrayList<>();
            mFragments.add(workFragment);
            mFragments.add(messageFragment);
            mFragments.add(linkmanFragment);
            mFragments.add(mineFragment);
        }
        FragmentUtils.addFragments(getSupportFragmentManager(), mFragments, R.id.main_frame, 0);

        setNewMsg(5);
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
    public void setNewMsg(int nub) {
        mBottomBar.getItem(1).setUnreadCount(9);
//        mBottomBar.getI().setUnreadCount(9);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        FragmentManager fragmentManager=getSupportFragmentManager();
        for(int indext=0;indext<fragmentManager.getFragments().size();indext++)
        {
            Fragment fragment=fragmentManager.getFragments().get(indext); //找到第一层Fragment
            if(fragment==null)
                Log.w(TAG, "Activity result no fragment exists for index: 0x"
                        + Integer.toHexString(requestCode));
            else
                handleResult(fragment,requestCode,resultCode,data);
        }
    }
    /**
     * 递归调用，对所有的子Fragment生效
     * @param fragment
     * @param requestCode
     * @param resultCode
     * @param data
     */
    private void handleResult(Fragment fragment,int requestCode,int resultCode,Intent data)
    {
        fragment.onActivityResult(requestCode, resultCode, data);//调用每个Fragment的onActivityResult
        Log.e(TAG, "MyBaseFragmentActivity");
        List<Fragment> childFragment = fragment.getChildFragmentManager().getFragments(); //找到第二层Fragment
        if(childFragment!=null)
            for(Fragment f:childFragment)
                if(f!=null)
                {
                    handleResult(f, requestCode, resultCode, data);
                }
        if(childFragment==null)
            Log.e(TAG, "MyBaseFragmentActivity1111");
    }
}
