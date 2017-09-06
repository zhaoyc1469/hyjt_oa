package com.hyjt.client.mvp.presenter;

import com.hyjt.client.mvp.contract.WorkContract;
import com.hyjt.client.mvp.model.entity.WorkMission;
import com.hyjt.frame.api.parseResponse;
import com.hyjt.frame.di.scope.ActivityScope;
import com.hyjt.frame.event.OutLoginEvent;
import com.hyjt.frame.mvp.BasePresenter;

import org.simple.eventbus.EventBus;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


@ActivityScope
public class WorkPresenter extends BasePresenter<WorkContract.Model, WorkContract.View> {


    @Inject
    public WorkPresenter(WorkContract.Model model, WorkContract.View rootView) {
        super(model, rootView);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public void getMsgNum() {
        mModel.getMdMsgNum()
                .map(new parseResponse<>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<WorkMission>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull WorkMission workMission) {
                        mRootView.showMission(workMission);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    public void ExitLogin() {
        mModel.outLogin()
                .map(new parseResponse<>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Object Obj) {
                        EventBus.getDefault().post(new OutLoginEvent(), "Exit_Login");
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}