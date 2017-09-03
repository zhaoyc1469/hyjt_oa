package com.hyjt.home.di.module;

import com.hyjt.frame.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import com.hyjt.home.mvp.contract.SkipReportCreateContract;
import com.hyjt.home.mvp.model.SkipReportCreateModel;


@Module
public class SkipReportCreateModule {
    private SkipReportCreateContract.View view;

    /**
     * 构建SkipReportCreateModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public SkipReportCreateModule(SkipReportCreateContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    SkipReportCreateContract.View provideSkipReportCreateView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    SkipReportCreateContract.Model provideSkipReportCreateModel(SkipReportCreateModel model) {
        return model;
    }
}