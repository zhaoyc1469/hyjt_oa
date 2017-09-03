package com.hyjt.home.di.component;

import com.hyjt.frame.di.component.AppComponent;
import com.hyjt.frame.di.scope.ActivityScope;

import dagger.Component;

import com.hyjt.home.di.module.SkipReportDetailModule;

import com.hyjt.home.mvp.ui.activity.SkipReportDetailActivity;

@ActivityScope
@Component(modules = SkipReportDetailModule.class, dependencies = AppComponent.class)
public interface SkipReportDetailComponent {
    void inject(SkipReportDetailActivity activity);
}