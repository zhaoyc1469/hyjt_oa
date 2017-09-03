package com.hyjt.home.di.component;

import com.hyjt.frame.di.component.AppComponent;
import com.hyjt.frame.di.scope.ActivityScope;
import com.hyjt.home.di.module.StaffMsgModule;
import com.hyjt.home.mvp.ui.activity.StaffMsgActivity;

import dagger.Component;

@ActivityScope
@Component(modules = StaffMsgModule.class, dependencies = AppComponent.class)
public interface StaffMsgComponent {
    void inject(StaffMsgActivity activity);
}