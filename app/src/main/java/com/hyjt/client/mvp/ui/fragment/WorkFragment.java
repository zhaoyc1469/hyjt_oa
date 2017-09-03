package com.hyjt.client.mvp.ui.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
import com.hyjt.client.mvp.model.entity.WorkMission;
import com.hyjt.client.mvp.presenter.WorkPresenter;
import com.hyjt.client.mvp.ui.view.EmailPop;
import com.hyjt.frame.api.Api;
import com.hyjt.frame.base.BaseFragment;
import com.hyjt.frame.di.component.AppComponent;
import com.hyjt.frame.utils.UiUtils;
import com.hyjt.home.mvp.ui.view.BlocPop;
import com.hyjt.home.mvp.ui.view.StaffStatePop;

import static android.content.Context.MODE_PRIVATE;
import static com.hyjt.app.R.id.tv_out_login;
import static com.hyjt.frame.utils.Preconditions.checkNotNull;


public class WorkFragment extends BaseFragment<WorkPresenter> implements WorkContract.View, View.OnClickListener {

    private BlocPop meetingLog;
    private StaffStatePop staffStatePop;
    private TranslateAnimation mShowAction;
    private TranslateAnimation mHiddenAction;
    private android.widget.TextView mTvGsglExpand;
    private android.widget.LinearLayout mLlGsglChild;
    private android.widget.TextView mTvYwglExpand;
    private android.widget.LinearLayout mLlYwglChild;
    private AlphaAnimation mHiddenAlpha;
    private AlphaAnimation mShowAlpha;
    private TextView mTvYwsqExpand;
    private LinearLayout mLlYwsqChild;
    private android.widget.RelativeLayout mRlDjjrwNum;
    private TextView mTvDjjrwNum;
    private android.widget.RelativeLayout mRlCqdjjNum;
    private TextView mTvCqdjjNum;
    private android.widget.RelativeLayout mRlDbrwNum;
    private TextView mTvDbrwNum;
    private RelativeLayout mRlGsjg;
    private RelativeLayout mRlGszl;
    private RelativeLayout mRlBmzl;
    private RelativeLayout mRlZfwz;
    private RelativeLayout mRlGswz;
    private RelativeLayout mRlHylt;
    private RelativeLayout mRlYjhb;
    private RelativeLayout mRlXjxc;
    private RelativeLayout mRlTxl;
    private RelativeLayout mRlHyjy;
    private RelativeLayout mRlDjjrw;
    private RelativeLayout mRlCqdjj;
    private RelativeLayout mRlDbrw;
    private RelativeLayout mRlKhgx;
    private RelativeLayout mRlLfxx;
    private RelativeLayout mRlJtgg;
    private RelativeLayout mRlJksq;
    private RelativeLayout mRlBxsq;
    private RelativeLayout mRlYcsq;
    private RelativeLayout mRlWply;
    private RelativeLayout mRlYysq;
    private RelativeLayout mRlCgsq;
    private RelativeLayout mRlDysq;
    private RelativeLayout mRlSjsq;
    private RelativeLayout mRlFwsq;
    private RelativeLayout mRlHtsq;
    private RelativeLayout mRlZp;
    private RelativeLayout mRlDgsqd;
    private RelativeLayout mRlLzsqd;
    private RelativeLayout mRlYgxx;
    private RelativeLayout mRlXmda;
    private RelativeLayout mRlXmhb;
    private RelativeLayout mRlXmzl;
    private RelativeLayout mRlJksp;
    private RelativeLayout mRlFysp;
    private RelativeLayout mRlBxsp;
    private RelativeLayout mRlBg;
    private RelativeLayout mRlHt;
    private RelativeLayout mRlQt;
    private TextView tvDepartment;
    private TextView tvPosition;
    private TextView tvName;
    private ImageView tvHead;
    private TextView tvOutLogin;
    private RelativeLayout mRlNbyj;
    private TextView mTvNbyjNum;
    private RelativeLayout mRlNbyjNum;

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

        // 设置展开
        mShowAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                -1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
        mShowAction.setDuration(500);
        mShowAlpha = new AlphaAnimation(0, 1);
        mShowAlpha.setDuration(500);

        mHiddenAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF,
                0.0f, Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                -1.0f);
        mHiddenAction.setDuration(500);
        mHiddenAlpha = new AlphaAnimation(1, 0);
        mHiddenAlpha.setDuration(500);

        mTvGsglExpand = (TextView) inflate.findViewById(R.id.tv_gsgl_expand);
        mLlGsglChild = (LinearLayout) inflate.findViewById(R.id.ll_gsgl_child);
        mTvYwglExpand = (TextView) inflate.findViewById(R.id.tv_ywgl_expand);
        mLlYwglChild = (LinearLayout) inflate.findViewById(R.id.ll_ywgl_child);
        mTvYwsqExpand = (TextView) inflate.findViewById(R.id.tv_ywsq_expand);
        mLlYwsqChild = (LinearLayout) inflate.findViewById(R.id.ll_ywsq_child);
        mTvGsglExpand.setOnClickListener(v -> setExpand(mLlGsglChild));
        mTvYwglExpand.setOnClickListener(v -> setExpand(mLlYwglChild));
        mTvYwsqExpand.setOnClickListener(v -> setExpand(mLlYwsqChild));

        //设置消息数量
        mRlDjjrwNum = (RelativeLayout) inflate.findViewById(R.id.rl_djjrw_num);
        mTvDjjrwNum = (TextView) inflate.findViewById(R.id.tv_djjrw_num);
        mRlCqdjjNum = (RelativeLayout) inflate.findViewById(R.id.rl_cqdjj_num);
        mTvCqdjjNum = (TextView) inflate.findViewById(R.id.tv_cqdjj_num);
        mRlDbrwNum = (RelativeLayout) inflate.findViewById(R.id.rl_dbrw_num);
        mTvDbrwNum = (TextView) inflate.findViewById(R.id.tv_dbrw_num);
        mRlNbyjNum = (RelativeLayout) inflate.findViewById(R.id.rl_nbyj_num);
        mTvNbyjNum = (TextView) inflate.findViewById(R.id.tv_nbyj_num);

        //设置模块点击事件
        mRlGsjg = (RelativeLayout) inflate.findViewById(R.id.rl_gsjg);
        mRlGszl = (RelativeLayout) inflate.findViewById(R.id.rl_gszl);
        mRlBmzl = (RelativeLayout) inflate.findViewById(R.id.rl_bmzl);
        mRlZfwz = (RelativeLayout) inflate.findViewById(R.id.rl_zfwz);
        mRlGswz = (RelativeLayout) inflate.findViewById(R.id.rl_gswz);
        mRlHylt = (RelativeLayout) inflate.findViewById(R.id.rl_hylt);
        mRlYjhb = (RelativeLayout) inflate.findViewById(R.id.rl_yjhb);
        mRlXjxc = (RelativeLayout) inflate.findViewById(R.id.rl_xjxc);
        mRlTxl = (RelativeLayout) inflate.findViewById(R.id.rl_txl);
        mRlHyjy = (RelativeLayout) inflate.findViewById(R.id.rl_hyjy);
        mRlDjjrw = (RelativeLayout) inflate.findViewById(R.id.rl_djjrw);
        mRlCqdjj = (RelativeLayout) inflate.findViewById(R.id.rl_cqdjj);
        mRlDbrw = (RelativeLayout) inflate.findViewById(R.id.rl_dbrw);
        mRlKhgx = (RelativeLayout) inflate.findViewById(R.id.rl_khgx);
        mRlLfxx = (RelativeLayout) inflate.findViewById(R.id.rl_lfxx);
        mRlJtgg = (RelativeLayout) inflate.findViewById(R.id.rl_jtgg);
        mRlJksq = (RelativeLayout) inflate.findViewById(R.id.rl_jksq);
        mRlBxsq = (RelativeLayout) inflate.findViewById(R.id.rl_bxsq);
        mRlYcsq = (RelativeLayout) inflate.findViewById(R.id.rl_ycsq);
        mRlWply = (RelativeLayout) inflate.findViewById(R.id.rl_wply);
        mRlYysq = (RelativeLayout) inflate.findViewById(R.id.rl_yysq);
        mRlCgsq = (RelativeLayout) inflate.findViewById(R.id.rl_cgsq);
        mRlDysq = (RelativeLayout) inflate.findViewById(R.id.rl_dysq);
        mRlSjsq = (RelativeLayout) inflate.findViewById(R.id.rl_sjsq);
        mRlFwsq = (RelativeLayout) inflate.findViewById(R.id.rl_fwsq);
        mRlHtsq = (RelativeLayout) inflate.findViewById(R.id.rl_htsq);
        mRlZp = (RelativeLayout) inflate.findViewById(R.id.rl_zp);
        mRlDgsqd = (RelativeLayout) inflate.findViewById(R.id.rl_dgsqd);
        mRlLzsqd = (RelativeLayout) inflate.findViewById(R.id.rl_lzsqd);
        mRlYgxx = (RelativeLayout) inflate.findViewById(R.id.rl_ygxx);
        mRlXmda = (RelativeLayout) inflate.findViewById(R.id.rl_xmda);
        mRlXmhb = (RelativeLayout) inflate.findViewById(R.id.rl_xmhb);
        mRlXmzl = (RelativeLayout) inflate.findViewById(R.id.rl_xmzl);
        mRlJksp = (RelativeLayout) inflate.findViewById(R.id.rl_jksp);
        mRlFysp = (RelativeLayout) inflate.findViewById(R.id.rl_fysp);
        mRlBxsp = (RelativeLayout) inflate.findViewById(R.id.rl_bxsp);
        mRlBg = (RelativeLayout) inflate.findViewById(R.id.rl_bg);
        mRlHt = (RelativeLayout) inflate.findViewById(R.id.rl_ht);
        mRlQt = (RelativeLayout) inflate.findViewById(R.id.rl_qt);
        mRlNbyj = (RelativeLayout) inflate.findViewById(R.id.rl_nbyj);

        mRlGsjg.setOnClickListener(this);
        mRlGszl.setOnClickListener(this);
        mRlBmzl.setOnClickListener(this);
        mRlZfwz.setOnClickListener(this);
        mRlGswz.setOnClickListener(this);
        mRlHylt.setOnClickListener(this);
        mRlYjhb.setOnClickListener(this);
        mRlXjxc.setOnClickListener(this);
        mRlTxl.setOnClickListener(this);
        mRlHyjy.setOnClickListener(this);
        mRlDjjrw.setOnClickListener(this);
        mRlCqdjj.setOnClickListener(this);
        mRlDbrw.setOnClickListener(this);
        mRlKhgx.setOnClickListener(this);
        mRlLfxx.setOnClickListener(this);
        mRlJtgg.setOnClickListener(this);
        mRlJksq.setOnClickListener(this);
        mRlBxsq.setOnClickListener(this);
        mRlYcsq.setOnClickListener(this);
        mRlWply.setOnClickListener(this);
        mRlYysq.setOnClickListener(this);
        mRlCgsq.setOnClickListener(this);
        mRlDysq.setOnClickListener(this);
        mRlSjsq.setOnClickListener(this);
        mRlFwsq.setOnClickListener(this);
        mRlHtsq.setOnClickListener(this);
        mRlZp.setOnClickListener(this);
        mRlDgsqd.setOnClickListener(this);
        mRlLzsqd.setOnClickListener(this);
        mRlYgxx.setOnClickListener(this);
        mRlXmda.setOnClickListener(this);
        mRlXmhb.setOnClickListener(this);
        mRlXmzl.setOnClickListener(this);
        mRlJksp.setOnClickListener(this);
        mRlFysp.setOnClickListener(this);
        mRlBxsp.setOnClickListener(this);
        mRlBg.setOnClickListener(this);
        mRlHt.setOnClickListener(this);
        mRlQt.setOnClickListener(this);
        mRlNbyj.setOnClickListener(this);

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

        tvDepartment.setText("部门:" + part);
        tvPosition.setText("职位:" + job);
        tvName.setText("姓名:" + username);
        Glide.with(getActivity()).load(pic).into(tvHead);

        tvOutLogin.setOnClickListener(v -> {
            ARouter.getInstance().build("/app/LoginActivity").navigation();

            mPresenter.ExitLogin();
        });

        return inflate;
    }

    /**
     * 展开收起
     *
     * @param mLlChild
     */
    private void setExpand(LinearLayout mLlChild) {
        if (mLlChild.getVisibility() == View.GONE) {
            mLlChild.startAnimation(mShowAction);
            mLlChild.startAnimation(mShowAlpha);
            mLlChild.setVisibility(View.VISIBLE);
        } else {
            mLlChild.startAnimation(mHiddenAction);
            mLlChild.startAnimation(mHiddenAlpha);
            mLlChild.setVisibility(View.GONE);
        }
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


    /**
     * 员工选择
     */
    private View.OnClickListener staffStateOnClick = v -> {
        staffStatePop.dismiss();
        int i = v.getId();
        if (i == R.id.ll_on_job) {
            //在职
            ARouter.getInstance().build("/home/StaffListActivity").withString("state", "在职").navigation(getActivity(), Api.WorkStartCode);
        } else if (i == R.id.ll_practice) {
            //实习
            ARouter.getInstance().build("/home/StaffListActivity").withString("state", "实习").navigation(getActivity(), Api.WorkStartCode);
        } else if (i == R.id.ll_part_job) {
            //兼职
            ARouter.getInstance().build("/home/StaffListActivity").withString("state", "兼职").navigation(getActivity(), Api.WorkStartCode);
        } else if (i == R.id.ll_holiday) {
            //休假
            ARouter.getInstance().build("/home/StaffListActivity").withString("state", "休假").navigation(getActivity(), Api.WorkStartCode);
        } else if (i == R.id.ll_leave_office) {
            //离职
            ARouter.getInstance().build("/home/StaffListActivity").withString("state", "离职").navigation(getActivity(), Api.WorkStartCode);
        }
    };

    /**
     * 通讯录员工选择
     */
    private View.OnClickListener addressBookOnClick = v -> {
        staffStatePop.dismiss();
        int i = v.getId();
        if (i == R.id.ll_on_job) {
            //在职
            ARouter.getInstance().build("/home/AddressBookActivity").withString("state", "在职").navigation(getActivity(), Api.WorkStartCode);
        } else if (i == R.id.ll_practice) {
            //实习
            ARouter.getInstance().build("/home/AddressBookActivity").withString("state", "实习").navigation(getActivity(), Api.WorkStartCode);
        } else if (i == R.id.ll_part_job) {
            //兼职
            ARouter.getInstance().build("/home/AddressBookActivity").withString("state", "兼职").navigation(getActivity(), Api.WorkStartCode);
        } else if (i == R.id.ll_holiday) {
            //休假
            ARouter.getInstance().build("/home/AddressBookActivity").withString("state", "休假").navigation(getActivity(), Api.WorkStartCode);
        } else if (i == R.id.ll_leave_office) {
            //离职
            ARouter.getInstance().build("/home/AddressBookActivity").withString("state", "离职").navigation(getActivity(), Api.WorkStartCode);
        }
    };

    /**
     * 展示消息个数
     *
     * @param workMission
     */
    @Override
    public void showMission(WorkMission workMission) {
        if (Integer.parseInt(workMission.getRecord()) > 0) {
            mRlDjjrwNum.setVisibility(View.VISIBLE);
            mTvDjjrwNum.setText(workMission.getRecord());
        } else {
            mRlDjjrwNum.setVisibility(View.GONE);
        }
        if (Integer.parseInt(workMission.getRecordL()) > 0) {
            mRlCqdjjNum.setVisibility(View.VISIBLE);
            mTvCqdjjNum.setText(workMission.getRecordL());
        } else {
            mRlCqdjjNum.setVisibility(View.GONE);
        }
        if (Integer.parseInt(workMission.getRecordDb()) > 0) {
            mRlDbrwNum.setVisibility(View.VISIBLE);
            mTvDbrwNum.setText(workMission.getRecordDb());
        } else {
            mRlDbrwNum.setVisibility(View.GONE);
        }
        if (Integer.parseInt(workMission.getSysLetter()) > 0) {
            mRlNbyjNum.setVisibility(View.VISIBLE);
            mTvNbyjNum.setText(workMission.getSysLetter());
        } else {
            mRlNbyjNum.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_hyjy: {
                meetingLog = new BlocPop(getActivity(), meetingLogOnClick);
                meetingLog.showAtLocation(getActivity().findViewById(R.id.rl_home_fragment_work),
                        Gravity.CENTER | Gravity.CENTER_HORIZONTAL, 0, 0);
                break;
            }
            case R.id.rl_djjrw: {
                ARouter.getInstance().build("/home/WaitSolveListActivity").navigation(getActivity(), Api.WorkStartCode);
                break;
            }
            case R.id.rl_cqdjj: {
                ARouter.getInstance().build("/home/LongSolveListActivity").navigation(getActivity(), Api.WorkStartCode);
                break;
            }
            case R.id.rl_dbrw: {
                ARouter.getInstance().build("/home/SuperviseSolveListActivity").navigation(getActivity(), Api.WorkStartCode);
                break;
            }
            case R.id.rl_txl: {
                staffStatePop = new StaffStatePop(getActivity(), addressBookOnClick);
                staffStatePop.showAtLocation(getActivity().findViewById(R.id.rl_home_fragment_work),
                        Gravity.CENTER | Gravity.CENTER_HORIZONTAL, 0, 0);
                break;
            }
            case R.id.rl_gszl: {
                ARouter.getInstance().build("/home/CreateMtActivity").navigation(getActivity(), Api.WorkStartCode);
                break;
            }
            case R.id.rl_ygxx: {
                staffStatePop = new StaffStatePop(getActivity(), staffStateOnClick);
                staffStatePop.showAtLocation(getActivity().findViewById(R.id.rl_home_fragment_work),
                        Gravity.CENTER | Gravity.CENTER_HORIZONTAL, 0, 0);
                break;
            }
            case R.id.rl_jtgg: {
                ARouter.getInstance().build("/home/BlocNoticeListActivity").navigation(getActivity(), Api.WorkStartCode);
                break;
            }
            case R.id.rl_nbyj: {

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
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Api.WorkStartCode){
            mPresenter.getMsgNum();
        }
    }
}
