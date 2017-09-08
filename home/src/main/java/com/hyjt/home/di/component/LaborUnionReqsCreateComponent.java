package com.hyjt.home.di.component;

import com.hyjt.frame.di.component.AppComponent;
import com.hyjt.frame.di.scope.ActivityScope;

import dagger.Component;


import com.hyjt.home.di.module.LaborUnionReqsCreateModule;

import com.hyjt.home.mvp.ui.activity.LaborUnionReqsCreateActivity;

@ActivityScope
@Component(modules = LaborUnionReqsCreateModule.class, dependencies = AppComponent.class)
public interface LaborUnionReqsCreateComponent {
    void inject(LaborUnionReqsCreateActivity activity);
}