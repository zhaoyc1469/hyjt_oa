package com.hyjt.frame.base;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;

import com.hyjt.frame.base.delegate.IActivity;
import com.hyjt.frame.event.OutLoginEvent;
import com.hyjt.frame.integration.lifecycle.ActivityLifecycleable;
import com.hyjt.frame.mvp.IPresenter;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;
import org.simple.eventbus.ThreadMode;

import javax.inject.Inject;

import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.Subject;

import static com.hyjt.frame.utils.ThirdViewUtil.convertAutoView;

/**
 * 因为java只能单继承,所以如果有需要继承特定Activity的三方库,那你就需要自己自定义Activity
 * 继承于这个特定的Activity,然后按照将BaseActivity的格式,复制过去记住一定要实现{@link IActivity}
 */
public abstract class BaseActivity<P extends IPresenter> extends RxAppCompatActivity implements IActivity, ActivityLifecycleable {
    protected final String TAG = this.getClass().getSimpleName();

    private final BehaviorSubject<ActivityEvent> mLifecycleSubject = BehaviorSubject.create();
    @Inject
    protected P mPresenter;

    @NonNull
    @Override
    public final Subject<ActivityEvent> provideLifecycleSubject() {
        return mLifecycleSubject;
    }

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        View view = convertAutoView(name, context, attrs);
        return view == null ? super.onCreateView(name, context, attrs) : view;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        initData(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        if (mPresenter != null) mPresenter.onDestroy();//释放资源
        this.mPresenter = null;
    }

    /**
     * 是否使用eventBus,默认为使用(true)，
     *
     * @return
     */
    @Override
    public boolean useEventBus() {
        return true;
    }

    /**
     * 这个Activity是否会使用Fragment,框架会根据这个属性判断是否注册{@link android.support.v4.app.FragmentManager.FragmentLifecycleCallbacks}
     * 如果返回false,那意味着这个Activity不需要绑定Fragment,那你再在这个Activity中绑定继承于 {} 的Fragment将不起任何作用
     *
     * @return
     */
    @Override
    public boolean useFragment() {
        return true;
    }


    public String getUserName() {
        SharedPreferences sharedPre = getSharedPreferences("config", MODE_PRIVATE);
        String username = sharedPre.getString("username", "");
        return username;
    }


    public void shortToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Subscriber(tag = "Exit_Login", mode = ThreadMode.MAIN)
    public void onOutLogin(OutLoginEvent outLoginEvent){
        finish();
    }

}
