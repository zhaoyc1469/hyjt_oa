package com.hyjt.home.di.module;


import com.hyjt.frame.di.scope.ActivityScope;
import com.hyjt.home.mvp.contract.MeetingLogContract;
import com.hyjt.home.mvp.model.MeetingLogModel;

import dagger.Module;
import dagger.Provides;


@Module
public class MeetingLogModule {
    private MeetingLogContract.View view;

    /**
     * 构建MeetingLogModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public MeetingLogModule(MeetingLogContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    MeetingLogContract.View provideMeetingLogView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    MeetingLogContract.Model provideMeetingLogModel(MeetingLogModel model) {
        return model;
    }
}