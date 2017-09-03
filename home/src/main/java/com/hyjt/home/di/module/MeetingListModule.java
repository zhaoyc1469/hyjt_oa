package com.hyjt.home.di.module;


import com.hyjt.frame.di.scope.ActivityScope;
import com.hyjt.home.mvp.contract.MeetingListContract;
import com.hyjt.home.mvp.model.MeetingListModel;

import dagger.Module;
import dagger.Provides;

@Module
public class MeetingListModule {
    private MeetingListContract.View view;

    /**
     * 构建MeetingListModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public MeetingListModule(MeetingListContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    MeetingListContract.View provideMeetingListView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    MeetingListContract.Model provideMeetingListModel(MeetingListModel model) {
        return model;
    }
}