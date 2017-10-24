package com.hyjt.frame.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hyjt.frame.base.delegate.IFragment;
import com.hyjt.frame.integration.lifecycle.FragmentLifecycleable;
import com.hyjt.frame.mvp.IPresenter;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.android.FragmentEvent;
import com.trello.rxlifecycle2.components.support.RxFragment;

import javax.inject.Inject;

import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.Subject;

/**
 * 因为java只能单继承,所以如果有需要继承特定Fragment的三方库,那你就需要自己自定义Fragment
 * 继承于这个特定的Fragment,然后按照将BaseFragment的格式,复制过去,记住一定要实现{@link IFragment}
 */
public abstract class BaseFragment<P extends IPresenter> extends RxFragment implements IFragment, FragmentLifecycleable {
    protected final String TAG = this.getClass().getSimpleName();
    @Inject
    protected P mPresenter;

    private final BehaviorSubject<FragmentEvent> mLifecycleSubject = BehaviorSubject.create();

    @NonNull
    @Override
    public final Subject<FragmentEvent> provideLifecycleSubject() {
        return mLifecycleSubject;
    }


    public BaseFragment() {
        //必须确保在Fragment实例化时setArguments()
        setArguments(new Bundle());
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return initView(inflater, container, savedInstanceState);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
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

}
