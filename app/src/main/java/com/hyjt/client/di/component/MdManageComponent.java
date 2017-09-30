package com.hyjt.client.di.component;

import com.hyjt.frame.di.component.AppComponent;
import com.hyjt.frame.di.scope.ActivityScope;

import dagger.Component;


import com.hyjt.client.di.module.MdManageModule;

import com.hyjt.client.mvp.ui.activity.MdManageActivity;

@ActivityScope
@Component(modules = MdManageModule.class, dependencies = AppComponent.class)
public interface MdManageComponent {
    void inject(MdManageActivity activity);
}