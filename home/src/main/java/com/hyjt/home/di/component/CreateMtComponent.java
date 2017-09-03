package com.hyjt.home.di.component;


import com.hyjt.frame.di.component.AppComponent;
import com.hyjt.frame.di.scope.ActivityScope;
import com.hyjt.home.di.module.CreateMtModule;
import com.hyjt.home.mvp.ui.activity.CreateMtActivity;

import dagger.Component;

@ActivityScope
@Component(modules = CreateMtModule.class, dependencies = AppComponent.class)
public interface CreateMtComponent {
    void inject(CreateMtActivity activity);
}