package com.hyjt.client.di.component;

import com.hyjt.client.di.module.LoginModule;
import com.hyjt.client.mvp.ui.activity.LoginActivity;
import com.hyjt.frame.di.component.AppComponent;
import com.hyjt.frame.di.scope.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(modules = LoginModule.class, dependencies = AppComponent.class)
public interface LoginComponent {
    void inject(LoginActivity activity);
}