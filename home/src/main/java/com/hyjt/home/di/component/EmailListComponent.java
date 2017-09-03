package com.hyjt.home.di.component;

import com.hyjt.frame.di.component.AppComponent;
import com.hyjt.frame.di.scope.ActivityScope;

import dagger.Component;

import com.hyjt.home.di.module.EmailListModule;

import com.hyjt.home.mvp.ui.activity.EmailListActivity;

@ActivityScope
@Component(modules = EmailListModule.class, dependencies = AppComponent.class)
public interface EmailListComponent {
    void inject(EmailListActivity activity);
}