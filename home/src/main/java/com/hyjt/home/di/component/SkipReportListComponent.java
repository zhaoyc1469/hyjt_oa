package com.hyjt.home.di.component;

import com.hyjt.frame.di.component.AppComponent;
import com.hyjt.frame.di.scope.ActivityScope;

import dagger.Component;

import com.hyjt.home.di.module.SkipReportListModule;

import com.hyjt.home.mvp.ui.activity.SkipReportListActivity;

@ActivityScope
@Component(modules = SkipReportListModule.class, dependencies = AppComponent.class)
public interface SkipReportListComponent {
    void inject(SkipReportListActivity activity);
}