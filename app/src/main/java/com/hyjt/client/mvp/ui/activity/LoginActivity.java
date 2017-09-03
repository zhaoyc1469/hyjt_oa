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
import com.hyjt.client.mvp.model.entity.LoginResp;
import com.hyjt.client.mvp.presenter.LoginPresenter;
import com.hyjt.frame.base.BaseActivity;
import com.hyjt.frame.di.component.AppComponent;
import com.hyjt.frame.event.OutLoginEvent;
import com.hyjt.frame.utils.UiUtils;

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

//        DaemonEnv.startServiceSafely(new Intent(this, TraceServiceImpl.class));
//        IntentWrapper.whiteListMatters(this, "轨迹跟踪服务的持续运行");

        //初始化推送
        JPushInterface.init(getApplicationContext());
        JPushInterface.resumePush(getApplicationContext());
        //RegistrationID
        registrationID = JPushInterface.getRegistrationID(getApplicationContext());


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
            editor.putString("pic", getString(com.hyjt.home.R.string.home_base_url) + loginResp.getPic());
            editor.putString("sessionid", loginResp.getSessionId());
            editor.commit();
        }

        String sessionId = loginResp.getSessionId();
        Log.e("sessionId", sessionId);


//        ARouter.getInstance()
//                .build("/app/HomeActivity")
//                .navigation();


        finish();
    }

    @Override
    public void hideDialog() {
        progressDialog.dismiss();
    }

    @Override
    public void onOutLogin(OutLoginEvent outLoginEvent) {
    }
}
