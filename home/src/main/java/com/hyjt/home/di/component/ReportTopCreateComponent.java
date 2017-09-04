package com.hyjt.home.di.component;

import com.hyjt.frame.di.component.AppComponent;
import com.hyjt.frame.di.scope.ActivityScope;

import dagger.Component;

import com.hyjt.home.di.module.ReportTopCreateModule;

import com.hyjt.home.mvp.ui.activity.ReportTopCreateActivity;

@ActivityScope
@Component(modules = ReportTopCreateModule.class, dependencies = AppComponent.class)
public interface ReportTopCreateComponent {
    void inject(ReportTopCreateActivity activity);
}