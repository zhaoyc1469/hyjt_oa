package com.hyjt.home.di.module;

import com.hyjt.frame.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import com.hyjt.home.mvp.contract.ReportTopListContract;
import com.hyjt.home.mvp.model.ReportTopListModel;


@Module
public class ReportTopListModule {
    private ReportTopListContract.View view;

    /**
     * 构建ReportTopListModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public ReportTopListModule(ReportTopListContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    ReportTopListContract.View provideReportTopListView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    ReportTopListContract.Model provideReportTopListModel(ReportTopListModel model) {
        return model;
    }
}