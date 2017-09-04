package com.hyjt.home.di.component;

import com.hyjt.frame.di.component.AppComponent;
import com.hyjt.frame.di.scope.ActivityScope;

import dagger.Component;

import com.hyjt.home.di.module.ReportTopDetailModule;

import com.hyjt.home.mvp.ui.activity.ReportTopDetailActivity;

@ActivityScope
@Component(modules = ReportTopDetailModule.class, dependencies = AppComponent.class)
public interface ReportTopDetailComponent {
    void inject(ReportTopDetailActivity activity);
}