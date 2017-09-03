package com.hyjt.home.di.component;

import com.hyjt.frame.di.component.AppComponent;
import com.hyjt.frame.di.scope.ActivityScope;

import dagger.Component;

import com.hyjt.home.di.module.EmailDetailsModule;

import com.hyjt.home.mvp.ui.activity.EmailDetailsActivity;

@ActivityScope
@Component(modules = EmailDetailsModule.class, dependencies = AppComponent.class)
public interface EmailDetailsComponent {
    void inject(EmailDetailsActivity activity);
}