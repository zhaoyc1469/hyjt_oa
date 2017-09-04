package com.hyjt.home.di.module;

import com.hyjt.frame.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import com.hyjt.home.mvp.contract.ReportTopDetailContract;
import com.hyjt.home.mvp.model.ReportTopDetailModel;


@Module
public class ReportTopDetailModule {
    private ReportTopDetailContract.View view;

    /**
     * 构建ReportTopDetailModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public ReportTopDetailModule(ReportTopDetailContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    ReportTopDetailContract.View provideReportTopDetailView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    ReportTopDetailContract.Model provideReportTopDetailModel(ReportTopDetailModel model) {
        return model;
    }
}