package com.hyjt.client.di.component;


import com.hyjt.client.di.module.SplashModule;
import com.hyjt.client.mvp.ui.activity.SplashActivity;
import com.hyjt.frame.di.component.AppComponent;
import com.hyjt.frame.di.scope.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(modules = SplashModule.class, dependencies = AppComponent.class)
public interface SplashComponent {
    void inject(SplashActivity activity);
}