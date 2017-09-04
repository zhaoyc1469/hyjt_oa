package com.hyjt.home.di.module;

import com.hyjt.frame.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import com.hyjt.home.mvp.contract.ReportTopCreateContract;
import com.hyjt.home.mvp.model.ReportTopCreateModel;


@Module
public class ReportTopCreateModule {
    private ReportTopCreateContract.View view;

    /**
     * 构建ReportTopCreateModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public ReportTopCreateModule(ReportTopCreateContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    ReportTopCreateContract.View provideReportTopCreateView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    ReportTopCreateContract.Model provideReportTopCreateModel(ReportTopCreateModel model) {
        return model;
    }
}