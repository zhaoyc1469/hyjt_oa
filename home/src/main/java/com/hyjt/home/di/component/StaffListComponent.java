package com.hyjt.home.di.component;


import com.hyjt.frame.di.component.AppComponent;
import com.hyjt.frame.di.scope.ActivityScope;
import com.hyjt.home.di.module.StaffListModule;
import com.hyjt.home.mvp.ui.activity.StaffListActivity;

import dagger.Component;

@ActivityScope
@Component(modules = StaffListModule.class, dependencies = AppComponent.class)
public interface StaffListComponent {
    void inject(StaffListActivity activity);
}