package com.hyjt.home.di.component;

import com.hyjt.frame.di.component.AppComponent;
import com.hyjt.frame.di.scope.ActivityScope;

import dagger.Component;

import com.hyjt.home.di.module.EmailCreateModule;

import com.hyjt.home.mvp.ui.activity.EmailCreateActivity;

@ActivityScope
@Component(modules = EmailCreateModule.class, dependencies = AppComponent.class)
public interface EmailCreateComponent {
    void inject(EmailCreateActivity activity);
}