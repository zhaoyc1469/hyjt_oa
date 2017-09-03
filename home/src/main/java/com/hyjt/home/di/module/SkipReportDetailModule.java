package com.hyjt.home.di.module;

import com.hyjt.frame.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import com.hyjt.home.mvp.contract.SkipReportDetailContract;
import com.hyjt.home.mvp.model.SkipReportDetailModel;


@Module
public class SkipReportDetailModule {
    private SkipReportDetailContract.View view;

    /**
     * 构建SkipReportDetailModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public SkipReportDetailModule(SkipReportDetailContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    SkipReportDetailContract.View provideSkipReportDetailView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    SkipReportDetailContract.Model provideSkipReportDetailModel(SkipReportDetailModel model) {
        return model;
    }
}