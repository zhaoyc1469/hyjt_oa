package com.hyjt.home.di.component;


import com.hyjt.frame.di.component.AppComponent;
import com.hyjt.frame.di.scope.ActivityScope;
import com.hyjt.home.di.module.CreateStaffModule;
import com.hyjt.home.mvp.ui.activity.CreateStaffActivity;

import dagger.Component;

@ActivityScope
@Component(modules = CreateStaffModule.class, dependencies = AppComponent.class)
public interface CreateStaffComponent {
    void inject(CreateStaffActivity activity);
}