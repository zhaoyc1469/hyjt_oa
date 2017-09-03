package com.hyjt.home.di.module;

import com.hyjt.frame.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import com.hyjt.home.mvp.contract.SLConsultListContract;
import com.hyjt.home.mvp.model.SLConsultListModel;


@Module
public class SLConsultListModule {
    private SLConsultListContract.View view;

    /**
     * 构建SLConsultListModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public SLConsultListModule(SLConsultListContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    SLConsultListContract.View provideSLConsultListView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    SLConsultListContract.Model provideSLConsultListModel(SLConsultListModel model) {
        return model;
    }
}