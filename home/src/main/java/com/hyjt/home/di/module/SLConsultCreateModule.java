package com.hyjt.home.di.module;

import com.hyjt.frame.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import com.hyjt.home.mvp.contract.SLConsultCreateContract;
import com.hyjt.home.mvp.model.SLConsultCreateModel;


@Module
public class SLConsultCreateModule {
    private SLConsultCreateContract.View view;

    /**
     * 构建SLConsultCreateModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public SLConsultCreateModule(SLConsultCreateContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    SLConsultCreateContract.View provideSLConsultCreateView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    SLConsultCreateContract.Model provideSLConsultCreateModel(SLConsultCreateModel model) {
        return model;
    }
}