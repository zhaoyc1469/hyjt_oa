package com.hyjt.home.di.module;

import com.hyjt.frame.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import com.hyjt.home.mvp.contract.SkipReportListContract;
import com.hyjt.home.mvp.model.SkipReportListModel;


@Module
public class SkipReportListModule {
    private SkipReportListContract.View view;

    /**
     * 构建SkipReportListModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public SkipReportListModule(SkipReportListContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    SkipReportListContract.View provideSkipReportListView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    SkipReportListContract.Model provideSkipReportListModel(SkipReportListModel model) {
        return model;
    }
}