package com.hyjt.home.di.component;

import com.hyjt.frame.di.component.AppComponent;
import com.hyjt.frame.di.scope.ActivityScope;

import dagger.Component;


import com.hyjt.home.di.module.LaborUnionReqsListModule;

import com.hyjt.home.mvp.ui.activity.LaborUnionReqsListActivity;

@ActivityScope
@Component(modules = LaborUnionReqsListModule.class, dependencies = AppComponent.class)
public interface LaborUnionReqsListComponent {
    void inject(LaborUnionReqsListActivity activity);
}