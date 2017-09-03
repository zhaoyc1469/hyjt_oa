package com.hyjt.home.di.component;

import com.hyjt.frame.di.component.AppComponent;
import com.hyjt.frame.di.scope.ActivityScope;

import dagger.Component;

import com.hyjt.home.di.module.SLConsultEditModule;

import com.hyjt.home.mvp.ui.activity.SLConsultEditActivity;

@ActivityScope
@Component(modules = SLConsultEditModule.class, dependencies = AppComponent.class)
public interface SLConsultEditComponent {
    void inject(SLConsultEditActivity activity);
}