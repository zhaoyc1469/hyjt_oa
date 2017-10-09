package com.hyjt.client.mvp.ui.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.hyjt.app.R;
import com.hyjt.client.di.component.DaggerWorkComponent;
import com.hyjt.client.di.module.WorkModule;
import com.hyjt.client.mvp.contract.WorkContract;
import com.hyjt.client.mvp.model.entity.Bean.ModuleBean;
import com.hyjt.client.mvp.model.entity.Bean.ModuleListBean;
import com.hyjt.client.mvp.model.entity.WorkMission;
import com.hyjt.client.mvp.presenter.WorkPresenter;
import com.hyjt.client.mvp.ui.adapter.ModuleAdapter;
import com.hyjt.client.mvp.ui.view.EmailPop;
import com.hyjt.client.mvp.ui.view.ReAndSePop;
import com.hyjt.client.mvp.ui.view.ReportTopPop;
import com.hyjt.client.mvp.ui.view.SLConsultPop;
import com.hyjt.client.mvp.ui.view.SkipReportPop;
import com.hyjt.db.DbHelper;
import com.hyjt.db.bean.ModuleBeanDb;
import com.hyjt.db.bean.StaffBeanDb;
import com.hyjt.db.gen.DaoSession;
import com.hyjt.db.gen.ModuleBeanDbDao;
import com.hyjt.db.gen.StaffBeanDbDao;
import com.hyjt.frame.api.Api;
import com.hyjt.frame.base.BaseFragment;
import com.hyjt.frame.di.component.AppComponent;
import com.hyjt.frame.event.RefModuleEvent;
import com.hyjt.frame.utils.JsonUtils;
import com.hyjt.frame.utils.UiUtils;
import com.hyjt.home.mvp.ui.view.BlocPop;
import com.hyjt.home.mvp.ui.view.StaffStatePop;

import org.greenrobot.greendao.query.QueryBuilder;
import org.simple.eventbus.Subscriber;
import org.simple.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;
import static com.hyjt.app.R.id.tv_out_login;
import static com.hyjt.frame.utils.Preconditions.checkNotNull;


public class WorkFragment extends BaseFragment<WorkPresenter> implements WorkContract.View {

    private TextView tvDepartment;
    private TextView tvPosition;
    private TextView tvName;
    private ImageView tvHead;
    private TextView tvOutLogin;
    private ModuleBeanDbDao moduleBeanDbDao;
    private DaoSession daoSession;
    private RecyclerView mRecyGsgl;
    private RecyclerView mRecyYwgl;
    private RecyclerView mRecyYwsq;
    private RecyclerView mRecyRsgl;
    private RecyclerView mRecyXmgl;
    private RecyclerView mRecyCwgl;
    private RecyclerView mRecyZlgl;
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
    private LinearLayout mLlGsgl;
    private LinearLayout mLlYwgl;
    private LinearLayout mLlYwsq;
    private LinearLayout mLlRsgl;
    private LinearLayout mLlXmgl;
    private LinearLayout mLlCwgl;
    private LinearLayout mLlZlgl;
    private BlocPop meetingLog;
    private TextView mTvGsglManage;
    private StaffBeanDbDao staffBeanDbDao;
    private StaffBeanDb staffBeanDb;

    public static WorkFragment newInstance() {
        WorkFragment fragment = new WorkFragment();
        return fragment;
    }

    @Override
    public void setupFragmentComponent(AppComponent appComponent) {
        DaggerWorkComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .workModule(new WorkModule(this))
                .build()
                .inject(this);
    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_work, container, false);


        tvDepartment = (TextView) inflate.findViewById(R.id.tv_department);
        tvPosition = (TextView) inflate.findViewById(R.id.tv_position);
        tvName = (TextView) inflate.findViewById(R.id.tv_name);
        tvHead = (ImageView) inflate.findViewById(R.id.iv_head);
        tvOutLogin = (TextView) inflate.findViewById(tv_out_login);

        SharedPreferences sharedPre = getActivity().getSharedPreferences("config", MODE_PRIVATE);
        String username = sharedPre.getString("username", "");
        String part = sharedPre.getString("part", "");
        String job = sharedPre.getString("job", "");
        String pic = sharedPre.getString("pic", "");
        String Id = sharedPre.getString("Id", "");

        tvDepartment.setText("部门:" + part);
        tvPosition.setText("职位:" + job);
        tvName.setText("姓名:" + username);
        Glide.with(getActivity()).load(pic).into(tvHead);

        tvOutLogin.setOnClickListener(v -> {
            ARouter.getInstance().build("/app/LoginActivity").navigation();
            mPresenter.ExitLogin();
        });


        mLlGsgl = (LinearLayout) inflate.findViewById(R.id.ll_gsgl);
        mLlYwgl = (LinearLayout) inflate.findViewById(R.id.ll_ywgl);
        mLlYwsq = (LinearLayout) inflate.findViewById(R.id.ll_ywsq);
        mLlRsgl = (LinearLayout) inflate.findViewById(R.id.ll_rsgl);
        mLlXmgl = (LinearLayout) inflate.findViewById(R.id.ll_xmgl);
        mLlCwgl = (LinearLayout) inflate.findViewById(R.id.ll_cwgl);
        mLlZlgl = (LinearLayout) inflate.findViewById(R.id.ll_zlgl);

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

        mTvGsglManage = (TextView) inflate.findViewById(R.id.tv_gsgl_manage);
        mTvGsglManage.setVisibility(View.GONE);
//        mTvGsglManage.setOnClickListener(v -> gsglDel());


        daoSession = DbHelper.getInstance().getDaoSession();
        moduleBeanDbDao = daoSession.getModuleBeanDbDao();
        staffBeanDbDao = daoSession.getStaffBeanDbDao();
        staffBeanDb = staffBeanDbDao.load(Id);

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

        GsglAdapter.setOnItemClickListener((itemView, position, moduleBean) -> arountModule(moduleBean.getClickId()));
        YwglAdapter.setOnItemClickListener((itemView, position, moduleBean) -> arountModule(moduleBean.getClickId()));
        YwsqAdapter.setOnItemClickListener((itemView, position, moduleBean) -> arountModule(moduleBean.getClickId()));
        RsglAdapter.setOnItemClickListener((itemView, position, moduleBean) -> arountModule(moduleBean.getClickId()));
        XmglAdapter.setOnItemClickListener((itemView, position, moduleBean) -> arountModule(moduleBean.getClickId()));
        CwglAdapter.setOnItemClickListener((itemView, position, moduleBean) -> arountModule(moduleBean.getClickId()));
        ZlglAdapter.setOnItemClickListener((itemView, position, moduleBean) -> arountModule(moduleBean.getClickId()));

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


    @Override
    public void initData(Bundle savedInstanceState) {
        mPresenter.getMsgNum();
    }


    @Override
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
        UiUtils.snackbarText(message);
    }

    @Override
    public void killMyself() {

    }

    private void gsglDel() {

        moduleGsgl.clear();
        QueryBuilder<ModuleBeanDb> gsglDb1 = moduleBeanDbDao.queryBuilder()
                .where(ModuleBeanDbDao.Properties.Type.eq(1));
        for (ModuleBeanDb moduleBeanDb : gsglDb1.list()) {
            moduleBeanDb.setShowDel(!moduleBeanDb.getShowDel());
            moduleBeanDbDao.update(moduleBeanDb);
        }
        QueryBuilder<ModuleBeanDb> gsglDb2 = moduleBeanDbDao.queryBuilder()
                .where(ModuleBeanDbDao.Properties.Type.eq(1));
        Db2Bean(gsglDb2.list());
        GsglAdapter.notifyDataSetChanged();

    }

    private void loadModuleList() {
        Log.e("嘎嘎嘎", staffBeanDb.getModuleList());
        List<ModuleBeanDb> moduleBeanDbs = moduleBeanDbDao.loadAll();
        moduleGsgl.clear();
        moduleYwgl.clear();
        moduleYwsq.clear();
        moduleRsgl.clear();
        moduleXmgl.clear();
        moduleCwgl.clear();
        moduleZlgl.clear();

//        Db2Bean(moduleBeanDbs);
        String StaffJson = staffBeanDb.getModuleList().replace("\\", "");
        String[] strAry = StaffJson.split("\\|");
        Bean2List(strAry);
        for (String staff : strAry) {
            ModuleBean moduleBean = JsonUtils.parseJson(staff, ModuleBean.class);
            moduleGsgl.add(moduleBean);
        }

        if (moduleGsgl.size() == 0) {
            mLlGsgl.setVisibility(View.GONE);
        } else {
            mLlGsgl.setVisibility(View.VISIBLE);
            GsglAdapter.notifyDataSetChanged();
        }
        if (moduleYwgl.size() == 0) {
            mLlYwgl.setVisibility(View.GONE);
        } else {
            mLlYwgl.setVisibility(View.VISIBLE);
            YwglAdapter.notifyDataSetChanged();
        }
        if (moduleYwsq.size() == 0) {
            mLlYwsq.setVisibility(View.GONE);
        } else {
            mLlYwsq.setVisibility(View.VISIBLE);
            YwsqAdapter.notifyDataSetChanged();
        }
        if (moduleRsgl.size() == 0) {
            mLlRsgl.setVisibility(View.GONE);
        } else {
            mLlRsgl.setVisibility(View.VISIBLE);
            RsglAdapter.notifyDataSetChanged();
        }
        if (moduleXmgl.size() == 0) {
            mLlXmgl.setVisibility(View.GONE);
        } else {
            mLlXmgl.setVisibility(View.VISIBLE);
            XmglAdapter.notifyDataSetChanged();
        }
        if (moduleCwgl.size() == 0) {
            mLlCwgl.setVisibility(View.GONE);
        } else {
            mLlCwgl.setVisibility(View.VISIBLE);
            CwglAdapter.notifyDataSetChanged();
        }
        if (moduleZlgl.size() == 0) {
            mLlZlgl.setVisibility(View.GONE);
        } else {
            mLlZlgl.setVisibility(View.VISIBLE);
            ZlglAdapter.notifyDataSetChanged();
        }
    }

    private void Db2Bean(List<ModuleBeanDb> moduleBeanDbs) {
        for (ModuleBeanDb moduleBeanDb : moduleBeanDbs) {
            if (moduleBeanDb.getIsShow()) {
                switch (moduleBeanDb.getType()) {
                    case 1:
                        moduleGsgl.add(new ModuleBean(moduleBeanDb.getImg(), moduleBeanDb.getName()
                                , moduleBeanDb.getMessage_nub(), moduleBeanDb.getClickId(), moduleBeanDb.getShowDel()));
                        break;
                    case 2:
                        moduleYwgl.add(new ModuleBean(moduleBeanDb.getImg(), moduleBeanDb.getName()
                                , moduleBeanDb.getMessage_nub(), moduleBeanDb.getClickId(), moduleBeanDb.getShowDel()));
                        break;
                    case 3:
                        moduleYwsq.add(new ModuleBean(moduleBeanDb.getImg(), moduleBeanDb.getName()
                                , moduleBeanDb.getMessage_nub(), moduleBeanDb.getClickId(), moduleBeanDb.getShowDel()));
                        break;
                    case 4:
                        moduleRsgl.add(new ModuleBean(moduleBeanDb.getImg(), moduleBeanDb.getName()
                                , moduleBeanDb.getMessage_nub(), moduleBeanDb.getClickId(), moduleBeanDb.getShowDel()));
                        break;
                    case 5:
                        moduleXmgl.add(new ModuleBean(moduleBeanDb.getImg(), moduleBeanDb.getName()
                                , moduleBeanDb.getMessage_nub(), moduleBeanDb.getClickId(), moduleBeanDb.getShowDel()));
                        break;
                    case 6:
                        moduleCwgl.add(new ModuleBean(moduleBeanDb.getImg(), moduleBeanDb.getName()
                                , moduleBeanDb.getMessage_nub(), moduleBeanDb.getClickId(), moduleBeanDb.getShowDel()));
                        break;
                    case 7:
                        moduleZlgl.add(new ModuleBean(moduleBeanDb.getImg(), moduleBeanDb.getName()
                                , moduleBeanDb.getMessage_nub(), moduleBeanDb.getClickId(), moduleBeanDb.getShowDel()));
                        break;
                }
            }
        }
    }

    private void Bean2List(String[] moduleBeanDbs) {
        for (String moduleBeanStr : moduleBeanDbs) {
            ModuleBean moduleBean = JsonUtils.parseJson(moduleBeanStr, ModuleBean.class);
            switch (moduleBean.getType()) {
                case 1:
                    moduleGsgl.add(moduleBean);
                    break;
                case 2:
                    moduleYwgl.add(moduleBean);
                    break;
                case 3:
                    moduleYwsq.add(moduleBean);
                    break;
                case 4:
                    moduleRsgl.add(moduleBean);
                    break;
                case 5:
                    moduleXmgl.add(moduleBean);
                    break;
                case 6:
                    moduleCwgl.add(moduleBean);
                    break;
                case 7:
                    moduleZlgl.add(moduleBean);
                    break;
            }
        }
    }

    @Subscriber(tag = "Ref_Module", mode = ThreadMode.MAIN)
    public void refModule(RefModuleEvent RefModuleEvent) {
        loadModuleList();
    }


    /**
     * 集团选择
     */
    private View.OnClickListener meetingLogOnClick = v -> {
        meetingLog.dismiss();
        int i = v.getId();
        if (i == R.id.ll_bloc) {
            //集团
            ARouter.getInstance().build("/home/MeetingListActivity").withString("bloc", "集团").navigation(getActivity(), Api.WorkStartCode);
        } else if (i == R.id.ll_mining) {
            //矿业
            ARouter.getInstance().build("/home/MeetingListActivity").withString("bloc", "矿业").navigation(getActivity(), Api.WorkStartCode);
        } else if (i == R.id.ll_project) {
            //工程
            ARouter.getInstance().build("/home/MeetingListActivity").withString("bloc", "工程").navigation(getActivity(), Api.WorkStartCode);
        } else if (i == R.id.ll_bloc_heating) {
            //集美
            ARouter.getInstance().build("/home/MeetingListActivity").withString("bloc", "集美").navigation(getActivity(), Api.WorkStartCode);
        }
    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Api.WorkStartCode) {
            mPresenter.getMsgNum();
        }
    }

    @Override
    public void showMission(WorkMission workMission) {
        if (Integer.parseInt(workMission.getRecord()) > 0) {
            ModuleBeanDb moduleBean = moduleBeanDbDao.load("待解决任务");
            moduleBean.setMessage_nub(Integer.parseInt(workMission.getRecord()));
            moduleBeanDbDao.update(moduleBean);
        }
        if (Integer.parseInt(workMission.getRecordL()) > 0) {
            ModuleBeanDb moduleBean = moduleBeanDbDao.load("长期待解决");
            moduleBean.setMessage_nub(Integer.parseInt(workMission.getRecordL()));
            moduleBeanDbDao.update(moduleBean);
        }
        if (Integer.parseInt(workMission.getRecordDb()) > 0) {
            ModuleBeanDb moduleBean = moduleBeanDbDao.load("督办任务");
            moduleBean.setMessage_nub(Integer.parseInt(workMission.getRecordDb()));
            moduleBeanDbDao.update(moduleBean);
        }
        if (Integer.parseInt(workMission.getSysLetter()) > 0) {
            ModuleBeanDb moduleBean = moduleBeanDbDao.load("内部邮件");
            moduleBean.setMessage_nub(Integer.parseInt(workMission.getSysLetter()));
            moduleBeanDbDao.update(moduleBean);
        }
        if (Integer.parseInt(workMission.getWorkingConference()) > 0) {
            ModuleBeanDb moduleBean = moduleBeanDbDao.load("汇报上级");
            moduleBean.setMessage_nub(Integer.parseInt(workMission.getWorkingConference()));
            moduleBeanDbDao.update(moduleBean);
        }
        if (Integer.parseInt(workMission.getWorkingConsult()) > 0) {
            ModuleBeanDb moduleBean = moduleBeanDbDao.load("评级协商");
            moduleBean.setMessage_nub(Integer.parseInt(workMission.getWorkingConsult()));
            moduleBeanDbDao.update(moduleBean);
        }
        if (Integer.parseInt(workMission.getLeapfrogReport()) > 0) {
            ModuleBeanDb moduleBean = moduleBeanDbDao.load("越级汇报");
            moduleBean.setMessage_nub(Integer.parseInt(workMission.getLeapfrogReport()));
            moduleBeanDbDao.update(moduleBean);
        }
        if (Integer.parseInt(workMission.getYuangongxianji()) > 0) {
            ModuleBeanDb moduleBean = moduleBeanDbDao.load("献计献策");
            moduleBean.setMessage_nub(Integer.parseInt(workMission.getYuangongxianji()));
            moduleBeanDbDao.update(moduleBean);
        }
        if (Integer.parseInt(workMission.getUnionAppeal()) > 0) {
            ModuleBeanDb moduleBean = moduleBeanDbDao.load("工会诉求");
            moduleBean.setMessage_nub(Integer.parseInt(workMission.getUnionAppeal()));
            moduleBeanDbDao.update(moduleBean);
        }
        loadModuleList();
    }

    private void arountModule(Integer moduleId) {
        switch (moduleId) {
            case 1:
                break;
            case 7: {
                ReAndSePop reAndSePop = new ReAndSePop(getActivity());
                reAndSePop.setOnItemClickListener(box -> {
                    ARouter.getInstance().build("/home/ContributeIdeaListActivity")
                            .withString("type", box).navigation(getActivity(), Api.WorkStartCode);
                    reAndSePop.dismiss();
                });
                reAndSePop.showAtLocation(getActivity().findViewById(R.id.rl_home_fragment_work),
                        Gravity.CENTER | Gravity.CENTER_HORIZONTAL, 0, 0);
                break;
            }
            case 8: {
                SkipReportPop skipReportPop = new SkipReportPop(getActivity());
                skipReportPop.setOnItemClickListener(box -> {
                    ARouter.getInstance().build("/home/SkipReportListActivity")
                            .withString("type", box).navigation(getActivity(), Api.WorkStartCode);
                    skipReportPop.dismiss();
                });
                skipReportPop.showAtLocation(getActivity().findViewById(R.id.rl_home_fragment_work),
                        Gravity.CENTER | Gravity.CENTER_HORIZONTAL, 0, 0);
                break;
            }
            case 9: {
                ReAndSePop reAndSePop = new ReAndSePop(getActivity());
                reAndSePop.setOnItemClickListener(box -> {
                    ARouter.getInstance().build("/home/LaborUnionReqsListActivity")
                            .withString("type", box).navigation(getActivity(), Api.WorkStartCode);
                    reAndSePop.dismiss();
                });
                reAndSePop.showAtLocation(getActivity().findViewById(R.id.rl_home_fragment_work),
                        Gravity.CENTER | Gravity.CENTER_HORIZONTAL, 0, 0);
                break;
            }
            case 10: {
                StaffStatePop staffStatePop = new StaffStatePop(getActivity());
                staffStatePop.setSelStafListener(type -> {
                    ARouter.getInstance().build("/home/AddressBookActivity")
                            .withString("state", type).navigation(getActivity(), Api.WorkStartCode);
                    staffStatePop.dismiss();
                });
                staffStatePop.showAtLocation(getActivity().findViewById(R.id.rl_home_fragment_work),
                        Gravity.CENTER | Gravity.CENTER_HORIZONTAL, 0, 0);
                break;
            }
            case 21: {
                meetingLog = new BlocPop(getActivity(), meetingLogOnClick);
                meetingLog.showAtLocation(getActivity().findViewById(R.id.rl_home_fragment_work),
                        Gravity.CENTER | Gravity.CENTER_HORIZONTAL, 0, 0);
                break;
            }
            case 22:
                ARouter.getInstance().build("/home/WaitSolveListActivity").navigation(getActivity(), Api.WorkStartCode);
                break;
            case 23:
                ARouter.getInstance().build("/home/LongSolveListActivity").navigation(getActivity(), Api.WorkStartCode);
                break;
            case 24:
                ARouter.getInstance().build("/home/SuperviseSolveListActivity").navigation(getActivity(), Api.WorkStartCode);
                break;
            case 27: {
                EmailPop emailPop = new EmailPop(getActivity());
                emailPop.setOnItemClickListener(box -> {
                    ARouter.getInstance().build("/home/EmailListActivity")
                            .withString("type", box).navigation(getActivity(), Api.WorkStartCode);
                    emailPop.dismiss();
                });
                emailPop.showAtLocation(getActivity().findViewById(R.id.rl_home_fragment_work),
                        Gravity.CENTER | Gravity.CENTER_HORIZONTAL, 0, 0);
                break;
            }
            case 28: {
                ReportTopPop reportTopPop = new ReportTopPop(getActivity());
                reportTopPop.setOnItemClickListener(box -> {
                    ARouter.getInstance().build("/home/ReportTopListActivity")
                            .withString("type", box).navigation(getActivity(), Api.WorkStartCode);
                    reportTopPop.dismiss();
                });
                reportTopPop.showAtLocation(getActivity().findViewById(R.id.rl_home_fragment_work),
                        Gravity.CENTER | Gravity.CENTER_HORIZONTAL, 0, 0);
                break;
            }
            case 29: {
                SLConsultPop sLConsultPop = new SLConsultPop(getActivity());
                sLConsultPop.setOnItemClickListener(box -> {
                    ARouter.getInstance().build("/home/SLConsultListActivity")
                            .withString("type", box).navigation(getActivity(), Api.WorkStartCode);
                    sLConsultPop.dismiss();
                });
                sLConsultPop.showAtLocation(getActivity().findViewById(R.id.rl_home_fragment_work),
                        Gravity.CENTER | Gravity.CENTER_HORIZONTAL, 0, 0);
                break;
            }
            case 64: {
                StaffStatePop staffStatePop = new StaffStatePop(getActivity());
                staffStatePop.setSelStafListener(type -> {
                    ARouter.getInstance().build("/home/StaffListActivity")
                            .withString("state", type).navigation(getActivity(), Api.WorkStartCode);
                    staffStatePop.dismiss();
                });
                staffStatePop.showAtLocation(getActivity().findViewById(R.id.rl_home_fragment_work),
                        Gravity.CENTER | Gravity.CENTER_HORIZONTAL, 0, 0);
                break;
            }
            case 65: {
                ARouter.getInstance().build("/home/BlocNoticeListActivity").navigation(getActivity(), Api.WorkStartCode);
                break;
            }
        }
    }

}
