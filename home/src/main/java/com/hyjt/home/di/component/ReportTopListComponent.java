package com.hyjt.home.di.component;

import com.hyjt.frame.di.component.AppComponent;
import com.hyjt.frame.di.scope.ActivityScope;

import dagger.Component;

import com.hyjt.home.di.module.ReportTopListModule;

import com.hyjt.home.mvp.ui.activity.ReportTopListActivity;

@ActivityScope
@Component(modules = ReportTopListModule.class, dependencies = AppComponent.class)
public interface ReportTopListComponent {
    void inject(ReportTopListActivity activity);
}