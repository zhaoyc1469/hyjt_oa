package com.hyjt.home.di.component;

import com.hyjt.frame.di.component.AppComponent;
import com.hyjt.frame.di.scope.ActivityScope;

import dagger.Component;

import com.hyjt.home.di.module.SkipReportCreateModule;

import com.hyjt.home.mvp.ui.activity.SkipReportCreateActivity;

@ActivityScope
@Component(modules = SkipReportCreateModule.class, dependencies = AppComponent.class)
public interface SkipReportCreateComponent {
    void inject(SkipReportCreateActivity activity);
}