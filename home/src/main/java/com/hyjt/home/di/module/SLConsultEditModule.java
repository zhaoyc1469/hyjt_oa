package com.hyjt.home.di.module;

import com.hyjt.frame.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import com.hyjt.home.mvp.contract.SLConsultEditContract;
import com.hyjt.home.mvp.model.SLConsultEditModel;


@Module
public class SLConsultEditModule {
    private SLConsultEditContract.View view;

    /**
     * 构建SLConsultEditModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public SLConsultEditModule(SLConsultEditContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    SLConsultEditContract.View provideSLConsultEditView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    SLConsultEditContract.Model provideSLConsultEditModel(SLConsultEditModel model) {
        return model;
    }
}