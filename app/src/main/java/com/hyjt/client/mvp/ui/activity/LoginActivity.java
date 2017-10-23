package com.hyjt.client.mvp.ui.activity;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.hyjt.app.R;
import com.hyjt.client.di.component.DaggerLoginComponent;
import com.hyjt.client.di.module.LoginModule;
import com.hyjt.client.mvp.contract.LoginContract;
import com.hyjt.client.mvp.model.entity.Bean.ModuleBean;
import com.hyjt.client.mvp.model.entity.LoginResp;
import com.hyjt.client.mvp.presenter.LoginPresenter;
import com.hyjt.db.DbHelper;
import com.hyjt.db.bean.ModuleBeanDb;
import com.hyjt.db.bean.StaffBeanDb;
import com.hyjt.db.gen.DaoSession;
import com.hyjt.db.gen.ModuleBeanDbDao;
import com.hyjt.db.gen.StaffBeanDbDao;
import com.hyjt.frame.base.BaseActivity;
import com.hyjt.frame.di.component.AppComponent;
import com.hyjt.frame.event.OutLoginEvent;
import com.hyjt.frame.utils.JsonUtils;
import com.hyjt.frame.utils.UiUtils;

import java.util.ArrayList;
import java.util.List;

import cn.jpush.android.api.JPushInterface;

import static com.hyjt.frame.utils.Preconditions.checkNotNull;


@Route(path = "/app/LoginActivity")
public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.View {


    private android.widget.EditText mEdtUsername;
    private android.widget.EditText mEdtPassword;
    private android.widget.Button mButton;
    private android.widget.CheckBox mCbRebPwd;
    private ProgressDialog progressDialog;
    private String registrationID;
    private DaoSession daoSession;
    private ModuleBeanDbDao moduleBeanDbDao;
    private StaffBeanDbDao staffBeanDbDao;

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerLoginComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .loginModule(new LoginModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(Bundle savedInstanceState) {
        return R.layout.activity_login; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(Bundle savedInstanceState) {

        //初始化推送
        JPushInterface.init(getApplicationContext());
        JPushInterface.resumePush(getApplicationContext());
        //RegistrationID
        registrationID = JPushInterface.getRegistrationID(getApplicationContext());


        daoSession = DbHelper.getInstance().getDaoSession();
        moduleBeanDbDao = daoSession.getModuleBeanDbDao();
        staffBeanDbDao = daoSession.getStaffBeanDbDao();

        if (moduleBeanDbDao.loadAll().size() <= 0) {
            reBaseData();
        }

        mEdtUsername = (EditText) findViewById(R.id.edt_username);
        mEdtPassword = (EditText) findViewById(R.id.edt_password);
        mButton = (Button) findViewById(R.id.button);
        mCbRebPwd = (CheckBox) findViewById(R.id.cb_reb_pwd);

        mButton.setOnClickListener(v -> {
            progressDialog = ProgressDialog.show(LoginActivity.this, null, "登录中…");
            mPresenter.sendLoginMsg(
                    mEdtUsername.getText().toString().trim(),
                    mEdtPassword.getText().toString().trim(),
                    mCbRebPwd.isChecked() ? 1 : 0,
                    registrationID);
        });


        SharedPreferences sharedPre = getSharedPreferences("config", MODE_PRIVATE);
        String username = sharedPre.getString("username", "");
        String password = sharedPre.getString("password", "");

        if (!"".equals(username)) {
            mCbRebPwd.setChecked(true);
            mEdtUsername.setText(username);
            mEdtPassword.setText(password);
        }

    }

    /**
     * 删除模块表数据，并重置数据
     */
    private void reBaseData() {
        moduleBeanDbDao.deleteAll();
        moduleBeanDbDao.insert(new ModuleBeanDb("公司架构", 1, true, false, "h_gsjg", 1, 0, -1));
        moduleBeanDbDao.insert(new ModuleBeanDb("公司治理", 1, true, false, "h_gszl", 2, 0, -1));
        moduleBeanDbDao.insert(new ModuleBeanDb("部门治理", 1, true, false, "h_bmzl", 3, 0, -1));
        moduleBeanDbDao.insert(new ModuleBeanDb("政府网站", 1, true, false, "h_zfwz", 4, 0, -1));
        moduleBeanDbDao.insert(new ModuleBeanDb("公司网站", 1, true, false, "h_gswz", 5, 0, -1));
        moduleBeanDbDao.insert(new ModuleBeanDb("环宇论坛", 1, true, false, "h_hylt", 6, 0, -1));
        moduleBeanDbDao.insert(new ModuleBeanDb("献计献策", 1, true, false, "h_xjxc", 7, 0, -1));
        moduleBeanDbDao.insert(new ModuleBeanDb("越级汇报", 1, true, false, "h_yjhb", 8, 0, -1));
        moduleBeanDbDao.insert(new ModuleBeanDb("工会诉求", 1, true, false, "h_xjxc", 9, 0, -1));
        moduleBeanDbDao.insert(new ModuleBeanDb("通讯录", 1, true, false, "h_txl", 10, 0, -1));

        moduleBeanDbDao.insert(new ModuleBeanDb("会议纪要", 2, true, false, "h_hyjy", 21, 0, -1));
        moduleBeanDbDao.insert(new ModuleBeanDb("待解决任务", 2, true, false, "h_djjrw", 22, 0, -1));
        moduleBeanDbDao.insert(new ModuleBeanDb("长期待解决", 2, true, false, "h_cqdjj", 23, 0, -1));
        moduleBeanDbDao.insert(new ModuleBeanDb("督办任务", 2, true, false, "h_dbrw", 24, 0, -1));
        moduleBeanDbDao.insert(new ModuleBeanDb("客户关系", 2, true, false, "h_khgx", 25, 0, -1));
        moduleBeanDbDao.insert(new ModuleBeanDb("来访消息", 2, true, false, "h_lfxx", 26, 0, -1));
        moduleBeanDbDao.insert(new ModuleBeanDb("内部邮件", 2, true, false, "h_nbyj", 27, 0, -1));
        moduleBeanDbDao.insert(new ModuleBeanDb("汇报上级", 2, true, false, "h_hbsj", 28, 0, -1));
        moduleBeanDbDao.insert(new ModuleBeanDb("评级协商", 2, true, false, "h_pjxs", 29, 0, -1));

        moduleBeanDbDao.insert(new ModuleBeanDb("借款申请", 3, true, false, "h_jksq", 41, 0, -1));
        moduleBeanDbDao.insert(new ModuleBeanDb("报销申请", 3, true, false, "h_bxsq", 42, 0, -1));
        moduleBeanDbDao.insert(new ModuleBeanDb("用车申请", 3, true, false, "h_ycsq", 43, 0, -1));
        moduleBeanDbDao.insert(new ModuleBeanDb("物品领用", 3, true, false, "h_wply", 44, 0, -1));
        moduleBeanDbDao.insert(new ModuleBeanDb("用印申请", 3, true, false, "h_yysq", 45, 0, -1));
        moduleBeanDbDao.insert(new ModuleBeanDb("采购申请", 3, true, false, "h_cgsq", 46, 0, -1));
        moduleBeanDbDao.insert(new ModuleBeanDb("打印申请", 3, true, false, "h_dysq", 47, 0, -1));
        moduleBeanDbDao.insert(new ModuleBeanDb("送件申请", 3, true, false, "h_sjsq", 48, 0, -1));
        moduleBeanDbDao.insert(new ModuleBeanDb("发文申请", 3, true, false, "h_fwsq", 49, 0, -1));
        moduleBeanDbDao.insert(new ModuleBeanDb("合同申请", 3, true, false, "h_htsq", 50, 0, -1));

        moduleBeanDbDao.insert(new ModuleBeanDb("招聘", 4, true, false, "h_zp", 61, 0, -1));
        moduleBeanDbDao.insert(new ModuleBeanDb("调岗申请单", 4, true, false, "h_dgsqd", 62, 0, -1));
        moduleBeanDbDao.insert(new ModuleBeanDb("离职申请单", 4, true, false, "h_lzsqd", 63, 0, -1));
        moduleBeanDbDao.insert(new ModuleBeanDb("员工信息", 4, true, false, "h_ygxx", 64, 0, -1));
        moduleBeanDbDao.insert(new ModuleBeanDb("集团公告", 4, true, false, "h_jtgg", 65, 0, -1));

        moduleBeanDbDao.insert(new ModuleBeanDb("项目档案", 5, true, false, "h_xmda", 81, 0, -1));
        moduleBeanDbDao.insert(new ModuleBeanDb("项目汇报", 5, true, false, "h_xmhb", 82, 0, -1));
        moduleBeanDbDao.insert(new ModuleBeanDb("项目资料", 5, true, false, "h_xmzl", 83, 0, -1));

        moduleBeanDbDao.insert(new ModuleBeanDb("个人借款", 6, true, false, "h_grjk", 101, 0, -1));
        moduleBeanDbDao.insert(new ModuleBeanDb("对公借款", 6, true, false, "h_fysp", 102, 0, -1));
        moduleBeanDbDao.insert(new ModuleBeanDb("费用报销", 6, true, false, "h_bxsp", 103, 0, -1));
        moduleBeanDbDao.insert(new ModuleBeanDb("出纳确认", 6, true, false, "h_bxsp", 104, 0, -1));

        moduleBeanDbDao.insert(new ModuleBeanDb("报告", 7, true, false, "h_bg", 121, 0, -1));
        moduleBeanDbDao.insert(new ModuleBeanDb("合同", 7, true, false, "h_ht", 122, 0, -1));
        moduleBeanDbDao.insert(new ModuleBeanDb("其他", 7, true, false, "h_qt", 123, 0, -1));
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
    public void skipHome(LoginResp loginResp) {
//        progressDialog.dismiss();
        if (mCbRebPwd.isChecked()) {
            // 保存用户名密码
            SharedPreferences sharedPre = getSharedPreferences("config", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPre.edit();
            editor.putString("username", mEdtUsername.getText().toString().trim());
            editor.putString("password", mEdtPassword.getText().toString().trim());
            editor.putString("part", loginResp.getPart());
            editor.putString("job", loginResp.getJob());
            editor.putString("Id", loginResp.getId());
            editor.putString("pic", getString(com.hyjt.home.R.string.home_base_url) + loginResp.getPic());
            editor.putString("sessionid", loginResp.getSessionId());
            editor.commit();
        }
        StaffBeanDb staffBean = staffBeanDbDao.load(loginResp.getId());
//        用户是否已经存储过自己的模块设置
        if (staffBean == null) {
            reBaseData();
//            将现有表数据全部加入用户模块字段
            StringBuilder stringBuilder = new StringBuilder();
            for (ModuleBeanDb moduleBean : moduleBeanDbDao.loadAll()) {
                stringBuilder.append(JsonUtils.beanToJson(new ModuleBean(moduleBean.getName(), moduleBean.getImg()
                        , moduleBean.getMessage_nub(), moduleBean.getClickId(), moduleBean.getShowDel()
                        , moduleBean.getType(), moduleBean.getIsShow())));
                stringBuilder.append("|");
            }
            StaffBeanDb insertStaffBean = new StaffBeanDb(loginResp.getId(),
                    loginResp.getName(), stringBuilder.toString());
            staffBeanDbDao.insert(insertStaffBean);
        } else {
//            将用户之前保存的模块数据导入模块表
            moduleBeanDbDao.deleteAll();
            String StaffJson = staffBean.getModuleList().replace("\\", "");
            String[] strAry = StaffJson.split("\\|");
            Bean2List(strAry);
        }
        finish();
    }

    private void Bean2List(String[] strAry) {
        for (String str : strAry) {
            ModuleBean moduleBean = JsonUtils.parseJson(str, ModuleBean.class);
            moduleBeanDbDao.insert(new ModuleBeanDb(moduleBean.getName(), moduleBean.getType()
                    , moduleBean.isShow(), moduleBean.isShow_del(), moduleBean.getImg()
                    , moduleBean.getClickId(), moduleBean.getMsg(), -1));
        }
    }

    @Override
    public void hideDialog() {
        progressDialog.dismiss();
    }

    @Override
    public void onOutLogin(OutLoginEvent outLoginEvent) {
    }
}
