package com.hyjt.home.di.module;


import com.hyjt.frame.di.scope.ActivityScope;
import com.hyjt.home.mvp.contract.MeetingEditContract;
import com.hyjt.home.mvp.model.MeetingEditModel;

import dagger.Module;
import dagger.Provides;


@Module
public class MeetingEditModule {
    private MeetingEditContract.View view;

    /**
     * 构建MeetingEditModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public MeetingEditModule(MeetingEditContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    MeetingEditContract.View provideMeetingEditView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    MeetingEditContract.Model provideMeetingEditModel(MeetingEditModel model) {
        return model;
    }
}