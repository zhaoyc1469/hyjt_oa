package com.hyjt.home.di.component;

import com.hyjt.frame.di.component.AppComponent;
import com.hyjt.frame.di.scope.ActivityScope;

import dagger.Component;

import com.hyjt.home.di.module.SLConsultCreateModule;

import com.hyjt.home.mvp.ui.activity.SLConsultCreateActivity;

@ActivityScope
@Component(modules = SLConsultCreateModule.class, dependencies = AppComponent.class)
public interface SLConsultCreateComponent {
    void inject(SLConsultCreateActivity activity);
}